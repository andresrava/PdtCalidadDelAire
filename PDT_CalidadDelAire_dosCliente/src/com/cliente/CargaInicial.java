package com.cliente;

import javax.naming.NamingException;

import com.controlador.GestionCasillas;
import com.controlador.GestionCiudades;
import com.controlador.GestionEstaciones;
import com.controlador.GestionUsuarios;
import com.entities.Administrador;
import com.entities.Aficionado;
import com.entities.Casilla;
import com.entities.Ciudad;
import com.entities.Ciudad.NombresEnum;
import com.entities.EstacionDeMedicion;
import com.entities.Investigador;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

public class CargaInicial {

	public void cargaInicial() throws NamingException, ServiciosException {
		
		GestionUsuarios gestionUsuarios = new GestionUsuarios();
		
		//Comienza la carga de un Usuario
		Usuario usuario = new Usuario("Andrés" , "Rava" , "mimail" , "miclave");
	
		usuario = gestionUsuarios.creaUsuario(usuario);
		
		//Comienza carga 2 Administradores 
		Administrador administrador1 = new Administrador("Adminnombre1" , "AdminApellido1" ,  "AdminMail1" , "AdminClave1" , "AdmDocu1" ,"AdminDomic1" , "AdminTel1");
		Administrador administrador2 = new Administrador("Adminnombre2" , "AdminApellido2" ,  "AdminMail2" , "AdminClave2" , "AdmDocu2" ,"AdminDomic2" , "AdminTel2");
		Administrador administrador3 = new Administrador("Adminnombre3" , "AdminApellido3" ,  "mailAdmin" , "claveAdmin" , "AdmDocu3" ,"AdminDomic3" , "AdminTel3");
		
		gestionUsuarios.crearAdministrador(administrador1);
		gestionUsuarios.crearAdministrador(administrador2);
		gestionUsuarios.crearAdministrador(administrador3);
		
		//Comienza carga 4 Investigadores 
		
		Investigador investigador1 = new Investigador( "Investnombre1" ,"InvestApellido1" , "InvestMail1" , "InvestClave1" , "InvDocu1" ,"InvestDomic1" , "InvestTel1");
		Investigador investigador2 = new Investigador( "Investnombre2" ,"InvestApellido2" , "InvestMail2" , "InvestClave2" , "InvDocu2" ,"InvestDomic2" , "InvestTel2");
		Investigador investigador3 = new Investigador( "Investnombre3" ,"InvestApellido3" , "InvestMail3" , "InvestClave3" , "InvDocu3" ,"InvestDomic3" , "InvestTel3");
		Investigador investigador4 = new Investigador( "Investnombre4" ,"InvestApellido4" , "mailInvest" , "claveInvest" , "InvDocu4" ,"InvestDomic4" , "InvestTel4");
		
			
		investigador1 = gestionUsuarios.crearInvestigador(investigador1);
		investigador2 = gestionUsuarios.crearInvestigador(investigador2);
		investigador3 = gestionUsuarios.crearInvestigador(investigador3);
		investigador4 = gestionUsuarios.crearInvestigador(investigador4);
			
				
		//Comienza la carga de 4 Aficionados
		Aficionado aficionado1 = new Aficionado("AficApellido1" , "Aficnombre1" , "AficMail1" , "AficClave1");
		Aficionado aficionado2 = new Aficionado("AficApellido2" , "Aficnombre2" , "AficMail2" , "AficClave2");
		Aficionado aficionado3 = new Aficionado("AficApellido3" , "Aficnombre3" , "AficMail3" , "AficClave3");
		Aficionado aficionado4 = new Aficionado("AficApellido4" , "Aficnombre4" , "mailAfic" , "claveAfic");
		
		aficionado1 = gestionUsuarios.crearAficionado(aficionado1);
		aficionado2 = gestionUsuarios.crearAficionado(aficionado2);
		aficionado3 = gestionUsuarios.crearAficionado(aficionado3);
		aficionado4 = gestionUsuarios.crearAficionado(aficionado4);
		System.out.println("Aficionado1: " + aficionado1);
		
		//Comienza la carga de 4 Casillas
		
		Casilla casilla1 = new Casilla("Primer Casilla" , "humedad" , "%");
		Casilla casilla2 = new Casilla("Segunda Casilla" , "temperatura" , "ºC");
		Casilla casilla3 = new Casilla("Tercera Casilla" , "viento" , "km/h");
		Casilla casilla4 = new Casilla("Cuarta Casilla" , "presión atm." , "HPa");
		
		
		
		GestionCasillas gestionCasilla = new GestionCasillas();
		casilla1 = gestionCasilla.crearCasilla(casilla1);
		Long idCasilla1 = casilla1.getId();
		System.out.println("Id de la Casilla1: " + idCasilla1);
		casilla2 = gestionCasilla.crearCasilla(casilla2);
		casilla3 = gestionCasilla.crearCasilla(casilla3);
		casilla4 = gestionCasilla.crearCasilla(casilla4);
		
		 //Comienza la carga de dos Ciudades
		
		Ciudad primera = new Ciudad("Santa Marta" , NombresEnum.TREINTA_Y_TRES);
		Ciudad segunda = new Ciudad("Mamboretá" , NombresEnum.COLONIA);
		
		GestionCiudades gestionCiudades = new GestionCiudades();
		primera = gestionCiudades.creaCiudad(primera);
		segunda = gestionCiudades.creaCiudad(segunda);
		
		//Comienza la carga de 1 Estaciones de Medición
		
		EstacionDeMedicion em1 = new EstacionDeMedicion("Primera_EM ",primera,usuario);
					
		GestionEstaciones gestionEstaciones = new GestionEstaciones();
		em1 = gestionEstaciones.crearEstacion(em1);
		Long idEstacion = em1.getId();
		Long idCasilla = casilla1.getId();
		System.out.println(idEstacion);			
		System.out.println(idCasilla);			
		System.out.println(idCasilla);
		
	}
}
