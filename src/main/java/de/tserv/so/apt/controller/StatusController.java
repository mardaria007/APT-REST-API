package de.tserv.so.apt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tserv.so.apt.db.StatusRepository;
import de.tserv.so.apt.entity.Status;

@RestController
public class StatusController {
    
    private final StatusRepository repository; 

    StatusController(StatusRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/status")
    List<Status> all() {
        return repository.findAll();
    }

    @GetMapping("/status/{id}")
    Status one(@PathVariable Long id) {
        return repository.findById(id).
            orElseThrow(); // TODO implement Not Found Exception
    }

    @PostMapping("/status")
    Status newStatus(@RequestBody Status newStatus) {
        return repository.save(newStatus);
    }

    @PutMapping("/status/{id}")
    Status replaceStatus(@RequestBody Status newStatus, @PathVariable Long id) {
        return repository.findById(id)
            .map(status -> {
                status.setStatus(newStatus.getStatus());
                return repository.save(status);
            })
            .orElseGet(() -> {
                return repository.save(newStatus);
            });
    }

    @DeleteMapping("/status/{id}")
    void deleteStatus(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
