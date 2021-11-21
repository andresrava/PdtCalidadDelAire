package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Casilla;
import com.exceptions.ServiciosException;

@Remote
public interface CasillasBeanRemote {
	Casilla crear(Casilla casilla) throws ServiciosException;
	void actualizar(Casilla casilla) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Casilla> obtenerTodos();
	List<Casilla> obtenerTodos(String filtro);
	List<Casilla> obtenerPorParametro(Long parametro);
	List<Casilla> obtenerPorFormulario(Long idFormulario);
}
