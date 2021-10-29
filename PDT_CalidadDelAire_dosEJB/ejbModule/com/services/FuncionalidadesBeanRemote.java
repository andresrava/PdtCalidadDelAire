package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Funcionalidad;
import com.exceptions.ServiciosException;

@Remote
public interface FuncionalidadesBeanRemote {
	void crear(Funcionalidad funcionalidad) throws ServiciosException;
	void actualizar(Funcionalidad funcionalidad) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Funcionalidad> obtenerTodos();
	List<Funcionalidad> obtenerTodos(String filtro);
	//void asignarUsuario (Long idFuncionalidad, Long idUsuario) throws ServiciosException;
	//List<Funcionalidad> obtenerPorUsuario(Long idUsuario);

}
