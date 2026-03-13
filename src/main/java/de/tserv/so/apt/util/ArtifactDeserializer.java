package de.tserv.so.apt.util;

import de.tserv.so.apt.entity.ABAP_Transport;
import de.tserv.so.apt.entity.Artifact;
import de.tserv.so.apt.enums.AssignmentType;
import de.tserv.so.apt.enums.DeploymentCategory;
import de.tserv.so.apt.enums.TransportType;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ValueDeserializer;

public class ArtifactDeserializer extends ValueDeserializer<Artifact> {

    @Override
    public Artifact deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        JsonNode tree = p.readValueAsTree();
        String description = tree.get("description").stringValue();
        Long version = tree.get("version").longValue();
        
        if (tree.get("external_id") != null) {
            DeploymentCategory deploymentCategory = DeploymentCategory.valueOf(tree.get("deployment_category").stringValue());
            AssignmentType assignmentType = AssignmentType.valueOf(tree.get("assignment_type").stringValue());
            TransportType transportType = TransportType.valueOf(tree.get("transport_type").stringValue());
            Long external_id = tree.get("external_id").longValue();

            return new ABAP_Transport(description, version, deploymentCategory, assignmentType, transportType, external_id);
        }

        return new Artifact(version, description);
    }
    
}
