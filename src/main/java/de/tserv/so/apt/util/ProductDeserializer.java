package de.tserv.so.apt.util;

import java.util.ArrayList;
import java.util.List;

import de.tserv.so.apt.entity.Product;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.deser.std.StdDeserializer;

public class ProductDeserializer  extends StdDeserializer<Product> {
    public ProductDeserializer() {
        this(Product.class); 
    }

    public ProductDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Product deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        JsonNode node = p.readValueAsTree(); 

        String productExternalId = node.get("productExternalId").asString();
        String productName = node.get("productName").asString(); 
        String productLink = node.get("productLink").asString(); 

        JsonNode versionsNode = node.get("versions"); 
        List<Long> versions = new ArrayList<>(); 

        versionsNode.forEach(version -> {
            versions.add(version.asLong());
        });

        return new Product(productExternalId, productName, productLink, versions);
    }
}
