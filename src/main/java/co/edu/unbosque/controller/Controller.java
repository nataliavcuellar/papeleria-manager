package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.ClienteDTO;
import co.edu.unbosque.model.CuentaPorCobrarDTO;
import co.edu.unbosque.model.CuentaPorPagarDTO;
import co.edu.unbosque.model.CuentaProductoDTO;
import co.edu.unbosque.model.CuentaProductoId;
import co.edu.unbosque.model.DetalleVentaDTO;
import co.edu.unbosque.model.ModelFacade;
import co.edu.unbosque.model.ProductoDTO;
import co.edu.unbosque.model.ProveedorDTO;
import co.edu.unbosque.model.ReporteDTO;
import co.edu.unbosque.model.UsuarioDTO;
import co.edu.unbosque.model.VentaDTO;
import co.edu.unbosque.model.persistence.FileHandler;
import co.edu.unbosque.util.enums.EstadoCuenta;
import co.edu.unbosque.util.enums.EstadoVenta;
import co.edu.unbosque.util.enums.TipoReporte;
import co.edu.unbosque.util.enums.TipoVenta;
import co.edu.unbosque.view.ViewFacade;

public class Controller implements ActionListener {

	private ViewFacade vf;
	private ModelFacade mf;
	private FileHandler fh;

	public Controller() {
		vf = new ViewFacade();
		mf = new ModelFacade();
		fh = new FileHandler();
	}

	public void run() {
		vf.getVp().setVisible(true);
		asignarLectores();
}

