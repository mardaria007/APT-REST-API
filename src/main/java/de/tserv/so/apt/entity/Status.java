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
@Table(name = "status")
public class Status {
    private @Id
    @Column(name = "status_id")
    @GeneratedValue Long id;
    
    private String status; 

    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Version> versions;

    public Status() {}

    public Status(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonBackReference(value="status-version")
    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    @Override
    public String toString() {
        return String.format("Status[id=%d, status='%s']", id, status);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Status)) return false;
        Status s = (Status) o;
        return s.getId().equals(this.id) && s.getStatus().equals(this.status);
    }

    @Override
    public int hashCode() {
        return id.hashCode() + status.hashCode();
    }
}
