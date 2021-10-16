package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Departamento;
import com.exception.ServiciosException;

@Remote
public interface DepartamentoBeanRemote {
	void crear(Departamento departamento) throws ServiciosException;
	void actualizar(Departamento departamento) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Departamento> obtenerTodos();
}
