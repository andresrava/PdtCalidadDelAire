package com.controlador;

import java.util.LinkedList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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
	
		public EstacionDeMedicion actualizarEstacion(EstacionDeMedicion estacion) throws NamingException, ServiciosException {
			String ruta = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
			EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
					InitialContext.doLookup(ruta);
			estacion = estacionBean.actualizar(estacion);
			
			return estacion;
			
	}
	
	public void asignarCasillaAEM ( Long idEstacion , Long idCasilla) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
				InitialContext.doLookup(ruta);
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
	
	public List<EstacionDeMedicion> obtieneEM() throws NamingException {		
		String ruta = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
				InitialContext.doLookup(ruta);
		List<EstacionDeMedicion> estaciones = new LinkedList<EstacionDeMedicion>();
		estaciones = estacionBean.obtenerTodasEM();
		
		return estaciones;
		
	}
	public List<EstacionDeMedicion> obtieneEmPorNombre(String nombre) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
				InitialContext.doLookup(ruta);
		List<EstacionDeMedicion> estaciones = estacionBean.obtenerTodasEM("%" + nombre + "%");
		
		return estaciones;
		
	}

	public void borraEM(EstacionDeMedicion estacionAEliminar) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/EstacionesDeMedicionBean!com.services.EstacionesDeMedicionBeanRemote";
		EstacionesDeMedicionBeanRemote estacionBean = (EstacionesDeMedicionBeanRemote)
				InitialContext.doLookup(ruta);
		Long estacionABorrarId = estacionAEliminar.getId();
		System.out.println("El id de la estacion a eliminar es: " + estacionABorrarId);
		estacionBean.borrar(estacionABorrarId);
	}

	
}
