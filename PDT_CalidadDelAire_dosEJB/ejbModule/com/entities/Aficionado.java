package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.ManyToMany;

/**
 * Entity implementation class for Entity: Aficionado
 *
 */
@Entity
@Table(name="AFICIONADOS")
@PrimaryKeyJoinColumn(referencedColumnName="id")
@NamedQuery(name="Aficionado.obtenerTodos", query="SELECT a FROM Aficionado a")


public class Aficionado extends Usuario implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@ManyToMany
	private List<Formulario> formularios;
	

	public List<Formulario> getFormularios() {
		return formularios;
	}


	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}


	public Aficionado() {
		super();
	}
   
}
