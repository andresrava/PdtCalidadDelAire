package com.controlador;

import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Formulario;
import com.exceptions.ServiciosException;
import com.services.FormulariosBeanRemote;

public class GestionFormularios {
	private String ruta = "PDT_CalidadDelAire_dosEJB/FormulariosBean!com.services.FormulariosBeanRemote";
	

	public Formulario crearFormulario(Formulario formulario) throws NamingException, ServiciosException {
		System.out.println("Formulario 1 GestionFormularios: " + formulario);
		
		FormulariosBeanRemote formularioBean = (FormulariosBeanRemote)
				InitialContext.doLookup(ruta);
		Formulario formularioPos = formularioBean.crear(formulario);
		System.out.println("Formulario 1 GestionFormularios después: " + formularioPos);

		return formularioPos;
	}
	
	public List<Formulario> listaFormularios() throws NamingException {
		FormulariosBeanRemote formularioBean = (FormulariosBeanRemote)
				InitialContext.doLookup(ruta);
		List<Formulario> formularios = formularioBean.obtenerTodos();
		return formularios;
		
	}

	public void borrarFormulario(Formulario formularioAEliminar) throws NamingException, ServiciosException {
		FormulariosBeanRemote formularioBean = (FormulariosBeanRemote)
				InitialContext.doLookup(ruta);
		Long idABorrar = formularioAEliminar.getId();
		formularioBean.borrar(idABorrar);
	}

	public Formulario actualizarFormulario(Formulario formularioAEditar) throws NamingException, ServiciosException {
		FormulariosBeanRemote formularioBean = (FormulariosBeanRemote)
				InitialContext.doLookup(ruta);
		Formulario formularioEditado = formularioBean.actualizar(formularioAEditar);
		return formularioEditado;
	}
	
	public Formulario encontrarFormulario(Long idFormulario) throws NamingException, ServiciosException {
		FormulariosBeanRemote formularioBean = (FormulariosBeanRemote)
				InitialContext.doLookup(ruta);
		Formulario formulario = formularioBean.obtenerPorId(idFormulario);
		return formulario;
	}
	
	

}
