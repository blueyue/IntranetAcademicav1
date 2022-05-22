package com.ppcr.sistema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Roles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idrol;
	private String descripcion ;
	//falta estado (prueba)
	public Integer getIdrol() {
		return idrol;
	}
	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Roles(Integer idrol, String descripcion) {
		super();
		this.idrol = idrol;
		this.descripcion = descripcion;
	}
	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
