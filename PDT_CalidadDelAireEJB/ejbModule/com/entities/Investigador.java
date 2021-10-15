package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Investigador
 *
 */
@MappedSuperclass
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
public class Investigador implements Serializable {

	
private static final long serialVersionUID = 1L;	
	
	@Id
	@SequenceGenerator(name = "SEQ_INV", sequenceName="SEQ_INV_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INV")
	private Long ID_INVESTIGADOR;
	
	@Column(length=38, unique=true, nullable=false) 
	private Long ID_USUARIO;
	
	public Long getID_INVESTIGADOR() {
		return ID_INVESTIGADOR;
	}



	public void setID_INVESTIGADOR(Long iD_INVESTIGADOR) {
		ID_INVESTIGADOR = iD_INVESTIGADOR;
	}



	public Long getID_USUARIO() {
		return ID_USUARIO;
	}



	public void setID_USUARIO(Long iD_USUARIO) {
		ID_USUARIO = iD_USUARIO;
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



	@Column(length=8, unique=true, nullable=false) 
	private String DOCUMENTO;	
	
	@Column(length=50, unique=false, nullable=true) 
	private String DOMICILIO;	
	
	@Column(length=10, unique=false, nullable=true) 
	private String TELEFONO;	
	
	@Column(length=38, unique=false, nullable=false) 
	private Long ID_CIUDAD;	
	
	
	
	public Investigador() {
		super();
	} 
	
   
}
