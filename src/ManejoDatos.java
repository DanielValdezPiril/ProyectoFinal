import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class ManejoDatos{

	//int cantidadCaracteres = 20;
	//public  final int SIZE = Integer.BYTES + (1 * (Character.BYTES * cantidadCaracteres));
	public RandomAccessFile archivo;
  
	/////****************************************************************************************////
	public void abrirArchivo(String ruta) throws Exception {
		this.archivo = new RandomAccessFile(ruta, "rw");
		this.archivo.seek(archivo.length());
	}
	
	public boolean existe(String ruta) {
		File archivo = new File(ruta+".data");
		return archivo.isFile();
	}

	public void cerrarArchivo() throws Exception {
		if (archivo != null)
			archivo.close();
	}

	public String leerString(int cantidadCaracteres) throws IOException {
		char[] s = new char[cantidadCaracteres];
		//System.out.println("--"+cantidadCaracteres);
		for (int i = 0; i < s.length; i++) {
			s[i] = archivo.readChar();
		}
		//System.out.println(new String(s));
		return new String(s).replace('\0', ' ').trim();
	}

	public void escribirString(String linea,int cantidadCaracteres) throws Exception {
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
	
}