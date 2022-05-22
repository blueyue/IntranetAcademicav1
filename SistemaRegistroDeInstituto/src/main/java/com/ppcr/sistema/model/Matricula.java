package com.ppcr.sistema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="matricula")
public class Matricula {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer nro_matricula;
	private Integer idcarrera; 
	private Integer iddet;
	private Integer idalumno;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String  fecha_matricula;
	private Integer estado;
	private Integer idboleta;
	
	@OneToOne
	@JoinColumn(name="idboleta", insertable = false, updatable = false)
	private Boleta boleta;
	
	@OneToOne
	@JoinColumn(name="idcarrera", insertable = false, updatable = false)
	private Carrera carrera;
	
	@OneToOne
	@JoinColumn(name="iddet", insertable = false, updatable = false)
	private  DetalleCurso detallecurso;
	
	@OneToOne
	@JoinColumn(name="idalumno", insertable = false, updatable = false)
	private Alumno alumno;
	
	
	public Integer getNro_matricula() {
		return nro_matricula;
	}
	public void setNro_matricula(Integer nro_matricula) {
		this.nro_matricula = nro_matricula;
	}
	public Integer getIdcarrera() {
		return idcarrera;
	}
	public void setIdcarrera(Integer idcarrera) {
		this.idcarrera = idcarrera;
	}
	public Integer getIddet() {
		return iddet;
	}
	public void setIddet(Integer iddet) {
		this.iddet = iddet;
	}
	public Integer getIdalumno() {
		return idalumno;
	}
	public void setIdalumno(Integer idalumno) {
		this.idalumno = idalumno;
	}
	public String getFecha_matricula() {
		return fecha_matricula;
	}
	public void setFecha_matricula(String fecha_matricula) {
		this.fecha_matricula = fecha_matricula;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Integer getIdboleta() {
		return idboleta;
	}
	public void setIdboleta(Integer idboleta) {
		this.idboleta = idboleta;
	}
	public Boleta getBoleta() {
		return boleta;
	}
	public void setBoleta(Boleta boleta) {
		this.boleta = boleta;
	}
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	public DetalleCurso getDetallecurso() {
		return detallecurso;
	}
	public void setDetallecurso(DetalleCurso detallecurso) {
		this.detallecurso = detallecurso;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	
	
}
