package com.controlador;

import java.util.List;

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
	
	
	public List<Actividad> obtieneTodas() throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/ActividadesBean!com.services.ActividadesBeanRemote";
		ActividadesBeanRemote actividadBean = (ActividadesBeanRemote)
				InitialContext.doLookup(ruta);
		List<Actividad> actividades = actividadBean.obtenerTodos();
		return actividades;
		// TODO Auto-generated method stub
		
	}

	public void borraActividad(Actividad actividad) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/ActividadesBean!com.services.ActividadesBeanRemote";
		ActividadesBeanRemote actividadBean = (ActividadesBeanRemote)
				InitialContext.doLookup(ruta);
		Long idActividadAEliminar = actividad.getId();
		actividadBean.borrar(idActividadAEliminar);
	}
	
	
}
