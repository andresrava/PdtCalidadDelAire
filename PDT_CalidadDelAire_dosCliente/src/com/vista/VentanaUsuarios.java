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
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.SwingConstants;

import com.controlador.GestionLocalidades;
import com.entities.Administrador;
import com.entities.Aficionado;
import com.entities.Ciudad;
import com.entities.Investigador;
import com.entities.Usuario;
import com.enumerados.BorradoLogico.Estado;
import com.exceptions.ServiciosException;
import com.services.AdministradoresBeanRemote;
import com.services.AficionadosBeanRemote;
import com.services.CiudadesBeanRemote;
import com.services.InvestigadoresBeanRemote;
import com.services.UsuariosBeanRemote;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Atxy2k.CustomTextField.RestrictedTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


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
		frame.setBounds(600, 100, 528, 446);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		
		textDocumento = new JTextField(8);
		textDocumento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!verificarCI(textDocumento.getText())) {
					JOptionPane.showMessageDialog(null, "Error, el documento ingresado no es correcto","Error", 
							JOptionPane.ERROR_MESSAGE);
					textDocumento.requestFocus();
				}
			}
		});
		textDocumento.setBounds(104, 216, 186, 20);
		frame.getContentPane().add(textDocumento);
		textDocumento.setColumns(10);
		textDocumento.setToolTipText("Ingresar CI con dígito verificador, sin puntos ni guiones.");
		//Definimos restricciones para que en el documento solo se acepten números, máximo 8 caractéres
		RestrictedTextField restrictedDocumento = new RestrictedTextField(this.textDocumento);
		restrictedDocumento.setOnlyNums(true);
		restrictedDocumento.setLimit(8);
		
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
		lblUsuario.setBounds(28, 11, 476, 14);
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
		btnVolver.setBounds(306, 386, 181, 23);
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
		lblCiudad.setBounds(28, 337, 100, 14);
		frame.getContentPane().add(lblCiudad);
		
		
//Creo el combo con departamentos y lo lleno de elementos
		JComboBox <String> comboBoxDepartamento = new JComboBox();
		GestionLocalidades gestionLocalidades = new GestionLocalidades();
		Set<String> departamentos = gestionLocalidades.obtieneDepartamentos();
		for (String nombre : departamentos) {
			comboBoxDepartamento.addItem(nombre);
		}
		comboBoxDepartamento.setSelectedIndex(0);
		comboBoxDepartamento.setBounds(104, 304, 186, 22);
		frame.getContentPane().add(comboBoxDepartamento);
		
		
