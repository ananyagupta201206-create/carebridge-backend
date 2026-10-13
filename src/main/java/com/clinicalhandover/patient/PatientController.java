package com.clinicalhandover.patient;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientRepository repository;
    public PatientController(PatientRepository repository) { this.repository = repository; }

    @GetMapping public List<Patient> all() { return repository.findAll(); }
    @GetMapping("/{id}") public Patient one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Patient create(@Valid @RequestBody Patient patient) {
        patient.setId(null); return repository.save(patient);
    }
    @PutMapping("/{id}") public Patient update(@PathVariable Long id, @Valid @RequestBody Patient input) {
        Patient p = one(id);
        p.setPatientCode(input.getPatientCode()); p.setFullName(input.getFullName());
        p.setAge(input.getAge()); p.setWard(input.getWard()); p.setDiagnosis(input.getDiagnosis()); p.setStatus(input.getStatus());
        return repository.save(p);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) {
        repository.delete(one(id));
    }
}
