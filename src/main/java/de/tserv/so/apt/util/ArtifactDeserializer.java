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
        
        if (tree.get("externalId") != null) {
            DeploymentCategory deploymentCategory = DeploymentCategory.valueOf(tree.get("deploymentCategory").stringValue());
            AssignmentType assignmentType = AssignmentType.valueOf(tree.get("assignmentType").stringValue());
            TransportType transportType = TransportType.valueOf(tree.get("transportType").stringValue());
            String externalId = tree.get("externalId").stringValue();

            return new ABAP_Transport(description, version, deploymentCategory, assignmentType, transportType, externalId);
        }

        return new Artifact(version, description);
    }
    
}
