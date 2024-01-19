package org.example;

public class LibroGiaInseritoException extends Exception{
    private String message;

    public LibroGiaInseritoException(String message){
        super(message);
    }
}
