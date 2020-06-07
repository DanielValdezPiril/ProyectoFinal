import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarDato extends JFrame {

	private JPanel contentPane;

	private Object[] appendValue(Object[] obj, Object newObj) {

		ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(obj));
		temp.add(newObj);
		return temp.toArray();

	 }

	/**
	 * Create the frame.
	 */
	public MostrarDato() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTable table = new JTable();
		table.setBounds(34, 106, 616, 111);
		contentPane.add(table);
		
		JComboBox list = new JComboBox();
		list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String entidadSeleccionada = list.getSelectedItem().toString().split("-")[0];
				String nombreEntidad = list.getSelectedItem().toString().split("-")[1];
				int idEntidad = Integer.parseInt(entidadSeleccionada);
				ArrayList<Atributo> atributos = Menu.atributos.listarAtributo();
				ManejoDatos manejo = new ManejoDatos();
				DefaultTableModel tableModel = new DefaultTableModel();
				tableModel.addColumn("ID");
				tableModel.addColumn("Nombre");
				tableModel.addColumn("Color");
				table.setModel(tableModel);
				
				
				try {
					manejo.abrirArchivo(nombreEntidad.trim() + ".data");
					manejo.archivo.seek(0);
					while(true && manejo.archivo.length()>0) {
						Object[] dato = new Object[]{};
						for(Atributo item : atributos) {
							if(item.getIdentidad() == idEntidad) {
								switch(item.getTipo()) {
									case Atributo.INT:
										dato = appendValue(dato, manejo.archivo.readInt());
										break;
									case Atributo.STRING:
										dato = appendValue(dato, manejo.leerString(item.getLongitud()));
										break;
									case Atributo.LONG:
										dato = appendValue(dato, manejo.archivo.readLong());
										break;
									case Atributo.DATE:
										dato = appendValue(dato, manejo.leerString(11));
										break;
									case Atributo.CHAR:
										dato = appendValue(dato, manejo.leerString(1));
										break;
									case Atributo.DOUBLE:
										dato = appendValue(dato, manejo.archivo.readDouble());
										break;
								}								
							}							
						}
						tableModel.addRow(dato);
					}
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
			}
		});
		list.setBounds(34, 11, 175, 21);
		contentPane.add(list);
		
		
		ArrayList<Entidad> entidades = Menu.entidades.listarEntidad();
		for(Entidad item : entidades) {
			list.addItem(item.getId()+"-"+item.getNombre());
		} 
		
	}
}
