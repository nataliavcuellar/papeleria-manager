package co.edu.unbosque.model;

import java.time.LocalDate;
import java.util.List;

import co.edu.unbosque.util.enums.EstadoVenta;
import co.edu.unbosque.util.enums.TipoVenta;

/**
 * Represents a data transfer object (DTO) for a sales transaction.
 * <p>
 * This class is used to transfer sale-related information between layers 
 * of the application without exposing the persistence entity directly.
 * It includes details such as the sale date, total amount, state, 
 * type of sale, invoice path, associated client, and sale details.
 * </p>
 * 
 * @author InnocaCore
 */
public class VentaDTO {

	/**
	 * Unique identifier of the sale.
	 */
	private int idVenta;

	/**
	 * Date when the sale was made.
	 */
	private LocalDate fecha;

	/**
	 * Total amount of the sale.
	 */
	private long total;

	/**
	 * Current state of the sale (e.g., CONTINGENCIA, PENDIENTE, VALIDADO).
	 */
	private EstadoVenta estado;

	/**
	 * File path where the invoice is stored.
	 */
	private String rutaFactura;

	/**
	 * Type of sale (e.g., FIADA, PAGADA).
	 */
	private TipoVenta tipoVenta;

	/**
	 * Client associated with the sale.
	 */
	private ClienteDTO cliente;

	/**
	 * List of detailed sale items related to this sale.
	 */
	private List<DetalleVentaDTO> listaDetallesVenta;

	/**
	 * Default constructor.
	 * <p>
	 * Creates an empty instance of the sale DTO.
	 * </p>
	 */
	public VentaDTO() {
		// Default constructor
	}

	/**
	 * Parameterized constructor.
	 * <p>
	 * Creates a new sale DTO with all attributes initialized.
	 * </p>
	 * 
	 * @param idVenta             the unique sale ID
	 * @param fecha               the sale date
	 * @param total               the total amount of the sale
	 * @param estado              the sale state
	 * @param rutaFactura         the invoice file path
	 * @param tipoVenta           the type of sale
	 * @param cliente             the client associated with the sale
	 * @param listaDetallesVenta  the list of sale details
	 */
	public VentaDTO(int idVenta, LocalDate fecha, long total, EstadoVenta estado, String rutaFactura,
			TipoVenta tipoVenta, ClienteDTO cliente, List<DetalleVentaDTO> listaDetallesVenta) {
		super();
		this.idVenta = idVenta;
		this.fecha = fecha;
		this.total = total;
		this.estado = estado;
		this.rutaFactura = rutaFactura;
		this.tipoVenta = tipoVenta;
		this.cliente = cliente;
		this.listaDetallesVenta = listaDetallesVenta;
	}

	/**
	 * Gets the sale type.
	 * 
	 * @return the sale type
	 */
	public TipoVenta getTipoVenta() {
		return tipoVenta;
	}

	/**
	 * Sets the sale type.
	 * 
	 * @param tipoVenta the sale type to set
	 */
	public void setTipoVenta(TipoVenta tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	/**
	 * Gets the sale ID.
	 * 
	 * @return the sale ID
	 */
	public int getIdVenta() {
		return idVenta;
	}

	/**
	 * Sets the sale ID.
	 * 
	 * @param idVenta the sale ID to set
	 */
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	/**
	 * Gets the sale date.
	 * 
	 * @return the sale date
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Sets the sale date.
	 * 
	 * @param fecha the sale date to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the total sale amount.
	 * 
	 * @return the total amount
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * Sets the total sale amount.
	 * 
	 * @param total the total amount to set
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * Gets the sale state.
	 * 
	 * @return the sale state
	 */
	public EstadoVenta getEstado() {
		return estado;
	}

	/**
	 * Sets the sale state.
	 * 
	 * @param estado the sale state to set
	 */
	public void setEstado(EstadoVenta estado) {
		this.estado = estado;
	}

	/**
	 * Gets the invoice file path.
	 * 
	 * @return the invoice path
	 */
	public String getRutaFactura() {
		return rutaFactura;
	}

	/**
	 * Sets the invoice file path.
	 * 
	 * @param rutaFactura the invoice path to set
	 */
	public void setRutaFactura(String rutaFactura) {
		this.rutaFactura = rutaFactura;
	}

	/**
	 * Gets the client associated with the sale.
	 * 
	 * @return the client
	 */
	public ClienteDTO getCliente() {
		return cliente;
	}

	/**
	 * Sets the client associated with the sale.
	 * 
	 * @param cliente the client to set
	 */
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	/**
	 * Sets the list of sale details.
	 * 
	 * @param listaDetallesVenta the list of sale details to set
	 */
	public void setListaDetallesVenta(List<DetalleVentaDTO> listaDetallesVenta) {
		this.listaDetallesVenta = listaDetallesVenta;
	}

	/**
	 * Gets the list of sale details.
	 * 
	 * @return the list of sale details
	 */
	public List<DetalleVentaDTO> getListaDetallesVenta() {
		return listaDetallesVenta;
	}
}
