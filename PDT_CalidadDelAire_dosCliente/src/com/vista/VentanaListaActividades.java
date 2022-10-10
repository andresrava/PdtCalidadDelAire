package com.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.controlador.GestionActividades;
import com.controlador.GestionIO;
import com.controlador.GestionUsuarios;
import com.entities.Actividad;
import com.entities.Aficionado;
import com.entities.Formulario;
import com.entities.Usuario;
import com.exceptions.ServiciosException;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaListaActividades extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void ventanaListaActividades() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaActividades frame = new VentanaListaActividades(usuarioLoged);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static Usuario usuarioLoged;
	private List<Actividad> actividades = new LinkedList<Actividad>();
	private List<Actividad> actividadesFiltradas = new LinkedList<Actividad>();
	DefaultTableModel modelo = new DefaultTableModel();
	JTable table = new JTable(modelo);
	JScrollPane scrollPane = new JScrollPane(table);
	JPanel panel = new JPanel();
	Usuario usuario;
	private List<Actividad> actividadesDelUsuario = new LinkedList<Actividad>();
	private List<Formulario> formularios = new LinkedList<>();
	private Set<Formulario> conjuntoFormularios = new HashSet<>();
	
	
	/**
	 * Create the frame.
	 */
	public VentanaListaActividades(Usuario usuarioLogedRef) {
		setTitle("Lista Actividades");
		VentanaListaActividades.usuarioLoged = usuarioLogedRef;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Agrego el fondo
		setBounds(100, 100, 800, 500);		
		contentPane = new PaneImage();		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String usuarioNombre = usuarioLoged.getNombre();
		JLabel lblNewLabel = new JLabel("Usuario: " + usuarioNombre);
		
		//Servicios
		HaceTablas haceTablas = new HaceTablas();
		GestionUsuarios gestionUsuarios = new GestionUsuarios();
		GestionActividades gestionActividades = new GestionActividades();
		
		JPanel panel_1 = new JPanel();
		
		
		//Lleno el combo con los Formularios correspondientes al usuario logueado
		
		JComboBox<Formulario> comboFormularios = new JComboBox<Formulario>();
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
		
		//Lleno la tabla con las actividades del usuario logueado
				
		try {
			usuario = gestionUsuarios.obtienePorId(idUsuario);
			actividades = gestionActividades.obtieneTodas();
			if (Aficionado.class.isInstance(usuario)) {
				for (Actividad a : actividades) {
					if (a.getUsuario().getId() == idUsuario) {
						actividadesDelUsuario.add(a);
					}
				}
				actividades = actividadesDelUsuario;
			}
			modelo = haceTablas.haceTablaActividades(actividades);
			//Agrego el modelo a la tabla

			table.setModel(modelo);
			panel.add(scrollPane);
			
		} catch (NamingException | ServiciosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//   ********** Botones ***********
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaActividadDeCampo ventanaActividadDeCampo = new VentanaActividadDeCampo(usuarioLoged);
				ventanaActividadDeCampo.ventanaActividadDeCampo();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Actividad actividad = new Actividad();
				int id = 0;
				if (table.getSelectedRow() != -1) {
					id = table.getSelectedRow();
					actividad = actividades.get(id);
					String idActividadAEliminar = actividad.getId().toString();
					int confirmacion =  JOptionPane.showConfirmDialog(null,"Realmente desea eliminar la Actividad de Campo con ID: " + idActividadAEliminar + " y todos sus registros?", "Confirmar la eliminación", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (confirmacion == 0)
						{
							try {
								gestionActividades.borraActividad(actividad);
								JFrame jFrame = new JFrame();
						        JOptionPane.showMessageDialog(jFrame, "Se eliminó la Actividad de Campo con ID: " + idActividadAEliminar);
						        actividades.remove(id);
						        modelo = haceTablas.haceTablaActividades(actividades);
								//Agrego el modelo a la tabla
						        table.setModel(modelo);
							} catch (NamingException | ServiciosException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
				}else {
					JOptionPane.showMessageDialog(null, "Seleccione la Estación a eliminar");
					dispose();
					VentanaListaEM frame = new VentanaListaEM(usuarioLoged);
					frame.setVisible(true);
				}	
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnAplica = new JButton("Aplicar");
		btnAplica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Formulario formularioElegido = new Formulario();
				formularioElegido = (Formulario) comboFormularios.getSelectedItem();
				actividadesFiltradas.clear();
				for (Actividad a : actividades) {
					if (a.getFormulario().getId() == formularioElegido.getId()) {
						actividadesFiltradas.add(a);
					}
				}
				 modelo = haceTablas.haceTablaActividades(actividadesFiltradas);
					//Agrego el modelo a la tabla

					table.setModel(modelo);
				
			}
		});
		btnAplica.setFont(new Font("Tahoma", Font.PLAIN, 16));
	

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modelo = haceTablas.haceTablaActividades(actividades);
				//Agrego el modelo a la tabla

				table.setModel(modelo);
			}
		});
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		JLabel lblFiltros = new JLabel("Formulario:");
		lblFiltros.setHorizontalAlignment(SwingConstants.LEFT);
		lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		panel.add(scrollPane);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(235, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnVolver, GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnEditar, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
					.addGap(30))
		);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblFiltros)
						.addComponent(comboFormularios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnAplica, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(lblFiltros)
					.addGap(4)
					.addComponent(comboFormularios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAplica)
						.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		contentPane.setLayout(gl_contentPane);
	}
}
