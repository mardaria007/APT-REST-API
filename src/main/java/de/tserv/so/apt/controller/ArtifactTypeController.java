package de.tserv.so.apt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tserv.so.apt.db.ArtifactTypeRepository;
import de.tserv.so.apt.entity.ArtifactType;

@RestController
public class ArtifactTypeController {
    
    private final ArtifactTypeRepository repository; 

    public ArtifactTypeController(ArtifactTypeRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/artifacttype")
    List<ArtifactType> all() {
        return repository.findAll();
    }

    @GetMapping("/artifacttype/{id}")
    ArtifactType one(@PathVariable Long id) {
        return repository.findById(id).
            orElseThrow(); // TODO implement Not Found Exception
    }

    @PostMapping("/artifacttype")
    ArtifactType newArtifactType(@RequestBody ArtifactType newArtifactType) {
        return repository.save(newArtifactType);
    }

    @PutMapping("/artifacttype/{id}")
    ArtifactType replaceArtifactType(@RequestBody ArtifactType newArtifactType, @PathVariable Long id) {
        return repository.findById(id)
            .map(artifacttype -> {
                artifacttype.setArtifactType(newArtifactType.getArtifactType());
                return repository.save(artifacttype);
            })
            .orElseGet(() -> {
                return repository.save(newArtifactType);
            });
    }

    @DeleteMapping("/artifacttype/{id}")
    void deleteArtifactType(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
