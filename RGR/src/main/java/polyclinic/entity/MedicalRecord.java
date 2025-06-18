package polyclinic.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_records")
public class MedicalRecord extends AbstractEntity {

    // Каждый медицинский документ привязан к пациенту
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Текст истории болезни
    @Lob
    @NotBlank(message = "Запись в медицинской карте не может быть пустой")
    private String description;

    @Column(name = "record_date")
    private LocalDateTime recordDate;

    // Геттеры и сеттеры
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getRecordDate() {
        return recordDate;
    }
    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }
}
