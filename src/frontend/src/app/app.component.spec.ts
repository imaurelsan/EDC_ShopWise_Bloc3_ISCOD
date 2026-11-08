import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { AppComponent } from './app.component';
describe('AppComponent',()=>{
 beforeEach(async()=>{await TestBed.configureTestingModule({imports:[AppComponent],providers:[provideHttpClient(),provideHttpClientTesting()]}).compileComponents();});
 it('affiche le titre et charge les listes',()=>{const f=TestBed.createComponent(AppComponent);f.detectChanges();const h=TestBed.inject(HttpTestingController);h.expectOne('/api/clients').flush([]);h.expectOne('/api/appointments').flush([]);expect(f.nativeElement.querySelector('.brand').textContent).toContain('shopwise');h.verify();});
 it('traduit les trois statuts métier',()=>{const c=TestBed.createComponent(AppComponent).componentInstance;expect(c.label('PLANNED')).toBe('À venir');expect(c.label('HONORED')).toBe('Honoré');expect(c.label('CANCELLED')).toBe('Annulé');});
});
