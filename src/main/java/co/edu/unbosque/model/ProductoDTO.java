package co.edu.unbosque.model;

/**
 * Represents the Data Transfer Object (DTO) for the {@code Producto} entity.  
 * <p>
 * This class is used to transfer product-related data between layers of the
 * application, preventing direct exposure of entity objects.
 * </p>
 * 
 * @author InnovaCore
 */
public class ProductoDTO {

	/**
	 * Unique identifier for the product.
	 */
	private int id_producto;

	/**
	 * Unique code used to identify the product.
	 */
	private String codigo;

	/**
	 * Descriptive name of the product.
	 */
	private String nombre;

	/**
	 * The purchase price of the product.
	 */
	private double precio_compra;

	/**
	 * The selling price of the product.
	 */
	private double precio_venta;

	/**
	 * The supplier associated with this product.
	 */
	private ProveedorDTO proveedor;

	/**
	 * The current available stock of the product.
	 */
	private int stock;

	/**
	 * The minimum stock level before restocking is required.
	 */
	private int stock_minimo;

	/**
	 * Default constructor.
	 */
	public ProductoDTO() {
		// Default constructor
	}

	/**
	 * Parameterized constructor that initializes all attributes of the product.
	 * 
	 * @param id_producto   the product identifier
	 * @param codigo        the product code
	 * @param nombre        the product name
	 * @param precio_compra the purchase price
	 * @param precio_venta  the selling price
	 * @param proveedor     the supplier of the product
	 * @param stock         the current stock quantity
	 * @param stock_minimo  the minimum stock allowed
	 */
	public ProductoDTO(int id_producto, String codigo, String nombre, double precio_compra, double precio_venta,
			ProveedorDTO proveedor, int stock, int stock_minimo) {
		super();
		this.id_producto = id_producto;
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio_compra = precio_compra;
		this.precio_venta = precio_venta;
		this.proveedor = proveedor;
		this.stock = stock;
		this.stock_minimo = stock_minimo;
	}

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
	 * Gets the purchase price of the product.
	 * 
	 * @return the purchase price
	 */
	public double getPrecio_compra() {
		return precio_compra;
	}

	/**
	 * Sets the purchase price of the product.
	 * 
	 * @param precio_compra the purchase price to set
	 */
	public void setPrecio_compra(double precio_compra) {
		this.precio_compra = precio_compra;
	}

	/**
	 * Gets the selling price of the product.
	 * 
	 * @return the selling price
	 */
	public double getPrecio_venta() {
		return precio_venta;
	}

	/**
	 * Sets the selling price of the product.
	 * 
	 * @param precio_venta the selling price to set
	 */
	public void setPrecio_venta(double precio_venta) {
		this.precio_venta = precio_venta;
	}

	/**
	 * Gets the supplier associated with this product.
	 * 
	 * @return the supplier DTO
	 */
	public ProveedorDTO getProveedor() {
		return proveedor;
	}

	/**
	 * Sets the supplier for this product.
	 * 
	 * @param proveedor the supplier DTO to set
	 */
	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * Gets the current stock quantity.
	 * 
	 * @return the stock value
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Sets the current stock quantity.
	 * 
	 * @param stock the stock value to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * Gets the minimum stock level.
	 * 
	 * @return the minimum stock value
	 */
	public int getStock_minimo() {
		return stock_minimo;
	}

	/**
	 * Sets the minimum stock level.
	 * 
	 * @param stock_minimo the minimum stock value to set
	 */
	public void setStock_minimo(int stock_minimo) {
		this.stock_minimo = stock_minimo;
	}

}
