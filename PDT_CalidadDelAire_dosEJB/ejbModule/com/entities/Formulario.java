package com.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table (name = "FORMULARIOS")
@NamedQuery(name="Formulario.obtenerTodos", query="SELECT f FROM Formulario f")

public class Formulario implements Serializable {

	
	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FOR" )
	@SequenceGenerator(name = "SEQ_FOR", initialValue = 1, allocationSize = 1)

	private Long id;
	
	@Column(length=30,nullable=false,unique=true)
	private String nombre;
	
	@Column(length=40)
	private String resumen;

	
	@ManyToMany ( mappedBy = "formularios")
	private List<Casilla> casillas = new LinkedList<Casilla>();
	
	@ManyToOne (fetch = FetchType.LAZY)
	private Investigador investigador;
	
	@ManyToMany (mappedBy = "formularios")
	private List<Usuario> usuarios = new LinkedList<Usuario>();
	
	@ManyToOne ( fetch = FetchType.LAZY)
	private Administrador administrador;
	
	@OneToMany (
			mappedBy = "formulario" ,
			cascade = CascadeType.ALL ,
			orphanRemoval = true)
	private List<Actividad> actividades = new LinkedList<Actividad>(); 
	
	
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



	public String getResumen() {
		return resumen;
	}


	public void setResumen(String resumen) {
		this.resumen = resumen;
	}


	public List<Casilla> getCasillas() {
		return casillas;
	}


	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}

	public Investigador getInvestigador() {
		return investigador;
	}


	public void setInvestigador(Investigador investigador) {
		this.investigador = investigador;
	}


	public Administrador getAdministrador() {
		return administrador;
	}


	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}


	public Formulario() {
		super();
	}


	public Formulario(String nombre, Investigador investigador) {
		super();
		this.nombre = nombre;
		this.investigador = investigador;
	} 
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public List<Actividad> getActividades() {
		return actividades;
	}


	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}


	@Override
	public String toString() {
		return "Formulario [nombre=" + nombre + ", resumen=" + resumen + ", casillas=" + casillas + ", investigador="
				+ investigador + ", usuarios=" + usuarios + ", administrador=" + administrador + ", actividades="
				+ actividades + "]";
	}
	
	
	
   
}
