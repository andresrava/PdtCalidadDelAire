package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Administrador;
import com.exceptions.ServiciosException;

@Remote
public interface AdministradoresBeanRemote {
	Administrador crear(Administrador administrador) throws ServiciosException;
	void actualizar(Administrador administrador) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Administrador> obtenerTodos();
	List<Administrador> obtenerTodos(String filtro);
	List<Administrador> obtenerPorFormulario(Long idFormulario);
	void asignarFormulario(Long idAdministrador, Long idFormulario) throws ServiciosException;
	void asignarCiudad(Long idAdministrador, Long idCiudad) throws ServiciosException;
}
