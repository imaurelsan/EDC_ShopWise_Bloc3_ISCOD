package fr.shopwise.api.model;
import jakarta.persistence.*;
@Entity @Table(name="customer", uniqueConstraints=@UniqueConstraint(columnNames={"shop_id","email"}))
public class Customer {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="shop_id", nullable=false) private Long shopId;
 @Column(nullable=false) private String name;
 @Column(nullable=false) private String email;
 private String phone;
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public Long getShopId(){return shopId;} public void setShopId(Long shopId){this.shopId=shopId;}
 public String getName(){return name;} public void setName(String name){this.name=name;}
 public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
 public String getPhone(){return phone;} public void setPhone(String phone){this.phone=phone;}
}
