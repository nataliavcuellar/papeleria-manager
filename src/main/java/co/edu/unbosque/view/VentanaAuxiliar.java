package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

public class VentanaAuxiliar extends JDialog {

	private JPanel panelActual;
	private JLabel lblSuperior, lblTitulo;
	private JButton btnGuardar, btnCancelar;

	private JTextField txtCedula, txtNombre, txtApellido, txtTelefono, txtDireccion, txtId;
	private JTextField txtCorreo, txtTiempoEntrega;
	private JTextField txtCodigo, txtPrecioCompra, txtPrecioVenta, txtStock, txtStockMinimo;
	private JComboBox<String> comboProveedor;

	private JDateChooser dateChooserCompra, dateChooserVencimiento;
	private JTextField txtMonto, txtNotas;
	private JComboBox<String> comboConcepto;
	private JTextArea txtAreaNotas;
	private JScrollPane scrollNotas;

	private JComboBox<String> comboProducto;

	public VentanaAuxiliar(JFrame parent) {
		super(parent, true);
		setTitle("Ventana Auxiliar");
		setSize(500, 550);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		lblTitulo = new JLabel("", SwingConstants.LEFT);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(20, 10, 300, 30);
		add(lblTitulo);

		lblSuperior = new JLabel();
		lblSuperior.setOpaque(true);
		lblSuperior.setBackground(new Color(72, 124, 154));
		lblSuperior.setBounds(0, 0, 500, 50);
		add(lblSuperior);

		btnGuardar = crearBoton("Guardar", new Color(26, 158, 145));
		btnGuardar.setBounds(100, 450, 130, 40);
		add(btnGuardar);

		btnCancelar = crearBoton("Cancelar", new Color(220, 53, 69));
		btnCancelar.setBounds(270, 450, 130, 40);
		add(btnCancelar);

		btnGuardar.setVisible(false);
		btnCancelar.setVisible(false);

		txtId = new JTextField();
		txtId.setVisible(false);
		add(txtId);

	}

	public void panelDeuda() {
		limpiarPanel();
		lblTitulo.setText("Registro de Deuda");
		btnGuardar.setText("Guardar");

		panelActual = crearPanel();
		add(panelActual);

		// Texto informativo en la parte superior
		JLabel lblInfo = new JLabel("Ingrese los detalles de la compra o servicio recibido a crédito");
		lblInfo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblInfo.setForeground(new Color(100, 100, 100));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(20, 10, 460, 30);
		lblInfo.setOpaque(true);
		lblInfo.setBackground(new Color(255, 235, 220));
		lblInfo.setBorder(BorderFactory.createLineBorder(new Color(200, 150, 100)));
		panelActual.add(lblInfo);

		int y = 60;

		// Producto
		panelActual.add(crearLabel("Producto:", 50, y));
		comboProducto = new JComboBox<>();
		comboProducto.setBounds(180, y, 250, 30);
		comboProducto.setBorder(BorderFactory.createLineBorder(new Color(72, 124, 154)));
		comboProducto.setBackground(Color.WHITE);
		comboProducto.addItem("Seleccione proveedor");
		panelActual.add(comboProducto);

		// Monto de la Deuda
		panelActual.add(crearLabel("Monto de la Deuda:", 50, y += 60));
		JPanel panelMonto = new JPanel(null);
		panelMonto.setBounds(180, y, 250, 30);
		panelMonto.setBackground(Color.WHITE);
		panelMonto.setBorder(BorderFactory.createLineBorder(new Color(72, 124, 154)));

		JLabel lblPeso = new JLabel("$");
		lblPeso.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblPeso.setForeground(new Color(0, 150, 0));
		lblPeso.setBounds(5, 0, 20, 30);
		panelMonto.add(lblPeso);

		txtMonto = new JTextField("0.00");
		txtMonto.setBounds(25, 5, 220, 20);
		txtMonto.setBorder(null);
		txtMonto.setFont(new Font("SansSerif", Font.PLAIN, 13));
		panelMonto.add(txtMonto);

		panelActual.add(panelMonto);

		// Fecha de Compra con JDateChooser
		panelActual.add(crearLabel("Fecha de Compra:", 50, y += 60));
		dateChooserCompra = new JDateChooser();
		dateChooserCompra.setBounds(180, y, 250, 30);
		dateChooserCompra.setDateFormatString("dd/MM/yyyy");
		dateChooserCompra.setFont(new Font("SansSerif", Font.PLAIN, 13));
		dateChooserCompra.setBackground(Color.WHITE);
		dateChooserCompra.setBorder(BorderFactory.createLineBorder(new Color(72, 124, 154)));
		dateChooserCompra.setDate(new Date()); // Fecha actual por defecto
		dateChooserCompra.getCalendarButton().setBackground(new Color(72, 124, 154));
		dateChooserCompra.getCalendarButton().setForeground(Color.WHITE);
		panelActual.add(dateChooserCompra);

		// Fecha de Vencimiento con JDateChooser
		panelActual.add(crearLabel("Fecha de Vencimiento:", 50, y += 60));
		dateChooserVencimiento = new JDateChooser();
		dateChooserVencimiento.setBounds(180, y, 250, 30);
		dateChooserVencimiento.setDateFormatString("dd/MM/yyyy");
		dateChooserVencimiento.setFont(new Font("SansSerif", Font.PLAIN, 13));
		dateChooserVencimiento.setBackground(Color.WHITE);
		dateChooserVencimiento.setBorder(BorderFactory.createLineBorder(new Color(72, 124, 154)));

		// Establecer fecha de vencimiento un mes después de hoy
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		dateChooserVencimiento.setDate(cal.getTime());
		dateChooserVencimiento.getCalendarButton().setBackground(new Color(72, 124, 154));
		dateChooserVencimiento.getCalendarButton().setForeground(Color.WHITE);
		panelActual.add(dateChooserVencimiento);

		mostrarBotones();
		repaint();
	}

