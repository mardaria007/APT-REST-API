package de.tserv.so.apt.entity;

import java.util.ArrayList;
import java.util.List;

import de.tserv.so.apt.SpringConfiguration;
import de.tserv.so.apt.db.ArtifactRepository;
import de.tserv.so.apt.db.ProductRepository;
import de.tserv.so.apt.enums.Status;
import de.tserv.so.apt.util.VersionDeserializer;
import de.tserv.so.apt.util.VersionSerializer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "version")
@JsonSerialize(using = VersionSerializer.class)
@JsonDeserialize(using = VersionDeserializer.class)
public class Version {
    private @Id
    @Column(name = "version_id")
    @GeneratedValue Long id;

    private Status status;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "version", cascade = CascadeType.ALL)
    private List<Artifact> artifacts = new ArrayList<>();

    private String version;
    private String description; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Version() {}

    public Version(String version, String description, Long product, Status status, List<Long> artifacts) {
        this.version = version;
        this.description = description;
        ProductRepository prepo = (ProductRepository) SpringConfiguration.contextProvider()
                                                        .getApplicationContext()
                                                        .getBean("productRepository");
        this.product = prepo.findById(product).orElseThrow();
        this.status = status;
        ArtifactRepository arepo = (ArtifactRepository) SpringConfiguration.contextProvider()
                                                            .getApplicationContext()
                                                            .getBean(("artifactRepository"));
        this.artifacts = arepo.findAllById(artifacts);
    }

    @Override
    public String toString() {
        return "Version [id=" + id + ", status=" + status + ", product=" + product + ", artifacts=" + artifacts
                + ", version=" + version + ", description=" + description + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Version)) return false;
        Version v = (Version) o;
        return v.getId().equals(this.id) && v.getVersion().equals(this.version) && v.getDescription().equals(this.description);
    }

    @Override
    public int hashCode() {
        return id.hashCode() + version.hashCode() + description.hashCode();
    }
}
