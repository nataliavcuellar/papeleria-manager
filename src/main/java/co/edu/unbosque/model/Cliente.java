package co.edu.unbosque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a client entity in the system.
 * <p>
 * This class is mapped to the {@code cliente} table in the database and contains
 * basic client information such as identification number, name, last name,
 * phone number, and address.
 * </p>
 * 
 * @author InnovaCore
 * @version 1.0
 */
@Entity
@Table(name = "cliente")
public class Cliente {

    /** Unique identifier for each client. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int id_cliente;

    /** Client's national ID number. */
    @Column(name = "cedula", nullable = false, unique = true)
    private long cedula;

    /** Client's first name. */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /** Client's last name. */
    @Column(name = "apellido", nullable = false)
    private String apellido;

    /** Client's phone number. */
    @Column(name = "telefono", nullable = false)
    private long telefono;

    /** Client's home address. */
    @Column(name = "direccion", nullable = false)
    private String direccion;

    /**
     * Default constructor.
     * <p>
     * Required by JPA for entity instantiation.
     * </p>
     */
    public Cliente() {
        // Default constructor
    }

    /**
     * Constructs a {@code Cliente} object with all its attributes.
     * 
     * @param id_cliente the client's ID
     * @param cedula the client's identification number
     * @param nombre the client's first name
     * @param apellido the client's last name
     * @param telefono the client's phone number
     * @param direccion the client's address
     */
    public Cliente(int id_cliente, long cedula, String nombre, String apellido, long telefono, String direccion) {
        super();
        this.id_cliente = id_cliente;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    /**
     * Gets the client's database ID.
     * 
     * @return the client's ID
     */
    public int getId_cliente() {
        return id_cliente;
    }

    /**
     * Sets the client's database ID.
     * 
     * @param id_cliente the client's ID to set
     */
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * Gets the client's national ID number.
     * 
     * @return the client's ID number
     */
    public long getCedula() {
        return cedula;
    }

    /**
     * Sets the client's national ID number.
     * 
     * @param cedula the ID number to set
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
