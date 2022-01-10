package com.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Investigador;
import com.entities.Usuario;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JButton btnCasillas = new JButton("Casillas");
		btnCasillas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		btnCargaMasiva.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCargaMasiva.setBackground(Color.WHITE);
		
		JButton btnActividadCampo = new JButton("Actividad de Campo");
		btnActividadCampo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				VentanaActividadDeCampo ventanaActividadDeCampo = null;
				ventanaActividadDeCampo = new VentanaActividadDeCampo((Usuario) investigadorLoged);
				ventanaActividadDeCampo.ventanaActividadDeCampo();
				
			}
		});
		btnActividadCampo.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnActividadCampo.setBackground(Color.WHITE);
		
		JButton btnGestionFormularios = new JButton("Gesti\u00F3n de Formularios");
		btnGestionFormularios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaFormularios ventanaFormularios = new VentanaFormularios((Usuario) investigadorLoged);
				ventanaFormularios.ventanaFormularios();
			}
		});
		btnGestionFormularios.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGestionFormularios.setBackground(Color.WHITE);
		
		JButton btnGestinDeEst = new JButton("Estaciones de Medici\u00F3n");
		btnGestinDeEst.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		
		JButton btnDescargaMasiva = new JButton("Descarga Masiva");
		btnDescargaMasiva.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDescargaMasiva.setBackground(Color.WHITE);
		
		JButton btnGestinDeRegistros = new JButton("Gesti\u00F3n de Registros");
		btnGestinDeRegistros.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnGestinDeRegistros.setBackground(Color.WHITE);
		
		String nombreDelInvestigador = investigadorLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Investigador: " + nombreDelInvestigador);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ingreso ingreso = new Ingreso();
				try {
					ingreso.main(null);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(266)
							.addComponent(btnGestionFormularios, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCasillas, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(134)
							.addComponent(btnGestinDeEst, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCargaMasiva, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(134)
							.addComponent(btnDescargaMasiva, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnActividadCampo, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnGestinDeRegistros, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addContainerGap(300, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(11)
					.addComponent(btnGestionFormularios, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCasillas, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGestinDeEst, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCargaMasiva, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDescargaMasiva, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnGestinDeRegistros, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton)
								.addComponent(btnActividadCampo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
