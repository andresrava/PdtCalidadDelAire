package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Aficionado;
import com.exception.ServiciosException;

@Remote
public interface AficionadoBeanRemote {
	void crear(Aficionado aficionado) throws ServiciosException;
	void actualizar(Aficionado aficionado) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Aficionado> obtenerTodos();
	List<Aficionado> obtenerTodos(String filtro);
}
