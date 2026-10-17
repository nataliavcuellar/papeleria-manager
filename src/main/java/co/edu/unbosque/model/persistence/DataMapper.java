package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import java.util.List;



import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.ClienteDTO;
import co.edu.unbosque.model.CuentaPorCobrar;
import co.edu.unbosque.model.CuentaPorCobrarDTO;
import co.edu.unbosque.model.CuentaPorPagar;
import co.edu.unbosque.model.CuentaPorPagarDTO;
import co.edu.unbosque.model.CuentaProducto;
import co.edu.unbosque.model.CuentaProductoDTO;
import co.edu.unbosque.model.CuentaProductoId;
import co.edu.unbosque.model.DetalleVenta;
import co.edu.unbosque.model.DetalleVentaDTO;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.ProductoDTO;
import co.edu.unbosque.model.Proveedor;
import co.edu.unbosque.model.ProveedorDTO;
import co.edu.unbosque.model.Reporte;
import co.edu.unbosque.model.ReporteDTO;
import co.edu.unbosque.model.Usuario;
import co.edu.unbosque.model.UsuarioDTO;
import co.edu.unbosque.model.Venta;
import co.edu.unbosque.model.VentaDTO;


/**
 * Utility class for mapping between Entity and DTO objects.
 * <p>
 * This class contains static methods to convert entities to DTOs and vice
 * versa, for all major entities in the system like Usuario, Cliente, Producto,
 * Venta, CuentaPorCobrar, CuentaPorPagar, CuentaProducto, Proveedor, and
 * DetalleVenta.
 * </p>
 * 
 * @author InnovaCore
 */
public class DataMapper {

	// ---------------------- USUARIO ----------------------

	/** Converts a Usuario entity to UsuarioDTO. */
	public static UsuarioDTO usuarioAusuarioDTO(Usuario entity) {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setId_usuario(entity.getIdUsuario());
		dto.setUsuario(entity.getUsuario());
		dto.setContrasena(entity.getContrasena());
		dto.setEsAdmin(entity.isEsAdmin());
		return dto;
	}

	/** Converts a UsuarioDTO to a Usuario entity. */
	public static Usuario usuarioDTOAusuario(UsuarioDTO dto) {
		Usuario entity = new Usuario();
		entity.setIdUsuario(dto.getId_usuario());
		entity.setUsuario(dto.getUsuario());
		entity.setContrasena(dto.getContrasena());
		entity.setEsAdmin(dto.isEsAdmin());
		return entity;
	}

	/** Converts a list of Usuario entities to a list of UsuarioDTOs. */
	public static ArrayList<UsuarioDTO> listaUsuarioAlistaUsuarioDTO(ArrayList<Usuario> entityList) {
		ArrayList<UsuarioDTO> dtoList = new ArrayList<>();
		entityList.forEach(u -> dtoList.add(usuarioAusuarioDTO(u)));
		return dtoList;
	}

	/** Converts a list of UsuarioDTOs to a list of Usuario entities. */
	public static ArrayList<Usuario> listaUsuarioDTOAlistaUsuario(ArrayList<UsuarioDTO> dtoList) {
		ArrayList<Usuario> entityList = new ArrayList<>();
		dtoList.forEach(u -> entityList.add(usuarioDTOAusuario(u)));
		return entityList;
	}

	// ---------------------- CLIENTE ----------------------

	/** Converts a Cliente entity to ClienteDTO. */
	public static ClienteDTO clienteAclienteDTO(Cliente entity) {
		ClienteDTO dto = new ClienteDTO();
		dto.setId_cliente(entity.getId_cliente());
		dto.setCedula(entity.getCedula());
		dto.setNombre(entity.getNombre());
		dto.setApellido(entity.getApellido());
		dto.setTelefono(entity.getTelefono());
		dto.setDireccion(entity.getDireccion());
		return dto;
	}

	/** Converts a ClienteDTO to a Cliente entity. */
	public static Cliente clienteDTOAcliente(ClienteDTO dto) {
		Cliente entity = new Cliente();
		entity.setId_cliente(dto.getId_cliente());
		entity.setCedula(dto.getCedula());
		entity.setNombre(dto.getNombre());
		entity.setApellido(dto.getApellido());
		entity.setTelefono(dto.getTelefono());
		entity.setDireccion(dto.getDireccion());
		return entity;
	}

