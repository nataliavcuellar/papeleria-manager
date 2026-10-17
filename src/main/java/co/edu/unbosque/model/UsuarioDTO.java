package co.edu.unbosque.model;

/**
 * Data Transfer Object (DTO) representing a system user.
 * <p>
 * This class is used to transfer user data between different layers of the application
 * without exposing the entity directly.
 * </p>
 * 
 * @author InnovaCore
 */
public class UsuarioDTO {

	/**
	 * Unique identifier of the user.
	 */
	private int id_usuario;

	/**
	 * Username used for authentication.
	 */
	private String usuario;

	/**
	 * Password associated with the user account.
	 */
	private String contrasena;

	/**
	 * Indicates whether the user has administrative privileges.
	 */
	private boolean esAdmin;

	/**
	 * Default constructor.
	 * <p>
	 * Initializes an empty user data object.
	 * </p>
	 */
	public UsuarioDTO() {
		// Default constructor
	}

	/**
	 * Parameterized constructor.
	 * <p>
	 * Creates a new user data object with all attributes defined.
	 * </p>
	 * 
	 * @param id_usuario the unique user ID
	 * @param usuario    the username
	 * @param contrasena the user's password
	 * @param esAdmin    true if the user is an administrator, false otherwise
	 */
	public UsuarioDTO(int id_usuario, String usuario, String contrasena, boolean esAdmin) {
		super();
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
	public int getId_usuario() {
		return id_usuario;
	}

	/**
	 * Sets the user's unique ID.
	 * 
	 * @param id_usuario the user ID to set
	 */
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
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
