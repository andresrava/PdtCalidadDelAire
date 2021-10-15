package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Departamento
 *
 */
public class Departamento implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "SEQ_DEP", sequenceName="SEQ_DEP_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DEP")
	private Long ID_DEPARTAMENTO;
	
	@Column(length=30, unique=true, nullable=false) 
	private String NOMBRE;
	
	public Departamento() {
		super();
	}

	public Long getID_DEPARTAMENTO() {
		return ID_DEPARTAMENTO;
	}

	public void setID_DEPARTAMENTO(Long iD_DEPARTAMENTO) {
		ID_DEPARTAMENTO = iD_DEPARTAMENTO;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	} 
	
	
   
}
