package co.edu.unbosque.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 * Entity class that represents the association between an account payable
 * and a product. This table stores which products are linked to which
 * payable accounts and the specific amount corresponding to each product.
 * <p>
 * The class uses a composite key represented by {@link CuentaProductoId},
 * and maintains bidirectional relationships with both {@link Producto}
 * and {@link CuentaPorPagar}.
 * </p>
 * 
 * <p>
 * This entity corresponds to the database table <b>cuentaproducto</b>.
 * </p>
 * 
 * @author Nube
 */
@Entity
@Table(name = "cuentaproducto")
public class CuentaProducto {

    /** Composite primary key that identifies the account-product relationship. */
    @EmbeddedId
    private CuentaProductoId idCuentaProducto;

    /** Product associated with this account-product record. */
    @ManyToOne
    @MapsId("idProducto")
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    /** Account payable linked to this account-product record. */
    @ManyToOne
    @MapsId("idCuenta")
    @JoinColumn(name = "id_cuenta", nullable = false)
    private CuentaPorPagar cuenta;

    /** Amount owed for this specific product in the payable account. */
    @Column(name = "monto", nullable = false)
    private Long monto;

    /**
     * Default constructor.
     */
    public CuentaProducto() {
        // Default constructor
    }

    /**
     * Parameterized constructor used to initialize a new account-product relationship.
     * 
     * @param idCuentaProducto the composite identifier of the relationship
     * @param producto         the product associated with the payable account
     * @param cuenta           the payable account linked to this product
     * @param monto            the amount associated with this product
     */
    public CuentaProducto(CuentaProductoId idCuentaProducto, Producto producto, CuentaPorPagar cuenta, Long monto) {
        super();
        this.idCuentaProducto = idCuentaProducto;
        this.producto = producto;
        this.cuenta = cuenta;
        this.monto = monto;
    }

    /**
     * @return the product associated with this record
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * @param producto the product to associate
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * @return the payable account linked to this record
     */
    public CuentaPorPagar getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the payable account to associate
     */
    public void setCuenta(CuentaPorPagar cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the amount linked to this product
     */
    public Long getMonto() {
        return monto;
    }

    /**
     * @param monto the amount to set for this product
     */
    public void setMonto(Long monto) {
        this.monto = monto;
    }

    /**
     * @return the composite identifier of the account-product relationship
     */
    public CuentaProductoId getIdCuentaProducto() {
        return idCuentaProducto;
    }

    /**
     * @param idCuentaProducto the composite identifier to set
     */
    public void setIdCuentaProducto(CuentaProductoId idCuentaProducto) {
        this.idCuentaProducto = idCuentaProducto;
    }
}
