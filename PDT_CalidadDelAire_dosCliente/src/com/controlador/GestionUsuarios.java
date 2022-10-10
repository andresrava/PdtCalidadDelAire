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
		usuario.setNombre(usuario.getNombre().toUpperCase());
		usuario.setApellido(usuario.getApellido().toUpperCase());
		usuario = usuariosBean.crear(usuario);
		return usuario;		
	}
	
	public Administrador crearAdministrador(Administrador administrador) throws NamingException , ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/AdministradoresBean!com.services.AdministradoresBeanRemote";
		
		AdministradoresBeanRemote administradorBean = (AdministradoresBeanRemote)
					InitialContext.doLookup(ruta);
		administrador.setNombre(administrador.getNombre().toUpperCase());
		administrador.setApellido(administrador.getApellido().toUpperCase());
		administrador = administradorBean.crear(administrador);
		return administrador;
	}
	
	public Administrador actualizarAdministrador(Administrador administrador) throws NamingException , ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/AdministradoresBean!com.services.AdministradoresBeanRemote";
		
		AdministradoresBeanRemote administradorBean = (AdministradoresBeanRemote)
				InitialContext.doLookup(ruta);
		administrador.setNombre(administrador.getNombre().toUpperCase());
		administrador.setApellido(administrador.getApellido().toUpperCase());
		administrador = administradorBean.actualizar(administrador);
		return administrador;
	}
	public Investigador actualizarInvestigador(Investigador investigador) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/InvestigadoresBean!com.services.InvestigadoresBeanRemote";
		
		InvestigadoresBeanRemote investigadorBean = (InvestigadoresBeanRemote)
				InitialContext.doLookup(ruta);
		investigador.setNombre(investigador.getNombre().toUpperCase());
		investigador.setApellido(investigador.getApellido().toUpperCase());
		investigador = investigadorBean.actualizarInvestigador(investigador);
		return investigador;
	}
	
	public Aficionado actualizaAficionado(Aficionado aficionado) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/AficionadosBean!com.services.AficionadosBeanRemote";
		
		AficionadosBeanRemote investigadorBean = (AficionadosBeanRemote)
				InitialContext.doLookup(ruta);
		aficionado.setNombre(aficionado.getNombre().toUpperCase());
		aficionado.setApellido(aficionado.getApellido().toUpperCase());
		aficionado = investigadorBean.actualizarAficionado(aficionado);
		return aficionado;
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
		investigador.setNombre(investigador.getNombre().toUpperCase());
		investigador.setApellido(investigador.getApellido().toUpperCase());
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
		aficionado.setNombre(aficionado.getNombre().toUpperCase());
		aficionado.setApellido(aficionado.getApellido().toUpperCase());
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
	
	public Administrador obtieneAdministradorPorId (Long idAdministrador) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
		UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote)
				InitialContext.doLookup(ruta);
		Administrador administrador = (Administrador) usuariosBean.obtenerPorId(idAdministrador);
		return administrador;
		
	}
	
	public Investigador obtieneInvestigadorPorId (Long idInvestigador) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
		UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote)
				InitialContext.doLookup(ruta);
		Investigador investigador = (Investigador) usuariosBean.obtenerPorId(idInvestigador);
		return investigador;
		
	}
	
	public Aficionado obtieneAficionadoPorId (Long idAficionado) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
		UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote)
				InitialContext.doLookup(ruta);
		Aficionado aficionado = (Aficionado) usuariosBean.obtenerPorId(idAficionado);
		return aficionado;
		
	}
	
	public void borraUsuario(Usuario usuarioABorrar) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
		UsuariosBeanRemote usuariosBean = (UsuariosBeanRemote)
				InitialContext.doLookup(ruta);
		Long idABorrar = usuarioABorrar.getId();
		usuariosBean.borrar(idABorrar);
	}
	public List<Usuario> obtieneUsuarios() throws NamingException {
		String ruta = "PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
		UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote)
					InitialContext.doLookup(ruta);
		List<Usuario> usuarios = usuarioBean.obtenerTodos();
		return usuarios;
	}
	public List<Usuario> obtenerPorMail(String mail) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
		UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote)
				InitialContext.doLookup(ruta);
		List<Usuario> usuarios = usuarioBean.obtenerPorMail(mail);
		return usuarios;
	}
	
	public List<Usuario> obtenerPorNombreApellido(String nombre, String apellido) throws NamingException, ServiciosException {
		String ruta = "PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
		UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote)
				InitialContext.doLookup(ruta);
		List<Usuario> usuarios = usuarioBean.obtenerPorNomApe(nombre, apellido);
		return usuarios;
	}
	
	
}
