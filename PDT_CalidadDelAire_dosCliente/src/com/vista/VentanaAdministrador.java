package com.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Administrador;
import com.entities.Usuario;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;


public class VentanaAdministrador extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaAdministrador() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdministrador frame = new VentanaAdministrador(administradorLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static Administrador administradorLoged;

	/**
	 * Create the frame.
	 */
	public VentanaAdministrador(Administrador ObjectToPassRef) {
		VentanaAdministrador.administradorLoged = ObjectToPassRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new PaneImage();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		String nombreDelAdministrador = administradorLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Administrador: " + nombreDelAdministrador);
		
		JButton btnGestionUsuarios = new JButton("Gesti\u00F3n de Usuarios");
		btnGestionUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGestionUsuarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					dispose();
					VentanaUsuarios ventanaUsuarios = new VentanaUsuarios ((Usuario) administradorLoged, null);
					ventanaUsuarios.VentanaUsuarios((Usuario) administradorLoged, null);
					} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGestionUsuarios.setBackground(Color.WHITE);
		btnGestionUsuarios.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JButton btnGestionFormularios = new JButton("Gesti\u00F3n de Formularios");
		btnGestionFormularios.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGestionFormularios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnGestionFormularios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaFormularios ventanaFormularios = new VentanaFormularios((Usuario) administradorLoged);
				ventanaFormularios.ventanaFormularios();
				dispose();
			}
		});
		btnGestionFormularios.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGestionFormularios.setBackground(Color.WHITE);
		
		JButton btnGestinDeRegistros = new JButton("Gesti\u00F3n de Registros");
		btnGestinDeRegistros.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGestinDeRegistros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaGestionRegistros ventanaGestionRegistros = new VentanaGestionRegistros((Usuario) administradorLoged);
				ventanaGestionRegistros.ventanaGestionRegistros();
			}
		});
		btnGestinDeRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestinDeRegistros.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGestinDeRegistros.setBackground(Color.WHITE);
		
		JButton btnGestinDeEst = new JButton("Estaciones de Medici\u00F3n");
		btnGestinDeEst.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGestinDeEst.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaEMedicion ventanaEMedicion;
				try {
					ventanaEMedicion = new VentanaEMedicion((Usuario) administradorLoged);
					ventanaEMedicion.ventanaEMedicion();
					dispose();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
		});
		btnGestinDeEst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnGestinDeEst.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGestinDeEst.setBackground(Color.WHITE);
		
		JButton btnCargaMasiva = new JButton("Carga Masiva");
		btnCargaMasiva.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCargaMasiva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				JOptionPane.showMessageDialog(null, "Funcionalidad no implementada", "Atención!" , JOptionPane.WARNING_MESSAGE);
				JFileChooser f = new JFileChooser("Seleccione el archivo");
			    f.setFileSelectionMode(JFileChooser.FILES_ONLY); 
			    f.showSaveDialog(null);
				dispose();
				VentanaCargaMasiva ventanaCargaMasiva;
				try {
					ventanaCargaMasiva = new VentanaCargaMasiva((Usuario) administradorLoged , f );
					ventanaCargaMasiva.ventanaCargaMasiva();
					
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
		});
		btnCargaMasiva.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCargaMasiva.setBackground(Color.WHITE);
		
		JButton btnActividadCampo = new JButton("Actividad de Campo");
		btnActividadCampo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnActividadCampo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaActividadDeCampo ventanaActividadDeCampo = null;
				ventanaActividadDeCampo = new VentanaActividadDeCampo((Usuario) administradorLoged);
				ventanaActividadDeCampo.ventanaActividadDeCampo();
				
			}
		});
		btnActividadCampo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnActividadCampo.setBackground(Color.WHITE);
		
		JButton btnCasillas = new JButton("Casillas");
		btnCasillas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCasillas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaCasillas ventanaCasillas = null;
				try {
					ventanaCasillas = new VentanaCasillas((Usuario) administradorLoged);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ventanaCasillas.ventanaCasillas();
			}
		});
		btnCasillas.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCasillas.setBackground(Color.WHITE);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Ingreso ingreso = new Ingreso();
				ingreso.setVisible(true);
//				try {
//					ingreso.main(null);
//				} catch (NamingException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(117)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnGestionUsuarios, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
								.addComponent(btnCargaMasiva, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
								.addComponent(btnCasillas, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))))
					.addGap(182)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnGestinDeRegistros, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnGestinDeEst, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnGestionFormularios, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(136))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(241)
					.addComponent(btnActividadCampo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(283, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(330)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(352, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGestionFormularios, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGestionUsuarios, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGestinDeEst, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCasillas, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCargaMasiva, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGestinDeRegistros, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnActividadCampo, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(95))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
}
