package com.mateo.bazar_api.exception;

// Extiende de runtime exception. Se da el resultado cuando se da una mala solicitud (bad request)
// por ejemplo, no encontrar DATA
// En este caso recibimos el mensaje que devolvera con el constructor
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {

        super(message);
    }

    public BadRequestException(String message, Throwable cause) {

        super(message, cause);
    }
}
