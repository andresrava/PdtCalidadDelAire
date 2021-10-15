package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Ciudad
 *
 */
@MappedSuperclass
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
public class Ciudad implements Serializable {

	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@SequenceGenerator(name = "SEQ_CIU", sequenceName="SEQ_CIU_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CIU")
	private Long ID_CIUDAD;
	
	@Column(length=30, unique=true, nullable=false) 
	private String NOMBRE;
	
	@OneToMany(mappedBy = "ciudad")
	private List<Administrador> administradores;
	
	@OneToMany(mappedBy = "ciudad")
	private List<Investigador> investigadores;
	
	public List<Investigador> getInvestigadores() {
		return investigadores;
	}

	public void setInvestigadores(List<Investigador> investigadores) {
		this.investigadores = investigadores;
	}

	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	public Ciudad() {
		super();
		// TODO Auto-generated constructor stub
	
	}

	public Long getID_CIUDAD() {
		return ID_CIUDAD;
	}

	public void setID_CIUDAD(Long iD_CIUDAD) {
		ID_CIUDAD = iD_CIUDAD;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}
	
   
}
