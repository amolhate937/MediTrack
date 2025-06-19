package com.meditrack.views.patients;

import com.meditrack.controllers.PatientController;
import com.meditrack.models.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PatientListView extends JFrame {
    public PatientListView() {
        setTitle("Patient List");
        setSize(600, 400);
        setLocationRelativeTo(null);

        String[] cols = {"ID", "Name", "Age", "Gender", "Contact", "Address"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        List<Patient> patients = new PatientController().getAllPatients();
        for (Patient p : patients) {
            model.addRow(new Object[]{
                p.getId(), p.getName(), p.getAge(), p.getGender(), p.getContact(), p.getAddress()
            });
        }

        JTable table = new JTable(model);
        add(new JScrollPane(table));
        setVisible(true);
    }
}
