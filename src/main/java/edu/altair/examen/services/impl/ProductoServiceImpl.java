package edu.altair.examen.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.altair.examen.dtos.requests.ProductoRequest;
import edu.altair.examen.dtos.requests.ProductoUpdateRequest;
import edu.altair.examen.dtos.responses.ProductoResponse;
import edu.altair.examen.entities.Producto;
import edu.altair.examen.exceptions.CodigoSkuDuplicadoException;
import edu.altair.examen.exceptions.ProductoNotFoundException;
import edu.altair.examen.repositories.ProductoRepository;
import edu.altair.examen.services.ProductoService;

/**
 * Implementa la lógica de negocio para gestionar productos.
 */
@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Devuelve todos los productos registrados.     *
     * @return lista de productos
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponse> listarProductos() {

        List<Producto> productos = productoRepository.findAll();
        List<ProductoResponse> respuestas = new ArrayList<>();

        for (Producto producto : productos) {
            respuestas.add(convertirAResponse(producto));
        }

        return respuestas;
    }

    /**
     * Registra un nuevo producto.     *
     * @param request datos del producto
     * @return producto registrado
     */
    @Override
    public ProductoResponse guardarProducto(ProductoRequest request) {

        if (productoRepository.existsByCodigoSku(request.codigoSku())) {
            throw new CodigoSkuDuplicadoException(
                    "Ya existe un producto con el código SKU: "
                    + request.codigoSku()
            );
        }

        Producto producto = new Producto();

        producto.setCodigoSku(request.codigoSku());
        producto.setNombre(request.nombre());
        producto.setDescripcion(request.descripcion());
        producto.setPrecio(request.precio());
        producto.setStock(request.stock());
        producto.setFechaAlta(LocalDate.now());

        Producto productoGuardado = productoRepository.save(producto);

        return convertirAResponse(productoGuardado);
    }

    /**
     * Actualiza un producto mediante su código SKU.     *
     * @param codigoSku código SKU del producto
     * @param request nuevos datos del producto
     * @return producto actualizado
     */
    @Override
    public ProductoResponse actualizarProducto(
            String codigoSku,
            ProductoUpdateRequest request) {

        Optional<Producto> resultado =
                productoRepository.findByCodigoSku(codigoSku);

        if (!resultado.isPresent()) {
            throw new ProductoNotFoundException(
                    "No existe un producto con el código SKU: " + codigoSku
            );
        }

        Producto producto = resultado.get();

        producto.setNombre(request.nombre());
        producto.setDescripcion(request.descripcion());
        producto.setPrecio(request.precio());
        producto.setStock(request.stock());

        Producto productoActualizado = productoRepository.save(producto);

        return convertirAResponse(productoActualizado);
    }

    /**
     * Elimina un producto mediante su código SKU.     *
     * @param codigoSku código SKU del producto
     */
    @Override
    public void eliminarProducto(String codigoSku) {

        Optional<Producto> resultado =
                productoRepository.findByCodigoSku(codigoSku);

        if (!resultado.isPresent()) {
            throw new ProductoNotFoundException(
                    "No existe un producto con el código SKU: " + codigoSku
            );
        }

        productoRepository.delete(resultado.get());
    }

    /**
     * Convierte una entidad Producto en su DTO de respuesta.     *
     * @param producto entidad que se desea convertir
     * @return DTO de respuesta
     */
    private ProductoResponse convertirAResponse(Producto producto) {

        return new ProductoResponse(
                producto.getIdProduct(),
                producto.getCodigoSku(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getFechaAlta()
        );
    }
}