package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Investigador;
import com.exceptions.ServiciosException;

@Remote
public interface InvestigadorBeanRemote {
	void crear(Investigador investigador) throws ServiciosException;
	void actualizar(Investigador investigador) throws ServiciosException;
	void borrar (Long id) throws ServiciosException;
	List<Investigador> obtenerTodos();
	List<Investigador> obtenerTodos(String filtro);
}
