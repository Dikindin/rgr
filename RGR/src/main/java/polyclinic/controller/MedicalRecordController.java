package polyclinic.controller;

import polyclinic.entity.MedicalRecord;
import polyclinic.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/medical-records")
public class MedicalRecordController extends AbstractController<MedicalRecord> {

    @Autowired
    private MedicalRecordService service;

    // Просмотр медицинской карты пациента (доступ для менеджера и админа)
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @Override
    @GetMapping
    public ResponseEntity<List<MedicalRecord>> get() {
        return super.get();
    }

    // Добавление записи в медицинскую карту (менеджер)
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @PostMapping
    public ResponseEntity<String> post(@Validated @RequestBody MedicalRecord record) {
        getService().save(record);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Редактирование записи в медкарте
    @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
    @PutMapping
    public ResponseEntity<String> put(@Validated @RequestBody MedicalRecord record) {
        getService().save(record);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Удаление записи в медкарте (при необходимости)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        getService().delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public MedicalRecordService getService() {
        return service;
    }
}
