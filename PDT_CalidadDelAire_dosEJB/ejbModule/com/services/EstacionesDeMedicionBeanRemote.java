package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.EstacionDeMedicion;
import com.exceptions.ServiciosException;

@Remote
public interface EstacionesDeMedicionBeanRemote {
	void crear(EstacionDeMedicion estacion) throws ServiciosException;
	void actualizar(EstacionDeMedicion estacion) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<EstacionDeMedicion> obtenerTodos();
	List<EstacionDeMedicion> obtenerTodos(String filtro);
	List<EstacionDeMedicion> obtenerPorDepartamento(Long idDepartamento);
	List<EstacionDeMedicion> obtenerPorCiudad(Long idCiudad);
	List<EstacionDeMedicion> obtenerPorInvestigador(Long idInvestigador);
	void asignarCasilla(Long idEstacion, Long idCasilla) throws ServiciosException;
}
