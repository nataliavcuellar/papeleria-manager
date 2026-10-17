package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class PanelInventario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblTitulo, lblHora, lblSuperior, lblAlerta;
	private JButton btnAgregar, btnActualizar, btnAlertas;
	private JTable tablaInventario;
	private JScrollPane scrollInventario;

	public PanelInventario() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(168, 45, 1012, 610);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setVisible(false);
		run();
	}

	public void run() {

		lblTitulo = new JLabel("[INVENTARIO] Control de Inventario");
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

		btnAgregar = new JButton("+ Agregar Producto");
		btnAgregar.setBounds(130, 50, 250, 45);
		btnAgregar.setBackground(new Color(26, 158, 145));
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnAgregar.setFocusPainted(false);
		btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnAgregar);

		btnActualizar = new JButton("Actualizar Producto");
		btnActualizar.setBounds(400, 50, 250, 45);
		btnActualizar.setBackground(new Color(72, 124, 154));
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnActualizar.setFocusPainted(false);
		btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnActualizar);

		btnAlertas = new JButton("Ver Alertas");
		btnAlertas.setBounds(670, 50, 250, 45);
		btnAlertas.setBackground(new Color(220, 53, 69));
		btnAlertas.setForeground(Color.WHITE);
		btnAlertas.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnAlertas.setFocusPainted(false);
		btnAlertas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnAlertas);

		String[] columnas = { "ID", "Código", "Nombre", "Precio de compra", "Precio de venta", "Proveedor", "Stock", "Stock minimo" };
		DefaultTableModel modeloInventario = new DefaultTableModel(null, columnas);
		tablaInventario = new JTable(modeloInventario);
		scrollInventario = new JScrollPane(tablaInventario);
		scrollInventario.setBounds(20, 110, 970, 420);
		add(scrollInventario);

		lblAlerta = new JLabel("ATENCIÓN: algunos productos tienen stock bajo", SwingConstants.CENTER);
		lblAlerta.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblAlerta.setForeground(new Color(153, 51, 0));
		lblAlerta.setOpaque(true);
		lblAlerta.setBackground(new Color(255, 230, 180));
		lblAlerta.setBorder(new LineBorder(new Color(200, 150, 100)));
		lblAlerta.setBounds(190, 540, 630, 35);
		lblAlerta.setVisible(false);
		add(lblAlerta);

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

	public void mostrarAlerta(boolean hayStockBajo) {
		lblAlerta.setVisible(hayStockBajo);
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
	 * @return the lblAlerta
	 */
	public JLabel getLblAlerta() {
		return lblAlerta;
	}

	/**
	 * @param lblAlerta the lblAlerta to set
	 */
	public void setLblAlerta(JLabel lblAlerta) {
		this.lblAlerta = lblAlerta;
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
	 * @return the btnActualizar
	 */
	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	/**
	 * @param btnActualizar the btnActualizar to set
	 */
	public void setBtnActualizar(JButton btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

	/**
	 * @return the btnAlertas
	 */
	public JButton getBtnAlertas() {
		return btnAlertas;
	}

	/**
	 * @param btnAlertas the btnAlertas to set
	 */
	public void setBtnAlertas(JButton btnAlertas) {
		this.btnAlertas = btnAlertas;
	}

	/**
	 * @return the tablaInventario
	 */
	public JTable getTablaInventario() {
		return tablaInventario;
	}

	/**
	 * @param tablaInventario the tablaInventario to set
	 */
	public void setTablaInventario(JTable tablaInventario) {
		this.tablaInventario = tablaInventario;
	}

	/**
	 * @return the scrollInventario
	 */
	public JScrollPane getScrollInventario() {
		return scrollInventario;
	}

	/**
	 * @param scrollInventario the scrollInventario to set
	 */
	public void setScrollInventario(JScrollPane scrollInventario) {
		this.scrollInventario = scrollInventario;
	}

}
