package com.meditrack.controllers;

import com.meditrack.dao.UserDAO;
import com.meditrack.models.User;
import com.meditrack.views.auth.DashboardView;

import javax.swing.*;

public class AuthController {
    private UserDAO userDAO = new UserDAO();

    // ✅ LOGIN method with frame disposal
    public void login(JFrame loginFrame, String username, String password, String role) {
        if (isEmpty(username) || isEmpty(password) || isEmpty(role)) {
            showError("All fields are required.");
            return;
        }

        User user = userDAO.login(username.trim(), password.trim(), role.trim());

        if (user != null) {
            if (!user.getRole().equalsIgnoreCase(role.trim())) {
                showError("Role mismatch! Please select correct role.");
                return;
            }

            // ✅ Login successful: Close login frame & open dashboard
            SwingUtilities.invokeLater(() -> {
                loginFrame.dispose();
                new DashboardView(user);
            });
        } else {
            showError("Invalid username or password.");
        }
    }

    // ✅ REGISTER method
    public boolean register(User user) {
        if (user == null || isEmpty(user.getUsername()) || isEmpty(user.getPassword()) || isEmpty(user.getRole())) {
            showError("All fields are required.");
            return false;
        }

        if (user.getPassword().length() < 6) {
            showError("Password must be at least 6 characters.");
            return false;
        }

        boolean result = userDAO.registerUser(user);
        if (result) {
            showSuccess("Registration successful!");
            return true;
        } else {
            showError("Registration failed. Username may already exist.");
            return false;
        }
    }

    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private void showError(String msg) {
        SwingUtilities.invokeLater(() ->
            JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void showSuccess(String msg) {
        SwingUtilities.invokeLater(() ->
            JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE)
        );
    }
}
