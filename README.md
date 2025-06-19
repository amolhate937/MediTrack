# 🏥 MediTrack

**MediTrack** is a Java-based hospital management desktop application that helps streamline medical operations like patient management, doctor listings, appointment scheduling, billing, and more. Built using Java Swing for the GUI and MySQL for the backend database.

---

## 📌 Features

- 🔐 **User Authentication**
  - Login/Register as Admin or Receptionist
- 👩‍⚕️ **Patient Management**
  - Add new patients
  - View patient list
- 🩺 **Doctor Management**
  - View available doctors
- 📅 **Appointment Scheduling**
  - Book and view appointments
- 💳 **Billing System**
  - Generate and manage patient bills

---

## 🛠️ Tech Stack

- **Language:** Java (JDK 17+)
- **GUI:** Java Swing
- **Database:** MySQL
- **JDBC Driver:** MySQL Connector/J
- **Build & Run Tool:** Command-line based

---

## 📂 Project Structure

C:.
│   README.md
│   TestDriverLoad.class
│   TestDriverLoad.java
│
├───bin
│   └───com
│       └───meditrack
│           ├───controllers
│           ├───dao
│           ├───models
│           │       User.class
│           │
│           └───views
│               ├───appointments
│               ├───auth
│               ├───billing
│               ├───doctors
│               └───patients
├───lib
│       mysql-connector-j-8.4.0.jar
│
└───src
    │   sources.txt
    │
    └───com
        └───meditrack
            │   Main.class
            │   Main.java
            │   TestDriverLoad.java
            │
            ├───controllers
            │       AppointmentController.class
            │       AppointmentController.java
            │       AuthController.class
            │       AuthController.java
            │       BillingController.class
            │       BillingController.java
            │       DoctorController.class
            │       DoctorController.java
            │       PatientController.class
            │       PatientController.java
            │
            ├───dao
            │       AppointmentDAO.class
            │       AppointmentDAO.java
            │       BillingDAO.class
            │       BillingDAO.java
            │       DatabaseConnection.class
            │       DatabaseConnection.java
            │       DoctorDAO.class
            │       DoctorDAO.java
            │       PatientDAO.class
            │       PatientDAO.java
            │       TestDriverLoad.class
            │       UserDAO.class
            │       UserDAO.java
            │
            ├───models
            │       Appointment.class
            │       Appointment.java
            │       Bill.class
            │       Bill.java
            │       Doctor.class
            │       Doctor.java
            │       Patient.class
            │       Patient.java
            │       User.class
            │       User.java
            │
            ├───resources
            │   └───images
            └───views
                ├───appointments
                │       ScheduleAppointmentView.class
                │       ScheduleAppointmentView.java
                │
                ├───auth
                │       DashboardView$1.class
                │       DashboardView.class
                │       DashboardView.java
                │       LoginView.class
                │       LoginView.java
                │       RegisterView$1.class
                │       RegisterView.class
                │       RegisterView.java
                │
                ├───billing
                │       BillingView.class
                │       BillingView.java
                │
                ├───doctors
                │       DoctorListView.class
                │       DoctorListView.java
                │
                └───patients
                        AddPatientView.class
                        AddPatientView.java
                        PatientListView.class
                        PatientListView.java

---

## ⚙️ Setup Instructions

### Clone the Repository


### Compile the Project 

javac -cp ".;lib/mysql-connector-j-8.4.0.jar" -d bin src/com/meditrack/**/*.java

### Run the Project

java -cp "bin;lib/mysql-connector-j-8.4.0.jar" com.meditrack.Main


### MySQL Setup 

-- 🚀 Create and Use the Database
CREATE DATABASE IF NOT EXISTS meditrack;
USE meditrack;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'user'
);
ALTER TABLE users ADD COLUMN email VARCHAR(255);

-- 🧍 Patients Table
CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    contact VARCHAR(15),
    address VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE patients ADD contact VARCHAR(15);
ALTER TABLE patients ADD address VARCHAR(100);

-- 🩺 Doctors Table
DROP TABLE IF EXISTS doctors;
CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL
);

-- 📅 Appointments Table
DROP TABLE IF EXISTS appointments;
CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    date DATETIME NOT NULL,
    reason TEXT,
    status VARCHAR(20) DEFAULT 'Scheduled',
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE
);

-- 💳 Bills Table
DROP TABLE IF EXISTS bills;
CREATE TABLE bills (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    billing_date DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE
);

-- ✅ Seed Default Admin User (Avoid duplicate if re-run)
INSERT IGNORE INTO users (username, password, role)
VALUES ('admin@example.com', 'admin123', 'admin');

-- ✅ Sample Doctors
INSERT INTO doctors (name, specialization) VALUES 
('Dr. A. Sharma', 'Cardiologist'),
('Dr. R. Mehta', 'Dermatologist'),
('Dr. K. Verma', 'Neurologist');

-- ✅ Sample Patients
INSERT INTO patients (name, age, gender) VALUES 
('John Doe', 30, 'Male'),
('Priya Shah', 25, 'Female'),
('Amit Kumar', 40, 'Male');

-- ✅ Sample Appointment
INSERT INTO appointments (patient_id, doctor_id, date, reason, status)
VALUES (1, 1, '2025-06-15 10:30:00', 'Chest pain consultation', 'Scheduled');

-- ✅ Sample Bill
INSERT INTO bills (patient_id, amount)
VALUES (1, 1500.00);

SELECT * FROM users WHERE username = 'admin@example.com';
SELECT * FROM users;


### Screenshots

