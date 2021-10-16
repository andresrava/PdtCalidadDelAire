package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Em;
import com.exception.ServiciosException;

@Remote
public interface EmBeanRemote {
	void crear(Em eme) throws ServiciosException;
	void actualizar(Em eme) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	void asignarCiudad(Long idEm, Long idCiudad) throws ServiciosException;
	void asignarDepartamento(Long idEm, Long idDepartamento) throws ServiciosException;
	void asignarInvestigador(Long idEm, Long idInvestigador) throws ServiciosException;
	void asignarUsuario(Long idEm, Long idUsuario) throws ServiciosException;
	List<Em> obtenerTodos();
}
