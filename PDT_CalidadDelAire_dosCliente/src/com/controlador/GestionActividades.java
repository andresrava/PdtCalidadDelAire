package com.controlador;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Actividad;
import com.exceptions.ServiciosException;
import com.services.ActividadesBeanRemote;

public class GestionActividades {
	public Actividad crearActividad(Actividad actividad) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/ActividadesBean!com.services.ActividadesBeanRemote";
		ActividadesBeanRemote actividadBean = (ActividadesBeanRemote)
				InitialContext.doLookup(ruta);
		actividad = actividadBean.crear(actividad);
		return actividad;
	}
	
	public Actividad actualizaActividad(Actividad actividad) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/ActividadesBean!com.services.ActividadesBeanRemote";
		ActividadesBeanRemote actividadBean = (ActividadesBeanRemote)
				InitialContext.doLookup(ruta);
		actividad = actividadBean.actualizar(actividad);
		return actividad;
	}
	
	
	
	public Actividad agregaRegistro(Long idActividad , Long idRegistro) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/ActividadesBean!com.services.ActividadesBeanRemote";
		ActividadesBeanRemote actividadBean = (ActividadesBeanRemote)
				InitialContext.doLookup(ruta);
		Actividad actividad = actividadBean.agregaRegistro (idActividad , idRegistro);
		return actividad;
		
	}

	public Actividad obtienePorId(Long id) throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/ActividadesBean!com.services.ActividadesBeanRemote";
		ActividadesBeanRemote actividadBean = (ActividadesBeanRemote)
				InitialContext.doLookup(ruta);
		Actividad actividad = actividadBean.encuentraPorId(id);
		return actividad;
		// TODO Auto-generated method stub
		
	}
}
