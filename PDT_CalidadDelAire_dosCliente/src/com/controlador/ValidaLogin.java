package com.controlador;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;

import com.entities.Usuario;
import com.services.UsuariosBeanRemote;

public class ValidaLogin {
	public Usuario validarLogin(String mail , String clave) throws NamingException {
		String ruta="PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
		UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote) InitialContext.doLookup(ruta);
		List<Usuario> usuarios = usuarioBean.validarLogin(mail,clave);
		Usuario usuario = new Usuario();
		if ( !usuarios.isEmpty()) {
			//Credenciales correctas
			usuario = usuarios.get(0);
			return usuario;
		}else {
        	
        	return null;
        }
		
		
		
	}
}