	private void asignarLectores() {
		vf.getVp().getJpnUsuario().getBtnIniciar().addActionListener(this);
		vf.getVp().getJpnUsuario().getBtnIniciar().setActionCommand("Iniciar Sesion");

		vf.getVp().getJpnSuperior().getBtnCerrarSesion().addActionListener(this);
		vf.getVp().getJpnSuperior().getBtnCerrarSesion().setActionCommand("Cerrar Sesion");

		vf.getVp().getJpnMenuPrincipal().getBtnVentas().addActionListener(this);
		vf.getVp().getJpnMenuPrincipal().getBtnVentas().setActionCommand("Ventas");

		vf.getVp().getJpnMenuPrincipal().getBtnInventario().addActionListener(this);
		vf.getVp().getJpnMenuPrincipal().getBtnInventario().setActionCommand("Inventario");

		vf.getVp().getJpnMenuPrincipal().getBtnClientes().addActionListener(this);
		vf.getVp().getJpnMenuPrincipal().getBtnClientes().setActionCommand("Clientes");

		vf.getVp().getJpnMenuPrincipal().getBtnProveedores().addActionListener(this);
		vf.getVp().getJpnMenuPrincipal().getBtnProveedores().setActionCommand("Proveedores");

		vf.getVp().getJpnMenuPrincipal().getBtnCuentas().addActionListener(this);
		vf.getVp().getJpnMenuPrincipal().getBtnCuentas().setActionCommand("Cuentas");

		vf.getVp().getJpnMenuPrincipal().getBtnReportes().addActionListener(this);
		vf.getVp().getJpnMenuPrincipal().getBtnReportes().setActionCommand("Reportes");

		vf.getVp().getJpnVentas().getBtnBuscar().addActionListener(this);
		vf.getVp().getJpnVentas().getBtnBuscar().setActionCommand("Buscar Cliente");

		vf.getVp().getJpnVentas().getBtnAgregar().addActionListener(this);
		vf.getVp().getJpnVentas().getBtnAgregar().setActionCommand("Agregar a Factura");

		vf.getVp().getJpnVentas().getJcbProductos().addActionListener(this);
		vf.getVp().getJpnVentas().getJcbProductos().setActionCommand("Seleccionar Producto");

		vf.getVp().getJpnVentas().getBtnGenerar().addActionListener(this);
		vf.getVp().getJpnVentas().getBtnGenerar().setActionCommand("Generar Factura");

		vf.getVp().getJpnVentas().getBtnEliminar().addActionListener(this);
		vf.getVp().getJpnVentas().getBtnEliminar().setActionCommand("Eliminar Producto");

		vf.getVp().getJpnVentas().getBtnImprimir().addActionListener(this);
		vf.getVp().getJpnVentas().getBtnImprimir().setActionCommand("Imprimir");

		vf.getVp().getJpnInventario().getBtnAgregar().addActionListener(this);
		vf.getVp().getJpnInventario().getBtnAgregar().setActionCommand("Agregar Producto");

		vf.getVp().getJpnInventario().getBtnActualizar().addActionListener(this);
		vf.getVp().getJpnInventario().getBtnActualizar().setActionCommand("Actualizar Producto");

		vf.getVp().getJpnInventario().getBtnAlertas().addActionListener(this);
		vf.getVp().getJpnInventario().getBtnAlertas().setActionCommand("Ver Alertas");

		vf.getVp().getJpnClientes().getBtnAgregar().addActionListener(this);
		vf.getVp().getJpnClientes().getBtnAgregar().setActionCommand("Nuevo Cliente");

		vf.getVp().getJpnClientes().getBtnActualizar().addActionListener(this);
		vf.getVp().getJpnClientes().getBtnActualizar().setActionCommand("Editar Cliente");

		vf.getVp().getJpnClientes().getBtnEliminar().addActionListener(this);
		vf.getVp().getJpnClientes().getBtnEliminar().setActionCommand("Eliminar Cliente");

		vf.getVp().getJpnProveedores().getBtnAgregar().addActionListener(this);
		vf.getVp().getJpnProveedores().getBtnAgregar().setActionCommand("Nuevo Proveedor");

		vf.getVp().getJpnProveedores().getBtnActualizar().addActionListener(this);
		vf.getVp().getJpnProveedores().getBtnActualizar().setActionCommand("Editar Proveedor");

		vf.getVp().getJpnProveedores().getBtnEliminar().addActionListener(this);
		vf.getVp().getJpnProveedores().getBtnEliminar().setActionCommand("Eliminar Proveedor");

		vf.getVp().getJpnCuentas().getBtnRegistrarCobro().addActionListener(this);
		vf.getVp().getJpnCuentas().getBtnRegistrarCobro().setActionCommand("Registrar Cobro");

		vf.getVp().getJpnCuentas().getBtnRegistrarPago().addActionListener(this);
		vf.getVp().getJpnCuentas().getBtnRegistrarPago().setActionCommand("Registrar Pago");

		vf.getVp().getJpnReportes().getBtnGenerar().addActionListener(this);
		vf.getVp().getJpnReportes().getBtnGenerar().setActionCommand("Generar Reporte");

		vf.getVp().getJpnReportes().getBtnImprimir().addActionListener(this);
		vf.getVp().getJpnReportes().getBtnImprimir().setActionCommand("Imprimir Reporte");

		vf.getVp().getJpnReportes().getBtnReporteInventario().addActionListener(this);
		vf.getVp().getJpnReportes().getBtnReporteInventario().setActionCommand("Reporte Inventario");

		vf.getVa().getBtnGuardar().addActionListener(this);
		vf.getVa().getBtnGuardar().setActionCommand("Guardar Auxiliar");

		vf.getVa().getBtnCancelar().addActionListener(this);
		vf.getVa().getBtnCancelar().setActionCommand("Cancelar Auxiliar");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel modeloDetalleFactura = (DefaultTableModel) vf.getVp().getJpnVentas().getTablaFactura()
				.getModel();
		DefaultTableModel modeloVentaReciente = (DefaultTableModel) vf.getVp().getJpnVentas().getTablaVentas()
				.getModel();
		DefaultTableModel modeloCuentasPagar = (DefaultTableModel) vf.getVp().getJpnCuentas().getTablaPagar()
				.getModel();
		DefaultTableModel modeloCuentasCobrar = (DefaultTableModel) vf.getVp().getJpnCuentas().getTablaCobrar()
				.getModel();
		switch (e.getActionCommand()) {

		case "Iniciar Sesion": {
			String usuario = vf.getVp().getJpnUsuario().getTxtUsuario().getText();

			char[] passChars = vf.getVp().getJpnUsuario().getTxtContrasena().getPassword();
			String contrasena = new String(passChars);

			UsuarioDTO user = mf.getuDAO().findOneByUsuario(usuario);

			if (user != null && user.getContrasena().equals(contrasena)) {
				vf.getVp().getJpnUsuario().getTxtUsuario().setText("");
				vf.getVp().getJpnUsuario().getTxtContrasena().setText("");
				if (user.isEsAdmin()) {
					vf.getVp().getJpnUsuario().setVisible(false);
					vf.getVp().getJpnMenuPrincipal().setVisible(true);
					vf.getVp().getJpnSuperior().setVisible(true);
					vf.getVp().getJpnSuperior().getLblUsuario().setText(usuario);
				} else {
					vf.getVp().getJpnUsuario().setVisible(false);
					vf.getVp().getJpnMenuPrincipal().setVisible(true);
					vf.getVp().getJpnSuperior().setVisible(true);
					vf.getVp().getJpnSuperior().getLblUsuario().setText(usuario);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
			}
			break;
		}

		case "Cerrar Sesion": {
			int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cerrar sesión?",
					"Confirmar cierre de sesión", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcion == JOptionPane.YES_OPTION) {
				vf.getVp().getJpnUsuario().setVisible(true);
				vf.getVp().getJpnMenuPrincipal().setVisible(false);
				vf.getVp().getJpnSuperior().setVisible(false);
				cerrarPaneles();
				activarBotones();
			}
			break;
		}
		case "Ventas": {
			cerrarPaneles();
			activarBotones();
			vf.getVp().getJpnVentas().getJcbProductos().removeAllItems();

			ArrayList<ProductoDTO> productos = mf.getProductoDAO().findAll();

			for (ProductoDTO p : productos) {
				vf.getVp().getJpnVentas().getJcbProductos().addItem(p.getCodigo());
			}
			vf.getVp().getJpnVentas().setVisible(true);
			vf.getVp().getJpnMenuPrincipal().getBtnVentas().setEnabled(false);
			break;
		}
		case "Inventario": {
			cerrarPaneles();
			activarBotones();
			actualizarTablaProductos();
			vf.getVp().getJpnInventario().setVisible(true);
			vf.getVp().getJpnInventario().mostrarAlerta(false);
			vf.getVp().getJpnMenuPrincipal().getBtnInventario().setEnabled(false);
			break;
		}
		case "Clientes": {
			cerrarPaneles();
			activarBotones();
			actualizarTablaClientes();
			vf.getVp().getJpnClientes().setVisible(true);
			vf.getVp().getJpnMenuPrincipal().getBtnClientes().setEnabled(false);
			break;
		}
		case "Proveedores": {
			cerrarPaneles();
			activarBotones();
			actualizarTablaProveedores();
			vf.getVp().getJpnProveedores().setVisible(true);
			vf.getVp().getJpnMenuPrincipal().getBtnProveedores().setEnabled(false);
			break;
		}
		case "Cuentas": {
			cerrarPaneles();
			activarBotones();
			modeloCuentasPagar.setRowCount(0);

			List<CuentaProductoDTO> listaCuentasProducto = mf.getcProductoDAO().findAll();
			for (CuentaProductoDTO c : listaCuentasProducto) {
				String proveedor = c.getProducto().getProveedor().getNombre() + " "
						+ c.getProducto().getProveedor().getApellido();
				System.out.println(c.getIdCuenta());
				long monto = c.getMonto();
				String fechaVencimiento = mf.getcPorPagarDAO().findOne(c.getIdCuenta()).getFechaVencimiento()
						.toString();
				String estado = mf.getcPorPagarDAO().findOne(c.getIdCuenta()).getEstado().name();
				modeloCuentasPagar.addRow(new Object[] { c.getIdCuenta(), c.getProducto().getId_producto(), proveedor,
						monto, fechaVencimiento, estado });
			}

			List<CuentaPorCobrarDTO> listaPorCobrar = mf.getcPorCobrarDAO().findAll();
			for (CuentaPorCobrarDTO c : listaPorCobrar) {
				String cliente = c.getCliente().getNombre() + " " + c.getCliente().getApellido();
				long monto = c.getMonto();
				String fechaVencimiento = c.getFechaVencimiento().toString();
				String estado = c.getEstado().name();
				modeloCuentasCobrar.addRow(new Object[] { c.getIdCuenta(), cliente, monto, fechaVencimiento, estado });
			}

			vf.getVp().getJpnCuentas().setVisible(true);
			vf.getVp().getJpnMenuPrincipal().getBtnCuentas().setEnabled(false);
			break;
		}

		case "Reportes": {
			cerrarPaneles();
			activarBotones();
			vf.getVp().getJpnReportes().setVisible(true);
			vf.getVp().getJpnMenuPrincipal().getBtnReportes().setEnabled(false);
			long total = 0;
			int productosVendidos = 0;
			for (VentaDTO v : mf.getVentaDAO().findAll()) {
				total += (long) v.getTotal();
				productosVendidos += v.getListaDetallesVenta().size();
			}
			vf.getVp().getJpnReportes().getLblTotalVentas().setText(Long.toString(total));
			vf.getVp().getJpnReportes().getLblProductosVendidosValor().setText(Integer.toString(productosVendidos));
			actualizarTablaReportes();
			break;
		}

		case "Buscar Cliente": {
			String cedulaBuscar = vf.getVp().getJpnVentas().getTxtCedula().getText();
			if (cedulaBuscar != null && !cedulaBuscar.trim().isEmpty()) {
				try {
					long cedulaNum = Long.parseLong(cedulaBuscar);
					ClienteDTO cliente = mf.getClienteDAO().findOneByCedula(cedulaNum);

					if (cliente != null) {
						vf.getVp().getJpnVentas().getLblCliente()
								.setText(cliente.getNombre() + " " + cliente.getApellido());
					} else {
						JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con esa cédula.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "La cédula debe ser numérica.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
		}

		case "Agregar a Factura": {
			try {
				int codigo = Integer.parseInt(vf.getVp().getJpnVentas().getJcbProductos().getSelectedItem().toString());
				String producto = vf.getVp().getJpnVentas().getLblNombreProducto().getText();
				String cantidadTxt = vf.getVp().getJpnVentas().getTxtCantidad().getText();
				int cantidad = Integer.parseInt(cantidadTxt);

				double precioUnitario = Double.parseDouble(
						vf.getVp().getJpnVentas().getLblPrecioValor().getText().toString().replace("$", ""));
				double subtotal = precioUnitario * cantidad;

				modeloDetalleFactura
						.addRow(new Object[] { codigo, producto, cantidad, "$" + precioUnitario, "$" + subtotal });

				double total = 0;
				for (int i = 0; i < modeloDetalleFactura.getRowCount(); i++) {
					String valor = modeloDetalleFactura.getValueAt(i, 3).toString().replace("$", "");

					total += Double.parseDouble(valor)
							* Integer.parseInt(modeloDetalleFactura.getValueAt(i, 2).toString());
				}
				vf.getVp().getJpnVentas().getLblTotalValor().setText("$" + total);

			} catch (InputMismatchException ex) {
				JOptionPane.showMessageDialog(null, "La cantidad debe ser númerica");
			}
			break;
		}
		case "Seleccionar Producto": {
			String productoSeleccionado = (String) vf.getVp().getJpnVentas().getJcbProductos().getSelectedItem();
			ProductoDTO productoAux = mf.getProductoDAO().findOneByCodigo(productoSeleccionado);

			if (productoAux == null) {
				vf.getVp().getJpnVentas().getLblNombreProducto().setText("");
				vf.getVp().getJpnVentas().getLblPrecioValor().setText("");
			} else {
				vf.getVp().getJpnVentas().getLblNombreProducto().setText(productoAux.getNombre());
				vf.getVp().getJpnVentas().getLblPrecioValor().setText("$" + productoAux.getPrecio_venta());
			}

			break;
		}

		case "Eliminar Producto": {
			int filaSeleccionada = vf.getVp().getJpnVentas().getTablaFactura().getSelectedRow();
			double nuevoTotal = 0;
			if (filaSeleccionada != -1) {
				modeloDetalleFactura.removeRow(filaSeleccionada);

				for (int i = 0; i < modeloDetalleFactura.getRowCount(); i++) {
					String valor = modeloDetalleFactura.getValueAt(i, 4).toString().replace("$", "");
					nuevoTotal += Double.parseDouble(valor);
				}
				vf.getVp().getJpnVentas().getLblTotalValor().setText("$" + nuevoTotal);
			} else {
				JOptionPane.showMessageDialog(null, "Selecciona un producto para eliminar");
			}
			break;
		}

		case "Generar Factura": {

			if (modeloDetalleFactura.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null,
						"Porfavor agrege productos a la compra antes de generar la factura.");
				break;
			}
			ClienteDTO cliente = mf.getClienteDAO()
					.findOneByCedula(Integer.parseInt(vf.getVp().getJpnVentas().getTxtCedula().getText()));

			VentaDTO venta = new VentaDTO();
			venta.setCliente(cliente);
			String path = "facturas/" + LocalDate.now().toString() + "-" + LocalDateTime.now().getHour() + "-"
					+ LocalDateTime.now().getMinute() + "-" + LocalDateTime.now().getSecond();
			venta.setRutaFactura(path);
			double total = Double.parseDouble(vf.getVp().getJpnVentas().getLblTotalValor().getText().replace("$", ""));
			venta.setTotal((int) total);
			venta.setFecha(LocalDate.now());
			venta.setTipoVenta(
					vf.getVp().getJpnVentas().getJrbPagada().isSelected() ? TipoVenta.PAGADA : TipoVenta.FIADA);
			venta.setEstado(venta.getTipoVenta() == TipoVenta.FIADA ? EstadoVenta.CONTINGENCIA : EstadoVenta.PENDIENTE);
			if (venta.getTipoVenta() == TipoVenta.FIADA) {
				CuentaPorCobrarDTO c = new CuentaPorCobrarDTO();
				c.setCliente(cliente);
				c.setEstado(EstadoCuenta.PENDIENTE);
				c.setFechaVenta(LocalDate.now());
				c.setFechaVencimiento(LocalDate.now().plusDays(7));
				c.setMonto((long) total);
				mf.getcPorCobrarDAO().crear(c);
			}
			ArrayList<DetalleVentaDTO> listaDetalles = new ArrayList<>();

			for (int i = 0; i < modeloDetalleFactura.getRowCount(); i++) {
				DetalleVentaDTO detalleDTO = new DetalleVentaDTO();
				int idProducto = Integer.parseInt(modeloDetalleFactura.getValueAt(i, 0).toString());
				ProductoDTO producto = mf.getProductoDAO().findOne(idProducto);
				int cantidad = Integer.parseInt(modeloDetalleFactura.getValueAt(i, 2).toString());
				if (producto.getStock() - cantidad < producto.getStock_minimo()) {
					JOptionPane.showMessageDialog(null, "Porfavor haz un pedido de " + producto.getNombre()
							+ " el stock mínimo ha sido alcanzado.");
				} else if (producto.getStock() - cantidad < 0) {
					JOptionPane.showMessageDialog(null,
							"Por el momento no hay stock suficiente de " + producto.getNombre() + ".");
					continue;
				} else if (producto.getStock() - cantidad == 0) {
					JOptionPane.showMessageDialog(null,
							"Porfavor haz un pedido de " + producto.getNombre() + " se ha agotado.");
				}
				producto.setStock(producto.getStock() - cantidad);
				double subtotal = Double.parseDouble(modeloDetalleFactura.getValueAt(i, 4).toString().replace("$", ""));
				detalleDTO.setCantidad(cantidad);
				detalleDTO.setProducto(producto);
				detalleDTO.setSubtotal((long) subtotal);
				listaDetalles.add(detalleDTO);
				mf.getProductoDAO().actualizar(producto.getId_producto(), producto);
			}
			venta.setListaDetallesVenta(listaDetalles);
			mf.getVentaDAO().crear(venta);
			modeloVentaReciente.addRow(new Object[] { cliente.getNombre() + " " + cliente.getApellido(),
					"$" + venta.getTotal(), venta.getEstado().name(), venta.getTipoVenta().name() });
			JOptionPane.showMessageDialog(null, "Generar Factura");

			break;
		}
		case "Imprimir": {
			if (modeloDetalleFactura.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null,
						"Porfavor agrege productos a la compra antes de imprimir la factura.");
				break;
			}
			ClienteDTO cliente = mf.getClienteDAO()
					.findOneByCedula(Integer.parseInt(vf.getVp().getJpnVentas().getTxtCedula().getText()));

			VentaDTO venta = new VentaDTO();
			venta.setCliente(cliente);
			String path = "facturas/" + LocalDate.now().toString() + "-" + LocalDateTime.now().getHour() + "-"
					+ LocalDateTime.now().getMinute() + "-" + LocalDateTime.now().getSecond();
			venta.setRutaFactura(path);
			double total = Double.parseDouble(vf.getVp().getJpnVentas().getLblTotalValor().getText().replace("$", ""));
			venta.setTotal((int) total);
			venta.setFecha(LocalDate.now());
			venta.setTipoVenta(
					vf.getVp().getJpnVentas().getJrbPagada().isSelected() ? TipoVenta.PAGADA : TipoVenta.FIADA);
			venta.setEstado(venta.getTipoVenta() == TipoVenta.FIADA ? EstadoVenta.CONTINGENCIA : EstadoVenta.PENDIENTE);

			ArrayList<DetalleVentaDTO> listaDetalles = new ArrayList<>();
			for (int i = 0; i < modeloDetalleFactura.getRowCount(); i++) {
				DetalleVentaDTO detalleDTO = new DetalleVentaDTO();
				int idProducto = Integer.parseInt(modeloDetalleFactura.getValueAt(i, 0).toString());
				int cantidad = Integer.parseInt(modeloDetalleFactura.getValueAt(i, 2).toString());
				double subtotal = Double.parseDouble(modeloDetalleFactura.getValueAt(i, 4).toString().replace("$", ""));
				detalleDTO.setCantidad(cantidad);
				detalleDTO.setProducto(mf.getProductoDAO().findOne(idProducto));
				detalleDTO.setSubtotal((long) subtotal);
				listaDetalles.add(detalleDTO);
			}
			venta.setListaDetallesVenta(listaDetalles);
			fh.generarFacturaPDF(path, venta);
			JOptionPane.showMessageDialog(null, "Imprimir");

			break;
		}
		case "Agregar Producto": {
			vf.getVa().panelProducto();

			vf.getVa().getComboProveedor().removeAllItems();

			ArrayList<ProveedorDTO> proveedores = mf.getProveedorDAO().findAll();

			for (ProveedorDTO p : proveedores) {
				vf.getVa().getComboProveedor().addItem(p.getNombre() + " " + p.getApellido());
			}

			vf.getVa().getComboProveedor().addItem("Externo");

			vf.getVa().setVisible(true);
			break;
		}

		case "Actualizar Producto": {
			DefaultTableModel productoTablaEditar = (DefaultTableModel) vf.getVp().getJpnInventario()
					.getTablaInventario().getModel();
			int productoSeleccionadoEditar = vf.getVp().getJpnInventario().getTablaInventario().getSelectedRow();

			if (productoSeleccionadoEditar != -1) {
				String id = productoTablaEditar.getValueAt(productoSeleccionadoEditar, 0).toString();
				String codigo = productoTablaEditar.getValueAt(productoSeleccionadoEditar, 1).toString();
				String nombre = productoTablaEditar.getValueAt(productoSeleccionadoEditar, 2).toString();
				String precioCompra = productoTablaEditar.getValueAt(productoSeleccionadoEditar, 3).toString();
				String precioVenta = productoTablaEditar.getValueAt(productoSeleccionadoEditar, 4).toString();
				String proveedor = productoTablaEditar.getValueAt(productoSeleccionadoEditar, 5).toString();
				String stock = productoTablaEditar.getValueAt(productoSeleccionadoEditar, 6).toString();
				String stockMinimo = productoTablaEditar.getValueAt(productoSeleccionadoEditar, 7).toString();

				vf.getVa().panelProducto();
				vf.getVa().cargarDatosProducto(Integer.parseInt(id), codigo, nombre, precioCompra, precioVenta,
						proveedor, stock, stockMinimo);
				vf.getVa().getComboProveedor().removeAllItems();

				ArrayList<ProveedorDTO> proveedoresActualizar = mf.getProveedorDAO().findAll();

				for (ProveedorDTO p : proveedoresActualizar) {
					vf.getVa().getComboProveedor().addItem(p.getNombre() + " " + p.getApellido());
				}
				vf.getVa().getComboProveedor().setSelectedItem((String) proveedor);
				vf.getVa().getComboProveedor().addItem("Externo");
				vf.getVa().modoActualizar();
				vf.getVa().setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "Selecciona un producto para actualizar");
			}
			break;
		}

		case "Ver Alertas": {
			JOptionPane.showMessageDialog(null, "Ver Alertas");
			break;
		}
		case "Nuevo Cliente": {
			vf.getVa().panelCliente();
			vf.getVa().setVisible(true);
			break;
		}

		case "Editar Cliente": {
			DefaultTableModel clienteTablaEditar = (DefaultTableModel) vf.getVp().getJpnClientes().getTablaClientes()
					.getModel();
			int clienteSeleccionadoEditar = vf.getVp().getJpnClientes().getTablaClientes().getSelectedRow();

			if (clienteSeleccionadoEditar != -1) {
				String id = clienteTablaEditar.getValueAt(clienteSeleccionadoEditar, 0).toString();
				String cedula = clienteTablaEditar.getValueAt(clienteSeleccionadoEditar, 1).toString();
				String nombre = clienteTablaEditar.getValueAt(clienteSeleccionadoEditar, 2).toString();
				String apellido = clienteTablaEditar.getValueAt(clienteSeleccionadoEditar, 3).toString();
				String telefono = clienteTablaEditar.getValueAt(clienteSeleccionadoEditar, 4).toString();
				String direccion = clienteTablaEditar.getValueAt(clienteSeleccionadoEditar, 5).toString();

				vf.getVa().panelCliente();
				vf.getVa().cargarDatosCliente(Integer.parseInt(id), cedula, nombre, apellido, telefono, direccion);
				vf.getVa().modoActualizar();
				vf.getVa().setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "Selecciona un cliente para actualizar");
			}
			break;
		}

		case "Eliminar Cliente": {
			int clienteSeleccionadoEliminar = vf.getVp().getJpnClientes().getTablaClientes().getSelectedRow();

			if (clienteSeleccionadoEliminar != -1) {
				int id = Integer.parseInt(vf.getVp().getJpnClientes().getTablaClientes()
						.getValueAt(clienteSeleccionadoEliminar, 0).toString());
				String nombre = vf.getVp().getJpnClientes().getTablaClientes()
						.getValueAt(clienteSeleccionadoEliminar, 2).toString();

				int opcionCliente = JOptionPane.showConfirmDialog(null,
						"¿Estás seguro de eliminar al cliente \"" + nombre + "\"?\nEsta acción no se puede deshacer",
						"Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (opcionCliente == JOptionPane.YES_OPTION) {
					mf.getClienteDAO().eliminar(id);
					actualizarTablaClientes();
					JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito", "Eliminado",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Selecciona un cliente para eliminnar");
			}
			break;
		}

		case "Nuevo Proveedor": {
			vf.getVa().panelProveedor();
			vf.getVa().setVisible(true);
			break;
		}

		case "Editar Proveedor": {
			DefaultTableModel proveedorTablaEditar = (DefaultTableModel) vf.getVp().getJpnProveedores()
					.getTablaProveedores().getModel();
			int proveedorSeleccionadoEditar = vf.getVp().getJpnProveedores().getTablaProveedores().getSelectedRow();

			if (proveedorSeleccionadoEditar != -1) {
				String id = proveedorTablaEditar.getValueAt(proveedorSeleccionadoEditar, 0).toString();
				String nombre = proveedorTablaEditar.getValueAt(proveedorSeleccionadoEditar, 1).toString();
				String apellido = proveedorTablaEditar.getValueAt(proveedorSeleccionadoEditar, 2).toString();
				String correo = proveedorTablaEditar.getValueAt(proveedorSeleccionadoEditar, 3).toString();
				String telefono = proveedorTablaEditar.getValueAt(proveedorSeleccionadoEditar, 4).toString();
				String tiempo_entrega = proveedorTablaEditar.getValueAt(proveedorSeleccionadoEditar, 5).toString();

				vf.getVa().panelProveedor();
				vf.getVa().cargarDatosProveedor(Integer.parseInt(id), nombre, apellido, correo, telefono,
						tiempo_entrega);
				vf.getVa().modoActualizar();
				vf.getVa().setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "Selecciona un proveedor para actualizar");
			}
			break;
		}

