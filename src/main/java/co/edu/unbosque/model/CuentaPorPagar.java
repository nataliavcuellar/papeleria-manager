package co.edu.unbosque.model;

import java.time.LocalDate;
import java.util.List;

import co.edu.unbosque.util.enums.EstadoCuenta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents an account payable record in the system.
 * <p>
 * This entity stores the main details of a payable account such as the total amount,
 * due date, purchase date, account status, and the list of products associated with it.
 * </p>
 * 
 * @author Nube
 */
@Entity
@Table(name = "cuentaporpagar")
public class CuentaPorPagar {

    /** Unique identifier for the payable account. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private int idCuenta;

    /** Total amount to be paid. */
    @Column(name = "monto")
    private long monto;

    /** Date when the account must be paid. */
    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    /** Date when the purchase was made. */
    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    /**
     * Current state of the account.
     * Possible values: PENDIENTE, PAGADA, or VENCIDA.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", columnDefinition = "ENUM('PENDIENTE', 'PAGADA', 'VENCIDA')", nullable = false)
    private EstadoCuenta estado;

    /**
     * List of products linked to this payable account.
     * The relationship is one-to-many and cascades all operations.
     */
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CuentaProducto> listaCuentaProducto;

    /**
     * Default constructor.
     */
    public CuentaPorPagar() {
        // Default constructor
    }

    /**
     * Parameterized constructor to create a new account payable instance.
     *
     * @param idCuenta            the unique identifier of the account
     * @param monto               the total amount to pay
     * @param fechaVencimiento    the due date of the payment
     * @param fechaVenta          the purchase date
     * @param estado              the current account state
     * @param listaCuentaProducto the list of products related to this account
     */
    public CuentaPorPagar(int idCuenta, long monto, LocalDate fechaVencimiento, LocalDate fechaVenta,
                          EstadoCuenta estado, List<CuentaProducto> listaCuentaProducto) {
        super();
        this.idCuenta = idCuenta;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaCompra = fechaVenta;
        this.estado = estado;
        this.listaCuentaProducto = listaCuentaProducto;
    }

    /** @return the account ID */
    public int getIdCuenta() {
        return idCuenta;
    }

    /** @param idCuenta the account ID to set */
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    /** @return the total amount */
    public long getMonto() {
        return monto;
    }

    /** @param monto the total amount to set */
    public void setMonto(long monto) {
        this.monto = monto;
    }

    /** @return the due date */
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    /** @param fechaVencimiento the due date to set */
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /** @return the current account status */
    public EstadoCuenta getEstado() {
        return estado;
    }

    /** @param estado the current account status to set */
    public void setEstado(EstadoCuenta estado) {
        this.estado = estado;
    }

    /** @return the list of associated products */
    public List<CuentaProducto> getListaCuentaProducto() {
        return listaCuentaProducto;
    }

    /** @return the purchase date */
    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    /** @param fechaVenta the purchase date to set */
    public void setFechaCompra(LocalDate fechaVenta) {
        this.fechaCompra = fechaVenta;
    }

    /**
     * Sets the list of products related to this account and updates
     * the bidirectional relationship.
     *
     * @param listaCuentaProducto the product list to associate
     */
    public void setListaCuentaProducto(List<CuentaProducto> listaCuentaProducto) {
        this.listaCuentaProducto = listaCuentaProducto;
        for (CuentaProducto cuenta : this.listaCuentaProducto) {
            cuenta.setCuenta(this);
            cuenta.getIdCuentaProducto().setIdCuenta(idCuenta);
        }
    }
}
