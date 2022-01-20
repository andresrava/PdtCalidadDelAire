package com.controlador;

import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.services.LocalidadesBeanRemote;


public class GestionLocalidades {
	public Set<String> obtieneDepartamentos () throws NamingException 
	{
		String ruta = "PDT_CalidadDelAire_dosEJB/LocalidadesBean!com.services.LocalidadesBeanRemote";
		LocalidadesBeanRemote localidadBean = (LocalidadesBeanRemote)
				InitialContext.doLookup(ruta);
		System.out.println("En gestiónLocalidades: " + localidadBean.obtenerDepartamentos());
		return localidadBean.obtenerDepartamentos();
		
	}
	
	public Set<String> obtieneLocalidades (String depto) throws NamingException
	{
		String ruta = "PDT_CalidadDelAire_dosEJB/LocalidadesBean!com.services.LocalidadesBeanRemote";
		LocalidadesBeanRemote localidadBean = (LocalidadesBeanRemote)
				InitialContext.doLookup(ruta);
		//System.out.println("En gestiónLocalidades: " + localidadBean.obtenerDepartamentos());
		return localidadBean.listaLocalidades(depto);
	}

}
