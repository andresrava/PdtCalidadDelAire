package com.cliente;

import javax.naming.InitialContext;

import com.entities.Administrador;
import com.exceptions.ServiciosException;
import com.services.AdministradoresBeanRemote;

public class PrueboUsuario {

	public static void main(String[] args) {
	String ruta = "PDT_CalidadDelAire_dosEJB/AdministradoresBean!com.services.AdministradoresBeanRemote";
		
		AdministradoresBeanRemote administradorBean = (AdministradoresBeanRemote)
					InitialContext.doLookup(ruta);
	
		
		Administrador administrador = new Administrador("Rava" , "miclave" , "midocumento" , "mimail" , "Andrés" , "mitelefono");
		System.out.println(administrador.toString());
		try {
			administradorBean.crear(administrador);
			System.out.println("Se creó el Administrador");
			
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
	}

}
