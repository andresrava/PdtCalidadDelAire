package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Aficionado;
import com.exceptions.ServiciosException;

@Remote
public interface AficionadosBeanRemote {
	void crear(Aficionado aficionado) throws ServiciosException;
	void actualizar(Aficionado aficionado) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Aficionado> obtenerTodos();
	List<Aficionado> obtenerTodos(String filtro);
	List<Aficionado> obtenerPorFormulario(Long idFormulario);
	void asignarFormulario(Long idAficionado, Long idFormulario) throws ServiciosException;
}
