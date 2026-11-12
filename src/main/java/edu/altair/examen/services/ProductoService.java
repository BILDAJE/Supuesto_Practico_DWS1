package edu.altair.examen.services;

import java.util.List;

import edu.altair.examen.dtos.requests.ProductoRequest;
import edu.altair.examen.dtos.requests.ProductoUpdateRequest;
import edu.altair.examen.dtos.responses.ProductoResponse;

/**
 * Define las operaciones de negocio para gestionar productos.
 */
public interface ProductoService {

    List<ProductoResponse> listarProductos();

    ProductoResponse guardarProducto(ProductoRequest request);

    ProductoResponse actualizarProducto(
            String codigoSku,
            ProductoUpdateRequest request
    );

    void eliminarProducto(String codigoSku);
}