package com.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
@Inheritance( strategy = InheritanceType.JOINED )
@NamedQuery(name="Actividad.obtenerTodos", query="SELECT a FROM Actividad a")

public class Actividad implements Serializable {


	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACT" )
	@SequenceGenerator(name = "SEQ_ACT", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne (fetch = FetchType.LAZY)
	private Formulario formulario;
	
	@ManyToOne (fetch = FetchType.LAZY)
	private Administrador administrador;
	
	@ManyToOne (fetch = FetchType.LAZY)
	private Investigador investigador;
	
	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Investigador getInvestigador() {
		return investigador;
	}

	public void setInvestigador(Investigador investigador) {
		this.investigador = investigador;
	}

	public Aficionado getAficionado() {
		return aficionado;
	}

	public void setAficionado(Aficionado aficionado) {
		this.aficionado = aficionado;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	private Aficionado aficionado;
	
	
	@Column(nullable=false)
	private Date fechaHora;
	
	@OneToMany (
			mappedBy = "actividad" ,
			cascade = CascadeType.ALL ,
			orphanRemoval = true)
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

	public Actividad() {
		super();
	} 
	
	@Override
	public String toString() {
		return "Actividad [id=" + id + ", formulario=" + formulario + ", Investigador= " + investigador + ", administrador=" + administrador + ", fechaHora="
				+ fechaHora + ", registros=" + registros + "]";
	}
   
}
