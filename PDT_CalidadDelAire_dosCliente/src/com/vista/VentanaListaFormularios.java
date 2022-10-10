package com.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.controlador.GestionCasillas;
import com.controlador.GestionFormularios;
import com.entities.Casilla;
import com.entities.Formulario;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.awt.Font;

public class VentanaListaFormularios extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaListaFormularios() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaFormularios frame = new VentanaListaFormularios(usuarioLoged, formularios);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static Usuario usuarioLoged;
	private static List<Formulario> formularios;
	private List<Formulario> formulariosEnLista;
	private JTextField textNombre;
	DefaultTableModel modelo = new DefaultTableModel();
	JTable table = new JTable(modelo);
	JScrollPane scrollPane = new JScrollPane(table);
	JPanel panel_1 = new JPanel();
	
	
	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public VentanaListaFormularios(Usuario usuarioLogedRef, List<Formulario> formulariosRef) throws NamingException {
		setTitle("Lista Formularios");
		VentanaListaFormularios.usuarioLoged = usuarioLogedRef;
		VentanaListaFormularios.formularios = formulariosRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		//Agrego el fondo
		contentPane = new PaneImage();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		//Creo el modelo de tabla con todos los Formularios
		GestionFormularios gestionFormularios = new GestionFormularios();
		formulariosEnLista = gestionFormularios.listaFormularios();
		HaceTablas haceTablas = new HaceTablas();
		if (formularios != null)
			formulariosEnLista=formularios;
		modelo = haceTablas.haceTablaFormularios(formulariosEnLista);
		//Agrego el modelo a la tabla
		table.setModel(modelo);
		panel_1.add(scrollPane);

				//****Etiquetas****
		JLabel lblNewLabel_1 = new JLabel("Filtros:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		
		JLabel lblNewLabel_3 = new JLabel("Casilla:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		
		
		//Creo y lleno el combo con casillas disponibles
		JComboBox comboCasillasDisponibles = new JComboBox();
		GestionCasillas gestionCasillas = new GestionCasillas();
		List<Casilla> casillas = new LinkedList<Casilla>();
		System.out.println(casillas);
		try {
			casillas = gestionCasillas.listaCasillas();  
			for (Casilla c: casillas) {
				comboCasillasDisponibles.addItem(c);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboCasillasDisponibles.setSelectedIndex(-1);
		
		
				//****Botones****
		JButton btnAplicarFiltro = new JButton("Aplicar");
		btnAplicarFiltro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAplicarFiltro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombreFormulario = "";
				Long idCasilla = (long) 0;
				if (textNombre != null) {
					nombreFormulario = textNombre.getText();
					try {
						formulariosEnLista = gestionFormularios.listaFormularios(nombreFormulario);
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (comboCasillasDisponibles.getSelectedItem() != null) {
					Casilla casilla = (Casilla) comboCasillasDisponibles.getSelectedItem();
					List<Formulario> formulariosFiltrados = new LinkedList<Formulario>();
					for (Formulario f : formulariosEnLista) {
						List <Casilla> casillasEnFormulario = f.getCasillas();
						System.out.println("Las casillas son: " + casillasEnFormulario);
						boolean bandera = false;
						for (Casilla c : casillasEnFormulario) {
							if (c.getId() == casilla.getId()) {
								bandera = true;
							}
						}
						if (bandera)
							formulariosFiltrados.add(f);
					}
					formulariosEnLista = formulariosFiltrados; 
				}
				modelo = haceTablas.haceTablaFormularios(formulariosEnLista);
				//Agrego el modelo a la tabla
				table.setModel(modelo);
				panel_1.add(scrollPane);
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaFormularios ventanaFormularios = new VentanaFormularios(usuarioLoged);
				ventanaFormularios.ventanaFormularios();
			}
		});
		
		JButton btnEditarFormulario = new JButton("Editar");
		btnEditarFormulario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditarFormulario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditarFormulario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				Formulario formulario = new Formulario();
				try {
					int id = 0;
					if (table.getSelectedRow() != -1) {
						id = table.getSelectedRow();
						formulario = formulariosEnLista.get(id);
						dispose();
						VentanaEditaFormulario ventanaEditaFormulario = new VentanaEditaFormulario(usuarioLoged , formulario );
						ventanaEditaFormulario.ventanaEditaFormulario();
					}else {
						JOptionPane.showMessageDialog(null, "Seleccione el Formulario a editar");
					}
					
					
				} catch (NamingException e1) {
					e1.printStackTrace();
				}		
			}
		});

		JButton btnAsignaUsuario = new JButton("Asignar a usuario");
		btnAsignaUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Formulario formulario = new Formulario();
				int id = 0;
				if (table.getSelectedRow() != -1) {
					
					id = table.getSelectedRow();
					formulario = formulariosEnLista.get(id);
					VentanaAsignarUsuario ventanaAsignarUsuario = new VentanaAsignarUsuario(usuarioLoged, formulario);
					ventanaAsignarUsuario.ventanaAsignaUsuario();
					dispose();

							
						}
				else {
					JOptionPane.showMessageDialog(null, "Seleccione un Formulario");
				}
			}

		});
		btnAsignaUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Formulario formularioAEliminar = new Formulario();
				int id = 0;
				if (table.getSelectedRow() != -1) {
					id = table.getSelectedRow();
					formularioAEliminar = formulariosEnLista.get(id);
					String nombreFormularioAEliminar = formularioAEliminar.getNombre();
					int confirmacion =  JOptionPane.showConfirmDialog(null,"Realmente desea eliminar el Formulario: " + nombreFormularioAEliminar + "?", "Confirmar la eliminación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (confirmacion == 0)
						{
						
							try {
								gestionFormularios.borrarFormulario(formularioAEliminar);
								JFrame jFrame = new JFrame();
						        JOptionPane.showMessageDialog(jFrame, "Se eliminó el Formulario: " + nombreFormularioAEliminar);
						        dispose();
								VentanaListaFormularios frame = new VentanaListaFormularios(usuarioLoged, null);
								frame.setVisible(true);
							} catch (NamingException | ServiciosException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
				}
				
			}
		});
		
		JButton btnLimpiaFiltros = new JButton("Limpia");
		btnLimpiaFiltros.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLimpiaFiltros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboCasillasDisponibles.setSelectedIndex(-1);
				textNombre.setText(null);
				try {
					formulariosEnLista = gestionFormularios.listaFormularios();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				modelo = haceTablas.haceTablaFormularios(formulariosEnLista);
				//Agrego el modelo a la tabla
				table.setModel(modelo);
				panel_1.add(scrollPane);
			}
		});
		
		JButton btnBuscaPorUsuario = new JButton("Por Usuario");
		btnBuscaPorUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaSeleccionarUsuario ventanaSeleccionarUsuario = new VentanaSeleccionarUsuario(usuarioLoged);
				ventanaSeleccionarUsuario.ventanaSeleccionarUsuario();
			}
		});
		btnBuscaPorUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(135)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3))
							.addGap(18)
							.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(comboCasillasDisponibles, 0, 250, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAplicarFiltro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLimpiaFiltros))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnBuscaPorUsuario, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnLimpiaFiltros, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAplicarFiltro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(27)
					.addComponent(btnBuscaPorUsuario, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(183))
		);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(38)
									.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEditarFormulario, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAsignaUsuario)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(175)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnVolver, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnEditarFormulario, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnAsignaUsuario, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)))
					.addGap(40))
		);
		panel.setLayout(gl_panel);
		
		contentPane.setLayout(gl_contentPane);
	}
}
