package com.meditrack.views.doctors;

import com.meditrack.controllers.DoctorController;
import com.meditrack.models.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class DoctorListView extends JFrame {
    public DoctorListView() {
        setTitle("Doctor List");
        setSize(500, 300);
        setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Specialization", "Contact"}, 0);
        List<Doctor> doctors = new DoctorController().getAllDoctors();

        for (Doctor d : doctors) {
            model.addRow(new Object[]{d.getId(), d.getName(), d.getSpecialization(), d.getContact()});
        }

        JTable table = new JTable(model);
        add(new JScrollPane(table));
        setVisible(true);
    }
}
