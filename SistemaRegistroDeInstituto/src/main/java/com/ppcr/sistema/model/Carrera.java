package com.ppcr.sistema.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="carrera")
public class Carrera  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcarrera;
	private String nomcarrera;
	private Integer estado;
	
	public Carrera() {
		super();
	}
	public Carrera(Integer idcarrera, String nomcarrera) {
		super();
		this.idcarrera = idcarrera;
		this.nomcarrera = nomcarrera;
	}
	
	public Integer getIdcarrera() {
		return idcarrera;
	}
	public void setIdcarrera(Integer idcarrera) {
		this.idcarrera = idcarrera;
	}
	public String getNomcarrera() {
		return nomcarrera;
	}
	public void setNomcarrera(String nomcarrera) {
		this.nomcarrera = nomcarrera;
	}
	@Override
	public String toString() {
		return "Carrera [idcarrera=" + idcarrera + ", nomcarrera=" + nomcarrera + "]";
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
}
