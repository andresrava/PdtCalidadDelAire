package com.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Usuario;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

public class VentanaCreaFormulario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void VentanaCreaFormulario() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCreaFormulario frame = new VentanaCreaFormulario(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;

	/**
	 * Create the frame.
	 */
	public VentanaCreaFormulario(Usuario usuarioLogedRef) {
		VentanaCreaFormulario.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreDelUsuario = usuarioLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addContainerGap(376, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addContainerGap(237, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
