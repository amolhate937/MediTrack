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
'''
MediTrack/
├── bin/
│ └── com/meditrack/
│ ├── controllers/
│ ├── dao/
│ ├── models/
│ └── views/
│ ├── appointments/
│ ├── auth/
│ ├── billing/
│ ├── doctors/
│ └── patients/
├── lib/
│ └── mysql-connector-j-8.4.0.jar
├── src/
│ └── com/meditrack/
│ ├── Main.java
│ ├── TestDriverLoad.java
│ ├── controllers/
│ ├── dao/
│ ├── models/
│ ├── resources/
│ │ └── images/
│ └── views/
│ ├── appointments/
│ ├── auth/
│ ├── billing/
│ ├── doctors/
│ └── patients/
├── TestDriverLoad.java
├── TestDriverLoad.class
└── README.md

'''
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
![Screenshot 2025-06-19 164320](https://github.com/user-attachments/assets/0ad1d4ff-f979-4275-bd2e-006132c6e914)
![Screenshot 2025-06-19 164200](https://github.com/user-attachments/assets/586dd03a-1aa7-424f-8569-8aea93fa0024)
![Screenshot 2025-06-19 164209](https://github.com/user-attachments/assets/cc4377ca-ce43-4dea-aa5a-84520908a218)
![Screenshot 2025-06-19 164219](https://github.com/user-attachments/assets/e07d8a4a-9709-4f69-b416-510df4361777)
![Screenshot 2025-06-19 164233](https://github.com/user-attachments/assets/9c1f6fe2-583d-4322-a4fe-843e702b1edf)
![Screenshot 2025-06-19 164244](https://github.com/user-attachments/assets/a70c7130-c71a-464b-a931-4bca24e3f5a3)






