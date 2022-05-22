package com.ppcr.sistema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.bytebuddy.implementation.bind.annotation.Empty;

@Entity
@Table(name="detalleprofesorcursos")
public class DetalleCurso {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iddet;
	private Integer idprofesor;
	private Integer idcurso;
	private int idturno;
	private String horainicurso;
	private String horafincurso;
	private int estado;
	
	//@Column(name="horafincurso")
	
	@OneToOne
	@JoinColumn(name="idprofesor", insertable = false, updatable = false)
	private Profesor profesor;
	
	@OneToOne
	@JoinColumn(name="idcurso", insertable = false, updatable = false)
	private Curso curso;
	
	@ManyToOne
	@JoinColumn(name = "idturno", insertable = false, updatable = false)
	private Turno turno;

	public Long getIddet() {
		return iddet;
	}

	public void setIddet(Long iddet) {
		this.iddet = iddet;
	}

	public Integer getIdprofesor() {
		return idprofesor;
	}

	public void setIdprofesor(Integer idprofesor) {
		this.idprofesor = idprofesor;
	}

	public Integer getIdcurso() {
		return idcurso;
	}

	public void setIdcurso(Integer idcurso) {
		this.idcurso = idcurso;
	}

	public int getIdturno() {
		return idturno;
	}

	public void setIdturno(int idturno) {
		this.idturno = idturno;
	}

	public String getHorainicurso() {
		return horainicurso;
	}

	public void setHorainicurso(String horainicurso) {
		this.horainicurso = horainicurso;
	}

	public String getHorafincurso() {
		return horafincurso;
	}

	public void setHorafincurso(String horafincurso) {
		this.horafincurso = horafincurso;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public DetalleCurso(Long iddet, Integer idprofesor, Integer idcurso, int idturno, String horainicurso,
			String horafincurso, int estado, Profesor profesor, Curso curso, Turno turno) {
		super();
		this.iddet = iddet;
		this.idprofesor = idprofesor;
		this.idcurso = idcurso;
		this.idturno = idturno;
		this.horainicurso = horainicurso;
		this.horafincurso = horafincurso;
		this.estado = estado;
		this.profesor = profesor;
		this.curso = curso;
		this.turno = turno;
	}

	public DetalleCurso() {
		super();
	}
	
	

	
}
