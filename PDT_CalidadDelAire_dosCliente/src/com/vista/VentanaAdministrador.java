package com.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Administrador;
import com.entities.Usuario;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		String nombreDelAdministrador = administradorLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Administrador: " + nombreDelAdministrador);
		
		JButton btnGestionUsuarios = new JButton("Gesti\u00F3n de Usuarios");
		btnGestionUsuarios.setBackground(Color.WHITE);
		btnGestionUsuarios.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JButton btnGestionFormularios = new JButton("Gesti\u00F3n de Formularios");
		btnGestionFormularios.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGestionFormularios.setBackground(Color.WHITE);
		
		JButton btnGestinDeRegistros = new JButton("Gesti\u00F3n de Registros");
		btnGestinDeRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestinDeRegistros.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGestinDeRegistros.setBackground(Color.WHITE);
		
		JButton btnGestinDeEst = new JButton("Estaciones de Medici\u00F3n");
		btnGestinDeEst.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGestinDeEst.setBackground(Color.WHITE);
		
		JButton btnDescargaMasiva = new JButton("Descarga Masiva");
		btnDescargaMasiva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDescargaMasiva.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDescargaMasiva.setBackground(Color.WHITE);
		
		JButton btnCargaMasiva = new JButton("Carga Masiva");
		btnCargaMasiva.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCargaMasiva.setBackground(Color.WHITE);
		
		JButton btnActividadCampo = new JButton("Actividad de Campo");
		btnActividadCampo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnActividadCampo.setBackground(Color.WHITE);
		
		JButton btnCasillas = new JButton("Casillas");
		btnCasillas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaCasillas ventanaCasillas = new VentanaCasillas((Usuario) administradorLoged);
				ventanaCasillas.ventanaCasillas();
			}
		});
		btnCasillas.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCasillas.setBackground(Color.WHITE);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btnGestionUsuarios, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(btnCasillas, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
							.addComponent(btnActividadCampo, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnCargaMasiva, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
					.addGap(134)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGestionFormularios, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGestinDeRegistros, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGestinDeEst, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDescargaMasiva, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
					.addGap(16))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnGestionFormularios, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnGestinDeEst, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnDescargaMasiva, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnGestionUsuarios, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(btnCasillas, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCargaMasiva, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnActividadCampo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGestinDeRegistros, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
