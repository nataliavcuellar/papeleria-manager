package co.edu.unbosque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents the supplier entity within the system.  
 * <p>
 * This class maps the {@code proveedor} table in the database, storing detailed
 * information about each supplier such as name, contact details, and delivery time.
 * </p>
 * 
 * @author InnovaCore
 * @since 1.0
 */
@Entity
@Table(name = "proveedor")
public class Proveedor {

	/**
	 * Unique identifier for the supplier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proveedor")
	private int id_proveedor;

	/**
	 * Supplier's first name.
	 */
	@Column(name = "nombre", nullable = false)
	private String nombre;

	/**
	 * Supplier's last name.
	 */
	@Column(name = "apellido", nullable = false)
	private String apellido;

	/**
	 * Supplier's email address.  
	 * Must be unique across all suppliers.
	 */
	@Column(name = "correo", nullable = false, unique = true)
	private String correo;

	/**
	 * Supplier's contact phone number.
	 */
	@Column(name = "telefono", nullable = false)
	private long telefono;

	/**
	 * Average delivery time (in days) promised by the supplier.
	 */
	@Column(name = "tiempo_entrega", nullable = false)
	private int tiempo_entrega;

	/**
	 * Default constructor.  
	 * Creates an empty supplier instance.
	 */
	public Proveedor() {
		// Default constructor
	}

	/**
	 * Parameterized constructor that initializes all attributes.
	 * 
	 * @param id_proveedor   the supplier's identifier
	 * @param nombre         the supplier's first name
	 * @param apellido       the supplier's last name
	 * @param correo         the supplier's email address
	 * @param telefono       the supplier's phone number
	 * @param tiempo_entrega the supplier's delivery time in days
	 */
	public Proveedor(int id_proveedor, String nombre, String apellido, String correo, long telefono,
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
	 * Gets the supplier ID.
	 * 
	 * @return the supplier ID
	 */
	public int getId_proveedor() {
		return id_proveedor;
	}

	/**
	 * Sets the supplier ID.
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
	 * @return the phone number
	 */
	public long getTelefono() {
		return telefono;
	}

	/**
	 * Sets the supplier's phone number.
	 * 
	 * @param telefono the phone number to set
	 */
	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	/**
	 * Gets the supplier's delivery time in days.
	 * 
	 * @return the delivery time
	 */
	public int getTiempo_entrega() {
		return tiempo_entrega;
	}

	/**
	 * Sets the supplier's delivery time in days.
	 * 
	 * @param tiempo_entrega the delivery time to set
	 */
	public void setTiempo_entrega(int tiempo_entrega) {
		this.tiempo_entrega = tiempo_entrega;
	}
}
