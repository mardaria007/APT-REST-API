package de.tserv.so.apt.db;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tserv.so.apt.entity.Version;

public interface VersionRepository extends JpaRepository<Version, Long> {
    
}
