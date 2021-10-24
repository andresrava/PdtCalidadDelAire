package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Actividad;
import com.exceptions.ServiciosException;

@Remote
public interface ActividadesBeanRemote {
	void crear(Actividad actividad) throws ServiciosException;
	void actualizar(Actividad actividad) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Actividad> obtenerTodos();
	List<Actividad> obtenerTodos(String filtro);
	void asignarRegistro (Long idActividad, Long idRegistro) throws ServiciosException;
}
