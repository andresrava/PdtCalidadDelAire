package com.cliente;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Funcionalidad;
import com.exceptions.ServiciosException;
import com.services.FuncionalidadesBeanRemote;

public class PrueboServices {

	public static void main(String[] args) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/FuncionalidadesBean!com.services.FuncionalidadesBeanRemote";
	
		FuncionalidadesBeanRemote funcionalidadBean = (FuncionalidadesBeanRemote)
					InitialContext.doLookup(ruta);
	
		
		Funcionalidad funcionalidad = new Funcionalidad("hace dibujos" , "dibujar");
		
		try {
			funcionalidadBean.crear(funcionalidad);
			System.out.println("Se creó la funcionalidad");
			
		} catch (ServiciosException e) {
			System.out.println(e.getMessage());
		}
	}
	

}
