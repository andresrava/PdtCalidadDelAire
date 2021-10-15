package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity
public class Usuario implements Serializable {

	
	private static final long serialVersionUID = 1L;	

	@Id
	@SequenceGenerator(name = "SEQ_USU", sequenceName="SEQ_USU_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USU")
	private Long ID_USUARIO;
	
	@Column(length=30, unique=true, nullable=false) 
	private String MAIL;
	
	@Column(length=30, unique=true, nullable=false) 
	private String NOMBUSUARIO;
	
	@Column(length=20, nullable=false) 
	private String CONTRASEÑA;
	
	@Column(length=30) 
	private String NOMBRE;
	
	@Column(length=30) 
	private String APELLIDO;
	
	public Usuario() {
		super();
	}

	public Usuario(String mAIL, String nOMBUSUARIO, String cONTRASEÑA, String nOMBRE, String aPELLIDO) {
		super();
		MAIL = mAIL;
		NOMBUSUARIO = nOMBUSUARIO;
		CONTRASEÑA = cONTRASEÑA;
		NOMBRE = nOMBRE;
		APELLIDO = aPELLIDO;
	}

	public Long getID_USUARIO() {
		return ID_USUARIO;
	}

	public void setID_USUARIO(Long iD_USUARIO) {
		ID_USUARIO = iD_USUARIO;
	}

	public String getMAIL() {
		return MAIL;
	}

	public void setMAIL(String mAIL) {
		MAIL = mAIL;
	}

	public String getNOMBUSUARIO() {
		return NOMBUSUARIO;
	}

	public void setNOMBUSUARIO(String nOMBUSUARIO) {
		NOMBUSUARIO = nOMBUSUARIO;
	}

	public String getCONTRASEÑA() {
		return CONTRASEÑA;
	}

	public void setCONTRASEÑA(String cONTRASEÑA) {
		CONTRASEÑA = cONTRASEÑA;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	public String getAPELLIDO() {
		return APELLIDO;
	}

	public void setAPELLIDO(String aPELLIDO) {
		APELLIDO = aPELLIDO;
	} 

	 
	
   
}
