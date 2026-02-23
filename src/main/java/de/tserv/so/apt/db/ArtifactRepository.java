package de.tserv.so.apt.db;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tserv.so.apt.entity.Artifact;

public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
    
}
