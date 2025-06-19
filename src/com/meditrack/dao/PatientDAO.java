package com.meditrack.dao;

import com.meditrack.models.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    public boolean addPatient(Patient patient) {
        String sql = "INSERT INTO patients(name, age, gender, contact, address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getContact());
            stmt.setString(5, patient.getAddress());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Add Patient Error: " + e.getMessage());
        }
        return false;
    }

    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Patient p = new Patient(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("contact"),
                    rs.getString("address")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Get Patients Error: " + e.getMessage());
        }
        return list;
    }
}
