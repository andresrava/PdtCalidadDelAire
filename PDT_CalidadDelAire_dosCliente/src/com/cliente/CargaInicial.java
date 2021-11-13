package com.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Administrador;
import com.entities.Aficionado;
import com.entities.Casilla;
import com.entities.Investigador;
import com.exceptions.ServiciosException;
import com.services.AdministradoresBeanRemote;
import com.services.AficionadosBeanRemote;
import com.services.CasillasBean;
import com.services.CasillasBeanRemote;
import com.services.InvestigadoresBeanRemote;

public class CargaInicial {

	public void cargaInicial() throws NamingException {
		
		//Comienza carga 2 Administradores 
		Administrador administrador1 = new Administrador("Adminnombre1" , "AdminApellido1" ,  "AdminMail1" , "AdminClave1" , "AdmDocu1" ,"AdminDomic1" , "AdminTel1");
		Administrador administrador2 = new Administrador("Adminnombre2" , "AdminApellido2" ,  "AdminMail2" , "AdminClave2" , "AdmDocu2" ,"AdminDomic2" , "AdminTel2");
		Administrador administrador3 = new Administrador("Adminnombre3" , "AdminApellido3" ,  "mail_valido" , "clave_valida" , "AdmDocu3" ,"AdminDomic3" , "AdminTel3");
		System.out.println("Creó los administradores");
		
		String ruta1 = "PDT_CalidadDelAire_dosEJB/AdministradoresBean!com.services.AdministradoresBeanRemote";
		
		AdministradoresBeanRemote administradorBean = (AdministradoresBeanRemote)
					InitialContext.doLookup(ruta1);
		System.out.println(administrador1.toString());
		System.out.println(administrador2.toString());
		try {
			administradorBean.crear(administrador1);
			System.out.println("Se creó el Administrador1");
			administradorBean.crear(administrador2);
			System.out.println("Se creó el Administrador2");
			administradorBean.crear(administrador3);
			System.out.println("Se creó el Administrador3");
			
			
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(administrador1.toString());
		
		//Comienza carga 4 Investigadores 
		
		Investigador investigador1 = new Investigador("InvestApellido1" , "Investnombre1" , "InvestMail1" , "InvestClave1" , "InvDocu1" ,"InvestDomic1" , "InvestTel1");
		Investigador investigador2 = new Investigador("InvestApellido2" , "Investnombre2" , "InvestMail2" , "InvestClave2" , "InvDocu2" ,"InvestDomic2" , "InvestTel2");
		Investigador investigador3 = new Investigador("InvestApellido3" , "Investnombre3" , "InvestMail3" , "InvestClave3" , "InvDocu3" ,"InvestDomic3" , "InvestTel3");
		Investigador investigador4 = new Investigador("InvestApellido4" , "Investnombre4" , "InvestMail4" , "InvestClave4" , "InvDocu4" ,"InvestDomic4" , "InvestTel4");
		
		System.out.println(investigador1.toString());
		System.out.println(investigador2.toString());
		System.out.println(investigador3.toString());
		System.out.println(investigador4.toString());
		
		String ruta2 = "PDT_CalidadDelAire_dosEJB/InvestigadoresBean!com.services.InvestigadoresBeanRemote";
		
		InvestigadoresBeanRemote investigadorBean = (InvestigadoresBeanRemote)
					InitialContext.doLookup(ruta2);
		
		
		try {
			investigadorBean.crear(investigador1);
			investigadorBean.crear(investigador2);
			investigadorBean.crear(investigador3);
			investigadorBean.crear(investigador4);
			System.out.println("Se crearon los 4 Investigadores");
			
			
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(investigador1.toString());
		
		//Comienza la carga de 4 Aficionados
		Aficionado aficionado1 = new Aficionado("AficApellido1" , "Aficnombre1" , "AficMail1" , "AficClave1");
		Aficionado aficionado2 = new Aficionado("AficApellido2" , "Aficnombre2" , "AficMail2" , "AficClave2");
		Aficionado aficionado3 = new Aficionado("AficApellido3" , "Aficnombre3" , "AficMail3" , "AficClave3");
		Aficionado aficionado4 = new Aficionado("AficApellido4" , "Aficnombre4" , "AficMail4" , "AficClave4");
		
		String ruta3 = "PDT_CalidadDelAire_dosEJB/AficionadosBean!com.services.AficionadosBeanRemote";
		
		AficionadosBeanRemote aficionadoBean = (AficionadosBeanRemote)
					InitialContext.doLookup(ruta3);
		System.out.println(administrador1.toString());
		System.out.println(administrador2.toString());
		try {
			aficionadoBean.crear(aficionado1);
			aficionadoBean.crear(aficionado2);
			aficionadoBean.crear(aficionado3);
			aficionadoBean.crear(aficionado4);
			
			System.out.println("Se crearon los 4 Aficionados");
			
			
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(administrador1.toString());
		
		//Comienza la carga de 2 Casillas
		
//		Casilla casilla1 = new Casilla("Primer Casilla" , "humedad" , "%");
//		Casilla casilla2 = new Casilla("Segunda Casilla" , "temperatura" , "ºC");
//		String ruta4 = "PDT_CalidadDelAire_dosEJB/CasillasBean!com.services.CasillasBeanRemote";
//		CasillasBeanRemote casillaBean = (CasillasBeanRemote)
//				InitialContext.doLookup(ruta4);
//		try {
//			casillaBean.crear(casilla1);
//			casillaBean.crear(casilla2);
//		} catch (ServiciosException e) {
//			System.out.println(e.getMessage());
//		}
		
	}
}
