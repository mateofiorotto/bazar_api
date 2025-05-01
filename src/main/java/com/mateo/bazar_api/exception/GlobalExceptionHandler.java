package com.mateo.bazar_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

//Centralizamos el manejo de exceptions
//cada vez que se manda una apirequestexception se captura y se devuelve un respons entity con el objeto apiexception
//Los errores se devolveran con el mismo formato

@RestControllerAdvice
public class GlobalExceptionHandler {

    //404 - NOT FOUND personalizado
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(NotFoundException e){

        ApiException apiException = new ApiException(
                e.getMessage(),
                //e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    };

    //500 - INTERNAL SERVER ERROR
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception e) {
        //payload con error generico 500
        ApiException apiException = new ApiException(
                "Ocurrió un error inesperado",
                //e,
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Excepcion para validaciones con MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException e) {
        String errores = e.getBindingResult()//obtenemos los errores
                .getFieldErrors() //extraer lista de errores x campo especifico
                .stream()//convierte la lista en un stream para poder usar maps y filter
                //transforma los fielderror a strings, error es el nobmre del campo con el error y el se obtiene el msg
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; ")); //Une los msg separados por ,

        ApiException apiException = new ApiException(
               errores,
                //e,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
