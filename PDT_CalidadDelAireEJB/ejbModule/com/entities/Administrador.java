package com.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Administrador
 *
 */
//@MappedSuperclass
//@Entity
//@Inheritance( strategy = InheritanceType.JOINED )
@Entity
@NamedQuery(name="Usuario.obtenerTodos", query="SELECT u FROM Usuario u")
public class Administrador extends Usuario {

	
	private static final long serialVersionUID = 1L;

	
	@Column(length=38, unique=true, nullable=false)
	private Long ID_ADMINISTRADOR;
	
	@Column(length=8, unique=true, nullable=false) 
	private String DOCUMENTO;
	
	@Column(length=50, unique=true, nullable=true) 
	private String DOMICILIO;
	
	@Column(length=10, unique=true, nullable=true) 
	private String TELEFONO;
	
	@ManyToOne
	private Ciudad ciudad;
	
	
	public Ciudad getCiudad() {
		return ciudad;
	}


	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}


	public Administrador() {
		super();
	}


	public Long getID_ADMINISTRADOR() {
		return ID_ADMINISTRADOR;
	}


	public void setID_ADMINISTRADOR(Long iD_ADMINISTRADOR) {
		ID_ADMINISTRADOR = iD_ADMINISTRADOR;
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

}
