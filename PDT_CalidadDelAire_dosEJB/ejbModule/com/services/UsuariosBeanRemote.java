package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Usuario;
import com.exceptions.ServiciosException;

@Remote
public interface UsuariosBeanRemote {
	Usuario crear(Usuario usuario) throws ServiciosException;
	void actualizar(Usuario usuario) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Usuario> obtenerTodos();
	List<Usuario> obtenerTodos(String filtro);
	List<Usuario> validarLogin(String mail, String password);
	void asignarActividad (Long idUsuario, Long idActividad) throws ServiciosException;
	
}
