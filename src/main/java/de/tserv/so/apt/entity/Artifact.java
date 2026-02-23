package de.tserv.so.apt.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "artifact")
public class Artifact {
    private @Id
    @Column(name = "artifact_id")
    @GeneratedValue Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artifact_type")
    private ArtifactType artifact_type;

    @ManyToMany(mappedBy = "artifacts", fetch = FetchType.EAGER)
    private List<Version> versions = new ArrayList<>();

    private Long transport_id;
    private String description;

    public Artifact() {}

    public Artifact(String description, ArtifactType artifact_type, Long transport_id, List<Version> versions) {
        this.description = description;
        this.artifact_type = artifact_type;
        this.transport_id = transport_id;
        this.versions = versions;
    }

    public Long getId() {
        return id;
    }

    @JsonManagedReference(value = "artifact_type-artifact")
    public ArtifactType getArtifactType() {
        return artifact_type;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public Long getTransport_id() {
        return transport_id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    public void setArtifactType(ArtifactType artifact_type) {
        this.artifact_type = artifact_type;
    }

    public void setTransport_id(Long transport_id) {
        this.transport_id = transport_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Artifact[id=%d, description='%s', transport_id=%d, artifact_type=%s]", id, description, transport_id, artifact_type.getArtifactType());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Artifact)) return false;
        Artifact a = (Artifact) o;
        return a.getId().equals(this.id) && a.getDescription().equals(this.description) && a.getTransport_id().equals(this.transport_id) && a.getArtifactType().equals(this.artifact_type);
    }

    public int hashCode() {
        return id.hashCode() + description.hashCode() + transport_id.hashCode() + artifact_type.hashCode();
    }
}
