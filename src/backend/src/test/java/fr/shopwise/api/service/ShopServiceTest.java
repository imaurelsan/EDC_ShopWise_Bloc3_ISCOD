package fr.shopwise.api.service;
import fr.shopwise.api.model.*;import fr.shopwise.api.repo.*;
import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;import static org.mockito.Mockito.*;
import java.time.LocalDate;import java.util.Optional;
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
}
