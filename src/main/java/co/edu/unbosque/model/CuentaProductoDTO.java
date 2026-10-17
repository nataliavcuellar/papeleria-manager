package co.edu.unbosque.model;

/**
 * Data Transfer Object (DTO) that represents the relationship between a payable account
 * and a product, including the amount associated with that product.
 * <p>
 * This class is used to transfer data between layers without exposing the entity directly.
 * </p>
 * 
 * @author InnovaCore
 */
public class CuentaProductoDTO {

    /** Composite identifier that represents the account-product relationship. */
    private CuentaProductoId idCuentaProducto;

    /** Identifier of the payable account associated with this record. */
    private int idCuenta;

    /** Product information associated with the payable account. */
    private ProductoDTO producto;

    /** Amount owed for this specific product. */
    private long monto;

    /**
     * Default constructor.
     */
    public CuentaProductoDTO() {
        // Default constructor
    }

    /**
     * Parameterized constructor that initializes all fields of this DTO.
     * 
     * @param idCuentaProducto the composite identifier of the account-product relationship
     * @param idCuenta         the ID of the payable account
     * @param producto         the product details
     * @param monto            the amount owed for the product
     */
    public CuentaProductoDTO(CuentaProductoId idCuentaProducto, int idCuenta, ProductoDTO producto, long monto) {
        super();
        this.idCuentaProducto = idCuentaProducto;
        this.idCuenta = idCuenta;
        this.producto = producto;
        this.monto = monto;
    }

    /**
     * @return the product details
     */
    public ProductoDTO getProducto() {
        return producto;
    }

    /**
     * @param producto the product to associate
     */
    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    /**
     * @return the amount owed for the product
     */
    public long getMonto() {
        return monto;
    }

    /**
     * @param monto the amount to set
     */
    public void setMonto(long monto) {
        this.monto = monto;
    }

    /**
     * @return the ID of the payable account
     */
    public int getIdCuenta() {
        return idCuenta;
    }

    /**
     * @param idCuenta the ID of the payable account to set
     */
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
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
