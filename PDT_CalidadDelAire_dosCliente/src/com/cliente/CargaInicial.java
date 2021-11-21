package com.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Administrador;
import com.entities.Aficionado;
import com.entities.Casilla;
import com.entities.Ciudad;
import com.entities.Ciudad.NombresEnum;
import com.entities.EstacionDeMedicion;
import com.entities.Investigador;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.services.AdministradoresBeanRemote;
import com.services.AficionadosBeanRemote;
import com.services.CasillasBeanRemote;
import com.services.CiudadesBeanRemote;
import com.services.EstacionesDeMedicionBeanRemote;
import com.services.InvestigadoresBeanRemote;
import com.services.UsuariosBeanRemote;

public class CargaInicial {

	public void cargaInicial() throws NamingException {
		
		//Comienza la carga de un Usuario
		Usuario usuario = new Usuario("Andrés" , "Rava" , "mimail" , "miclave");
		
		String ruta = "PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
		UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote)
				InitialContext.doLookup(ruta);
		try {
			usuario = usuariosBean.crear(usuario);
			System.out.println("Se creó el usuario: " + usuario.getNombre() + " " + usuario.getApellido());
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
		
		//Comienza carga 2 Administradores 
		Administrador administrador1 = new Administrador("Adminnombre1" , "AdminApellido1" ,  "AdminMail1" , "AdminClave1" , "AdmDocu1" ,"AdminDomic1" , "AdminTel1");
		Administrador administrador2 = new Administrador("Adminnombre2" , "AdminApellido2" ,  "AdminMail2" , "AdminClave2" , "AdmDocu2" ,"AdminDomic2" , "AdminTel2");
		Administrador administrador3 = new Administrador("Adminnombre3" , "AdminApellido3" ,  "mail_valido" , "clave_valida" , "AdmDocu3" ,"AdminDomic3" , "AdminTel3");
		
		String ruta1 = "PDT_CalidadDelAire_dosEJB/AdministradoresBean!com.services.AdministradoresBeanRemote";
		
		AdministradoresBeanRemote administradorBean = (AdministradoresBeanRemote)
					InitialContext.doLookup(ruta1);
		
		try {
			administradorBean.crear(administrador1);
			administradorBean.crear(administrador2);
			administradorBean.crear(administrador3);
			
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(administrador1.toString());
		
		//Comienza carga 4 Investigadores 
		
		Investigador investigador1 = new Investigador("InvestApellido1" , "Investnombre1" , "InvestMail1" , "InvestClave1" , "InvDocu1" ,"InvestDomic1" , "InvestTel1");
		Investigador investigador2 = new Investigador("InvestApellido2" , "Investnombre2" , "InvestMail2" , "InvestClave2" , "InvDocu2" ,"InvestDomic2" , "InvestTel2");
		Investigador investigador3 = new Investigador("InvestApellido3" , "Investnombre3" , "InvestMail3" , "InvestClave3" , "InvDocu3" ,"InvestDomic3" , "InvestTel3");
		Investigador investigador4 = new Investigador("InvestApellido4" , "Investnombre4" , "InvestMail4" , "InvestClave4" , "InvDocu4" ,"InvestDomic4" , "InvestTel4");
		
			
		String ruta2 = "PDT_CalidadDelAire_dosEJB/InvestigadoresBean!com.services.InvestigadoresBeanRemote";
		
		InvestigadoresBeanRemote investigadorBean = (InvestigadoresBeanRemote)
					InitialContext.doLookup(ruta2);
		
		
		try {
			investigadorBean.crear(investigador1);
			investigadorBean.crear(investigador2);
			investigadorBean.crear(investigador3);
			investigadorBean.crear(investigador4);
			
			
			
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
		
		
		//Comienza la carga de 4 Aficionados
		Aficionado aficionado1 = new Aficionado("AficApellido1" , "Aficnombre1" , "AficMail1" , "AficClave1");
		Aficionado aficionado2 = new Aficionado("AficApellido2" , "Aficnombre2" , "AficMail2" , "AficClave2");
		Aficionado aficionado3 = new Aficionado("AficApellido3" , "Aficnombre3" , "AficMail3" , "AficClave3");
		Aficionado aficionado4 = new Aficionado("AficApellido4" , "Aficnombre4" , "AficMail4" , "AficClave4");
		
		String ruta3 = "PDT_CalidadDelAire_dosEJB/AficionadosBean!com.services.AficionadosBeanRemote";
		
		AficionadosBeanRemote aficionadoBean = (AficionadosBeanRemote)
					InitialContext.doLookup(ruta3);
		
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
		
		Casilla casilla1 = new Casilla("Primer Casilla" , "humedad" , "%");
		Casilla casilla2 = new Casilla("Segunda Casilla" , "temperatura" , "ºC");
		Casilla casilla3 = new Casilla("Tercera Casilla" , "viento" , "km/h");
		Casilla casilla4 = new Casilla("Cuarta Casilla" , "presión atm." , "HPa");
		String ruta4 = "PDT_CalidadDelAire_dosEJB/CasillasBean!com.services.CasillasBeanRemote";
		CasillasBeanRemote casillaBean = (CasillasBeanRemote)
				InitialContext.doLookup(ruta4);
		try {
			casillaBean.crear(casilla1);
			casillaBean.crear(casilla2);
			casillaBean.crear(casilla3);
			casillaBean.crear(casilla4);
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
		 //Comienza la carga de dos Ciudades
		
		Ciudad primera = new Ciudad("Santa Marta" , NombresEnum.TREINTA_Y_TRES);
		Ciudad segunda = new Ciudad("Mamboretá" , NombresEnum.COLONIA);
		System.out.println(primera.toString());
		System.out.println(segunda.toString());
		
		String ruta5 = "PDT_CalidadDelAire_dosEJB/CiudadesBean!com.services.CiudadesBeanRemote";
		CiudadesBeanRemote ciudadBean = (CiudadesBeanRemote)
				InitialContext.doLookup(ruta5);
		
		try {
			ciudadBean.crear(primera);
			ciudadBean.crear(segunda);
			
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
		//Comienza la carga de 4 Estaciones de Medición
		
		
		
		EstacionDeMedicion em1 = new EstacionDeMedicion("Primera EM" , primera );
		System.out.println(em1.toString());
		
		EstacionDeMedicion em2 = new EstacionDeMedicion("Segunda EM" , segunda );
		System.out.println(em2.toString());
		
		EstacionDeMedicion em3 = new EstacionDeMedicion("Tercera EM" , primera );
		System.out.println(em3.toString());
		
		String ruta6 = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		EstacionesDeMedicionBeanRemote estacionesDeMedicionBean = (EstacionesDeMedicionBeanRemote)
				InitialContext.doLookup(ruta6);
		
		try {
			em1 = estacionesDeMedicionBean.crear(em1);
			em2 = estacionesDeMedicionBean.crear(em2);
			em3 = estacionesDeMedicionBean.crear(em3);
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
		Long idUsuario = usuario.getId();
		try {
			estacionesDeMedicionBean.agregarUsuario(em1.getId(), idUsuario);
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
	}
}
