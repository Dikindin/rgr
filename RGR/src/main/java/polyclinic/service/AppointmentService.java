package polyclinic.service;

import polyclinic.entity.Appointment;
import java.util.List;

public interface AppointmentService extends Service<Appointment> {
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPatientId(Long patientId);
}
