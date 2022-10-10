package com.cliente;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import javax.naming.NamingException;

import com.controlador.GestionActividades;
import com.controlador.GestionCasillas;
import com.controlador.GestionEstaciones;
import com.controlador.GestionFormularios;
import com.controlador.GestionRegistros;
import com.controlador.GestionUsuarios;
import com.entities.Actividad;
import com.entities.Administrador;
import com.entities.Aficionado;
import com.entities.Casilla;
import com.entities.EstacionDeMedicion;
import com.entities.Formulario;
import com.entities.Investigador;
import com.entities.RegistroFloat;
import com.entities.RegistroInteger;
import com.entities.RegistroString;
import com.entities.Usuario;
//import com.enumerados.BorradoLogico.Estado;
import com.enumerados.BorradoLogico.*;
import com.exceptions.ServiciosException;

public class CargaInicial {

	public void cargaInicial() throws NamingException, ServiciosException {
		
		GestionUsuarios gestionUsuarios = new GestionUsuarios();
		
		//Comienza carga Administradores 
		Administrador administrador1 = new Administrador("Adminnombre1" , "AdminApellido1" ,  "AdminMail1@algo.com" , "AdminClave1", com.enumerados.BorradoLogico.Estado.HABILITADO, "AdmDocu1" ,"AdminDomic1" , "AdminTel1", "Bella Unión", "ARTIGAS");
		Administrador administrador2 = new Administrador("Adminnombre2" , "AdminApellido2" ,  "AdminMail2@algo.com" , "AdminClave2" , com.enumerados.BorradoLogico.Estado.HABILITADO, "AdmDocu2" ,"AdminDomic2" , "AdminTel2", "Bella Unión", "ARTIGAS");
		Administrador administrador3 = new Administrador("Adminnombre3" , "AdminApellido3" ,  "mailAdmin@algo.com" , "claveAdmin1" , com.enumerados.BorradoLogico.Estado.HABILITADO, "AdmDocu3" ,"AdminDomic3" , "AdminTel3", "Bella Unión","ARTIGAS");
		Administrador administrador4 = new Administrador("Adminnombre4" , "AdminApellido4" ,  "admin@algo.com" , "1234567A" , com.enumerados.BorradoLogico.Estado.HABILITADO, "AdmDocu4" ,"AdminDomic4" , "AdminTel4", "Bella Unión","ARTIGAS");

		administrador1 = gestionUsuarios.crearAdministrador(administrador1);
		administrador2 = gestionUsuarios.crearAdministrador(administrador2);
		administrador3 = gestionUsuarios.crearAdministrador(administrador3);
		administrador4 = gestionUsuarios.crearAdministrador(administrador4);
		
		gestionUsuarios.obtieneAdministradores();
		Long idAdmin4 = administrador4.getId();
		gestionUsuarios.obtienePorId(idAdmin4);
		
		
		
		//Comienza carga 4 Investigadores 
		
		Investigador investigador1 = new Investigador( "Investnombre1" ,"InvestApellido1" , "InvestMail1@algo.com" , "InvestClave1", com.enumerados.BorradoLogico.Estado.HABILITADO , "InvDocu1" ,"InvestDomic1" , "InvestTel1",  "Bella Unión", "ARTIGAS");
		Investigador investigador2 = new Investigador( "Investnombre2" ,"InvestApellido2" , "InvestMail2@algo.com" , "InvestClave2", com.enumerados.BorradoLogico.Estado.HABILITADO , "InvDocu2" ,"InvestDomic2" , "InvestTel2", "Bella Unión", "ARTIGAS");
		Investigador investigador3 = new Investigador( "Investnombre3" ,"InvestApellido3" , "InvestMail3@algo.com" , "InvestClave3", com.enumerados.BorradoLogico.Estado.HABILITADO , "InvDocu3" ,"InvestDomic3" , "InvestTel3", "Bella Unión", "ARTIGAS");
		Investigador investigador4 = new Investigador( "Investnombre4" ,"InvestApellido4" , "invest@algo.com" , "1234567A", com.enumerados.BorradoLogico.Estado.HABILITADO , "InvDocu4" ,"InvestDomic4" , "InvestTel4", "Bella Unión", "ARTIGAS");
		
			
		investigador1 = gestionUsuarios.crearInvestigador(investigador1);
		investigador2 = gestionUsuarios.crearInvestigador(investigador2);
		investigador3 = gestionUsuarios.crearInvestigador(investigador3);
		investigador4 = gestionUsuarios.crearInvestigador(investigador4);
			
		gestionUsuarios.obtieneInvestigadores();
		Long idInvest4 = investigador4.getId();
		gestionUsuarios.obtienePorId(idInvest4);
				
		//Comienza la carga de 4 Aficionados
		Aficionado aficionado1 = new Aficionado( "Aficnombre1" , "AficApellido1" , "AficMail1@algo.com" , "AficClave1", com.enumerados.BorradoLogico.Estado.HABILITADO);
		Aficionado aficionado2 = new Aficionado( "Aficnombre2" , "AficApellido2" ,"AficMail2@algo.com" , "AficClave2", com.enumerados.BorradoLogico.Estado.HABILITADO);
		Aficionado aficionado3 = new Aficionado( "Aficnombre3" , "AficApellido3" ,"AficMail3@algo.com" , "AficClave3", com.enumerados.BorradoLogico.Estado.HABILITADO);
		Aficionado aficionado4 = new Aficionado( "Aficnombre4" , "AficApellido4" , "afic@algo.com" , "1234567A", com.enumerados.BorradoLogico.Estado.HABILITADO);
		
		aficionado1 = gestionUsuarios.crearAficionado(aficionado1);
		aficionado2 = gestionUsuarios.crearAficionado(aficionado2);
		aficionado3 = gestionUsuarios.crearAficionado(aficionado3);
		aficionado4 = gestionUsuarios.crearAficionado(aficionado4);
		
		gestionUsuarios.obtieneAficionados();
		Long idAficionado4 = aficionado4.getId();
		gestionUsuarios.obtienePorId(idAficionado4);
		
		
		//Comienza la carga de 3 Casillas
		
		Casilla casilla1 = new Casilla("Primer Casilla" , TipoDatoEnum.DECIMAL , "humedad" , "%" , "Vimos" , investigador2 );
		Casilla casilla2 = new Casilla("Segunda Casilla" , TipoDatoEnum.ENTERO , "temperatura" , "ºC" , "Visto");
		Casilla casilla3 = new Casilla("Tercera Casilla" , TipoDatoEnum.TEXTO , "viento" , "-" , Obligatoria.SI);
		Casilla casilla4 = new Casilla("Cuarta Casilla" , TipoDatoEnum.DECIMAL , "presión atm." , "HPa" , Obligatoria.SI);
		Casilla casilla5 = new Casilla("Quinta Casilla" , TipoDatoEnum.VERDADEROóFALSO , "certeza" , "-" , "Viendo" , administrador4);
		
		
		
		GestionCasillas gestionCasilla = new GestionCasillas();
		gestionCasilla.crearCasilla(casilla1);
		Casilla casilla2pos = gestionCasilla.crearCasilla(casilla2);
		Casilla casilla3pos = gestionCasilla.crearCasilla(casilla3);
		Casilla casilla4pos = gestionCasilla.crearCasilla(casilla4);
		gestionCasilla.crearCasilla(casilla5);
		List<Casilla> listaCasillas = gestionCasilla.listaCasillas();
		List<Casilla> otraListaCasillas = new LinkedList<>();
		otraListaCasillas.add(listaCasillas.get(0));
		otraListaCasillas.add(listaCasillas.get(1));
		
		//Comienza la carga de 2 Estaciones de Medición
		
		EstacionDeMedicion em1 = new EstacionDeMedicion("Primera_EM ", "TREINTA Y TRES", "Vergara" , investigador3);
		EstacionDeMedicion em2 = new EstacionDeMedicion("Segunda_EM ", "LAVALLEJA" , "Pirarajá" , administrador1);
					
		GestionEstaciones gestionEstaciones = new GestionEstaciones();
		
		em1 = gestionEstaciones.crearEstacion(em1);
		em2 = gestionEstaciones.crearEstacion(em2);
		
		//Asigna algunas Casillas a las EM creadas
		gestionEstaciones.asignarCasillaAEM(Long.parseLong(String.valueOf(1)), Long.parseLong(String.valueOf(1)));
		gestionEstaciones.asignarCasillaAEM(Long.parseLong(String.valueOf(1)), Long.parseLong(String.valueOf(2)));
		gestionEstaciones.asignarCasillaAEM(Long.parseLong(String.valueOf(1)), Long.parseLong(String.valueOf(3)));
		gestionEstaciones.asignarCasillaAEM(Long.parseLong(String.valueOf(2)), Long.parseLong(String.valueOf(3)));
		gestionEstaciones.asignarCasillaAEM(Long.parseLong(String.valueOf(2)), Long.parseLong(String.valueOf(4)));
		gestionEstaciones.asignarCasillaAEM(Long.parseLong(String.valueOf(2)), Long.parseLong(String.valueOf(5)));
	
		//Comienza la carga de 2 formularios
		List<Casilla> lista1 = new LinkedList<>();
		lista1.add(listaCasillas.get(0));
		lista1.add(listaCasillas.get(2));
		lista1.add(listaCasillas.get(3));
		lista1.add(listaCasillas.get(4));
		List<Casilla> lista2 = new LinkedList<>();
		lista2.add(listaCasillas.get(1));
		lista2.add(listaCasillas.get(2));
		lista2.add(listaCasillas.get(3));
		List<Usuario> usuariosHabilitados = new LinkedList<Usuario>();
		usuariosHabilitados.add(administrador3);
		
		GestionFormularios gestionFormularios = new GestionFormularios();
		
		Formulario form1 = new Formulario("PrimerFormulario" , lista1 , investigador1);
		form1 = gestionFormularios.crearFormulario(form1);
		
		Formulario form2 = new Formulario("SegundoFormulario", lista2 , administrador2);
		form2 = gestionFormularios.crearFormulario(form2);
		
		
		gestionFormularios.listaFormularios();
		  
		//Comienza la carga de 2 Actividades
		Actividad actividad1 = new Actividad(form2 , investigador1);
		Actividad actividad2 = new Actividad(form2 , (Usuario) administrador3);
		GestionActividades gestionActividades = new GestionActividades();
		actividad1 = gestionActividades.crearActividad(actividad1);
		actividad2 = gestionActividades.crearActividad(actividad2);
			
		
		//Comienza la carga de 6 registros
		 long miliseconds = System.currentTimeMillis();
	        Date date = new Date(miliseconds);
	         
        RegistroString registro1 = new RegistroString("fuerte" , (float) 23.1 , (float) -33.6 , date , Estado.HABILITADO, casilla3pos );
        RegistroInteger registro2 = new RegistroInteger(18 , (float) 20.1 , (float) -43.6 , date , Estado.HABILITADO , casilla2pos);
	    RegistroFloat registro3 = new RegistroFloat(1568.4 , (float) 23.1 , (float) -33.6 , date , Estado.HABILITADO , casilla4pos);
	    RegistroString registro4 = new RegistroString("poco" , (float) 23.1 , (float) -33.6 , date , Estado.HABILITADO, casilla3pos );
	    RegistroInteger registro5 = new RegistroInteger(21 , (float) 23.1 , (float) -33.6 , date , Estado.HABILITADO , casilla2pos);
	    RegistroFloat registro6 = new RegistroFloat(1024.3 , (float) 23.1 , (float) -33.6 , date , Estado.HABILITADO , casilla4pos);
	    
	    GestionRegistros gestionRegistros = new GestionRegistros();
	    
	    actividad1 = gestionActividades.obtienePorId(actividad1.getId());
		registro1.setActividad(actividad1);
		registro1.setCasilla(listaCasillas.get(2)); 
	    registro1 = gestionRegistros.crearRegistroString(registro1);
	    
	    registro2.setActividad(actividad1);
	    registro2.setCasilla(listaCasillas.get(1));
	    registro2 = gestionRegistros.crearRegistroInteger(registro2);
	    
	    
	    registro3.setActividad(actividad1);
	    registro3.setCasilla(listaCasillas.get(3));
	    registro3 = gestionRegistros.crearRegistroFloat(registro3);
	    
	    actividad2 = gestionActividades.obtienePorId(actividad2.getId());
		   
	    registro4.setActividad(actividad2);
	    registro4.setCasilla(listaCasillas.get(2));
	    registro4 = gestionRegistros.crearRegistroString(registro4);
	    
	    registro5.setActividad(actividad2);
	    registro5.setCasilla(listaCasillas.get(1));
	    registro5 = gestionRegistros.crearRegistroInteger(registro5);
	    
	    registro6.setActividad(actividad2);
	    registro6.setCasilla(listaCasillas.get(3));
	    registro6 = gestionRegistros.crearRegistroFloat(registro6);
	    
	 
	}
}
