package com.entities;

import javax.persistence.*;

@Entity
@Table(name="USUARIOS")
@Inheritance(strategy=InheritanceType.JOINED)

public class Usuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USU" )
	@SequenceGenerator(name = "SEQ_USU", initialValue = 1, allocationSize = 1)

	private Long id;
	
	@Column(length=40,nullable=false,unique=true)
	private String nombreUsuario;
	
	@Column(length=40,nullable=false)
	private String contraseña;
	
	@Column(length=40,nullable=false,unique=true)
	private String mail;
	
	@Column(length=40,nullable=false)
	private String nombre;
	
	@Column(length=40,nullable=false)
	private String apellido;
	
	public Usuario() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	} 
	
	
   
}
