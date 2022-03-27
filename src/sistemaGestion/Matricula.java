package sistemaGestion;

public class Matricula {
	private int nip;
	private int cod;
	
	/**
	 * Pre: 
	 * Post: Metodo constructor, que determina sus variables
	 * segun lo recibido por parametro
	 * 
	 */
	public Matricula (int n, int c) {
		this.nip = n;
		this.cod = c;
	}
	public int getNip() {
		return nip;
	}
	public void setNip(int nip) {
		this.nip = nip;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	
	/**
	 * Pre: 
	 * Post: metodo toString devuelve un String con las variables
	 * 
	 */
	@Override
	public String toString() {
		String datos = "NIP: " + this.nip + " COD: " + this.cod;
		return datos;
	}
}
