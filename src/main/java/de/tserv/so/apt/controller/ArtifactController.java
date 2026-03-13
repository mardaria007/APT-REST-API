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
import de.tserv.so.apt.entity.ABAP_Transport;
import de.tserv.so.apt.entity.Artifact;
import de.tserv.so.apt.exceptions.ResourceNotFoundException;

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
            orElseThrow(() -> new ResourceNotFoundException(id, "artifacts"));
    }

    @PostMapping("/artifacts")
    Artifact newArtifact(@RequestBody Artifact newArtifact) {
        return repository.save(newArtifact);
    }

    @PutMapping("/artifacts/{id}")
    Artifact replaceArtifact(@RequestBody Artifact newArtifact, @PathVariable Long id) {
        return repository.findById(id)
            .map(artifact -> {
                artifact.setDescription(newArtifact.getDescription());
                artifact.setVersion(newArtifact.getVersion());

                if (artifact instanceof ABAP_Transport) {
                    ((ABAP_Transport)artifact).setAssignmentType(((ABAP_Transport)newArtifact).getAssignmentType());
                    ((ABAP_Transport)artifact).setDeploymentCategory(((ABAP_Transport)newArtifact).getDeploymentCategory());
                    ((ABAP_Transport)artifact).setTransportType(((ABAP_Transport)newArtifact).getTransportType());
                    ((ABAP_Transport)artifact).setExternal_id(((ABAP_Transport)newArtifact).getExternal_id());

                    return repository.save((ABAP_Transport)artifact);
                }
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
