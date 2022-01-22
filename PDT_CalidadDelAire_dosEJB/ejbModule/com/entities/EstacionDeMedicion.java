package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table (name = "ESTACIONESDEMEDICION")
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
	
//	@JoinTable (
//			name = "EM_CASILLAS",
//			joinColumns = @JoinColumn(name = "FK_EM" , nullable = false),
//			inverseJoinColumns = @JoinColumn(name = "FK_CASILLA" , nullable = false)
//			)
//	
	@ManyToMany (mappedBy = "estaciones"  , fetch = FetchType.EAGER)
	private List<Casilla> casillas;
	
	private String departamento;
//	@ManyToOne (cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
//	private Ciudad ciudad;
	
	private String localidad;
	
	@ManyToOne  (fetch = FetchType.EAGER )
	private Usuario usuario;
	
	
	public EstacionDeMedicion() {
		super();
	}

	
	public EstacionDeMedicion(String nombre, String departamento, Usuario usuario) {
		super();
		this.nombre = nombre;
		this.departamento = departamento;
		this.usuario = usuario;
	}

		
	public EstacionDeMedicion(String nombre, String departamento) {
		super();
		this.nombre = nombre;
		this.departamento = departamento;
	}


	public EstacionDeMedicion(String nombre) {
		super();
		this.nombre = nombre;
	}

	
	
	public EstacionDeMedicion(String nombre, String descripcion, List<Casilla> casillas, String departamento, String localidad ,
			Usuario usuario) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.casillas = casillas;
		this.departamento = departamento;
		this.localidad = localidad;
		this.usuario = usuario;
	}
	
	
	
	public EstacionDeMedicion(String nombre, String departamento, String localidad, Usuario usuario) {
		super();
		this.nombre = nombre;
		this.departamento = departamento;
		this.localidad = localidad;
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

	public List<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas (List<Casilla> casillas) {
		this.casillas = casillas;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setCiudad(String departamento) {
		this.departamento = departamento;
	}

	
	
	public String getLocalidad() {
		return localidad;
	}


	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public String toString() {
		return "EstacionDeMedicion [nombre=" + nombre + ", ciudad=" + departamento + ", usuario=" + usuario + ", descripcion=" + descripcion + ", casillas=" + casillas
				+ "]";
	}
	
	public String toStringCorto() {
		return "EstacionDeMedicion [nombre=" + nombre + ", ciudad=" + departamento + "]";
	}
	
	
   
}
