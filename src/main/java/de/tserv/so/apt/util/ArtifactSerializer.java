package de.tserv.so.apt.util;

import de.tserv.so.apt.entity.ABAP_Transport;
import de.tserv.so.apt.entity.Artifact;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

public class ArtifactSerializer extends StdSerializer<Artifact> {

    public ArtifactSerializer() {
        this(null);
    }

    public ArtifactSerializer(Class<Artifact> t) {
        super(t);
    }

    @Override
    public void serialize(Artifact value, JsonGenerator gen, SerializationContext provider) throws JacksonException {
    
        gen.writeStartObject(); 

        gen.writeNumberProperty("id", value.getId());
        gen.writeStringProperty("description", value.getDescription());
        gen.writeNumberProperty("version", value.getVersion().getId());
        if (value instanceof ABAP_Transport) {
            gen.writeStringProperty("deployment_category", ((ABAP_Transport)value).getDeploymentCategory().name());
            gen.writeStringProperty("assignment_type", ((ABAP_Transport)value).getAssignmentType().name());
            gen.writeStringProperty("transport_type", ((ABAP_Transport)value).getTransportType().name());
            gen.writeNumberProperty("external_id", ((ABAP_Transport)value).getExternal_id());
        }
        gen.writeEndObject();
    }
    
}
