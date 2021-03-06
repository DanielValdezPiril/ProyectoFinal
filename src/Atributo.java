/** Esta clase permite recibir los atributos que se ingresaran en la entidad  
 * 
 */
public class Atributo {
	
	public static final int INT = 1;
	public static final int STRING = 2;
	public static final int LONG = 3;
	public static final int DATE = 4;
	public static final int CHAR = 5;
	public static final int DOUBLE = 6;
	
	private int identidad;
	private int idatributo;
	private String nombre;
	private int tipo;
	private int longitud;
	
	public String obtenerTipo() {
		switch(tipo){
		case INT:
			return "Integer";
		case STRING:
			return "String";
		case LONG:
			return "Long";
		case DATE:
			return "Date";
		case CHAR:
			return "Char";
		case DOUBLE:
			return "Double";
		}
		return "tipo no disponible";
	}
		
	public String detalle() {
		if(tipo==STRING) {
			return idatributo+":"+nombre.trim()+" : " + obtenerTipo() + " : " + longitud;
		}
		else {
			return idatributo+":"+nombre.trim()+" : " + obtenerTipo();
		}
	}
	
	public int getIdentidad() {
		return identidad;
	}
	public void setIdentidad(int identidad) {
		this.identidad = identidad;
	}
	public int getIdatributo() {
		return idatributo;
	}
	public void setIdatributo(int idatributo) {
		this.idatributo = idatributo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	
}
