package com.vista;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.entities.Usuario;

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
	private JButton btnListarFuncionalidades;
	private JLabel lblUsuario;
	private JLabel lblID;
	private JLabel lblRol;
	private JTextField textID;
	private JTextField textRol;
	
	/**
	 * Launch the application.
	 */
	public static void VentanaUsuarios(Usuario usuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaUsuarios window = new VentanaUsuarios(usuario);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws NamingException 
	 */
	public VentanaUsuarios(Usuario usuario) throws NamingException {
		initialize(usuario);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws NamingException 
	 */
	private void initialize(Usuario usuario) throws NamingException {
		String ruta="GestionUsuarios/UsuarioBean!com.servicios.UsuarioBeanRemote";
		UsuarioBeanRemote usuarioBean = (UsuarioBeanRemote) InitialContext.doLookup(ruta);
		
		String ruta2="GestionUsuarios/RoleBean!com.servicios.RoleBeanRemote";
		RoleBeanRemote rolBean = (RoleBeanRemote) InitialContext.doLookup(ruta2);
		
		String ruta3="GestionUsuarios/FuncionalidadBean!com.servicios.FuncionalidadBeanRemote";
		FuncionalidadBeanRemote funcionalidadBean = (FuncionalidadBeanRemote) InitialContext.doLookup(ruta3);
		
		frame = new JFrame();
		frame.setBounds(600, 100, 418, 384);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textDocumento = new JTextField();
		textDocumento.setBounds(104, 58, 186, 20);
		frame.getContentPane().add(textDocumento);
		textDocumento.setColumns(10);
			
		textNombre = new JTextField();
		textNombre.setBounds(104, 91, 186, 20);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setBounds(104, 122, 186, 20);
		frame.getContentPane().add(textApellido);
		textApellido.setColumns(10);
		
		textClave = new JPasswordField();
		textClave.setBounds(104, 153, 186, 20);
		frame.getContentPane().add(textClave);
		textClave.setColumns(10);
		
		textMail = new JTextField();
		textMail.setBounds(104, 186, 186, 20);
		frame.getContentPane().add(textMail);
		textMail.setColumns(10);
		
		lblRol = new JLabel("Rol");
		lblRol.setBounds(28, 220, 47, 14);
		frame.getContentPane().add(lblRol);
		
		textRol = new JTextField();
		textRol.setBounds(104, 217, 186, 20);
		frame.getContentPane().add(textRol);
		textRol.setColumns(10);
		
		lblUsuario = new JLabel("New label");
		lblUsuario.setBounds(28, 11, 361, 14);
		lblUsuario.setText("Usuario logueado en el sistema: " + usuario.getNombre() + " " + usuario.getApellido());
		frame.getContentPane().add(lblUsuario);
		
		lblID = new JLabel("ID");
		lblID.setBounds(28, 37, 47, 14);
		frame.getContentPane().add(lblID);
		
		textID = new JTextField();
		textID.setEditable(false);
		textID.setBounds(103, 31, 187, 20);
		frame.getContentPane().add(textID);
		textID.setColumns(10);
		
		btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String documento=textDocumento.getText();
					String nombre=textNombre.getText();
					String apellido=textApellido.getText();
					String clave=textClave.getText();
					String mail=textMail.getText();				 
					
					Usuario usuario2 = usuarioBean.crear(new Usuario(apellido, clave, documento, mail, nombre));
					
					JOptionPane.showMessageDialog(null, "Exito","Usuario ingresado con éxito", JOptionPane.INFORMATION_MESSAGE);

					List<Rol> roles = rolBean.buscarRol(textRol.getText());
					
					if (roles.size() == 1) {
						usuarioBean.asignarRol(usuario2.getIdUsuario(), roles.get(0).getIdRol());
					} else {
						JOptionPane.showMessageDialog(null, "Error, no se encontró rol indicado.","Error", JOptionPane.ERROR_MESSAGE);
					}
					
					

				} catch (ServiciosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error","Error, el usuario no pudo ingresarse", JOptionPane.ERROR_MESSAGE);
				}
				limpiarFormulario();

			}
		});
		btnAlta.setBounds(300, 58, 89, 23);
		frame.getContentPane().add(btnAlta);
				
		JLabel lblDocumento = new JLabel("cedula");
		lblDocumento.setBounds(28, 62, 100, 14);
		frame.getContentPane().add(lblDocumento);
		
		JLabel lblNombre1 = new JLabel("nombre");
		lblNombre1.setBounds(28, 95, 100, 14);
		frame.getContentPane().add(lblNombre1);
		
		JLabel lblApellido1 = new JLabel("apellido");
		lblApellido1.setBounds(28, 126, 100, 14);
		frame.getContentPane().add(lblApellido1);
		
		
		JLabel lblClave = new JLabel("clave");
		lblClave.setBounds(28, 157, 100, 14);
		frame.getContentPane().add(lblClave);
		
		
		JLabel lblMail = new JLabel("mail");
		lblMail.setBounds(28, 190, 100, 14);
		frame.getContentPane().add(lblMail);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textID.getText() != "") {
					try {
						usuarioBean.borrar(Long.parseLong(textID.getText()));
						JOptionPane.showMessageDialog(null, "Usuario eliminado con éxito.", "Exito", JOptionPane.INFORMATION_MESSAGE);
						limpiarFormulario();
					} catch (NumberFormatException | ServiciosException e1) {
						JOptionPane.showMessageDialog(null, "Error, no se pudo eliminar el usuario.","Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Error, el campo ID no puede estar vacío. Debe buscar la persona a eliminar primero.","Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnEliminar.setBounds(300, 94, 89, 23);
		frame.getContentPane().add(btnEliminar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Usuario> usuarios = usuarioBean.buscarPorDocumento(textDocumento.getText());
					if (usuarios.size() == 1) {
						textID.setText(Long.toString(usuarios.get(0).getIDUsuario()));
						textNombre.setText(usuarios.get(0).getNombre());
						textApellido.setText(usuarios.get(0).getApellido());
						textClave.setText(usuarios.get(0).getClave());
						textMail.setText(usuarios.get(0).getMail());
						if (usuarios.get(0).getRol() !=null) {
							textRol.setText(usuarios.get(0).getRol().getNombre());
						}else {
							textRol.setText("");
						}
						
							JOptionPane.showMessageDialog(null, "Usuario encontrado con éxito.", "Exito", JOptionPane.INFORMATION_MESSAGE);
						
					} else {
						JOptionPane.showMessageDialog(null, "Error, no se encontró el usuario.","Error", JOptionPane.ERROR_MESSAGE);
						limpiarFormulario();
					}
				} catch (HeadlessException e1) {
					JOptionPane.showMessageDialog(null, "Error, no se encontró el usuario.","Error", JOptionPane.ERROR_MESSAGE);
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
				List<Rol> roles = rolBean.buscarRol(textRol.getText());
				if (roles.size() == 1) {
					try {
						usuarioBean.actualizar(new Usuario(Long.parseLong(textID.getText()),textApellido.getText(), textClave.getText(), textDocumento.getText(),
								textMail.getText(), textNombre.getText(), roles.get(0)));
						JOptionPane.showMessageDialog(null, "Usuario modificado con éxito.", "Exito", JOptionPane.INFORMATION_MESSAGE);
						limpiarFormulario();
					} catch (NumberFormatException | ServiciosException e1) {
						JOptionPane.showMessageDialog(null, "Error, no se pudo actualizar al usuario.","Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error, no se encontró rol indicado.","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModificar.setBounds(300, 156, 89, 23);
		frame.getContentPane().add(btnModificar);
		
		btnListarFuncionalidades = new JButton("Listar funcionalidades");
		btnListarFuncionalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rol rolAux = (Rol) rolBean.buscarRol(usuario.getRol().getNombre());
				for (Funcionalidad fun: rolAux.getFuncionalidades()) {
					System.out.println(fun);
				}
			}
		});
		btnListarFuncionalidades.setBounds(219, 278, 175, 23);
		frame.getContentPane().add(btnListarFuncionalidades);
		
		JComboBox<String> cmbFuncionalidades = new JComboBox();
		cmbFuncionalidades.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		cmbFuncionalidades.setBounds(28, 278, 160, 22);
		List<Funcionalidad> funcionalidades = funcionalidadBean.obtenerTodos();
		for (Funcionalidad fun: funcionalidades) {
			cmbFuncionalidades.addItem(fun.getNombre());
		}
		frame.getContentPane().add(cmbFuncionalidades);
		
		JLabel lblFuncionalidades = new JLabel("Funcionalidades");
		lblFuncionalidades.setBounds(28, 263, 100, 14);
		frame.getContentPane().add(lblFuncionalidades);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					logout();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogout.setBounds(300, 313, 89, 23);
		frame.getContentPane().add(btnLogout);
		
		
		
		
		
	}
	
	public void limpiarFormulario() {
		textNombre.setText(null);
		textApellido.setText(null);
		textDocumento.setText(null);
		textClave.setText(null);
		textMail.setText(null);
		textID.setText(null);
		textRol.setText(null);
	}
	
	public void logout() throws NamingException {
		this.frame.dispose();
		Login login= new Login();
		Login.main(null);
		
	}

	
}

