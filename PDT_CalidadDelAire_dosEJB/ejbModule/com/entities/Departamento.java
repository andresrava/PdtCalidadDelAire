package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;


@Entity
@NamedQuery(name="Departmento.obtenerTodos", query="SELECT d FROM Departamento d")
public class Departamento implements Serializable {
	
private static final long serialVersionUID = 1L;


@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DEP" )
@SequenceGenerator(name = "SEQ_DEP", initialValue = 1, allocationSize = 1)
private Long id_casilla;

public enum NombresEnum {ARTIGAS, SALTO, PAYSANDU, R�O_NEGRO, SORIANO, COLONIA, SAN_JOS�, CANELONES, MONTEVIDEO, MALDONADO, ROCHA, TREINTA_Y_TRES, CERRO_LARGO, RIVERA, TACUAREMB�, DURAZNO, FLORES, FLORIDA };








@Enumerated(EnumType.STRING)  // fuerza a que se persista un String con el elemento del enum
private NombresEnum nombre;

@OneToMany
private List<Ciudad> ciudades;

private String descripci�n;
	
	
	public Departamento() {
		super();
	}


	public NombresEnum getNombre() {
		return nombre;
	}


	public void setNombre(NombresEnum nombre) {
		this.nombre = nombre;
	}


	public String getDescripci�n() {
		return descripci�n;
	}


	public void setDescripci�n(String descripci�n) {
		this.descripci�n = descripci�n;
	}


	public Long getId_casilla() {
		return id_casilla;
	}


	public void setId_casilla(Long id_casilla) {
		this.id_casilla = id_casilla;
	}


	public List<Ciudad> getCiudades() {
		return ciudades;
	}


	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	} 
	
   
}