	public void panelCliente() {
		limpiarPanel();
		lblTitulo.setText("Registro de Cliente");
		btnGuardar.setText("Guardar");

		panelActual = crearPanel();
		add(panelActual);

		int y = 40;
		panelActual.add(crearLabel("Cédula *:", 50, y));
		txtCedula = crearCampo(180, y);

		panelActual.add(crearLabel("Nombre *:", 50, y += 50));
		txtNombre = crearCampo(180, y);

		panelActual.add(crearLabel("Apellido *:", 50, y += 50));
		txtApellido = crearCampo(180, y);

		panelActual.add(crearLabel("Teléfono *:", 50, y += 50));
		txtTelefono = crearCampo(180, y);

		panelActual.add(crearLabel("Dirección *:", 50, y += 50));
		txtDireccion = crearCampo(180, y);

		panelActual.add(txtCedula);
		panelActual.add(txtNombre);
		panelActual.add(txtApellido);
		panelActual.add(txtTelefono);
		panelActual.add(txtDireccion);

		mostrarBotones();
		repaint();
	}

	public void panelProveedor() {
		limpiarPanel();
		lblTitulo.setText("Registro de Proveedor");
		btnGuardar.setText("Guardar");

		panelActual = crearPanel();
		add(panelActual);

		int y = 40;
		panelActual.add(crearLabel("Nombre *:", 50, y));
		txtNombre = crearCampo(180, y);

		panelActual.add(crearLabel("Apellido *:", 50, y += 50));
		txtApellido = crearCampo(180, y);

		panelActual.add(crearLabel("Correo *:", 50, y += 50));
		txtCorreo = crearCampo(180, y);

		panelActual.add(crearLabel("Teléfono *:", 50, y += 50));
		txtTelefono = crearCampo(180, y);

		panelActual.add(crearLabel("Tiempo entrega *:", 50, y += 50));
		txtTiempoEntrega = crearCampo(180, y);

		panelActual.add(txtNombre);
		panelActual.add(txtApellido);
		panelActual.add(txtCorreo);
		panelActual.add(txtTelefono);
		panelActual.add(txtTiempoEntrega);

		mostrarBotones();
		repaint();
	}

	public void panelProducto() {
		limpiarPanel();
		lblTitulo.setText("Registro de Producto");
		btnGuardar.setText("Guardar");

		panelActual = crearPanel();
		add(panelActual);

		int y = 40;
		panelActual.add(crearLabel("Código *:", 50, y));
		txtCodigo = crearCampo(180, y);

		panelActual.add(crearLabel("Nombre *:", 50, y += 40));
		txtNombre = crearCampo(180, y);

		panelActual.add(crearLabel("Precio compra *:", 50, y += 40));
		txtPrecioCompra = crearCampo(180, y);

		panelActual.add(crearLabel("Precio venta *:", 50, y += 40));
		txtPrecioVenta = crearCampo(180, y);

		panelActual.add(crearLabel("Proveedor *:", 50, y += 40));
		comboProveedor = new JComboBox<>();
		comboProveedor.setBounds(180, y, 250, 25);
		comboProveedor.setBorder(BorderFactory.createLineBorder(new Color(72, 124, 154)));
		comboProveedor.addItem("Seleccione proveedor");
		comboProveedor.addItem("Otro...");
		panelActual.add(comboProveedor);

		panelActual.add(crearLabel("Stock *:", 50, y += 40));
		txtStock = crearCampo(180, y);

		panelActual.add(crearLabel("Stock mínimo *:", 50, y += 40));
		txtStockMinimo = crearCampo(180, y);

		panelActual.add(txtCodigo);
		panelActual.add(txtNombre);
		panelActual.add(txtPrecioCompra);
		panelActual.add(txtPrecioVenta);
		panelActual.add(txtStock);
		panelActual.add(txtStockMinimo);

		mostrarBotones();
		repaint();
	}

