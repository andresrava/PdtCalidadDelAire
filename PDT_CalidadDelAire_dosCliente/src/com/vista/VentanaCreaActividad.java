package com.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.controlador.GestionFormularios;
import com.entities.Formulario;
import com.entities.Usuario;

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
	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public VentanaCreaActividad(Usuario usuarioLogedRef) throws NamingException {
		setTitle("Nueva Actividad de Campo");
		VentanaCreaActividad.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String nombreDelUsuario = usuarioLogedRef.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreDelUsuario);
		
		//Acá falta implementar relación NaN Formulario-Usuario
		//Se va a mostrar todos los formularios en principio,
		//Luego se mostrará sólo los formularios asociados al usuarioLoged
		JComboBox<Formulario> comboFormulariosDisponibles = new JComboBox();
		GestionFormularios gestionFormularios = new GestionFormularios();
		List<Formulario> formulariosDisponibles = gestionFormularios.listaFormularios();
		for (Formulario f : formulariosDisponibles) {
			comboFormulariosDisponibles.addItem(f);
		}
		
		JLabel lblNewLabel_1 = new JLabel("Formularios disponibles");
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Funcionalidad aún no implementada");
				
//				Formulario formularioElegido = (Formulario) comboFormulariosDisponibles.getSelectedItem();
//				dispose();
//				VentanaYenaFormulario ventanaLlenaFormulario = new VentanaYenaFormulario(usuarioLoged , formularioElegido);
//				ventanaLlenaFormulario.ventanaYenaFormulario();
			}
		});
		
		JButton btnVolver = new JButton("Volver");
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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnVolver, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAceptar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.LEADING))
					.addPreferredGap(ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(comboFormulariosDisponibles, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, Alignment.TRAILING)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(lblNewLabel_1))
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboFormulariosDisponibles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(107, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
