package com.vista;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.controlador.GestionLocalidades;
import com.controlador.GestionUsuarios;
import com.entities.Administrador;
import com.entities.Aficionado;
import com.entities.Investigador;
import com.entities.Usuario;
import com.enumerados.BorradoLogico.Estado;
import com.exceptions.ServiciosException;
import com.services.AdministradoresBeanRemote;
import com.services.AficionadosBeanRemote;
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
import java.awt.Font;
import java.awt.Color;



public class VentanaUsuarios {

	private JFrame frame;
	private static JPanel contentPane_1;
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
	public static void VentanaUsuarios(Usuario usuarioLoged, Long id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaUsuarios window = new VentanaUsuarios(usuarioLoged, id);
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static Usuario usuarioLoged;
	private static Long id; 
	
	private static final String EMAIL_PATTERN = 
		    "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,20}$";
	private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	
	public VentanaUsuarios(Usuario usuarioLogedRef, Long idRef) throws NamingException {
		VentanaUsuarios.usuarioLoged = usuarioLogedRef;
		VentanaUsuarios.id = idRef;
		
		String ruta="PDT_CalidadDelAire_dosEJB/UsuariosBean!com.services.UsuariosBeanRemote";
		UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote) InitialContext.doLookup(ruta);
		
		String ruta2="PDT_CalidadDelAire_dosEJB/AdministradoresBean!com.services.AdministradoresBeanRemote";
		AdministradoresBeanRemote administradorBean = (AdministradoresBeanRemote) InitialContext.doLookup(ruta2);
		
		String ruta3="PDT_CalidadDelAire_dosEJB/InvestigadoresBean!com.services.InvestigadoresBeanRemote";
		InvestigadoresBeanRemote investigadorBean = (InvestigadoresBeanRemote) InitialContext.doLookup(ruta3);
		
		String ruta4="PDT_CalidadDelAire_dosEJB/AficionadosBean!com.services.AficionadosBeanRemote";
		AficionadosBeanRemote aficionadoBean = (AficionadosBeanRemote) InitialContext.doLookup(ruta4);
	
//		contentPane = new PaneImage();		//Cambié esto para el fondo decía "content = newJPanel()"
		
		
		frame = new JFrame();
		
		frame.setBounds(100, 100, 800, 500);	// anterior: frame.setBounds(600, 100, 528, 446);
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//Agrego el fondo
				contentPane_1 = new PaneImage();		
				contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame.setContentPane(contentPane_1);
		textDocumento = new JTextField(8);
		textDocumento.setActionCommand("");
		textDocumento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textDocumento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!verificarCI(textDocumento.getText())) {
					JOptionPane.showMessageDialog(null, "Error, el documento ingresado no es correcto","Error", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		textDocumento.setBounds(223, 129, 277, 20);
		textDocumento.setColumns(10);
		//Definimos restricciones para que en el documento solo se acepten números, máximo 8 caractéres
		RestrictedTextField restrictedDocumento = new RestrictedTextField(this.textDocumento);
		restrictedDocumento.setOnlyNums(true);
		restrictedDocumento.setLimit(8);
		
		
		
		textNombre = new JTextField();
		textNombre.setActionCommand("");
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textNombre.setBounds(223, 66, 277, 20);
		textNombre.setColumns(10);
		RestrictedTextField restrictedNombre = new RestrictedTextField(this.textNombre);
		restrictedNombre.setOnlyText(true);
		
		textApellido = new JTextField();
		textApellido.setActionCommand("");
		textApellido.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textApellido.setBounds(223, 98, 277, 20);
		textApellido.setColumns(10);
		RestrictedTextField restrictedApellido = new RestrictedTextField(this.textApellido);
		restrictedApellido.setOnlyText(true);
		
		textClave = new JPasswordField();
		textClave.setActionCommand("");
		textClave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textClave.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!verificarPass(textClave.getText())) {
					JOptionPane.showMessageDialog(null, "Error, la password ingresada no es correcta.","Error", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		textClave.setBounds(223, 158, 277, 20);
		textClave.setColumns(10);
		
		textMail = new JTextField();
		textMail.setActionCommand("");
		textMail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textMail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!verificarMail(textMail.getText())) {
					JOptionPane.showMessageDialog(null, "Error, el mail ingresado no es correcto","Error", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		textMail.setBounds(223, 188, 277, 20);
		textMail.setColumns(10);
		
		lblRol = new JLabel("Rol");
		lblRol.setForeground(Color.BLACK);
		lblRol.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRol.setBounds(15, 337, 144, 22);
		
		lblUsuario = new JLabel("New label");
		lblUsuario.setBounds(15, 10, 275, 14);
		lblUsuario.setText("Usuario logueado en el sistema: " + usuarioLoged.getNombre() + " " + usuarioLoged.getApellido());
		
		lblID = new JLabel("ID");
		lblID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblID.setBounds(15, 41, 144, 19);
		
		textID = new JTextField();
		textID.setActionCommand("");
		textID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textID.setEditable(false);
		textID.setBounds(223, 35, 277, 20);
		textID.setColumns(10);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.setBounds(397, 379, 180, 35);
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
		lblDomicilio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDomicilio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDomicilio.setBounds(15, 247, 144, 19);
		
		textDomicilio = new JTextField();
		textDomicilio.setActionCommand("");
		textDomicilio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textDomicilio.setColumns(10);
		textDomicilio.setBounds(223, 248, 287, 20);
		RestrictedTextField restrictedDomicilio = new RestrictedTextField(this.textDomicilio);
		restrictedDomicilio.setOnlyAlphaNumeric(true);
		restrictedDomicilio.setAcceptSpace(true);
		restrictedDomicilio.setOnlyNums(false);
		restrictedDomicilio.setLimit(40);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTelefono.setBounds(15, 217, 144, 19);
		
		textTelefono = new JTextField();
		textTelefono.setActionCommand("");
		textTelefono.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textTelefono.setColumns(10);
		textTelefono.setBounds(223, 218, 277, 20);
		//Definimos restricciones para que en el campo teléfono sólo se acepten números
		RestrictedTextField restrictedTelefono = new RestrictedTextField(this.textTelefono);
		restrictedTelefono.setOnlyNums(true);
		
		JLabel lblCiudad = new JLabel("Localidad");
		lblCiudad.setForeground(Color.BLACK);
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCiudad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCiudad.setBounds(99, 307, 60, 19);
		
		
//Creo el combo con departamentos y lo lleno de elementos
		JComboBox <String> comboBoxDepartamento = new JComboBox();
		GestionLocalidades gestionLocalidades = new GestionLocalidades();
		Set<String> departamentos = gestionLocalidades.obtieneDepartamentos();
		for (String nombre : departamentos) {
			comboBoxDepartamento.addItem(nombre);
		}
		comboBoxDepartamento.setSelectedIndex(0);
		comboBoxDepartamento.setBounds(223, 277, 156, 22);
		
		
//Creo el Combo con las localidades y lo lleno usando el depto seleccionado
		JComboBox<String> cmbCiudad = new JComboBox();
		String deptoSelec = (String) comboBoxDepartamento.getSelectedItem();					
		Set<String> localidadesEnDepto = gestionLocalidades.obtieneLocalidades(deptoSelec);
		for (String l : localidadesEnDepto)
		{
			cmbCiudad.addItem(l);
		}
		cmbCiudad.setBounds(223, 307, 156, 22);
		
		comboBoxDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbCiudad.removeAllItems();
				Set<String> localidades = gestionLocalidades.obtieneLocalidades((String) comboBoxDepartamento.getSelectedItem());
				for (String l : localidades) {
					cmbCiudad.addItem(l);
				}	
			}
		});
		
		
		
		JComboBox cmbRol = new JComboBox();
		
		cmbRol.setBounds(223, 341, 95, 20);
		cmbRol.addItem("Administrador");
		cmbRol.addItem("Investigador");
		cmbRol.addItem("Aficionado");
		
		
		GestionUsuarios gestionUsuarios = new GestionUsuarios();
		btnAlta = new JButton("Alta");
		btnAlta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
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
					if ((!verificarMail(mail)) || (!verificarPass(clave)) || (!verificarTel(telefono))){
						JOptionPane.showMessageDialog(null, "Error, verifica los datos ingresados.","Error", 
								JOptionPane.ERROR_MESSAGE);
					}else {

						if (cmbRol.getSelectedItem() == "Administrador") {
							if (verificarCI(documento)) {
								Administrador administrador2 = new Administrador(nombre, apellido, mail, clave, Estado.HABILITADO, documento, domicilio, telefono, ciudad, departamento);
								administrador2 = gestionUsuarios.crearAdministrador(administrador2);
								JOptionPane.showMessageDialog(null,  "Administrador ingresado con éxito","Exito",
										JOptionPane.INFORMATION_MESSAGE);	
								limpiarFormulario();
							}else {
								JOptionPane.showMessageDialog(null, "Error, verifica los datos ingresados.","Error", 
										JOptionPane.ERROR_MESSAGE);
							}
						}else if(cmbRol.getSelectedItem() == "Investigador"){
							if (verificarCI(documento)) {
								Investigador investigador = new Investigador(nombre, apellido, mail, clave, Estado.HABILITADO, documento, domicilio, telefono, ciudad, departamento);
								investigador = gestionUsuarios.crearInvestigador(investigador);
								JOptionPane.showMessageDialog(null,  "Usuario ingresado con éxito","Exito",
										JOptionPane.INFORMATION_MESSAGE);	
								limpiarFormulario();
							}else {
								JOptionPane.showMessageDialog(null, "Error, verifica los datos ingresados.","Error", 
										JOptionPane.ERROR_MESSAGE);
							}
						}else { //sino crea un usuario aficionado
							Aficionado aficionado = new Aficionado(nombre, apellido, mail, clave, Estado.HABILITADO);
							aficionado = gestionUsuarios.crearAficionado(aficionado);
							JOptionPane.showMessageDialog(null,  "Aficionado ingresado con éxito","Exito",
									JOptionPane.INFORMATION_MESSAGE);	
							limpiarFormulario();
						}
						
					}

				} catch (ServiciosException | NamingException e) { // TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error", "Error, el usuario no pudo ingresarse",
							JOptionPane.ERROR_MESSAGE);
				}
				

			}
		});

		btnAlta.setBounds(522, 26, 250, 29);
				
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDocumento.setBounds(15, 127, 144, 19);
		
		JLabel lblNombre1 = new JLabel("Nombre");
		lblNombre1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre1.setBounds(15, 66, 144, 19);
		
		JLabel lblApellido1 = new JLabel("Apellido");
		lblApellido1.setToolTipText("");
		lblApellido1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellido1.setBounds(15, 97, 144, 19);
		
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClave.setBounds(15, 157, 144, 19);
		
		
		JLabel lblMail = new JLabel("Correo electr\u00F3nico");
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMail.setBounds(15, 187, 144, 19);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textID.getText().isEmpty()) {
					GestionUsuarios gestionUsuarios = new GestionUsuarios();
					Usuario usuarioAEliminar;
					try {
						usuarioAEliminar = gestionUsuarios.obtienePorId(Long.parseLong(textID.getText()));
						String nombre = usuarioAEliminar.getNombre();
						if (JOptionPane.showConfirmDialog(null, 
								"Está seguro que desea eliminar el usuario " + nombre + "?", 
								"Confirmación",
								JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
							gestionUsuarios.borraUsuario(usuarioAEliminar);
							JFrame jFrame = new JFrame();
							JOptionPane.showMessageDialog(jFrame, "Se eliminó el usuario: " + nombre);
							limpiarFormulario();
						
						}else {
							JOptionPane.showMessageDialog(null,
									"Se canceló la eliminación.",
									"Aviso", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ServiciosException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, el campo ID no puede estar vacío. Debe buscar la persona a eliminar primero.",
							"Error", JOptionPane.ERROR_MESSAGE);

					}
				}
		
		});
		
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		 
		btnEliminar.setBounds(522, 66, 252, 29);
		
		btnBuscar = new JButton("Buscar por mail");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Usuario> usuarios = gestionUsuarios.obtenerPorMail(textMail.getText());
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

				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		 
		btnBuscar.setBounds(522, 152, 252, 29);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Está seguro que desea modificar este usuario?", "Confirmación",
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {

					try {
						if (verificarMail(textMail.getText()) && verificarPass(textClave.getText())){
							Usuario usuario = gestionUsuarios.obtienePorId(Long.parseLong(textID.getText()));
						
							if (Administrador.class.isInstance(usuario) ) {
								if (verificarCI(textDocumento.getText()) || (!verificarTel(textTelefono.getText()))) {
									Administrador administrador = (Administrador) usuario;
									administrador.setNombre(textNombre.getText());
									administrador.setApellido(textApellido.getText());
									administrador.setMail(textMail.getText());
									administrador.setContraseña(textClave.getText());
									administrador.setDocumento(textDocumento.getText());
									administrador.setDomicilio(textDomicilio.getText());
									administrador.setTelefono(textTelefono.getText());
									administrador.setCiudad(cmbCiudad.getSelectedItem().toString());
									administrador.setDepartamento(comboBoxDepartamento.getSelectedItem().toString());
									administrador = gestionUsuarios.actualizarAdministrador(administrador); 
								}else {
									JOptionPane.showMessageDialog(null, "Error, verifica los datos ingresados.","Error", 
											JOptionPane.ERROR_MESSAGE);
								}
									
							} else if (Investigador.class.isInstance(usuario)) {
								if (verificarCI(textDocumento.getText())|| (!verificarTel(textTelefono.getText()))) {
									Investigador investigador = (Investigador) usuario;
									investigador.setNombre(textNombre.getText());
									investigador.setApellido(textApellido.getText());
									investigador.setMail(textMail.getText());
									investigador.setContraseña(textClave.getText());
									investigador.setDocumento(textDocumento.getText());
									investigador.setDomicilio(textDomicilio.getText());
									investigador.setTelefono(textTelefono.getText());
									investigador.setCiudad(cmbCiudad.getSelectedItem().toString());
									investigador.setDepartamento(comboBoxDepartamento.getSelectedItem().toString());
									investigador = gestionUsuarios.actualizarInvestigador(investigador); 
								}else {
									JOptionPane.showMessageDialog(null, "Error, verifica los datos ingresados.","Error", 
											JOptionPane.ERROR_MESSAGE);
								}
							} else if (Aficionado.class.isInstance(usuario)) {
								Aficionado aficionado = (Aficionado) usuario;
								aficionado.setNombre(textNombre.getText());
								aficionado.setApellido(textApellido.getText());
								aficionado.setMail(textMail.getText());
								aficionado.setContraseña(textClave.getText());
								aficionado = gestionUsuarios.actualizaAficionado(aficionado);
								
							}
		
				
							 JOptionPane.showMessageDialog(null, "Usuario modificado con éxito.", "Exito", JOptionPane.INFORMATION_MESSAGE); 
							 limpiarFormulario();
						}else {
							JOptionPane.showMessageDialog(null, "Error, verifica los datos ingresados.","Error", 
									JOptionPane.ERROR_MESSAGE);
						}

					} catch (NumberFormatException | ServiciosException e1) {
						JOptionPane.showMessageDialog(null, "Error, no se pudo actualizar al usuario.", "Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null,
							"Se canceló la modificación.",
							"Aviso", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		 
		btnModificar.setBounds(522, 106, 252, 29);
		
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDepartamento.setBounds(15, 277, 144, 19);
		
		JButton btnLimpiar = new JButton("Limpiar formulario");
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarFormulario();
			}
		});
		btnLimpiar.setBounds(223, 382, 163, 29);
		
		JButton btnBuscarNombre = new JButton("Buscar por nombre y apellido");
		btnBuscarNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuscarNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					frame.dispose();
					List<Usuario> usuarios = gestionUsuarios.obtenerPorNombreApellido(textNombre.getText().toUpperCase(), textApellido.getText().toUpperCase());
