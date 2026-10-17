package co.edu.unbosque.model;

import java.time.LocalDate;

import co.edu.unbosque.util.enums.TipoReporte;

public class ReporteDTO {
	private int idReporte;
	private TipoReporte tipo;
	private LocalDate fechaGeneracion;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private String rutaPdf;

	public ReporteDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReporteDTO(int idReporte, TipoReporte tipo, LocalDate fechaGeneracion, LocalDate fechaInicio,
			LocalDate fechaFin, String rutaPdf) {
		super();
		this.idReporte = idReporte;
		this.tipo = tipo;
		this.fechaGeneracion = fechaGeneracion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.rutaPdf = rutaPdf;
	}

	public int getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(int idReporte) {
		this.idReporte = idReporte;
	}

	public TipoReporte getTipo() {
		return tipo;
	}

	public void setTipo(TipoReporte tipo) {
		this.tipo = tipo;
	}

	public LocalDate getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(LocalDate fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getRutaPdf() {
		return rutaPdf;
	}

	public void setRutaPdf(String rutaPdf) {
		this.rutaPdf = rutaPdf;
	}

}
