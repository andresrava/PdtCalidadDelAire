package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.ManyToMany;

/**
 * Entity implementation class for Entity: Casilla
 *
 */


	@MappedSuperclass
	@Entity
	@Inheritance( strategy = InheritanceType.JOINED )
	
public class Casilla implements Serializable {
		
		private static final long serialVersionUID = 1L;	
		
		@Id
		@SequenceGenerator(name = "SEQ_CAS", sequenceName="SEQ_CAS_seq",initialValue = 1, allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAS")
		private Long ID_CASILLA;
		
		@Column(length=10, unique=true, nullable=false) 
		private String UNIDADMEDIDA;

	
		@Column(length=50, unique=true, nullable=false) 
		private String DESCRIPCION;
		
		@Column(length=30, unique=true, nullable=false) 
		private String PARAMETRO;
		
		@Column(length=30, unique=true, nullable=false) 
		private String TIPOINPUT;
		
		@Column(length=30, unique=true, nullable=false) 
		private String NOMBRE;
		
		@ManyToMany(mappedBy = "casillas")
		private List<Formulario> formularios;
		
		
	
	public Long getID_CASILLA() {
			return ID_CASILLA;
		}



		public void setID_CASILLA(Long iD_CASILLA) {
			ID_CASILLA = iD_CASILLA;
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



		public String getPARAMETRO() {
			return PARAMETRO;
		}



		public void setPARAMETRO(String pARAMETRO) {
			PARAMETRO = pARAMETRO;
		}



		public String getTIPOINPUT() {
			return TIPOINPUT;
		}



		public void setTIPOINPUT(String tIPOINPUT) {
			TIPOINPUT = tIPOINPUT;
		}



		public String getNOMBRE() {
			return NOMBRE;
		}



		public void setNOMBRE(String nOMBRE) {
			NOMBRE = nOMBRE;
		}



		public List<Formulario> getFormularios() {
			return formularios;
		}



		public void setFormularios(List<Formulario> formularios) {
			this.formularios = formularios;
		}



	public Casilla() {
		super();
	} 
	
   
}
