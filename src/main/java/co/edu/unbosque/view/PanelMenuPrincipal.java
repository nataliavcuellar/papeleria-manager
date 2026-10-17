package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelMenuPrincipal extends JPanel {

	private JLabel lblTituloMenu;
	private JButton btnVentas, btnInventario, btnClientes, btnProveedores, btnCuentas, btnReportes;

	public PanelMenuPrincipal() {
		setLayout(null);
		setBackground(new Color(230, 236, 240));
		setBounds(5, 45, 158, 610);
		setVisible(false);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		run();

	}

	public void run() {
		btnVentas = new JButton("VENTAS");
		btnInventario = new JButton("INVENTARIO");
		btnClientes = new JButton("CLIENTES");
		btnProveedores = new JButton("PROVEEDORES");
		btnCuentas = new JButton("CUENTAS");
		btnReportes = new JButton("REPORTES");

		lblTituloMenu = new JLabel("MENÚ PRINCIPAL", SwingConstants.CENTER);
		lblTituloMenu.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblTituloMenu.setForeground(Color.BLACK);
		lblTituloMenu.setBounds(0, 10, 160, 25);
		add(lblTituloMenu);

		JButton[] botones = { btnVentas, btnInventario, btnClientes, btnProveedores, btnCuentas, btnReportes };
		int y = 50;

		for (JButton b : botones) {
			b.setBounds(5, y, 148, 40);
			b.setBackground(new Color(58, 105, 141));
			b.setForeground(Color.WHITE);
			b.setFont(new Font("SansSerif", Font.BOLD, 13));
			b.setFocusPainted(false);
			b.setCursor(new Cursor(Cursor.HAND_CURSOR));
			add(b);
			y += 60;
		}
	}

	/**
	 * @return the lblTituloMenu
	 */
	public JLabel getLblTituloMenu() {
		return lblTituloMenu;
	}

	/**
	 * @param lblTituloMenu the lblTituloMenu to set
	 */
	public void setLblTituloMenu(JLabel lblTituloMenu) {
		this.lblTituloMenu = lblTituloMenu;
	}

	/**
	 * @return the btnVentas
	 */
	public JButton getBtnVentas() {
		return btnVentas;
	}

	/**
	 * @param btnVentas the btnVentas to set
	 */
	public void setBtnVentas(JButton btnVentas) {
		this.btnVentas = btnVentas;
	}

	/**
	 * @return the btnInventario
	 */
	public JButton getBtnInventario() {
		return btnInventario;
	}

	/**
	 * @param btnInventario the btnInventario to set
	 */
	public void setBtnInventario(JButton btnInventario) {
		this.btnInventario = btnInventario;
	}

	/**
	 * @return the btnClientes
	 */
	public JButton getBtnClientes() {
		return btnClientes;
	}

	/**
	 * @param btnClientes the btnClientes to set
	 */
	public void setBtnClientes(JButton btnClientes) {
		this.btnClientes = btnClientes;
	}

	/**
	 * @return the btnProveedores
	 */
	public JButton getBtnProveedores() {
		return btnProveedores;
	}

	/**
	 * @param btnProveedores the btnProveedores to set
	 */
	public void setBtnProveedores(JButton btnProveedores) {
		this.btnProveedores = btnProveedores;
	}

	/**
	 * @return the btnCuentas
	 */
	public JButton getBtnCuentas() {
		return btnCuentas;
	}

	/**
	 * @param btnCuentas the btnCuentas to set
	 */
	public void setBtnCuentas(JButton btnCuentas) {
		this.btnCuentas = btnCuentas;
	}

	/**
	 * @return the btnReportes
	 */
	public JButton getBtnReportes() {
		return btnReportes;
	}

	/**
	 * @param btnReportes the btnReportes to set
	 */
	public void setBtnReportes(JButton btnReportes) {
		this.btnReportes = btnReportes;
	}

}