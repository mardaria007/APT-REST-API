package de.tserv.so.apt.util;

import de.tserv.so.apt.entity.Version;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

public class VersionSerializer extends StdSerializer<Version> {

    public VersionSerializer() {
        this(null);
    }

    public VersionSerializer(Class<Version> t) {
        super(t);
    }

    @Override
    public void serialize(Version value, JsonGenerator gen, SerializationContext provider) throws JacksonException {
        gen.writeStartObject(); 
        gen.writeNumberProperty("id", value.getId());
        gen.writeNumberProperty("product", value.getProduct().getId());
        gen.writeStringProperty("version", value.getVersion());
        gen.writeStringProperty("description", value.getDescription());
        gen.writeStringProperty("status", value.getStatus().name());
        gen.writeArrayPropertyStart("artifacts");
        value.getArtifacts().forEach(artifact -> {
            gen.writeNumber(artifact.getId());
        });
        gen.writeEndArray();
        gen.writeEndObject();
    }
    
}
