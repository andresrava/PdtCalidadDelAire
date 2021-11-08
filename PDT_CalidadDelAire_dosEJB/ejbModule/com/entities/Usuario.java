package com.entities;




import java.io.Serializable;
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
	
	@OneToMany(mappedBy = "usuario")
	private List<Actividad> actividades;
	
	@ManyToMany
	private List<Funcionalidad> funcionalidades;
	
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
	
	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	


	
	
   
}
