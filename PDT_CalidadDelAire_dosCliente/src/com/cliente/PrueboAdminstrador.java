package com.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Administrador;
import com.exceptions.ServiciosException;
import com.services.AdministradoresBeanRemote;

public class PrueboAdminstrador {

	public static void main(String[] args) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/AdministradoresBean!com.services.AdministradoresBeanRemote";
		
		AdministradoresBeanRemote administradorBean = (AdministradoresBeanRemote)
					InitialContext.doLookup(ruta);
		Administrador administrador =  new Administrador("Andr�s" ,"Rava" ,  "mimail" , "miclave", "38549975" , "micasa" , "mitel�fono");
	
		System.out.println(administrador.toString());
		
		
		try {
			administradorBean.crear(administrador);
			System.out.println("Se cre� el Administrador");
			
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
	}
	

}
