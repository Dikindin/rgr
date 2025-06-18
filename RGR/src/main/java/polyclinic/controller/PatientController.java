package polyclinic.controller;

import polyclinic.entity.Patient;
import polyclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patients")
public class PatientController extends AbstractController<Patient> {

    @Autowired
    private PatientService service;

    // Просмотр пациентов и изменение учёта – менеджер и админ
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @Override
    @GetMapping
    public ResponseEntity<List<Patient>> get() {
        return super.get();
    }

    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable long id) {
        return super.getById(id);
    }

    // Регистрация пациента (постановка на учёт)
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<String> register(@Validated @RequestBody Patient patient) {
        // При регистрации ставим registered = true
        patient.setRegistered(true);
        getService().save(patient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Снятие с учёта пациента
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @PutMapping("/unregister/{id}")
    public ResponseEntity<String> unregister(@PathVariable long id) {
        Patient patient = getService().read(id);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        patient.setRegistered(false);
        getService().save(patient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public PatientService getService() {
        return service;
    }
}
