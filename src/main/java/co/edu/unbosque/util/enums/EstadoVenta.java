package co.edu.unbosque.util.enums;

/**
 * Enum representing the possible states of a sale.
 * <p>
 * It indicates whether a sale is in contingency, pending, or validated.
 * </p>
 * 
 * @author InnovaCore
 * @version 1.0
 */
public enum EstadoVenta {
    /**
     * The sale is in a contingency state.
     */
    CONTINGENCIA,

    /**
     * The sale is pending and not yet completed.
     */
    PENDIENTE,

    /**
     * The sale has been validated and confirmed.
     */
    VALIDADA
}
