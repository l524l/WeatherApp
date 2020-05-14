package com.l524l.exception;

public class CityNotFoundException extends Exception {
    public CityNotFoundException(){
        super("City not found");
    }
}
