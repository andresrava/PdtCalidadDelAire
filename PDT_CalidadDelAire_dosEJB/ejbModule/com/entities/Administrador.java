package com.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity (name = "Administrador")
@Table (name = "ADMINISTRADORES")
@PrimaryKeyJoinColumn(referencedColumnName="id")
@NamedQuery(name="Administradores.obtenerTodos", query="SELECT a FROM Administrador a")


public class Administrador extends Usuario implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Column(length=8,unique=true,nullable=false)
	private String documento;
	
	@Column(length=40)
	private String domicilio;
	
	@Column(length=20)
	private String telefono;
	
	@ManyToOne
	private Ciudad ciudad;
	
	public Ciudad getCiudad() {
		return ciudad;
	}


	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}



	@OneToMany (mappedBy = "administrador" ,
			cascade = CascadeType.ALL ,
			orphanRemoval = true
			)
	private List<Formulario> formularios = new LinkedList<Formulario>();
	
	@OneToMany (
			mappedBy = "administrador" ,
			cascade = CascadeType.ALL ,
			orphanRemoval = true)
	private List<Actividad> actividades;
	
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




public Administrador( String nombre , String apellido, String mail, String clave , String documento , String domicilio, String telefono) 
	{
		super(nombre , apellido , mail , clave);
		this.documento = documento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		
		;
	}

public Administrador( Long id, String nombre , String apellido, String mail, String clave , String documento , String domicilio, String telefono) 
{
	super(id, nombre , apellido , mail , clave);
	this.documento = documento;
	this.domicilio = domicilio;
	this.telefono = telefono;

}

public Administrador( Long id, String nombre , String apellido, String mail, String clave , String documento , String domicilio, String telefono, Ciudad ciudad) 
{
	super(id, nombre , apellido , mail , clave);
	this.documento = documento;
	this.domicilio = domicilio;
	this.telefono = telefono;
	this.ciudad = ciudad;


}

@Override
	public String toString() {
		return "Administrador [ id=" + this.getId() + ", documento=" + documento + ", domicilio=" + domicilio + ", telefono=" + telefono	 + "]";
	}


public List<Actividad> getActividades() {
	return actividades;
}


public void setActividades(List<Actividad> actividades) {
	this.actividades = actividades;
}



	
	
   
}
