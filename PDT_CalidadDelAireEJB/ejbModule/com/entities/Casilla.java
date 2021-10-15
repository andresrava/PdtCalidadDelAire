package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Casilla
 *
 */


	@MappedSuperclass
	@Entity
	@Inheritance( strategy = InheritanceType.JOINED )
	
public class Casilla implements Serializable {
		
		private static final long serialVersionUID = 1L;	
		
		@Id
		@SequenceGenerator(name = "SEQ_CAS", sequenceName="SEQ_CAS_seq",initialValue = 1, allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAS")
		private Long ID_CASILLA;
		
		@Column(length=10, unique=true, nullable=false) 
		private String UNIDADMEDIDA;

	
		@Column(length=50, unique=true, nullable=false) 
		private String DESCRIPCION;
		
		@Column(length=30, unique=true, nullable=false) 
		private String PARAMETRO;
		
		@Column(length=30, unique=true, nullable=false) 
		private String TIPOINPUT;
		
		@Column(length=30, unique=true, nullable=false) 
		private String NOMBRE;
		
		
	
	public Casilla() {
		super();
	} 
	
   
}
