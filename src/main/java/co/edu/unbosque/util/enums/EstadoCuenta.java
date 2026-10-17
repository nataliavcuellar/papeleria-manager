package co.edu.unbosque.util.enums;

/**
 * Enum representing the possible states of an account.
 * <p>
 * It is used to define whether an account is pending, paid, or overdue.
 * </p>
 * 
 * @author InnovaCore
 * @version 1.0
 */
public enum EstadoCuenta {
    /**
     * The account is pending payment.
     */
    PENDIENTE,

    /**
     * The account has been fully paid.
     */
    PAGADA,

    /**
     * The account is overdue and the payment is past due.
     */
    VENCIDA
}
