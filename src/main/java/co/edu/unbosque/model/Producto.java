package co.edu.unbosque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a product within the system.
 * <p>
 * The {@code Producto} entity stores essential information about each product,
 * including its unique code, name, purchase and sale prices, stock levels, and
 * supplier relationship.
 * </p>
 * 
 * <p>
 * This class is mapped to the {@code producto} table in the database.
 * </p>
 * 
 * @author InnovaCore
 */
@Entity
@Table(name = "producto")
public class Producto {

    /** Unique identifier for the product. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int id_producto;

    /** Unique product code. */
    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    /** Product name. */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /** Purchase price of the product. */
    @Column(name = "precio_compra", nullable = false)
    private double precio_compra;

    /** Selling price of the product. */
    @Column(name = "precio_venta", nullable = false)
    private double precio_venta;

    /** Supplier associated with this product. */
    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    /** Current stock quantity. */
    @Column(name = "stock", nullable = false)
    private int stock;

    /** Minimum stock level required before restocking. */
    @Column(name = "stock_minimo", nullable = false)
    private int stock_minimo;

    // -------------------- Getters & Setters --------------------

    /**
     * Gets the product ID.
     * 
     * @return the product ID
     */
    public int getId_producto() {
        return id_producto;
    }

    /**
     * Sets the product ID.
     * 
     * @param id_producto the product ID to set
     */
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    /**
     * Gets the product code.
     * 
     * @return the product code
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the product code.
     * 
     * @param codigo the product code to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets the product name.
     * 
     * @return the product name
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the product name.
     * 
     * @param nombre the product name to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the purchase price.
     * 
     * @return the purchase price
     */
    public double getPrecio_compra() {
        return precio_compra;
    }

    /**
     * Sets the purchase price.
     * 
     * @param precio_compra the purchase price to set
     */
    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    /**
     * Gets the selling price.
     * 
     * @return the selling price
     */
    public double getPrecio_venta() {
        return precio_venta;
    }

    /**
     * Sets the selling price.
     * 
     * @param precio_venta the selling price to set
     */
    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    /**
     * Gets the supplier.
     * 
     * @return the supplier
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * Sets the supplier.
     * 
     * @param proveedor the supplier to set
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Gets the current stock quantity.
     * 
     * @return the current stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the current stock quantity.
     * 
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the minimum stock level.
     * 
     * @return the minimum stock level
     */
    public int getStock_minimo() {
        return stock_minimo;
    }

    /**
     * Sets the minimum stock level.
     * 
     * @param stock_minimo the minimum stock level to set
     */
    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }
}
