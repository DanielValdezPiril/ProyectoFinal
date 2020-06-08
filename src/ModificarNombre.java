import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class ModificarNombre extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public ModificarNombre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 444);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre"); // nombre que desea ingresar el usuario para la entidad
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(35, 60, 46, 14);
		contentPane.add(lblNewLabel);
		
		JTextField txtnombre = new JTextField();
		txtnombre.setBackground(new Color(95, 158, 160));
		txtnombre.setBounds(109, 57, 106, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		JList listaatributos = new JList();		// Se mostrara los atributos agregados a la entidad 
		listaatributos.setBackground(new Color(95, 158, 160));
		listaatributos.setBounds(267, 40, 272, 310);
		contentPane.add(listaatributos);
		
		
		JComboBox list = new JComboBox();		// Le indica al usuario que tipo de entidad desea Seleccionar 
		list.setBackground(new Color(32, 178, 170));
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String entidadSeleccionada = list.getSelectedItem().toString().split("-")[0];
				int idEntidad = Integer.parseInt(entidadSeleccionada);
				ArrayList<Atributo> atributos = Menu.atributos.listarAtributo();
				DefaultListModel listModel = new DefaultListModel();
				for(Atributo item : atributos) {
					if(item.getIdentidad() == idEntidad) {
						listModel.addElement(item.detalle());
					}					
				}
				listaatributos.setModel(listModel);
			}
		});
		list.setBounds(52, 11, 175, 21);
		contentPane.add(list);
		
		
		ArrayList<Entidad> entidades = Menu.entidades.listarEntidad();
		for(Entidad item : entidades) {
			list.addItem(item.getId()+"-"+item.getNombre());
		}
		
		JButton btncambiarNombre = new JButton("Cambiar Nombre");
		btncambiarNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		btncambiarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String idSeleccionada = list.getSelectedItem().toString().split("-")[0];
				String entidadSeleccionada = list.getSelectedItem().toString().split("-")[1];
				int id = Integer.parseInt(idSeleccionada);
				ManejoDatos datos = new ManejoDatos();
				if(!datos.existe(entidadSeleccionada.trim())) {
					try {
						Menu.entidades.cambiarNombre(id, txtnombre.getText());
						JOptionPane.showMessageDialog(null, "se modifico nombre");
						txtnombre.setText("");
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Error al tratar de cambiar el nombre ");
					}
				}else {
					JOptionPane.showMessageDialog(null, "No se puede cambiar nombre porque ya tiene datos");
				}
				
			}
		});
		btncambiarNombre.setBounds(52, 116, 152, 31);
		contentPane.add(btncambiarNombre);
		
		
	}
}