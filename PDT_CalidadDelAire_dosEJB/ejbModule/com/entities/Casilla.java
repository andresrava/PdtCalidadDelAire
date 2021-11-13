package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

@MappedSuperclass
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
@NamedQuery(name="Casilla.obtenerTodos", query="SELECT c FROM Casilla c")

public class Casilla implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAS" )
	@SequenceGenerator(name = "SEQ_CAS", initialValue = 1, allocationSize = 1)

	private Long id;
	
	@Column(length=30,unique=true)
	private String nombre;
	
	public List<Formulario> getFormularios() {
		return formularios;
	}



	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}



	public List<EstacionDeMedicion> getEstaciones() {
		return estaciones;
	}



	public void setEstaciones(List<EstacionDeMedicion> estaciones) {
		this.estaciones = estaciones;
	}

	private enum TipoDatoEnum {STRING, BOOLEAN, INTEGER, FLOAT};
	
	@Enumerated
	@Column(length=10)
	private TipoDatoEnum tipoDeDato;
	
	@Column(length=40,nullable=false,unique=true)
	private String parametro;
	
	@Column(length=40)
	private String unidaDeMedida;
	
	@Column(length=50)
	private String descripcion;
	
	@ManyToMany
	private List<Formulario> formularios;
	
	@ManyToMany
	private List<EstacionDeMedicion> estaciones;
	
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

	public TipoDatoEnum getTipoDeDato() {
		return tipoDeDato;
	}

	public void setTipoDeDato(TipoDatoEnum tipoDeDato) {
		this.tipoDeDato = tipoDeDato;
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



	public Casilla(String nombre, String parametro, String unidaDeMedida) {
		super();
		this.nombre = nombre;
		this.parametro = parametro;
		this.unidaDeMedida = unidaDeMedida;
	}

	
   
}
