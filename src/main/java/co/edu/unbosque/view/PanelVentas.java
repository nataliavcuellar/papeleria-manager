package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class PanelVentas extends JPanel {

	private JPanel panelAgregarProducto, panelDetalleFactura, panelHistorialVentas;
	private JLabel lblTitulo, lblSuperior, lblHora;
	private JLabel lblAgregar, lblCedula, lblNombre, lblCliente, lblProducto, lblCantidad, lblPrecio, lblPrecioValor,
			lblTipoVenta, lblDetalle, lblTotal, lblTotalValor, lblHistorial, lblNombreProducto, lblNombreEtiqueta;
	private JTextField txtCedula, txtCantidad;
	private JButton btnBuscar, btnAgregar, btnGenerar, btnImprimir, btnEliminar;
	private JComboBox<String> jcbProductos;
	private JRadioButton jrbPagada, jrbFiada;
	private JTable tablaFactura, tablaVentas;
	private JScrollPane scrollFactura, scrollVentas;
	private JSeparator sep1, sep2;
	private ButtonGroup grupoVenta;

	public PanelVentas() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(168, 45, 1012, 610);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setVisible(false);
		run();
	}

	public void run() {

		lblTitulo = new JLabel("[VENTAS] Ventas y Facturación");
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTitulo.setForeground(new Color(30, 60, 80));
		lblTitulo.setBounds(25, 0, 400, 30);
		add(lblTitulo);

		lblHora = new JLabel(getHoraActual(), SwingConstants.RIGHT);
		lblHora.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblHora.setForeground(new Color(30, 60, 80));
		lblHora.setBounds(810, 5, 180, 30);
		add(lblHora);

		lblSuperior = new JLabel();
		lblSuperior.setOpaque(true);
		lblSuperior.setBackground(new Color(192, 226, 226));
		lblSuperior.setBounds(1, 1, 1010, 30);
		add(lblSuperior);

		panelAgregarProducto = new JPanel();
		panelAgregarProducto.setLayout(null);
		panelAgregarProducto.setBackground(Color.WHITE);
		panelAgregarProducto.setBorder(new LineBorder(Color.GRAY, 1));
		panelAgregarProducto.setBounds(4, 35, 500, 350);
		add(panelAgregarProducto);

		lblAgregar = new JLabel("AGREGAR PRODUCTOS");
		lblAgregar.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblAgregar.setForeground(new Color(20, 40, 80));
		lblAgregar.setBounds(160, 10, 200, 25);
		panelAgregarProducto.add(lblAgregar);

		lblCedula = new JLabel("Cédula Cliente:");
		lblCedula.setBounds(40, 50, 110, 25);
		panelAgregarProducto.add(lblCedula);

		txtCedula = new JTextField();
		txtCedula.setBounds(150, 50, 120, 25);
		panelAgregarProducto.add(txtCedula);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(280, 50, 90, 25);
		btnBuscar.setBackground(new Color(72, 124, 154));
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelAgregarProducto.add(btnBuscar);

		lblNombre = new JLabel("Nombre Cliente: ");
		lblNombre.setBounds(40, 85, 150, 25);
		panelAgregarProducto.add(lblNombre);

		lblCliente = new JLabel("[Cliente]");
		lblCliente.setForeground(new Color(26, 158, 145));
		lblCliente.setBounds(150, 85, 200, 25);
		panelAgregarProducto.add(lblCliente);

		sep1 = new JSeparator();
		sep1.setBounds(30, 115, 420, 1);
		panelAgregarProducto.add(sep1);

		lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(40, 130, 100, 25);
		panelAgregarProducto.add(lblProducto);

		jcbProductos = new JComboBox<>();
		jcbProductos.setBounds(150, 130, 220, 25);
		panelAgregarProducto.add(jcbProductos);

		lblNombreEtiqueta = new JLabel("Nombre:");
		lblNombreEtiqueta.setBounds(40, 165, 100, 25);
		panelAgregarProducto.add(lblNombreEtiqueta);

		lblNombreProducto = new JLabel("[Nombre producto]");
		lblNombreProducto.setForeground(new Color(26, 158, 145));
		lblNombreProducto.setBounds(150, 165, 100, 25);
		panelAgregarProducto.add(lblNombreProducto);

		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(40, 200, 100, 25);
		panelAgregarProducto.add(lblCantidad);

		txtCantidad = new JTextField("1");
		txtCantidad.setBounds(150, 200, 60, 25);
		panelAgregarProducto.add(txtCantidad);

		lblPrecio = new JLabel("Precio Unit.:");
		lblPrecio.setBounds(280, 200, 100, 25);
		panelAgregarProducto.add(lblPrecio);

		lblPrecioValor = new JLabel("$0.00");
		lblPrecioValor.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblPrecioValor.setForeground(new Color(26, 158, 145));
		lblPrecioValor.setBounds(370, 200, 100, 25);
		panelAgregarProducto.add(lblPrecioValor);

		btnAgregar = new JButton("+ Agregar a Factura");
		btnAgregar.setBounds(150, 240, 200, 35);
		btnAgregar.setBackground(new Color(26, 158, 145));
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFocusPainted(false);
		btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelAgregarProducto.add(btnAgregar);

		sep2 = new JSeparator();
		sep2.setBounds(30, 285, 420, 1);
		panelAgregarProducto.add(sep2);

		lblTipoVenta = new JLabel("Tipo de Venta:");
		lblTipoVenta.setBounds(40, 295, 120, 25);
		panelAgregarProducto.add(lblTipoVenta);

		jrbPagada = new JRadioButton("Venta Pagada", true);
		jrbPagada.setBounds(40, 320, 120, 25);
		jrbPagada.setBackground(Color.WHITE);
		jrbPagada.setFocusable(false);
		panelAgregarProducto.add(jrbPagada);

		jrbFiada = new JRadioButton("Venta Fiada");
		jrbFiada.setBounds(180, 320, 120, 25);
		jrbFiada.setBackground(Color.WHITE);
		jrbFiada.setFocusable(false);
		panelAgregarProducto.add(jrbFiada);

		grupoVenta = new ButtonGroup();
		grupoVenta.add(jrbPagada);
		grupoVenta.add(jrbFiada);

		panelDetalleFactura = new JPanel();
		panelDetalleFactura.setLayout(null);
		panelDetalleFactura.setBackground(Color.WHITE);
		panelDetalleFactura.setBorder(new LineBorder(Color.GRAY));
		panelDetalleFactura.setBounds(508, 35, 500, 350);
		add(panelDetalleFactura);

		lblDetalle = new JLabel("DETALLES DE LA FACTURA");
		lblDetalle.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblDetalle.setForeground(new Color(20, 40, 80));
		lblDetalle.setBounds(20, 10, 250, 25);
		panelDetalleFactura.add(lblDetalle);

		String[] columnas = { "Cod. Prod", "Producto", "Cant.", "Precio Unit.", "Subtotal" };
		DefaultTableModel modeloFactura = new DefaultTableModel(null, columnas);
		tablaFactura = new JTable(modeloFactura);
		scrollFactura = new JScrollPane(tablaFactura);
		scrollFactura.setBounds(20, 40, 465, 210);
		panelDetalleFactura.add(scrollFactura);

		lblTotal = new JLabel("TOTAL:");
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTotal.setBounds(20, 260, 100, 25);
		panelDetalleFactura.add(lblTotal);

		lblTotalValor = new JLabel("0.00");
		lblTotalValor.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTotalValor.setForeground(Color.RED);
		lblTotalValor.setBounds(370, 260, 100, 25);
		panelDetalleFactura.add(lblTotalValor);

		btnGenerar = new JButton("Generar Factura");
		btnGenerar.setBounds(120, 300, 150, 30);
		btnGenerar.setBackground(new Color(26, 158, 145));
		btnGenerar.setForeground(Color.WHITE);
		btnGenerar.setFocusPainted(false);
		btnGenerar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelDetalleFactura.add(btnGenerar);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(280, 300, 100, 30);
		btnImprimir.setBackground(new Color(72, 124, 154));
		btnImprimir.setForeground(Color.WHITE);
		btnImprimir.setFocusPainted(false);
		btnImprimir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelDetalleFactura.add(btnImprimir);

		btnEliminar = new JButton("🗑");
		btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 32));
		btnEliminar.setBackground(new Color(220, 53, 69));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFocusPainted(false);
		btnEliminar.setBorder(BorderFactory.createEmptyBorder());
		btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEliminar.setBounds(440, 300, 40, 30);
		panelDetalleFactura.add(btnEliminar);

		panelHistorialVentas = new JPanel();
		panelHistorialVentas.setLayout(null);
		panelHistorialVentas.setBackground(Color.WHITE);
		panelHistorialVentas.setBorder(new LineBorder(Color.GRAY));
		panelHistorialVentas.setBounds(4, 390, 1004, 215);
		add(panelHistorialVentas);

		lblHistorial = new JLabel("Ventas Recientes");
		lblHistorial.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblHistorial.setForeground(new Color(20, 40, 80));
		lblHistorial.setBounds(20, 10, 200, 25);
		panelHistorialVentas.add(lblHistorial);

		String[] columnasVentas = { "Cliente", "Total", "Estado", "Tipo" };
		DefaultTableModel modeloHistorial = new DefaultTableModel(null, columnasVentas);
		tablaVentas = new JTable(modeloHistorial);
		scrollVentas = new JScrollPane(tablaVentas);
		scrollVentas.setBounds(20, 40, 967, 160);
		panelHistorialVentas.add(scrollVentas);

		actualizarHora();
	}

	private String getHoraActual() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return formato.format(new Date());
	}

	private void actualizarHora() {
		Timer timer = new Timer(1000, e -> lblHora.setText(getHoraActual()));
		timer.start();
	}

	/**
	 * @return the btnEliminar
	 */
	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	/**
	 * @param btnEliminar the btnEliminar to set
	 */
	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	/**
	 * @return the panelAgregarProducto
	 */
	public JPanel getPanelAgregarProducto() {
		return panelAgregarProducto;
	}

	/**
	 * @param panelAgregarProducto the panelAgregarProducto to set
	 */
	public void setPanelAgregarProducto(JPanel panelAgregarProducto) {
		this.panelAgregarProducto = panelAgregarProducto;
	}

	/**
	 * @return the panelDetalleFactura
	 */
	public JPanel getPanelDetalleFactura() {
		return panelDetalleFactura;
	}

	/**
	 * @param panelDetalleFactura the panelDetalleFactura to set
	 */
	public void setPanelDetalleFactura(JPanel panelDetalleFactura) {
		this.panelDetalleFactura = panelDetalleFactura;
	}

	/**
	 * @return the panelHistorialVentas
	 */
	public JPanel getPanelHistorialVentas() {
		return panelHistorialVentas;
	}

	/**
	 * @param panelHistorialVentas the panelHistorialVentas to set
	 */
	public void setPanelHistorialVentas(JPanel panelHistorialVentas) {
		this.panelHistorialVentas = panelHistorialVentas;
	}

	/**
	 * @return the lblTitulo
	 */
	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	/**
	 * @param lblTitulo the lblTitulo to set
	 */
	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	/**
	 * @return the lblSuperior
	 */
	public JLabel getLblSuperior() {
		return lblSuperior;
	}

	/**
	 * @param lblSuperior the lblSuperior to set
	 */
	public void setLblSuperior(JLabel lblSuperior) {
		this.lblSuperior = lblSuperior;
	}

	/**
	 * @return the lblHora
	 */
	public JLabel getLblHora() {
		return lblHora;
	}

	/**
	 * @param lblHora the lblHora to set
	 */
	public void setLblHora(JLabel lblHora) {
		this.lblHora = lblHora;
	}

	/**
	 * @return the lblAgregar
	 */
	public JLabel getLblAgregar() {
		return lblAgregar;
	}

	/**
	 * @param lblAgregar the lblAgregar to set
	 */
	public void setLblAgregar(JLabel lblAgregar) {
		this.lblAgregar = lblAgregar;
	}

	/**
	 * @return the lblCedula
	 */
	public JLabel getLblCedula() {
		return lblCedula;
	}

	/**
	 * @param lblCedula the lblCedula to set
	 */
	public void setLblCedula(JLabel lblCedula) {
		this.lblCedula = lblCedula;
	}

	/**
	 * @return the lblNombre
	 */
	public JLabel getLblNombre() {
		return lblNombre;
	}

	/**
	 * @param lblNombre the lblNombre to set
	 */
	public void setLblNombre(JLabel lblNombre) {
		this.lblNombre = lblNombre;
	}

	/**
	 * @return the lblCliente
	 */
	public JLabel getLblCliente() {
		return lblCliente;
	}

	/**
	 * @param lblCliente the lblCliente to set
	 */
	public void setLblCliente(JLabel lblCliente) {
		this.lblCliente = lblCliente;
	}

	/**
	 * @return the lblProducto
	 */
	public JLabel getLblProducto() {
		return lblProducto;
	}

	/**
	 * @param lblProducto the lblProducto to set
	 */
	public void setLblProducto(JLabel lblProducto) {
		this.lblProducto = lblProducto;
	}

	/**
	 * @return the lblCantidad
	 */
	public JLabel getLblCantidad() {
		return lblCantidad;
	}

	/**
	 * @param lblCantidad the lblCantidad to set
	 */
	public void setLblCantidad(JLabel lblCantidad) {
		this.lblCantidad = lblCantidad;
	}

	/**
	 * @return the lblPrecio
	 */
	public JLabel getLblPrecio() {
		return lblPrecio;
	}

	/**
	 * @param lblPrecio the lblPrecio to set
	 */
	public void setLblPrecio(JLabel lblPrecio) {
		this.lblPrecio = lblPrecio;
	}

	/**
	 * @return the lblPrecioValor
	 */
	public JLabel getLblPrecioValor() {
		return lblPrecioValor;
	}

	/**
	 * @param lblPrecioValor the lblPrecioValor to set
	 */
	public void setLblPrecioValor(JLabel lblPrecioValor) {
		this.lblPrecioValor = lblPrecioValor;
	}

	/**
	 * @return the lblTipoVenta
	 */
	public JLabel getLblTipoVenta() {
		return lblTipoVenta;
	}

	/**
	 * @param lblTipoVenta the lblTipoVenta to set
	 */
	public void setLblTipoVenta(JLabel lblTipoVenta) {
		this.lblTipoVenta = lblTipoVenta;
	}

	/**
	 * @return the lblDetalle
	 */
	public JLabel getLblDetalle() {
		return lblDetalle;
	}

	/**
	 * @param lblDetalle the lblDetalle to set
	 */
	public void setLblDetalle(JLabel lblDetalle) {
		this.lblDetalle = lblDetalle;
	}

	/**
	 * @return the lblTotal
	 */
	public JLabel getLblTotal() {
		return lblTotal;
	}

	/**
	 * @param lblTotal the lblTotal to set
	 */
	public void setLblTotal(JLabel lblTotal) {
		this.lblTotal = lblTotal;
	}

	/**
	 * @return the lblTotalValor
	 */
	public JLabel getLblTotalValor() {
		return lblTotalValor;
	}

	/**
	 * @param lblTotalValor the lblTotalValor to set
	 */
	public void setLblTotalValor(JLabel lblTotalValor) {
		this.lblTotalValor = lblTotalValor;
	}

	/**
	 * @return the lblHistorial
	 */
	public JLabel getLblHistorial() {
		return lblHistorial;
	}

	/**
	 * @param lblHistorial the lblHistorial to set
	 */
	public void setLblHistorial(JLabel lblHistorial) {
		this.lblHistorial = lblHistorial;
	}

	/**
	 * @return the txtCedula
	 */
	public JTextField getTxtCedula() {
		return txtCedula;
	}

	/**
	 * @param txtCedula the txtCedula to set
	 */
	public void setTxtCedula(JTextField txtCedula) {
		this.txtCedula = txtCedula;
	}

	/**
	 * @return the txtCantidad
	 */
	public JTextField getTxtCantidad() {
		return txtCantidad;
	}

	/**
	 * @param txtCantidad the txtCantidad to set
	 */
	public void setTxtCantidad(JTextField txtCantidad) {
		this.txtCantidad = txtCantidad;
	}

	/**
	 * @return the btnBuscar
	 */
	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	/**
	 * @param btnBuscar the btnBuscar to set
	 */
	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	/**
	 * @return the btnAgregar
	 */
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	/**
	 * @param btnAgregar the btnAgregar to set
	 */
	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	/**
	 * @return the btnGenerar
	 */
	public JButton getBtnGenerar() {
		return btnGenerar;
	}

	/**
	 * @param btnGenerar the btnGenerar to set
	 */
	public void setBtnGenerar(JButton btnGenerar) {
		this.btnGenerar = btnGenerar;
	}

	/**
	 * @return the btnImprimir
	 */
	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	/**
	 * @param btnImprimir the btnImprimir to set
	 */
	public void setBtnImprimir(JButton btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	/**
	 * @return the jcbProductos
	 */
	public JComboBox<String> getJcbProductos() {
		return jcbProductos;
	}

	/**
	 * @param jcbProductos the jcbProductos to set
	 */
	public void setJcbProductos(JComboBox<String> jcbProductos) {
		this.jcbProductos = jcbProductos;
	}

	/**
	 * @return the jrbPagada
	 */
	public JRadioButton getJrbPagada() {
		return jrbPagada;
	}

	/**
	 * @param jrbPagada the jrbPagada to set
	 */
	public void setJrbPagada(JRadioButton jrbPagada) {
		this.jrbPagada = jrbPagada;
	}

	/**
	 * @return the jrbFiada
	 */
	public JRadioButton getJrbFiada() {
		return jrbFiada;
	}

	/**
	 * @param jrbFiada the jrbFiada to set
	 */
	public void setJrbFiada(JRadioButton jrbFiada) {
		this.jrbFiada = jrbFiada;
	}

	/**
	 * @return the tablaFactura
	 */
	public JTable getTablaFactura() {
		return tablaFactura;
	}

	/**
	 * @param tablaFactura the tablaFactura to set
	 */
	public void setTablaFactura(JTable tablaFactura) {
		this.tablaFactura = tablaFactura;
	}

	/**
	 * @return the tablaVentas
	 */
	public JTable getTablaVentas() {
		return tablaVentas;
	}

	/**
	 * @param tablaVentas the tablaVentas to set
	 */
	public void setTablaVentas(JTable tablaVentas) {
		this.tablaVentas = tablaVentas;
	}

	/**
	 * @return the scrollFactura
	 */
	public JScrollPane getScrollFactura() {
		return scrollFactura;
	}

	/**
	 * @param scrollFactura the scrollFactura to set
	 */
	public void setScrollFactura(JScrollPane scrollFactura) {
		this.scrollFactura = scrollFactura;
	}

	/**
	 * @return the scrollVentas
	 */
	public JScrollPane getScrollVentas() {
		return scrollVentas;
	}

	/**
	 * @param scrollVentas the scrollVentas to set
	 */
	public void setScrollVentas(JScrollPane scrollVentas) {
		this.scrollVentas = scrollVentas;
	}

	/**
	 * @return the sep1
	 */
	public JSeparator getSep1() {
		return sep1;
	}

	/**
	 * @param sep1 the sep1 to set
	 */
	public void setSep1(JSeparator sep1) {
		this.sep1 = sep1;
	}

	/**
	 * @return the sep2
	 */
	public JSeparator getSep2() {
		return sep2;
	}

	/**
	 * @param sep2 the sep2 to set
	 */
	public void setSep2(JSeparator sep2) {
		this.sep2 = sep2;
	}

	/**
	 * @return the grupoVenta
	 */
	public ButtonGroup getGrupoVenta() {
		return grupoVenta;
	}

	/**
	 * @param grupoVenta the grupoVenta to set
	 */
	public void setGrupoVenta(ButtonGroup grupoVenta) {
		this.grupoVenta = grupoVenta;
	}

	/**
	 * @return the lblNombreProducto
	 */
	public JLabel getLblNombreProducto() {
		return lblNombreProducto;
	}

	/**
	 * @param lblNombreProducto the lblNombreProducto to set
	 */
	public void setLblNombreProducto(JLabel lblNombreProducto) {
		this.lblNombreProducto = lblNombreProducto;
	}

	/**
	 * @return the lblNombreEtiqueta
	 */
	public JLabel getLblNombreEtiqueta() {
		return lblNombreEtiqueta;
	}

	/**
	 * @param lblNombreEtiqueta the lblNombreEtiqueta to set
	 */
	public void setLblNombreEtiqueta(JLabel lblNombreEtiqueta) {
		this.lblNombreEtiqueta = lblNombreEtiqueta;
	}

}
