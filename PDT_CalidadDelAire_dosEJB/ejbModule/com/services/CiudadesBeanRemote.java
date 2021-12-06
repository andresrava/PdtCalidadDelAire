package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Ciudad;
import com.exceptions.ServiciosException;

@Remote
public interface CiudadesBeanRemote {
	Ciudad crear(Ciudad ciudad) throws ServiciosException;
	void actualizar(Ciudad ciudad) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Ciudad> obtenerTodos();
	List<Ciudad> obtenerTodos(String filtro);
	Ciudad obtenerPorId(Long idCiudad);
}
