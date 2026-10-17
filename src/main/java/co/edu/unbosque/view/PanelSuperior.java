package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelSuperior extends JPanel {

	private JLabel lblTitulo, lblTituloUsuario, lblUsuario;
	private JButton btnCerrarSesion;

	public PanelSuperior() {
		setLayout(null);
		setBackground(new Color(72, 124, 154));
		setBounds(0, 0, 1200, 40);
		setVisible(false);
		run();
	}

	public void run() {
		lblTitulo = new JLabel("[TIENDA] Papelería Rosita", SwingConstants.LEFT);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(15, 5, 400, 30);
		add(lblTitulo);

		btnCerrarSesion = new JButton("Cerrar Sesión");
		btnCerrarSesion.setBounds(1040, 5, 120, 30);
		btnCerrarSesion.setBackground(new Color(200, 50, 50));
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnCerrarSesion.setFocusPainted(false);
		btnCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnCerrarSesion);

		lblTituloUsuario = new JLabel("Usuario: ");
		lblTituloUsuario.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblTituloUsuario.setForeground(Color.WHITE);
		lblTituloUsuario.setBounds(830, 5, 55, 30);
		add(lblTituloUsuario);
		
		lblUsuario = new JLabel();
		lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setBounds(882, 5, 60, 30);
		add(lblUsuario);
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
	 * @return the btnCerrarSesion
	 */
	public JButton getBtnCerrarSesion() {
		return btnCerrarSesion;
	}

	/**
	 * @param btnCerrarSesion the btnCerrarSesion to set
	 */
	public void setBtnCerrarSesion(JButton btnCerrarSesion) {
		this.btnCerrarSesion = btnCerrarSesion;
	}

	/**
	 * @return the lblTituloUsuario
	 */
	public JLabel getLblTituloUsuario() {
		return lblTituloUsuario;
	}

	/**
	 * @param lblTituloUsuario the lblTituloUsuario to set
	 */
	public void setLblTituloUsuario(JLabel lblTituloUsuario) {
		this.lblTituloUsuario = lblTituloUsuario;
	}

}