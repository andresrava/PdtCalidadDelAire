package com.entities;




import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import com.enumerados.BorradoLogico.Estado;

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
	
	@Column
	private Estado estado;
	
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

	@OneToMany (mappedBy = "usuario" , 
			cascade = CascadeType.ALL , 
			orphanRemoval = true)
	private List<Actividad> actividades = new ArrayList<Actividad>();
	
	
	@JoinTable(
			name = "USUARIOS_FORMULARIOS" ,
			joinColumns = @JoinColumn(name = "FK_USUARIO" , nullable = false),
			inverseJoinColumns = @JoinColumn (name = "FK_FORMULARIO" , nullable = false)
			)
	@ManyToMany (cascade = CascadeType.ALL)
	private List<Formulario> formularios = new LinkedList<Formulario>();
	
	public Usuario() {
		super();
		this.estado = Estado.HABILITADO;
	}

	//Constructor sin ID
		public Usuario( String nombre , String apellido, String mail, String clave , Estado estado) {
			
			this.apellido = apellido;
			this.contraseña = clave;
			this.mail = mail;
			this.nombre = nombre;
			this.estado = estado;
		}
		
	//Constructor agregando ID
	public Usuario(long idUsuario,  String nombre , String apellido, String mail, String clave, Estado estado) {
		
		this.id = idUsuario;
		this.apellido = apellido;
		this.contraseña = clave;
		this.mail = mail;
		this.nombre = nombre;
		this.estado = estado;
	}
	
	
		
		
	
		
	public Estado getEstado() {
			return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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

	
	public List<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}

	public List<EstacionDeMedicion> getEstaciones() {
		return estaciones;
	}

	public void setEstaciones(List<EstacionDeMedicion> estaciones) {
		this.estaciones = estaciones;
	}

	public List<Formulario> getFormularios() {
		return formularios;
	}

	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}

	@Override
	public String toString() {
		return "Usuario [contraseña=" + contraseña + ", mail=" + mail + ", nombre=" + nombre + ", apellido=" + apellido
				+ "]";
	}


}
