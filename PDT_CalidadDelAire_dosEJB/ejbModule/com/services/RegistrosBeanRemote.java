package com.services;

import java.sql.Date;
import java.util.List;

import javax.ejb.Remote;

import com.entities.Registro;
import com.exceptions.ServiciosException;

@Remote
public interface RegistrosBeanRemote {
	void crear(Registro registro) throws ServiciosException;
	void actualizar(Registro registro) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Registro> obtenerTodos();
	List<Registro> obtenerTodos(Date fechahora);
}