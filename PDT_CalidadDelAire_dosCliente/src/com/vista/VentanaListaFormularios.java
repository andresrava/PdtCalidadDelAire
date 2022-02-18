package com.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class VentanaListaFormularios extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaListaFormularios() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaFormularios frame = new VentanaListaFormularios(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static Usuario usuarioLoged;
	private JTextField textNombre;
	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public VentanaListaFormularios(Usuario usuarioLogedRef) throws NamingException {
		setTitle("Lista Formularios");
		VentanaListaFormularios.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaFormularios ventanaFormularios = new VentanaFormularios(usuarioLoged);
				ventanaFormularios.ventanaFormularios();
			}
		});
		
		// Creo el Combo de Formularios y lo lleno
		JComboBox<Formulario> comboFormularios = new JComboBox();
		scrollPane.setColumnHeaderView(comboFormularios);
		GestionFormularios gestionFormularios = new GestionFormularios();
		List<Formulario> formularios = gestionFormularios.listaFormularios();
		for (Formulario f : formularios) {
			comboFormularios.addItem(f);
			
		}
		comboFormularios.updateUI();
		
		JButton btnEditarFormulario = new JButton("Editar");
		btnEditarFormulario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Formulario formularioAEditar = (Formulario) comboFormularios.getSelectedItem();
				
				try {
					VentanaEditaFormulario ventanaEditaFormulario = new VentanaEditaFormulario(usuarioLoged , formularioAEditar);
					ventanaEditaFormulario.ventanaEditaFormulario();
				
				} catch (NamingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Formulario formularioAEliminar = (Formulario)comboFormularios.getSelectedItem();
				GestionFormularios gestionFormularios = new GestionFormularios();
				String nombreFormularioAEliminar = formularioAEliminar.getNombre();	
				int confirmacion =  JOptionPane.showConfirmDialog(null,"Realmente desea eliminar el formulario: " + nombreFormularioAEliminar + "?", "Confirmar la eliminaci�n", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (confirmacion == 0)
					{
						try {
							gestionFormularios.borrarFormulario(formularioAEliminar);
							comboFormularios.removeItem(formularioAEliminar);
					        comboFormularios.updateUI();
					        JFrame jFrame = new JFrame();
					        JOptionPane.showMessageDialog(jFrame, "Se elimin� el formulario: " + nombreFormularioAEliminar);
					        
						} catch (NamingException | ServiciosException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}	
			}
		});
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
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(33)
									.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnEditarFormulario, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
						.addComponent(btnEditarFormulario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
						.addComponent(btnVolver, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblNewLabel_1 = new JLabel("Filtros:");
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Casilla:");
		
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
		
		
		JButton btnAplicarFiltro = new JButton("Aplicar");
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
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(comboCasillasDisponibles, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(85, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAplicarFiltro)
					.addContainerGap(245, Short.MAX_VALUE))
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
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(comboCasillasDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addComponent(btnAplicarFiltro)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		contentPane.setLayout(gl_contentPane);
	}
}
