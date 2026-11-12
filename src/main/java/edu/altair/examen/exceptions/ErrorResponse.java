package edu.altair.examen.exceptions;

import java.time.LocalDateTime;

/**
 * Representa la estructura de una respuesta de error de la API.
 */
public record ErrorResponse(
        LocalDateTime fecha,
        int estado,
        String error,
        String mensaje
) {
}