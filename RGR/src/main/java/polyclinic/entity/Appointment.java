package polyclinic.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull(message = "Доктор обязателен")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "Пациент обязателен")
    private Patient patient;

    @NotNull(message = "Дата и время приема обязательны")
    @Column(name = "appointment_date_time")
    private LocalDateTime appointmentDateTime;

    // Статус приема (например, "Запланирован", "Завершен")
    @NotNull(message = "Статус должен быть указан")
    private String status;

    // Геттеры и сеттеры
    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }
    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
