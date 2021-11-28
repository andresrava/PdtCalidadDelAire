package com.entities;

import java.io.Serializable;
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

@NamedQuery(name="Ciudad.obtenerTodos", query="SELECT c FROM Ciudad c")
@Entity
public class Ciudad implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CIU" )
	@SequenceGenerator(name = "SEQ_CIU", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(length=30,nullable=false)
	private String nombre;
	
	public enum NombresEnum {ARTIGAS, SALTO, PAYSANDU, RÍO_NEGRO, SORIANO, COLONIA, SAN_JOSÉ, CANELONES, MONTEVIDEO, MALDONADO, ROCHA, TREINTA_Y_TRES, CERRO_LARGO, RIVERA, TACUAREMBÓ, DURAZNO, FLORES, FLORIDA };

	@Enumerated(EnumType.STRING)
	private NombresEnum departamento;
	
	@OneToMany ( fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	private List<EstacionDeMedicion> em;
	
	
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
