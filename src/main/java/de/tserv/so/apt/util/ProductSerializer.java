package de.tserv.so.apt.util;

import org.springframework.beans.factory.annotation.Autowired;

import de.tserv.so.apt.entity.Product;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

public class ProductSerializer extends StdSerializer<Product> {

    @Autowired
    private AuthorizationHelper authHelper;

    public ProductSerializer() {
        this(Product.class); 
    }

    public ProductSerializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public void serialize(Product value, JsonGenerator gen, SerializationContext provider) throws JacksonException {
        gen.writeStartObject(); 
        gen.writePOJO(value);
        gen.writeBooleanProperty("isAdmin", authHelper.isAdmin());
        gen.writeEndObject();
    }
    
}
