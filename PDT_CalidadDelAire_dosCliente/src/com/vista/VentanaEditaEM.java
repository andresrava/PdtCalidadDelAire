package com.vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.controlador.GestionCasillas;
import com.controlador.GestionEstaciones;
import com.controlador.GestionLocalidades;
import com.entities.Casilla;
import com.entities.EstacionDeMedicion;
import com.entities.Usuario;
import com.exceptions.ServiciosException;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class VentanaEditaEM extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaEditaEM() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEditaEM frame = new VentanaEditaEM(usuarioLoged, estacionAEditar);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	private static EstacionDeMedicion estacionAEditar;
	private JTextField textNombre;
	private JTextField textComentarios;
	private List<Casilla> lista = new LinkedList<Casilla>();
	
	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public VentanaEditaEM(Usuario usuarioLogedRef, EstacionDeMedicion estacionAEditarRef) throws NamingException {
		setTitle("Edita Estaci\u00F3n de Medici\u00F3n");
		VentanaEditaEM.usuarioLoged = usuarioLogedRef;
		VentanaEditaEM.estacionAEditar = estacionAEditarRef;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		//Le agrego el fondo
		contentPane = new PaneImage();		
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre(*):");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_2 = new JLabel("Localidad (*):");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_3 = new JLabel("Departamento (*):");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_4 = new JLabel("Casillas:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
				
		//Creo el Combo con departamentos y lo lleno de elementos
		JComboBox <String> comboBoxDepartamento = new JComboBox();
		GestionLocalidades gestionLocalidades = new GestionLocalidades();
		Set<String> departamentos = gestionLocalidades.obtieneDepartamentos();
		for (String nombre : departamentos) {
			comboBoxDepartamento.addItem(nombre);
		}
		comboBoxDepartamento.setSelectedItem(estacionAEditar.getDepartamento());
		
		//Creo el Combo con localidades y lo lleno de elementos
		JComboBox<String> comboBoxLocalidades = new JComboBox();
		Set<String> localidades = gestionLocalidades.obtieneLocalidades(estacionAEditar.getDepartamento());
		for (String l : localidades) {
			comboBoxLocalidades.addItem(l);
		}
		comboBoxLocalidades.setSelectedItem(estacionAEditar.getLocalidad());
		//El combo Localidades responde a un cambio en el combo Departamentos
		comboBoxDepartamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxLocalidades.removeAllItems();
				Set<String> localidades = gestionLocalidades.obtieneLocalidades((String) comboBoxDepartamento.getSelectedItem());
				for (String l : localidades) {
					comboBoxLocalidades.addItem(l);
				}	
			}
		});

		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnActualizar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (textNombre.getText() == null) 
					JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para el formulario","Error", JOptionPane.WARNING_MESSAGE);
				else {
				String nombre = textNombre.getText();
				estacionAEditar.setNombre(nombre);
				String depto = (String) comboBoxDepartamento.getSelectedItem();
				estacionAEditar.setDepartamento(depto);
				String localidad = (String) comboBoxLocalidades.getSelectedItem();
				estacionAEditar.setLocalidad(localidad);
				String coment = textComentarios.getText();
				estacionAEditar.setDescripcion(coment);
				GestionEstaciones gestionEstaciones = new GestionEstaciones();
				try {
					estacionAEditar = gestionEstaciones.actualizarEstacion(estacionAEditar);
					JOptionPane.showMessageDialog(null, "Se actualizó la Estación de Medición","Actualizar Estación de Medición", JOptionPane.OK_OPTION);
				} catch (NamingException e1) {
					System.out.println("No se pudo crear la Estación por NamingException");
					e1.printStackTrace();
				} catch (ServiciosException e1) {
					System.out.println("No se pudo crear la Estación por ServiciosException");
					e1.printStackTrace();
				}
				dispose();
				VentanaEMedicion ventanaEMedicion;
				try {
					ventanaEMedicion = new VentanaEMedicion(usuarioLoged);
					ventanaEMedicion.ventanaEMedicion();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
			}	
			
		});
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setText(estacionAEditar.getNombre());
		
		JComboBox<Casilla> comboBoxCasillasEnEM = new JComboBox();
		lista = estacionAEditar.getCasillas();
		for (Casilla c : lista) {
			comboBoxCasillasEnEM.addItem(c);
		}
		
		JLabel lblNewLabel_6 = new JLabel("Comentarios:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textComentarios = new JTextField();
		textComentarios.setColumns(10);
		textComentarios.setText(estacionAEditar.getDescripcion());
		
		JLabel lblNewLabel_5 = new JLabel("Casillas disponibles");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		

			
		JComboBox<Casilla> comboBoxCasillasDisponibles = new JComboBox();
		List<Casilla> casillasDisponibles = new LinkedList<Casilla>();
		GestionCasillas gestionCasillas = new GestionCasillas();
		casillasDisponibles = gestionCasillas.listaCasillas();
		for (Casilla c : casillasDisponibles) {
			comboBoxCasillasDisponibles.addItem(c);
			comboBoxCasillasDisponibles.updateUI();
		}
		
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Casilla c = (Casilla) comboBoxCasillasDisponibles.getSelectedItem();
				if (!lista.contains(c)) {
				comboBoxCasillasEnEM.addItem(c);
				comboBoxCasillasEnEM.updateUI();
				lista.add(c);}				
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(144, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(comboBoxCasillasDisponibles, 0, 239, Short.MAX_VALUE))
						.addComponent(lblNewLabel_5))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_5)
					.addGap(13)
					.addComponent(comboBoxCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
					.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		panel.setLayout(gl_panel);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaListaEM ventanaListaEM = new VentanaListaEM(usuarioLoged);
				ventanaListaEM.ventanaListaEM();
			}
		});
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnQuitar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Casilla c = (Casilla) comboBoxCasillasEnEM.getSelectedItem();
				lista.remove(c);
				comboBoxCasillasEnEM.removeItem(c);
				comboBoxCasillasEnEM.updateUI();
				}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addGap(45)
								.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(10)
										.addComponent(lblNewLabel_1)
										.addGap(34)
										.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(10)
										.addComponent(lblNewLabel_4)
										.addGap(10)
										.addComponent(comboBoxCasillasEnEM, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(10)
										.addComponent(lblNewLabel_6)
										.addGap(18)
										.addComponent(textComentarios, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(10)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNewLabel_3)
											.addComponent(lblNewLabel_2))
										.addGap(132)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(comboBoxLocalidades, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(comboBoxDepartamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGap(127)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnQuitar, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addGap(31)))
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel_1))
								.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3)
								.addComponent(comboBoxDepartamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(19)
									.addComponent(lblNewLabel_2))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(comboBoxLocalidades, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(4)
									.addComponent(lblNewLabel_4))
								.addComponent(comboBoxCasillasEnEM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnQuitar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_6)
								.addComponent(textComentarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(140, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
