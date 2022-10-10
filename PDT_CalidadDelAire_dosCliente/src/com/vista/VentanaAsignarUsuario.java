package com.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.controlador.GestionFormularios;
import com.controlador.GestionUsuarios;
import com.entities.Formulario;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import Atxy2k.CustomTextField.RestrictedTextField;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VentanaAsignarUsuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaAsignaUsuario() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAsignarUsuario frame = new VentanaAsignarUsuario(usuarioLoged, formulario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	private static Formulario formulario;
	private JTextField textId;
	private JTextField textNombre;
	private JTextField textApellido;
	private List<Usuario> usuarios;
	
	DefaultTableModel modelo = new DefaultTableModel();
	JTable table = new JTable(modelo);
	JScrollPane scrollPane = new JScrollPane(table);
	JPanel panel = new JPanel();
	
	
	
	/**
	 * Create the frame.
	 */
	public VentanaAsignarUsuario(Usuario usuarioLogedRef, Formulario formularioRef) {
		setTitle("Asigna Usuario");
		VentanaAsignarUsuario.usuarioLoged = usuarioLogedRef;
		VentanaAsignarUsuario.formulario = formularioRef;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);		
		//Agrego el fondo
		contentPane = new PaneImage();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel(nombreDelUsuario);
		
		String nombreFormulario = formulario.getNombre();
		Long idFormulario = formulario.getId();
		
		
		
		
		String nombreDelFormulario = formularioRef.getNombre();
		JLabel lblNewLabel_1 = new JLabel(nombreDelFormulario);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		
		textId = new JTextField();
		textId.setColumns(10);
		
		// Servicios
		GestionUsuarios gestionUsuarios = new GestionUsuarios();
		GestionFormularios gestionFormularios = new GestionFormularios();
		HaceTablas haceTablas = new HaceTablas();
		RestrictedTextField restrictedId = new RestrictedTextField(this.textId);
		
		
		//Definimos restricciones para que en el campo Id sólo se acepten números
		restrictedId.setOnlyNums(true);
		
		//Creo el modelo de tabla con todos los usuarios
		try {
			usuarios = gestionUsuarios.obtieneUsuarios();
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		modelo = haceTablas.haceTablaUsuarios(usuarios);
		//Agrego el modelo a la tabla
		table.setModel(modelo);
		panel.add(scrollPane);
		
		JButton btnBuscaPorNombreApellido = new JButton("Nombre y Apellido");
		btnBuscaPorNombreApellido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					usuarios = gestionUsuarios.obtenerPorNombreApellido(textNombre.getText().toUpperCase(), textApellido.getText().toUpperCase());
					modelo = haceTablas.haceTablaUsuarios(usuarios);
					//Agrego el modelo a la tabla
					table.setModel(modelo);
					
					
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ServiciosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBuscaPorNombreApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_2 = new JLabel("Busca por:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnBuscaId = new JButton("Id");
		btnBuscaId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Usuario usuario = new Usuario();
				if (!textId.getText().equals("")) {
					try {
						usuario = gestionUsuarios.obtienePorId(Long.parseLong(textId.getText()));
					} catch (NumberFormatException | NamingException | ServiciosException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (usuario == null) {
						JOptionPane.showMessageDialog(null, "Usuario no encontrado");	
						limpiaCampos();}
					else {
						String nombre = usuario.getNombre();
						Long idUsuario = usuario.getId();
						Long idFormulario = formulario.getId();
						int confirmacion =  JOptionPane.showConfirmDialog(null,"¿Asigna el formulario: " + nombreFormulario + " a " + nombre + "?", "Confirmar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (confirmacion == 0) {
							try {
								Formulario formulario = gestionFormularios.asignarUsuario(idFormulario, idUsuario);
								JOptionPane.showMessageDialog(null, "Se asignó correctamente el formulario " + formulario.getNombre() + " al usuario: " + nombre);	
								limpiaCampos();
							} catch (NamingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ServiciosException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
					}
					}
					else {
						JOptionPane.showMessageDialog(null, "Debe ingresar un Id");	
						limpiaCampos();
					}
			}
		});
		btnBuscaId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				try {
					VentanaListaFormularios ventanaListaFormularios = new VentanaListaFormularios(usuarioLoged, null);
					ventanaListaFormularios.ventanaListaFormularios();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnAsignar = new JButton("Asignar");
		btnAsignar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List <Usuario> usuariosSeleccionados = new LinkedList<Usuario>();
				int[] lineasSeleccionadas = table.getSelectedRows();
				if (lineasSeleccionadas.length != 0)
				{
					for (int i : lineasSeleccionadas) {
						usuariosSeleccionados.add(usuarios.get(i));
					}
					String lista = "¿Asigna el formulario " + nombreDelFormulario + " a: " ;
					for (Usuario u : usuariosSeleccionados) {
						lista = lista + u.getNombre() + ", ";
					}
					lista = lista + "?";
					int confirmacion =  JOptionPane.showConfirmDialog(null, lista , "Confirmar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (confirmacion == 0) {
						List<Usuario> usuariosAsignados = new LinkedList<>();
						for (Usuario u : usuariosSeleccionados) {
							Long idUsuario = u.getId();
							try {
								gestionFormularios.asignarUsuario(idFormulario, idUsuario);
								usuariosAsignados.add(u);
							} catch (NamingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ServiciosException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if (usuariosAsignados.size() == usuariosSeleccionados.size()) {
							int largo = usuariosAsignados.size();
							JOptionPane.showMessageDialog(null, "Se asignó correctamente " + largo + " usuarios al formulario " + nombreFormulario);	
							limpiaCampos();
						}
						else {
							JOptionPane.showMessageDialog(null, "No se pudo asignar correctamente los usuarios al formulario");	
							limpiaCampos();
						}
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Debes seleccionar al menos un usuario");	
				limpiaCampos();
			}
			
		});
		btnAsignar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(237)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(205)
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAsignar, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(17)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblApellido)
										.addComponent(lblNombre)
										.addComponent(lblId))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblNewLabel_2)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnBuscaPorNombreApellido, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnBuscaId, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 501, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(40, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombre)
								.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblApellido)
								.addComponent(textApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblId)
								.addComponent(textId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(49)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnBuscaPorNombreApellido)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBuscaId))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnAsignar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnVolver, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
					.addGap(12))
		);
		
		
		contentPane.setLayout(gl_contentPane);
	}


	protected void limpiaCampos() {
		textNombre.setText(null);
		textApellido.setText(null);
		textId.setText(null);
	}
}
