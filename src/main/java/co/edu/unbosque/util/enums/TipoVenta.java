package co.edu.unbosque.util.enums;

/**
 * Enum representing the type of a sale.
 * <p>
 * It indicates whether a sale is on credit (FIADA) or already paid (PAGADA).
 * </p>
 * 
 * @author InnovaCore
 * @version 1.0
 */
public enum TipoVenta {
    /**
     * The sale is on credit (to be paid later).
     */
    FIADA,

    /**
     * The sale has been paid.
     */
    PAGADA
}
