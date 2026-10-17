package co.edu.unbosque.model;

/**
 * Data Transfer Object (DTO) for the {@link Cliente} entity.
 * <p>
 * This class is used to transfer client data between different layers
 * of the application without exposing the entity directly.
 * </p>
 * 
 * @author InnovaCore
 * @version 1.0
 */
public class ClienteDTO {

    /** Unique identifier for the client. */
    private int id_cliente;

    /** Client's national ID number. */
    private long cedula;

    /** Client's first name. */
    private String nombre;

    /** Client's last name. */
    private String apellido;

    /** Client's phone number. */
    private long telefono;

    /** Client's home address. */
    private String direccion;

    /**
     * Default constructor.
     * <p>
     * Creates an empty {@code ClienteDTO} instance.
     * </p>
     */
    public ClienteDTO() {
        // Default constructor
    }

    /**
     * Constructs a {@code ClienteDTO} with all its attributes.
     * 
     * @param id_cliente the client's unique ID
     * @param cedula the client's identification number
     * @param nombre the client's first name
     * @param apellido the client's last name
     * @param telefono the client's phone number
     * @param direccion the client's address
     */
    public ClienteDTO(int id_cliente, long cedula, String nombre, String apellido, long telefono, String direccion) {
        super();
        this.id_cliente = id_cliente;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    /**
     * Gets the client's ID.
     * 
     * @return the client's ID
     */
    public int getId_cliente() {
        return id_cliente;
    }

    /**
     * Sets the client's ID.
     * 
     * @param id_cliente the client's ID to set
     */
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * Gets the client's identification number.
     * 
     * @return the client's identification number
     */
    public long getCedula() {
        return cedula;
    }

    /**
     * Sets the client's identification number.
     * 
     * @param cedula the identification number to set
     */
    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    /**
     * Gets the client's first name.
     * 
     * @return the client's first name
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the client's first name.
     * 
     * @param nombre the name to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the client's last name.
     * 
     * @return the client's last name
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets the client's last name.
     * 
     * @param apellido the last name to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Gets the client's phone number.
     * 
     * @return the client's phone number
     */
    public long getTelefono() {
        return telefono;
    }

    /**
     * Sets the client's phone number.
     * 
     * @param telefono the phone number to set
     */
    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    /**
     * Gets the client's address.
     * 
     * @return the client's address
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets the client's address.
     * 
     * @param direccion the address to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
