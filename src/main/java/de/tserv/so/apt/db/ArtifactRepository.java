package de.tserv.so.apt.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.tserv.so.apt.entity.Artifact;

@Repository
public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
    
}
