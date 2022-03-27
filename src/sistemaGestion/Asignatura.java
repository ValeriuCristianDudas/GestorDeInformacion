package sistemaGestion;

public class Asignatura {
	private int cod;
	private String nombre;
	
	/**
	 * Pre: 
	 * Post: Metodo constructor, que determina sus variables
	 * segun lo recibido por parametro
	 * 
	 */
	public Asignatura(int c, String n) {
		this.cod = c;
		this.nombre = n;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Pre: 
	 * Post: metodo toString devuelve un String con las variables
	 * 
	 */
	@Override
	public String toString() {
		String datos = "Nombre: " + this.nombre + " COD: " + this.cod;
		return datos;
	}
}
