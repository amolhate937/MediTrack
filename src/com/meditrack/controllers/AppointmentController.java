package com.meditrack.controllers;

import com.meditrack.dao.AppointmentDAO;
import com.meditrack.models.Appointment;
import java.util.List;

public class AppointmentController {
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    public boolean scheduleAppointment(Appointment appointment) {
        return appointmentDAO.scheduleAppointment(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDAO.getAllAppointments();
    }

    public boolean schedule(Appointment appt) {
    // This method is not implemented yet, but it should call the scheduleAppointment method
        throw new UnsupportedOperationException("Unimplemented method 'schedule'");
    }
}