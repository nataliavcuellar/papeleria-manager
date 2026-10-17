package co.edu.unbosque.model;

import java.time.LocalDate;
import java.util.List;

import co.edu.unbosque.util.enums.EstadoVenta;
import co.edu.unbosque.util.enums.TipoVenta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a sales transaction in the system.
 * <p>
 * Each sale contains details such as the date, total amount, state,
 * type of sale, invoice path, and relationships with the client and
 * detailed sale items.
 * </p>
 * 
 * @author InnovaCore
 */
@Entity
@Table(name = "venta")
public class Venta {

	/**
	 * Unique identifier of the sale.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venta")
	private int idVenta;

	/**
	 * Date when the sale was made.
	 */
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;

	/**
	 * Total amount of the sale.
	 */
	@Column(name = "total", nullable = false)
	private long total;

	/**
	 * Current state of the sale (e.g., CONTINGENCIA, PENDIENTE, VALIDADO).
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", columnDefinition = "ENUM('CONTINGENCIA', 'PENDIENTE', 'VALIDADO')", nullable = false)
	private EstadoVenta estado;

	/**
	 * File path where the invoice is stored.
	 */
	@Column(name = "ruta_factura", nullable = false)
	private String rutaFactura;

	/**
	 * Type of sale (e.g., FIADA, PAGADA).
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_venta", columnDefinition = "ENUM('FIADA', 'PAGADA')", nullable = false)
	private TipoVenta tipoVenta;

	/**
	 * Client associated with the sale.
	 */
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	/**
	 * List of sale details linked to this sale.
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "venta", orphanRemoval = true)
	private List<DetalleVenta> listaDetallesVenta;

	/**
	 * Default constructor.
	 * <p>
	 * Creates an empty sale instance.
	 * </p>
	 */
	public Venta() {
		// Default constructor
	}

	/**
	 * Parameterized constructor.
	 * <p>
	 * Creates a new sale with all attributes defined.
	 * </p>
	 * 
	 * @param idVenta             the unique sale ID
	 * @param fecha               the sale date
	 * @param total               the total amount
	 * @param estado              the sale state
	 * @param rutaFactura         the invoice path
	 * @param tipoVenta           the sale type
	 * @param cliente             the client involved in the sale
	 * @param listaDetallesVenta  the list of sale details
	 */
	public Venta(int idVenta, LocalDate fecha, long total, EstadoVenta estado, String rutaFactura, TipoVenta tipoVenta,
			Cliente cliente, List<DetalleVenta> listaDetallesVenta) {
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
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the client associated with the sale.
	 * 
	 * @param cliente the client to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the list of sale details.
	 * 
	 * @return the list of sale details
	 */
	public List<DetalleVenta> getListaDetallesVenta() {
		return listaDetallesVenta;
	}

	/**
	 * Sets the list of sale details.
	 * <p>
	 * Automatically associates each {@link DetalleVenta} with this sale.
	 * </p>
	 * 
	 * @param listaDetallesVenta the list of sale details to set
	 */
	public void setListaDetallesVenta(List<DetalleVenta> listaDetallesVenta) {
		this.listaDetallesVenta = listaDetallesVenta;
		if (this.listaDetallesVenta != null) {
			for (DetalleVenta detalle : this.listaDetallesVenta) {
				detalle.setVenta(this);
			}
		}
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

}
