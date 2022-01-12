package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table (name = "casillas")
@NamedQuery(name="Casilla.obtenerTodos", query="SELECT c FROM Casilla c")

public class Casilla implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAS" )
	@SequenceGenerator(name = "SEQ_CAS", initialValue = 1, allocationSize = 1)

	private Long id;
	
	@Column(length=30,unique=true)
	private String nombre;
	

	public enum TipoDatoEnum {STRING, BOOLEAN, INTEGER, FLOAT};
	
	@Enumerated
	@Column(length=10)
	private TipoDatoEnum tipoDeDato;
	
	@Column(length=40,nullable=false,unique=true)
	private String parametro;
	
	@Column(length=40)
	private String unidaDeMedida;
	
	@Column(length=50)
	private String descripcion;
	
	@ManyToOne (fetch = FetchType.LAZY)
	private Usuario usuario;
	
	@JoinTable (
			name = "CASILLAS_FORMULARIOS" , 
			joinColumns = @JoinColumn (name = "FK_CASILLA" , nullable = false),
			inverseJoinColumns = @JoinColumn(name = "FK_FORMULARIO" , nullable = false)
			)
	@ManyToMany (cascade = CascadeType.ALL)
	private Set<Formulario> formularios = new HashSet<Formulario>();
	
	@ManyToMany ( mappedBy = "casillas")
	private List<EstacionDeMedicion> estaciones = new LinkedList<EstacionDeMedicion>();
	
	@OneToMany (
			mappedBy = "casilla" ,
			cascade = CascadeType.ALL ,
			orphanRemoval = true
			)
	private List<Registro> registros = new ArrayList<Registro>();
	
	public Casilla() {
		super();
	}

	
	
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getUnidaDeMedida() {
		return unidaDeMedida;
	}

	public void setUnidaDeMedida(String unidaDeMedida) {
		this.unidaDeMedida = unidaDeMedida;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Formulario> getFormularios() {
		return formularios;
	}



	public void setFormularios(Set<Formulario> formularios) {
		this.formularios = formularios;
	}



	public List<EstacionDeMedicion> getEstaciones() {
		return estaciones;
	}



	public void setEstaciones(List<EstacionDeMedicion> estaciones) {
		this.estaciones = estaciones;
	}

	public Casilla(String nombre, String parametro, String unidaDeMedida) {
		super();
		this.nombre = nombre;
		this.parametro = parametro;
		this.unidaDeMedida = unidaDeMedida;
	}
	
	



	public Casilla(String nombre, TipoDatoEnum tipoDeDato, String parametro, String unidaDeMedida, String descripcion,
			Usuario usuario) {
		super();
		this.nombre = nombre;
		this.tipoDeDato = tipoDeDato;
		this.parametro = parametro;
		this.unidaDeMedida = unidaDeMedida;
		this.descripcion = descripcion;
		this.usuario = usuario;
	}



	public String toStringCorto() {
		return "Casilla [nombre=" + nombre + ", parametro=" + parametro + ", unidaDeMedida=" + unidaDeMedida + "]";
	}



	@Override
	public String toString() {
		return "Casilla [id=" + id + ", nombre=" + nombre + ", parametro=" + parametro + ", unidaDeMedida="
				+ unidaDeMedida + "]";
	}

	
   
}
