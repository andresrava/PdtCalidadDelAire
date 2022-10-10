package com.vista;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.entities.Usuario;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaListaUsuarios extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaListaUsuaries() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaUsuarios frame = new VentanaListaUsuarios(usuarioLoged, usuarios);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	private static List<Usuario> usuarios;
	DefaultTableModel modelo = new DefaultTableModel();
	JTable table = new JTable(modelo);
	JScrollPane scrollPane = new JScrollPane(table);
	JPanel panel = new JPanel();
	
	/**
	 * Create the frame.
	 */
	public VentanaListaUsuarios(Usuario usuarioLogedRef, List<Usuario> usuariosRef) {
		setTitle("Lista Usuarios");
		VentanaListaUsuarios.usuarioLoged = usuarioLogedRef;
		VentanaListaUsuarios.usuarios = usuariosRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		//Agrego el fondo
		contentPane = new PaneImage();		
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel.add(scrollPane);

		String nombreUsuario = usuarioLoged.getNombre();
		JLabel lblNewLabel_2 = new JLabel("Usuario: " + nombreUsuario);
		
		HaceTablas haceTablas = new HaceTablas();
		modelo = haceTablas.haceTablaUsuarios(usuarios);
		
		//Agrego el modelo a la tabla
		table.setModel(modelo);
		
		panel.add(scrollPane);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Long id;
					
					if (table.getSelectedRow() != -1) {
						Usuario usuario = usuarios.get(table.getSelectedRow());
						id = usuario.getId();
//						id = (Long) table.getValueAt(table.getSelectedRow(), 0);
					} else {
						id = null;
					}
					editar(usuarioLoged, id);
				} catch (NamingException e1) {
					e1.printStackTrace();
				}	
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					dispose();
					VentanaUsuarios ventanaUsuarios = new VentanaUsuarios((Usuario) usuarioLoged, null);
					ventanaUsuarios.VentanaUsuarios((Usuario) usuarioLoged, null);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))))
					.addGap(42)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(68)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		
	}
	public void editar(Usuario usuarioLoged, Long id) throws NamingException {
		try {
			dispose();
			VentanaUsuarios ventanaUsuarios = new VentanaUsuarios((Usuario) usuarioLoged, id);
			ventanaUsuarios.VentanaUsuarios((Usuario) usuarioLoged, id);
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
	}
}
