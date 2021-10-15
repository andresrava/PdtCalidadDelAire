package com.entities;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Investigador
 *
 */
//@MappedSuperclass
//@Entity
//@Inheritance( strategy = InheritanceType.JOINED )
public class Investigador extends Usuario {

	
private static final long serialVersionUID = 1L;	
	
	@Column(length=30, unique=true, nullable=false)
	private Long ID_INVESTIGADOR;
	
	@Column(length=8, unique=true, nullable=false) 
	private String DOCUMENTO;	
	
	@Column(length=50, unique=false, nullable=true) 
	private String DOMICILIO;	
	
	@Column(length=10, unique=false, nullable=true) 
	private String TELEFONO;	
	
	@Column(length=38, unique=false, nullable=false) 
	private Long ID_CIUDAD;	
	
	
	public Long getID_INVESTIGADOR() {
		return ID_INVESTIGADOR;
	}



	public void setID_INVESTIGADOR(Long iD_INVESTIGADOR) {
		ID_INVESTIGADOR = iD_INVESTIGADOR;
	}


	public String getDOCUMENTO() {
		return DOCUMENTO;
	}



	public void setDOCUMENTO(String dOCUMENTO) {
		DOCUMENTO = dOCUMENTO;
	}



	public String getDOMICILIO() {
		return DOMICILIO;
	}



	public void setDOMICILIO(String dOMICILIO) {
		DOMICILIO = dOMICILIO;
	}



	public String getTELEFONO() {
		return TELEFONO;
	}



	public void setTELEFONO(String tELEFONO) {
		TELEFONO = tELEFONO;
	}



	public Long getID_CIUDAD() {
		return ID_CIUDAD;
	}



	public void setID_CIUDAD(Long iD_CIUDAD) {
		ID_CIUDAD = iD_CIUDAD;
	}


	
	public Investigador() {
		super();
	} 
	
   
}
