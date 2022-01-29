package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


import com.enumerados.BorradoLogico.Estado;
import com.enumerados.BorradoLogico.Obligatoria;

@Entity
@Table (name = "casillas")
@NamedQuery(name="Casilla.obtenerTodos", query="SELECT c FROM Casilla c")

public class Casilla implements Serializable {


	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAS" )
	@SequenceGenerator(name = "SEQ_CAS", initialValue = 1, allocationSize = 1)
	@Column( name = "FK")
	private Long id;
	
	@Column(length=30,unique=true)
	private String nombre;
	

	public enum TipoDatoEnum 
	{
		STRING("String",0), BOOLEAN("Boolean",1), INTEGER("Integer",2), FLOAT("Float",3);
		private String nombreTipoDato;
		private int numero;
		private TipoDatoEnum (String nombre, int numero) {
			this.numero = numero;
			this.nombreTipoDato = nombre;
		}
		public String getTipoDato() 
		{return nombreTipoDato;
		}
		public int getNumero()
		{return numero;
		}
}
		
	
	@Column(length=10)
	private String tipoDeDato;
	
	@Column(length=40,nullable=false,unique=true)
	private String parametro;
	
	@Column(length=40)
	private String unidaDeMedida;
	
	@Column(length=50)
	private String descripcion;
	
	@Column(length=2)
	private Obligatoria obligatoria; 
	
	@Column(length=10)
	private Estado estado;
	
		
	@ManyToOne (fetch = FetchType.EAGER)
	private Usuario usuario;
	

	@ManyToMany 
	(mappedBy = "casillas" ,
	cascade = {	CascadeType.MERGE } ,
	fetch = FetchType.LAZY)
	private List<Formulario> formularios = new ArrayList<>();
	
	@ManyToMany (mappedBy = "casillas" ,
	cascade = {CascadeType.MERGE} , 
	fetch = FetchType.LAZY)
	private List<EstacionDeMedicion> estaciones;
	
	@OneToMany (
			mappedBy = "casilla" ,
			cascade = CascadeType.ALL ,
			orphanRemoval = true
			)
	private List<Registro> registros = new ArrayList<Registro>();
	
	public Casilla() {
		super();
	}

	
	
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Estado getEstado() {
		return estado;
	}



	public void setEstado(Estado estado) {
		this.estado = estado;
	}



	public Obligatoria getObligatoria() {
		return obligatoria;
	}



	public void setObligatoria(Obligatoria obligatoria) {
		this.obligatoria = obligatoria;
	}



	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getUnidaDeMedida() {
		return unidaDeMedida;
	}

	public void setUnidaDeMedida(String unidaDeMedida) {
		this.unidaDeMedida = unidaDeMedida;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Formulario> getFormularios() {
		return formularios;
	}



	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}



	public List<EstacionDeMedicion> getEstaciones() {
		return estaciones;
	}



	public void setEstaciones(List<EstacionDeMedicion> estaciones) {
		this.estaciones = estaciones;
	}

	public Casilla(String nombre, String parametro, String unidaDeMedida) {
		super();
		this.nombre = nombre;
		this.parametro = parametro;
		this.unidaDeMedida = unidaDeMedida;
		this.estado = Estado.HABILITADO;
		this.obligatoria = Obligatoria.NO;
	}
	
	



	public Casilla(String nombre, String tipoDeDato, String parametro, 
			String unidaDeMedida, String descripcion, Usuario usuario) 
	{
		super();
		this.nombre = nombre;
		this.tipoDeDato = tipoDeDato;
		this.parametro = parametro;
		this.unidaDeMedida = unidaDeMedida;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.estado = Estado.HABILITADO;
		this.obligatoria = Obligatoria.NO;
		
	}
	public Casilla(String nombre, String tipoDeDato, String parametro, 
			String unidaDeMedida, String descripcion) 
	{
		super();
		this.nombre = nombre;
		this.tipoDeDato = tipoDeDato;
		this.parametro = parametro;
		this.unidaDeMedida = unidaDeMedida;
		this.descripcion = descripcion;
		this.estado = Estado.HABILITADO;
		this.obligatoria = Obligatoria.NO;
	}

	public String getTipoDeDato() {
		return tipoDeDato;
	}



	public void setTipoDeDato(String tipoDeDato) {
		this.tipoDeDato = tipoDeDato;
	}



	public List<Registro> getRegistros() {
		return registros;
	}



	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}



	@Override
	public String toString() {
		return "Casilla [id=" + id + ", nombre=" + nombre + ", parametro=" + parametro + "]";
	}

	
	
}