	/** Converts a list of Cliente entities to a list of ClienteDTOs. */
	public static ArrayList<ClienteDTO> listaClienteAlistaClienteDTO(ArrayList<Cliente> entityList) {
		ArrayList<ClienteDTO> dtoList = new ArrayList<>();
		entityList.forEach(c -> dtoList.add(clienteAclienteDTO(c)));
		return dtoList;
	}

	/** Converts a list of ClienteDTOs to a list of Cliente entities. */
	public static ArrayList<Cliente> listaClienteDTOAlistaCliente(ArrayList<ClienteDTO> dtoList) {
		ArrayList<Cliente> entityList = new ArrayList<>();
		dtoList.forEach(c -> entityList.add(clienteDTOAcliente(c)));
		return entityList;
	}

	// ---------------------- PROVEEDOR ----------------------

	/** Converts a Proveedor entity to ProveedorDTO. */
	public static ProveedorDTO proveedorAproveedorDTO(Proveedor entity) {
		ProveedorDTO dto = new ProveedorDTO();
		dto.setId_proveedor(entity.getId_proveedor());
		dto.setNombre(entity.getNombre());
		dto.setApellido(entity.getApellido());
		dto.setCorreo(entity.getCorreo());
		dto.setTelefono(entity.getTelefono());
		dto.setTiempo_entrega(entity.getTiempo_entrega());
		return dto;
	}

	/** Converts a ProveedorDTO to a Proveedor entity. */
	public static Proveedor proveedorDTOAproveedor(ProveedorDTO dto) {
		Proveedor entity = new Proveedor();
		entity.setId_proveedor(dto.getId_proveedor());
		entity.setNombre(dto.getNombre());
		entity.setApellido(dto.getApellido());
		entity.setCorreo(dto.getCorreo());
		entity.setTelefono(dto.getTelefono());
		entity.setTiempo_entrega(dto.getTiempo_entrega());
		return entity;
	}

	/** Converts a list of Proveedor entities to a list of ProveedorDTOs. */
	public static ArrayList<ProveedorDTO> listaProveedorAlistaProveedorDTO(ArrayList<Proveedor> entityList) {
		ArrayList<ProveedorDTO> dtoList = new ArrayList<>();
		entityList.forEach(p -> dtoList.add(proveedorAproveedorDTO(p)));
		return dtoList;
	}

	/** Converts a list of ProveedorDTOs to a list of Proveedor entities. */
	public static ArrayList<Proveedor> listaProveedorDTOAlistaProveedor(ArrayList<ProveedorDTO> dtoList) {
		ArrayList<Proveedor> entityList = new ArrayList<>();
		dtoList.forEach(p -> entityList.add(proveedorDTOAproveedor(p)));
		return entityList;
	}

	// ---------------------- PRODUCTO ----------------------

	/** Converts a Producto entity to ProductoDTO. */
	public static ProductoDTO productoAproductoDTO(Producto entity) {
		ProductoDTO dto = new ProductoDTO();
		dto.setId_producto(entity.getId_producto());
		dto.setCodigo(entity.getCodigo());
		dto.setNombre(entity.getNombre());
		dto.setPrecio_compra(entity.getPrecio_compra());
		dto.setPrecio_venta(entity.getPrecio_venta());
		dto.setStock(entity.getStock());
		dto.setStock_minimo(entity.getStock_minimo());
		dto.setProveedor(proveedorAproveedorDTO(entity.getProveedor()));
		return dto;
	}

	/** Converts a ProductoDTO to a Producto entity. */
	public static Producto productoDTOAproducto(ProductoDTO dto) {
		Producto entity = new Producto();
		entity.setId_producto(dto.getId_producto());
		entity.setCodigo(dto.getCodigo());
		entity.setNombre(dto.getNombre());
		entity.setPrecio_compra(dto.getPrecio_compra());
		entity.setPrecio_venta(dto.getPrecio_venta());
		entity.setStock(dto.getStock());
		entity.setStock_minimo(dto.getStock_minimo());
		entity.setProveedor(proveedorDTOAproveedor(dto.getProveedor()));
		return entity;
	}

	/** Converts a list of Producto entities to a list of ProductoDTOs. */
	public static ArrayList<ProductoDTO> listaProductoAlistaProductoDTO(ArrayList<Producto> entityList) {
		ArrayList<ProductoDTO> dtoList = new ArrayList<>();
		entityList.forEach(p -> dtoList.add(productoAproductoDTO(p)));
		return dtoList;
	}

