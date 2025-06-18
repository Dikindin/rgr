package polyclinic.service;

import polyclinic.entity.MedicalRecord;
import java.util.List;

public interface MedicalRecordService extends Service<MedicalRecord> {
    List<MedicalRecord> findByPatientId(Long patientId);
}
