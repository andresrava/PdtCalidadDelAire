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
	
		Usuario usuario = new Usuario("Rava" , "miclave" , "midocumento" , "mimail" , "Andr�s");
		System.out.println(usuario.toString());
		Administrador administrador = new Administrador();
		administrador.setApellido("Rava");
		administrador.setContrase�a("miclave");
		administrador.setDocumento("midocumento");
		administrador.setMail("mimail");
		administrador.setNombre("Andr�s");
		
		
		System.out.println(administrador.toString());
		try {
			administradorBean.crear(administrador);
			System.out.println("Se cre� el Administrador");
			
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
	}
	

}
