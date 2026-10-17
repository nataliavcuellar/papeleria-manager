package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

public class PanelReportes extends JPanel {

	private JLabel lblTitulo, lblHora, lblSuperior, lblRecordatorio, lblSubtitulo, lblTotalVentas, lblProductosVendidos,
			lblProductosVendidosValor, lblPeriodo, lblInicio, lblFin, lblTipo, lblVista, lblTotales;
	private JTable tabla;
	private JDateChooser dateInicio, dateFin;
	private JComboBox<String> comboTipoReporte;
	private JButton btnGenerar, btnImprimir, btnReporteInventario;
	private JPanel panelRecordatorio, panelGenerar, panelVista;
	private JScrollPane scroll;

	public PanelReportes() {
		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(168, 45, 1012, 610);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setVisible(false);
		run();
	}

	public void run() {
		lblTitulo = new JLabel("[REPORTES] Reportes e Impresiones");
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

		panelRecordatorio = new JPanel();
		panelRecordatorio.setLayout(null);
		panelRecordatorio.setBackground(new Color(180, 230, 180));
		panelRecordatorio.setBorder(new LineBorder(Color.BLACK, 1));
		panelRecordatorio.setBounds(15, 50, 980, 40);
		add(panelRecordatorio);

		lblRecordatorio = new JLabel("[RECORDATORIO] Recuerde imprimir el reporte de inventario cada 3 días",
				SwingConstants.CENTER);
		lblRecordatorio.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblRecordatorio.setForeground(new Color(0, 102, 0));
		lblRecordatorio.setBounds(0, 0, 980, 40);
		panelRecordatorio.add(lblRecordatorio);

		panelGenerar = new JPanel();
		panelGenerar.setLayout(null);
		panelGenerar.setBackground(new Color(230, 236, 240));
		panelGenerar.setBorder(new LineBorder(Color.BLACK, 1));
		panelGenerar.setBounds(15, 102, 980, 175);
		add(panelGenerar);

		lblSubtitulo = new JLabel("Generar Reporte Personalizado", SwingConstants.CENTER);
		lblSubtitulo.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblSubtitulo.setForeground(new Color(38, 92, 123));
		lblSubtitulo.setBounds(0, 5, 980, 20);
		panelGenerar.add(lblSubtitulo);

		lblPeriodo = new JLabel("Periodo:");
		lblPeriodo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblPeriodo.setForeground(new Color(38, 92, 123));
		lblPeriodo.setBounds(50, 40, 60, 25);
		panelGenerar.add(lblPeriodo);

		lblInicio = new JLabel("Fecha inicio:");
		lblInicio.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblInicio.setForeground(new Color(38, 92, 123));
		lblInicio.setBounds(130, 40, 90, 25);
		panelGenerar.add(lblInicio);

		dateInicio = new JDateChooser();
		dateInicio.setBounds(220, 40, 120, 25);
		dateInicio.setDateFormatString("dd/MM/yyyy");
		dateInicio.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panelGenerar.add(dateInicio);

		lblFin = new JLabel("Fecha fin:");
		lblFin.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblFin.setForeground(new Color(38, 92, 123));
		lblFin.setBounds(370, 40, 80, 25);
		panelGenerar.add(lblFin);

		dateFin = new JDateChooser();
		dateFin.setBounds(440, 40, 120, 25);
		dateFin.setDateFormatString("dd/MM/yyyy");
		dateFin.setFont(new Font("SansSerif", Font.PLAIN, 12));
		panelGenerar.add(dateFin);

		lblTipo = new JLabel("Tipo de reporte:");
		lblTipo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblTipo.setForeground(new Color(38, 92, 123));
		lblTipo.setBounds(50, 80, 110, 25);
		panelGenerar.add(lblTipo);

		comboTipoReporte = new JComboBox<>(new String[] { "General", "Ventas", "Inventario" });
		comboTipoReporte.setBounds(160, 80, 150, 25);
		panelGenerar.add(comboTipoReporte);

		btnGenerar = new JButton("Generar Reporte");
		btnGenerar.setBounds(20, 125, 180, 35);
		btnGenerar.setBackground(new Color(26, 158, 145));
		btnGenerar.setForeground(Color.WHITE);
		btnGenerar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnGenerar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnGenerar.setFocusPainted(false);
		panelGenerar.add(btnGenerar);

		btnImprimir = new JButton("Imprimir Reporte");
		btnImprimir.setBounds(230, 125, 180, 35);
		btnImprimir.setBackground(new Color(72, 124, 154));
		btnImprimir.setForeground(Color.WHITE);
		btnImprimir.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnImprimir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnImprimir.setFocusPainted(false);
		panelGenerar.add(btnImprimir);

		btnReporteInventario = new JButton("Reporte Inventario (3 días)");
		btnReporteInventario.setBounds(440, 125, 230, 35);
		btnReporteInventario.setBackground(new Color(220, 53, 69));
		btnReporteInventario.setForeground(Color.WHITE);
		btnReporteInventario.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnReporteInventario.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReporteInventario.setFocusPainted(false);
		panelGenerar.add(btnReporteInventario);

		panelVista = new JPanel();
		panelVista.setLayout(null);
		panelVista.setBackground(new Color(245, 245, 245));
		panelVista.setBorder(new LineBorder(Color.BLACK, 1));
		panelVista.setBounds(15, 290, 980, 310);
		add(panelVista);

		lblVista = new JLabel("Vista previa del reporte - Últimos 3 días", SwingConstants.CENTER);
		lblVista.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblVista.setForeground(new Color(38, 92, 123));
		lblVista.setBounds(0, 5, 980, 20);
		panelVista.add(lblVista);

		lblTotales = new JLabel("Total de ventas:", SwingConstants.RIGHT);
		lblTotales.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblTotales.setForeground(new Color(26, 158, 145));
		lblTotales.setBounds(260, 25, 120, 25);
		panelVista.add(lblTotales);

		lblTotalVentas = new JLabel("$0", SwingConstants.LEFT);
		lblTotalVentas.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTotalVentas.setForeground(new Color(26, 158, 145));
		lblTotalVentas.setBounds(385, 25, 60, 25);
		panelVista.add(lblTotalVentas);

		lblProductosVendidos = new JLabel(" |  Productos vendidos:", SwingConstants.LEFT);
		lblProductosVendidos.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblProductosVendidos.setForeground(new Color(26, 158, 145));
		lblProductosVendidos.setBounds(440, 25, 160, 25);
		panelVista.add(lblProductosVendidos);

		lblProductosVendidosValor = new JLabel("0", SwingConstants.LEFT);
		lblProductosVendidosValor.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblProductosVendidosValor.setForeground(new Color(26, 158, 145));
		lblProductosVendidosValor.setBounds(600, 25, 50, 25);
		panelVista.add(lblProductosVendidosValor);

		String[] columnas = { "ID", "fecha generación", "tipo", "ruta" };
		DefaultTableModel modelo = new DefaultTableModel(null, columnas);
		tabla = new JTable(modelo);
		scroll = new JScrollPane(tabla);
		scroll.setBounds(15, 60, 950, 230);
		panelVista.add(scroll);

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

	// Getters y Setters

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public JLabel getLblHora() {
		return lblHora;
	}

	public void setLblHora(JLabel lblHora) {
		this.lblHora = lblHora;
	}

	public JLabel getLblSuperior() {
		return lblSuperior;
	}

	public void setLblSuperior(JLabel lblSuperior) {
		this.lblSuperior = lblSuperior;
	}

	public JLabel getLblRecordatorio() {
		return lblRecordatorio;
	}

	public void setLblRecordatorio(JLabel lblRecordatorio) {
		this.lblRecordatorio = lblRecordatorio;
	}

	public JLabel getLblSubtitulo() {
		return lblSubtitulo;
	}

	public void setLblSubtitulo(JLabel lblSubtitulo) {
		this.lblSubtitulo = lblSubtitulo;
	}

	public JLabel getLblTotalVentas() {
		return lblTotalVentas;
	}

	public void setLblTotalVentas(JLabel lblTotalVentas) {
		this.lblTotalVentas = lblTotalVentas;
	}

	public JLabel getLblProductosVendidos() {
		return lblProductosVendidos;
	}

	public void setLblProductosVendidos(JLabel lblProductosVendidos) {
		this.lblProductosVendidos = lblProductosVendidos;
	}

	public JLabel getLblProductosVendidosValor() {
		return lblProductosVendidosValor;
	}

	public void setLblProductosVendidosValor(JLabel lblProductosVendidosValor) {
		this.lblProductosVendidosValor = lblProductosVendidosValor;
	}

	public JLabel getLblPeriodo() {
		return lblPeriodo;
	}

	public void setLblPeriodo(JLabel lblPeriodo) {
		this.lblPeriodo = lblPeriodo;
	}

	public JLabel getLblInicio() {
		return lblInicio;
	}

	public void setLblInicio(JLabel lblInicio) {
		this.lblInicio = lblInicio;
	}

	public JLabel getLblFin() {
		return lblFin;
	}

	public void setLblFin(JLabel lblFin) {
		this.lblFin = lblFin;
	}

	public JLabel getLblTipo() {
		return lblTipo;
	}

	public void setLblTipo(JLabel lblTipo) {
		this.lblTipo = lblTipo;
	}

	public JLabel getLblVista() {
		return lblVista;
	}

	public void setLblVista(JLabel lblVista) {
		this.lblVista = lblVista;
	}

	public JLabel getLblTotales() {
		return lblTotales;
	}

	public void setLblTotales(JLabel lblTotales) {
		this.lblTotales = lblTotales;
	}

	public JTable getTabla() {
		return tabla;
	}

	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}

