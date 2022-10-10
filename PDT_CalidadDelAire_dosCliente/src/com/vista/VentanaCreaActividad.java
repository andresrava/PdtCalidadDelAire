package com.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controlador.GestionFormularios;
import com.controlador.GestionUsuarios;
import com.entities.Formulario;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class VentanaCreaActividad extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaCreaActividad() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCreaActividad frame = new VentanaCreaActividad(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static Usuario usuarioLoged;
	private List<Formulario> formularios = new LinkedList<>();
	private Set<Formulario> conjuntoFormularios = new HashSet<>();
	
	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public VentanaCreaActividad(Usuario usuarioLogedRef) throws NamingException {
		setTitle("Nueva Actividad de Campo");
		VentanaCreaActividad.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);		
		contentPane = new PaneImage();		
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		
		GestionUsuarios gestionUsuarios = new GestionUsuarios();
		
		
		//Creo el Combo para elegir los formularios
		JComboBox<Formulario> comboFormularios = new JComboBox();
		
		//Lleno el combo con los Formularios correspondientes al usuario logueado
		
				Long idUsuario = usuarioLoged.getId();
				
				try {
					Usuario usuario = gestionUsuarios.obtienePorId(idUsuario);
					formularios = usuario.getFormularios();
					for (Formulario f : formularios) {
						conjuntoFormularios.add(f);
					}
					} catch (NamingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (ServiciosException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				comboFormularios.removeAllItems();
				
				for (Formulario f : conjuntoFormularios) {
					comboFormularios.addItem(f);
				}
		
	
		JLabel lblNewLabel_1 = new JLabel("Formularios disponibles");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (comboFormularios.getSelectedIndex() != -1) {
					Formulario formularioElegido = (Formulario) comboFormularios.getSelectedItem();
					dispose();
					VentanaLlenaFormulario ventanaLlenaFormulario = new VentanaLlenaFormulario(usuarioLoged , formularioElegido);
					ventanaLlenaFormulario.ventanaLlenaFormulario();
					}
				else {
					JOptionPane.showMessageDialog(null, "Seleccione un formulario");
				}
					
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaActividadDeCampo ventanaActividadDeCampo = new VentanaActividadDeCampo(usuarioLoged);
				ventanaActividadDeCampo.ventanaActividadDeCampo();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addContainerGap(674, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(90)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(83)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnAceptar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnVolver, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(comboFormularios, 0, 350, Short.MAX_VALUE)
					.addGap(66))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(58)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboFormularios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(227, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
