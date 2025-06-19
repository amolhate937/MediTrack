package com.meditrack;

import com.meditrack.dao.UserDAO;
import com.meditrack.views.auth.LoginView;
import com.meditrack.views.auth.RegisterView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                UserDAO userDAO = new UserDAO();
                if (userDAO.hasUsers()) {
                    new LoginView();      // Existing user? show login
                } else {
                    new RegisterView();   // First-time setup? show register
                }
            } catch (Exception e) {
                System.err.println("Application startup failed: " + e.getMessage());
                // Fallback to login view if there's an error checking users
                new LoginView();
            }
        });
    }
}