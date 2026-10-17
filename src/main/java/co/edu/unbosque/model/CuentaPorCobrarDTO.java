package co.edu.unbosque.model;

import java.time.LocalDate;

import co.edu.unbosque.util.enums.EstadoCuenta;

/**
 * Data Transfer Object (DTO) for the {@link CuentaPorCobrar} entity.
 * <p>
 * This class is used to transfer account receivable data between different
 * layers of the application without directly exposing the entity model.
 * </p>
 * 
 * @author InnovaCore
 * @version 1.0
 */
public class CuentaPorCobrarDTO {

    /** Unique identifier for the account receivable. */
    private int idCuenta;

    /** Total amount owed by the client. */
    private long monto;

    /** Date when the payment is due. */
    private LocalDate fechaVencimiento;

    /** Date when the sale or transaction occurred. */
    private LocalDate fechaVenta;

    /** Current status of the account (e.g., PENDING, PAID, OVERDUE). */
    private EstadoCuenta estado;

    /** The client associated with this account receivable. */
    private ClienteDTO cliente;

    /**
     * Default constructor.
     * <p>
     * Creates an empty {@code CuentaPorCobrarDTO} instance.
     * </p>
     */
    public CuentaPorCobrarDTO() {
        // Default constructor
    }

    /**
     * Constructs a {@code CuentaPorCobrarDTO} with all its attributes.
     * 
     * @param idCuenta the account's unique ID
     * @param monto the total amount owed
     * @param fechaVencimiento the payment due date
     * @param fechaCompra the sale date
     * @param estado the current account status
     * @param cliente the client linked to this account
     */
    public CuentaPorCobrarDTO(int idCuenta, long monto, LocalDate fechaVencimiento, LocalDate fechaCompra,
            EstadoCuenta estado, ClienteDTO cliente) {
        super();
        this.idCuenta = idCuenta;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaVenta = fechaCompra;
        this.estado = estado;
        this.cliente = cliente;
    }

    /**
     * Gets the sale date.
     * 
     * @return the sale date
     */
    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    /**
     * Sets the sale date.
     * 
     * @param fechaCompra the sale date to set
     */
    public void setFechaVenta(LocalDate fechaCompra) {
        this.fechaVenta = fechaCompra;
    }

    /**
     * Gets the account ID.
     * 
     * @return the account ID
     */
    public int getIdCuenta() {
        return idCuenta;
    }

    /**
     * Sets the account ID.
     * 
     * @param idCuenta the account ID to set
     */
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * Gets the total amount owed.
     * 
     * @return the amount owed
     */
    public long getMonto() {
        return monto;
    }

    /**
     * Sets the total amount owed.
     * 
     * @param monto the amount to set
     */
    public void setMonto(long monto) {
        this.monto = monto;
    }

    /**
     * Gets the payment due date.
     * 
     * @return the due date
     */
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the payment due date.
     * 
     * @param fechaVencimiento the due date to set
     */
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Gets the current account status.
     * 
     * @return the account status
     */
    public EstadoCuenta getEstado() {
        return estado;
    }

    /**
     * Sets the current account status.
     * 
     * @param estado the account status to set
     */
    public void setEstado(EstadoCuenta estado) {
        this.estado = estado;
    }

    /**
     * Gets the client associated with this account.
     * 
     * @return the client
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Sets the client associated with this account.
     * 
     * @param cliente the client to set
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}
