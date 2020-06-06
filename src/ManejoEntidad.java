import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class ManejoEntidad{

	int cantidadCaracteres = 20;
	public  final int SIZE = Integer.BYTES + (1 * (Character.BYTES * cantidadCaracteres));
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
	
	public Entidad obtenerEntidad(int idp) throws Exception {
		if (idp < 1) {
			System.out.println("El identificador debe ser mayor a uno");
			return null;
		}
		Entidad entidad = new Entidad();
		int posicion = SIZE * (idp - 1);
		archivo.seek(posicion);
		try {
			entidad.setId(archivo.readInt());
			entidad.setNombre(leerString());
		} catch (EOFException e) {
			entidad = new Entidad();
		}
		return entidad;
	}

	public boolean agregarEntidad(Entidad entidad) throws Exception {  
		Entidad buscar = obtenerEntidad(entidad.getId());   /// busca si ya existe una entidad 
		if (buscar.getId() != 0) {
			System.out.println("El identificador ya existe");
			return false;
		} else {
			int posicion = SIZE * (entidad.getId() - 1);
			archivo.seek(posicion);
			archivo.writeInt(entidad.getId());
			escribirString(entidad.getNombre());    
			return true;
		}
	}

	public void modificarEntidad (Entidad entidad) throws Exception {
		Entidad buscar = obtenerEntidad(entidad.getId());
		if (buscar.getId() == 0) {
			System.out.println("El identificador no existe");
			return;
		} else {
			int posicion = SIZE * (entidad.getId() - 1);
			archivo.seek(posicion);
			archivo.writeInt(entidad.getId());
			escribirString(entidad.getNombre());
			System.out.println("La edentidad se ha modificado");
		}
	}

	public void borrarEntidad(int id) throws Exception {
		Entidad buscar = obtenerEntidad(id);
		if (buscar.getId() == 0) {
			System.out.println("El identificador no existe");
			return;
		} else {
			int posicion = SIZE * (id - 1);
			archivo.seek(posicion);
			archivo.writeInt(0);
			escribirString("");
			System.out.println("se ha eliminado la Entidad");
		}
	}

	public ArrayList<Entidad> listarEntidad() {
		ArrayList<Entidad> lista = new ArrayList();
		try {
			archivo.seek(0);
			while (true) {
				Entidad entidad = new Entidad();
				entidad.setId(archivo.readInt());
				entidad.setNombre(leerString());
				lista.add(entidad);
			}
		} catch (IOException e) {
		} catch (Exception e) {
			System.err.println("Ocurrio un error al leer datos del archivo " + e.getMessage());
		}
		return lista;
	}
	

}