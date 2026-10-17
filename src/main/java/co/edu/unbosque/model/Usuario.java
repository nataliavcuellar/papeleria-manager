package co.edu.unbosque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a system user.
 * <p>
 * This class maps to the "usuario" table in the database and stores user account
 * information, including login credentials and administrative privileges.
 * </p>
 * 
 * @author InnovaCore
 */
@Entity
@Table(name = "usuario")
public class Usuario {

	/**
	 * Unique identifier of the user.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int id_usuario;

	/**
	 * Username used for login. Must be unique.
	 */
	@Column(name = "usuario", nullable = false, unique = true)
	private String usuario;

	/**
	 * Password associated with the user account.
	 */
	@Column(name = "contrasena", nullable = false)
	private String contrasena;

	/**
	 * Indicates whether the user has administrative privileges.
	 */
	@Column(name = "esAdmin", nullable = false)
	private boolean esAdmin;

	/**
	 * Default constructor.
	 * <p>
	 * Initializes an empty user instance.
	 * </p>
	 */
	public Usuario() {
	}

	/**
	 * Parameterized constructor.
	 * <p>
	 * Creates a new user with all its attributes defined.
	 * </p>
	 * 
	 * @param id_usuario the unique user ID
	 * @param usuario    the username
	 * @param contrasena the user's password
	 * @param esAdmin    true if the user is an administrator, false otherwise
	 */
	public Usuario(int id_usuario, String usuario, String contrasena, boolean esAdmin) {
		this.id_usuario = id_usuario;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.esAdmin = esAdmin;
	}

	/**
	 * Gets the user's unique ID.
	 * 
	 * @return the user ID
	 */
	public int getIdUsuario() {
		return id_usuario;
	}

	/**
	 * Sets the user's unique ID.
	 * 
	 * @param idUsuario the user ID to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.id_usuario = idUsuario;
	}

	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the username.
	 * 
	 * @param usuario the username to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the user's password.
	 * 
	 * @return the password
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Sets the user's password.
	 * 
	 * @param contrasena the password to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Checks if the user has administrative privileges.
	 * 
	 * @return true if the user is an administrator, false otherwise
	 */
	public boolean isEsAdmin() {
		return esAdmin;
	}

	/**
	 * Sets whether the user has administrative privileges.
	 * 
	 * @param esAdmin true if the user is an administrator, false otherwise
	 */
	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}
}
