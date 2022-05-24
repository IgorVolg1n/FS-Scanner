package org.volgin.exceptions;

public class InvalidPathScanException extends RuntimeException{

    public InvalidPathScanException(String path) {
        super(String.format("Provided path is invalid: %s", path));
    }
}