	private JPanel crearPanel() {
		JPanel panel = new JPanel(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 50, 500, 380); // 👈 más alto para la nueva altura
		return panel;
	}

	private JLabel crearLabel(String texto, int x, int y) {
		JLabel lbl = new JLabel(texto);
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lbl.setForeground(new Color(30, 60, 80));
		lbl.setBounds(x, y, 130, 25);
		return lbl;
	}

	private JTextField crearCampo(int x, int y) {
		JTextField txt = new JTextField();
		txt.setBounds(x, y, 250, 25);
		txt.setBorder(BorderFactory.createLineBorder(new Color(72, 124, 154)));
		return txt;
	}

	private JButton crearBoton(String texto, Color color) {
		JButton btn = new JButton(texto);
		btn.setBackground(color);
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("SansSerif", Font.BOLD, 13));
		btn.setFocusPainted(false);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		return btn;
	}

	private void limpiarPanel() {
		if (panelActual != null) {
			remove(panelActual);
		}
		btnGuardar.setVisible(false);
		btnCancelar.setVisible(false);
		repaint();
	}

	private void mostrarBotones() {
		btnGuardar.setVisible(true);
		btnCancelar.setVisible(true);
	}

	public void modoActualizar() {
		lblTitulo.setText(lblTitulo.getText().replace("Registro de", "Actualizar"));
		btnGuardar.setText("Actualizar");
		btnGuardar.setBackground(new Color(72, 124, 154));
	}

	public void cargarDatosCliente(int id, String cedula, String nombre, String apellido, String telefono,
			String direccion) {
		if (txtId != null)
			txtId.setText(String.valueOf(id));
		if (txtCedula != null)
			txtCedula.setText(cedula);
		if (txtNombre != null)
			txtNombre.setText(nombre);
		if (txtApellido != null)
			txtApellido.setText(apellido);
		if (txtTelefono != null)
			txtTelefono.setText(telefono);
		if (txtDireccion != null)
			txtDireccion.setText(direccion);
	}

	public void cargarDatosProveedor(int id, String nombre, String apellido, String correo, String telefono,
			String tiempoEntrega) {
		if (txtId != null)
			txtId.setText(String.valueOf(id));
		if (txtNombre != null)
			txtNombre.setText(nombre);
		if (txtApellido != null)
			txtApellido.setText(apellido);
		if (txtCorreo != null)
			txtCorreo.setText(correo);
		if (txtTelefono != null)
			txtTelefono.setText(telefono);
		if (txtTiempoEntrega != null)
			txtTiempoEntrega.setText(tiempoEntrega);
	}

	public void cargarDatosProducto(int id, String codigo, String nombre, String precioCompra, String precioVenta,
			String proveedor, String stock, String stockMinimo) {
		if (txtId != null)
			txtId.setText(String.valueOf(id));
		if (txtCodigo != null)
			txtCodigo.setText(codigo);
		if (txtNombre != null)
			txtNombre.setText(nombre);
		if (txtPrecioCompra != null)
			txtPrecioCompra.setText(precioCompra);
		if (txtPrecioVenta != null)
			txtPrecioVenta.setText(precioVenta);
		if (comboProveedor != null) {
			comboProveedor.removeAllItems();
		}
		if (txtStock != null)
			txtStock.setText(stock);
		if (txtStockMinimo != null)
			txtStockMinimo.setText(stockMinimo);
	}

	public void limpiarCampos() {
		if (txtCedula != null)
			txtCedula.setText("");
		if (txtNombre != null)
			txtNombre.setText("");
		if (txtApellido != null)
			txtApellido.setText("");
		if (txtTelefono != null)
			txtTelefono.setText("");
		if (txtDireccion != null)
			txtDireccion.setText("");
		if (txtCorreo != null)
			txtCorreo.setText("");
		if (txtTiempoEntrega != null)
			txtTiempoEntrega.setText("");
	}

	/**
	 * @return the panelActual
	 */
	public JPanel getPanelActual() {
		return panelActual;
	}

	/**
	 * @param panelActual the panelActual to set
	 */
	public void setPanelActual(JPanel panelActual) {
		this.panelActual = panelActual;
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
	 * @return the btnGuardar
	 */
	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	/**
	 * @param btnGuardar the btnGuardar to set
	 */
	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	/**
	 * @return the btnCancelar
	 */
	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	/**
	 * @param btnCancelar the btnCancelar to set
	 */
	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
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
	 * @return the txtNombre
	 */
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	/**
	 * @param txtNombre the txtNombre to set
	 */
	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	/**
	 * @return the txtApellido
	 */
	public JTextField getTxtApellido() {
		return txtApellido;
	}

	/**
	 * @param txtApellido the txtApellido to set
	 */
	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	/**
	 * @return the txtTelefono
	 */
	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	/**
	 * @param txtTelefono the txtTelefono to set
	 */
	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	/**
	 * @return the txtDireccion
	 */
	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	/**
	 * @param txtDireccion the txtDireccion to set
	 */
	public void setTxtDireccion(JTextField txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	/**
	 * @return the txtCorreo
	 */
	public JTextField getTxtCorreo() {
		return txtCorreo;
	}

	/**
	 * @param txtCorreo the txtCorreo to set
	 */
	public void setTxtCorreo(JTextField txtCorreo) {
		this.txtCorreo = txtCorreo;
	}

	/**
	 * @return the txtTiempoEntrega
	 */
	public JTextField getTxtTiempoEntrega() {
		return txtTiempoEntrega;
	}

	/**
	 * @param txtTiempoEntrega the txtTiempoEntrega to set
	 */
	public void setTxtTiempoEntrega(JTextField txtTiempoEntrega) {
		this.txtTiempoEntrega = txtTiempoEntrega;
	}

	/**
	 * @return the txtCodigo
	 */
	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	/**
	 * @param txtCodigo the txtCodigo to set
	 */
	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	/**
	 * @return the txtPrecioCompra
	 */
	public JTextField getTxtPrecioCompra() {
		return txtPrecioCompra;
	}

	/**
	 * @param txtPrecioCompra the txtPrecioCompra to set
	 */
	public void setTxtPrecioCompra(JTextField txtPrecioCompra) {
		this.txtPrecioCompra = txtPrecioCompra;
	}

	/**
	 * @return the txtPrecioVenta
	 */
	public JTextField getTxtPrecioVenta() {
		return txtPrecioVenta;
	}

	/**
	 * @param txtPrecioVenta the txtPrecioVenta to set
	 */
	public void setTxtPrecioVenta(JTextField txtPrecioVenta) {
		this.txtPrecioVenta = txtPrecioVenta;
	}

	/**
	 * @return the txtStock
	 */
	public JTextField getTxtStock() {
		return txtStock;
	}

	/**
	 * @param txtStock the txtStock to set
	 */
	public void setTxtStock(JTextField txtStock) {
		this.txtStock = txtStock;
	}

	/**
	 * @return the txtStockMinimo
	 */
	public JTextField getTxtStockMinimo() {
		return txtStockMinimo;
	}

	/**
	 * @param txtStockMinimo the txtStockMinimo to set
	 */
	public void setTxtStockMinimo(JTextField txtStockMinimo) {
		this.txtStockMinimo = txtStockMinimo;
	}

	/**
	 * @return the comboProveedor
	 */
	public JComboBox<String> getComboProveedor() {
		return comboProveedor;
	}

	/**
	 * @param comboProveedor the comboProveedor to set
	 */
	public void setComboProveedor(JComboBox<String> comboProveedor) {
		this.comboProveedor = comboProveedor;
	}

	/**
	 * @return the txtId
	 */
	public JTextField getTxtId() {
		return txtId;
	}

	/**
	 * @param txtId the txtId to set
	 */
	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JDateChooser getDateChooserCompra() {
		return dateChooserCompra;
	}

	public void setDateChooserCompra(JDateChooser dateChooserCompra) {
		this.dateChooserCompra = dateChooserCompra;
	}

	public JDateChooser getDateChooserVencimiento() {
		return dateChooserVencimiento;
	}

	public void setDateChooserVencimiento(JDateChooser dateChooserVencimiento) {
		this.dateChooserVencimiento = dateChooserVencimiento;
	}

	public JTextField getTxtMonto() {
		return txtMonto;
	}

	public void setTxtMonto(JTextField txtMonto) {
		this.txtMonto = txtMonto;
	}

	public JTextField getTxtNotas() {
		return txtNotas;
	}

	public void setTxtNotas(JTextField txtNotas) {
		this.txtNotas = txtNotas;
	}

	public JComboBox<String> getComboConcepto() {
		return comboConcepto;
	}

	public void setComboConcepto(JComboBox<String> comboConcepto) {
		this.comboConcepto = comboConcepto;
	}

	public JTextArea getTxtAreaNotas() {
		return txtAreaNotas;
	}

	public void setTxtAreaNotas(JTextArea txtAreaNotas) {
		this.txtAreaNotas = txtAreaNotas;
	}

	public JScrollPane getScrollNotas() {
		return scrollNotas;
	}

	public void setScrollNotas(JScrollPane scrollNotas) {
		this.scrollNotas = scrollNotas;
	}

	public JComboBox<String> getComboProducto() {
		return comboProducto;
	}

	public void setComboProducto(JComboBox<String> comboProducto) {
		this.comboProducto = comboProducto;
	}

}
