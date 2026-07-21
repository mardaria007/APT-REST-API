package de.tserv.so.apt.util;

import org.springframework.beans.factory.annotation.Autowired;

import de.tserv.so.apt.entity.Product;
import de.tserv.so.apt.entity.ABAP_Transport;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ser.std.StdSerializer;

public class ProductSerializer extends StdSerializer<Product> {

    @Autowired
    private AuthorizationHelper authHelper;

    public ProductSerializer() {
        this(Product.class); 
        this.authHelper = new AuthorizationHelper();
    }

    public ProductSerializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public void serialize(Product value, JsonGenerator gen, SerializationContext provider) throws JacksonException {
        gen.writeStartObject();
        gen.writeNumberProperty("id", value.getId());
        gen.writeStringProperty("productExternalId", value.getProductExternalId());
        gen.writeStringProperty("productLink", value.getProductLink());
        
        gen.writeArrayPropertyStart("versions");
        for (int i = 0; i < value.getVersions().size(); i++) {
            gen.writeStartObject(); 
            gen.writeNumberProperty("id", value.getVersions().get(i).getId());
            gen.writeStringProperty("status", value.getVersions().get(i).getStatus().name());
            gen.writeStringProperty("version", value.getVersions().get(i).getVersion());
            gen.writeStringProperty("description", value.getVersions().get(i).getDescription());
            gen.writeArrayPropertyStart("artifacts");
            for (int j = 0; j < value.getVersions().get(i).getArtifacts().size(); j++) {
                gen.writeStartObject();
                gen.writeNumberProperty("id", value.getVersions().get(i).getArtifacts().get(j).getId());
                gen.writeStringProperty("description", value.getVersions().get(i).getArtifacts().get(j).getDescription());
                if (value.getVersions().get(i).getArtifacts().get(j) instanceof ABAP_Transport) {
                    gen.writeStringProperty("deploymentCategory", ((ABAP_Transport)value.getVersions().get(i).getArtifacts().get(j)).getDeploymentCategory().name());
                    gen.writeStringProperty("assignmentType", ((ABAP_Transport)value.getVersions().get(i).getArtifacts().get(j)).getAssignmentType().name());
                    gen.writeStringProperty("transportType", ((ABAP_Transport)value.getVersions().get(i).getArtifacts().get(j)).getTransportType().name());
                    gen.writeStringProperty("externalId", ((ABAP_Transport)value.getVersions().get(i).getArtifacts().get(j)).getExternalId());
                }
                gen.writeEndObject();
            }
            gen.writeEndArray();
            gen.writeEndObject();
        }
        gen.writeEndArray();
        gen.writeBooleanProperty("isAdmin", authHelper.isAdmin());
        gen.writeEndObject();
    }
    
}
