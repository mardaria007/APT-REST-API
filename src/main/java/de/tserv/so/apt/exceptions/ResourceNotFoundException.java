package de.tserv.so.apt.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id, String tableName) {
        super("Could not find resource with id: " + id + " in Table: " + tableName);
    }
}
