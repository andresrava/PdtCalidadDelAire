package com.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.persistence.ManyToOne;

import com.enumerados.BorradoLogico.Booleano;
import com.enumerados.BorradoLogico.Estado;


@Entity
@Inheritance( strategy = InheritanceType.JOINED )

public class Registro implements Serializable {
	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REG" )
	@SequenceGenerator(name = "SEQ_REG", initialValue = 1, allocationSize = 1)
	private Long id;
	
//	@Column (length = 20)
//	private String valorString;
//	
//	@Column
//	private Booleano valorBooleano;
//	
//	@Column
//	private Float valorFloat;
//	
//	@Column
//	private Integer valorInteger;
//	
	@Column 
	private String valor;
	
	@Column
	private float latitud;
	
	@Column
	private float longitud;
	
	@Column(nullable=false)
	private Date fechaHora;
	
	@Column(length=20)
	private String metodoMuestreo;
	
	@Column
	private Estado estado;
	
	@ManyToOne (fetch = FetchType.EAGER)
	private Casilla casilla;
	
	@ManyToOne (fetch = FetchType.EAGER)
	private Actividad actividad;
	
	public Registro() {
		super();
		this.estado = Estado.HABILITADO;
	}

	
	public Registro(String valor, float latitud, float longitud, Date fechaHora, Estado estado ) {
		super();
		this.valor = valor;
		this.latitud = latitud;
		this.longitud = longitud;
		this.fechaHora = fechaHora;
		this.estado = estado;
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
	 
	
//	public String getValorString() {
//		return valorString;
//	}
//
//	public void setValorString(String valor) {
//		this.valorString = valor;
//	}
//
//	public Booleano getValorBooleano() {
//		return valorBooleano;
//	}
//
//	public void setValorBooleano(Booleano valorBooleano) {
//		this.valorBooleano = valorBooleano;
//	}
//
//	public Float getValorFloat() {
//		return valorFloat;
//	}
//
//	public void setValorFloat(Float valorFloat) {
//		this.valorFloat = valorFloat;
//	}
//
//	public Integer getValorInteger() {
//		return valorInteger;
//	}
//
//	public void setValorInteger(Integer valorInteger) {
//		this.valorInteger = valorInteger;
//	}

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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Registro [id=" + id + ", valor=" + valor + ", latitud=" + latitud + ", longitud=" + longitud
				+ ", fechaHora=" + fechaHora + ", casilla=" + casilla + "]";
	}

	

   
	
}
