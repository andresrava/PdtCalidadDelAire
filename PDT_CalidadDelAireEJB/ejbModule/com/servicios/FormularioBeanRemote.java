package com.servicios;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Formulario;
import com.exception.ServiciosException;

@Remote
public interface FormularioBeanRemote {
	void crear(Formulario formulario) throws ServiciosException;
	void actualizar(Formulario formulario) throws ServiciosException;
	void borrar(Long id) throws ServiciosException;
	void asignarAficionado(Long idFormulario, Long idAficionado) throws ServiciosException;
	void asignarAdministrador(Long idFormulario, Long idAdministrador) throws ServiciosException;
	void asignarInvestigador(Long idFormulario, Long idInvestigador) throws ServiciosException;
	void asignarCasilla(Long idFormulario, Long idCasilla) throws ServiciosException;
	List<Formulario> obtenerTodos();
}
