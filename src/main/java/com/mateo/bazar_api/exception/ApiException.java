package com.mateo.bazar_api.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

//Este modelo representa la rta de un error al ocurrir la excepcion
//Se devuelve al cliente al ocurrir x ERROR
@Getter @Setter
public class ApiException {
    private final String mensaje;
    //private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ApiException(String mensaje, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.mensaje = mensaje;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
