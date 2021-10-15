package com.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Formulario
 *
 */
@MappedSuperclass
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
public class Formulario implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "SEQ_FOR", sequenceName="SEQ_FOR_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FOR")
	protected Long ID_FORMULARIO;
	
	@Column(length=30, unique=true, nullable=false)
	private String NOMBRE;
	
	@ManyToOne
	private Aficionado aficionado;
	
	@ManyToOne
	private Administrador administrador;
	
	@ManyToOne
	private Investigador investigador;
	
	private Date FECHA_HORA;
	
	@Column(length=100)
	private String RESUMEN;
	
	@ManyToMany
	private List<Casilla> casillas;
	
	public List<Casilla> getCasillas() {
		return casillas;
	}

	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}

	public Formulario() {
		super();
	}

	public Long getID_FORMULARIO() {
		return ID_FORMULARIO;
	}

	public void setID_FORMULARIO(Long iD_FORMULARIO) {
		ID_FORMULARIO = iD_FORMULARIO;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public Aficionado getAficionado() {
		return aficionado;
	}

	public void setAficionado(Aficionado aficionado) {
		this.aficionado = aficionado;
	}

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

	public Date getFECHA_HORA() {
		return FECHA_HORA;
	}

	public void setFECHA_HORA(Date fECHA_HORA) {
		FECHA_HORA = fECHA_HORA;
	}

	public String getRESUMEN() {
		return RESUMEN;
	}

	public void setRESUMEN(String rESUMEN) {
		RESUMEN = rESUMEN;
	} 
	
   
}
