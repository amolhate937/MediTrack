package com.meditrack.controllers;

import com.meditrack.dao.BillingDAO;
import com.meditrack.models.Bill;
import java.util.List;

public class BillingController {
    private BillingDAO billingDAO = new BillingDAO();

    public boolean generateBill(Bill bill) {
        return billingDAO.generateBill(bill);
    }

    public List<Bill> getAllBills() {
        return billingDAO.getAllBills();
    }
}