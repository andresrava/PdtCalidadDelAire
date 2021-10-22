package com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Entity implementation class for Entity: Ciudad
 *
 */
@Entity
public class Ciudad implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CIU" )
	@SequenceGenerator(name = "SEQ_CIU", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(length=30,nullable=false)
	private String nombre;
	
	@ManyToOne
	private Departamento departamento;
	
	
	public Ciudad() {
		super();
	} 
	
   
}
