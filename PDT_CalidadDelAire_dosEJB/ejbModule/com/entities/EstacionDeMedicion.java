package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@MappedSuperclass
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
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
	
	@OneToMany
	private List<Casilla> casillas;
	
	@ManyToOne
	private Ciudad ciudad;
	
	@ManyToOne
	private Investigador investigador;
	
	public EstacionDeMedicion() {
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

	public Investigador getInvestigador() {
		return investigador;
	}

	public void setInvestigador(Investigador investigador) {
		this.investigador = investigador;
	} 
	
	
   
}
