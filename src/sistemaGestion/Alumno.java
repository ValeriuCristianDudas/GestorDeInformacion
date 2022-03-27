package sistemaGestion;

public class Alumno {
	private int nip;
	private String nombre;
	private String apellido;
	
	/**
	 * Pre: 
	 * Post: Metodo constructor, que determina sus variables
	 * segun lo recibido por parametro
	 * 
	 */
	public Alumno(int nip, String n, String a) {
		this.nip = nip;
		this.nombre = n;
		this.apellido = a;
	}
	public int getNip() {
		return nip;
	}
	public void setNip(int nip) {
		this.nip = nip;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	/**
	 * Pre: 
	 * Post: metodo toString devuelve un String con las variables
	 * 
	 */
	@Override
	public String toString() {
		String datos = "Nombre: " + this.nombre + " Apellido: " + this.apellido + " NIP: " + this.nip;
		return datos;
	}
	

}
