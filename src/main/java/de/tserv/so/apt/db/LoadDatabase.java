package de.tserv.so.apt.db;

import java.util.ArrayList;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.tserv.so.apt.entity.Artifact;
import de.tserv.so.apt.entity.Product;
import de.tserv.so.apt.entity.Version;
import de.tserv.so.apt.enums.Status;


@Configuration
public class LoadDatabase {

    // private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner loadData(
            ProductRepository productRepository,
            VersionRepository versionRepository, 
            ArtifactRepository artifactRepository) {
        return args -> {
            productRepository.save(new Product("test.app", "test app", "link", new ArrayList<>()));
            productRepository.save(new Product("test.app2", "test app 2", "link 2", new ArrayList<>()));
            versionRepository.save(new Version("1.0.0", "Test", 1L, Status.IN_PROCESS, new ArrayList<>()));
            versionRepository.save(new Version("2.0.0", "Prod", 1L, Status.IN_PROCESS, new ArrayList<>()));
            versionRepository.save(new Version("1.1.0", "Prod", 2L, Status.IN_PROCESS, new ArrayList<>()));
            artifactRepository.save(new Artifact(1L, "test Artifact"));
            artifactRepository.save(new Artifact(1L, "test Artifact 2"));
            artifactRepository.save(new Artifact(2L, "test Artifact for Prod"));
            artifactRepository.save(new Artifact(2L, "test Artifact for Prod 2"));

        };
    }
}
