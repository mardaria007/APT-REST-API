package de.tserv.so.apt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tserv.so.apt.db.VersionRepository;
import de.tserv.so.apt.entity.Version;
import de.tserv.so.apt.exceptions.MissingReferenceException;
import de.tserv.so.apt.exceptions.ResourceNotFoundException;

@RestController
public class VersionController {
    private final VersionRepository repository; 

    public VersionController(VersionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/versions") 
    List<Version> all() {
        return repository.findAll();
    }

    @GetMapping("/versions/{id}")
    Version one(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id, "versions"));
    }

    @PostMapping("/versions") 
    Version newVersion(@RequestBody Version newVersion) {
        if (newVersion.getProduct() == null) {
            throw new MissingReferenceException("Product");
        }

        return repository.save(newVersion);
    }

    @PutMapping("/versions/{id}")
    Version replaceVersion(@RequestBody Version newVersion, @PathVariable Long id) {

        if (newVersion.getProduct() == null) {
            throw new MissingReferenceException("Product");
        }
        
        return repository.findById(id)
            .map(version -> {
                version.setDescription(newVersion.getDescription());
                version.setStatus(newVersion.getStatus());
                version.setProduct(newVersion.getProduct());
                version.setArtifacts(newVersion.getArtifacts());
                version.setVersion(newVersion.getVersion());
                return repository.save(version);
            })
            .orElseGet(() -> {
                return repository.save(newVersion);
            });  
    }

    @DeleteMapping("/versions/{id}")
    void deleteVersion(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
