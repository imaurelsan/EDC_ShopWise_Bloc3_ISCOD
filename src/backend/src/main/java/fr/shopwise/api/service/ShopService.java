package fr.shopwise.api.service;

import fr.shopwise.api.model.*;
import fr.shopwise.api.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ShopService {

    private final CustomerRepository customers;
    private final AppointmentRepository appointments;
    private final LoyaltyRepository loyalty;

    public ShopService(CustomerRepository c, AppointmentRepository a, LoyaltyRepository l) {
        customers = c;
        appointments = a;
        loyalty = l;
    }

    public Customer saveCustomer(Customer c) {
        return customers.save(c);
    }

    public Customer customer(Long id) {
        return customers.findById(id)
                .orElseThrow(() -> new ResourceMissingException("Client introuvable"));
    }

    public Appointment createAppointment(Long customerId, LocalDate date, LocalTime time, String service) {
        Customer c = customer(customerId);
        Appointment a = new Appointment();
        a.setCustomer(c);
        a.setDate(date);
        a.setTime(time);
        a.setService(service);
        return appointments.save(a);
    }

    public List<Appointment> listAppointments(LocalDate date, Appointment.Status status, Long customerId) {
        if (date != null && status != null && customerId != null)
            return appointments.findAllByDateAndStatusAndCustomer_Id(date, status, customerId);
        if (date != null && status != null)
            return appointments.findAllByDateAndStatus(date, status);
        if (date != null && customerId != null)
            return appointments.findAllByDateAndCustomer_Id(date, customerId);
        if (status != null && customerId != null)
            return appointments.findAllByStatusAndCustomer_Id(status, customerId);
        if (date != null)
            return appointments.findAllByDate(date);
        if (status != null)
            return appointments.findAllByStatus(status);
        if (customerId != null)
            return appointments.findAllByCustomer_Id(customerId);
        return appointments.findAll();
    }

    @Transactional
    public Appointment changeStatus(Long id, Appointment.Status status) {
        // On vérifie d'abord le statut demandé, avant même d'aller chercher le rendez-vous
        if (status == Appointment.Status.PLANNED) {
            throw new IllegalArgumentException("Le statut cible doit être HONORED ou CANCELLED");
        }

        Appointment a = appointments.findById(id)
                .orElseThrow(() -> new ResourceMissingException("Rendez-vous introuvable"));

        // Les points ne sont donnés qu'à la première fois où le RDV passe en "honoré"
        boolean firstHonor = status == Appointment.Status.HONORED
                && a.getStatus() != Appointment.Status.HONORED
                && !loyalty.existsByAppointment_Id(id);

        a.setStatus(status);
        Appointment saved = appointments.save(a);

        if (firstHonor) {
            LoyaltyTransaction t = new LoyaltyTransaction();
            t.setCustomer(a.getCustomer());
            t.setAppointment(a);
            t.setPointsDelta(10);
            t.setReason("Rendez-vous honoré");
            loyalty.save(t);
        }
        return saved;
    }

    public LoyaltySummary loyalty(Long customerId) {
        Customer c = customer(customerId);
        List<LoyaltyTransaction> items = loyalty.findAllByCustomer_IdOrderByCreatedAtDesc(customerId);
        int balance = items.stream().mapToInt(LoyaltyTransaction::getPointsDelta).sum();
        return new LoyaltySummary(c.getId(), balance, items);
    }

    public record LoyaltySummary(Long customerId, int balance, List<LoyaltyTransaction> transactions) {
    }
}