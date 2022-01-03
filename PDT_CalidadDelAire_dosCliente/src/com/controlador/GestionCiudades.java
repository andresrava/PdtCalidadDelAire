package com.controlador;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Ciudad;
import com.entities.Ciudad.NombresEnum;
import com.exceptions.ServiciosException;
import com.services.CiudadesBeanRemote;

public class GestionCiudades {
	public Ciudad creaCiudad(Ciudad ciudad) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/CiudadesBean!com.services.CiudadesBeanRemote";
		CiudadesBeanRemote ciudadBean = (CiudadesBeanRemote)
				InitialContext.doLookup(ruta);
		ciudad = ciudadBean.crear(ciudad);
		return ciudad;
		
	}
	public Ciudad creaCiudad(String nombre , NombresEnum depto) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/CiudadesBean!com.services.CiudadesBeanRemote";
		CiudadesBeanRemote ciudadBean = (CiudadesBeanRemote)
				InitialContext.doLookup(ruta);
		Ciudad ciudad = new Ciudad(nombre , depto);
		ciudad = ciudadBean.crear(ciudad);
		return ciudad;
	}
	public Ciudad obtienePorId (Long idCiudad) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/CiudadesBean!com.services.CiudadesBeanRemote";
		CiudadesBeanRemote ciudadBean = (CiudadesBeanRemote)
				InitialContext.doLookup(ruta);
		Ciudad ciudad = ciudadBean.obtenerPorId(idCiudad);
		return ciudad;
	}
	
	
}