	/** Converts a list of ProductoDTOs to a list of Producto entities. */
	public static ArrayList<Producto> listaProductoDTOAlistaProducto(ArrayList<ProductoDTO> dtoList) {
		ArrayList<Producto> entityList = new ArrayList<>();
		dtoList.forEach(p -> entityList.add(productoDTOAproducto(p)));
		return entityList;
	}

	// ---------------------- VENTA ----------------------

	/** Converts a Venta entity to VentaDTO. */
	public static VentaDTO ventaAVentaDTO(Venta venta) {
		VentaDTO dto = new VentaDTO();
		dto.setCliente(clienteAclienteDTO(venta.getCliente()));
		dto.setEstado(venta.getEstado());
		dto.setFecha(venta.getFecha());
		dto.setIdVenta(venta.getIdVenta());
		dto.setRutaFactura(venta.getRutaFactura());
		dto.setTotal(venta.getTotal());
		dto.setListaDetallesVenta(listaDetalleVentaAlistaDetalleVentaDTO(venta.getListaDetallesVenta()));
		dto.setTipoVenta(venta.getTipoVenta());
		return dto;
	}

	/** Converts a VentaDTO to Venta entity. */
	public static Venta ventaDTOAVenta(VentaDTO ventaDTO) {
		Venta entity = new Venta();
		entity.setCliente(clienteDTOAcliente(ventaDTO.getCliente()));
		entity.setEstado(ventaDTO.getEstado());
		entity.setFecha(ventaDTO.getFecha());
		entity.setIdVenta(ventaDTO.getIdVenta());
		entity.setRutaFactura(ventaDTO.getRutaFactura());
		entity.setTotal(ventaDTO.getTotal());
		List<DetalleVenta> detalles = listaDetalleVentaDTOAlistaDetalleVenta(ventaDTO.getListaDetallesVenta());
		detalles.forEach(detalle -> detalle.setVenta(entity));
		entity.setListaDetallesVenta(detalles);
		entity.setTipoVenta(ventaDTO.getTipoVenta());
		return entity;
	}

	/** Converts a list of Venta entities to a list of VentaDTOs. */
	public static ArrayList<VentaDTO> listaVentaAlistaVentaDTO(ArrayList<Venta> entityList) {
		ArrayList<VentaDTO> dtoList = new ArrayList<>();
		entityList.forEach(v -> dtoList.add(ventaAVentaDTO(v)));
		return dtoList;
	}

	/** Converts a list of VentaDTOs to a list of Venta entities. */
	public static ArrayList<Venta> listaVentaDTOAlistaVenta(ArrayList<VentaDTO> dtoList) {
		ArrayList<Venta> entityList = new ArrayList<>();
		dtoList.forEach(v -> entityList.add(ventaDTOAVenta(v)));
		return entityList;
	}

	// ---------------------- DETALLE VENTA ----------------------

	/** Converts a DetalleVenta entity to DetalleVentaDTO. */
	public static DetalleVentaDTO detalleVentaAdetalleVentaDTO(DetalleVenta entity) {
		DetalleVentaDTO dto = new DetalleVentaDTO();
		dto.setCantidad(entity.getCantidad());
		dto.setIdDetalle(entity.getIdDetalle());
		dto.setProducto(productoAproductoDTO(entity.getProducto()));
		dto.setSubtotal(entity.getSubtotal());
		return dto;
	}

	/** Converts a DetalleVentaDTO to DetalleVenta entity. */
	public static DetalleVenta detalleVentaDTOAdetalleVenta(DetalleVentaDTO dto) {
		DetalleVenta entity = new DetalleVenta();
		entity.setCantidad(dto.getCantidad());
		entity.setIdDetalle(dto.getIdDetalle());
		entity.setProducto(productoDTOAproducto(dto.getProducto()));
		entity.setSubtotal(dto.getSubtotal());
		return entity;
	}

	/** Converts a list of DetalleVenta entities to a list of DetalleVentaDTOs. */
	public static List<DetalleVentaDTO> listaDetalleVentaAlistaDetalleVentaDTO(List<DetalleVenta> list) {
		ArrayList<DetalleVentaDTO> dtoList = new ArrayList<>();
		list.forEach(d -> dtoList.add(detalleVentaAdetalleVentaDTO(d)));
		return dtoList;
	}

	/** Converts a list of DetalleVentaDTOs to a list of DetalleVenta entities. */
	public static List<DetalleVenta> listaDetalleVentaDTOAlistaDetalleVenta(List<DetalleVentaDTO> list) {
		ArrayList<DetalleVenta> entityList = new ArrayList<>();
		list.forEach(d -> entityList.add(detalleVentaDTOAdetalleVenta(d)));
		return entityList;
	}

