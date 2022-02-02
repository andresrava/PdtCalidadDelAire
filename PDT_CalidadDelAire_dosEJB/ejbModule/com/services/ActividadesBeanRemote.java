package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Actividad;
import com.exceptions.ServiciosException;

@Remote
public interface ActividadesBeanRemote {
	Actividad crear(Actividad actividad) throws ServiciosException;
	Actividad actualizar(Actividad actividad) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Actividad> obtenerTodos();
	List<Actividad> obtenerTodos(String filtro);
	void asignarRegistro (Long idActividad, Long idRegistro) throws ServiciosException;
	Actividad agregaRegistro(Long idActividad, Long idRegistro);
	}
