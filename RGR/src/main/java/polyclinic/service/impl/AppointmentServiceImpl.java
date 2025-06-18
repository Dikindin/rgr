package polyclinic.service.impl;

import polyclinic.entity.Appointment;
import polyclinic.repository.AppointmentRepository;
import polyclinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment read(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> read() {
        return appointmentRepository.findAll();
    }

    @Override
    public void save(Appointment entity) {
        if(entity.getId() != null) {
            Appointment existing = appointmentRepository.findById(entity.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
            existing.setDoctor(entity.getDoctor());
            existing.setPatient(entity.getPatient());
            existing.setAppointmentDateTime(entity.getAppointmentDateTime());
            existing.setStatus(entity.getStatus());
            appointmentRepository.save(existing);
        } else {
            appointmentRepository.save(entity);
        }
    }

    @Override
    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<Appointment> findByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> findByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
}
