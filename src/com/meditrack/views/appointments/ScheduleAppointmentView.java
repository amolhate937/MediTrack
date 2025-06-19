package com.meditrack.views.appointments;

import com.meditrack.controllers.AppointmentController;
import com.meditrack.models.Appointment;

import javax.swing.*;
import java.time.LocalDateTime;

public class ScheduleAppointmentView extends JFrame {
    public ScheduleAppointmentView() {
        setTitle("Schedule Appointment");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JTextField patientId = new JTextField();
        JTextField doctorId = new JTextField();
        JTextField datetime = new JTextField("YYYY-MM-DDTHH:MM"); // ISO format
        JTextField reason = new JTextField();

        JButton submit = new JButton("Schedule");

        submit.addActionListener(e -> {
            Appointment appt = new Appointment(
                0,
                Integer.parseInt(patientId.getText()),
                Integer.parseInt(doctorId.getText()),
                LocalDateTime.parse(datetime.getText()),
                reason.getText(),
                "Scheduled"
            );

            if (new AppointmentController().schedule(appt)) {
                JOptionPane.showMessageDialog(this, "Appointment scheduled!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to schedule.");
            }
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Patient ID")); add(patientId);
        add(new JLabel("Doctor ID")); add(doctorId);
        add(new JLabel("DateTime (ISO)")); add(datetime);
        add(new JLabel("Reason")); add(reason);
        add(submit);

        setVisible(true);
    }
}
