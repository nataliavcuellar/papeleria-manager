package co.edu.unbosque.model;

/**
 * Data Transfer Object (DTO) representing a supplier (Proveedor).
 * <p>
 * This class is used to transfer supplier data between layers of the application
 * without exposing the internal entity structure. It contains basic information
 * such as name, contact details, and delivery time.
 * </p>
 * 
 * @author InnovaCore
 */
public class ProveedorDTO {

	/**
	 * Unique identifier of the supplier.
	 */
	private int id_proveedor;

	/**
	 * Supplier's first name.
	 */
	private String nombre;

	/**
	 * Supplier's last name.
	 */
	private String apellido;

	/**
	 * Supplier's email address.
	 */
	private String correo;

	/**
	 * Supplier's phone number.
	 */
	private long telefono;

	/**
	 * Estimated delivery time in days.
	 */
	private int tiempo_entrega;

	/**
	 * Default constructor.
	 * <p>
	 * Initializes an empty supplier DTO instance.
	 * </p>
	 */
	public ProveedorDTO() {
	}

	/**
	 * Parameterized constructor.
	 * <p>
	 * Initializes a supplier DTO with all its attributes.
	 * </p>
	 *
	 * @param id_proveedor   the unique identifier of the supplier
	 * @param nombre         the supplier's first name
	 * @param apellido       the supplier's last name
	 * @param correo         the supplier's email address
	 * @param telefono       the supplier's phone number
	 * @param tiempo_entrega the supplier's delivery time in days
	 */
	public ProveedorDTO(int id_proveedor, String nombre, String apellido, String correo, long telefono,
			int tiempo_entrega) {
		super();
		this.id_proveedor = id_proveedor;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
		this.tiempo_entrega = tiempo_entrega;
	}

	/**
	 * Gets the supplier's unique ID.
	 *
	 * @return the supplier ID
	 */
	public int getId_proveedor() {
		return id_proveedor;
	}

	/**
	 * Sets the supplier's unique ID.
	 *
	 * @param id_proveedor the supplier ID to set
	 */
	public void setId_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	/**
	 * Gets the supplier's first name.
	 *
	 * @return the supplier's name
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the supplier's first name.
	 *
	 * @param nombre the supplier's name to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the supplier's last name.
	 *
	 * @return the supplier's last name
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Sets the supplier's last name.
	 *
	 * @param apellido the supplier's last name to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Gets the supplier's email address.
	 *
	 * @return the supplier's email
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Sets the supplier's email address.
	 *
	 * @param correo the supplier's email to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Gets the supplier's phone number.
	 *
	 * @return the supplier's phone number
	 */
	public long getTelefono() {
		return telefono;
	}

	/**
	 * Sets the supplier's phone number.
	 *
	 * @param telefono the supplier's phone number to set
	 */
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	/**
	 * Gets the supplier's estimated delivery time in days.
	 *
	 * @return the delivery time in days
	 */
	public int getTiempo_entrega() {
		return tiempo_entrega;
	}

	/**
	 * Sets the supplier's estimated delivery time in days.
	 *
	 * @param tiempo_entrega the delivery time to set
	 */
	public void setTiempo_entrega(int tiempo_entrega) {
		this.tiempo_entrega = tiempo_entrega;
	}

}
