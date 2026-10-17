package co.edu.unbosque.model;

import java.time.LocalDate;
import java.util.List;

import co.edu.unbosque.util.enums.EstadoCuenta;

/**
 * Data Transfer Object (DTO) that represents an account payable record.
 * <p>
 * This class is used to transfer data between layers without exposing
 * the entity structure. It contains basic information about the payable
 * account such as its ID, total amount, status, due date, purchase date,
 * and the list of products associated with it.
 * </p>
 * 
 * @author Nube
 */
public class CuentaPorPagarDTO {

    /** Unique identifier for the payable account. */
    private int idCuenta;

    /** Total amount to be paid. */
    private long monto;

    /** Current state of the account (PENDING, PAID, or EXPIRED). */
    private EstadoCuenta estado;

    /** Date when the account must be paid. */
    private LocalDate fechaVencimiento;

    /** Date when the purchase was made. */
    private LocalDate fechaCompra;

    /** List of products linked to this payable account. */
    private List<CuentaProductoDTO> listaCuentaProducto;

    /**
     * Default constructor.
     */
    public CuentaPorPagarDTO() {
        // Default constructor
    }

    /**
     * Parameterized constructor to create a new payable account DTO.
     * 
     * @param idCuenta            the unique identifier of the account
     * @param monto               the total amount to pay
     * @param estado              the current account status
     * @param fechaVencimiento    the due date of the account
     * @param fechaCompra         the purchase date
     * @param listaCuentaProducto the list of products related to this account
     */
    public CuentaPorPagarDTO(int idCuenta, long monto, EstadoCuenta estado, LocalDate fechaVencimiento,
                             LocalDate fechaCompra, List<CuentaProductoDTO> listaCuentaProducto) {
        super();
        this.idCuenta = idCuenta;
        this.monto = monto;
        this.estado = estado;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaCompra = fechaCompra;
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

    /** @return the current account status */
    public EstadoCuenta getEstado() {
        return estado;
    }

    /** @param estado the account status to set */
    public void setEstado(EstadoCuenta estado) {
        this.estado = estado;
    }

    /** @return the due date */
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    /** @param fechaVencimiento the due date to set */
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /** @return the list of associated products */
    public List<CuentaProductoDTO> getListaCuentaProducto() {
        return listaCuentaProducto;
    }

    /** @param listaCuentaProducto the list of associated products to set */
    public void setListaCuentaProducto(List<CuentaProductoDTO> listaCuentaProducto) {
        this.listaCuentaProducto = listaCuentaProducto;
    }

    /** @return the purchase date */
    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    /** @param fechaCompra the purchase date to set */
    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
