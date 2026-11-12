package edu.altair.examen.dtos.requests;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO utilizado para recibir los datos de un nuevo producto.
 */
public record ProductoRequest(

        @NotBlank(message = "El código SKU es obligatorio")
        @Size(max = 10, message = "El código SKU no puede superar 10 caracteres")
        String codigoSku,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
        String nombre,

        @Size(max = 255, message = "La descripción no puede superar 255 caracteres")
        String descripcion,

        @NotNull(message = "El precio es obligatorio")
        BigDecimal precio,

        @NotNull(message = "El stock es obligatorio")
        Integer stock

) {
}