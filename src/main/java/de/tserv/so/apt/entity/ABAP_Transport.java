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

    private String externalId; 

    public ABAP_Transport() {}

    public ABAP_Transport(String description, Long versionId, DeploymentCategory deploymentCategory,
            AssignmentType assignmentType, TransportType transportType, String external_id) {
        super(versionId, description);
        this.deploymentCategory = deploymentCategory;
        this.assignmentType = assignmentType;
        this.transportType = transportType;
        this.externalId = external_id;
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

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String external_id) {
        this.externalId = external_id;
    }

    @Override
    public String toString() {
        return "ABAP_Transport [deploymentCategory=" + deploymentCategory + ", assignmentType=" + assignmentType
                + ", transportType=" + transportType + ", externalId=" + externalId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((deploymentCategory == null) ? 0 : deploymentCategory.hashCode());
        result = prime * result + ((assignmentType == null) ? 0 : assignmentType.hashCode());
        result = prime * result + ((transportType == null) ? 0 : transportType.hashCode());
        result = prime * result + ((externalId == null) ? 0 : externalId.hashCode());
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
        if (externalId == null) {
            if (other.externalId != null)
                return false;
        } else if (!externalId.equals(other.externalId))
            return false;
        return true;
    }
}
