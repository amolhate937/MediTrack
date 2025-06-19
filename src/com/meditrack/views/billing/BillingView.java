package com.meditrack.views.billing;

import com.meditrack.controllers.BillingController;
import com.meditrack.models.Bill;

import javax.swing.*;
import java.time.LocalDate;

public class BillingView extends JFrame {
    public BillingView() {
        setTitle("Generate Bill");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JTextField apptId = new JTextField();
        JTextField amount = new JTextField();
        JCheckBox paidBox = new JCheckBox("Paid");

        JButton generate = new JButton("Generate");

        generate.addActionListener(e -> {
            Bill bill = new Bill(
                0,
                Integer.parseInt(apptId.getText()),
                Double.parseDouble(amount.getText()),
                paidBox.isSelected(),
                LocalDate.now()
            );

            if (new BillingController().generateBill(bill)) {
                JOptionPane.showMessageDialog(this, "Bill Generated!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to generate.");
            }
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(new JLabel("Appointment ID")); add(apptId);
        add(new JLabel("Amount")); add(amount);
        add(paidBox);
        add(generate);

        setVisible(true);
    }
}
