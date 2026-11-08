package fr.shopwise.api.model;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity @Table(name="appointment")
public class Appointment {
 public enum Status { PLANNED, HONORED, CANCELLED }
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) @JoinColumn(name="customer_id",nullable=false) private Customer customer;
 @Column(name="appointment_date",nullable=false) private LocalDate date;
 @Column(nullable=false) private String service;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private Status status=Status.PLANNED;
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public Customer getCustomer(){return customer;} public void setCustomer(Customer c){this.customer=c;}
 public LocalDate getDate(){return date;} public void setDate(LocalDate date){this.date=date;}
 public String getService(){return service;} public void setService(String service){this.service=service;}
 public Status getStatus(){return status;} public void setStatus(Status status){this.status=status;}
}
