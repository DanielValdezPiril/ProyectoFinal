import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
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

	public void agregarEntidad(Entidad entidad) throws Exception {  
		Entidad buscar = obtenerEntidad(entidad.getId());   /// busca si ya existe una entidad 
		if (buscar.getId() != 0) {
			System.out.println("El identificador ya existe");
			return;
		} else {
			int posicion = SIZE * (entidad.getId() - 1);
			archivo.seek(posicion);
			archivo.writeInt(entidad.getId());
			escribirString(entidad.getNombre());      
		}
	}

	public void modificarproducto(Producto c) throws Exception {
		Producto buscar = obtenerProducto(c.getIdp());
		if (buscar.getIdp() == 0) {
			System.out.println("El identificador no existe");
			return;
		} else {
			int posicion = SIZE * (c.getIdp() - 1);
			archivo.seek(posicion);
			archivo.writeInt(c.getIdp());
			escribirString(c.getProducto());
			escribirString(c.getcodigop());
			escribirString(c.getpreciou());
			System.out.println("El Producto modificado");
		}
	}

	public void borrarProducto(int idp) throws Exception {
		Producto buscar = obtenerProducto(idp);
		if (buscar.getIdp() == 0) {
			System.out.println("El identificador no existe");
			return;
		} else {
			int posicion = SIZE * (idp - 1);
			archivo.seek(posicion);
			archivo.writeInt(0);
			escribirString("");
			escribirString("");
			escribirString("");
			System.out.println("El Producto se elimino");
		}
	}

	public void listarProducto() {
		try {
			Producto c = new Producto();
			archivo.seek(0);
			while (true) {
				c.setId(archivo.readInt());
				c.setProducto(leerString());
				c.setcodigop(leerString());
				c.setpreciou(leerString());
				System.out.println(c.mostrar());
				
			}
		} catch (EOFException e) {
			return;
		} catch (Exception e) {
			System.err.println("Ocurrio un error al leer datos del archivo " + e.getMessage());
		}
	}
	
	public void listarProductoSimple() {
		try {
			Producto c = new Producto();
			archivo.seek(0);
			while (true) {
				c.setId(archivo.readInt());
				c.setProducto(leerString());
				c.setcodigop(leerString());
				c.setpreciou(leerString());
				if (c.getIdp() != 0) {
					System.out.println(c.mostrar());
				}
			}
		} catch (EOFException e) {
			return;
		} catch (Exception e) {
			System.err.println("Ocurrio un error al leer datos del archivo " + e.getMessage());
		}
	}



}