package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class PanelCuentas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblTitulo, lblHora, lblSuperior;
	private JTable tablaCobrar, tablaPagar;
	private JButton btnRegistrarCobro, btnRegistrarPago;
	private JScrollPane scrollCobrar, scrollPagar;
	private JPanel panelPorCobrar, panelPorPagar;
	private JTabbedPane pestañas;

	public PanelCuentas() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(168, 45, 1012, 610);
		setVisible(false);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		run();
	}

	public void run() {

		lblTitulo = new JLabel("[CUENTAS] Control de Cuentas");
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

		pestañas = new JTabbedPane();
		pestañas.setBounds(10, 50, 990, 540);
		pestañas.setFont(new Font("SansSerif", Font.BOLD, 13));
		pestañas.setBackground(new Color(192, 226, 226));
		add(pestañas);

		panelPorCobrar = new JPanel(null);
		panelPorCobrar.setBackground(Color.WHITE);

		btnRegistrarCobro = new JButton("Registrar Pago Recibido");
		btnRegistrarCobro.setBounds(20, 20, 250, 40);
		btnRegistrarCobro.setBackground(new Color(58, 105, 141));
		btnRegistrarCobro.setForeground(Color.WHITE);
		btnRegistrarCobro.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnRegistrarCobro.setFocusPainted(false);
		panelPorCobrar.add(btnRegistrarCobro);

		String[] colCobrar = { "Código", "Cliente", "Monto", "Fecha Venc.", "Estado" };
		DefaultTableModel modeloCobrar = new DefaultTableModel(null, colCobrar);
		tablaCobrar = new JTable(modeloCobrar);
		scrollCobrar = new JScrollPane(tablaCobrar);
		scrollCobrar.setBounds(20, 80, 940, 380);
		panelPorCobrar.add(scrollCobrar);

		pestañas.addTab("💰 Por Cobrar", panelPorCobrar);

		panelPorPagar = new JPanel(null);
		panelPorPagar.setBackground(Color.WHITE);

		btnRegistrarPago = new JButton("Registrar Pago a Proveedor");
		btnRegistrarPago.setBounds(20, 20, 250, 40);
		btnRegistrarPago.setBackground(new Color(58, 105, 141));
		btnRegistrarPago.setForeground(Color.WHITE);
		btnRegistrarPago.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnRegistrarPago.setFocusPainted(false);
		panelPorPagar.add(btnRegistrarPago);
		
		

		String[] colPagar = { "Código","Producto" , "Proveedor", "Monto", "Fecha Venc.", "Estado" };
		DefaultTableModel modeloPagar = new DefaultTableModel(null, colPagar);
		tablaPagar = new JTable(modeloPagar);
		scrollPagar = new JScrollPane(tablaPagar);
		scrollPagar.setBounds(20, 80, 940, 380);
		panelPorPagar.add(scrollPagar);

		pestañas.addTab("📦 Por Pagar", panelPorPagar);

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
	 * @return the panelPorCobrar
	 */
	public JPanel getPanelPorCobrar() {
		return panelPorCobrar;
	}

	/**
	 * @param panelPorCobrar the panelPorCobrar to set
	 */
	public void setPanelPorCobrar(JPanel panelPorCobrar) {
		this.panelPorCobrar = panelPorCobrar;
	}

	/**
	 * @return the panelPorPagar
	 */
	public JPanel getPanelPorPagar() {
		return panelPorPagar;
	}

	/**
	 * @param panelPorPagar the panelPorPagar to set
	 */
	public void setPanelPorPagar(JPanel panelPorPagar) {
		this.panelPorPagar = panelPorPagar;
	}

	/**
	 * @return the tablaCobrar
	 */
	public JTable getTablaCobrar() {
		return tablaCobrar;
	}

	/**
	 * @param tablaCobrar the tablaCobrar to set
	 */
	public void setTablaCobrar(JTable tablaCobrar) {
		this.tablaCobrar = tablaCobrar;
	}

	/**
	 * @return the tablaPagar
	 */
	public JTable getTablaPagar() {
		return tablaPagar;
	}

	/**
	 * @param tablaPagar the tablaPagar to set
	 */
	public void setTablaPagar(JTable tablaPagar) {
		this.tablaPagar = tablaPagar;
	}

	/**
	 * @return the btnRegistrarCobro
	 */
	public JButton getBtnRegistrarCobro() {
		return btnRegistrarCobro;
	}

	/**
	 * @param btnRegistrarCobro the btnRegistrarCobro to set
	 */
	public void setBtnRegistrarCobro(JButton btnRegistrarCobro) {
		this.btnRegistrarCobro = btnRegistrarCobro;
	}

	/**
	 * @return the btnRegistrarPago
	 */
	public JButton getBtnRegistrarPago() {
		return btnRegistrarPago;
	}

	/**
	 * @param btnRegistrarPago the btnRegistrarPago to set
	 */
	public void setBtnRegistrarPago(JButton btnRegistrarPago) {
		this.btnRegistrarPago = btnRegistrarPago;
	}

	/**
	 * @return the scrollCobrar
	 */
	public JScrollPane getScrollCobrar() {
		return scrollCobrar;
	}

	/**
	 * @param scrollCobrar the scrollCobrar to set
	 */
	public void setScrollCobrar(JScrollPane scrollCobrar) {
		this.scrollCobrar = scrollCobrar;
	}

	/**
	 * @return the scrollPagar
	 */
	public JScrollPane getScrollPagar() {
		return scrollPagar;
	}

	/**
	 * @param scrollPagar the scrollPagar to set
	 */
	public void setScrollPagar(JScrollPane scrollPagar) {
		this.scrollPagar = scrollPagar;
	}

	/**
	 * @return the pestañas
	 */
	public JTabbedPane getPestañas() {
		return pestañas;
	}

	/**
	 * @param pestañas the pestañas to set
	 */
	public void setPestañas(JTabbedPane pestañas) {
		this.pestañas = pestañas;
	}

}
