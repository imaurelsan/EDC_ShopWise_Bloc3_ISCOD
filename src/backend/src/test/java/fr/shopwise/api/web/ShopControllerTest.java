package fr.shopwise.api.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fr.shopwise.api.model.Appointment;
import fr.shopwise.api.model.Customer;
import fr.shopwise.api.repo.CustomerRepository;
import fr.shopwise.api.service.ShopService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// Tests du contrôleur : on simule le service et le repository avec Mockito,
// du coup pas besoin de base de données pour les lancer.
class ShopControllerTest {

    private MockMvc mvc;
    private ShopService service;
    private CustomerRepository customers;

    @BeforeEach
    void preparer() {
        service = Mockito.mock(ShopService.class);
        customers = Mockito.mock(CustomerRepository.class);
        ShopController controller = new ShopController(service, customers);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listeDesClientsRenvoie200() throws Exception {
        when(customers.findAll()).thenReturn(List.of(new Customer()));

        mvc.perform(get("/api/clients"))
           .andExpect(status().isOk());
    }

    @Test
    void creationClientValideRenvoie201() throws Exception {
        when(service.saveCustomer(any(Customer.class))).thenReturn(new Customer());

        String json = "{\"shopId\":1,\"name\":\"Awa Diop\",\"email\":\"awa@mail.fr\",\"phone\":\"0600000000\"}";

        mvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
           .andExpect(status().isCreated());
    }

    @Test
    void creationClientSansNomRenvoie400() throws Exception {
        // le nom est vide, donc la validation @NotBlank doit refuser
        String json = "{\"shopId\":1,\"name\":\"\",\"email\":\"awa@mail.fr\"}";

        mvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
           .andExpect(status().isBadRequest());
    }

    @Test
    void lectureDunClientRenvoie200() throws Exception {
        when(service.customer(1L)).thenReturn(new Customer());

        mvc.perform(get("/api/clients/1"))
           .andExpect(status().isOk());
    }

    @Test
    void modificationDunClientRenvoie200() throws Exception {
        when(service.customer(1L)).thenReturn(new Customer());
        when(service.saveCustomer(any(Customer.class))).thenReturn(new Customer());

        String json = "{\"shopId\":1,\"name\":\"Awa Diop\",\"email\":\"awa@mail.fr\",\"phone\":\"0611111111\"}";

        mvc.perform(put("/api/clients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
           .andExpect(status().isOk());
    }

    @Test
    void creationRendezVousRenvoie201() throws Exception {
        when(service.createAppointment(eq(1L), any(LocalDate.class), any(LocalTime.class), eq("Coupe")))
            .thenReturn(new Appointment());

        String json = "{\"customerId\":1,\"date\":\"2026-12-01\",\"time\":\"10:30:00\",\"service\":\"Coupe\"}";

        mvc.perform(post("/api/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
           .andExpect(status().isCreated());
    }

    @Test
    void listeDesRendezVousRenvoie200() throws Exception {
        when(service.listAppointments(any(), any(), any())).thenReturn(List.of());

        mvc.perform(get("/api/appointments"))
           .andExpect(status().isOk());
    }

    @Test
    void changementDeStatutRenvoie200() throws Exception {
        when(service.changeStatus(eq(1L), any())).thenReturn(new Appointment());

        // je prends la première valeur de l'enum pour ne pas deviner son nom
        String statut = Appointment.Status.values()[0].name();
        String json = "{\"status\":\"" + statut + "\"}";

        mvc.perform(patch("/api/appointments/1/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
           .andExpect(status().isOk());
    }

    @Test
    void fideliteRenvoie200() throws Exception {
        when(service.loyalty(1L)).thenReturn(null);

        mvc.perform(get("/api/clients/1/loyalty"))
           .andExpect(status().isOk());
    }
}