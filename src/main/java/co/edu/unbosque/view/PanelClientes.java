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

public class PanelClientes extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblTitulo, lblHora, lblSuperior;
	private JButton btnAgregar, btnActualizar, btnEliminar;
	private JTable tablaClientes;
	private JScrollPane scrollClientes;

	public PanelClientes() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(168, 45, 1012, 610);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setVisible(false);
		run();
	}

	public void run() {

		lblTitulo = new JLabel("[CLIENTES] Gestión de Clientes");
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

		btnAgregar = new JButton("+ Nuevo Cliente");
		btnAgregar.setBounds(130, 50, 250, 45);
		btnAgregar.setBackground(new Color(26, 158, 145));
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnAgregar.setFocusPainted(false);
		btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnAgregar);

		btnActualizar = new JButton("Editar Cliente");
		btnActualizar.setBounds(400, 50, 250, 45);
		btnActualizar.setBackground(new Color(72, 124, 154));
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnActualizar.setFocusPainted(false);
		btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnActualizar);

		btnEliminar = new JButton("Eliminar Cliente");
		btnEliminar.setBounds(670, 50, 250, 45);
		btnEliminar.setBackground(new Color(220, 53, 69));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnEliminar.setFocusPainted(false);
		btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnEliminar);

		String[] columnas = { "ID", "Cédula", "Nombre", "Apellido",  "Teléfono", "Dirección" };
		DefaultTableModel modeloClientes = new DefaultTableModel(null, columnas);
		tablaClientes = new JTable(modeloClientes);
		scrollClientes = new JScrollPane(tablaClientes);
		scrollClientes.setBounds(20, 110, 970, 420);
		add(scrollClientes);

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
	 * @return the tablaClientes
	 */
	public JTable getTablaClientes() {
		return tablaClientes;
	}

	/**
	 * @param tablaClientes the tablaClientes to set
	 */
	public void setTablaClientes(JTable tablaClientes) {
		this.tablaClientes = tablaClientes;
	}

	/**
	 * @return the scrollClientes
	 */
	public JScrollPane getScrollClientes() {
		return scrollClientes;
	}

	/**
	 * @param scrollClientes the scrollClientes to set
	 */
	public void setScrollClientes(JScrollPane scrollClientes) {
		this.scrollClientes = scrollClientes;
	}

}
