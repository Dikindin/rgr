package polyclinic.service.impl;

import polyclinic.entity.Patient;
import polyclinic.repository.PatientRepository;
import polyclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient read(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Patient> read() {
        return patientRepository.findAll();
    }

    @Override
    public void save(Patient entity) {
        if(entity.getId() != null) {
            Patient existing = patientRepository.findById(entity.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
            existing.setFullName(entity.getFullName());
            existing.setDateOfBirth(entity.getDateOfBirth());
            existing.setRegistered(entity.getRegistered());
            patientRepository.save(existing);
        } else {
            patientRepository.save(entity);
        }
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
