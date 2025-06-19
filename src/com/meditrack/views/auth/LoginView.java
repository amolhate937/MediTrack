package com.meditrack.views.auth;

import com.meditrack.controllers.AuthController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JFrame {
    public LoginView() {
        setTitle("MediTrack - Login");
        setSize(450, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set application icon
        try {
            ImageIcon imgIcon = new ImageIcon(getClass().getResource("/images/icon.png"));
            setIconImage(imgIcon.getImage());
        } catch (Exception e) {
            System.out.println("Icon not found, using default");
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel titleLabel = new JLabel("MediTrack Login");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(66, 135, 245));
        headerPanel.add(titleLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Form
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username field (used for DB login)
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(userLabel, gbc);

        JTextField userField = new JTextField(20);
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userField.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 1;
        formPanel.add(userField, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Role selection
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(roleLabel, gbc);

        JComboBox<String> roleBox = new JComboBox<>(new String[]{"Admin", "Receptionist"});
        roleBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        roleBox.setPreferredSize(new Dimension(250, 30));
        gbc.gridx = 1;
        formPanel.add(roleBox, gbc);

        // Login button
        JButton loginBtn = new JButton("Login");
        styleButton(loginBtn, new Color(66, 135, 245));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(loginBtn, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Footer with register link
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        JLabel registerLabel = new JLabel("Don't have an account?");
        registerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JButton registerLink = new JButton("Register here");
        registerLink.setFont(new Font("Segoe UI", Font.BOLD, 12));
        registerLink.setBorderPainted(false);
        registerLink.setContentAreaFilled(false);
        registerLink.setForeground(new Color(66, 135, 245));
        registerLink.setCursor(new Cursor(Cursor.HAND_CURSOR));

        footerPanel.add(registerLabel);
        footerPanel.add(registerLink);

        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        // ✅ Action listeners
        loginBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String role = (String) roleBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 🔗 Uses AuthController (which internally uses UserDAO & DatabaseConnection)
            AuthController authController = new AuthController();
            authController.login(this, username, password, role);
        });

        registerLink.addActionListener(e -> {
            dispose();
            new RegisterView();
        });

        add(mainPanel);
        setVisible(true);
    }

    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
    }

    // ✅ Entry point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginView::new);
    }
}
