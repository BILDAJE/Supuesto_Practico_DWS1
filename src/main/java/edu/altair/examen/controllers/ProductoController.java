package edu.altair.examen.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import edu.altair.examen.dtos.requests.ProductoRequest;
import edu.altair.examen.dtos.requests.ProductoUpdateRequest;
import edu.altair.examen.dtos.responses.ProductoResponse;
import edu.altair.examen.services.ProductoService;

/**
 * Controlador REST para gestionar el catálogo de productos.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    /**
     * Constructor del controlador.     *
     * @param productoService servicio de productos
     */
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Consulta todos los productos.     *
     * @return lista de productos
     */
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listarProductos() {

        List<ProductoResponse> productos =
                productoService.listarProductos();

        return ResponseEntity.ok(productos);
    }

    /**
     * Registra un nuevo producto.     *
     * @param request datos del producto
     * @return producto creado con estado 201
     */
    @PostMapping
    public ResponseEntity<ProductoResponse> guardarProducto(
            @Valid @RequestBody ProductoRequest request) {

        ProductoResponse producto =
                productoService.guardarProducto(request);

        URI ubicacion = URI.create(
                "/api/productos/" + producto.codigoSku()
        );

        return ResponseEntity
                .created(ubicacion)
                .body(producto);
    }

    /**
     * Modifica un producto mediante su código SKU.
     *
     * @param codigoSku código SKU del producto
     * @param request nuevos datos del producto
     * @return producto actualizado
     */
    @PutMapping("/{codigoSku}")
    public ResponseEntity<ProductoResponse> actualizarProducto(
            @PathVariable String codigoSku,
            @Valid @RequestBody ProductoUpdateRequest request) {

        ProductoResponse producto =
                productoService.actualizarProducto(codigoSku, request);

        return ResponseEntity.ok(producto);
    }

    /**
     * Elimina un producto mediante su código SKU.
     *
     * @param codigoSku código SKU del producto
     * @return respuesta HTTP 204
     */
    @DeleteMapping("/{codigoSku}")
    public ResponseEntity<Void> eliminarProducto(
            @PathVariable String codigoSku) {

        productoService.eliminarProducto(codigoSku);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}