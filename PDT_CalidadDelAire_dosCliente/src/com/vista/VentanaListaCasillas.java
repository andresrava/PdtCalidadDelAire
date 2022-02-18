package com.vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import com.controlador.GestionCasillas;
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
import javax.swing.JCheckBox;

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
		contentPane.setBackground(new Color(255, 228, 225));
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
				
		JComboBox<Casilla> comboBoxCasillas = new JComboBox();
		scrollPane.setColumnHeaderView(comboBoxCasillas);
		
		JComboBox<EstacionDeMedicion> comboEM = new JComboBox();
		comboEM.setSize( 350, 22 );
		List<EstacionDeMedicion> estacionesDeMedicion = new LinkedList<EstacionDeMedicion>();
		GestionEstaciones gestionEstaciones = new GestionEstaciones();

		try { 
			estacionesDeMedicion = gestionEstaciones.obtieneEM();
			for (EstacionDeMedicion EM: estacionesDeMedicion) {
				comboEM.addItem(EM); 
			} 
			comboEM.setSelectedIndex(-1);
		} catch (NamingException e) { // TODO Auto-generated catch block 
			e.printStackTrace();
		}
		  
		GestionCasillas gestionCasillas = new GestionCasillas();
		List<Casilla> casillas = new LinkedList<Casilla>();
		System.out.println(casillas);
		try {
			casillas = gestionCasillas.listaCasillas();  
			System.out.println("size: " + casillas.size());
			System.out.println(casillas.toString());
			for (Casilla c: casillas) {
				comboBoxCasillas.addItem(c);
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
				JOptionPane.showMessageDialog(null, "No se ha implementado esta funcionalidad","Error", JOptionPane.WARNING_MESSAGE);
				
				
//				List<Casilla> casillasFiltradasNombre = new LinkedList<Casilla>();
//				List<Casilla> casillasFiltradas = new LinkedList<Casilla>();
//				GestionCasillas gestionCasillas = new GestionCasillas();
//				try {
//					casillasFiltradasNombre = gestionCasillas.listaCasillas(textNombreCasilla.getText());
//					comboBoxCasillas.removeAllItems();
//					for (Casilla c: casillasFiltradasNombre) {
//						comboBoxCasillas.addItem(c);
//					}
//					if (comboEM.getSelectedIndex() != -1  )
//					{
//						EstacionDeMedicion estacionSeleccionada = (EstacionDeMedicion) comboEM.getSelectedItem();
//						List<Casilla> casillasEnEM = estacionSeleccionada.getCasillas();
//						for (Casilla c : casillasFiltradasNombre)
//						{
//							if (casillasEnEM.contains(c))
//							{
//								casillasFiltradas.add(c);
//							}
//						}
//						comboBoxCasillas.removeAllItems();
//						for (Casilla c : casillasFiltradas)
//						{
//							comboBoxCasillas.addItem(c);
//						}
//						comboBoxCasillas.updateUI();
//					}
//				} catch (NamingException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
			}
		});
		
		
		
		
		 
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(65)
							.addComponent(comboEM, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
							.addComponent(textNombreCasilla, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(29))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(89)
					.addComponent(lblNewLabel))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAplicaFiltro, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(262, Short.MAX_VALUE))
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
					.addGap(72)
					.addComponent(btnAplicaFiltro, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(58))
		);
		panel.setLayout(gl_panel);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Esa funcionalidad no está implementada");	
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Casilla casillaAEliminar = (Casilla)comboBoxCasillas.getSelectedItem();
				GestionCasillas gestionCasillas = new GestionCasillas();
				String nombreCasillaAEliminar = casillaAEliminar.getNombre();	
				int confirmacion =  JOptionPane.showConfirmDialog(null,"Realmente desea Eliminar la casilla: " + nombreCasillaAEliminar + "?", "Confirmar la eliminación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (confirmacion == 0)
					{
						try {
							gestionCasillas.borrarCasilla(casillaAEliminar);
							comboBoxCasillas.removeItem(casillaAEliminar);
					        comboBoxCasillas.updateUI();
					        JFrame jFrame = new JFrame();
					        JOptionPane.showMessageDialog(jFrame, "Se eliminó la casilla: " + nombreCasillaAEliminar);
					        
						} catch (NamingException | ServiciosException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				try {
					VentanaCasillas ventanaCasillas = new VentanaCasillas(usuarioLoged);
					ventanaCasillas.ventanaCasillas();
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel_3)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(143)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(101)
					.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel_3)
					.addGap(11)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnVolver, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
