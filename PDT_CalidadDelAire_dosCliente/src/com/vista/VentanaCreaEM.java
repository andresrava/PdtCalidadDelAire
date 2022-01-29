package com.vista;

import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaCreaEM extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaCreaEM() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCreaEM frame = new VentanaCreaEM(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static Usuario usuarioLoged;
	private JTextField textNombre = new JTextField();
	private JTextField textComentarios;
	private List<Casilla> lista = new LinkedList<Casilla>();
	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public VentanaCreaEM(Usuario usuarioLogedRef) throws NamingException {
		setTitle("Crea Estaci\u00F3n de Medici\u00F3n");
		VentanaCreaEM.usuarioLoged = usuarioLogedRef;
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
		
		
		//Creo el combo con departamentos y lo lleno de elementos
		JComboBox <String> comboBoxDepartamento = new JComboBox();
		GestionLocalidades gestionLocalidades = new GestionLocalidades();
		Set<String> departamentos = gestionLocalidades.obtieneDepartamentos();
		for (String nombre : departamentos) {
			comboBoxDepartamento.addItem(nombre);
		}
		comboBoxDepartamento.setSelectedIndex(0);
		

		//Creo el Combo con Casillas disponibles y lo lleno de elementos	
		JComboBox<Casilla> comboBoxCasillasDisponibles = new JComboBox();
		List<Casilla> casillasDisponibles = new LinkedList<Casilla>();
		GestionCasillas gestionCasillas = new GestionCasillas();
		casillasDisponibles = gestionCasillas.listaCasillas();
		for (Casilla c : casillasDisponibles) {
			comboBoxCasillasDisponibles.addItem(c);
		}
		
		//Creo el Combo con las localidades y lo lleno usando el depto seleccionado
		JComboBox<String> comboBoxLocalidades = new JComboBox();
		String deptoSelec = (String) comboBoxDepartamento.getSelectedItem();					
		Set<String> localidadesEnDepto = gestionLocalidades.obtieneLocalidades(deptoSelec);
		for (String l : localidadesEnDepto)
		{
			comboBoxLocalidades.addItem(l);
		}
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
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!textNombre.getText().isEmpty()) {
				String nombre = textNombre.getText();
				String nombreLocalidad = (String) comboBoxLocalidades.getSelectedItem();
				String depto = (String) comboBoxDepartamento.getSelectedItem();
				String coment = textComentarios.getText();
				GestionEstaciones gestionEstaciones = new GestionEstaciones();
				
				EstacionDeMedicion estacion = new EstacionDeMedicion(nombre, coment , lista , depto , nombreLocalidad , usuarioLoged);
				try {
					estacion = gestionEstaciones.crearEstacion(estacion);
					Long id = estacion.getId();
					if (!(id == null))
					System.out.println("Se creó la Estación de Medición!");
					JOptionPane.showMessageDialog(null, "Se creó la Estación de Medición");
					dispose();
					VentanaEMedicion ventanaEMedicion = new VentanaEMedicion(usuarioLoged);
					ventanaEMedicion.ventanaEMedicion();
				} catch (NamingException e1) {
					System.out.println("No se pudo crear la Estación por NamingException");
					e1.printStackTrace();
				} catch (ServiciosException e1) {
					System.out.println("No se pudo crear la Estación por ServiciosException");
					e1.printStackTrace();
				}
				} else {
					JOptionPane.showMessageDialog(null, "Debe ingresar un nombre");
				}

			}
			
		});
		
		JComboBox<Casilla> comboBoxCasillasEnEM = new JComboBox();
		
		textNombre.setColumns(15);
		
		JLabel lblNewLabel_6 = new JLabel("Comentarios:");
		
		textComentarios = new JTextField();
		textComentarios.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Casillas disponibles");
		

		
		JButton btnAgregar = new JButton("Agregar");
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
				try {
					VentanaEMedicion ventanaEM = new VentanaEMedicion(usuarioLoged);
					ventanaEM.ventanaEMedicion();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(comboBoxLocalidades, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(lblNewLabel_4)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBoxCasillasEnEM, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(lblNewLabel_6)
									.addGap(18)
									.addComponent(textComentarios, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(lblNewLabel_1)
									.addGap(48)
									.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(comboBoxDepartamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnQuitar, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(126)
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNewLabel_1))
								.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(comboBoxDepartamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(comboBoxLocalidades, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(4)
									.addComponent(lblNewLabel_4))
								.addComponent(btnQuitar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxCasillasEnEM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_6)
								.addComponent(textComentarios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCrear, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
