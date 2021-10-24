package com.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.persistence.ManyToOne;

@MappedSuperclass
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
	
	@ManyToOne
	private Casilla casilla;
	
	@ManyToOne
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
	
   
}
