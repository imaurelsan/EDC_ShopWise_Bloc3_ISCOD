package fr.shopwise.api.web;
import fr.shopwise.api.model.*; import fr.shopwise.api.repo.*; import fr.shopwise.api.service.*;
import jakarta.validation.Valid; import jakarta.validation.constraints.*;
import org.springframework.web.bind.annotation.*; import org.springframework.http.HttpStatus;
import java.time.LocalDate; import java.util.List;
@RestController @RequestMapping("/api") @CrossOrigin(origins="*")
public class ShopController {
 private final ShopService service; private final CustomerRepository customers;
 public ShopController(ShopService s,CustomerRepository c){service=s;customers=c;}
 record CustomerBody(@NotNull Long shopId,@NotBlank String name,@Email @NotBlank String email,String phone){}
 record AppointmentBody(@NotNull Long customerId,@NotNull LocalDate date,@NotBlank String service){}
 record StatusBody(@NotNull Appointment.Status status){}
 @PostMapping("/clients") @ResponseStatus(HttpStatus.CREATED) public Customer create(@Valid @RequestBody CustomerBody b){return service.saveCustomer(toCustomer(b));}
 @GetMapping("/clients") public List<Customer> clients(){return customers.findAll();}
 @GetMapping("/clients/{id}") public Customer get(@PathVariable Long id){return service.customer(id);}
 @PutMapping("/clients/{id}") public Customer update(@PathVariable Long id,@Valid @RequestBody CustomerBody b){Customer c=service.customer(id);c.setShopId(b.shopId());c.setName(b.name());c.setEmail(b.email());c.setPhone(b.phone());return service.saveCustomer(c);}
 private Customer toCustomer(CustomerBody b){Customer c=new Customer();c.setShopId(b.shopId());c.setName(b.name());c.setEmail(b.email());c.setPhone(b.phone());return c;}
 @PostMapping("/appointments") @ResponseStatus(HttpStatus.CREATED) public Appointment createAppointment(@Valid @RequestBody AppointmentBody b){return service.createAppointment(b.customerId(),b.date(),b.service());}
 @GetMapping("/appointments") public List<Appointment> appointments(@RequestParam(required=false) LocalDate date,@RequestParam(required=false) Appointment.Status status,@RequestParam(required=false) Long customerId){return service.listAppointments(date,status,customerId);}
 @PatchMapping("/appointments/{id}/status") public Appointment status(@PathVariable Long id,@Valid @RequestBody StatusBody b){return service.changeStatus(id,b.status());}
 @GetMapping("/clients/{id}/loyalty") public ShopService.LoyaltySummary loyalty(@PathVariable Long id){return service.loyalty(id);}
}
