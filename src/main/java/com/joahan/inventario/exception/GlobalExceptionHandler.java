package com.joahan.inventario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarRuntimeException(RuntimeException ex) {

        Map<String, String> error = new HashMap<>();

        error.put("error", ex.getMessage());

        return error;
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<String>> manejarErroresValidacion(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {

        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();

        Map<String, List<String>> respuesta = new HashMap<>();

        respuesta.put("errores", errores);

        return respuesta;
    }
}