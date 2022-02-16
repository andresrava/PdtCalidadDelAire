package com.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.enumerados.Enumerados.Estado;

@Entity (name = "RegistroFloat")
@Table (name = "REGISTROSFLOAT")
@PrimaryKeyJoinColumn(referencedColumnName="id")

public class RegistroFloat extends Registro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private Double valor;
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public RegistroFloat() {
		super();
	}
	public RegistroFloat(Double valor, float latitud, float longitud, Date fechaHora, Estado estado, Casilla casilla) {
		super(latitud, longitud, fechaHora, estado, casilla);
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "RegistroFloat  [id= " + this.getId() + ", latitud= " + getLatitud() + ", longitud= " + getLongitud()
				+ ", fechaHora= " + getFechaHora() + ", casilla= " + getCasilla() + ", valor=" + valor + "]";
	}
	
	
	
}
