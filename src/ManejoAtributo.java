import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class ManejoAtributo{

	int cantidadCaracteres = 20;
	public  final int SIZE = Integer.BYTES *4 + ( 1* (Character.BYTES * cantidadCaracteres));
	RandomAccessFile archivo;
  
	/////****************************************************************************************////
	public void abrirArchivo(String ruta) throws Exception {
		this.archivo = new RandomAccessFile(ruta, "rw");
	}

	public void cerrarArchivo() throws Exception {
		if (archivo != null)
			archivo.close();
	}

	private String leerString() throws IOException {
		char[] s = new char[cantidadCaracteres];
		for (int i = 0; i < s.length; i++) {
			s[i] = archivo.readChar();
		}
		return new String(s).replace('\0', ' ');
	}

	private void escribirString(String linea) throws Exception {
		StringBuffer buffer = null;
		if (linea != null) {
			buffer = new StringBuffer(linea);
		} else {
			buffer = new StringBuffer(cantidadCaracteres);
		}
		buffer.setLength(cantidadCaracteres);
		archivo.writeChars(buffer.toString());
	}
  
	/////****************************************************************************************////
	
	public int calcularId(String linea) {
		int total = 0;
		for (int i = 0; i < linea.length(); i++) {
			char c = linea.charAt(i);
			int ascii = (int) c;
			total += ascii;
		}
		int idp = (total % 50)+1;
		return idp;
	}
	
	public Atributo obtenerAtributo(int idp) throws Exception {
		if (idp < 1) {
			System.out.println("El identificador debe ser mayor a uno");
			return null;
		}
		Atributo atributo = new Atributo();
		int posicion = SIZE * (idp - 1);
		archivo.seek(posicion);
		try {
			atributo.setIdentidad(archivo.readInt());
			atributo.setIdatributo(archivo.readInt());
			atributo.setNombre(leerString());
			atributo.setTipo(archivo.readInt());
			atributo.setLongitud(archivo.readInt());
		} catch (EOFException e) {
			atributo = new Atributo ();
		}
		return atributo;
	}

	public boolean agregarAtributo(Atributo atributo) throws Exception {  
		Atributo buscar = obtenerAtributo(atributo.getIdatributo());   /// busca si ya existe un atributo 
		if (buscar.getIdatributo() != 0) {
			System.out.println("El identificador ya existe");
			return false;
		} else {
			int posicion = SIZE * (atributo.getIdatributo() - 1);
			archivo.seek(posicion);
			archivo.writeInt(atributo.getIdentidad());
			archivo.writeInt(atributo.getIdatributo());
			escribirString(atributo.getNombre());  
			archivo.writeInt(atributo.getTipo());
			archivo.writeInt(atributo.getLongitud());
			return true;
		}
	}

	public Atributo cambiarNombre(int idp, String nombre)  {
		if (idp < 1) {
			System.out.println("El identificador debe ser mayor a uno");
			return null;
		}
		Atributo atributo = new Atributo();
		int posicion = SIZE * (idp - 1);
		try {
			archivo.seek(posicion);
			archivo.readInt();
			archivo.readInt();
			escribirString(nombre);
		} catch (Exception e) {
			atributo = new Atributo ();
		}
		return atributo;
	}
	
	public Atributo cambiarTipo(int idp, int tipo)  {
		if (idp < 1) {
			System.out.println("El identificador debe ser mayor a uno");
			return null;
		}
		Atributo atributo = new Atributo();
		int posicion = SIZE * (idp - 1);
		try {
			archivo.seek(posicion);
			archivo.readInt();
			archivo.readInt();
			leerString();
			archivo.writeInt(tipo);
		} catch (Exception e) {
			atributo = new Atributo ();
		}
		return atributo;
	}
	
	
	public Atributo eliminarAtributo(int idp)  {
		if (idp < 1) {
			System.out.println("El identificador debe ser mayor a uno");
			return null;
		}
		Atributo atributo = new Atributo();
		int posicion = SIZE * (idp - 1);
		try {
			archivo.seek(posicion);
			archivo.writeInt(-1);
		} catch (Exception e) {
			atributo = new Atributo ();
		}
		return atributo;
	}
	
	
	/*
	public void modificarAtributo (Atributo atributo) throws Exception {
		Atributo buscar = obtenerAtributo(atributo.getIdatributo());
		if (buscar.getIdatributo() == 0) {
			System.out.println("El identificador no existe");
			return;
		} else {
			int posicion = SIZE * (atributo.getIdatributo() - 1);
			archivo.seek(posicion);
			archivo.writeInt(atributo.getIdentidad());
			archivo.writeInt(atributo.getIdatributo());
			escribirString(atributo.getNombre());  
			archivo.writeInt(atributo.getTipo());
			archivo.writeInt(atributo.getLongitud());
			System.out.println("El atributo se ha modificado");
		}
	}*/
/*
	public void borrarAtributo(int id) throws Exception {
		Atributo buscar = obtenerAtributo(id);
		if (buscar.getIdatributo() == 0) {
			System.out.println("El identificador no existe");
			return;
		} else {
			int posicion = SIZE * (id - 1);
			archivo.seek(posicion);
			archivo.writeInt(0);
			archivo.writeInt(0);
			escribirString("");
			archivo.writeInt(0);
			archivo.writeInt(0);
			System.out.println("se ha eliminado el atributo");
		}
	}
	*/
	
	public ArrayList<Atributo> listarAtributo() {
		ArrayList<Atributo> lista = new ArrayList();
		try {
			archivo.seek(0);
			while (true) {
				Atributo atributo = new Atributo();				
				atributo.setIdentidad(archivo.readInt());
				atributo.setIdatributo(archivo.readInt());
				atributo.setNombre(leerString());
				atributo.setTipo(archivo.readInt());
				atributo.setLongitud(archivo.readInt());
				lista.add(atributo);
			}
		} catch (IOException e) {
		} catch (Exception e) {
			System.err.println("No se pudo leer atributos" + e.getMessage());
		}
		return lista;
	}
	

}