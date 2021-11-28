package com.entities;

import java.io.Serializable;
import java.util.List;

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
	private List<Casilla> casillas;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private Ciudad ciudad;
	
	@ManyToOne (cascade = CascadeType.ALL , fetch = FetchType.EAGER)
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

	public List<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<Casilla> casillas) {
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
