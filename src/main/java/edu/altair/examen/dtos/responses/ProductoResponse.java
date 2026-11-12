package edu.altair.examen.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO utilizado para devolver la información de un producto.
 */
public record ProductoResponse(
        Long idProduct,
        String codigoSku,
        String nombre,
        String descripcion,
        BigDecimal precio,
        Integer stock,
        LocalDate fechaAlta
) {
}