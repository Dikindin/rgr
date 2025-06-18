package polyclinic.service.impl;

import polyclinic.entity.Doctor;
import polyclinic.repository.DoctorRepository;
import polyclinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor read(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Doctor> read() {
        return doctorRepository.findAll();
    }

    @Override
    public void save(Doctor entity) {
        if (entity.getId() != null) {
            Doctor existing = doctorRepository.findById(entity.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
            existing.setName(entity.getName());
            existing.setSpecialization(entity.getSpecialization());
            existing.setExperienceYears(entity.getExperienceYears());
            doctorRepository.save(existing);
        } else {
            doctorRepository.save(entity);
        }
    }

    @Override
    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public List<Doctor> findBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }
}
