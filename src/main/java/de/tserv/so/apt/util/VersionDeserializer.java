package de.tserv.so.apt.util;

import java.util.ArrayList;
import java.util.List;

import de.tserv.so.apt.entity.Version;
import de.tserv.so.apt.enums.Status;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.deser.std.StdDeserializer;

public class VersionDeserializer extends StdDeserializer<Version> {

    public VersionDeserializer() {
        this(Version.class);
    }

    public VersionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Version deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        JsonNode node = p.readValueAsTree();
        
        Status status = Status.valueOf(node.get("status").asString());
        Long productId = node.get("product").asLong();
        JsonNode artifacts = node.get("artifacts");

        List<Long> artifactsid = new ArrayList<>();
        artifacts.forEach(artifact -> {
            artifactsid.add(artifact.asLong());
        });

        String description = node.get("description").asString();
        String version = node.get("version").asString();

        return new Version(version, description, productId, status, artifactsid);
    }
    
    
}
