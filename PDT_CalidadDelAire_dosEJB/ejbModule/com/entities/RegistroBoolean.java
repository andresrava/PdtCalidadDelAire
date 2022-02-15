package com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.enumerados.Enumerados.Booleano;

@Entity (name = "RegistroBoolean")
@Table (name = "REGISTROSBOOLEAN")
@PrimaryKeyJoinColumn(referencedColumnName="id")

public class RegistroBoolean extends Registro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column 
	private Booleano valor;
	public Booleano getValor() {
		return valor;
	}
	public void setValor(Booleano valor) {
		this.valor = valor;
	}
	public RegistroBoolean() {
		super();
	}
	
	
}
