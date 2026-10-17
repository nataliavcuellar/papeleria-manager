package co.edu.unbosque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents the detail of a sale, linking a product with its corresponding sale.
 * This entity stores information such as quantity, subtotal, and relationships
 * to both {@link Producto} and {@link Venta}.
 * 
 * <p>Each record in this table corresponds to a specific item included in a sale.</p>
 * 
 * @author InnovaCore
 */
@Entity
@Table(name = "detalleventa")
public class DetalleVenta {

    /** Unique identifier for the sale detail. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private int idDetalle;

    /** Subtotal amount for this specific sale detail. */
    @Column(name = "subtotal", nullable = false)
    private long subtotal;

    /** Quantity of the product sold in this detail. */
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    /** Product associated with this sale detail. */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    /** Sale to which this detail belongs. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    /**
     * Default constructor.
     * Initializes an empty {@code DetalleVenta} instance.
     */
    public DetalleVenta() {
        // Default constructor
    }

    /**
     * Creates a new {@code DetalleVenta} instance with all its fields initialized.
     *
     * @param idDetalle the unique identifier of the sale detail
     * @param subtotal the subtotal amount of this detail
     * @param cantidad the quantity of the product sold
     * @param producto the associated product
     * @param venta the sale this detail belongs to
     */
    public DetalleVenta(int idDetalle, long subtotal, int cantidad, Producto producto, Venta venta) {
        super();
        this.idDetalle = idDetalle;
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.producto = producto;
        this.venta = venta;
    }

    /**
     * Gets the unique identifier of the sale detail.
     *
     * @return the sale detail ID
     */
    public int getIdDetalle() {
        return idDetalle;
    }

    /**
     * Sets the unique identifier of the sale detail.
     *
     * @param idDetalle the new sale detail ID
     */
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    /**
     * Gets the subtotal of this sale detail.
     *
     * @return the subtotal amount
     */
    public long getSubtotal() {
        return subtotal;
    }

    /**
     * Sets the subtotal of this sale detail.
     *
     * @param subtotal the subtotal amount to set
     */
    public void setSubtotal(long subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Gets the quantity of the product sold in this detail.
     *
     * @return the quantity of the product
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Sets the quantity of the product sold in this detail.
     *
     * @param cantidad the quantity to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Gets the product associated with this sale detail.
     *
     * @return the associated product
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Sets the product associated with this sale detail.
     *
     * @param producto the product to associate
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Gets the sale that this detail belongs to.
     *
     * @return the associated sale
     */
    public Venta getVenta() {
        return venta;
    }

    /**
     * Sets the sale that this detail belongs to.
     *
     * @param venta the sale to associate
     */
    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
