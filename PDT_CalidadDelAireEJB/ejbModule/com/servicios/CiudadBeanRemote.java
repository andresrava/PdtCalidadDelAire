package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Ciudad;
import com.exception.ServiciosException;

@Remote
public interface CiudadBeanRemote {
	void crear(Ciudad ciudad) throws ServiciosException;
	void actualizar(Ciudad ciudad) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	void asignarAdministrador(Long idCiudad, Long idAdministrador) throws ServiciosException;
	void asignarInvestigador(Long idCiudad, Long idInvestigador) throws ServiciosException;
	List<Ciudad> obtenerTodos();
}
