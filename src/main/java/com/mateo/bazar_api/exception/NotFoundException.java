package com.mateo.bazar_api.exception;

// Extiende de runtime exception. Se da el resultado cuando se da una mala solicitud (bad request)
// por ejemplo, no encontrar DATA
// En este caso recibimos el mensaje que devolvera con el constructor
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
