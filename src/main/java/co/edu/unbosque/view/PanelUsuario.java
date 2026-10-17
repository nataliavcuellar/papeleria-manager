package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelUsuario extends JPanel {

	private JLabel lblTitulo, lblSubtitulo, lblSistema, lblConexion;
	private JLabel lblUsuario, lblContrasena, lblVersion;
	private JTextField txtUsuario;
	private JPasswordField txtContrasena;
	private JButton btnIniciar;
	private Color colorAzul;

	public PanelUsuario() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(425, 130, 350, 400);

		colorAzul = new Color(38, 92, 123);
		lblTitulo = new JLabel("TIENDA", SwingConstants.CENTER);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblTitulo.setForeground(colorAzul);
		lblTitulo.setBounds(0, 30, 350, 40);
		add(lblTitulo);

		lblSubtitulo = new JLabel("Papelería Rosita", SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSubtitulo.setForeground(colorAzul);
		lblSubtitulo.setBounds(0, 70, 350, 30);
		add(lblSubtitulo);

		lblSistema = new JLabel("Sistema de Gestión Local", SwingConstants.CENTER);
		lblSistema.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblSistema.setBounds(0, 110, 350, 25);
		add(lblSistema);

		lblConexion = new JLabel("+ Sin conexión a internet", SwingConstants.CENTER);
		lblConexion.setFont(new Font("SansSerif", Font.ITALIC, 11));
		lblConexion.setForeground(Color.GRAY);
		lblConexion.setBounds(0, 130, 350, 25);
		add(lblConexion);

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(colorAzul);
		lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblUsuario.setBounds(50, 170, 250, 25);
		add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(50, 195, 250, 30);
		txtUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(txtUsuario);

		lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblContrasena.setForeground(colorAzul);
		lblContrasena.setBounds(50, 235, 250, 25);
		add(lblContrasena);

		txtContrasena = new JPasswordField();
		txtContrasena.setBounds(50, 260, 250, 30);
		txtContrasena.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(txtContrasena);

		btnIniciar = new JButton("Iniciar Sesión");
		btnIniciar.setBounds(50, 310, 250, 35);
		btnIniciar.setBackground(new Color(70, 120, 150));
		btnIniciar.setForeground(Color.WHITE);
		btnIniciar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnIniciar.setFocusPainted(false);
		btnIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnIniciar);

		lblVersion = new JLabel("Versión 1.0 - Sistema Offline", SwingConstants.CENTER);
		lblVersion.setFont(new Font("SansSerif", Font.PLAIN, 10));
		lblVersion.setForeground(Color.GRAY);
		lblVersion.setBounds(0, 350, 350, 25);
		add(lblVersion);

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
	 * @return the lblSubtitulo
	 */
	public JLabel getLblSubtitulo() {
		return lblSubtitulo;
	}

	/**
	 * @param lblSubtitulo the lblSubtitulo to set
	 */
	public void setLblSubtitulo(JLabel lblSubtitulo) {
		this.lblSubtitulo = lblSubtitulo;
	}

	/**
	 * @return the lblSistema
	 */
	public JLabel getLblSistema() {
		return lblSistema;
	}

	/**
	 * @param lblSistema the lblSistema to set
	 */
	public void setLblSistema(JLabel lblSistema) {
		this.lblSistema = lblSistema;
	}

	/**
	 * @return the lblConexion
	 */
	public JLabel getLblConexion() {
		return lblConexion;
	}

	/**
	 * @param lblConexion the lblConexion to set
	 */
	public void setLblConexion(JLabel lblConexion) {
		this.lblConexion = lblConexion;
	}

	/**
	 * @return the lblUsuario
	 */
	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	/**
	 * @param lblUsuario the lblUsuario to set
	 */
	public void setLblUsuario(JLabel lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	/**
	 * @return the lblContrasena
	 */
	public JLabel getLblContrasena() {
		return lblContrasena;
	}

	/**
	 * @param lblContrasena the lblContrasena to set
	 */
	public void setLblContrasena(JLabel lblContrasena) {
		this.lblContrasena = lblContrasena;
	}

	/**
	 * @return the lblVersion
	 */
	public JLabel getLblVersion() {
		return lblVersion;
	}

	/**
	 * @param lblVersion the lblVersion to set
	 */
	public void setLblVersion(JLabel lblVersion) {
		this.lblVersion = lblVersion;
	}

	/**
	 * @return the txtUsuario
	 */
	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	/**
	 * @param txtUsuario the txtUsuario to set
	 */
	public void setTxtUsuario(JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	/**
	 * @return the txtContrasena
	 */
	public JPasswordField getTxtContrasena() {
		return txtContrasena;
	}

	/**
	 * @param txtContrasena the txtContrasena to set
	 */
	public void setTxtContrasena(JPasswordField txtContrasena) {
		this.txtContrasena = txtContrasena;
	}

	/**
	 * @return the btnIniciar
	 */
	public JButton getBtnIniciar() {
		return btnIniciar;
	}

	/**
	 * @param btnIniciar the btnIniciar to set
	 */
	public void setBtnIniciar(JButton btnIniciar) {
		this.btnIniciar = btnIniciar;
	}

	/**
	 * @return the colorAzul
	 */
	public Color getColorAzul() {
		return colorAzul;
	}

	/**
	 * @param colorAzul the colorAzul to set
	 */
	public void setColorAzul(Color colorAzul) {
		this.colorAzul = colorAzul;
	}

}