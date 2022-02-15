package com.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import com.enumerados.BorradoLogico.Estado;

@Entity (name = "Administrador")
@Table (name = "ADMINISTRADORES")
@PrimaryKeyJoinColumn(referencedColumnName="id")
@NamedQuery(name="Administrador.obtenerTodos", query="SELECT a FROM Administrador a")


public class Administrador extends Usuario implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Column(length=8,unique=true,nullable=false)
	private String documento;
	
	@Column(length=40)
	private String domicilio;
	
	@Column(length=20)
	private String telefono;
	
	/*
	 * @ManyToOne (cascade = CascadeType.ALL , fetch = FetchType.EAGER) private
	 * Ciudad ciudad;
	 */
	@Column
	private String ciudad;
	@Column
	private String departamento;
	




	@OneToMany (mappedBy = "administradorCreador" ,
			cascade = CascadeType.ALL ,
			orphanRemoval = true
			)
	private List<Formulario> formularios = new LinkedList<Formulario>();
	
	
	public List<Formulario> getFormularios() {
		return formularios;
	}


	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}


	public Administrador() {
		super();
	}

	

	public String getDepartamento() {
		return departamento;
	}


	public void setDepartamento(String departamento) {
		this.departamento = departamento;
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

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCiudad() {
		return ciudad;
	}




public Administrador( String nombre , String apellido, String mail, String clave, Estado estado , String documento , String domicilio, String telefono, String ciudad, String departamento) 
	{
		super(nombre , apellido , mail , clave, estado);
		this.documento = documento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.ciudad = ciudad;
		this.departamento = departamento;
	}

public Administrador( Long id, String nombre , String apellido, String mail, String clave, Estado estado , String documento , String domicilio, String telefono) 
{
	super(id, nombre , apellido , mail , clave, estado);
	this.documento = documento;
	this.domicilio = domicilio;
	this.telefono = telefono;

}

public Administrador( Long id, String nombre , String apellido, String mail, String clave, Estado estado , String documento , String domicilio, String telefono, String ciudad) 
{
	super(id, nombre , apellido , mail , clave, estado);
	this.documento = documento;
	this.domicilio = domicilio;
	this.telefono = telefono;
	this.ciudad = ciudad;


}

@Override
	public String toString() {
		return "Administrador [ id=" + this.getId() + ", documento=" + documento + ", domicilio=" + domicilio + ", telefono=" + telefono	 + "]";
	}






	
	
   
}
