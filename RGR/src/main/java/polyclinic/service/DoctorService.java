package polyclinic.service;

import polyclinic.entity.Doctor;
import java.util.List;

public interface DoctorService extends Service<Doctor> {
    List<Doctor> findBySpecialization(String specialization);
}
