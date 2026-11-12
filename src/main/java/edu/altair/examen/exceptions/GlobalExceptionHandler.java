package edu.altair.examen.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Gestiona de forma centralizada las excepciones de la aplicación.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gestiona los productos que no se encuentran.     *
     * @param ex excepción producida
     * @return respuesta HTTP 404
     */
    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ErrorResponse> gestionarProductoNoEncontrado(
            ProductoNotFoundException ex) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                404,
                "Not Found",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Gestiona los códigos SKU duplicados.     *
     * @param ex excepción producida
     * @return respuesta HTTP 409
     */
    @ExceptionHandler(CodigoSkuDuplicadoException.class)
    public ResponseEntity<ErrorResponse> gestionarSkuDuplicado(
            CodigoSkuDuplicadoException ex) {

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                409,
                "Conflict",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    /**
     * Gestiona los errores de validación de los DTOs.     *
     * @param ex excepción producida
     * @return respuesta HTTP 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> gestionarValidacion(
            MethodArgumentNotValidException ex) {

        String mensaje = "Los datos enviados no son válidos";

        if (!ex.getBindingResult().getFieldErrors().isEmpty()) {
            mensaje = ex.getBindingResult()
                    .getFieldErrors()
                    .get(0)
                    .getDefaultMessage();
        }

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                400,
                "Bad Request",
                mensaje
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}