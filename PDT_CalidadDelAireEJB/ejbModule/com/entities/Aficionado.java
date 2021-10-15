package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Aficionado
 *
 */
@MappedSuperclass
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
public class Aficionado extends Usuario {

private static final long serialVersionUID = 1L;	
	
//@Id
//@SequenceGenerator(name = "SEQ_AFI", sequenceName="SEQ_AFI_seq",initialValue = 1, allocationSize = 1)
//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AFI")

@Column(length=30, unique=true, nullable=false)
private Long ID_AFICIONADO;

//@Column(length=30, unique=true, nullable=false) 
//private Long ID_USUARIO;	

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
