
public class Atributo {

	public final int INT = 1;
	public final int STRING = 2;
	public final int LONG = 3;
	public final int DATE = 4;
	public final int CHAR = 5;
	public final int DOUBLE = 6;
	
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
			return nombre.trim()+" : " + obtenerTipo() + " : " + longitud;
		}
		else {
			return nombre.trim()+" : " + obtenerTipo();
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
