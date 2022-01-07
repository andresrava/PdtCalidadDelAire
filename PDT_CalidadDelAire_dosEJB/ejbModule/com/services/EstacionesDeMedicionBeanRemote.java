package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.EstacionDeMedicion;
import com.exceptions.ServiciosException;

@Remote
public interface EstacionesDeMedicionBeanRemote {
	EstacionDeMedicion crear(EstacionDeMedicion estacion) throws ServiciosException;
	EstacionDeMedicion actualizar(EstacionDeMedicion estacion) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	List<EstacionDeMedicion> obtenerTodasEM();
	List<EstacionDeMedicion> obtenerTodasEM(String filtro);
	List<EstacionDeMedicion> obtenerPorDepartamento(Long idDepartamento);
	List<EstacionDeMedicion> obtenerPorCiudad(Long idCiudad);
	List<EstacionDeMedicion> obtenerPorInvestigador(Long idInvestigador);
	void asignarCasillaAEM(Long idEstacion, Long idCasilla) throws ServiciosException;
	void agregarUsuario(Long idEM , Long idUsuario) throws ServiciosException;
	EstacionDeMedicion obtenerPorId(Long id) throws ServiciosException;
}