	// ---------------------- CUENTA POR COBRAR ----------------------

	/** Converts a CuentaPorCobrar entity to DTO. */
	public static CuentaPorCobrarDTO cuentaPorCobrarACuentaPorCobrarDTO(CuentaPorCobrar entity) {
		CuentaPorCobrarDTO dto = new CuentaPorCobrarDTO();
		dto.setCliente(clienteAclienteDTO(entity.getCliente()));
		dto.setEstado(entity.getEstado());
		dto.setFechaVencimiento(entity.getFechaVencimiento());
		dto.setFechaVenta(entity.getFechaVenta());
		dto.setIdCuenta(entity.getIdCuenta());
		dto.setMonto(entity.getMonto());
		return dto;
	}

	/** Converts a CuentaPorCobrarDTO to entity. */
	public static CuentaPorCobrar cuentaPorCobrarDTOACuentaPorCobrar(CuentaPorCobrarDTO dto) {
		CuentaPorCobrar entity = new CuentaPorCobrar();
		entity.setCliente(clienteDTOAcliente(dto.getCliente()));
		entity.setEstado(dto.getEstado());
		entity.setFechaVencimiento(dto.getFechaVencimiento());
		entity.setFechaVenta(dto.getFechaVenta());
		entity.setIdCuenta(dto.getIdCuenta());
		entity.setMonto(dto.getMonto());
		return entity;
	}

	/** Converts a list of CuentaPorCobrar entities to DTOs. */
	public static List<CuentaPorCobrarDTO> listaCuentaPorCobrarAlistaCuentaPorCobrarDTO(
			List<CuentaPorCobrar> listaEntity) {
		List<CuentaPorCobrarDTO> listaDTO = new ArrayList<>();
		listaEntity.forEach(c -> listaDTO.add(cuentaPorCobrarACuentaPorCobrarDTO(c)));
		return listaDTO;
	}

	/** Converts a list of CuentaPorCobrarDTOs to entities. */
	public static List<CuentaPorCobrar> listaCuentaPorCobrarDTOAlistaCuentaPorCobrar(
			List<CuentaPorCobrarDTO> listaDTO) {
		List<CuentaPorCobrar> listaEntity = new ArrayList<>();
		listaDTO.forEach(c -> listaEntity.add(cuentaPorCobrarDTOACuentaPorCobrar(c)));
		return listaEntity;
	}

	// ---------------------- CUENTA PRODUCTO ----------------------

	/** Converts a CuentaProducto entity to DTO. */
	public static CuentaProductoDTO cuentaProductoAcuentaProductoDTO(CuentaProducto entity) {
		CuentaProductoDTO dto = new CuentaProductoDTO();
		dto.setIdCuentaProducto(entity.getIdCuentaProducto());
		dto.setIdCuenta(entity.getIdCuentaProducto().getIdCuenta());
		dto.setProducto(productoAproductoDTO(entity.getProducto()));
		dto.setMonto(entity.getMonto());
		return dto;
	}

	/** Converts a CuentaProductoDTO to entity. */
	public static CuentaProducto cuentaProductoDTOAcuentaProducto(CuentaProductoDTO dto) {
		CuentaProducto entity = new CuentaProducto();
		entity.setProducto(productoDTOAproducto(dto.getProducto()));
		entity.setMonto(dto.getMonto());
		entity.setIdCuentaProducto(new CuentaProductoId(dto.getProducto().getId_producto(), dto.getIdCuenta()));
		return entity;
	}

	/** Converts a list of CuentaProducto entities to DTOs. */
	public static List<CuentaProductoDTO> ListaCuentaProductoAlistaCuentaProductoDTO(List<CuentaProducto> entityList) {
		List<CuentaProductoDTO> dtoList = new ArrayList<>();
		entityList.forEach(c -> dtoList.add(cuentaProductoAcuentaProductoDTO(c)));
		return dtoList;
	}

	/** Converts a list of CuentaProductoDTOs to entities. */
	public static List<CuentaProducto> ListaCuentaProductoDTOAlistaCuentaProducto(List<CuentaProductoDTO> dtoList) {
		List<CuentaProducto> entityList = new ArrayList<>();
		dtoList.forEach(c -> entityList.add(cuentaProductoDTOAcuentaProducto(c)));
		return entityList;
	}

	// ---------------------- CUENTA POR PAGAR ----------------------

