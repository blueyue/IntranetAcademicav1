package com.ppcr.sistema.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tarifaservicio")
public class TarifaServicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idservicio;
	private String descripcion;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	private double monto;
	
	
	public TarifaServicio() {
		super();
	}
	
	public int getIdservicio() {
		return idservicio;
	}

	public void setIdservicio(int idservicio) {
		this.idservicio = idservicio;
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
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}

	public TarifaServicio(int idservicio, String descripcion, Date fecha, double monto) {
		super();
		this.idservicio = idservicio;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.monto = monto;
	}
	
	

}
