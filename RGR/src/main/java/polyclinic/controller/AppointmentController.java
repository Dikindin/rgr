package polyclinic.controller;

import polyclinic.entity.Appointment;
import polyclinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController extends AbstractController<Appointment> {

    @Autowired
    private AppointmentService service;

    // Просмотр приёмов (доступ для менеджера и админа)
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @Override
    @GetMapping
    public ResponseEntity<List<Appointment>> get() {
        return super.get();
    }

    // Запись на приём (назначение, ставится менеджером)
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @PostMapping("/schedule")
    public ResponseEntity<String> schedule(@Validated @RequestBody Appointment appointment) {
        getService().save(appointment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Фиксация факта приема (например, изменение статуса на "Завершен")
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @PutMapping("/complete/{id}")
    public ResponseEntity<String> complete(@PathVariable long id) {
        Appointment appointment = getService().read(id);
        if(appointment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        appointment.setStatus("Завершён");
        getService().save(appointment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public AppointmentService getService() {
        return service;
    }
}
