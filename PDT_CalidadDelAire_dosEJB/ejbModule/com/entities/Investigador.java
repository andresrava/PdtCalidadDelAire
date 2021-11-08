package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name="INVESTIGADORES")
@PrimaryKeyJoinColumn(referencedColumnName="id")
@NamedQuery(name="Investigador.obtenerTodos", query="SELECT i FROM Investigador i")

public class Investigador extends Usuario implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Column(length=8,nullable=false,unique=true)
	private String documento;
	
	@Column(length=40)
	private String domicilio;
	
	@Column(length=20)
	private String telefono;
	
	@ManyToOne
	private Ciudad ciudad;
	
	@OneToMany
	private List<Formulario> formularios;
	
	
	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public List<Formulario> getFormularios() {
		return formularios;
	}

	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}

	public Investigador() {
		super();
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTeléfono() {
		return telefono;
	}

	public void setTeléfono(String teléfono) {
		this.telefono = teléfono;
	}

	public Investigador( String nombre , String apellido, String mail, String clave , String documento , String domicilio, String telefono) {
		super( nombre , apellido , mail , clave);
		 
		this.documento = documento;
		this.domicilio = domicilio;
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Investigador [documento=" + documento + ", domicilio=" + domicilio + ", telefono=" + telefono + "]";
	} 
	
	
   
}
