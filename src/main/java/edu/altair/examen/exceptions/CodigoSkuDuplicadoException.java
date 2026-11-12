package edu.altair.examen.exceptions;

/**
 * Excepción lanzada cuando se intenta registrar un SKU duplicado.
 */
public class CodigoSkuDuplicadoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

    public CodigoSkuDuplicadoException(String mensaje) {
        super(mensaje);
    }
}