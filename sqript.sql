
DROP DATABASE IF EXISTS polyclinic_system;
CREATE DATABASE polyclinic_system DEFAULT CHARACTER SET utf8;
USE polyclinic_system;

-- Таблица для врачей
DROP TABLE IF EXISTS doctors;
CREATE TABLE doctors (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  specialization VARCHAR(100) NOT NULL,
  experience_years INT NOT NULL,
  PRIMARY KEY (id)
);

-- Таблица для пациентов
DROP TABLE IF EXISTS patients;
CREATE TABLE patients (
  id BIGINT NOT NULL AUTO_INCREMENT,
  full_name VARCHAR(150) NOT NULL,
  date_of_birth DATE NOT NULL,
  registered BOOLEAN NOT NULL DEFAULT TRUE,
  PRIMARY KEY (id)
);

-- Таблица для записей на прием (Appointments)
DROP TABLE IF EXISTS appointments;
CREATE TABLE appointments (
  id BIGINT NOT NULL AUTO_INCREMENT,
  doctor_id BIGINT NOT NULL,
  patient_id BIGINT NOT NULL,
  appointment_date_time DATETIME NOT NULL,
  status VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE,
  FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE
);

-- Таблица для медицинских карт (Medical Records)
DROP TABLE IF EXISTS medical_records;
CREATE TABLE medical_records (
  id BIGINT NOT NULL AUTO_INCREMENT,
  patient_id BIGINT NOT NULL,
  description TEXT NOT NULL,
  record_date DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE
);

-- Таблица пользователей (Users)
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(64) NOT NULL,
  password VARCHAR(64) NOT NULL,
  authority VARCHAR(64) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (username)
);

--------------------------------------------------------------------------------
-- Заполнение таблицы doctors (минимум 5 записей)
INSERT INTO doctors (name, specialization, experience_years) VALUES 
  ('Ivan Ivanov', 'Therapist', 15),
  ('Anna Petrova', 'Cardiologist', 10),
  ('Maxim Smirnov', 'Neurologist', 8),
  ('Elena Volkova', 'Dermatologist', 12),
  ('Sergey Sidorov', 'Orthopedist', 20);

--------------------------------------------------------------------------------
-- Заполнение таблицы patients (минимум 5 записей)
INSERT INTO patients (full_name, date_of_birth, registered) VALUES 
  ('Sergey Kuznetsov', '1985-06-15', TRUE),
  ('Maria Lebedeva', '1993-09-22', TRUE),
  ('Dmitry Vasiliev', '1978-02-07', TRUE),
  ('Olga Ivanova', '1980-11-30', TRUE),
  ('Alexey Smirnov', '2000-03-12', FALSE);

--------------------------------------------------------------------------------
-- Заполнение таблицы appointments (минимум 5 записей)
INSERT INTO appointments (doctor_id, patient_id, appointment_date_time, status) VALUES
  (1, 1, '2025-06-18 10:00:00', 'Scheduled'),
  (2, 2, '2025-06-18 12:30:00', 'Scheduled'),
  (3, 3, '2025-06-19 09:15:00', 'Completed'),
  (4, 4, '2025-06-20 11:00:00', 'Scheduled'),
  (1, 5, '2025-06-21 14:30:00', 'Cancelled');

--------------------------------------------------------------------------------
-- Заполнение таблицы medical_records (минимум 5 записей)
INSERT INTO medical_records (patient_id, description, record_date) VALUES
  (1, 'Routine check-up. All parameters are normal.', '2025-06-18 10:05:00'),
  (2, 'Cardiology consult: confirmed mild arrhythmia.', '2025-06-18 12:45:00'),
  (3, 'Neurological exam - no abnormalities detected.', '2025-06-19 09:30:00'),
  (4, 'Diagnosis: mild eczema. Prescribed topical treatment.', '2025-06-20 11:15:00'),
  (5, 'Follow-up appointment cancelled due to rescheduling.', '2025-06-21 14:45:00');

--------------------------------------------------------------------------------

INSERT INTO users (username, password, authority) VALUES
  ('admin', '$2a$10$W9F/wYqh1qnMT0uQToZa..J2db3BCtsQx3zmWjUZvl.Gs/NK4CpZW', 'ROLE_ADMIN'),
  ('manager', '$2a$10$W9F/wYqh1qnMT0uQToZa..J2db3BCtsQx3zmWjUZvl.Gs/NK4CpZW', 'ROLE_MANAGER');

