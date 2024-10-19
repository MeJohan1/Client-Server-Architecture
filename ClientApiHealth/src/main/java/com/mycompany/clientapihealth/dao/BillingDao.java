package com.mycompany.clientapihealth.dao;

import com.mycompany.clientapihealth.models.Billing;
import com.mycompany.clientapihealth.models.Patient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BillingDao {

    private List<Billing> billings = new ArrayList<>();

    public BillingDao() {
        initializeBillings();
    }

    private void initializeBillings() {
        Patient patient1 = new Patient("No Medical History", "Good", "Johan", "07474653738", "Canning Town", 1);
        Patient patient2 = new Patient("Back-Pain", "Good", "Rafi", "07846637738", "Stratford", 2);
        Patient patient3 = new Patient("Long term suffer fever", "Bad", "Hasan", "0738497383", "Greenwich", 3);
        Patient patient4 = new Patient("1 year ago done Surgery", "Good", "Abir", "07537464847", "Oxford Circus", 4);
        Patient patient5 = new Patient("Allergy", "Fine", "Jabir", "0763537383", "Bond Street", 5);

        billings.add(new Billing(1, patient1, 70.00, 70.00, 0.00, LocalDate.of(2024, 6, 15)));
        billings.add(new Billing(2, patient2, 50.00, 10.00, 40.00, LocalDate.of(2023, 9, 16)));
        billings.add(new Billing(3, patient3, 30.00, 15.00, 15.00, LocalDate.of(2024, 1, 10)));
        billings.add(new Billing(4, patient4, 300.00, 150.00, 150.00, LocalDate.of(2024, 3, 10)));
        billings.add(new Billing(5, patient5, 20.00, 15.00, 5.00, LocalDate.of(2024, 2, 10)));
    }

    public void addBilling(Billing billing) {
        validateBilling(billing);
        if (billings.stream().anyMatch(b -> b.getId() == billing.getId())) {
            throw new IllegalArgumentException("Billing already exists with the same ID");
        }
        billings.add(billing);
    }

    public Billing getBilling(int id) {
        return billings.stream()
                .filter(billing -> billing.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateBilling(Billing updatedBilling) {
        validateBilling(updatedBilling);
        int index = billings.indexOf(billings.stream()
                    .filter(billing -> billing.getId() == updatedBilling.getId())
                    .findFirst()
                    .orElse(null));
        if (index != -1) {
            billings.set(index, updatedBilling);
        } else {
            throw new IllegalArgumentException("No existing billing with ID: " + updatedBilling.getId());
        }
    }

    private void validateBilling(Billing billing) {
        if (billing == null) {
            throw new IllegalArgumentException("Billing cannot be null.");
        }
        if (billing.getAmountCharged() < 0) {
            throw new IllegalArgumentException("Amount Charged cannot be negative.");
        }
        if (billing.getAmountPaid() < 0 || billing.getAmountPaid() > billing.getAmountCharged()) {
            throw new IllegalArgumentException("Invalid Amount Paid. It cannot be negative or greater than Amount Charged.");
        }
    }

    public void deleteBilling(int id) {
        boolean removed = billings.removeIf(billing -> billing.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("Failed to delete billing with ID: " + id + ". No such billing found.");
        }
    }

    public List<Billing> getAllBillings() {
        return new ArrayList<>(billings);
    }
}