//Creo el Combo con las localidades y lo lleno usando el depto seleccionado
		JComboBox<String> cmbCiudad = new JComboBox();
		String deptoSelec = (String) comboBoxDepartamento.getSelectedItem();					
		Set<String> localidadesEnDepto = gestionLocalidades.obtieneLocalidades(deptoSelec);
		for (String l : localidadesEnDepto)
		{
			cmbCiudad.addItem(l);
		}
		cmbCiudad.setBounds(104, 333, 186, 22);
		frame.getContentPane().add(cmbCiudad);
		
		comboBoxDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbCiudad.removeAllItems();
				Set<String> localidades = gestionLocalidades.obtieneLocalidades((String) comboBoxDepartamento.getSelectedItem());
				for (String l : localidades) {
					cmbCiudad.addItem(l);
				}	
			}
		});
		
		
		
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
					String ciudad = cmbCiudad.getSelectedItem().toString();
					String departamento = comboBoxDepartamento.getSelectedItem().toString();
					if (cmbRol.getSelectedItem() == "Administrador") {
						Administrador administrador2 = administradorBean.crear(new Administrador(nombre, apellido, mail, clave, Estado.HABILITADO, documento, domicilio, telefono, ciudad, departamento));
					}else if(cmbRol.getSelectedItem() == "Investigador"){
						Investigador investigador = investigadorBean.crear(new Investigador(nombre, apellido, mail, clave, Estado.HABILITADO, documento, domicilio, telefono, ciudad, departamento));
					}else { //sino crea un usuario aficionado
						Aficionado aficionado = aficionadoBean.crear(new Aficionado(nombre, apellido, mail, clave, Estado.HABILITADO));
					}
					JOptionPane.showMessageDialog(null,  "Usuario ingresado con éxito","Exito",
							JOptionPane.INFORMATION_MESSAGE);		 

				} catch (ServiciosException e) { // TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error", "Error, el usuario no pudo ingresarse",
							JOptionPane.ERROR_MESSAGE);
				}
				limpiarFormulario();

			}
		});

		btnAlta.setBounds(300, 58, 204, 23);
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
					if (JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar este usuario?", "Confirmación",
							JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						try {
							Usuario usuario = usuarioBean.obtenerPorID(Long.parseLong(textID.getText()));
							usuario.setEstado(Estado.BORRADO);
							usuarioBean.actualizar(usuario);
							
							JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito.", "Exito",
									JOptionPane.INFORMATION_MESSAGE);
							limpiarFormulario();
						} catch (NumberFormatException | ServiciosException e1) {
							JOptionPane.showMessageDialog(null, "Error, no se pudo eliminar el usuario.", "Error",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null,
								"Se canceló la eliminación.",
								"Aviso", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, el campo ID no puede estar vacío. Debe buscar la persona a eliminar primero.",
							"Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		 
		btnEliminar.setBounds(300, 94, 204, 23);
		frame.getContentPane().add(btnEliminar);
		
		btnBuscar = new JButton("Buscar por mail");
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Usuario> usuarios = usuarioBean.obtenerPorMail(textMail.getText());
					if (usuarios.size() == 1) {
						Usuario usuario = usuarios.get(0);
						if (usuarios.get(0) instanceof Administrador) {
							Administrador administrador = (Administrador) usuarios.get(0);
							if (administrador.getCiudad() != null) 
								cmbCiudad.setSelectedItem(administrador.getCiudad());
							cmbRol.setSelectedItem("Administrador");
							textDocumento.setText(administrador.getDocumento());
							textDomicilio.setText(administrador.getDomicilio());
							textTelefono.setText(administrador.getTelefono());
							cmbCiudad.setSelectedItem(administrador.getCiudad());
							comboBoxDepartamento.setSelectedItem(administrador.getDepartamento());
							
						} else if (usuarios.get(0) instanceof Investigador) {
							Investigador investigador = (Investigador) usuarios.get(0);
							if (investigador.getCiudad() != null)
								cmbCiudad.setSelectedItem(investigador.getCiudad());
							cmbRol.setSelectedItem("Investigador");
							textDocumento.setText(investigador.getDocumento());
							textDomicilio.setText(investigador.getDomicilio());
							textTelefono.setText(investigador.getTelefono());
							cmbCiudad.setSelectedItem(investigador.getCiudad());
							comboBoxDepartamento.setSelectedItem(investigador.getDepartamento());
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
		 
		btnBuscar.setBounds(300, 156, 204, 23);
		frame.getContentPane().add(btnBuscar);
		
		btnModificar = new JButton("Modificar");
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Está seguro que desea modificar este usuario?", "Confirmación",
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

					try {
						Administrador administrador = administradorBean.obtenerPorID(Long.parseLong(textID.getText()));
						Investigador investigador = investigadorBean.obtenerPorID(Long.parseLong(textID.getText()));
						Aficionado aficionado = aficionadoBean.obtenerPorID(Long.parseLong(textID.getText()));
						if (administrador != null ) {
							administrador.setNombre(textNombre.getText());
							administrador.setApellido(textApellido.getText());
							administrador.setMail(textMail.getText());
							administrador.setContraseña(textClave.getText());
							administrador.setDocumento(textDocumento.getText());
							administrador.setDomicilio(textDomicilio.getText());
							administrador.setTelefono(textTelefono.getText());
							administrador.setCiudad(cmbCiudad.getSelectedItem().toString());
							administrador.setDepartamento(comboBoxDepartamento.getSelectedItem().toString());
							administradorBean.actualizar(administrador); 
							
						} else if (investigador != null) {
							investigador.setNombre(textNombre.getText());
							investigador.setApellido(textApellido.getText());
							investigador.setMail(textMail.getText());
							investigador.setContraseña(textClave.getText());
							investigador.setDocumento(textDocumento.getText());
							investigador.setDomicilio(textDomicilio.getText());
							investigador.setTelefono(textTelefono.getText());
							investigador.setCiudad(cmbCiudad.getSelectedItem().toString());
							investigador.setDepartamento(comboBoxDepartamento.getSelectedItem().toString());
							investigadorBean.actualizar(investigador); 
							
						} else if (aficionado != null) {
							aficionado.setNombre(textNombre.getText());
							aficionado.setApellido(textApellido.getText());
							aficionado.setMail(textMail.getText());
							aficionado.setContraseña(textClave.getText());
							
						}
	
						 JOptionPane.showMessageDialog(null, "Usuario modificado con éxito.", "Exito", JOptionPane.INFORMATION_MESSAGE); 
						 limpiarFormulario();
						

					} catch (NumberFormatException | ServiciosException e1) {
						JOptionPane.showMessageDialog(null, "Error, no se pudo actualizar al usuario.", "Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null,
							"Se canceló la modificación.",
							"Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		 
		btnModificar.setBounds(300, 123, 204, 23);
		frame.getContentPane().add(btnModificar);
		
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(28, 308, 100, 14);
		frame.getContentPane().add(lblDepartamento);
		
		JButton btnLimpiar = new JButton("Limpiar formulario");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarFormulario();
			}
		});
		btnLimpiar.setBounds(306, 352, 181, 23);
		frame.getContentPane().add(btnLimpiar);
		
		JButton btnBuscarNombre = new JButton("Buscar por nombre y apellido");
		btnBuscarNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				VentanaListaUsuarios ventanaListaUsuarios = new VentanaListaUsuarios ((Usuario) usuarioLoged, null);
				ventanaListaUsuarios.VentanaListaUsuarios((Usuario) usuarioLoged, null);
				
			}
		});
		btnBuscarNombre.setBounds(300, 187, 204, 23);
		frame.getContentPane().add(btnBuscarNombre);
		
		JButton btnBuscarRol = new JButton("Buscar por rol");
		btnBuscarRol.setBounds(300, 215, 204, 23);
		frame.getContentPane().add(btnBuscarRol);
		
		
				
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
					comboBoxDepartamento.setEnabled(false);
				}else {
					textDocumento.setEnabled(true);
					textDomicilio.setEnabled(true);
					textTelefono.setEnabled(true);
					cmbCiudad.setEnabled(true);
					comboBoxDepartamento.setEnabled(true);

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
	
	public Boolean verificarCI(String documento) {
		int suma = 0;
		int correcto=0;
		String ced = documento.trim();
		int cedula[]; // Vector donde van a estar los digitos de la cedula
		int factor[] = {8,1,2,3,4,7,6,0};// factor a multiplicar
		
		cedula = new int[8];
		
		for(int i=0;i<ced.length();i++){
			if(ced.charAt(i) == '0' || ced.charAt(i)== '1' || ced.charAt(i)=='2' 
               || ced.charAt(i)== '3' || ced.charAt(i) == '4' || ced.charAt(i)== '5' || ced.charAt(i)=='6' 
              || ced.charAt(i) == '7' || ced.charAt(i)== '8' || ced.charAt(i)=='9'){
				correcto++;
				cedula[i]=Integer.parseInt("" +ced.charAt(i));
				suma = suma + (cedula[i]*factor[i]);
				
			}
			
		}
		if (correcto!=8){
			return Boolean.FALSE;
		} else {
			int resto=suma%10;
			if (resto == cedula[7]) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;				
			}
		}
	}
}

