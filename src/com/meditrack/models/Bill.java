package com.meditrack.models;

import java.time.LocalDate;

public class Bill {
    private int id;
    private int appointmentId;
    private double amount;
    private boolean paid;
    private LocalDate billingDate;

    public Bill() {}

    public Bill(int id, int appointmentId, double amount, boolean paid, LocalDate billingDate) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.paid = paid;
        this.billingDate = billingDate;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAppointmentId() { return appointmentId; }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }

    public LocalDate getBillingDate() { return billingDate; }
    public void setBillingDate(LocalDate billingDate) { this.billingDate = billingDate; }
}
