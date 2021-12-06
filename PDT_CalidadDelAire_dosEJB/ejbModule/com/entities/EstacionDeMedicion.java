package com.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.ManyToOne;


@Entity
@Table (name = "EM")
@NamedQuery(name="EstacionDeMedicion.obtenerTodos", query="SELECT e FROM EstacionDeMedicion e")
public class EstacionDeMedicion implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EM" )
	@SequenceGenerator(name = "SEQ_EM", initialValue = 1, allocationSize = 1)

	private Long id;
	
	@Column(length=40,nullable=false,unique=true)
	private String nombre;
	
	@Column(length=40)
	private String descripcion;
	
	@JoinTable (
			name = "EM_CASILLAS",
			joinColumns = @JoinColumn(name = "FK_EM" , nullable = false),
			inverseJoinColumns = @JoinColumn(name = "FK_CASILLA" , nullable = false)
			)
	
	@ManyToMany (cascade = CascadeType.ALL)
	private Set<Casilla> casillas = new HashSet<Casilla>();
	
	
	@ManyToOne (cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
	private Ciudad ciudad;
	
	@ManyToOne  (cascade = CascadeType.PERSIST , fetch = FetchType.LAZY )
	private Usuario usuario;
	
	
	public EstacionDeMedicion() {
		super();
	}

	
	public EstacionDeMedicion(String nombre, Ciudad ciudad, Usuario usuario) {
		
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.usuario = usuario;
	}

		
	public EstacionDeMedicion(String nombre, Ciudad ciudad) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
	}


	public EstacionDeMedicion(String nombre) {
		super();
		this.nombre = nombre;
	}

	
	
	public EstacionDeMedicion(String nombre, String descripcion, Set<Casilla> casillas, Ciudad ciudad,
			Usuario usuario) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.casillas = casillas;
		this.ciudad = ciudad;
		this.usuario = usuario;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(Set<Casilla> casillas) {
		this.casillas = casillas;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public String toString() {
		return "EstacionDeMedicion [nombre=" + nombre + ", ciudad=" + ciudad + ", usuario=" + usuario + "]";
	} 
	
	
   
}
