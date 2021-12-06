package com.entities;




import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@MappedSuperclass
@Entity (name = "Usuario")
@Table(name="USUARIOS")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQuery(name="Usuario.obtenerTodos", query="SELECT u FROM Usuario u")

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USU" )
	@SequenceGenerator(name = "SEQ_USU", initialValue = 1, allocationSize = 1)
	private Long id;
	
	
	@Column(length=255,nullable=false)
	private String contraseña;
	
	@Column(length=255,nullable=false,unique=true)
	private String mail;
	
	@Column(length=255,nullable=false)
	private String nombre;
	
	@Column(length=255,nullable=false)
	private String apellido;
	
	@OneToMany (
			mappedBy = "usuario" ,
			cascade = CascadeType.ALL ,
			orphanRemoval = true)
	private List<Casilla> casillas = new ArrayList<Casilla>();
	
	@OneToMany (mappedBy = "usuario" , 
			cascade = CascadeType.ALL , 
			orphanRemoval = true)
	private List<EstacionDeMedicion> estaciones = new ArrayList<EstacionDeMedicion>();
	
	
	@JoinTable(
			name = "USUARIOS_FORMULARIOS" ,
			joinColumns = @JoinColumn(name = "FK_USUARIO" , nullable = false),
			inverseJoinColumns = @JoinColumn (name = "FK_FORMULARIO" , nullable = false)
		)	
	@ManyToMany (cascade = CascadeType.ALL)
	private List<Formulario> formularios = new LinkedList<Formulario>();
	
	public Usuario() {
		super();
	}

	//Constructor sin ID
		public Usuario( String nombre , String apellido, String mail, String clave ) {
			
			this.apellido = apellido;
			this.contraseña = clave;
			this.mail = mail;
			this.nombre = nombre;
		}
		
		//Constructor agregando ID
		public Usuario(long idUsuario,  String nombre , String apellido, String mail, String clave) {
			
			this.id = idUsuario;
			this.apellido = apellido;
			this.contraseña = clave;
			this.mail = mail;
			this.nombre = nombre;
		}
		
		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Usuario [contraseña=" + contraseña + ", mail=" + mail + ", nombre=" + nombre + ", apellido=" + apellido
				+ "]";
	}


}
