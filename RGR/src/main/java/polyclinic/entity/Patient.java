package polyclinic.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient extends AbstractEntity {

    @NotBlank(message = "ФИО пациента не может быть пустым")
    @Size(max = 150, message = "ФИО пациента не может быть длиннее 150 символов")
    @Column(name = "full_name")
    private String fullName;

    @Past(message = "Дата рождения должна быть в прошлом")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    // true - пациент находится на учёте, false - снят с учёта
    private Boolean registered;

    // Геттеры и сеттеры
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Boolean getRegistered() {
        return registered;
    }
    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }
}
