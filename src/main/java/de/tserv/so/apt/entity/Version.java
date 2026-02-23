package de.tserv.so.apt.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "version")
public class Version {
    private @Id
    @Column(name = "version_id")
    @GeneratedValue Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "version_artifact",
        joinColumns = @JoinColumn(name = "version_id"),
        inverseJoinColumns = @JoinColumn(name = "artifact_id")
    )
    private List<Artifact> artifacts;

    private String version;
    private String description; 

    public Version() {}

    public Version(String version, String description, Product product, Status status) {
        this.version = version;
        this.description = description;
        this.product = product;
        this.status = status;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    // Get Product ID 
    public Long getId() {
        return id;
    }

    // Get Version String
    public String getVersion() {
        return version;
    }

    // Get Version Description
    public String getDescription() {
        return description;
    }

    // Get Product Object
    @JsonBackReference(value = "product-version")
    public Product getProduct() {
        return product;
    }

    // Get Status Object
    @JsonManagedReference(value = "status-version")
    public Status getStatus() {
        return status;
    }

    // Set Product Object
    public void setProduct(Product product) {
        this.product = product;
    }

    // Set Status Object
    public void setStatus(Status status) {
        this.status = status;
    }

    // Set Version ID
    public void setId(Long id) {
        this.id = id;
    }

    // Set Version String
    public void setVersion(String version) {
        this.version = version;
    }

    // Set Version Description
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Version[id=%d, version='%s', description='%s']", id, version, description);
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
