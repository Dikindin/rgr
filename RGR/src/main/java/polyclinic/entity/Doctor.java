package polyclinic.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "doctors")
public class Doctor extends AbstractEntity {

    @NotBlank(message = "Имя врача не может быть пустым")
    @Size(max = 100, message = "Имя врача не может быть длиннее 100 символов")
    private String name;

    @NotBlank(message = "Специализация не может быть пустой")
    @Size(max = 100, message = "Специализация не может быть длиннее 100 символов")
    private String specialization;

    @Column(name = "experience_years")
    private Integer experienceYears;

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public Integer getExperienceYears() {
        return experienceYears;
    }
    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }
}
