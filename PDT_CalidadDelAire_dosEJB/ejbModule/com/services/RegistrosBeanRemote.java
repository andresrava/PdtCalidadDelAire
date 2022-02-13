package com.services;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

import javax.ejb.Remote;

import com.entities.Registro;
import com.exceptions.ServiciosException;

@Remote
public interface RegistrosBeanRemote {
	Registro crear(Registro registro) throws ServiciosException;
	Registro actualizar(Registro registro) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<Registro> obtenerTodos();
	List<Registro> obtenerTodos(Date dateDesde, Date dateHasta);
	ResultSet obtenerTodos(Long idFormulario);
	List<Registro> obtenerTodosLista(Long idFormulario);
	Registro encuentraPorId(Long id);
}
