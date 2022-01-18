package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Investigador;
import com.exceptions.ServiciosException;

@Remote
public interface InvestigadoresBeanRemote {
	Investigador crear(Investigador investigador) throws ServiciosException;
	void actualizar(Investigador investigador) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Investigador> obtenerTodos();
	List<Investigador> obtenerTodos(String filtro);
	List<Investigador> obtenerPorID(String filtro);
	List<Investigador> obtenerPorFormulario(Long idFormulario);
	void asignarFormulario(Long idInvestigador, Long idFormulario) throws ServiciosException;
	void asignarCiudad(Long idInvestigador, Long idCiudad) throws ServiciosException;
}
