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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.LayoutStyle.ComponentPlacement;

public class VentanaSeleccionarUsuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaSeleccionarUsuario() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSeleccionarUsuario frame = new VentanaSeleccionarUsuario(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	private JTextField textId;
	private JTextField textNombre;
	private JTextField textApellido;
	private List<Usuario> usuarios;
	private List<Formulario> formularios;
	
	DefaultTableModel modelo = new DefaultTableModel();
	JTable table = new JTable(modelo);
	JScrollPane scrollPane = new JScrollPane(table);
	JPanel panel = new JPanel();
	
	
	
	/**
	 * Create the frame.
	 */
	public VentanaSeleccionarUsuario(Usuario usuarioLogedRef) {
		setTitle("Selecciona Usuario");
		VentanaSeleccionarUsuario.usuarioLoged = usuarioLogedRef;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);		
		//Agrego el fondo
		contentPane = new PaneImage();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel(nombreDelUsuario);
						
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
						JOptionPane.showMessageDialog(null, "Usuario " + nombre);	
						formularios = usuario.getFormularios();
						try {
							VentanaListaFormularios ventanaListaFormularios = new VentanaListaFormularios(usuarioLoged, formularios);
							ventanaListaFormularios.ventanaListaFormularios();
							dispose();
						} catch (NamingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List <Usuario> usuariosSeleccionados = new LinkedList<Usuario>();
				Set <Formulario> conjuntoFormulariosAsociados = new HashSet<>();
				List<Formulario> listaFormulariosAsociados = new LinkedList<>();
				int[] lineasSeleccionadas = table.getSelectedRows();
				if (lineasSeleccionadas.length != 0)
				{
					for (int i : lineasSeleccionadas) {
						usuariosSeleccionados.add(usuarios.get(i));
					}
					for (Usuario u : usuariosSeleccionados) {
						List<Formulario> formulariosAsociadosAlUsuario = u.getFormularios();
						for (Formulario f : formulariosAsociadosAlUsuario) {
							conjuntoFormulariosAsociados.add(f);
						}
					}
					for (Formulario f : conjuntoFormulariosAsociados) {
						listaFormulariosAsociados.add(f);
					}
					VentanaListaFormularios ventanaListaFormularios;
					try {
						ventanaListaFormularios = new VentanaListaFormularios(usuarioLoged, listaFormulariosAsociados);
						ventanaListaFormularios.ventanaListaFormularios();
						dispose();
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Debes seleccionar al menos un usuario");	
					limpiaCampos();
				}
				
			}
			
		});
		btnSeleccionar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(205)
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSeleccionar, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
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
					.addComponent(lblNewLabel)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
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
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnSeleccionar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
