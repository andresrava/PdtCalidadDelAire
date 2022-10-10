package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Administrador;
import com.entities.Investigador;
import com.exceptions.ServiciosException;

@Remote
public interface InvestigadoresBeanRemote {
	Investigador crear(Investigador investigador) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Investigador> obtenerTodos();
	List<Investigador> obtenerTodos(String filtro);
	List<Investigador> obtenerPorID(String filtro);
	Investigador obtenerPorID(Long id) throws ServiciosException;
	List<Investigador> obtenerPorFormulario(Long idFormulario);
	void asignarFormulario(Long idInvestigador, Long idFormulario) throws ServiciosException;
	Investigador actualizarInvestigador(Investigador investigador) throws ServiciosException;
}
