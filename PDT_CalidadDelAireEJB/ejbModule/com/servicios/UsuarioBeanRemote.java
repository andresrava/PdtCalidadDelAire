package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Usuario;
import com.exceptions.ServiciosException;

@Remote
public interface UsuarioBeanRemote {
	void crear(Usuario usuario) throws ServiciosException;
	void actualizar(Usuario usuario) throws ServiciosException;
	void borrar (Long id) throws ServiciosException;
	List<Usuario> obtenerTodos();
	List<Usuario> obtenerTodos(String filtro);
}
