package com.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQuery(name="Ciudad.obtenerTodos", query="SELECT c FROM Ciudad c")
@Entity (name = "Ciudad")
@Table (name = "ciudad")

public class Ciudad implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CIU" )
	@SequenceGenerator(name = "SEQ_CIU", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(length=30,nullable=false,unique=true)
	private String nombre;
	
//	public List<EstacionDeMedicion> getEm() {
//		return em;
//	}


//	public void setEm(List<EstacionDeMedicion> em) {
//		this.em = em;
//	}


	public enum NombresEnum {ARTIGAS, SALTO, PAYSANDU, R�O_NEGRO, SORIANO, COLONIA, SAN_JOS�, CANELONES, MONTEVIDEO, MALDONADO, ROCHA, TREINTA_Y_TRES, CERRO_LARGO, RIVERA, TACUAREMB�, DURAZNO, FLORES, FLORIDA };

	@Enumerated(EnumType.STRING)
	private NombresEnum departamento;
	
	public List<Administrador> getAdministradores() {
		return administradores;
	}


	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}


	public List<Investigador> getInvestigadores() {
		return investigadores;
	}


	public void setInvestigadores(List<Investigador> investigadores) {
		this.investigadores = investigadores;
	}


	@OneToMany ( fetch = FetchType.LAZY , 
			cascade = CascadeType.ALL  , orphanRemoval = true , mappedBy = "ciudad")
	private List<Administrador> administradores = new LinkedList<Administrador>();
	
	@OneToMany ( fetch = FetchType.LAZY , 
			cascade = CascadeType.ALL  , orphanRemoval = true , mappedBy = "ciudad")
	private List<Investigador> investigadores = new LinkedList<Investigador>();
	
	public Ciudad() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public NombresEnum getDepartamento() {
		return departamento;
	}


	public void setDepartamento(NombresEnum departamento) {
		this.departamento = departamento;
	}


	public Ciudad(String nombre) {
		super();
		this.nombre = nombre;
	}


	public Ciudad(String nombre, NombresEnum departamento) {
		super();
		this.nombre = nombre;
		this.departamento = departamento;
	}


	@Override
	public String toString() {
		return "Ciudad [nombre=" + nombre + ", departamento=" + departamento + "]";
	} 
	
	
   
}
