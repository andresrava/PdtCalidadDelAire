package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="INVESTIGADORES")
@PrimaryKeyJoinColumn(referencedColumnName="id")
@NamedQuery(name="Investigador.obtenerTodos", query="SELECT i FROM Investigador i")

public class Investigador extends Usuario implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Column(length=8,nullable=false,unique=true)
	private String documento;
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(length=40)
	private String domicilio;
	
	@Column(length=20)
	private String telefono;
	
	/*
	 * @ManyToOne (cascade = CascadeType.ALL , fetch = FetchType.LAZY) private
	 * Ciudad ciudad;
	 */
	private String ciudad;
	
	@OneToMany (
			mappedBy = "investigadorCreador" ,
			cascade = CascadeType.ALL ,
			orphanRemoval = true
			)
	private List<Formulario> formularios;
	
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
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

	public String getTel�fono() {
		return telefono;
	}

	public void setTel�fono(String tel�fono) {
		this.telefono = tel�fono;
	}

	public Investigador( String nombre , String apellido, String mail, String clave , String documento , String domicilio, String telefono, String ciudad) {
		super( nombre , apellido , mail , clave);
		 
		this.documento = documento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.ciudad = ciudad;
	}
	
	public Investigador( Long id, String nombre , String apellido, String mail, String clave , String documento , String domicilio, String telefono) {
		super( id, nombre , apellido , mail , clave);
		 
		this.documento = documento;
		this.domicilio = domicilio;
		this.telefono = telefono;
	}
	
	public Investigador( Long id, String nombre , String apellido, String mail, String clave , String documento , String domicilio, String telefono, String ciudad) {
		super( id, nombre , apellido , mail , clave);
		 
		this.documento = documento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Investigador [documento=" + documento + ", domicilio=" + domicilio + ", telefono=" + telefono + "]";
	} 
	
	
   
}
