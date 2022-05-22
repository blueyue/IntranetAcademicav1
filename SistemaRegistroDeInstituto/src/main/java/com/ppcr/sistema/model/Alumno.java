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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="alumno")
public class Alumno implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idalumno;
	private String codalum;
	private String alum_nom ;
	private String alum_ape ;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String alum_nac ;
	private String alum_dir;
	private Integer alum_telf;
	@Column(nullable=true)
	private Integer idusuario;
	
	@ManyToOne
	@JoinColumn(name="idusuario", insertable=false , updatable=false)
	private Usuario usuario;

	
	
	public Alumno() {
		super();
	}

	public Long getIdalumno() {
		return idalumno;
	}

	public void setIdalumno(Long idalumno) {
		this.idalumno = idalumno;
	}

	public String getCodalum() {
		return codalum;
	}

	public void setCodalum(String codalum) {
		this.codalum = codalum;
	}

	public String getAlum_nom() {
		return alum_nom;
	}

	public void setAlum_nom(String alum_nom) {
		this.alum_nom = alum_nom;
	}

	public String getAlum_ape() {
		return alum_ape;
	}

	public void setAlum_ape(String alum_ape) {
		this.alum_ape = alum_ape;
	}

	public String getAlum_nac() {
		return alum_nac;
	}

	public void setAlum_nac(String alum_nac) {
		this.alum_nac = alum_nac;
	}

	public String getAlum_dir() {
		return alum_dir;
	}

	public void setAlum_dir(String alum_dir) {
		this.alum_dir = alum_dir;
	}

	public Integer getAlum_telf() {
		return alum_telf;
	}

	public void setAlum_telf(Integer alum_telf) {
		this.alum_telf = alum_telf;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Alumno(Long idalumno, String codalum, String alum_nom, String alum_ape, String alum_nac, String alum_dir,
			Integer alum_telf, Integer idusuario, Usuario usuario) {
		super();
		this.idalumno = idalumno;
		this.codalum = codalum;
		this.alum_nom = alum_nom;
		this.alum_ape = alum_ape;
		this.alum_nac = alum_nac;
		this.alum_dir = alum_dir;
		this.alum_telf = alum_telf;
		this.idusuario = idusuario;
		this.usuario = usuario;
	}

	
	
}