//					List<Usuario> usuarios = usuarioBean.obtenerPorNomApe(textNombre.getText(), textApellido.getText());
					VentanaListaUsuarios ventanaListaUsuaries = new VentanaListaUsuarios ((Usuario) usuarioLoged, (List<Usuario>) usuarios);
					ventanaListaUsuaries.ventanaListaUsuaries();
				} catch (ServiciosException e1) {
					JOptionPane.showMessageDialog(null, "Error, no se pudo realizar la búsqueda.", "Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnBuscarNombre.setBounds(520, 192, 252, 27);
		
		JButton btnBuscarRol = new JButton("Buscar por rol");
		btnBuscarRol.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBuscarRol.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				List<Usuario> usuarios = usuarioBean.obtenerTodos();
				List<Usuario> usuariosFiltrados = new LinkedList<Usuario>();
				if (cmbRol.getSelectedItem().toString() == "Administrador") {
					for (Usuario u : usuarios) {
						if (u instanceof Administrador)
							usuariosFiltrados.add(u);
					}
				} else if (cmbRol.getSelectedItem().toString() == "Investigador") {
					for (Usuario u : usuarios) {
						if (u instanceof Investigador)
							usuariosFiltrados.add(u);
					}
				} else {
					for (Usuario u : usuarios) {
						if (u instanceof Aficionado)
							usuariosFiltrados.add(u);
					}
				}
				VentanaListaUsuarios ventanaListaUsuaries = new VentanaListaUsuarios ((Usuario) usuarioLoged, (List<Usuario>) usuariosFiltrados);
				ventanaListaUsuaries.ventanaListaUsuaries();
			}
		});
		btnBuscarRol.setBounds(520, 230, 252, 29);
		contentPane_1.setLayout(null);
		contentPane_1.add(lblDomicilio);
		contentPane_1.add(textDomicilio);
		contentPane_1.add(lblDepartamento);
		contentPane_1.add(comboBoxDepartamento);
		contentPane_1.add(lblCiudad);
		contentPane_1.add(lblRol);
		contentPane_1.add(cmbCiudad);
		contentPane_1.add(cmbRol);
		contentPane_1.add(btnBuscar);
		contentPane_1.add(btnLimpiar);
		contentPane_1.add(btnVolver);
		contentPane_1.add(btnBuscarNombre);
		contentPane_1.add(btnBuscarRol);
		contentPane_1.add(lblClave);
		contentPane_1.add(lblMail);
		contentPane_1.add(lblTelefono);
		contentPane_1.add(textTelefono);
		contentPane_1.add(textMail);
		contentPane_1.add(textClave);
		contentPane_1.add(lblDocumento);
		contentPane_1.add(textDocumento);
		contentPane_1.add(lblNombre1);
		contentPane_1.add(lblApellido1);
		contentPane_1.add(btnModificar);
		contentPane_1.add(btnEliminar);
		contentPane_1.add(lblUsuario);
		contentPane_1.add(lblID);
		contentPane_1.add(textNombre);
		contentPane_1.add(textID);
		contentPane_1.add(textApellido);
		contentPane_1.add(btnAlta);
		
		
				
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
		
		//Cargo datos de persona si se le pasa un ID al crear la ventana
		if (id != null) {
			try {
				Usuario usuario = usuarioBean.obtenerPorID(id);
				if (usuario != null) {
					if (usuario instanceof Administrador) {
						Administrador administrador = (Administrador) usuario;
						if (administrador.getCiudad() != null) 
							cmbCiudad.setSelectedItem(administrador.getCiudad());
						cmbRol.setSelectedItem("Administrador");
						textDocumento.setText(administrador.getDocumento());
						textDomicilio.setText(administrador.getDomicilio());
						textTelefono.setText(administrador.getTelefono());
						cmbCiudad.setSelectedItem(administrador.getCiudad());
						comboBoxDepartamento.setSelectedItem(administrador.getDepartamento());
						
					} else if (usuario instanceof Investigador) {
						Investigador investigador = (Investigador) usuario;
						if (investigador.getCiudad() != null)
							cmbCiudad.setSelectedItem(investigador.getCiudad());
						cmbRol.setSelectedItem("Investigador");
						textDocumento.setText(investigador.getDocumento());
						textDomicilio.setText(investigador.getDomicilio());
						textTelefono.setText(investigador.getTelefono());
						cmbCiudad.setSelectedItem(investigador.getCiudad());
						comboBoxDepartamento.setSelectedItem(investigador.getDepartamento());
					} else {
						Aficionado aficionado = (Aficionado) usuario;
						cmbRol.setSelectedItem("Aficionado");
					}
					textID.setText(Long.toString(usuario.getId()));
					textNombre.setText(usuario.getNombre());
					textApellido.setText(usuario.getApellido());
					textClave.setText(usuario.getContraseña());
					textMail.setText(usuario.getMail());
					
					

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
	
	public Boolean verificarMail(String mail) {
		if (!mail.matches(EMAIL_PATTERN)) {
			return Boolean.FALSE;
		}else {
			return Boolean.TRUE;
		}
	}
	
	public static boolean verificarPass(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
	
	public static boolean verificarTel(final String telefono) {
		if (telefono.matches("[+-]?\\d*(\\.\\d+)?"))
			return true;
		else 
			return false;
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

