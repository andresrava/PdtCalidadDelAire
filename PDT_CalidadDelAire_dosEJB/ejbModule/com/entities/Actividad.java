package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.enumerados.Enumerados.Estado;
import com.enumerados.Enumerados.Ingreso;


@Entity
@Inheritance( strategy = InheritanceType.JOINED )
@NamedQuery(name="Actividad.obtenerTodos", query="SELECT a FROM Actividad a")

public class Actividad implements Serializable {


	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACT" )
	@SequenceGenerator(name = "SEQ_ACT", initialValue = 1, allocationSize = 1)
	private Long id;


	@Column
	private Estado estado;
	
	@Column
	private Ingreso ingreso;
	
	@ManyToOne (fetch = FetchType.EAGER)
	private Formulario formulario;
	
	@OneToMany (
			mappedBy = "actividad" ,
			cascade = CascadeType.ALL ,
			orphanRemoval = true)
	private List<Registro> registros;
	
	@ManyToOne (fetch = FetchType.LAZY)
	private Usuario usuario;
	
	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Formulario getFormulario() {
		return formulario;
	}

	public void setFormulario(Formulario formulario) {
		this.formulario = formulario;
	}

	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Ingreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Actividad [id=" + id + ", estado=" + estado + ", formulario=" + formulario + ", usuario=" + usuario + "]";
	}

	public Actividad() {
		super();
		this.estado = Estado.HABILITADO;
		this.ingreso = Ingreso.NORMAL;
	} 
	
	public Actividad(Formulario formulario, Usuario usuario) {
		super();
		this.formulario = formulario;
		this.usuario = usuario;
		this.estado = Estado.HABILITADO;
		this.ingreso = Ingreso.NORMAL;
	}

	

}
