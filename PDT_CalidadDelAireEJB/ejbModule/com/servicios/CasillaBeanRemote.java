package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Casilla;
import com.exception.ServiciosException;

@Remote
public interface CasillaBeanRemote {
	void crear(Casilla casilla) throws ServiciosException;
	void actualizar(Casilla casilla) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	void asignarFormulario(Long idCasilla, Long idFormulario) throws ServiciosException;
	List<Casilla> obtenerTodos();
}
