import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
interface Customer { id:number; shopId:number; name:string; email:string; phone:string; }
interface Appointment { id:number; date:string; service:string; status:'PLANNED'|'HONORED'|'CANCELLED'; customer:Customer; }
interface Loyalty { customerId:number; balance:number; transactions:{pointsDelta:number;reason:string;createdAt:string}[]; }
@Component({selector:'app-root',standalone:true,imports:[CommonModule,FormsModule],template:`
<header><div class="brand">shopwise<span>.</span></div><nav><a href="#clients">Clients</a><a href="#agenda">Rendez-vous</a><a href="#fidelite">Fidélité</a></nav></header>
<main><section class="hero"><div><p class="eyebrow">CHEZ MARIE · ESPACE COMMERÇANT</p><h1>Le quotidien du commerce,<br>un peu plus simple.</h1><p>Les clients, les rendez-vous et les points réunis au même endroit.</p></div><div class="hero-note">Une vue claire.<br><b>Moins d’administratif.</b></div></section>
<p class="error" *ngIf="error">{{error}}</p>
<section id="clients" class="panel"><div class="section-title"><div><p class="eyebrow">CARNET CLIENT</p><h2>Ajouter un client</h2></div></div>
<form (ngSubmit)="saveCustomer()"><label>Nom<input name="name" [(ngModel)]="customerForm.name" required></label><label>Email<input name="email" type="email" [(ngModel)]="customerForm.email" required></label><label>Téléphone<input name="phone" [(ngModel)]="customerForm.phone"></label><button>Enregistrer</button></form>
<div class="client-row" *ngFor="let c of customers"><span><b>{{c.name}}</b><small>{{c.email}} · {{c.phone || 'Téléphone non renseigné'}}</small></span><button class="soft" (click)="selectCustomer(c)">Voir fidélité</button></div></section>
<section id="agenda" class="panel"><div class="section-title"><div><p class="eyebrow">PLANNING</p><h2>Nouveau rendez-vous</h2></div></div>
<form (ngSubmit)="saveAppointment()"><label>Client<select name="customer" [(ngModel)]="appointmentForm.customerId" required><option [ngValue]="null">Choisir…</option><option *ngFor="let c of customers" [ngValue]="c.id">{{c.name}}</option></select></label><label>Date<input name="date" type="date" [(ngModel)]="appointmentForm.date" required></label><label>Service<input name="service" [(ngModel)]="appointmentForm.service" required></label><button>Planifier</button></form>
<div class="filters"><label>Filtrer par date<input type="date" [(ngModel)]="filterDate" (change)="loadAppointments()"></label><label>Statut<select [(ngModel)]="filterStatus" (change)="loadAppointments()"><option value="">Tous</option><option value="PLANNED">À venir</option><option value="HONORED">Honoré</option><option value="CANCELLED">Annulé</option></select></label></div>
<div class="appointment" *ngFor="let a of appointments"><span class="date">{{a.date}}</span><span><b>{{a.customer.name}}</b><small>{{a.service}}</small></span><span class="badge">{{label(a.status)}}</span><button *ngIf="a.status!=='HONORED'" class="soft" (click)="setStatus(a,'HONORED')">Honoré</button><button *ngIf="a.status==='PLANNED'" class="soft" (click)="setStatus(a,'CANCELLED')">Annuler</button></div></section>
<section id="fidelite" class="panel loyalty" *ngIf="loyalty"><div><p class="eyebrow">FIDÉLITÉ · {{selectedName}}</p><h2>{{loyalty.balance}} <small>points</small></h2></div><div><h3>Historique</h3><p *ngFor="let t of loyalty.transactions">+{{t.pointsDelta}} · {{t.reason}}</p></div></section>
<footer>ShopWise · Prototype de démonstration</footer></main>`})
export class AppComponent implements OnInit {
 private http=inject(HttpClient); private api='/api'; customers:Customer[]=[];appointments:Appointment[]=[];loyalty:Loyalty|null=null;selectedName='';error='';
 customerForm={shopId:1,name:'',email:'',phone:''};appointmentForm:{customerId:number|null,date:string,service:string}={customerId:null,date:'',service:''};filterDate='';filterStatus='';
 ngOnInit():void {this.loadCustomers();this.loadAppointments();}
 loadCustomers():void {this.http.get<Customer[]>(this.api+'/clients').subscribe({next:v=>this.customers=v,error:()=>this.error='Impossible de joindre les clients. Vérifiez que l’API est lancée.'});}
 loadAppointments():void {let p=new HttpParams();if(this.filterDate)p=p.set('date',this.filterDate);if(this.filterStatus)p=p.set('status',this.filterStatus);this.http.get<Appointment[]>(this.api+'/appointments',{params:p}).subscribe({next:v=>this.appointments=v,error:()=>this.error='La liste des rendez-vous ne peut pas être chargée.'});}
 saveCustomer():void {this.http.post<Customer>(this.api+'/clients',this.customerForm).subscribe({next:()=>{this.customerForm={shopId:1,name:'',email:'',phone:''};this.loadCustomers();this.error='';},error:()=>this.error='Vérifiez les informations du client.'});}
 saveAppointment():void {if(!this.appointmentForm.customerId)return;this.http.post(this.api+'/appointments',this.appointmentForm).subscribe({next:()=>{this.loadAppointments();this.appointmentForm={customerId:null,date:'',service:''};this.error='';},error:()=>this.error='Le rendez-vous n’a pas pu être créé.'});}
 setStatus(a:Appointment,status:'HONORED'|'CANCELLED'):void {this.http.patch(this.api+'/appointments/'+a.id+'/status',{status}).subscribe({next:()=>this.loadAppointments(),error:()=>this.error='La mise à jour a échoué.'});}
 selectCustomer(c:Customer):void {this.selectedName=c.name;this.http.get<Loyalty>(this.api+'/clients/'+c.id+'/loyalty').subscribe({next:v=>this.loyalty=v,error:()=>this.error='Historique de fidélité indisponible.'});}
 label(s:Appointment['status']):string {return s==='PLANNED'?'À venir':s==='HONORED'?'Honoré':'Annulé';}
}
