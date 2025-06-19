package com.meditrack.dao;

import com.meditrack.models.Bill;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {
    public boolean generateBill(Bill bill) {
        String sql = "INSERT INTO bills(appointment_id, amount, paid, billingDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bill.getAppointmentId());
            stmt.setDouble(2, bill.getAmount());
            stmt.setBoolean(3, bill.isPaid());
            stmt.setDate(4, Date.valueOf(bill.getBillingDate()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Generate Bill Error: " + e.getMessage());
        }
        return false;
    }

    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                bills.add(new Bill(
                    rs.getInt("id"),
                    rs.getInt("appointment_id"),
                    rs.getDouble("amount"),
                    rs.getBoolean("paid"),
                    rs.getDate("billingDate").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Get Bills Error: " + e.getMessage());
        }
        return bills;
    }
}
