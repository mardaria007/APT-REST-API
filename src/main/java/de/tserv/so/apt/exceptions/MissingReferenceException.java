package de.tserv.so.apt.exceptions;

public class MissingReferenceException extends RuntimeException{
    public MissingReferenceException(String refObject) {
        super("Missing the reference to " + refObject + " in the Request Body");
    }
}
