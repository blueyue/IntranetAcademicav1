package com.ppcr.sistema.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="usuario")
public class Usuario implements Serializable{
	
	private static final long serialVersionUID=1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idusuario")
	private Integer idusuario;
	@Column(name="nomusuario")
	private String nomusuario;
	@Column(name="contrasena")
	private String contrasena;
	@Column(name="rol")
	private Integer rol;
	@Column(name="estado" ,nullable=true)
	private Integer estado;
	
	@ManyToOne
	@JoinColumn(name = "rol",insertable = false, updatable = false)
	private Roles roles;

	public Usuario() {
		super();
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getNomusuario() {
		return nomusuario;
	}

	public void setNomusuario(String nomusuario) {
		this.nomusuario = nomusuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	

	public Usuario(Integer idusuario, String nomusuario, String contrasena, Integer rol, Integer estado) {
		super();
		this.idusuario = idusuario;
		this.nomusuario = nomusuario;
		this.contrasena = contrasena;
		this.rol = rol;
		this.estado = estado;
	}

	

	
	
	
	
	

}
