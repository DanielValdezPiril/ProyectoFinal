import java.io.RandomAccessFile;


public class ManejoId{

	public  final int SIZE = Integer.BYTES;
	RandomAccessFile archivo;
  
	/////****************************************************************************************////
	public void abrirArchivo(String ruta) throws Exception {
		this.archivo = new RandomAccessFile(ruta, "rw");
	}

	public void cerrarArchivo() throws Exception {
		if (archivo != null)
			archivo.close();
	}
  
	/////****************************************************************************************////
	
	public int obtenerID() {
		try {
			archivo.seek(0);
			int a = archivo.readInt();
			return a;
		} catch (Exception e) {
			return 1;
		}
	}

	public void modificarID(int id)  {
		try {
			archivo.seek(0);
			archivo.writeInt(id);
		} catch (Exception e) {
			
		}
	}	

}