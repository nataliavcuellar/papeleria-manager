package co.edu.unbosque.model;

import java.time.LocalDate;

import co.edu.unbosque.util.enums.EstadoCuenta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents an account receivable record in the system.
 * <p>
 * This entity stores information about a pending or paid debt related to a client,
 * including the total amount, due date, sale date, and current payment status.
 * </p>
 * 
 * @author InnovaCore
 * @version 1.0
 */
@Entity
@Table(name = "cuentaporcobrar")
public class CuentaPorCobrar {

    /** Unique identifier for the account receivable. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private int idCuenta;

    /** Total amount owed by the client. */
    @Column(name = "monto", nullable = false)
    private long monto;

    /** Date when the payment is due. */
    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    /** Date when the sale or transaction occurred. */
    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    /** Current status of the account (e.g., PENDING, PAID, OVERDUE). */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", columnDefinition = "ENUM('PENDIENTE', 'PAGADA', 'VENCIDA')", nullable = false)
    private EstadoCuenta estado;

    /** The client associated with this account receivable. */
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    /**
     * Default constructor.
     * <p>
     * Required by JPA for entity instantiation.
     * </p>
     */
    public CuentaPorCobrar() {
        // Default constructor
    }

    /**
     * Constructs a {@code CuentaPorCobrar} object with all its attributes.
     * 
     * @param idCuenta the account's unique ID
     * @param monto the total amount owed
     * @param fechaVencimiento the payment due date
     * @param fechaCompra the sale date
     * @param estado the current status of the account
     * @param cliente the client linked to this account
     */
    public CuentaPorCobrar(int idCuenta, long monto, LocalDate fechaVencimiento, LocalDate fechaCompra,
            EstadoCuenta estado, Cliente cliente) {
        super();
        this.idCuenta = idCuenta;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaVenta = fechaCompra;
        this.estado = estado;
        this.cliente = cliente;
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
     * Gets the due date for the payment.
     * 
     * @return the due date
     */
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the due date for the payment.
     * 
     * @param fechaVencimiento the due date to set
     */
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
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
     * Gets the current status of the account.
     * 
     * @return the account status
     */
    public EstadoCuenta getEstado() {
        return estado;
    }

    /**
     * Sets the current status of the account.
     * 
     * @param estado the status to set
     */
    public void setEstado(EstadoCuenta estado) {
        this.estado = estado;
    }

    /**
     * Gets the client associated with the account.
     * 
     * @return the client
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Sets the client associated with the account.
     * 
     * @param cliente the client to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
