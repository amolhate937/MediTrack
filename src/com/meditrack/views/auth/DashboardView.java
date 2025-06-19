package com.meditrack.views.auth;

import com.meditrack.models.User;
import com.meditrack.views.patients.*;
import com.meditrack.views.doctors.*;
import com.meditrack.views.appointments.*;
import com.meditrack.views.billing.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardView extends JFrame {
    public DashboardView(User user) {
        setTitle("MediTrack - Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/icon.png"));
            setIconImage(imgIcon.getImage());
        } catch (Exception e) {
            System.out.println("Icon not found, using default");
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 248, 250));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(245, 248, 250));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername() + "!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(51, 51, 51));

        JLabel roleLabel = new JLabel("Role: " + user.getRole());
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        roleLabel.setForeground(new Color(102, 102, 102));

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBackground(new Color(245, 248, 250));
        userInfoPanel.add(welcomeLabel);
        userInfoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        userInfoPanel.add(roleLabel);

        headerPanel.add(userInfoPanel, BorderLayout.WEST);

        JLabel dateLabel = new JLabel(new SimpleDateFormat("EEE, MMM d, yyyy").format(new Date()));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateLabel.setForeground(new Color(102, 102, 102));
        headerPanel.add(dateLabel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        buttonPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        buttonPanel.setBackground(new Color(245, 248, 250));

        Font btnFont = new Font("Segoe UI", Font.BOLD, 16);
        Color btnColor = new Color(66, 135, 245);
        Color btnHoverColor = new Color(50, 115, 220);
        Dimension btnSize = new Dimension(200, 100);

        JButton patientBtn = createStyledButton("Add Patient", "➕", btnFont, btnColor, btnHoverColor, btnSize);
        JButton viewPatientsBtn = createStyledButton("View Patients", "👨", btnFont, btnColor, btnHoverColor, btnSize);
        JButton doctorsBtn = createStyledButton("View Doctors", "🧑", btnFont, btnColor, btnHoverColor, btnSize);
        JButton apptBtn = createStyledButton("Schedule", "📅", btnFont, btnColor, btnHoverColor, btnSize);
        JButton billBtn = createStyledButton("Generate Bill", "💰", btnFont, btnColor, btnHoverColor, btnSize);
        JButton reportsBtn = createStyledButton("Reports", "📊", btnFont, btnColor, btnHoverColor, btnSize);

        buttonPanel.add(patientBtn);
        buttonPanel.add(viewPatientsBtn);
        buttonPanel.add(doctorsBtn);
        buttonPanel.add(apptBtn);
        buttonPanel.add(billBtn);
        buttonPanel.add(reportsBtn);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(245, 248, 250));
        centerPanel.add(buttonPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(new Color(245, 248, 250));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        logoutBtn.setBackground(new Color(220, 53, 69));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        footerPanel.add(logoutBtn);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // Actions
        patientBtn.addActionListener(e -> SwingUtilities.invokeLater(AddPatientView::new));
        viewPatientsBtn.addActionListener(e -> SwingUtilities.invokeLater(PatientListView::new));
        doctorsBtn.addActionListener(e -> SwingUtilities.invokeLater(DoctorListView::new));
        apptBtn.addActionListener(e -> SwingUtilities.invokeLater(ScheduleAppointmentView::new));
        billBtn.addActionListener(e -> SwingUtilities.invokeLater(BillingView::new));
        logoutBtn.addActionListener(e -> {
            this.dispose();
            SwingUtilities.invokeLater(() -> new LoginView());
        });

        add(mainPanel);
        setVisible(true);
    }

    private JButton createStyledButton(String text, String icon, Font font, Color bgColor, Color hoverColor, Dimension size) {
        JButton button = new JButton("<html><center>" + icon + "<br>" + text + "</center></html>");
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setPreferredSize(size);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }
}
