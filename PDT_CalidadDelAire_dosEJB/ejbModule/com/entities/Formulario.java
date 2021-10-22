package com.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@MappedSuperclass
@Entity
@Inheritance( strategy = InheritanceType.JOINED )

public class Formulario implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FOR" )
	@SequenceGenerator(name = "SEQ_FOR", initialValue = 1, allocationSize = 1)

	private Long id;
	
	@Column(length=30,nullable=false,unique=true)
	private String nombre;
	
	@Column(nullable=false)
	private Date fechaHora;
	
	@Column(length=40)
	private String resumen;
	
	@Column
	private float latitud;
	
	@Column
	private float longitud;
	
	@ManyToMany
	private List<Casilla> casillas;
	
	@ManyToMany
	private List<Aficionado> aficionados;
	
	@ManyToOne
	private Investigador investigador;
	
	@ManyToOne
	private Administrador administrador;
	
	
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


	public Date getFechaHora() {
		return fechaHora;
	}


	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}


	public String getResumen() {
		return resumen;
	}


	public void setResumen(String resumen) {
		this.resumen = resumen;
	}


	public float getLatitud() {
		return latitud;
	}


	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}


	public float getLongitud() {
		return longitud;
	}


	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}


	public List<Casilla> getCasillas() {
		return casillas;
	}


	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}


	public List<Aficionado> getAficionados() {
		return aficionados;
	}


	public void setAficionados(List<Aficionado> aficionados) {
		this.aficionados = aficionados;
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
	
   
}
