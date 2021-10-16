package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Administrador;
import com.exception.ServiciosException;

@Remote
public interface AdministradorBeanRemote {
	void crear(Administrador administrador) throws ServiciosException;
	void actualizar(Administrador administrador) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Administrador> obtenerTodos();
	List<Administrador> obtenerTodos(String filtro);
}
