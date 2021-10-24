package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Departamento;
import com.exceptions.ServiciosException;

@Remote
public interface DepartamentosBeanRemote {
	void crear(Departamento departamento) throws ServiciosException;
	void actualizar(Departamento departamento) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Departamento> obtenerTodos();
	List<Departamento> obtenerTodos(String filtro);
	void asignarCiudad (Long idDepartamento, Long idCiudad) throws ServiciosException;
}
