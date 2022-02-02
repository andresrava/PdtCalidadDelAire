package com.controlador;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Actividad;
import com.entities.Registro;
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
}
