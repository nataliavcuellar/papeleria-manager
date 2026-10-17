package co.edu.unbosque.model;

/**
 * Data Transfer Object (DTO) that represents the details of a sale.
 * This class is used to transfer sale detail data between different layers
 * of the application without exposing the entity model directly.
 * 
 * <p>Contains basic information such as the sale detail ID, subtotal,
 * quantity, and associated product.</p>
 * 
 * @author InnovaCore
 */
public class DetalleVentaDTO {

    /** Unique identifier of the sale detail. */
    private int idDetalle;

    /** Subtotal amount for this specific sale detail. */
    private long subtotal;

    /** Quantity of the product sold. */
    private int cantidad;

    /** Product associated with this sale detail. */
    private ProductoDTO producto;

    /**
     * Default constructor.
     * Initializes an empty {@code DetalleVentaDTO} instance.
     */
    public DetalleVentaDTO() {
        // Default constructor
    }

    /**
     * Creates a new {@code DetalleVentaDTO} instance with all its fields initialized.
     *
     * @param idDetalle the unique identifier of the sale detail
     * @param subtotal the subtotal amount of this detail
     * @param cantidad the quantity of the product sold
     * @param producto the associated product
     */
    public DetalleVentaDTO(int idDetalle, long subtotal, int cantidad, ProductoDTO producto) {
        super();
        this.idDetalle = idDetalle;
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.producto = producto;
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
    public ProductoDTO getProducto() {
        return producto;
    }

    /**
     * Sets the product associated with this sale detail.
     *
     * @param producto the product to associate
     */
    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }
}
