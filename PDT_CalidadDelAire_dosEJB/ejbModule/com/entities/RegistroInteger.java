package com.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.enumerados.Enumerados.Estado;

@Entity (name = "RegistroInteger")
@Table (name = "REGISTROSINTEGER")
@PrimaryKeyJoinColumn(referencedColumnName="id")

public class RegistroInteger extends Registro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private Integer valor;
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}
	public RegistroInteger() {
		super();
	}
	public RegistroInteger(Integer valor, float latitud, float longitud, Date fechaHora, Estado estado, Casilla casilla) {
		super(latitud, longitud, fechaHora, estado, casilla);
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "RegistroInteger  [id= " + this.getId() + ", latitud= " + getLatitud() + ", longitud= " + getLongitud()
				+ ", fechaHora= " + getFechaHora() + ", casilla= " + getCasilla() + ", valor=" + valor + "]";
	}
	
	
	
}
