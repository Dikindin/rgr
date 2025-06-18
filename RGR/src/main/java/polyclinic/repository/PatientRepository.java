package polyclinic.repository;

import polyclinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Дополнительные методы запроса можно добавить при необходимости
}
