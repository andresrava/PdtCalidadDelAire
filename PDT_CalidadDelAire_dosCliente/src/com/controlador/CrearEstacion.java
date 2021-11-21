package com.controlador;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.EstacionDeMedicion;
import com.exceptions.ServiciosException;
import com.services.EstacionesDeMedicionBeanRemote;

public class CrearEstacion {
	public EstacionDeMedicion crearEstacion(EstacionDeMedicion estacion) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
				InitialContext.doLookup(ruta);
		estacionBean.crear(estacion);
		return estacion;
		
	}
}
