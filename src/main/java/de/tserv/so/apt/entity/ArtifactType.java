package de.tserv.so.apt.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artifact_type")
public class ArtifactType {
    private @Id
    @Column(name = "artifact_type_id")
    @GeneratedValue Long id;

    private String artifact_type;

    @OneToMany(mappedBy = "artifact_type", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Artifact> artifacts;


    public ArtifactType() {}

    public ArtifactType(String artifact_type) {
        this.artifact_type = artifact_type;
    }

    public Long getId() {
        return id;
    }

    public String getArtifactType() {
        return artifact_type;
    }

    @JsonBackReference(value = "artifact_type-artifact")
    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArtifactType(String artifact_type) {
        this.artifact_type = artifact_type;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    @Override
    public String toString() {
        return String.format("ArtifactType[id=%d, artifact_type='%s']", id, artifact_type);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ArtifactType)) return false;
        ArtifactType at = (ArtifactType) o;
        return at.getId().equals(this.id) && at.getArtifactType().equals(this.artifact_type);
    }

    @Override
    public int hashCode() {
        return id.hashCode() + artifact_type.hashCode();
    }
}
