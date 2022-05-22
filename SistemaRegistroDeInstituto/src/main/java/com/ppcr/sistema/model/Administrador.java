package com.ppcr.sistema.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="administrador")
public class Administrador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idadmin;
	private String codadmin;
	private String admin_nom;
	private String idusuario;
	
	@ManyToOne
	@JoinColumn(name="idusuario", insertable=false , updatable=false)
	private Usuario usuario;
	
	public Administrador() {
		super();
	}
	public Integer getIdadmin() {
		return idadmin;
	}
	public void setIdadmin(Integer idadmin) {
		this.idadmin = idadmin;
	}
	public String getCodadmin() {
		return codadmin;
	}
	public void setCodadmin(String codadmin) {
		this.codadmin = codadmin;
	}
	public String getAdmin_nom() {
		return admin_nom;
	}
	public void setAdmin_nom(String admin_nom) {
		this.admin_nom = admin_nom;
	}
	public String getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}
	public Administrador(Integer idadmin, String codadmin, String admin_nom, String idusuario) {
		super();
		this.idadmin = idadmin;
		this.codadmin = codadmin;
		this.admin_nom = admin_nom;
		this.idusuario = idusuario;
	}
	
	

	
}
	