		case "Eliminar Proveedor": {
			int proveedorSeleccionadoEliminar = vf.getVp().getJpnProveedores().getTablaProveedores().getSelectedRow();

			if (proveedorSeleccionadoEliminar != -1) {
				int id = Integer.parseInt(vf.getVp().getJpnProveedores().getTablaProveedores()
						.getValueAt(proveedorSeleccionadoEliminar, 0).toString());
				String nombre = vf.getVp().getJpnProveedores().getTablaProveedores()
						.getValueAt(proveedorSeleccionadoEliminar, 1).toString();

				int opcionProveedor = JOptionPane.showConfirmDialog(null,
						"¿Estás seguro de eliminar al proveedor \"" + nombre + "\"?\nEsta acción no se puede deshacer",
						"Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (opcionProveedor == JOptionPane.YES_OPTION) {
					mf.getProveedorDAO().eliminar(id);
					actualizarTablaProveedores();
					JOptionPane.showMessageDialog(null, "Proveedor eliminado con éxito", "Eliminado",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Selecciona un proveedor para eliminar");
			}
			break;
		}

		case "Registrar Cobro": {
			vf.getVa().panelDeuda();
			int selected = vf.getVp().getJpnCuentas().getTablaCobrar().getSelectedRow();
			if (selected >= 0) {
				int id = Integer
						.parseInt(vf.getVp().getJpnCuentas().getTablaCobrar().getValueAt(selected, 0).toString());
				String usuario = vf.getVp().getJpnCuentas().getTablaCobrar().getValueAt(selected, 1).toString();
				if (mf.getcPorCobrarDAO().eliminar(id)) {
					JOptionPane.showMessageDialog(null, "Registrar Cobro: La deuda de " + usuario + " fue saldada.");
					modeloCuentasCobrar.removeRow(selected);
				} else {
					JOptionPane.showMessageDialog(null, "Hubo un error y no fue posible saldar su deuda.");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Porfavor seleccion la cuenta por cobrar que desea saldar.");
			}

			activarBotones();
			break;
		}

		case "Registrar Pago": {
			vf.getVa().panelDeuda();
			List<ProductoDTO> listaProductos = mf.getProductoDAO().findAll();
			for (ProductoDTO p : listaProductos) {
				vf.getVa().getComboProducto().addItem(p.getId_producto() + ": " + p.getNombre());
			}
			vf.getVa().setVisible(true);
			activarBotones();
			break;
		}
		case "Generar Reporte": {

			ReporteDTO reporte = new ReporteDTO();
			String tipo = vf.getVp().getJpnReportes().getComboTipoReporte().getSelectedItem().toString();
			if (vf.getVp().getJpnReportes().getDateInicio().getDate() == null) {
				JOptionPane.showMessageDialog(null, "Porfavor seleccionar una fecha de inicio en el calendario.");
				break;
			} else if (vf.getVp().getJpnReportes().getDateFin().getDate() == null) {
				JOptionPane.showMessageDialog(null, "Porfavor seleccionar una fecha de finalización en el calendario.");
				break;
			}
			LocalDate inicio = vf.getVp().getJpnReportes().getDateInicio().getDate().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate fin = vf.getVp().getJpnReportes().getDateFin().getDate().toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate();

			reporte.setFechaGeneracion(LocalDate.now());
			if (fin.isBefore(inicio)) {
				JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser antes de la fecha de fin.");
				break;
			} else if (inicio.isAfter(LocalDate.now())) {
				JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser antes de la fecha de hoy.");
				break;
			} else if (fin.isAfter(LocalDate.now())) {
				JOptionPane.showMessageDialog(null, "La fecha de fin puede ser como máximo la fecha actual.");
				break;
			}
			switch (tipo) {
			case "Inventario": {
				reporte.setTipo(TipoReporte.Inventario);
				reporte.setRutaPdf(fh.getDirReportes() + File.separator + "INV(" + inicio + "-" + fin + ").pdf");
				List<ProductoDTO> listaProductos = mf.getProductoDAO().findAll();
				if (fh.generarReporteInventarioPDF(reporte.getRutaPdf(), listaProductos)) {
					JOptionPane.showMessageDialog(null, "Reporte de inventario creado exitosamente.");
					mf.getReporteDAO().crear(reporte);
				} else {
					JOptionPane.showMessageDialog(null,
							"No fue posible crear el reporte de inventario, revise si hay productos disponibles.");
				}

				break;
			}
			case "Ventas": {
				reporte.setTipo(TipoReporte.Ventas);
				reporte.setRutaPdf(fh.getDirReportes() + File.separator + "VEN(" + inicio + "-" + fin + ").pdf");
				List<VentaDTO> listaVentas = mf.getVentaDAO().findAll();
				listaVentas.removeIf(v -> v.getFecha().isBefore(inicio) || v.getFecha().isAfter(fin));

				if (fh.generarReporteVentasPDF(reporte.getRutaPdf(), listaVentas)) {
					JOptionPane.showMessageDialog(null, "Reporte de ventas creado exitosamente.");
					mf.getReporteDAO().crear(reporte);
				} else {
					JOptionPane.showMessageDialog(null,
							"No fue posible crear el reporte de ventas, revise en el apartado de ventas si ha realizado alguna venta en ese periodo.");
				}
				break;
			}
			case "General": {
				reporte.setTipo(TipoReporte.General);
				reporte.setRutaPdf(fh.getDirReportes() + File.separator + "GEN(" + inicio + "-" + fin + ").pdf");
				System.out.println(mf.getVentaDAO().findAll().size());
				System.out.println(mf.getProductoDAO().findAll().size());

				if (fh.generarReporteGeneralPDF(reporte.getRutaPdf(), mf.getVentaDAO().findAll(),
						mf.getProductoDAO().findAll())) {
					JOptionPane.showMessageDialog(null, "Reporte de general creado exitosamente.");
					mf.getReporteDAO().crear(reporte);
				} else {
					JOptionPane.showMessageDialog(null,
							"No fue posible crear el reporte general, revise el inventario y las ventas tengan elementos.");
				}
				break;
			}
			}

			activarBotones();
			actualizarTablaClientes();
			break;
		}
		case "Imprimir Reporte": {
			JOptionPane.showMessageDialog(null, "Imprimir Reporte");
			break;
		}

		case "Reporte Inventario": {
			JOptionPane.showMessageDialog(null, "Reporte Inventario");
			activarBotones();
			break;
		}

		case "Guardar Auxiliar": {
			String ventana = vf.getVa().getLblTitulo().getText();

			if (ventana.contains("Registro de Cliente")) {
				String cedula = vf.getVa().getTxtCedula().getText().trim();
				String nombre = vf.getVa().getTxtNombre().getText().trim();
				String apellido = vf.getVa().getTxtApellido().getText().trim();
				String telefono = vf.getVa().getTxtTelefono().getText().trim();
				String direccion = vf.getVa().getTxtDireccion().getText().trim();

				try {
					if (cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()
							|| direccion.isEmpty()) {
						throw new IllegalArgumentException("Ningún campo puede estar vacío.");
					}

					long cedulaNum;
					long telefonoNum;

					try {
						cedulaNum = Long.parseLong(cedula);
						telefonoNum = Long.parseLong(telefono);
					} catch (NumberFormatException ex) {
						throw new IllegalArgumentException("Cédula y teléfono deben ser numéricos.");
					}

					mf.getClienteDAO().crear(new ClienteDTO(0, cedulaNum, nombre, apellido, telefonoNum, direccion));
					vf.getVa().setVisible(false);

					actualizarTablaClientes();
					JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");

				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación",
							JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado al registrar el cliente.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} else if (ventana.contains("Registro de Proveedor")) {
				String nombre = vf.getVa().getTxtNombre().getText().trim();
				String apellido = vf.getVa().getTxtApellido().getText().trim();
				String correo = vf.getVa().getTxtCorreo().getText().trim();
				String telefono = vf.getVa().getTxtTelefono().getText().trim();
				String tiempoEntrega = vf.getVa().getTxtTiempoEntrega().getText().trim();

				try {
					if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty()
							|| tiempoEntrega.isEmpty()) {
						throw new IllegalArgumentException("Ningún campo puede estar vacío.");
					}

					if (!correo.contains("@") || !correo.contains(".")) {
						throw new IllegalArgumentException("El correo no tiene un formato válido.");
					}

					long telefonoNum;
					int tiempoEntregaNum;

					try {
						telefonoNum = Long.parseLong(telefono);
						tiempoEntregaNum = Integer.parseInt(tiempoEntrega);
					} catch (NumberFormatException ex) {
						throw new IllegalArgumentException("Teléfono y tiempo de entrega deben ser numéricos.");
					}

					mf.getProveedorDAO()
							.crear(new ProveedorDTO(0, nombre, apellido, correo, telefonoNum, tiempoEntregaNum));
					vf.getVa().setVisible(false);

					actualizarTablaProveedores();
					JOptionPane.showMessageDialog(null, "Proveedor registrado correctamente.");

				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación",
							JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado al registrar el proveedor.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			} else if (ventana.contains("Registro de Producto")) {
				String codigo = vf.getVa().getTxtCodigo().getText().trim();
				String nombre = vf.getVa().getTxtNombre().getText().trim();
				String precioCompra = vf.getVa().getTxtPrecioCompra().getText().trim();
				String precioVenta = vf.getVa().getTxtPrecioVenta().getText().trim();
				String proveedor = vf.getVa().getComboProveedor().getSelectedItem().toString();
				String stock = vf.getVa().getTxtStock().getText().trim();
				String stockMinimo = vf.getVa().getTxtStockMinimo().getText().trim();

				if (codigo.isEmpty() || nombre.isEmpty() || precioCompra.isEmpty() || precioVenta.isEmpty()
						|| proveedor.equals("Seleccione proveedor") || stock.isEmpty() || stockMinimo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor completa todos los campos obligatorios (*)",
							"Campos vacíos", JOptionPane.WARNING_MESSAGE);
					return;
				}

				try {
					double precioCompraNum = Double.parseDouble(precioCompra);
					double precioVentaNum = Double.parseDouble(precioVenta);
					int stockNum = Integer.parseInt(stock);
					int stockMinNum = Integer.parseInt(stockMinimo);

					if (precioCompraNum < 0 || precioVentaNum < 0) {
						JOptionPane.showMessageDialog(null, "Los precios no pueden ser negativos.", "Error en precios",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (stockNum < 0 || stockMinNum < 0) {
						JOptionPane.showMessageDialog(null, "El stock no puede ser negativo.", "Error en stock",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (mf.getProductoDAO().findOneByCodigo(codigo) != null) {
						JOptionPane.showMessageDialog(null, "No puede haber productos con código repetido.",
								"Error en stock", JOptionPane.ERROR_MESSAGE);
						return;
					}

					String proveedorNombre, proveedorApellido;
					if (proveedor.equals("Externo")) {
						proveedorNombre = "--";
						proveedorApellido = "--";
					} else {
						proveedorNombre = proveedor.split("\\ ")[0];

						proveedorApellido = proveedor.split("\\ ")[1];
					}

					mf.getProductoDAO()
							.crear(new ProductoDTO(0, codigo, nombre, precioCompraNum, precioVentaNum,
									mf.getProveedorDAO().findOneByNameAndLastName(proveedorNombre, proveedorApellido),
									stockNum, stockMinNum));
					actualizarTablaProductos();

					JOptionPane.showMessageDialog(null, "Producto registrado con éxito 🎉", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);

					vf.getVa().setVisible(false);
					vf.getVa().limpiarCampos();

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Verifica que los precios y stocks sean valores numéricos.",
							"Error de formato", JOptionPane.ERROR_MESSAGE);
				}
			}

			else if (ventana.contains("Actualizar Cliente")) {
				String cedula = vf.getVa().getTxtCedula().getText().trim();
				String nombre = vf.getVa().getTxtNombre().getText().trim();
				String apellido = vf.getVa().getTxtApellido().getText().trim();
				String telefono = vf.getVa().getTxtTelefono().getText().trim();
				String direccion = vf.getVa().getTxtDireccion().getText().trim();

				try {
					if (cedula.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()
							|| direccion.isEmpty()) {
						throw new IllegalArgumentException("Ningún campo puede estar vacío.");
					}

					long cedulaNum;
					long telefonoNum;

					try {
						cedulaNum = Long.parseLong(cedula);
						telefonoNum = Long.parseLong(telefono);
					} catch (NumberFormatException ex) {
						throw new IllegalArgumentException("Cédula y teléfono deben ser numéricos.");
					}
					int id = Integer.parseInt(vf.getVa().getTxtId().getText());

					boolean actualizado = mf.getClienteDAO().actualizar(id,
							new ClienteDTO(0, cedulaNum, nombre, apellido, telefonoNum, direccion));

					if (actualizado) {
						actualizarTablaClientes();
						vf.getVa().setVisible(false);
						JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.");
					} else {
						vf.getVa().setVisible(false);
						JOptionPane.showMessageDialog(null, "No se pudo actualizar", "Advertencia",
								JOptionPane.WARNING_MESSAGE);
					}

				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación",
							JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado al actualizar el cliente.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			} else if (ventana.contains("Actualizar Proveedor")) {
				String nombre = vf.getVa().getTxtNombre().getText().trim();
				String apellido = vf.getVa().getTxtApellido().getText().trim();
				String correo = vf.getVa().getTxtCorreo().getText().trim();
				String telefono = vf.getVa().getTxtTelefono().getText().trim();
				String tiempoEntrega = vf.getVa().getTxtTiempoEntrega().getText().trim();

				try {
					if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty()
							|| tiempoEntrega.isEmpty()) {
						throw new IllegalArgumentException("Ningún campo puede estar vacío.");
					}

					if (!correo.contains("@") || !correo.contains(".")) {
						throw new IllegalArgumentException("El correo no tiene un formato válido.");
					}

					long telefonoNum;
					int tiempoEntregaNum;

					try {
						telefonoNum = Long.parseLong(telefono);
						tiempoEntregaNum = Integer.parseInt(tiempoEntrega);
					} catch (NumberFormatException ex) {
						throw new IllegalArgumentException("Teléfono y tiempo de entrega deben ser numéricos.");
					}

					int id = Integer.parseInt(vf.getVa().getTxtId().getText());

					boolean actualizado = mf.getProveedorDAO().actualizar(id,
							new ProveedorDTO(0, nombre, apellido, correo, telefonoNum, tiempoEntregaNum));

					if (actualizado) {
						actualizarTablaProveedores();
						vf.getVa().setVisible(false);
						JOptionPane.showMessageDialog(null, "Proveedor actualizado correctamente.");
					} else {
						vf.getVa().setVisible(false);
						JOptionPane.showMessageDialog(null, "No se pudo actualizar el proveedor", "Advertencia",
								JOptionPane.WARNING_MESSAGE);
					}

				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación",
							JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado al actualizar el proveedor.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			} else if (ventana.contains("Actualizar Producto")) {
				String codigo = vf.getVa().getTxtCodigo().getText().trim();
				String nombre = vf.getVa().getTxtNombre().getText().trim();
				String precioCompraStr = vf.getVa().getTxtPrecioCompra().getText().trim();
				String precioVentaStr = vf.getVa().getTxtPrecioVenta().getText().trim();
				String proveedor = vf.getVa().getComboProveedor().getSelectedItem().toString();
				String stockStr = vf.getVa().getTxtStock().getText().trim();
				String stockMinimoStr = vf.getVa().getTxtStockMinimo().getText().trim();

				if (mf.getProductoDAO().findOneByCodigo(codigo) != null) {
					JOptionPane.showMessageDialog(null, "No puede haber productos con código repetido.",
							"Error en stock", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					if (codigo.isEmpty() || nombre.isEmpty() || precioCompraStr.isEmpty() || precioVentaStr.isEmpty()
							|| proveedor.equals("Seleccione proveedor") || stockStr.isEmpty()
							|| stockMinimoStr.isEmpty()) {
						throw new IllegalArgumentException("Ningún campo puede estar vacío.");
					}

					double precioCompra;
					double precioVenta;
					int stock;
					int stockMinimo;

					try {
						precioCompra = Double.parseDouble(precioCompraStr);
						precioVenta = Double.parseDouble(precioVentaStr);
						stock = Integer.parseInt(stockStr);
						stockMinimo = Integer.parseInt(stockMinimoStr);
					} catch (NumberFormatException ex) {
						throw new IllegalArgumentException("Precios y stock deben ser numéricos.");
					}

					if (precioCompra <= 0 || precioVenta <= 0) {
						throw new IllegalArgumentException("Los precios deben ser mayores a 0.");
					}
					if (stock < 0 || stockMinimo < 0) {
						throw new IllegalArgumentException("El stock no puede ser negativo.");
					}

					int id = Integer.parseInt(vf.getVa().getTxtId().getText());

					String proveedorNombre = proveedor.split("\\ ")[0];
					String proveedorApellido = proveedor.split("\\ ")[1];
					boolean actualizado = mf.getProductoDAO().actualizar(id,
							new ProductoDTO(0, codigo, nombre, precioCompra, precioVenta,
									mf.getProveedorDAO().findOneByNameAndLastName(proveedorNombre, proveedorApellido),
									stock, stockMinimo));

					if (actualizado) {
						actualizarTablaProductos();
						vf.getVa().setVisible(false);
						JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");
					} else {
						vf.getVa().setVisible(false);
						JOptionPane.showMessageDialog(null, "No se pudo actualizar el producto", "Advertencia",
								JOptionPane.WARNING_MESSAGE);
					}

				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de validación",
							JOptionPane.WARNING_MESSAGE);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado al actualizar el producto.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			} else if (ventana.contains("Registro de Deuda")) {
				CuentaPorPagarDTO cuenta = new CuentaPorPagarDTO();
				cuenta.setEstado(EstadoCuenta.PENDIENTE);
				cuenta.setFechaCompra(vf.getVa().getDateChooserCompra().getDate().toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate());
				cuenta.setFechaVencimiento(vf.getVa().getDateChooserVencimiento().getDate().toInstant()
						.atZone(ZoneId.systemDefault()).toLocalDate());
				List<CuentaProductoDTO> listaCProd = new ArrayList<>();
				CuentaProductoDTO temp = new CuentaProductoDTO();
				temp.setProducto(mf.getProductoDAO().findOne(
						Integer.parseInt(vf.getVa().getComboProducto().getSelectedItem().toString().split(":")[0])));
				temp.setIdCuentaProducto(new CuentaProductoId(temp.getProducto().getId_producto(), 0));
				temp.setMonto(Long.parseLong(vf.getVa().getTxtMonto().getText()));
				listaCProd.add(temp);
				cuenta.setListaCuentaProducto(listaCProd);
				cuenta.setMonto(temp.getMonto());
				mf.getcPorPagarDAO().crear(cuenta);
				JOptionPane.showMessageDialog(null, "Cuenta por pagar creada con éxito.", "Error",
						JOptionPane.WARNING_MESSAGE);
				modeloCuentasPagar.addRow(new Object[] { mf.getcPorPagarDAO().findAll().getLast().getIdCuenta(),
						temp.getProducto().getId_producto(),
						temp.getProducto().getProveedor().getNombre() + " "
								+ temp.getProducto().getProveedor().getApellido(),
						"$" + temp.getMonto(), cuenta.getFechaVencimiento().toString(), cuenta.getEstado().name() });
			}
			vf.getVa().setVisible(false);
			break;
		}

		case "Cancelar Auxiliar": {
			vf.getVa().setVisible(false);
			break;
		}

		}
	}

	public void cerrarPaneles() {
		vf.getVp().getJpnVentas().setVisible(false);
		vf.getVp().getJpnVentas().repaint();
		vf.getVp().getJpnInventario().setVisible(false);
		vf.getVp().getJpnInventario().repaint();
		vf.getVp().getJpnClientes().setVisible(false);
		vf.getVp().getJpnClientes().repaint();
		vf.getVp().getJpnProveedores().setVisible(false);
		vf.getVp().getJpnProveedores().repaint();
		vf.getVp().getJpnReportes().setVisible(false);
		vf.getVp().getJpnReportes().repaint();
		vf.getVp().getJpnCuentas().setVisible(false);
		vf.getVp().getJpnCuentas().repaint();
	}

	public void activarBotones() {
		vf.getVp().getJpnMenuPrincipal().getBtnVentas().setEnabled(true);
		vf.getVp().getJpnMenuPrincipal().getBtnInventario().setEnabled(true);
		vf.getVp().getJpnMenuPrincipal().getBtnClientes().setEnabled(true);
		vf.getVp().getJpnMenuPrincipal().getBtnProveedores().setEnabled(true);
		vf.getVp().getJpnMenuPrincipal().getBtnCuentas().setEnabled(true);
		vf.getVp().getJpnMenuPrincipal().getBtnReportes().setEnabled(true);
	}

	public void actualizarTablaClientes() {
		DefaultTableModel modelo = (DefaultTableModel) vf.getVp().getJpnClientes().getTablaClientes().getModel();
		modelo.setRowCount(0);
		ArrayList<ClienteDTO> lista = mf.getClienteDAO().findAll();
		for (ClienteDTO c : lista) {

			modelo.addRow(new Object[] { c.getId_cliente(), c.getCedula(), c.getNombre(), c.getApellido(),
					c.getTelefono(), c.getDireccion() });
		}
	}

	public void actualizarTablaProveedores() {
		DefaultTableModel modelo = (DefaultTableModel) vf.getVp().getJpnProveedores().getTablaProveedores().getModel();
		modelo.setRowCount(0);
		ArrayList<ProveedorDTO> lista = mf.getProveedorDAO().findAll();
		for (ProveedorDTO p : lista) {
			modelo.addRow(new Object[] { p.getId_proveedor(), p.getNombre(), p.getApellido(), p.getCorreo(),
					p.getTelefono(), p.getTiempo_entrega() });
		}
	}

	public void actualizarTablaProductos() {
		DefaultTableModel modelo = (DefaultTableModel) vf.getVp().getJpnInventario().getTablaInventario().getModel();
		modelo.setRowCount(0);
		ArrayList<ProductoDTO> lista = mf.getProductoDAO().findAll();
		for (ProductoDTO p : lista) {
			modelo.addRow(new Object[] { p.getId_producto(), p.getCodigo(), p.getNombre(), p.getPrecio_compra(),
					p.getPrecio_venta(), p.getProveedor().getNombre() + " " + p.getProveedor().getApellido(),
					p.getStock(), p.getStock_minimo() });
		}
	}

	public void actualizarTablaReportes() {
		DefaultTableModel modeloReporte = (DefaultTableModel) vf.getVp().getJpnReportes().getTabla().getModel();
		modeloReporte.setRowCount(0);
		for (ReporteDTO r : mf.getReporteDAO().findAll()) {
			modeloReporte
					.addRow(new Object[] { r.getIdReporte(), r.getFechaGeneracion(), r.getTipo(), r.getRutaPdf() });
		}
	}

}
