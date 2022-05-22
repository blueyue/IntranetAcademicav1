package com.ppcr.sistema.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="turno")
public class Turno implements Serializable{
private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	public Integer idturno;
	public String nomturno;
	public Turno() {
		super();
	}
	public Turno(Integer idturno, String nomturno) {
		super();
		this.idturno = idturno;
		this.nomturno = nomturno;
	}
	public Integer getIdturno() {
		return idturno;
	}
	public void setIdturno(Integer idturno) {
		this.idturno = idturno;
	}
	public String getNomturno() {
		return nomturno;
	}
	public void setNomturno(String nomturno) {
		this.nomturno = nomturno;
	}
	@Override
	public String toString() {
		return "Turno [idturno=" + idturno + ", nomturno=" + nomturno + "]";
	}
	
	
}
