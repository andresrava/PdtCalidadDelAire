package com.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.entities.Aficionado;
import com.entities.Usuario;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAficionado extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaAficionado() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAficionado frame = new VentanaAficionado(aficionadoLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Aficionado aficionadoLoged;

	/**
	 * Create the frame.
	 */
	public VentanaAficionado(Aficionado aficionadoLogedRef) {
		setTitle("Aficionado");
		VentanaAficionado.aficionadoLoged = aficionadoLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);		//Cambié esto para el fondo
		contentPane = new PaneImage();		//Cambié esto para el fomdo
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreDelAficionado = aficionadoLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Aficionado: " + nombreDelAficionado);
		
		JButton btnActividadCampo = new JButton("Actividad de Campo");
		btnActividadCampo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnActividadCampo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaActividadDeCampo ventanaActividadDeCampo = new VentanaActividadDeCampo((Usuario) aficionadoLoged);
				ventanaActividadDeCampo.ventanaActividadDeCampo();
				
			}
		});
		btnActividadCampo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnActividadCampo.setBackground(Color.WHITE);
		
		JButton btnGestinDeRegistros = new JButton("Gesti\u00F3n de Registros");
		btnGestinDeRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGestinDeRegistros.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGestinDeRegistros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaGestionRegistros ventanaGestionRegistros = new VentanaGestionRegistros((Usuario) aficionadoLoged);
				ventanaGestionRegistros.ventanaGestionRegistros();
			}
		});
		btnGestinDeRegistros.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGestinDeRegistros.setBackground(Color.WHITE);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Ingreso ingreso = new Ingreso();
				ingreso.setVisible(true);

			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(58)
					.addComponent(btnActividadCampo, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
					.addComponent(btnGestinDeRegistros, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(91))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addContainerGap(370, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(323)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(334, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(148)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnGestinDeRegistros, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnActividadCampo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
					.addGap(76)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(110, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		
	}
}
