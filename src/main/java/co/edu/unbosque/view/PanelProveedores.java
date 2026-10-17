package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class PanelProveedores extends JPanel {

	private JLabel lblTitulo, lblHora, lblSuperior;
	private JButton btnAgregar, btnActualizar, btnEliminar;
	private JTable tablaProveedores;
	private JScrollPane scrollProveedores;

	public PanelProveedores() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(168, 45, 1012, 610);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setVisible(false);
		run();
	}

	public void run() {
		lblTitulo = new JLabel("[PROVEEDORES] Gestión de Proveedores");
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

		btnAgregar = new JButton("+ Nuevo Proveedor");
		btnAgregar.setBounds(130, 50, 250, 45);
		btnAgregar.setBackground(new Color(26, 158, 145));
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnAgregar.setFocusPainted(false);
		btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnAgregar);

		btnActualizar = new JButton("Editar Proveedor");
		btnActualizar.setBounds(400, 50, 250, 45);
		btnActualizar.setBackground(new Color(72, 124, 154));
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnActualizar.setFocusPainted(false);
		btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnActualizar);

		btnEliminar = new JButton("Eliminar Proveedor");
		btnEliminar.setBounds(670, 50, 250, 45);
		btnEliminar.setBackground(new Color(220, 53, 69));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnEliminar.setFocusPainted(false);
		btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnEliminar);

		String[] columnas = { "ID", "Nombre", "Apellido", "Correo", "Teléfono", "Tiempo de entrega" };
		DefaultTableModel modeloProveedores = new DefaultTableModel(null, columnas);
		tablaProveedores = new JTable(modeloProveedores);
		scrollProveedores = new JScrollPane(tablaProveedores);
		scrollProveedores.setBounds(20, 110, 970, 420);
		add(scrollProveedores);

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
	 * @return the tablaProveedores
	 */
	public JTable getTablaProveedores() {
		return tablaProveedores;
	}

	/**
	 * @param tablaProveedores the tablaProveedores to set
	 */
	public void setTablaProveedores(JTable tablaProveedores) {
		this.tablaProveedores = tablaProveedores;
	}

	/**
	 * @return the scrollProveedores
	 */
	public JScrollPane getScrollProveedores() {
		return scrollProveedores;
	}

	/**
	 * @param scrollProveedores the scrollProveedores to set
	 */
	public void setScrollProveedores(JScrollPane scrollProveedores) {
		this.scrollProveedores = scrollProveedores;
	}

}
