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

public class IngresarDatos extends JFrame {

	private JPanel contentPane;
	private JTextField txtlongitud;

	

	/**
	 * Create the frame.
	 */
	public IngresarDatos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(35, 60, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(35, 108, 46, 21);
		contentPane.add(lblTipo);
		
		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setBounds(35, 161, 46, 14);
		contentPane.add(lblLongitud);
		
		JTextField txtnombre = new JTextField();
		txtnombre.setBounds(90, 57, 86, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		JComboBox txttipo = new JComboBox();
		txttipo.setBounds(91, 108, 86, 20);
		contentPane.add(txttipo);
		txttipo.addItem("Integer");
		txttipo.addItem("String");
		txttipo.addItem("Long");
		txttipo.addItem("Date");
		txttipo.addItem("Char");
		txttipo.addItem("Double");
		
		txtlongitud = new JTextField();
		txtlongitud.setColumns(10);
		txtlongitud.setBounds(90, 158, 86, 20);
		contentPane.add(txtlongitud);
		
		JList listaatributos = new JList();
		listaatributos.setBounds(267, 40, 121, 143);
		contentPane.add(listaatributos);
		
		
		JComboBox list = new JComboBox();
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
		
		JButton btnNewButton = new JButton("Crear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String entidadSeleccionada = list.getSelectedItem().toString().split("-")[0];
				int idEntidad = Integer.parseInt(entidadSeleccionada);
				int idAtributo = Menu.idAtributo.obtenerID();
				Atributo atributo = new Atributo();
				atributo.setIdatributo(idAtributo);
				atributo.setIdentidad(idEntidad);
				atributo.setNombre(txtnombre.getText());
				atributo.setTipo(txttipo.getSelectedIndex()+1);
				atributo.setLongitud(Integer.parseInt(txtlongitud.getText()));
				try {
					boolean resultado = Menu.atributos.agregarAtributo(atributo);
					Menu.idAtributo.modificarID(idAtributo+1);
					if(resultado) {
						JOptionPane.showMessageDialog(null, "Atributo agregado");
						txtnombre.setText("");
						txtlongitud.setText("");
					}
					
				}catch(Exception e) {
					
				}
			}
		});
		btnNewButton.setBounds(153, 189, 89, 23);
		contentPane.add(btnNewButton);
		
		
		
	}
}
