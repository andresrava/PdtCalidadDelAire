package com.entities;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Aficionado
 *
 */
//@MappedSuperclass
//@Entity
//@Inheritance( strategy = InheritanceType.JOINED )
public class Aficionado extends Usuario {

private static final long serialVersionUID = 1L;	
	
@Column(length=30, unique=true, nullable=false)
private Long ID_AFICIONADO;



public Aficionado() {
	super();
}

public Long getID_AFICIONADO() {
	return ID_AFICIONADO;
}

public void setID_AFICIONADO(Long iD_AFICIONADO) {
	ID_AFICIONADO = iD_AFICIONADO;
}

public Long getID_USUARIO() {
	return ID_USUARIO;
}

public void setID_USUARIO(Long iD_USUARIO) {
	ID_USUARIO = iD_USUARIO;
} 

	
   
}
