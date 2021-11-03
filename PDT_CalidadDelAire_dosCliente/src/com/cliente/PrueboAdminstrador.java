package com.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Administrador;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.services.AdministradoresBeanRemote;

public class PrueboAdminstrador {

	public static void main(String[] args) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/AdministradoresBean!com.services.AdministradoresBeanRemote";
		
		AdministradoresBeanRemote administradorBean = (AdministradoresBeanRemote)
					InitialContext.doLookup(ruta);
	
		Usuario usuario = new Usuario("Rava" , "miclave" , "midocumento" , "mimail" , "Andrés");
		System.out.println(usuario.toString());
		Administrador administrador = new Administrador();
		administrador.setApellido("Rava");
		administrador.setContraseña("miclave");
		administrador.setDocumento("midocumento");
		administrador.setMail("mimail");
		administrador.setNombre("Andrés");
		
		
		System.out.println(administrador.toString());
		try {
			administradorBean.crear(administrador);
			System.out.println("Se creó el Administrador");
			
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
	}
	

}
