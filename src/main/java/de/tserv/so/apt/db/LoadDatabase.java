package de.tserv.so.apt.db;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.tserv.so.apt.entity.Artifact;
import de.tserv.so.apt.entity.ArtifactType;
import de.tserv.so.apt.entity.Product;
import de.tserv.so.apt.entity.Status;
import de.tserv.so.apt.entity.Version;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner loadData(
            StatusRepository statusRepository, 
            ProductRepository productRepository,
            VersionRepository versionRepository, 
            ArtifactTypeRepository artifactTypeRepository, 
            ArtifactRepository artifactRepository) {
        return args -> {
            Status status1 = statusRepository.save(new Status("Freigegeben"));
            Status status2 = statusRepository.save(new Status("In Bearbeitung"));
            log.info("Initializing " + status1);
            log.info("Initializing " + status2);

            Product product = new Product("de.tserv.so.rs.doc", "accruals_doc", "link", new ArrayList<>());
            product = productRepository.save(product);
            log.info("Initializing " + product);

            Version version = new Version("1.0.0", "Initiale Version", product, status1);
            version = versionRepository.save(version);
            // status1.setVersions(version);
            // statusRepository.save(status1);
            log.info("Initializing " + version);

            product.getVersions().add(version);
            productRepository.save(product);
            log.info("Modified Product " + product);

            ArtifactType artifactType = artifactTypeRepository.save(new ArtifactType("ABAP_TRANSPORT"));
            log.info("Initializing " + artifactType);
            
            Artifact artifact = new Artifact("Artifakt 1", artifactType, 123456L, List.of(version));
            artifactRepository.save(artifact);
            log.info("Initializing " + artifact);
        };
    }
}
