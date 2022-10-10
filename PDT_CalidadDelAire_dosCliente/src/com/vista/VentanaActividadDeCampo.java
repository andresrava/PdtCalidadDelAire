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
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class VentanaActividadDeCampo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaActividadDeCampo() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaActividadDeCampo frame = new VentanaActividadDeCampo(usuarioLoged);
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
	public VentanaActividadDeCampo(Usuario usuarioLogedRef) {
		setTitle("Actividad de Campo");
		VentanaActividadDeCampo.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Agrego el fondo
		setBounds(100, 100, 800, 500);		
		contentPane = new PaneImage();		
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String usuarioNombre = usuarioLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + usuarioNombre);
		
		JButton btnNoevaActividadDeCampo = new JButton("Nueva");
		btnNoevaActividadDeCampo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNoevaActividadDeCampo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaCreaActividad ventanaCreaActividad;
				try {
					ventanaCreaActividad = new VentanaCreaActividad(usuarioLoged);
					ventanaCreaActividad.ventanaCreaActividad();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnListaActividadDeCampo = new JButton("Lista");
		btnListaActividadDeCampo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnListaActividadDeCampo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaListaActividades ventanaListaActividades = new VentanaListaActividades(usuarioLoged);
				ventanaListaActividades.ventanaListaActividades();
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
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(149)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnVolver, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnListaActividadDeCampo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNoevaActividadDeCampo, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(328, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(btnNoevaActividadDeCampo, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnListaActividadDeCampo, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(241, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
