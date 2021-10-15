package com.entities;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Investigador
 *
 */

@Entity
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
		
	
	public Long getID_INVESTIGADOR() {
		return ID_INVESTIGADOR;
	}

	@ManyToOne
	private Ciudad ciudad;
	
	@OneToMany(mappedBy = "investigador")
	private List<Em> estaciones;
	
	
	public List<Em> getEstaciones() {
		return estaciones;
	}


	public void setEstaciones(List<Em> estaciones) {
		this.estaciones = estaciones;
	}


	public Ciudad getCiudad() {
		return ciudad;
	}


	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
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

	public Investigador() {
		super();
	} 
	
   
}