	public JDateChooser getDateInicio() {
		return dateInicio;
	}

	public void setDateInicio(JDateChooser dateInicio) {
		this.dateInicio = dateInicio;
	}

	public JDateChooser getDateFin() {
		return dateFin;
	}

	public void setDateFin(JDateChooser dateFin) {
		this.dateFin = dateFin;
	}

	public JComboBox<String> getComboTipoReporte() {
		return comboTipoReporte;
	}

	public void setComboTipoReporte(JComboBox<String> comboTipoReporte) {
		this.comboTipoReporte = comboTipoReporte;
	}

	public JButton getBtnGenerar() {
		return btnGenerar;
	}

	public void setBtnGenerar(JButton btnGenerar) {
		this.btnGenerar = btnGenerar;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(JButton btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	public JButton getBtnReporteInventario() {
		return btnReporteInventario;
	}

	public void setBtnReporteInventario(JButton btnReporteInventario) {
		this.btnReporteInventario = btnReporteInventario;
	}

	public JPanel getPanelRecordatorio() {
		return panelRecordatorio;
	}

	public void setPanelRecordatorio(JPanel panelRecordatorio) {
		this.panelRecordatorio = panelRecordatorio;
	}

	public JPanel getPanelGenerar() {
		return panelGenerar;
	}

	public void setPanelGenerar(JPanel panelGenerar) {
		this.panelGenerar = panelGenerar;
	}

	public JPanel getPanelVista() {
		return panelVista;
	}

	public void setPanelVista(JPanel panelVista) {
		this.panelVista = panelVista;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

}