error id: file:///C:/Users/imaur/Desktop/shopwise-projet/src/backend/src/test/java/fr/shopwise/api/service/ShopServiceTest.java:java/util/Optional#
file:///C:/Users/imaur/Desktop/shopwise-projet/src/backend/src/test/java/fr/shopwise/api/service/ShopServiceTest.java
empty definition using pc, found symbol in pc: java/util/Optional#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 259
uri: file:///C:/Users/imaur/Desktop/shopwise-projet/src/backend/src/test/java/fr/shopwise/api/service/ShopServiceTest.java
text:
```scala
package fr.shopwise.api.service;
import fr.shopwise.api.model.*;import fr.shopwise.api.repo.*;
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import static org.mockito.Mockito.*;
import java.time.LocalDate;import java.util.@@Optional;
class ShopServiceTest {
 @Test void honorerUnRendezVousCrediteUneSeuleFois(){
  CustomerRepository cr=mock(CustomerRepository.class);AppointmentRepository ar=mock(AppointmentRepository.class);LoyaltyRepository lr=mock(LoyaltyRepository.class);
  Customer c=new Customer(); c.setId(4L);Appointment a=new Appointment();a.setId(9L);a.setCustomer(c);a.setDate(LocalDate.now());
  when(ar.findById(9L)).thenReturn(Optional.of(a));when(ar.save(any())).thenAnswer(x->x.getArgument(0));when(lr.existsByAppointment_Id(9L)).thenReturn(false);
  ShopService s=new ShopService(cr,ar,lr);s.changeStatus(9L,Appointment.Status.HONORED);s.changeStatus(9L,Appointment.Status.HONORED);
  verify(lr,times(1)).save(any(LoyaltyTransaction.class));
 }
 @Test void planifieNePeutPasEtreUnStatutCible(){
  ShopService s=new ShopService(mock(CustomerRepository.class),mock(AppointmentRepository.class),mock(LoyaltyRepository.class));
  assertThrows(IllegalArgumentException.class,()->s.changeStatus(1L,Appointment.Status.PLANNED));
 }
 @Test void rendezVousAbsentEstSignale(){
  AppointmentRepository ar=mock(AppointmentRepository.class);when(ar.findById(2L)).thenReturn(Optional.empty());
  ShopService s=new ShopService(mock(CustomerRepository.class),ar,mock(LoyaltyRepository.class));
  assertThrows(ResourceMissingException.class,()->s.changeStatus(2L,Appointment.Status.CANCELLED));
 }
 @Test void filtresDeRendezVousSontTransmisAuDepot(){
  CustomerRepository cr=mock(CustomerRepository.class);AppointmentRepository ar=mock(AppointmentRepository.class);LoyaltyRepository lr=mock(LoyaltyRepository.class);ShopService s=new ShopService(cr,ar,lr);
  LocalDate d=LocalDate.of(2026,11,8);Appointment.Status st=Appointment.Status.PLANNED;
  s.listAppointments(d,st,1L);verify(ar).findAllByDateAndStatusAndCustomer_Id(d,st,1L);
  s.listAppointments(d,st,null);verify(ar).findAllByDateAndStatus(d,st);
  s.listAppointments(d,null,1L);verify(ar).findAllByDateAndCustomer_Id(d,1L);
  s.listAppointments(null,st,1L);verify(ar).findAllByStatusAndCustomer_Id(st,1L);
  s.listAppointments(d,null,null);verify(ar).findAllByDate(d);
  s.listAppointments(null,st,null);verify(ar).findAllByStatus(st);
  s.listAppointments(null,null,1L);verify(ar).findAllByCustomer_Id(1L);
  s.listAppointments(null,null,null);verify(ar).findAll();
 }
 @Test void soldeEstLaSommeDesMouvements(){
  CustomerRepository cr=mock(CustomerRepository.class);LoyaltyRepository lr=mock(LoyaltyRepository.class);Customer c=new Customer();c.setId(5L);
  when(cr.findById(5L)).thenReturn(Optional.of(c));LoyaltyTransaction t=new LoyaltyTransaction();t.setPointsDelta(10);
  when(lr.findAllByCustomer_IdOrderByCreatedAtDesc(5L)).thenReturn(java.util.List.of(t));
  ShopService.LoyaltySummary result=new ShopService(cr,mock(AppointmentRepository.class),lr).loyalty(5L);
  assertEquals(10,result.balance());assertEquals(1,result.transactions().size());
 }
 @Test void creeRendezVousApresAvoirTrouveLeClient(){
  CustomerRepository cr=mock(CustomerRepository.class);AppointmentRepository ar=mock(AppointmentRepository.class);Customer c=new Customer();c.setId(1L);
  when(cr.findById(1L)).thenReturn(Optional.of(c));when(ar.save(any())).thenAnswer(x->x.getArgument(0));
  Appointment a=new ShopService(cr,ar,mock(LoyaltyRepository.class)).createAppointment(1L,LocalDate.now(),java.time.LocalTime.NOON,"Test");
  assertEquals(c,a.getCustomer());assertEquals(java.time.LocalTime.NOON,a.getTime());
 }

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/util/Optional#