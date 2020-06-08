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

public class CrearAtributos extends JFrame {

	private JPanel contentPane;
	private JTextField txtlongitud;

	

	/**
	 * Create the frame.
	 */
	public CrearAtributos() {
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
		
		JLabel lblTipo = new JLabel("Tipo"); // De que tipo desea la entidad
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipo.setBounds(35, 108, 46, 21);
		contentPane.add(lblTipo);
		
		JLabel lblLongitud = new JLabel("Longitud"); // longitud para la entidad 
		lblLongitud.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLongitud.setBounds(35, 161, 70, 17);
		contentPane.add(lblLongitud);
		
		JTextField txtnombre = new JTextField();
		txtnombre.setBackground(new Color(95, 158, 160));
		txtnombre.setBounds(129, 57, 86, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		JComboBox txttipo = new JComboBox(); 
		txttipo.setFont(new Font("Tahoma", Font.BOLD, 12));
		txttipo.setBounds(129, 108, 86, 20);
		contentPane.add(txttipo);
		txttipo.addItem("Integer");
		txttipo.addItem("String");
		txttipo.addItem("Long");
		txttipo.addItem("Date");
		txttipo.addItem("Char");
		txttipo.addItem("Double");
		
		txtlongitud = new JTextField();
		txtlongitud.setBackground(new Color(95, 158, 160));
		txtlongitud.setColumns(10);
		txtlongitud.setBounds(129, 158, 86, 20);
		contentPane.add(txtlongitud);
		
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
		
		JButton btnNewButton = new JButton("Crear");  // Crea los atributos ingresados para la nueva entidad 
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(32, 178, 170));
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
		btnNewButton.setBounds(80, 200, 91, 31);
		contentPane.add(btnNewButton);
		
		JButton btncambiarNombre = new JButton("CambiarNombre");
		btncambiarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String atributoSeleccionado = listaatributos.getSelectedValue().toString().split(":")[0];
				int idAtributo = Integer.parseInt(atributoSeleccionado);
				Menu.atributos.cambiarNombre(idAtributo, txtnombre.getText());
			}
		});
		btncambiarNombre.setBounds(36, 256, 152, 31);
		contentPane.add(btncambiarNombre);
		
		JButton btncambiarTipo = new JButton("Cambiar Tipo");
		btncambiarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String atributoSeleccionado = listaatributos.getSelectedValue().toString().split(":")[0];
				int idAtributo = Integer.parseInt(atributoSeleccionado);
				Menu.atributos.cambiarTipo(idAtributo, txttipo.getSelectedIndex()+1);
			}
		});
		btncambiarTipo.setBounds(35, 294, 152, 31);
		contentPane.add(btncambiarTipo);
		
		JButton btneliminar = new JButton("Eliminar atributo");
		btneliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String atributoSeleccionado = listaatributos.getSelectedValue().toString().split(":")[0];
				String entidadSeleccionada =list.getSelectedItem().toString().split("-")[1];
				int idAtributo = Integer.parseInt(atributoSeleccionado);
				ManejoDatos datos = new ManejoDatos();
				if(!datos.existe(entidadSeleccionada.trim())) {
					Menu.atributos.eliminarAtributo(idAtributo);
					JOptionPane.showMessageDialog(null, "seelimino atributo");
				}
				else {
					JOptionPane.showMessageDialog(null, "No se puede cambiar nombre porque ya tiene datos");
				}
				
			}
		});
		btneliminar.setBounds(45, 340, 143, 23);
		contentPane.add(btneliminar);
		
		
	}
}