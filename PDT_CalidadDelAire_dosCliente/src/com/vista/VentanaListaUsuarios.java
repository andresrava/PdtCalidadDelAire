package com.vista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.entities.Administrador;
import com.entities.Usuario;
import com.enumerados.BorradoLogico.Estado;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

import javax.naming.NamingException;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaListaUsuarios {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void VentanaListaUsuarios(Usuario usuarioLoged, List<Usuario> usuarios) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaUsuarios window = new VentanaListaUsuarios(usuarioLoged, usuarios);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static Usuario usuarioLoged;
	private static List<Usuario> usuarios;
	
	public VentanaListaUsuarios(Usuario usuarioLogedRef, List<Usuario> usuariosRef) {
		VentanaListaUsuarios.usuarioLoged = usuarioLogedRef;
		VentanaListaUsuarios.usuarios = usuariosRef;
		
		frame = new JFrame();
		frame.setTitle("Listado usuarios");
		frame.setBounds(100, 100, 639, 289);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblUsuario = new JLabel("New label");
		lblUsuario.setBounds(28, 11, 476, 14);
		lblUsuario.setText("Usuario logueado en el sistema: " + usuarioLoged.getNombre() + " " + usuarioLoged.getApellido());
		frame.getContentPane().add(lblUsuario);
		
		DefaultTableModel modelo= new DefaultTableModel();
		//crea un array que contiene los nombre de las columnas
		final String[] columnNames = {"ID Usuario", "Nombre", "Apellido", "Mail"};
		// insertamos las columnas
		for(int column = 0; column < columnNames.length; column++){
			//agrega las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}
		// Se crea un array que será una de las filas de la tabla. 
		Object [] fila = new Object[columnNames.length]; 
		// Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
		for (int i=0;i<usuarios.size();i++){
			if (usuarios.get(i).getEstado() == Estado.HABILITADO) {
				Long id=usuarios.get(i).getId(); 
				String nombre=usuarios.get(i).getNombre(); 
				String apellido=usuarios.get(i).getApellido(); 
				String mail=usuarios.get(i).getMail();  
			
				fila[0] = id; 
				fila[1] = nombre; 
				fila[2] = apellido; 
				fila[3] = mail;
				modelo.addRow(fila); 
			}
		}
		 
		final JTable table = new JTable(modelo);
		// Se añade al modelo la fila completa.

		//se define el tamaño de la tabla
		table.setPreferredScrollableViewportSize(new Dimension(600, 200));
		//Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(table);
		//crea el panel
		JPanel panel = new JPanel();
				
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					volver(usuarioLoged);
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnVolver)
						.addComponent(btnEditar))
					.addContainerGap(65, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnVolver)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditar)
					.addContainerGap(144, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		frame.pack();

	}
		
	public void volver(Usuario usuarioLoged) throws NamingException {
		try {
		this.frame.dispose();
		VentanaUsuarios ventanaUsuarios = new VentanaUsuarios ((Usuario) usuarioLoged);
		ventanaUsuarios.VentanaUsuarios((Usuario) usuarioLoged);
		} catch (NamingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		
	}
	}
}