	/** Converts a CuentaPorPagar entity to DTO. */
	public static CuentaPorPagarDTO cuentaPorPagarACuentaPorPagarDTO(CuentaPorPagar entity) {
		CuentaPorPagarDTO dto = new CuentaPorPagarDTO();
		dto.setEstado(entity.getEstado());
		dto.setFechaVencimiento(entity.getFechaVencimiento());
		dto.setFechaCompra(entity.getFechaCompra());
		dto.setIdCuenta(entity.getIdCuenta());
		dto.setMonto(entity.getMonto());
		dto.setListaCuentaProducto(ListaCuentaProductoAlistaCuentaProductoDTO(entity.getListaCuentaProducto()));
		return dto;
	}

	/** Converts a CuentaPorPagarDTO to entity. */
	public static CuentaPorPagar cuentaPorPagarDTOACuentaPorPagar(CuentaPorPagarDTO dto) {
		CuentaPorPagar entity = new CuentaPorPagar();
		entity.setEstado(dto.getEstado());
		entity.setFechaVencimiento(dto.getFechaVencimiento());
		entity.setFechaCompra(dto.getFechaCompra());
		entity.setIdCuenta(dto.getIdCuenta());
		entity.setMonto(dto.getMonto());
		entity.setListaCuentaProducto(ListaCuentaProductoDTOAlistaCuentaProducto(dto.getListaCuentaProducto()));
		return entity;
	}

	/** Converts a list of CuentaPorPagar entities to DTOs. */
	public static List<CuentaPorPagarDTO> listaCuentaPorPagarAlistaCuentaPorPagarDTO(List<CuentaPorPagar> listaEntity) {
		List<CuentaPorPagarDTO> listaDTO = new ArrayList<>();
		listaEntity.forEach(c -> listaDTO.add(cuentaPorPagarACuentaPorPagarDTO(c)));
		return listaDTO;
	}

	/**
	 * Converts a list of CuentaPorCobrarDTOs to CuentaPorCobrar entities (possible
	 * mistake, kept from original).
	 */
	public static List<CuentaPorCobrar> listaCuentaPorPagarDTOAlistaCuentaPorPagar(List<CuentaPorCobrarDTO> listaDTO) {
		List<CuentaPorCobrar> listaEntity = new ArrayList<>();
		listaDTO.forEach(c -> listaEntity.add(cuentaPorCobrarDTOACuentaPorCobrar(c)));
		return listaEntity;
	}

	/**
	 * Converts reporte entity to DTO
	 * 
	 * @param entity
	 * @return dto
	 */
	public static ReporteDTO reporteToReporteDTO(Reporte entity) {
		ReporteDTO dto = new ReporteDTO();
		dto.setFechaFin(entity.getFechaFin());
		dto.setFechaGeneracion(entity.getFechaGeneracion());
		dto.setFechaInicio(entity.getFechaInicio());
		dto.setIdReporte(entity.getIdReporte());
		dto.setRutaPdf(entity.getRutaPdf());
		dto.setTipo(entity.getTipo());
		return dto;
	}

	/**
	 * Converts reporte DTO into entity.
	 * 
	 * @param dto
	 * @return entity
	 */
	public static Reporte reporteDTOToReporte(ReporteDTO dto) {
		Reporte entity = new Reporte();
		entity.setFechaFin(dto.getFechaFin());
		entity.setFechaGeneracion(dto.getFechaGeneracion());
		entity.setFechaInicio(dto.getFechaInicio());
		entity.setIdReporte(dto.getIdReporte());
		entity.setRutaPdf(dto.getRutaPdf());
		entity.setTipo(dto.getTipo());
		return entity;
	}

	/**
	 * Converts a list of Reporte into a list of dto.
	 * 
	 * @param listaEntity
	 * @return listaDTO
	 */
	public static List<ReporteDTO> listaReporteAlistaReporteDTO(List<Reporte> listaEntity) {
		List<ReporteDTO> dtoList = new ArrayList<>();
		listaEntity.forEach(e -> {
			dtoList.add(reporteToReporteDTO(e));
		});
		return dtoList;
	}

	/**
	 * Converts a list of ReporteDTO into a list of entities.
	 * 
	 * @param dtoList
	 * @return entityList
	 */
	public static List<Reporte> listaReporteDTOAlistaReporte(List<ReporteDTO> dtoList) {
		List<Reporte> entityList = new ArrayList<>();
		dtoList.forEach(e -> {
			entityList.add(reporteDTOToReporte(e));
		});
		return entityList;
	}
}
