package com.entities;

import java.util.List;

import javax.persistence.*;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name="ADMINISTRADORES")
@PrimaryKeyJoinColumn(referencedColumnName="id")
@NamedQuery(name="Administradores.obtenerTodos", query="SELECT a FROM Administrador a")


public class Administrador extends Usuario implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Column(length=8,unique=true,nullable=false)
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


	public Administrador() {
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


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	} 
	
   
}
