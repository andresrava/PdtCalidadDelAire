package com.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Administrador;
import com.entities.Aficionado;
import com.entities.Investigador;
import com.entities.Usuario;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaGestionRegistros extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaGestionRegistros() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGestionRegistros frame = new VentanaGestionRegistros(usuarioLoged);
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
	public VentanaGestionRegistros(Usuario usuarioLogedRef) {
		setTitle("Gesti\u00F3n de Registros");
		VentanaGestionRegistros.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);		//Cambié esto para el fondo
		contentPane = new PaneImage();		//Cambié esto para el fondo decía "content = newJPanel()"
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreUsuario = usuarioLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreUsuario);
		
		JButton btnListaPorFormulario = new JButton("Lista por Formulario");
		btnListaPorFormulario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnListaPorFormulario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaListaRegistroFormulario ventanaListaRegistroFormulario;
				try {
					ventanaListaRegistroFormulario = new VentanaListaRegistroFormulario(usuarioLoged);
					ventanaListaRegistroFormulario.ventanaListaRegistroFormulario();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnListaPorFechas = new JButton("Lista por fechas");
		btnListaPorFechas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnListaPorFechas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaListaRegistrosFecha ventanaListaRegistrosFecha = new VentanaListaRegistrosFecha(usuarioLoged);
				ventanaListaRegistrosFecha.ventanaListaRegistrosFecha();
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if (usuarioLoged instanceof Administrador) {
					VentanaAdministrador ventanaAdministrador = new VentanaAdministrador((Administrador) usuarioLoged);
					ventanaAdministrador.ventanaAdministrador();
				}
				if (usuarioLoged instanceof Investigador) {
					VentanaInvestigador ventanaInvestigador = new VentanaInvestigador((Investigador) usuarioLoged);
					ventanaInvestigador.ventanaInvestigador();
				}
				if (usuarioLoged instanceof Aficionado) {
					VentanaAficionado ventanaAficionado = new VentanaAficionado((Aficionado) usuarioLoged);
					ventanaAficionado.ventanaAficionado();
				}
				
			}
		});
		
		JButton btnListaPorCasilla = new JButton("Lista por par\u00E1metro");
		btnListaPorCasilla.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnListaPorCasilla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaListaRegistrosCasilla ventanaListaRegistrosCasilla;
				try {
					ventanaListaRegistrosCasilla = new VentanaListaRegistrosCasilla(usuarioLoged);
					ventanaListaRegistrosCasilla.ventanaListaRegistrosCasilla();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnListaPorFechas, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
								.addComponent(btnListaPorFormulario, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
								.addComponent(btnListaPorCasilla, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE))))
					.addGap(52))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(211)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(221, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(26)
					.addComponent(btnListaPorFormulario, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(btnListaPorFechas, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(btnListaPorCasilla, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(61)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(94, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
