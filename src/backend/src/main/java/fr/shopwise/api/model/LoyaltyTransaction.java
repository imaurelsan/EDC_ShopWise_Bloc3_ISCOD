package fr.shopwise.api.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="loyalty_transaction", uniqueConstraints=@UniqueConstraint(columnNames="appointment_id"))
public class LoyaltyTransaction {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) @JoinColumn(name="customer_id",nullable=false) private Customer customer;
 @OneToOne @JoinColumn(name="appointment_id",unique=true) private Appointment appointment;
 @Column(name="points_delta",nullable=false) private Integer pointsDelta;
 @Column(nullable=false) private String reason;
 @Column(name="created_at",nullable=false) private Instant createdAt=Instant.now();
 public Long getId(){return id;} public Customer getCustomer(){return customer;} public void setCustomer(Customer c){customer=c;}
 public Appointment getAppointment(){return appointment;} public void setAppointment(Appointment a){appointment=a;}
 public Integer getPointsDelta(){return pointsDelta;} public void setPointsDelta(Integer p){pointsDelta=p;}
 public String getReason(){return reason;} public void setReason(String r){reason=r;}
 public Instant getCreatedAt(){return createdAt;}
}
