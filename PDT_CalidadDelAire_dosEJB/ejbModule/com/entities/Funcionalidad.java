package com.entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Funcionalidad implements Serializable {

public enum Rol {ADMINISTRADOR, INVESTIGADOR, AFICIONADO};
	
	
	private static final long serialVersionUID = 1L;	
	@Id
	@SequenceGenerator(name = "SEQ_FUN", sequenceName="SEQ_FUN_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FUN")
	private long id;
	
	@Column(length=255)
	private String descripcion;

	@Column(length=255, unique=true, nullable=false)
	private String nombre;

	public Funcionalidad() {
		super();
	}
	public long getIdFuncionalidad() {
		return id;
	}
	public void setIdFuncionalidad(long idFuncionalidad) {
		this.id = idFuncionalidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Funcionalidad(String descripcion, String nombre) {
		super();
		this.descripcion = descripcion;
		this.nombre = nombre;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
   
}
