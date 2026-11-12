package edu.altair.examen.exceptions;

/**
 * Excepción lanzada cuando no se encuentra un producto.
 */
public class ProductoNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public ProductoNotFoundException(String mensaje) {
        super(mensaje);
    }
}