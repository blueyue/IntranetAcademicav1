package com.ppcr.sistema.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="notas")
public class Nota implements Serializable{

	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idreporte;
	
	private Integer idalumno;
	private Integer idcurso;
	private Integer nota1;
	private Integer nota2;
	private Integer nota3;
	private Integer nota4;
	private Integer exparcial;
	private Integer exfinal;
	private Integer exsustitutorio;
	private Integer promediofinal;
	
	
	@ManyToOne
	@JoinColumn(name = "idalumno", insertable = false, updatable = false)
	private Alumno alumno;
	
	@ManyToOne
	@JoinColumn(name = "idcurso", insertable = false, updatable = false)
	private Curso curso;

	public Integer getIdreporte() {
		return idreporte;
	}

	public void setIdreporte(Integer idreporte) {
		this.idreporte = idreporte;
	}

	public Integer getIdalumno() {
		return idalumno;
	}

	public void setIdalumno(Integer idalumno) {
		this.idalumno = idalumno;
	}

	public Integer getIdcurso() {
		return idcurso;
	}

	public void setIdcurso(Integer idcurso) {
		this.idcurso = idcurso;
	}

	public Integer getNota1() {
		return nota1;
	}

	public void setNota1(Integer nota1) {
		this.nota1 = nota1;
	}

	public Integer getNota2() {
		return nota2;
	}

	public void setNota2(Integer nota2) {
		this.nota2 = nota2;
	}

	public Integer getNota3() {
		return nota3;
	}

	public void setNota3(Integer nota3) {
		this.nota3 = nota3;
	}

	public Integer getNota4() {
		return nota4;
	}

	public void setNota4(Integer nota4) {
		this.nota4 = nota4;
	}

	public Integer getExparcial() {
		return exparcial;
	}

	public void setExparcial(Integer exparcial) {
		this.exparcial = exparcial;
	}

	public Integer getExfinal() {
		return exfinal;
	}

	public void setExfinal(Integer exfinal) {
		this.exfinal = exfinal;
	}

	public Integer getExsustitutorio() {
		return exsustitutorio;
	}

	public void setExsustitutorio(Integer exsustitutorio) {
		this.exsustitutorio = exsustitutorio;
	}

	public Integer getPromediofinal() {
		return promediofinal;
	}

	public void setPromediofinal(Integer promediofinal) {
		this.promediofinal = promediofinal;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Nota [idreporte=" + idreporte + ", idalumno=" + idalumno + ", idcurso=" + idcurso + ", nota1=" + nota1
				+ ", nota2=" + nota2 + ", nota3=" + nota3 + ", nota4=" + nota4 + ", exparcial=" + exparcial
				+ ", exfinal=" + exfinal + ", exsustitutorio=" + exsustitutorio + ", promediofinal=" + promediofinal
				+ ", alumno=" + alumno + ", curso=" + curso + "]";
	}
	
	
	
}
