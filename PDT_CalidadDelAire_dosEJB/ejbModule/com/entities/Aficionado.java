package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Aficionado
 *
 */
@Entity

@PrimaryKeyJoinColumn(referencedColumnName="id")
@NamedQuery(name="Aficionado.obtenerTodos", query="SELECT a FROM Aficionado a")


public class Aficionado extends Usuario implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@ManyToMany
	private List<Formulario> formularios;
	
	@OneToMany (
			mappedBy = "aficionado" ,
			cascade = CascadeType.ALL ,
			orphanRemoval = true)
	private List<Actividad> actividades;
	

	public List<Formulario> getFormularios() {
		return formularios;
	}


	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}


	public Aficionado() {
		super();
	}


	public Aficionado(String nombre, String apellido, String mail, String clave) {
		super(nombre, apellido, mail, clave);
		// TODO Auto-generated constructor stub
	}
	
	public Aficionado(Long id, String nombre, String apellido, String mail, String clave) {
		super(id, nombre, apellido, mail, clave);
		// TODO Auto-generated constructor stub
	}


	public List<Actividad> getActividades() {
		return actividades;
	}


	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}


	@Override
	public String toString() {
		String nombre = this.getNombre();
		String apellido = this.getApellido();
		String mail = this.getMail();
		return "Aficionado  [nombre: " + nombre + "apellido: " + apellido + "mail: " + mail +"]";
	}


	
	
}
