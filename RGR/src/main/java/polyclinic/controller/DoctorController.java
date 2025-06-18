package polyclinic.controller;

import polyclinic.entity.Doctor;
import polyclinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/doctors")
public class DoctorController extends AbstractController<Doctor> {

    @Autowired
    private DoctorService service;

    // Просмотр списка врачей (доступ guest)
    @Override
    @GetMapping
    public ResponseEntity<List<Doctor>> get() {
        return super.get();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable long id) {
        return super.getById(id);
    }

    // К CRUD-операциям на врачей доступ только для админа
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> post(@Validated @RequestBody Doctor doctor) {
        getService().save(doctor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<String> put(@Validated @RequestBody Doctor doctor) {
        getService().save(doctor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        getService().delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public DoctorService getService() {
        return service;
    }
}
