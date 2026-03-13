package de.tserv.so.apt.entity;

import de.tserv.so.apt.SpringConfiguration;
import de.tserv.so.apt.db.VersionRepository;
import de.tserv.so.apt.util.ArtifactDeserializer;
import de.tserv.so.apt.util.ArtifactSerializer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "artifact")
@JsonSerialize(using = ArtifactSerializer.class)
@JsonDeserialize(using = ArtifactDeserializer.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "artifactType", discriminatorType = DiscriminatorType.STRING)
public class Artifact {
    private @Id
    @Column(name = "artifact_id")
    @GeneratedValue Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Version version;

    private String description;

    public Artifact() {}

    public Artifact(Long versionId, String description) {
        VersionRepository repo = (VersionRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("versionRepository");
        this.version = repo.findById(versionId).orElseThrow();
        this.description = description;
    }

    public Long getId() {
        return id;
    }

   public Version getVersion() {
        return version;
    }
    
    public void setVersion(Version version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Artifact[id=%d, description='%s']", id, description);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Artifact)) return false;
        Artifact a = (Artifact) o;
        return a.getId().equals(this.id) && a.getDescription().equals(this.description);
    }

    public int hashCode() {
        return id.hashCode() + description.hashCode();
    }
}
