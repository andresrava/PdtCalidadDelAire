package com.vista;

import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controlador.GestionEstaciones;
import com.controlador.GestionLocalidades;
import com.entities.EstacionDeMedicion;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class VentanaListaEM extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaListaEM() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaEM frame = new VentanaListaEM(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	private List<EstacionDeMedicion> estaciones;
	private JTextField textNombreEM;
	DefaultTableModel modelo = new DefaultTableModel();
	JTable table = new JTable(modelo);
	JScrollPane scrollPane = new JScrollPane(table);
	JPanel panel_1 = new JPanel();
	/**
	 * Create the frame.
	 */
	public VentanaListaEM(Usuario usuarioLogedRef) {
		setTitle("Lista Estaciones de Medici\u00F3n");
		VentanaListaEM.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 800, 500);
		//Agrego el fondo
		contentPane = new PaneImage();		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreUsuario = usuarioLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreUsuario);
		
		GestionEstaciones gestionEstaciones = new GestionEstaciones();
		try {
			estaciones = gestionEstaciones.obtieneEM(); 
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		


		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		//Creo el modelo de tabla con todas las estaciones
		HaceTablas haceTablas = new HaceTablas();
		modelo= haceTablas.haceTablaEM(estaciones);
	
		//Creo la tabla y agrego el modelo
		table.setModel(modelo);
		
		//Creo el panel y le agrego el ScrollPane
		panel_1.add(scrollPane);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	
				EstacionDeMedicion estacion = new EstacionDeMedicion();
				try {
					int id = 0;
					if (table.getSelectedRow() != -1) {
						id = table.getSelectedRow();
						estacion = estaciones.get(id);
						VentanaEditaEM ventanaEditaEM = new VentanaEditaEM(usuarioLoged , estacion );
						ventanaEditaEM.ventanaEditaEM();
					}else {
						JOptionPane.showMessageDialog(null, "Seleccione la Estación a editar");
						dispose();
						VentanaListaEM frame = new VentanaListaEM(usuarioLoged);
						frame.setVisible(true);
					}
					
					
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				try {
					VentanaEMedicion ventanaEMedicion = new VentanaEMedicion(usuarioLoged);
					ventanaEMedicion.ventanaEMedicion();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EstacionDeMedicion estacion = new EstacionDeMedicion();
				int id = 0;
				if (table.getSelectedRow() != -1) {
					id = table.getSelectedRow();
					estacion = estaciones.get(id);
					String nombreEstacionAEliminar = estacion.getNombre();
					int confirmacion =  JOptionPane.showConfirmDialog(null,"Realmente desea Eliminar la Estacion De Medicion: " + nombreEstacionAEliminar + "?", "Confirmar la eliminación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (confirmacion == 0)
						{
							try {
								gestionEstaciones.borraEM(estacion);
								JFrame jFrame = new JFrame();
						        JOptionPane.showMessageDialog(jFrame, "Se eliminó la Estacion de Medicion: " + nombreEstacionAEliminar);
						        dispose();
								VentanaListaEM frame = new VentanaListaEM(usuarioLoged);
								frame.setVisible(true);
							} catch (NamingException | ServiciosException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
				}else {
					JOptionPane.showMessageDialog(null, "Seleccione la Estación a eliminar");
					dispose();
					VentanaListaEM frame = new VentanaListaEM(usuarioLoged);
					frame.setVisible(true);
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(25)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
						.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		
		JLabel lblNewLabel_1 = new JLabel("Filtros:");
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textNombreEM = new JTextField();
		textNombreEM.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Departamento:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		//Creo el combo con departamentos y lo lleno de elementos
		JComboBox comboDepartamentos = new JComboBox();
		GestionLocalidades gestionLocalidades = new GestionLocalidades();
		Set<String> departamentos = gestionLocalidades.obtieneDepartamentos();
		for (String nombre : departamentos) {
			comboDepartamentos.addItem(nombre);
		}
		comboDepartamentos.setSelectedIndex(-1);
		
		JLabel lblNewLabel_4 = new JLabel("Localidad:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		
		//Creo el Combo con las localidades y lo lleno usando el depto seleccionado
		JComboBox comboLocalidad = new JComboBox();
		String deptoSelec = "";
		if (comboDepartamentos.getSelectedIndex() != -1)
			deptoSelec = (String) comboDepartamentos.getSelectedItem();					
		Set<String> localidadesEnDepto = gestionLocalidades.obtieneLocalidades(deptoSelec);
		for (String l : localidadesEnDepto)
		{
			comboLocalidad.addItem(l);
		}
		comboDepartamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboLocalidad.removeAllItems();
				Set<String> localidades = gestionLocalidades.obtieneLocalidades((String) comboDepartamentos.getSelectedItem());
				for (String l : localidades) {
					comboLocalidad.addItem(l);
				}	
			}
		});
		
		JButton btnAplicaFiltro = new JButton("Aplicar");
		btnAplicaFiltro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAplicaFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAplicaFiltro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombreEM = "";
				if (textNombreEM != null)
					nombreEM = textNombreEM.getText();
				List<EstacionDeMedicion> listaEMNombre = new LinkedList<EstacionDeMedicion>();
				List<EstacionDeMedicion> listaEM = new LinkedList<EstacionDeMedicion>();
				try {
					listaEMNombre = gestionEstaciones.obtieneEmPorNombre(nombreEM);
					System.out.println("estaciones por nombre: " + listaEMNombre);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String localidad = (String) comboLocalidad.getSelectedItem();
				System.out.println("La localidad seleccionada es: " + localidad);
				for (EstacionDeMedicion em : listaEMNombre) {
					String localidadd = em.getLocalidad();
					System.out.println("Localidad en la lista: " + localidadd);
					if (localidadd.equals(localidad) || localidad == null ) {
						System.out.println("localidad de la em: " + em.getLocalidad());
						listaEM.add(em);
					}
				}
				System.out.println("lista de localidades a mostrar: " + listaEM);
				modelo= haceTablas.haceTablaEM(listaEM);
				
				//Creo la tabla y agrego el modelo
				table.setModel(modelo);
				
			}
		});
		
		JButton btnLimpiaFiltros = new JButton("Limpia");
		btnLimpiaFiltros.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLimpiaFiltros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboDepartamentos.setSelectedIndex(-1);
				textNombreEM.setText(null);
				//Creo el modelo de tabla con todas las estaciones
				HaceTablas haceTablas = new HaceTablas();
				modelo= haceTablas.haceTablaEM(estaciones);
				//Creo la tabla y agrego el modelo
				table.setModel(modelo);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnAplicaFiltro, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(btnLimpiaFiltros, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(47)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(comboDepartamentos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textNombreEM, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboLocalidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textNombreEM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addComponent(comboDepartamentos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addComponent(comboLocalidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnLimpiaFiltros, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAplicaFiltro, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
