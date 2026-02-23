package de.tserv.so.apt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tserv.so.apt.db.ArtifactRepository;
import de.tserv.so.apt.entity.Artifact;

@RestController
public class ArtifactController {
    
    private final ArtifactRepository repository; 

    public ArtifactController(ArtifactRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/artifacts")
    List<Artifact> all() {
        return repository.findAll();
    }

    @GetMapping("/artifacts/{id}")
    Artifact one(@PathVariable Long id) {
        return repository.findById(id).
            orElseThrow(); // TODO implement Not Found Exception
    }

    @PostMapping("/artifacts")
    Artifact newArtifact(@RequestBody Artifact newArtifact) {
        return repository.save(newArtifact);
    }

    @PutMapping("/artifacts/{id}")
    Artifact replaceArtifact(@RequestBody Artifact newArtifact, @PathVariable Long id) {
        return repository.findById(id)
            .map(artifact -> {
                artifact.setArtifactType(newArtifact.getArtifactType());
                artifact.setDescription(newArtifact.getDescription());
                artifact.setTransport_id(newArtifact.getTransport_id());
                artifact.setVersions(newArtifact.getVersions());
                return repository.save(artifact);
            })
            .orElseGet(() -> {
                return repository.save(newArtifact);
            });
    }

    @DeleteMapping("/artifacts/{id}")
    void deleteArtifact(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
