package com.vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.controlador.GestionFormularios;
import com.controlador.GestionIO;
import com.controlador.GestionRegistros;
import com.controlador.GestionUsuarios;
import com.entities.Formulario;
import com.entities.Registro;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;

public class VentanaListaRegistroFormulario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaListaRegistroFormulario() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaRegistroFormulario frame = new VentanaListaRegistroFormulario(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	List<Registro> registros = new LinkedList<Registro>();
	DefaultTableModel modelo = new DefaultTableModel();
	JTable table = new JTable(modelo);
	JScrollPane scrollPane = new JScrollPane(table);
	JPanel panel = new JPanel();
	private List<Formulario> formularios = new LinkedList<>();
	private Set<Formulario> conjuntoFormularios = new HashSet<>();
	
	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public VentanaListaRegistroFormulario(Usuario usuarioLogedRef) throws NamingException {
		
				//****Servicios****
		GestionFormularios gestionFormularios = new GestionFormularios();
		GestionRegistros gestionRegistros = new GestionRegistros();
		GestionIO gestionIO = new GestionIO();
		HaceTablas haceTablas = new HaceTablas();
		GestionUsuarios gestionUsuarios = new GestionUsuarios();
		
		
				//****Etiquetas****
		setTitle("Lista Registros por Formulario");
		JLabel lblNewLabel_1 = new JLabel("Seleccione el formulario");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		VentanaListaRegistroFormulario.usuarioLoged = usuarioLogedRef;
		String nombreUsuario = usuarioLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + nombreUsuario);
		
		
		
		
		
				//****Ventana****
		//Agrego el fondo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);	
		contentPane = new PaneImage();		
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//Agrego el modelo a la tabla
		table.setModel(modelo);
		panel.add(scrollPane);
		
		//Creo el Combo para elegir los formularios
		JComboBox<Formulario> comboFormularios = new JComboBox();
		comboFormularios.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//Lleno el combo con los Formularios correspondientes al usuario logueado
		
		Long idUsuario = usuarioLoged.getId();
		
		try {
			Usuario usuario = gestionUsuarios.obtienePorId(idUsuario);
			formularios = usuario.getFormularios();
			for (Formulario f : formularios) {
				conjuntoFormularios.add(f);
			}
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ServiciosException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		comboFormularios.removeAllItems();
		
		for (Formulario f : conjuntoFormularios) {
			comboFormularios.addItem(f);
		}
				//****Botones****
		//Exportar
		JButton btnExportar = new JButton("Exportar");
		btnExportar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
				f.showSaveDialog(null);
				System.out.println("La cancel option fue: " + f.CANCEL_OPTION);
				if (f.getSelectedFile() != null) {
					boolean confirma = false;
					confirma = gestionIO.descargaRegistros(registros , f);
				    if (confirma)
						JOptionPane.showMessageDialog(null, "Se descargó el archivo", "Atención!" , JOptionPane.WARNING_MESSAGE);
				}
			    
			}
		});
		btnExportar.setVisible(false);
		
		//Buscar
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Formulario formulario = (Formulario) comboFormularios.getSelectedItem();
					Long idFormulario = formulario.getId();
					registros = gestionRegistros.muestraRegistrosPorFormulario(idFormulario);
					modelo = haceTablas.haceTablaRegistros(registros);
					table.setModel(modelo);
					if (registros.size() == 0)
						btnExportar.setVisible(false);
					else
						btnExportar.setVisible(true);
					} catch (NamingException e1) {
					e1.printStackTrace();
					}
				
				
				
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaGestionRegistros ventanaGestionRegistros = new VentanaGestionRegistros(usuarioLoged);
				ventanaGestionRegistros.ventanaGestionRegistros();
			}
		});
		
		
		
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addComponent(comboFormularios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_1)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnExportar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnBuscar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(102)
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(377, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(50))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboFormularios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
					.addComponent(btnExportar, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(102))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
					.addGap(61)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(36))
		);
		

		
		
		
		contentPane.setLayout(gl_contentPane);
	}
}
