package com.vista;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.SwingConstants;

import com.entities.Administrador;
import com.entities.Aficionado;
import com.entities.Ciudad;
import com.entities.Investigador;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import com.services.AdministradoresBeanRemote;
import com.services.AficionadosBeanRemote;
import com.services.CiudadesBeanRemote;
import com.services.InvestigadoresBeanRemote;
import com.services.UsuariosBeanRemote;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class VentanaUsuarios {

	private JFrame frame;
	private JTextField textDocumento;
	private JTextField textApellido;
	private JTextField textNombre;
	private JTextField textClave;
	private JTextField textMail;
	
	private JButton btnAlta;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JButton btnModificar;
	private JLabel lblUsuario;
	private JLabel lblID;
	private JLabel lblRol;
	private JTextField textID;
	private JTextField textDomicilio;
	private JTextField textTelefono;
	
	/**
	 * Launch the application.
	 */
	public static void VentanaUsuarios(Usuario usuarioLoged) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaUsuarios window = new VentanaUsuarios(usuarioLoged);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static Usuario usuarioLoged;
	
	public VentanaUsuarios(Usuario usuarioLogedRef) throws NamingException {
		VentanaUsuarios.usuarioLoged = usuarioLogedRef;
		
		String ruta="PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
		UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote) InitialContext.doLookup(ruta);
		
		String ruta2="PDT_CalidadDelAire_dosEJB/AdministradoresBean!com.services.AdministradoresBeanRemote";
		AdministradoresBeanRemote administradorBean = (AdministradoresBeanRemote) InitialContext.doLookup(ruta2);
		
		String ruta3="PDT_CalidadDelAire_dosEJB/InvestigadoresBean!com.services.InvestigadoresBeanRemote";
		InvestigadoresBeanRemote investigadorBean = (InvestigadoresBeanRemote) InitialContext.doLookup(ruta3);
		
		String ruta4="PDT_CalidadDelAire_dosEJB/AficionadosBean!com.services.AficionadosBeanRemote";
		AficionadosBeanRemote aficionadoBean = (AficionadosBeanRemote) InitialContext.doLookup(ruta4);
		
		String ruta5="PDT_CalidadDelAire_dosEJB/CiudadesBean!com.services.CiudadesBeanRemote";
		CiudadesBeanRemote ciudadBean = (CiudadesBeanRemote) InitialContext.doLookup(ruta5);
		
		
		frame = new JFrame();
		frame.setBounds(600, 100, 419, 410);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textDocumento = new JTextField();
		textDocumento.setBounds(104, 216, 186, 20);
		frame.getContentPane().add(textDocumento);
		textDocumento.setColumns(10);
			
		textNombre = new JTextField();
		textNombre.setBounds(104, 62, 186, 20);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setBounds(104, 93, 186, 20);
		frame.getContentPane().add(textApellido);
		textApellido.setColumns(10);
		
		textClave = new JPasswordField();
		textClave.setBounds(104, 124, 186, 20);
		frame.getContentPane().add(textClave);
		textClave.setColumns(10);
		
		textMail = new JTextField();
		textMail.setBounds(104, 157, 186, 20);
		frame.getContentPane().add(textMail);
		textMail.setColumns(10);
		
		lblRol = new JLabel("Rol");
		lblRol.setBounds(28, 191, 47, 14);
		frame.getContentPane().add(lblRol);
		
		lblUsuario = new JLabel("New label");
		lblUsuario.setBounds(28, 11, 361, 14);
		lblUsuario.setText("Usuario logueado en el sistema: " + usuarioLoged.getNombre() + " " + usuarioLoged.getApellido());
		frame.getContentPane().add(lblUsuario);
		
		lblID = new JLabel("ID");
		lblID.setBounds(28, 37, 47, 14);
		frame.getContentPane().add(lblID);
		
		textID = new JTextField();
		textID.setEditable(false);
		textID.setBounds(103, 31, 187, 20);
		frame.getContentPane().add(textID);
		textID.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(300, 340, 89, 23);
		frame.getContentPane().add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					volver(usuarioLoged);
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
			}
		});

		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(28, 251, 100, 14);
		frame.getContentPane().add(lblDomicilio);
		
		textDomicilio = new JTextField();
		textDomicilio.setColumns(10);
		textDomicilio.setBounds(104, 247, 186, 20);
		frame.getContentPane().add(textDomicilio);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setBounds(28, 280, 100, 14);
		frame.getContentPane().add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(104, 276, 186, 20);
		frame.getContentPane().add(textTelefono);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(28, 309, 100, 14);
		frame.getContentPane().add(lblCiudad);
		
		JComboBox cmbCiudad = new JComboBox();
		cmbCiudad.setBounds(104, 305, 186, 22);
		frame.getContentPane().add(cmbCiudad);
		
		List<Ciudad> ciudades = ciudadBean.obtenerTodos();
		for (Ciudad ciudad: ciudades) {
			cmbCiudad.addItem(ciudad.getNombre()); }
		
		JComboBox cmbRol = new JComboBox();
		
		cmbRol.setBounds(104, 188, 186, 22);
		cmbRol.addItem("Administrador");
		cmbRol.addItem("Investigador");
		cmbRol.addItem("Aficionado");
		frame.getContentPane().add(cmbRol);
		
		btnAlta = new JButton("Alta");
		
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String documento = textDocumento.getText();
					String nombre = textNombre.getText();
					String apellido = textApellido.getText();
					String clave = textClave.getText();
					String mail = textMail.getText();
					String domicilio = textDomicilio.getText();
					String telefono = textTelefono.getText();
					if (cmbRol.getSelectedItem() == "Administrador") {
						Administrador administrador2 = administradorBean.crear(new Administrador(nombre, apellido, mail, clave, documento, domicilio, telefono));
						administradorBean.asignarCiudad(administrador2.getId(), ciudadBean.obtenerTodos(cmbCiudad.getSelectedItem().toString()).get(0).getId());
					}else if(cmbRol.getSelectedItem() == "Investigador"){
						Investigador investigador = investigadorBean.crear(new Investigador(nombre, apellido, mail, clave, documento, domicilio, telefono));
						investigadorBean.asignarCiudad(investigador.getId(), ciudadBean.obtenerTodos(cmbCiudad.getSelectedItem().toString()).get(0).getId());						
					}else { //sino crea un usuario aficionado
						Aficionado aficionado = aficionadoBean.crear(new Aficionado(nombre, apellido, mail, clave));
					}
					JOptionPane.showMessageDialog(null, "Exito", "Usuario ingresado con éxito",
							JOptionPane.INFORMATION_MESSAGE);		 

				} catch (ServiciosException e) { // TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error", "Error, el usuario no pudo ingresarse",
							JOptionPane.ERROR_MESSAGE);
				}
				limpiarFormulario();

			}
		});

		btnAlta.setBounds(300, 58, 89, 23);
		frame.getContentPane().add(btnAlta);
				
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(28, 220, 100, 14);
		frame.getContentPane().add(lblDocumento);
		
		JLabel lblNombre1 = new JLabel("Nombre");
		lblNombre1.setBounds(28, 66, 100, 14);
		frame.getContentPane().add(lblNombre1);
		
		JLabel lblApellido1 = new JLabel("Apellido");
		lblApellido1.setBounds(28, 97, 100, 14);
		frame.getContentPane().add(lblApellido1);
		
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(28, 128, 100, 14);
		frame.getContentPane().add(lblClave);
		
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(28, 161, 100, 14);
		frame.getContentPane().add(lblMail);
		
		btnEliminar = new JButton("Eliminar");
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textID.getText() != "") {
					try {
						usuarioBean.borrar(Long.parseLong(textID.getText()));
						JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito.", "Exito",
								JOptionPane.INFORMATION_MESSAGE);
						limpiarFormulario();
					} catch (NumberFormatException | ServiciosException e1) {
						JOptionPane.showMessageDialog(null, "Error, no se pudo eliminar el usuario.", "Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, el campo ID no puede estar vacío. Debe buscar la persona a eliminar primero.",
							"Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		 
		btnEliminar.setBounds(300, 94, 89, 23);
		frame.getContentPane().add(btnEliminar);
		
		btnBuscar = new JButton("Buscar");
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Usuario> usuarios = usuarioBean.obtenerPorMail(textMail.getText());
					if (usuarios.size() == 1) {
						Usuario usuario = usuarios.get(0);
						if (usuarios.get(0) instanceof Administrador) {
							Administrador administrador = (Administrador) usuarios.get(0);
							if (administrador.getCiudad() != null) 
								cmbCiudad.setSelectedItem(administrador.getCiudad().getNombre());
							cmbRol.setSelectedItem("Administrador");
							textDocumento.setText(administrador.getDocumento());
							textDomicilio.setText(administrador.getDomicilio());
							textTelefono.setText(administrador.getTelefono());
						} else if (usuarios.get(0) instanceof Investigador) {
							Investigador investigador = (Investigador) usuarios.get(0);
							if (investigador.getCiudad() != null)
								cmbCiudad.setSelectedItem(investigador.getCiudad().getNombre());
							cmbRol.setSelectedItem("Investigador");
							textDocumento.setText(investigador.getDocumento());
							textDomicilio.setText(investigador.getDomicilio());
							textTelefono.setText(investigador.getTelefono());
						} else {
							Aficionado aficionado = (Aficionado) usuarios.get(0);
							cmbRol.setSelectedItem("Aficionado");
						}
						textID.setText(Long.toString(usuario.getId()));
						textNombre.setText(usuario.getNombre());
						textApellido.setText(usuario.getApellido());
						textClave.setText(usuario.getContraseña());
						textMail.setText(usuario.getMail());
						
						JOptionPane.showMessageDialog(null, "Usuario encontrado con éxito.", "Exito",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(null, "Error, no se encontró el usuario.", "Error",
								JOptionPane.ERROR_MESSAGE);
						limpiarFormulario();
					}
				} catch (HeadlessException e1) {
					JOptionPane.showMessageDialog(null, "Error, no se encontró el usuario.", "Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
					limpiarFormulario();

				} catch (ServiciosException e1) {
					JOptionPane.showMessageDialog(null, "Error, no se encontró el usuario.", "Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
					limpiarFormulario();

				}
			}
		});
		 
		btnBuscar.setBounds(300, 125, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
		btnModificar = new JButton("Modificar");
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Administrador administrador = administradorBean.obtenerPorID(Long.parseLong(textID.getText()));
					
					if (administrador != null ) {
						administradorBean.actualizar(new Administrador(Long.parseLong(textID.getText()),
								textNombre.getText(), textApellido.getText(), textMail.getText(), textClave.getText(),
								textDocumento.getText(), textDomicilio.getText(), textTelefono.getText(),
								ciudadBean.obtenerTodos(cmbCiudad.getSelectedItem().toString()).get(0))); 
						
						/*
						 * }else if (investigadorBean.obtenerPorID(textID.getText()) != null) {
						 * 
						 * }else if (aficionadoBean.obtenerPorID(textID.getText()) != null) {
						 */
					}

					/*
					 * JOptionPane.showMessageDialog(null, "Usuario modificado con éxito.", "Exito",
					 * JOptionPane.INFORMATION_MESSAGE); limpiarFormulario();
					 */
				} catch (NumberFormatException | ServiciosException e1) {
					JOptionPane.showMessageDialog(null, "Error, no se pudo actualizar al usuario.", "Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				 
			}
		});
		 
		btnModificar.setBounds(300, 156, 89, 23);
		frame.getContentPane().add(btnModificar);
		
		
				
		//Se selecciona rol Aficionado deshabilita los datos que son solo de administrador e investigador
		cmbRol.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cmbRol.getSelectedItem().toString() == "Aficionado") {
					textDocumento.setEnabled(false);
					textDocumento.setText("");
					textDomicilio.setEnabled(false);
					textDomicilio.setText("");
					textTelefono.setEnabled(false);
					textTelefono.setText("");
					cmbCiudad.setEnabled(false);
				}else {
					textDocumento.setEnabled(true);
					textDomicilio.setEnabled(true);
					textTelefono.setEnabled(true);
					cmbCiudad.setEnabled(true);
				}
			}
		});
		
		
		
	}
	
	public void limpiarFormulario() {
		textNombre.setText(null);
		textApellido.setText(null);
		textDocumento.setText(null);
		textClave.setText(null);
		textMail.setText(null);
		textID.setText(null);
		textDomicilio.setText(null);
		textTelefono.setText(null);
	}
	
	public void volver(Usuario usuarioLoged) throws NamingException {
		this.frame.dispose();
		VentanaAdministrador ventanaAdministrador = new VentanaAdministrador((Administrador)usuarioLoged);
		ventanaAdministrador.ventanaAdministrador();
		
	}
}

