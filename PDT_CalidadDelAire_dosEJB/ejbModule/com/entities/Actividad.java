package com.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@MappedSuperclass
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
@NamedQuery(name="Actividad.obtenerTodos", query="SELECT a FROM Actividad a")

public class Actividad implements Serializable {

	
	@Override
	public String toString() {
		return "Actividad [id=" + id + ", formulario=" + formulario + ", usuario=" + usuario + ", fechaHora="
				+ fechaHora + ", registros=" + registros + "]";
	}

	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACT" )
	@SequenceGenerator(name = "SEQ_ACT", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	private Formulario formulario;
	
	@ManyToOne
	private Usuario usuario;
	
	@Column(nullable=false)
	private Date fechaHora;
	
	@OneToMany
	private List<Registro> registros;
	
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Actividad() {
		super();
	} 
	
   
}
