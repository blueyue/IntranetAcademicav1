package com.ppcr.sistema.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="boleta")
public class Boleta {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idboleta;
	private Integer idservicio;
	private String descripcion;
	private Date fecha;
	private Integer estado;
	
	@OneToOne
	@JoinColumn(name="idservicio", insertable = false, updatable = false)
	private TarifaServicio tarifaServicio;
	
	
	public Boleta() {
		super();
	}
	public Integer getIdboleta() {
		return idboleta;
	}
	public void setIdboleta(Integer idboleta) {
		this.idboleta = idboleta;
	}
	
	public Integer getIdservicio() {
		return idservicio;
	}
	public void setIdservicio(Integer idservicio) {
		this.idservicio = idservicio;
	}
	public TarifaServicio getTarifaServicio() {
		return tarifaServicio;
	}
	public void setTarifaServicio(TarifaServicio tarifaServicio) {
		this.tarifaServicio = tarifaServicio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Boleta(Integer idboleta, Integer idservicio, String descripcion, Date fecha, Integer estado,
			TarifaServicio tarifaServicio) {
		super();
		this.idboleta = idboleta;
		this.idservicio = idservicio;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.estado = estado;
		this.tarifaServicio = tarifaServicio;
	}

	
	


	
	
	
}
