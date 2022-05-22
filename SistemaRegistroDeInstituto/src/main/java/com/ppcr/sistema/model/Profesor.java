package com.ppcr.sistema.model;

import java.util.Date;

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
@Table(name="profesor")
public class Profesor {
	
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Integer idprofesor;
	private String codprof;
    private String prof_nom; 
    private String prof_ape;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String prof_nac;
    private String prof_dir;
    private Integer prof_telf; 
    private String prof_especialidad;
    @Column(nullable=true)
    private Integer idusuario;
	
	@ManyToOne
	@JoinColumn(name="idusuario", insertable=false , updatable=false)
	private Usuario usuario;
	
	public Profesor() {
		super();
	}

	public Profesor(Integer idprofesor, String codprof, String prof_nom, String prof_ape, String prof_nac,
			String prof_dir, Integer prof_telf, String prof_especialidad, Integer idusuario, Usuario usuario) {
		super();
		this.idprofesor = idprofesor;
		this.codprof = codprof;
		this.prof_nom = prof_nom;
		this.prof_ape = prof_ape;
		this.prof_nac = prof_nac;
		this.prof_dir = prof_dir;
		this.prof_telf = prof_telf;
		this.prof_especialidad = prof_especialidad;
		this.idusuario = idusuario;
		this.usuario = usuario;
	}

	public Integer getIdprofesor() {
		return idprofesor;
	}

	public void setIdprofesor(Integer idprofesor) {
		this.idprofesor = idprofesor;
	}

	public String getCodprof() {
		return codprof;
	}

	public void setCodprof(String codprof) {
		this.codprof = codprof;
	}

	public String getProf_nom() {
		return prof_nom;
	}

	public void setProf_nom(String prof_nom) {
		this.prof_nom = prof_nom;
	}

	public String getProf_ape() {
		return prof_ape;
	}

	public void setProf_ape(String prof_ape) {
		this.prof_ape = prof_ape;
	}

	public String getProf_nac() {
		return prof_nac;
	}

	public void setProf_nac(String prof_nac) {
		this.prof_nac = prof_nac;
	}

	public String getProf_dir() {
		return prof_dir;
	}

	public void setProf_dir(String prof_dir) {
		this.prof_dir = prof_dir;
	}

	public Integer getProf_telf() {
		return prof_telf;
	}

	public void setProf_telf(Integer prof_telf) {
		this.prof_telf = prof_telf;
	}

	public String getProf_especialidad() {
		return prof_especialidad;
	}

	public void setProf_especialidad(String prof_especialidad) {
		this.prof_especialidad = prof_especialidad;
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
}