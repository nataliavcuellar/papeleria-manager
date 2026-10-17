package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class VentanaPrincipal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelUsuario jpnUsuario;
	private PanelMenuPrincipal jpnMenuPrincipal;
	private PanelSuperior jpnSuperior;
	private PanelVentas jpnVentas;
	private PanelInventario jpnInventario;
	private PanelClientes jpnClientes;
	private PanelProveedores jpnProveedores;
	private PanelCuentas jpnCuentas;
	private PanelReportes jpnReportes;

	public VentanaPrincipal() {
		jpnUsuario = new PanelUsuario();
		jpnMenuPrincipal = new PanelMenuPrincipal();
		jpnSuperior = new PanelSuperior();
		jpnVentas = new PanelVentas();
		jpnInventario = new PanelInventario();
		jpnClientes = new PanelClientes();
		jpnProveedores = new PanelProveedores();
		jpnCuentas = new PanelCuentas();
		jpnReportes = new PanelReportes();

		setSize(1200, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Papelería Rosita - Sistema de Gestión");
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		getContentPane().setBackground(new Color(247, 230, 210));

		Image logo = Toolkit.getDefaultToolkit().getImage("imagenes/icon.png");
		setIconImage(logo);

		add(jpnUsuario);
		add(jpnMenuPrincipal);
		add(jpnSuperior);
		add(jpnVentas);
		add(jpnInventario);
		add(jpnClientes);
		add(jpnProveedores);
		add(jpnCuentas);
		add(jpnReportes);

		repaint();
	}

	/**
	 * @return the jpnInventario
	 */
	public PanelInventario getJpnInventario() {
		return jpnInventario;
	}

	/**
	 * @param jpnInventario the jpnInventario to set
	 */
	public void setJpnInventario(PanelInventario jpnInventario) {
		this.jpnInventario = jpnInventario;
	}

	/**
	 * @return the jpnClientes
	 */
	public PanelClientes getJpnClientes() {
		return jpnClientes;
	}

	/**
	 * @param jpnClientes the jpnClientes to set
	 */
	public void setJpnClientes(PanelClientes jpnClientes) {
		this.jpnClientes = jpnClientes;
	}

	/**
	 * @return the jpnProveedores
	 */
	public PanelProveedores getJpnProveedores() {
		return jpnProveedores;
	}

	/**
	 * @param jpnProveedores the jpnProveedores to set
	 */
	public void setJpnProveedores(PanelProveedores jpnProveedores) {
		this.jpnProveedores = jpnProveedores;
	}

	/**
	 * @return the jpnCuentas
	 */
	public PanelCuentas getJpnCuentas() {
		return jpnCuentas;
	}

	/**
	 * @param jpnCuentas the jpnCuentas to set
	 */
	public void setJpnCuentas(PanelCuentas jpnCuentas) {
		this.jpnCuentas = jpnCuentas;
	}

	/**
	 * @return the jpnReportes
	 */
	public PanelReportes getJpnReportes() {
		return jpnReportes;
	}

	/**
	 * @param jpnReportes the jpnReportes to set
	 */
	public void setJpnReportes(PanelReportes jpnReportes) {
		this.jpnReportes = jpnReportes;
	}

	/**
	 * @return the jpnUsuario
	 */
	public PanelUsuario getJpnUsuario() {
		return jpnUsuario;
	}

	/**
	 * @param jpnUsuario the jpnUsuario to set
	 */
	public void setJpnUsuario(PanelUsuario jpnUsuario) {
		this.jpnUsuario = jpnUsuario;
	}

	/**
	 * @return the jpnMenuPrincipal
	 */
	public PanelMenuPrincipal getJpnMenuPrincipal() {
		return jpnMenuPrincipal;
	}

	/**
	 * @param jpnMenuPrincipal the jpnMenuPrincipal to set
	 */
	public void setJpnMenuPrincipal(PanelMenuPrincipal jpnMenuPrincipal) {
		this.jpnMenuPrincipal = jpnMenuPrincipal;
	}

	/**
	 * @return the jpnSuperior
	 */
	public PanelSuperior getJpnSuperior() {
		return jpnSuperior;
	}

	/**
	 * @param jpnSuperior the jpnSuperior to set
	 */
	public void setJpnSuperior(PanelSuperior jpnSuperior) {
		this.jpnSuperior = jpnSuperior;
	}

	/**
	 * @return the jpnVentas
	 */
	public PanelVentas getJpnVentas() {
		return jpnVentas;
	}

	/**
	 * @param jpnVentas the jpnVentas to set
	 */
	public void setJpnVentas(PanelVentas jpnVentas) {
		this.jpnVentas = jpnVentas;
	}

}
