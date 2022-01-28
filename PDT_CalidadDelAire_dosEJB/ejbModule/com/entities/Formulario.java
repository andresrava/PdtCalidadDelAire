package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;


import com.enumerados.BorradoLogico.Estado;

@Entity
@Table (name = "FORMULARIOS")
@NamedQuery(name="Formulario.obtenerTodos", query="SELECT f FROM Formulario f")

public class Formulario implements Serializable {

	
	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FOR" )
	@SequenceGenerator(name = "SEQ_FOR", initialValue = 1, allocationSize = 1)
	@Column (name = "FK_FORMULARIO")
	private Long id;
	
	@Column(length=30,nullable=false,unique=true)
	private String nombre;
	
	@Column(length=40)
	private String resumen;

	@Column(length=10)
	private Estado estado;
		
	@ManyToMany 
	(
	cascade = {
			CascadeType.MERGE 		
	} ,
	fetch = FetchType.EAGER)
	
	@JoinTable (
	name = "CASILLAS_FORMULARIOS" , 
	joinColumns = @JoinColumn (referencedColumnName = "FK_FORMULARIO" , nullable = false),
	inverseJoinColumns = @JoinColumn(referencedColumnName = "FK_CASILLA" , nullable = false)
	)

	private List<Casilla> casillas = new ArrayList<>();
	
	@ManyToOne (fetch = FetchType.EAGER)
	private Investigador investigadorCreador;
	
	@ManyToMany ( mappedBy = "formularios" , 
			cascade = CascadeType.ALL 
			)
	private List<Usuario> usuariosHabilitados = new LinkedList<Usuario>();
	
	@ManyToOne ( fetch = FetchType.EAGER)
	private Administrador administradorCreador;
	
	@OneToMany (
			mappedBy = "formulario" ,
			cascade = CascadeType.ALL ,
			orphanRemoval = true)
	private List<Actividad> actividades = new LinkedList<Actividad>(); 
	
	
	public String getNombre() {
		return nombre;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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

	
	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public List<Casilla> getCasillas() {
		return casillas;
	}


	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}

	public Investigador getInvestigador() {
		return investigadorCreador;
	}


	public void setInvestigador(Investigador investigador) {
		this.investigadorCreador = investigador;
	}


	public Administrador getAdministrador() {
		return administradorCreador;
	}


	public void setAdministrador(Administrador administrador) {
		this.administradorCreador = administrador;
	}


	public Formulario() {
		super();
		this.estado = Estado.HABILITADO;
	}


	public Formulario(String nombre, Investigador investigador) {
		super();
		this.nombre = nombre;
		this.investigadorCreador = investigador;
		this.estado = Estado.HABILITADO;
	} 
	
	
	


	public Formulario(String nombre, List<Casilla> casillas, Investigador investigadorCreador) {
		super();
		this.nombre = nombre;
		this.estado = Estado.HABILITADO;
		this.casillas = casillas;
		this.investigadorCreador = investigadorCreador;
	}


	public List<Usuario> getUsuarios() {
		return usuariosHabilitados;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		this.usuariosHabilitados = usuarios;
	}


	public List<Actividad> getActividades() {
		return actividades;
	}


	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}


	@Override
	public String toString() {
		return "Formulario [id=" + id + ", nombre=" + nombre + ", casillas=" + casillas + "]";
	}


	
	
	
	
   
}
