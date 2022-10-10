package com.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Investigador;
import com.entities.Usuario;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class VentanaInvestigador extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaInvestigador() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInvestigador frame = new VentanaInvestigador(investigadorLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Investigador investigadorLoged;
	/**
	 * Create the frame.
	 */
	public VentanaInvestigador(Investigador investigadorLogedRef) {
		VentanaInvestigador.investigadorLoged = investigadorLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);		//Cambié esto para el fondo
		contentPane = new PaneImage();		//Cambié esto para el fondo decía "content = newJPanel()"
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JButton btnCasillas = new JButton("Casillas");
		btnCasillas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCasillas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaCasillas ventanaCasillas = null;
				try {
					ventanaCasillas = new VentanaCasillas((Usuario) investigadorLoged);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ventanaCasillas.ventanaCasillas();
			}
		});
		btnCasillas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCasillas.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCasillas.setBackground(Color.WHITE);
		
		JButton btnCargaMasiva = new JButton("Carga Masiva");
		btnCargaMasiva.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCargaMasiva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser f = new JFileChooser("Seleccione el archivo");
			    f.setFileSelectionMode(JFileChooser.FILES_ONLY); 
			    f.showSaveDialog(null);
				dispose();
				VentanaCargaMasiva ventanaCargaMasiva;
				try {
					ventanaCargaMasiva = new VentanaCargaMasiva((Usuario) investigadorLoged , f );
					ventanaCargaMasiva.ventanaCargaMasiva();
					
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
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
				ventanaActividadDeCampo = new VentanaActividadDeCampo((Usuario) investigadorLoged);
				ventanaActividadDeCampo.ventanaActividadDeCampo();
				
			}
		});
		btnActividadCampo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnActividadCampo.setBackground(Color.WHITE);
		
		JButton btnGestionFormularios = new JButton("Gesti\u00F3n de Formularios");
		btnGestionFormularios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGestionFormularios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaFormularios ventanaFormularios = new VentanaFormularios((Usuario) investigadorLoged);
				ventanaFormularios.ventanaFormularios();
			}
		});
		btnGestionFormularios.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGestionFormularios.setBackground(Color.WHITE);
		
		JButton btnGestinDeEst = new JButton("Estaciones de Medici\u00F3n");
		btnGestinDeEst.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGestinDeEst.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaEMedicion ventanaEMedicion;
				try {
					ventanaEMedicion = new VentanaEMedicion((Usuario) investigadorLoged);
					ventanaEMedicion.ventanaEMedicion();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGestinDeEst.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGestinDeEst.setBackground(Color.WHITE);
		
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
				VentanaGestionRegistros ventanaGestionRegistros = new VentanaGestionRegistros((Usuario) investigadorLoged);
				ventanaGestionRegistros.ventanaGestionRegistros();
			}
		});
		btnGestinDeRegistros.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGestinDeRegistros.setBackground(Color.WHITE);
		
		String nombreDelInvestigador = investigadorLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Investigador: " + nombreDelInvestigador);
		
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(32)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnCargaMasiva, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnCasillas, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
										.addComponent(btnActividadCampo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(313)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
							.addGap(72)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnGestionFormularios, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
								.addComponent(btnGestinDeEst, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
								.addComponent(btnGestinDeRegistros, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
							.addGap(45)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(77)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCasillas, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
						.addComponent(btnGestionFormularios, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCargaMasiva, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGestinDeEst, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnActividadCampo, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnGestinDeRegistros, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
					.addGap(86))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
