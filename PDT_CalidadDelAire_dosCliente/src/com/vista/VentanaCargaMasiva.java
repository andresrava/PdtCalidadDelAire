package com.vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controlador.GestionFormularios;
import com.controlador.GestionIO;
import com.entities.Administrador;
import com.entities.Aficionado;
import com.entities.Formulario;
import com.entities.Investigador;
import com.entities.Usuario;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class VentanaCargaMasiva extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaCargaMasiva() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCargaMasiva frame = new VentanaCargaMasiva(usuarioLoged , f);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	private static JFileChooser f;
	GestionFormularios gestionFormularios = new GestionFormularios();
	List<Formulario> formularios = gestionFormularios.listaFormularios();
	
	/**
	 * Create the frame.
	 * @param f 
	 * @throws NamingException 
	 */
	public VentanaCargaMasiva(Usuario usuarioLogedRef, JFileChooser fRef) throws NamingException {
		setTitle("Carga Masiva");
		VentanaCargaMasiva.f = fRef;
		VentanaCargaMasiva.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);		//Cambié esto para el fondo
		contentPane = new PaneImage();		//Cambié esto para el fomdo
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		String nombreUsuario = usuarioLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreUsuario);
		JComboBox<Formulario> comboFormularios = new JComboBox();
		
		for ( Formulario form : formularios)
			comboFormularios.addItem(form);
		
		JLabel lblNewLabel_1 = new JLabel("Elija el formulario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
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
		
		JButton btnImportar = new JButton("Importar");
		btnImportar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnImportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Formulario form = (Formulario) comboFormularios.getSelectedItem();
				JOptionPane.showMessageDialog(null, "Obtuve el formulario seleccionado", "Atención!" , JOptionPane.WARNING_MESSAGE);
				System.out.println("Obtuve el formulario seleccionado");
				GestionIO gestionIO = new GestionIO();
				gestionIO.cargarRegistros(usuarioLoged , form , f);
			}
		});
		btnImportar.setEnabled(false);
		comboFormularios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboFormularios.getSelectedItem() != null)
					btnImportar.setEnabled(true);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(734, Short.MAX_VALUE)
					.addComponent(comboFormularios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 428, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addGap(159))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(320)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnVolver, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnImportar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
					.addContainerGap(317, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(27)
							.addComponent(comboFormularios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)))
					.addGap(214)
					.addComponent(btnImportar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(39)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(67))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
