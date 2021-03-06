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

public class IngresarDatos extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public IngresarDatos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 461);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList listaatributos = new JList(); // Se mostrara los datos ingresados 
		listaatributos.setBackground(new Color(95, 158, 160));
		listaatributos.setBounds(230, 11, 271, 386);
		contentPane.add(listaatributos);
		
		
		JComboBox list = new JComboBox(); // Selecionar la entidad para ingresar los datos que desea el usuario 
		list.setBackground(new Color(95, 158, 160));
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
		list.setBounds(34, 11, 175, 21);
		contentPane.add(list);
		
		
		ArrayList<Entidad> entidades = Menu.entidades.listarEntidad();
		for(Entidad item : entidades) {
			list.addItem(item.getId()+"-"+item.getNombre());
		} 
		
		JButton btnNewButton = new JButton("Crear"); // Boton para iniciar a ingresar los datos de la entidad 
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String entidadSeleccionada = list.getSelectedItem().toString().split("-")[0];
				String nombreEntidad = list.getSelectedItem().toString().split("-")[1];
				int idEntidad = Integer.parseInt(entidadSeleccionada);
				ArrayList<Atributo> atributos = Menu.atributos.listarAtributo();
				ManejoDatos manejo = new ManejoDatos();
				try {
					manejo.abrirArchivo(nombreEntidad.trim() + ".data");
					for(Atributo item : atributos) {
						if(item.getIdentidad() == idEntidad) {
							String valor = JOptionPane.showInputDialog("Ingrese " + item.getNombre().trim() + " de tipo " + item.obtenerTipo());
							switch(item.getTipo()) {
								case Atributo.INT:
									manejo.archivo.writeInt(Integer.parseInt(valor));
									break;
								case Atributo.STRING:
									manejo.escribirString(valor, item.getLongitud());
									break;
								case Atributo.LONG:
									manejo.archivo.writeLong(Long.parseLong(valor));
									break;
								case Atributo.DATE:
									manejo.escribirString(valor, 11);
									break;
								case Atributo.CHAR:
									manejo.escribirString(valor, 1);
									break;
								case Atributo.DOUBLE:
									manejo.archivo.writeDouble(Double.parseDouble(valor));
									break;
							}
							
						}					
					}
				}catch(Exception e) {
					
				}
			}
		});
		btnNewButton.setBounds(63, 114, 110, 49);
		contentPane.add(btnNewButton);
		
		
		
	}
}
