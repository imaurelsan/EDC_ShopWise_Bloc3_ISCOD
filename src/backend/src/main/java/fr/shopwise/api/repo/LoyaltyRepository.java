package fr.shopwise.api.repo;
import fr.shopwise.api.model.LoyaltyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LoyaltyRepository extends JpaRepository<LoyaltyTransaction,Long> {
 List<LoyaltyTransaction> findAllByCustomer_IdOrderByCreatedAtDesc(Long customerId);
 boolean existsByAppointment_Id(Long appointmentId);
}
