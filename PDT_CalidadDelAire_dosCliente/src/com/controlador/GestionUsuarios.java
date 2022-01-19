package com.controlador;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Administrador;
import com.entities.Aficionado;
import com.entities.Investigador;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.services.AdministradoresBeanRemote;
import com.services.AficionadosBeanRemote;
import com.services.InvestigadoresBeanRemote;
import com.services.UsuariosBeanRemote;

public class GestionUsuarios {
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
	public Usuario creaUsuario(Usuario usuario) throws NamingException , ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
		UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote)
				InitialContext.doLookup(ruta);
		usuario = usuariosBean.crear(usuario);
		return usuario;		
	}
	
	public Administrador crearAdministrador(Administrador administrador) throws NamingException , ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/AdministradoresBean!com.services.AdministradoresBeanRemote";
		
		AdministradoresBeanRemote administradorBean = (AdministradoresBeanRemote)
					InitialContext.doLookup(ruta);
		administrador = administradorBean.crear(administrador);
		return administrador;
	}
	
	public List<Administrador> obtieneAdministradores() throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/AdministradoresBean!com.services.AdministradoresBeanRemote";
		AdministradoresBeanRemote administradorBean = (AdministradoresBeanRemote)
					InitialContext.doLookup(ruta);
		List<Administrador> administradores = administradorBean.obtenerTodos();
		return administradores;
	}
	
	public Investigador crearInvestigador (Investigador investigador) throws NamingException , ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/InvestigadoresBean!com.services.InvestigadoresBeanRemote";
		
		InvestigadoresBeanRemote investigadorBean = (InvestigadoresBeanRemote)
					InitialContext.doLookup(ruta);
		investigador = investigadorBean.crear(investigador);
		return investigador;
	}
	
	public List<Investigador> obtieneInvestigadores() throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/InvestigadoresBean!com.services.InvestigadoresBeanRemote";
		InvestigadoresBeanRemote investigadorBean = (InvestigadoresBeanRemote)
					InitialContext.doLookup(ruta);
		List<Investigador> investigador = investigadorBean.obtenerTodos();
		return investigador;
	}
	
	public Aficionado crearAficionado (Aficionado aficionado) throws NamingException , ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/AficionadosBean!com.services.AficionadosBeanRemote";
		
		AficionadosBeanRemote aficionadoBean = (AficionadosBeanRemote)
					InitialContext.doLookup(ruta);
		aficionado = aficionadoBean.crear(aficionado);
		return aficionado;
	}
	
	public List<Aficionado> obtieneAficionados() throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/AficionadosBean!com.services.AficionadosBeanRemote";
		AficionadosBeanRemote aficionadoBean = (AficionadosBeanRemote)
					InitialContext.doLookup(ruta);
		List<Aficionado> aficionado = aficionadoBean.obtenerTodos();
		return aficionado;
	}
	
	
	public Usuario obtienePorId (Long idUsuario) throws NamingException, ServiciosException {
	String ruta = "PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
	UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote)
			InitialContext.doLookup(ruta);
	Usuario usuario = usuariosBean.obtenerPorId(idUsuario);
	return usuario;

	}
}
