package com.meditrack.views.patients;

import com.meditrack.controllers.PatientController;
import com.meditrack.models.Patient;

import javax.swing.*;

public class AddPatientView extends JFrame {
    public AddPatientView() {
        setTitle("Add Patient");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JTextField gender = new JTextField();
        JTextField contact = new JTextField();
        JTextField address = new JTextField();

        JButton submit = new JButton("Submit");

        submit.addActionListener(e -> {
            Patient p = new Patient(0, name.getText(), Integer.parseInt(age.getText()),
                    gender.getText(), contact.getText(), address.getText());

            if (new PatientController().addPatient(p)) {
                JOptionPane.showMessageDialog(this, "Patient added!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add.");
            }
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Name")); add(name);
        add(new JLabel("Age")); add(age);
        add(new JLabel("Gender")); add(gender);
        add(new JLabel("Contact")); add(contact);
        add(new JLabel("Address")); add(address);
        add(submit);

        setVisible(true);
    }
}
