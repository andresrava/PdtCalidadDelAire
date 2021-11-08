package com.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Investigador;
import com.exceptions.ServiciosException;
import com.services.InvestigadoresBeanRemote;

public class PrueboInvestigador {

	public static void main(String[] args) throws NamingException {
String ruta = "PDT_CalidadDelAire_dosEJB/InvestigadoresBean!com.services.InvestigadoresBeanRemote";
		
InvestigadoresBeanRemote investigadorBean = (InvestigadoresBeanRemote)
					InitialContext.doLookup(ruta);
	

		Investigador investigador = new Investigador( "Albert" ,"Einstein" , "EMail", "einteinClave" , "EDocu"  , "EDomicilio" , "Eteléfono" );
		System.out.println(investigador.toString());
		try {
			investigadorBean.crear(investigador);
			System.out.println("Se creó el Investigador");
			
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
	}

}
