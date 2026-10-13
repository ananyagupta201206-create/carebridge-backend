package com.clinicalhandover.handover;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/handovers")
public class HandoverController {
    private final HandoverRepository repository;
    public HandoverController(HandoverRepository repository) { this.repository = repository; }

    @GetMapping public List<Handover> all() { return repository.findAll(); }
    @GetMapping("/{id}") public Handover one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Handover not found"));
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Handover create(@Valid @RequestBody Handover h) {
        h.setId(null); h.setCreatedAt(java.time.LocalDateTime.now()); return repository.save(h);
    }
    @PutMapping("/{id}") public Handover update(@PathVariable Long id, @Valid @RequestBody Handover input) {
        Handover h = one(id);
        h.setPatientName(input.getPatientName()); h.setShift(input.getShift()); h.setSummary(input.getSummary());
        h.setPendingTasks(input.getPendingTasks()); h.setPriority(input.getPriority()); h.setCreatedBy(input.getCreatedBy());
        return repository.save(h);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) {
        repository.delete(one(id));
    }
}
