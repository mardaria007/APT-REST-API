package de.tserv.so.apt.util;

import de.tserv.so.apt.entity.Product;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

public class ProductSerializer extends StdSerializer<Product> {

    public ProductSerializer() {
        this(null);
    }
    

    public ProductSerializer(Class<Product> t) {
        super(t);
    }

    @Override
    public void serialize(Product value, JsonGenerator gen, SerializationContext provider) throws JacksonException {
        gen.writeStartObject(); 
        gen.writeNumberProperty("id", value.getId());
        gen.writeStringProperty("product_external_id", value.getProductExternalId());
        gen.writeStringProperty("product_name", value.getProduct_name());
        gen.writeStringProperty("product_link", value.getProduct_link());
        gen.writeArrayPropertyStart("versions"); 
        value.getVersions().forEach(version -> {
            gen.writeNumber(version.getId());
        });
        gen.writeEndArray(); 
        gen.writeEndObject();
    }
    
}