package com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
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
	
	@ManyToOne
	private Departamento departamento;
	
	
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


	public Departamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	} 
	
   
}
