package com.cliente;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

import com.controlador.GestionActividades;
import com.controlador.GestionCasillas;
import com.controlador.GestionCiudades;
import com.controlador.GestionEstaciones;
import com.controlador.GestionFormularios;
import com.controlador.GestionLocalidades;
import com.controlador.GestionRegistros;
import com.controlador.GestionUsuarios;
import com.entities.Actividad;
import com.entities.Administrador;
import com.entities.Aficionado;
import com.entities.Casilla;
import com.entities.Casilla.TipoDatoEnum;
import com.entities.Ciudad;
import com.entities.Ciudad.NombresEnum;
import com.entities.EstacionDeMedicion;
import com.entities.Formulario;
import com.entities.Investigador;
import com.entities.Registro;
import com.entities.Usuario;
import com.enumerados.BorradoLogico.Estado;
import com.enumerados.BorradoLogico.Obligatoria;
import com.exceptions.ServiciosException;

public class CargaInicial {

	public void cargaInicial() throws NamingException, ServiciosException {
		
		GestionUsuarios gestionUsuarios = new GestionUsuarios();
		
		//Comienza la carga de un Usuario
		Usuario usuario = new Usuario("Andrés" , "Rava" , "mimail" , "miclave", Estado.HABILITADO);
	
		usuario = gestionUsuarios.creaUsuario(usuario);
		
		//Comienza carga Administradores 
		Administrador administrador1 = new Administrador("Adminnombre1" , "AdminApellido1" ,  "AdminMail1" , "AdminClave1", Estado.HABILITADO, "AdmDocu1" ,"AdminDomic1" , "AdminTel1", "Bella Unión", "ARTIGAS");
		Administrador administrador2 = new Administrador("Adminnombre2" , "AdminApellido2" ,  "AdminMail2" , "AdminClave2" , Estado.HABILITADO, "AdmDocu2" ,"AdminDomic2" , "AdminTel2", "Bella Unión", "ARTIGAS");
		Administrador administrador3 = new Administrador("Adminnombre3" , "AdminApellido3" ,  "mailAdmin" , "claveAdmin" , Estado.HABILITADO, "AdmDocu3" ,"AdminDomic3" , "AdminTel3", "Bella Unión","ARTIGAS");
		Administrador administrador4 = new Administrador("Adminnombre4" , "AdminApellido4" ,  "123" , "123" , Estado.HABILITADO, "AdmDocu4" ,"AdminDomic4" , "AdminTel4", "Bella Unión","ARTIGAS");

		administrador1 = gestionUsuarios.crearAdministrador(administrador1);
		administrador2 = gestionUsuarios.crearAdministrador(administrador2);
		administrador3 = gestionUsuarios.crearAdministrador(administrador3);
		administrador4 = gestionUsuarios.crearAdministrador(administrador4);
		
		List<Administrador> listaAdministrador = gestionUsuarios.obtieneAdministradores();
		System.out.println("Administradores creados:");
		System.out.println(listaAdministrador);
		Long idAdmin4 = administrador4.getId();
		System.out.println("Id Admin4 creado: " + idAdmin4);
		Administrador adminEncontrado = (Administrador) gestionUsuarios.obtienePorId(idAdmin4);
		System.out.println("Administrador encontrado por id:");
		System.out.println(adminEncontrado);
		
		
		//Comienza carga 4 Investigadores 
		
		Investigador investigador1 = new Investigador( "Investnombre1" ,"InvestApellido1" , "InvestMail1" , "InvestClave1", Estado.HABILITADO , "InvDocu1" ,"InvestDomic1" , "InvestTel1",  "Bella Unión", "ARTIGAS");
		Investigador investigador2 = new Investigador( "Investnombre2" ,"InvestApellido2" , "InvestMail2" , "InvestClave2", Estado.HABILITADO , "InvDocu2" ,"InvestDomic2" , "InvestTel2", "Bella Unión", "ARTIGAS");
		Investigador investigador3 = new Investigador( "Investnombre3" ,"InvestApellido3" , "InvestMail3" , "InvestClave3", Estado.HABILITADO , "InvDocu3" ,"InvestDomic3" , "InvestTel3", "Bella Unión", "ARTIGAS");
		Investigador investigador4 = new Investigador( "Investnombre4" ,"InvestApellido4" , "mailInvest" , "claveInvest", Estado.HABILITADO , "InvDocu4" ,"InvestDomic4" , "InvestTel4", "Bella Unión", "ARTIGAS");
		
			
		investigador1 = gestionUsuarios.crearInvestigador(investigador1);
		investigador2 = gestionUsuarios.crearInvestigador(investigador2);
		investigador3 = gestionUsuarios.crearInvestigador(investigador3);
		investigador4 = gestionUsuarios.crearInvestigador(investigador4);
			
		List<Investigador> listaInvestigador = gestionUsuarios.obtieneInvestigadores();
		System.out.println("Investigadores creados:");
		System.out.println(listaInvestigador);
		Long idInvest4 = investigador4.getId();
		System.out.println("Id Invest4 creado: " + idInvest4);
		Investigador investEncontrado = (Investigador) gestionUsuarios.obtienePorId(idInvest4);
		System.out.println("Investigador encontrado por id:");
		System.out.println(investEncontrado);
				
		//Comienza la carga de 4 Aficionados
		Aficionado aficionado1 = new Aficionado( "Aficnombre1" , "AficApellido1" , "AficMail1" , "AficClave1", Estado.HABILITADO);
		Aficionado aficionado2 = new Aficionado( "Aficnombre2" , "AficApellido2" ,"AficMail2" , "AficClave2", Estado.HABILITADO);
		Aficionado aficionado3 = new Aficionado( "Aficnombre3" , "AficApellido3" ,"AficMail3" , "AficClave3", Estado.HABILITADO);
		Aficionado aficionado4 = new Aficionado( "Aficnombre4" , "AficApellido4" , "mailAfic" , "claveAfic", Estado.HABILITADO);
		
		aficionado1 = gestionUsuarios.crearAficionado(aficionado1);
		aficionado2 = gestionUsuarios.crearAficionado(aficionado2);
		aficionado3 = gestionUsuarios.crearAficionado(aficionado3);
		aficionado4 = gestionUsuarios.crearAficionado(aficionado4);
		
		List<Aficionado> listaAficionado = gestionUsuarios.obtieneAficionados();
		System.out.println("Aficionados creados:");
		System.out.println(listaAficionado);
		Long idAficionado4 = aficionado4.getId();
		System.out.println("Id Afic4 creado: " + idAficionado4);
		Aficionado aficionadoEncontrado = (Aficionado) gestionUsuarios.obtienePorId(idAficionado4);
		System.out.println("Aficionado encontrado por id:");
		System.out.println(aficionadoEncontrado);
		
		
		//Comienza la carga de 3 Casillas
		
		Casilla casilla1 = new Casilla("Primer Casilla" , TipoDatoEnum.FLOAT , "humedad" , "%" , "Vimos" , usuario );
		Casilla casilla2 = new Casilla("Segunda Casilla" , TipoDatoEnum.INTEGER , "temperatura" , "ºC" , "Visto");
		Casilla casilla3 = new Casilla("Tercera Casilla" , TipoDatoEnum.STRING , "viento" , "km/h" , Obligatoria.SI);
		Casilla casilla4 = new Casilla("Cuarta Casilla" , TipoDatoEnum.FLOAT , "presión atm." , "HPa" , Obligatoria.SI);
		
		
		
		GestionCasillas gestionCasilla = new GestionCasillas();
		Casilla casilla1pos = gestionCasilla.crearCasilla(casilla1);
		Casilla casilla2pos = gestionCasilla.crearCasilla(casilla2);
		Casilla casilla3pos = gestionCasilla.crearCasilla(casilla3);
		Casilla casilla4pos = gestionCasilla.crearCasilla(casilla4);
		List<Casilla> listaCasillas = gestionCasilla.listaCasillas();
		System.out.println("Casillas creadas:");
		System.out.println(listaCasillas);
//		List<Casilla> otraListaCasillas = new LinkedList<>();
//		otraListaCasillas.add(listaCasillas.get(0));
//		otraListaCasillas.add(listaCasillas.get(1));
		 //Comienza la carga de dos Ciudades
		
		Ciudad primera = new Ciudad("Santa Marta" , NombresEnum.TREINTA_Y_TRES);
		Ciudad segunda = new Ciudad("Mamboretá" , NombresEnum.COLONIA);
		
		GestionCiudades gestionCiudades = new GestionCiudades();
		primera = gestionCiudades.creaCiudad(primera);
		segunda = gestionCiudades.creaCiudad(segunda);
		
		
		//Comienza la carga de 2 Estaciones de Medición
		
		EstacionDeMedicion em1 = new EstacionDeMedicion("Primera_EM ", "TREINTA Y TRES", "Vergara" , usuario);
		EstacionDeMedicion em2 = new EstacionDeMedicion("Segunda_EM ", "LAVALLEJA" , "Piraraja" , administrador1);
					
		GestionEstaciones gestionEstaciones = new GestionEstaciones();
		
		em1 = gestionEstaciones.crearEstacion(em1);
		em2 = gestionEstaciones.crearEstacion(em2);
		List<EstacionDeMedicion> listaEM = gestionEstaciones.obtieneEM();
		System.out.println("EM creadas:");
		System.out.println(listaEM);
		
				
		//Comienza la carga de 2 formularios
		List<Casilla> lista1 = new LinkedList<>();
		lista1.add(listaCasillas.get(0));
		lista1.add(listaCasillas.get(2));
		lista1.add(listaCasillas.get(3));
		List<Casilla> lista2 = new LinkedList<>();
		lista2.add(listaCasillas.get(1));
		lista2.add(listaCasillas.get(2));
		lista2.add(listaCasillas.get(3));
		Formulario form1 = new Formulario("PrimerFormulario" , lista1 , investigador1);
		Formulario form2 = new Formulario("SegundoFormulario", lista2 , investigador2 );
		System.out.println("Formulario 1 Carga inicial antes: " + form1);
		
		GestionFormularios gestionFormularios = new GestionFormularios();
		form1 = gestionFormularios.crearFormulario(form1);
		form2 = gestionFormularios.crearFormulario(form2);
//		System.out.println("Formulario 1 Carga inicial después: " + form1Creado);
//		Long ideForm1 = form1Creado.getId();
//		System.out.println("Ide de form1Creado: " + ideForm1);
		
		List<Formulario> formularios = gestionFormularios.listaFormularios();
		System.out.println("los formularios creados son: ");
		System.out.println(formularios);
		  
		//Comienza la carga de 2 Actividades
		Actividad actividad1 = new Actividad(form2 , usuario);
		Actividad actividad2 = new Actividad(form2 , usuario);
		GestionActividades gestionActividades = new GestionActividades();
		actividad1 = gestionActividades.crearActividad(actividad1);
		actividad2 = gestionActividades.crearActividad(actividad2);
			
		
		//Comienza la carga de 6 registros
		 long miliseconds = System.currentTimeMillis();
	        Date date = new Date(miliseconds);
	        System.out.println("El datees: " + date);
	        
        Registro registro1 = new Registro("fuerte" , (float) 23.1 , (float) -33.6 , date , Estado.HABILITADO );
	    Registro registro2 = new Registro("18" , (float) 20.1 , (float) -43.6 , date , Estado.HABILITADO );
	    Registro registro3 = new Registro("1568.4" , (float) 23.1 , (float) -33.6 , date , Estado.HABILITADO );
	    Registro registro4 = new Registro("poco" , (float) 23.1 , (float) -33.6 , date , Estado.HABILITADO );
	    Registro registro5 = new Registro("21" , (float) 23.1 , (float) -33.6 , date , Estado.HABILITADO );
	    Registro registro6 = new Registro("1024.3" , (float) 23.1 , (float) -33.6 , date , Estado.HABILITADO );
	    
	    GestionRegistros gestionRegistros = new GestionRegistros();
	    GestionCasillas gestionCasillas = new GestionCasillas();
	    
	    actividad1 = gestionActividades.obtienePorId(actividad1.getId());
		registro1.setActividad(actividad1);
		registro1.setCasilla(listaCasillas.get(2)); 
	    registro1 = gestionRegistros.crearRegistro(registro1);
	    System.out.println("registro1: ");
	    System.out.println(registro1);
	    
	    registro2.setActividad(actividad1);
	    registro2.setCasilla(listaCasillas.get(1));
	    registro2 = gestionRegistros.crearRegistro(registro2);
	    
	    
	    registro3.setActividad(actividad1);
	    registro3.setCasilla(listaCasillas.get(3));
	    registro3 = gestionRegistros.crearRegistro(registro3);
	    
	    actividad2 = gestionActividades.obtienePorId(actividad2.getId());
		   
	    registro4.setActividad(actividad2);
	    registro4.setCasilla(listaCasillas.get(2));
	    registro4 = gestionRegistros.crearRegistro(registro4);
	    
	    registro5.setActividad(actividad2);
	    registro5.setCasilla(listaCasillas.get(1));
	    registro5 = gestionRegistros.crearRegistro(registro5);
	    
	    registro6.setActividad(actividad2);
	    registro6.setCasilla(listaCasillas.get(3));
	    registro6 = gestionRegistros.crearRegistro(registro6);
	    
	   
	    System.out.println("Se completó la carga inicial");
		
//		Se muestran los departamentos
		GestionLocalidades gestionLocalidades = new GestionLocalidades();
		Set<String> departamentos = gestionLocalidades.obtieneDepartamentos();
		System.out.println("Los departamentos disponibles son:");
		System.out.println(departamentos);
		Set <String> deptosDeLavalleja = gestionLocalidades.obtieneLocalidades("LAVALLEJA");
		System.out.println("Las localidades de Lavalleja son: ");
		System.out.println(deptosDeLavalleja);
		
	}
}
