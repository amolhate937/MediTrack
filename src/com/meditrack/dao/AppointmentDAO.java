package com.meditrack.dao;

import com.meditrack.models.Appointment;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    public boolean scheduleAppointment(Appointment appt) {
        String sql = "INSERT INTO appointments(patient_id, doctor_id, appointmentDateTime, reason, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appt.getPatientId());
            stmt.setInt(2, appt.getDoctorId());
            stmt.setTimestamp(3, Timestamp.valueOf(appt.getAppointmentDateTime()));
            stmt.setString(4, appt.getReason());
            stmt.setString(5, appt.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Schedule Error: " + e.getMessage());
        }
        return false;
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Appointment a = new Appointment(
                    rs.getInt("id"),
                    rs.getInt("patient_id"),
                    rs.getInt("doctor_id"),
                    rs.getTimestamp("appointmentDateTime").toLocalDateTime(),
                    rs.getString("reason"),
                    rs.getString("status")
                );
                list.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Get Appointments Error: " + e.getMessage());
        }
        return list;
    }
}
