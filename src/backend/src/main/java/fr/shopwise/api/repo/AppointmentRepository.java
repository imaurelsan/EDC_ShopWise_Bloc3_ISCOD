package fr.shopwise.api.repo;
import fr.shopwise.api.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate; import java.util.List;
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
 List<Appointment> findAllByDateAndStatusAndCustomer_Id(LocalDate date, Appointment.Status status, Long customerId);
 List<Appointment> findAllByDateAndStatus(LocalDate date, Appointment.Status status);
 List<Appointment> findAllByDateAndCustomer_Id(LocalDate date, Long customerId);
 List<Appointment> findAllByStatusAndCustomer_Id(Appointment.Status status, Long customerId);
 List<Appointment> findAllByDate(LocalDate date);
 List<Appointment> findAllByStatus(Appointment.Status status);
 List<Appointment> findAllByCustomer_Id(Long customerId);
}
