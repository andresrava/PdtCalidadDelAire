package com.vista;

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
import com.services.LocalidadesBeanRemote;

import javax.naming.InitialContext;
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
		setBounds(100, 100, 702, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre(*):");
		
		JLabel lblNewLabel_2 = new JLabel("Localidad (*):");
		
		JLabel lblNewLabel_3 = new JLabel("Departamento (*):");
		
		JLabel lblNewLabel_4 = new JLabel("Casillas:");
		
		GestionLocalidades gestionLocalidades = new GestionLocalidades();
				
		//Creo el Combo con departamentos y lo lleno de elementos
		JComboBox <String> comboBoxDepartamento = new JComboBox();
		Set<String> departamentos = gestionLocalidades.obtieneDepartamentos();
		System.out.println(departamentos);
		for (String nombre : departamentos) {
			comboBoxDepartamento.addItem(nombre);
		}
		String deptoOriginal = estacionAEditar.getDepartamento();
		comboBoxDepartamento.setSelectedItem(deptoOriginal);
		
		JComboBox<String> comboBoxLocalidades = new JComboBox();
		
		comboBoxDepartamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Cargo el Combo con Localidades usando el departamento seleccionado
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
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		//Actualiza con problemas
		btnActualizar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				 JFrame jFrame = new JFrame();
			        JOptionPane.showMessageDialog(jFrame, "Se elimin� la Estacion de Medicion: ");
			}
//				String nombre = textNombre.getText();
//				String depto = (String) comboBoxDepartamento.getSelectedItem();
//				String localidad = (String) comboBoxLocalidades.getSelectedItem();
//				String coment = textComentarios.getText();
//				GestionEstaciones gestionEstaciones = new GestionEstaciones();
//				EstacionDeMedicion estacion = new EstacionDeMedicion(nombre, coment , lista , depto , localidad , usuarioLoged);
//				try {
//					estacion = gestionEstaciones.actualizarEstacion(estacion);
//					Long id = estacion.getId();
//					if (!(id == null))
//					{
//						System.out.println("Se cre� la Estaci�n de Medici�n!");
//						dispose();
//						VentanaEMedicion ventanaEMedicion = new VentanaEMedicion(usuarioLoged);
//						ventanaEMedicion.ventanaEMedicion();
//					}
//				} catch (NamingException e1) {
//					System.out.println("No se pudo crear la Estaci�n por NamingException");
//					e1.printStackTrace();
//				} catch (ServiciosException e1) {
//					System.out.println("No se pudo crear la Estaci�n por ServiciosException");
//					e1.printStackTrace();
//				}
//				
//			}

			
		});
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setText(estacionAEditar.getNombre());
		
		JComboBox<Casilla> comboBoxCasillasEnEM = new JComboBox();
		lista = estacionAEditar.getCasillas();
		
		JLabel lblNewLabel_6 = new JLabel("Comentarios:");
		
		textComentarios = new JTextField();
		textComentarios.setColumns(10);
		textComentarios.setText(estacionAEditar.getDescripcion());
		
		JLabel lblNewLabel_5 = new JLabel("Casillas disponibles");
		

			
		JComboBox<Casilla> comboBoxCasillasDisponibles = new JComboBox();
		List<Casilla> casillasDisponibles = new LinkedList<Casilla>();
		GestionCasillas gestionCasillas = new GestionCasillas();
		casillasDisponibles = gestionCasillas.listaCasillas();
		for (Casilla c : casillasDisponibles) {
			comboBoxCasillasDisponibles.addItem(c);
		}
		
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Casilla c = (Casilla) comboBoxCasillasDisponibles.getSelectedItem();
				if (!lista.contains(c)) {
				comboBoxCasillasEnEM.addItem(c);
				comboBoxCasillasEnEM.updateUI();
				lista.add(c);}
					//JOptionPane.showMessageDialog(null, "Esa Casila ya est� en la Estaci�n");
				
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_5)
					.addContainerGap(121, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(229, Short.MAX_VALUE)
					.addComponent(comboBoxCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(144, Short.MAX_VALUE))
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
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaListaEM ventanaListaEM = new VentanaListaEM(usuarioLoged);
				ventanaListaEM.ventanaListaEM();
			}
		});
		
		JButton btnQuitar = new JButton("Quitar");
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
					.addGap(6)
					.addComponent(btnQuitar, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(126)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
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
							.addGap(50)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_6)
								.addComponent(textComentarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(149)
							.addComponent(btnQuitar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnActualizar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
