package com.meditrack.controllers;

import com.meditrack.dao.DoctorDAO;
import com.meditrack.models.Doctor;
import java.util.List;

public class DoctorController {
    private DoctorDAO doctorDAO = new DoctorDAO();

    public List<Doctor> getAllDoctors() {
        return doctorDAO.getAllDoctors();
    }
}
