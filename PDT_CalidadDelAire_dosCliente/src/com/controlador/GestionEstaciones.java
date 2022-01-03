package com.controlador;

import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Casilla;
import com.entities.EstacionDeMedicion;
import com.exceptions.ServiciosException;
import com.services.EstacionesDeMedicionBeanRemote;

public class GestionEstaciones {
	public EstacionDeMedicion crearEstacion(EstacionDeMedicion estacion) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
				InitialContext.doLookup(ruta);
		estacion = estacionBean.crear(estacion);
		
		return estacion;
		
	}
	
	public void asignarCasillaAEM ( EstacionDeMedicion estacion , Casilla casilla) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
				InitialContext.doLookup(ruta);
		Long idEstacion = estacion.getId();
		Long idCasilla = casilla.getId();
		estacionBean.asignarCasillaAEM(idEstacion, idCasilla);
		
	}
	
	public EstacionDeMedicion obtieneEMPorId (Long idEM) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
				InitialContext.doLookup(ruta);
		EstacionDeMedicion estacion = new EstacionDeMedicion();
		estacion = estacionBean.obtenerPorId(idEM);
		return estacion;
	}
	
	public List<EstacionDeMedicion> obtieneTodas() throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
				InitialContext.doLookup(ruta);
		List<EstacionDeMedicion> estaciones = estacionBean.obtenerTodos();
		
		return estaciones;
		
	}

	
}
