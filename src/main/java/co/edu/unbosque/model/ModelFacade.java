package co.edu.unbosque.model;

import co.edu.unbosque.model.persistence.ClienteDAO;
import co.edu.unbosque.model.persistence.CuentaPorCobrarDAO;
import co.edu.unbosque.model.persistence.CuentaProductoDAO;
import co.edu.unbosque.model.persistence.CuentasPorPagarDAO;
import co.edu.unbosque.model.persistence.DetalleVentaDAO;
import co.edu.unbosque.model.persistence.ProductoDAO;
import co.edu.unbosque.model.persistence.ProveedorDAO;
import co.edu.unbosque.model.persistence.ReporteDAO;
import co.edu.unbosque.model.persistence.UsuarioDAO;
import co.edu.unbosque.model.persistence.VentaDAO;

/**
 * Acts as a facade for the data model layer.
 * <p>
 * The {@code ModelFacade} class provides centralized access to all DAO (Data
 * Access Object) instances used throughout the system. This structure promotes
 * decoupling between the controller and persistence layers, improving
 * maintainability and reducing dependencies.
 * </p>
 * 
 * <p>
 * Each DAO corresponds to a specific entity within the application.
 * </p>
 * 
 * @author InnovaCore
 */
public class ModelFacade {

	/** Data Access Object for user operations. */
	private UsuarioDAO usuarioDAO;

	/** Data Access Object for client operations. */
	private ClienteDAO clienteDAO;

	/** Data Access Object for supplier operations. */
	private ProveedorDAO proveedorDAO;

	/** Data Access Object for product operations. */
	private ProductoDAO productoDAO;

	/** Data Access Object for sales operations. */
	private VentaDAO ventaDAO;

	/** Data Access Object for sale detail operations. */
	private DetalleVentaDAO detalleVentaDAO;

	/** Data Access Object for accounts receivable operations. */
	private CuentaPorCobrarDAO cPorCobrarDAO;

	/** Data Access Object for accounts payable operations. */
	private CuentasPorPagarDAO cPorPagarDAO;

	/** Data Access Object for product-account operations. */
	private CuentaProductoDAO cProductoDAO;

	/**
	 * Data Access Object for reports generated as pdf by the sistem.
	 */
	private ReporteDAO reporteDAO;

	/**
	 * Default constructor that initializes all DAO instances.
	 */
	public ModelFacade() {
		usuarioDAO = new UsuarioDAO();
		clienteDAO = new ClienteDAO();
		proveedorDAO = new ProveedorDAO();
		productoDAO = new ProductoDAO();
		ventaDAO = new VentaDAO();
		detalleVentaDAO = new DetalleVentaDAO();
		cPorCobrarDAO = new CuentaPorCobrarDAO();
		cPorPagarDAO = new CuentasPorPagarDAO();
		cProductoDAO = new CuentaProductoDAO();
		reporteDAO = new ReporteDAO();
	}

	/**
	 * Gets the {@link ProductoDAO}.
	 * 
	 * @return the product DAO
	 */
	public ProductoDAO getProductoDAO() {
		return productoDAO;
	}

	/**
	 * Sets the {@link ProductoDAO}.
	 * 
	 * @param productoDAO the product DAO to set
	 */
	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}

	/**
	 * Gets the {@link ProveedorDAO}.
	 * 
	 * @return the supplier DAO
	 */
	public ProveedorDAO getProveedorDAO() {
		return proveedorDAO;
	}

	/**
	 * Sets the {@link ProveedorDAO}.
	 * 
	 * @param proveedorDAO the supplier DAO to set
	 */
	public void setProveedorDAO(ProveedorDAO proveedorDAO) {
		this.proveedorDAO = proveedorDAO;
	}

	/**
	 * Gets the {@link UsuarioDAO}.
	 * 
	 * @return the user DAO
	 */
	public UsuarioDAO getuDAO() {
		return usuarioDAO;
	}

	/**
	 * Sets the {@link UsuarioDAO}.
	 * 
	 * @param uDAO the user DAO to set
	 */
	public void setuDAO(UsuarioDAO uDAO) {
		this.usuarioDAO = uDAO;
	}

	/**
	 * Gets the {@link UsuarioDAO}.
	 * 
	 * @return the user DAO
	 */
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	/**
	 * Sets the {@link UsuarioDAO}.
	 * 
	 * @param usuarioDAO the user DAO to set
	 */
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	/**
	 * Gets the {@link ClienteDAO}.
	 * 
	 * @return the client DAO
	 */
	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	/**
	 * Sets the {@link ClienteDAO}.
	 * 
	 * @param clienteDAO the client DAO to set
	 */
	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	/**
	 * Gets the {@link VentaDAO}.
	 * 
	 * @return the sale DAO
	 */
	public VentaDAO getVentaDAO() {
		return ventaDAO;
	}

	/**
	 * Sets the {@link VentaDAO}.
	 * 
	 * @param ventaDAO the sale DAO to set
	 */
	public void setVentaDAO(VentaDAO ventaDAO) {
		this.ventaDAO = ventaDAO;
	}

	/**
	 * Gets the {@link DetalleVentaDAO}.
	 * 
	 * @return the sale detail DAO
	 */
	public DetalleVentaDAO getDetalleVentaDAO() {
		return detalleVentaDAO;
	}

	/**
	 * Sets the {@link DetalleVentaDAO}.
	 * 
	 * @param detalleVentaDAO the sale detail DAO to set
	 */
	public void setDetalleVentaDAO(DetalleVentaDAO detalleVentaDAO) {
		this.detalleVentaDAO = detalleVentaDAO;
	}

	/**
	 * Gets the {@link CuentaPorCobrarDAO}.
	 * 
	 * @return the accounts receivable DAO
	 */
	public CuentaPorCobrarDAO getcPorCobrarDAO() {
		return cPorCobrarDAO;
	}

	/**
	 * Sets the {@link CuentaPorCobrarDAO}.
	 * 
	 * @param cPorCobrarDAO the accounts receivable DAO to set
	 */
	public void setcPorCobrarDAO(CuentaPorCobrarDAO cPorCobrarDAO) {
		this.cPorCobrarDAO = cPorCobrarDAO;
	}

	/**
	 * Gets the {@link CuentasPorPagarDAO}.
	 * 
	 * @return the accounts payable DAO
	 */
	public CuentasPorPagarDAO getcPorPagarDAO() {
		return cPorPagarDAO;
	}

	/**
	 * Sets the {@link CuentasPorPagarDAO}.
	 * 
	 * @param cPorPagarDAO the accounts payable DAO to set
	 */
	public void setcPorPagarDAO(CuentasPorPagarDAO cPorPagarDAO) {
		this.cPorPagarDAO = cPorPagarDAO;
	}

	/**
	 * Gets the {@link CuentaProductoDAO}.
	 * 
	 * @return the account-product DAO
	 */
	public CuentaProductoDAO getcProductoDAO() {
		return cProductoDAO;
	}

	/**
	 * Sets the {@link CuentaProductoDAO}.
	 * 
	 * @param cProductoDAO the account-product DAO to set
	 */
	public void setcProductoDAO(CuentaProductoDAO cProductoDAO) {
		this.cProductoDAO = cProductoDAO;
	}

	/**
	 * Gets the {@link ReporteDAO}
	 * 
	 * @return
	 */
	public ReporteDAO getReporteDAO() {
		return reporteDAO;
	}

	/**
	 * Sets the {@link ReporteDAO}
	 * 
	 * @param reporteDAO
	 */
	public void setReporteDAO(ReporteDAO reporteDAO) {
		this.reporteDAO = reporteDAO;
	}

}
