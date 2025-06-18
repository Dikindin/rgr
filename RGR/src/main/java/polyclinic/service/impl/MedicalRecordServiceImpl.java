package polyclinic.service.impl;

import polyclinic.entity.MedicalRecord;
import polyclinic.repository.MedicalRecordRepository;
import polyclinic.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord read(Long id) {
        return medicalRecordRepository.findById(id).orElse(null);
    }

    @Override
    public List<MedicalRecord> read() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public void save(MedicalRecord entity) {
        if(entity.getId() != null) {
            MedicalRecord existing = medicalRecordRepository.findById(entity.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Medical Record not found"));
            existing.setPatient(entity.getPatient());
            existing.setDescription(entity.getDescription());
            existing.setRecordDate(entity.getRecordDate());
            medicalRecordRepository.save(existing);
        } else {
            medicalRecordRepository.save(entity);
        }
    }

    @Override
    public void delete(Long id) {
        medicalRecordRepository.deleteById(id);
    }

    @Override
    public List<MedicalRecord> findByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }
}
