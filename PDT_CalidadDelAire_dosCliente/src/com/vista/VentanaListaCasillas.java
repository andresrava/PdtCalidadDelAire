package com.vista;

import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.controlador.GestionCasillas;
import com.controlador.GestionCiudades;
import com.controlador.GestionEstaciones;
import com.entities.Casilla;
import com.entities.Ciudad;
import com.entities.Ciudad.NombresEnum;
import com.entities.EstacionDeMedicion;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaListaCasillas extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaListaCasillas() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaCasillas frame = new VentanaListaCasillas(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	private JTextField textNombreCasilla;
	/**
	 * Create the frame.
	 * @throws NamingException 
	 */
	public VentanaListaCasillas(Usuario usuarioLogedRef) throws NamingException {
		setTitle("Lista Casillas");
		VentanaListaCasillas.usuarioLoged = usuarioLogedRef;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel lblNewLabel = new JLabel("Filtros: ");
		
		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		
		textNombreCasilla = new JTextField();
		textNombreCasilla.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Est. de Medici\u00F3n");
				
		JComboBox comboBoxCasillas = new JComboBox();
		scrollPane.setColumnHeaderView(comboBoxCasillas);
				
		GestionCasillas gestionCasillas = new GestionCasillas();
		List<Casilla> casillas = new LinkedList<Casilla>();
		System.out.println(casillas);
		try {
			casillas = gestionCasillas.listaCasillas();  
			System.out.println("size: " + casillas.size());
			System.out.println(casillas.toString());
			for (Casilla c: casillas) {
				comboBoxCasillas.addItem(c.toStringCorto());
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String nombreUsuario = usuarioLoged.getNombre();
		
		JLabel lblNewLabel_3 = new JLabel("Usuario: " + nombreUsuario);
		
		JButton btnAplicaFiltro = new JButton("Aplicar");
		btnAplicaFiltro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				List<Casilla> casillasFiltradasNombre = new LinkedList<Casilla>();
				GestionCasillas gestionCasillas = new GestionCasillas();
				try {
					casillasFiltradasNombre = gestionCasillas.listaCasillas(textNombreCasilla.getText());
					comboBoxCasillas.removeAllItems();
					for (Casilla c: casillasFiltradasNombre) {
						comboBoxCasillas.addItem(c.toStringCorto());
					}
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				List<Casilla> casillasEnEM = new LinkedList<Casilla>();
//				EstacionDeMedicion estacion = new EstacionDeMedicion();
//				
//				casillasEnEM = estacion.getCasillas();
//				Set<Casilla> casillas = new HashSet<Casilla>();
//				comboBoxCasillas.removeAllItems();
//				for (Casilla c : casillas) {
//					comboBoxCasillas.addItem(c.toStringCorto());
//				}
				
			}
		});
		
		
		
		JComboBox comboEM = new JComboBox();
		List<EstacionDeMedicion> estacionesDeMedicion = new LinkedList<EstacionDeMedicion>();
//		GestionEstaciones gestionEstaciones = new GestionEstaciones();
//		estacionesDeMedicion = gestionEstaciones.obtieneTodas();
		
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
							.addComponent(comboEM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
							.addComponent(textNombreCasilla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAplicaFiltro, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel)
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textNombreCasilla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(comboEM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(108)
					.addComponent(btnAplicaFiltro, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(22))
		);
		panel.setLayout(gl_panel);
		
		JButton btnEditar = new JButton("Editar");
		
		JButton btnEliminar = new JButton("Eliminar");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addGap(32)
									.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel_3)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
					.addGap(30))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
