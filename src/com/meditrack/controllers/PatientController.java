package com.meditrack.controllers;

import com.meditrack.dao.PatientDAO;
import com.meditrack.models.Patient;
import java.util.List;

public class PatientController {
    private PatientDAO patientDAO = new PatientDAO();

    public boolean addPatient(Patient patient) {
        return patientDAO.addPatient(patient);
    }

    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }
}