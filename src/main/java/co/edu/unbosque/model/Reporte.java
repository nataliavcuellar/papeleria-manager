package co.edu.unbosque.model;

import java.time.LocalDate;

import co.edu.unbosque.util.enums.TipoReporte;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reporte")
public class Reporte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reporte")
	private int idReporte;

	@Column(name = "tipo")
	private TipoReporte tipo;

	@Column(name = "fecha_generacion")
	private LocalDate fechaGeneracion;

	@Column(name = "fecha_incio")
	private LocalDate fechaInicio;

	@Column(name = "fecha_fin")
	private LocalDate fechaFin;

	@Column(name = "rutaPdf")
	private String rutaPdf;

	public Reporte() {
		// TODO Auto-generated constructor stub
	}

	public Reporte(int idReporte, TipoReporte tipo, LocalDate fechaGeneracion, LocalDate fechaInicio,
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

	public void setFechaInicio(LocalDate fecha_inicio) {
		this.fechaInicio = fecha_inicio;
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
