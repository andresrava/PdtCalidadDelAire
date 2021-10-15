package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Em
 *
 */
@MappedSuperclass
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
public class Em implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "SEQ_EM", sequenceName="SEQ_EM_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EM")
	private Long ID_EM;
	
	@Column(length=30, unique=true, nullable=false) 
	private String NOMBRE;
	
	@Column(length=30, unique=true, nullable=false) 
	private String PARAMETRO;
	
	@Column(length=30, unique=true, nullable=false) 
	private String UNIDADMEDIDA;
	
	@Column(length=30, unique=true, nullable=false) 
	private String DESCRIPCION;
	
	@Column(length=30, unique=true, nullable=false) 
	private String TIPOVALOR;
	
	@ManyToOne
	private Ciudad ciudad;
	
	@ManyToOne
	private Departamento departamento;
	
	@ManyToOne
	private Investigador investigador;
	
	@OneToMany
	private List<Usuario> usuarios;
	
	
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}





	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}





	public Long getID_EM() {
		return ID_EM;
	}





	public void setID_EM(Long iD_EM) {
		ID_EM = iD_EM;
	}





	public String getNOMBRE() {
		return NOMBRE;
	}





	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}





	public String getPARAMETRO() {
		return PARAMETRO;
	}





	public void setPARAMETRO(String pARAMETRO) {
		PARAMETRO = pARAMETRO;
	}





	public String getUNIDADMEDIDA() {
		return UNIDADMEDIDA;
	}





	public void setUNIDADMEDIDA(String uNIDADMEDIDA) {
		UNIDADMEDIDA = uNIDADMEDIDA;
	}





	public String getDESCRIPCION() {
		return DESCRIPCION;
	}





	public void setDESCRIPCION(String dESCRIPCION) {
		DESCRIPCION = dESCRIPCION;
	}





	public String getTIPOVALOR() {
		return TIPOVALOR;
	}





	public void setTIPOVALOR(String tIPOVALOR) {
		TIPOVALOR = tIPOVALOR;
	}





	public Ciudad getCiudad() {
		return ciudad;
	}





	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}





	public Departamento getDepartamento() {
		return departamento;
	}





	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}





	public Investigador getInvestigador() {
		return investigador;
	}





	public void setInvestigador(Investigador investigador) {
		this.investigador = investigador;
	}





	public Em() {
		super();
	} 
	
   
}
