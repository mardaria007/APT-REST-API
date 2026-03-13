package de.tserv.so.apt.entity;

import de.tserv.so.apt.enums.AssignmentType;
import de.tserv.so.apt.enums.DeploymentCategory;
import de.tserv.so.apt.enums.TransportType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ABAP_Transport")
public class ABAP_Transport extends Artifact {
    private DeploymentCategory deploymentCategory;
    private AssignmentType assignmentType; 
    private TransportType transportType; 

    private Long external_id; 

    public ABAP_Transport() {}

    public ABAP_Transport(String description, Long versionId, DeploymentCategory deploymentCategory,
            AssignmentType assignmentType, TransportType transportType, Long external_id) {
        super(versionId, description);
        this.deploymentCategory = deploymentCategory;
        this.assignmentType = assignmentType;
        this.transportType = transportType;
        this.external_id = external_id;
    }

    public DeploymentCategory getDeploymentCategory() {
        return deploymentCategory;
    }

    public void setDeploymentCategory(DeploymentCategory deploymentCategory) {
        this.deploymentCategory = deploymentCategory;
    }

    public AssignmentType getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(AssignmentType assignmentType) {
        this.assignmentType = assignmentType;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public Long getExternal_id() {
        return external_id;
    }

    public void setExternal_id(Long external_id) {
        this.external_id = external_id;
    }

    @Override
    public String toString() {
        return "ABAP_Transport [deploymentCategory=" + deploymentCategory + ", assignmentType=" + assignmentType
                + ", transportType=" + transportType + ", external_id=" + external_id + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((deploymentCategory == null) ? 0 : deploymentCategory.hashCode());
        result = prime * result + ((assignmentType == null) ? 0 : assignmentType.hashCode());
        result = prime * result + ((transportType == null) ? 0 : transportType.hashCode());
        result = prime * result + ((external_id == null) ? 0 : external_id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ABAP_Transport other = (ABAP_Transport) obj;
        if (deploymentCategory != other.deploymentCategory)
            return false;
        if (assignmentType != other.assignmentType)
            return false;
        if (transportType != other.transportType)
            return false;
        if (external_id == null) {
            if (other.external_id != null)
                return false;
        } else if (!external_id.equals(other.external_id))
            return false;
        return true;
    }
}
