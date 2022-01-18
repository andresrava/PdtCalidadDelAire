package com.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.persistence.ManyToOne;


@Entity
@Inheritance( strategy = InheritanceType.JOINED )

public class Registro implements Serializable {
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REG" )
	@SequenceGenerator(name = "SEQ_REG", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(nullable=false)
	private String valor;
	
	@Column
	private float latitud;
	
	@Column
	private float longitud;
	
	@Column(nullable=false)
	private Date fechaHora;
	
	@Column(length=20)
	private String metodoMuestreo;
	
	@ManyToOne (fetch = FetchType.LAZY)
	private Casilla casilla;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "ACTIVIDADES_REGISTROS" , nullable = false)
	private Actividad actividad;
	
	public Registro() {
		super();
	}

	public Casilla getCasilla() {
		return casilla;
	}

	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
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

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getMetodoMuestreo() {
		return metodoMuestreo;
	}

	public void setMetodoMuestreo(String metodoMuestreo) {
		this.metodoMuestreo = metodoMuestreo;
	} 
	
   
}
