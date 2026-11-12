package edu.altair.examen.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.altair.examen.entities.Producto;

/**
 * Repositorio para gestionar las operaciones de persistencia de productos.
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Busca un producto mediante su código SKU.     *
     * @param codigoSku código SKU del producto
     * @return producto encontrado o vacío
     */
    Optional<Producto> findByCodigoSku(String codigoSku);

    /**
     * Comprueba si existe un producto con el código SKU indicado.     *
     * @param codigoSku código SKU que se desea comprobar
     * @return true si existe, false en caso contrario
     */
    boolean existsByCodigoSku(String codigoSku);
}