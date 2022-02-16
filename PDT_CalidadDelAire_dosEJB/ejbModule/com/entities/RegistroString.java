package com.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.enumerados.BorradoLogico.Estado;
@Entity (name = "RegistroString")
@Table (name = "REGISTROSSTRING")
@PrimaryKeyJoinColumn(referencedColumnName="id")

public class RegistroString extends Registro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String valor;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public RegistroString(String valor) {
		super();
		this.valor = valor;
	}

	public RegistroString() {
		super();
	}

	public RegistroString(String valor , float latitud, float longitud, Date fechaHora, Estado estado, Casilla casilla) {
		super(latitud, longitud, fechaHora, estado, casilla);
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "RegistroString [id= " + this.getId() + ", latitud= " + getLatitud() + ", longitud= " + getLongitud()
				+ ", fechaHora= " + getFechaHora() + ", casilla= " + getCasilla() + ", valor=" + valor + "]";
	}

	
	
	
}
