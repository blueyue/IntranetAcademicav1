package com.ppcr.sistema.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="curso")
public class Curso implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idcurso")
	private Long idcurso;
	@Column(name="nomcurso")
	private String nomcurso;
	@Column(name="estado")
	private Boolean estado;
	@Column(name="requisito")
	private String requisito;
	
	
	public Curso() {
		super();
	}

	public Long getIdcurso() {
		return idcurso;
	}

	public void setIdcurso(Long idcurso) {
		this.idcurso = idcurso;
	}

	public String getNomcurso() {
		return nomcurso;
	}

	public void setNomcurso(String nomcurso) {
		this.nomcurso = nomcurso;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getRequisito() {
		return requisito;
	}

	public void setRequisito(String requisito) {
		this.requisito = requisito;
	}


	public Curso(Long idcurso, String nomcurso, Boolean estado, String requisito) {
		super();
		this.idcurso = idcurso;
		this.nomcurso = nomcurso;
		this.estado = estado;
		this.requisito = requisito;
	}

	

	
	
	
}
