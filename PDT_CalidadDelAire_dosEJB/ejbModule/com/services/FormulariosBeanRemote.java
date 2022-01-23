package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Formulario;
import com.exceptions.ServiciosException;

@Remote
public interface FormulariosBeanRemote {
	Formulario crear(Formulario formulario) throws ServiciosException;
	Formulario actualizar(Formulario formulario) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Formulario> obtenerTodos();
	List<Formulario> obtenerTodos(String filtro);
	void asignarCasilla (Long idFormulario, Long idCasilla) throws ServiciosException;
}
