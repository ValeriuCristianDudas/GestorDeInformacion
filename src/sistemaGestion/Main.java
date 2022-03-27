package sistemaGestion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	/**
	 * Pre: 
	 * Post: Este metodo main crea las variables principales y recoje los datos introducidos
	 * por el usuario, despues los modifica para hacerlos compatibles con todos los metodos
	 * 
	 */
	public static void main(String[] args) {
		ArrayList<Alumno> al = new ArrayList<Alumno>();
		ArrayList<Matricula> am = new ArrayList<Matricula>();
		ArrayList<Asignatura> aa = new ArrayList<Asignatura>();
		ArrayList<Integer> cods = new ArrayList<Integer>();
		al = lF(".//alumnos.csv", al);
		am = mat(".//matriculas.dat", am);
		aa = lFa(".//asignaturas.txt", aa);
		Scanner capt = new Scanner(System.in);
		String m = null;
		int nip = 0;
		int cod = 0;
		String[] s = null;
		while (true) {
			int pos = 1;
			System.out.print("orden> ");
			m = capt.nextLine();
			m = m.replace("{", "").replace("}", "").replace(",", "");
			s = m.split(" ");
			try {
				if (s[0].equalsIgnoreCase("matriculas")) {
					System.out.println(am.size());
				} else if (s[0].equalsIgnoreCase("asignaturas")) {
					nip = Integer.parseInt(s[1]);
					if (s.length == 3 && s[2].equalsIgnoreCase("a")) {
						mAas(aa, am, nip, s[2]);
					} else if (s.length == 2 || (s.length == 3 && s[2].equalsIgnoreCase("c"))) {
						mAas(aa, am, nip, ":)");
					} else {
						System.out.println(s[2] + " No es un codigo valido");
					}

				} else if (s[0].equalsIgnoreCase("alumnos")) {
					cod = Integer.parseInt(s[1]);
					if (s.length == 3 && s[2].equalsIgnoreCase("a")) {
						for (int j = 0; j < aa.size(); j++) {
							if (cod == aa.get(j).getCod()) {
								System.out.println("Alumnos matriculados en " + aa.get(j).toString());
							}
						}
						mAma(am, al, cod, s[2]);
					} else if (s.length == 2 || (s.length == 3 && s[2].equalsIgnoreCase("n"))) {
						for (int j = 0; j < aa.size(); j++) {
							if (cod == aa.get(j).getCod()) {
								System.out.println("Alumnos matriculados en " + aa.get(j).toString());
							}
						}
						mAma(am, al, cod, ":)");
					} else {
						System.out.println(s[2] + " No es un parametro valido");
					}

				} else if (s[0].equalsIgnoreCase("eliminar")) {
					nip = Integer.parseInt(s[1]);
					if (s.length > 2) {
						for (int v = 2; v < s.length; v++) {
							cods.add(Integer.parseInt(s[v]));
						}
					}
					am = eB(am, cods, nip);
					cods.removeAll(cods);

				} else if (s[0].equalsIgnoreCase("matricular")) {
					nip = Integer.parseInt(s[1]);
					for (int v = 2; v < s.length; v++) {
						cods.add(Integer.parseInt(s[v]));
					}
					am = matr(am, al, cods, aa, nip);
					cods.removeAll(cods);

				} else if (s[0].equalsIgnoreCase("fin")) {
					System.exit(0);
				} else {
					System.out.println("Orden no encontrada");
				}
			} catch (Exception e) {
				System.out.println("COMANDO FALLIDO");
			}
		}
	}

	/**
	 * Pre: 
	 * Post: Este metodo mat rellena un array creando objetos de tipo Matricula, leyendo
	 * un archivo.dat (binario)
	 * 
	 */
	private static ArrayList<Matricula> mat(String r, ArrayList<Matricula> matr) {
		try {
			DataInputStream f = new DataInputStream(new FileInputStream(r));
			try {
				int o = 0;
				while (true) {
					int m = f.readInt();
					int c = f.readInt();
					Matricula mat = new Matricula(m, c);
					matr.add(mat);
					o++;
				}
			} catch (EOFException e) {
				
			}
			f.close();
		} catch (FileNotFoundException e) {
			System.out.println("El fichero " + r + " no ha podido ser abierto");
		} catch (IOException e) {
			System.out.println("Error en operaci�n de E/S con el fichero " + r);
		}
		return matr;
	}

	/**
	 * Pre: 
	 * Post: Este metodo lF rellena un Array de objetos Alumno leyendo un archivo.csv
	 * 
	 */
	private static ArrayList<Alumno> lF(String r, ArrayList<Alumno> al) {
		int c = 0;
		File k = new File(r);
		try {
			Scanner f = new Scanner(k);
			while (f.hasNextLine()) {
				String li = f.nextLine();
				if (c > 0) {
					String[] lin = li.split(";");
					Alumno a = new Alumno(Integer.parseInt(lin[0]), lin[2], lin[1]);
					al.add(a);
				}
				c++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return al;
	}

	/**
	 * Pre: 
	 * Post: Este metodo lFa reyena un array de objetos Asignatura leyendo un archivo.txt
	 * 
	 */
	private static ArrayList<Asignatura> lFa(String r, ArrayList<Asignatura> al) {
		File k = new File(r);
		try {
			Scanner f = new Scanner(k);
			while (f.hasNextLine()) {
				String l = "";
				String li = f.nextLine();
				String[] lin = li.split(" ");
				for (int i = 4; i < lin.length; i++) {
					l = l + lin[i] + " ";
				}
				Asignatura a = new Asignatura(Integer.parseInt(lin[0]), l);
				al.add(a);
				// System.out.println(l);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return al;
	}

	/**
	 * Pre: 
	 * Post: Este metodo mAas crea una Array con objetos Asignatura
	 * segun los parametros pasados desde el main y los ordena en orden
	 * alfabetico o numerico 
	 * 
	 */
	public static void mAas(ArrayList<Asignatura> aa, ArrayList<Matricula> am, int n, String op) {
		ArrayList<Asignatura> al = new ArrayList<Asignatura>();
		for (int i = 0; i < am.size(); i++) {
			if (n == am.get(i).getNip()) {
				for (int j = 0; j < aa.size(); j++) {
					if (am.get(i).getCod() == aa.get(j).getCod()) {
						al.add(aa.get(j));
					}
				}
			}
		}
		if (op.equals("a")) {
			Collections.sort(al, new Comparator<Asignatura>() {
				public int compare(Asignatura a1, Asignatura a2) {
					return a1.getNombre().compareTo(a2.getNombre());
				}
			});
		} else {
			Collections.sort(al, new Comparator<Asignatura>() {
				public int compare(Asignatura a1, Asignatura a2) {
					if (a1.getCod() > a2.getCod()) {
						return 1;
					} else {
						return -1;
					}
				}
			});
		}
		for (Asignatura a : al) {
			System.out.println(a.toString());
		}
	}

	/**
	 * Pre: 
	 * Post: Este metodo mAma crea un Array de objetos Alumno y lo rellena
	 * segun los parametros recibidos desde el main, y los ordena alfabeticamente
	 * o numericamente
	 * 
	 */
	public static void mAma(ArrayList<Matricula> am, ArrayList<Alumno> al, int c, String op) {
		ArrayList<Alumno> ap = new ArrayList<Alumno>();
		for (int i = 0; i < am.size(); i++) {
			if (c == am.get(i).getCod()) {
				for (int k = 0; k < al.size(); k++) {
					if (al.get(k).getNip() == am.get(i).getNip()) {
						ap.add(al.get(k));
					}
				}
			}
		}
		if (op.equals("a")) {
			Collections.sort(ap, new Comparator<Alumno>() {
				public int compare(Alumno a1, Alumno a2) {
					return (a1.getApellido() + " " + a1.getNombre())
								.compareTo(a2.getApellido() + " " + a2.getNombre());
				}
			});
		} else {
			Collections.sort(ap, new Comparator<Alumno>() {
				public int compare(Alumno a1, Alumno a2) {
					if (a1.getNip() > a2.getNip()) {
						return 1;
					} else {
						return -1;
					}
				}
			});
		}
		for (Alumno a : ap) {
			System.out.println(a.toString());
		}
	}
	
	/**
	 * Pre: 
	 * Post: Este metodo eB elimina datos de un archivo.dat (binario) 
	 * segun los parametros recibidos desde el main
	 * 
	 */
	private static ArrayList<Matricula> eB(ArrayList<Matricula> am, ArrayList<Integer> s, int n) {
		try {
			int ocu = 0;
			DataOutputStream f = new DataOutputStream(new FileOutputStream(".//matriculas.dat"));
			for (int i = 0; i < am.size(); i++) {
				if (n == am.get(i).getNip()) {
					if (s.size() == 0) {
						ocu++;
						am.remove(i);
						i--;
					} else {
						for (int j = 0; j < s.size(); j++) {
							if (am.get(i).getCod() == s.get(j)) {
								ocu++;
								am.remove(i);
							}
						}
					}

				}
			}
			if(ocu == 0) {
				System.out.println("NIP: " + n + " no encontrado en lista de matriculas");
			} else {
				System.out.println("Se han eliminado " + ocu + " matriculas de NIP: " + n);
			}
			int o = 0;
			while (true) {
				f.writeInt(am.get(o).getNip());
				f.writeInt(am.get(o).getCod());
				o++;
			}
		} catch (Exception e) {

		}
		return am;
	}
	
	/**
	 * Pre: 
	 * Post: Este metodo matr rellena con datos un archivo.dat,
	 * segun los recibido como parametro desde el main
	 * 
	 */
	private static ArrayList<Matricula> matr (ArrayList<Matricula> am, ArrayList<Alumno> aa,
												ArrayList<Integer> s, ArrayList<Asignatura> as, int n) {
		try {
			int ocu = 0;
			int ocus = 0;
			int ocum = 0;
			DataOutputStream f = new DataOutputStream(new FileOutputStream(".//matriculas.dat"));
			for (int i = 0; i < aa.size(); i++) {
				if (n == aa.get(i).getNip()) {
					ocu++;
				}
			}
			if(ocu == 0) {
				System.out.println("NIP: " + n + " no encontrado en lista de alumnos");
			} if(ocu > 0){
				for(int j = 0; j < am.size(); j++) {
					for(int k = 0; k < s.size(); k++) {
						if(am.get(j).getNip() == n && am.get(j).getCod() == s.get(k)) {
							s.remove(k);
							k--;
						}
					}
				}
				for(int y = 0; y < s.size(); y++) {
					for(int l = 0; l < as.size(); l++) {
						if(s.get(y) == as.get(l).getCod()) {
							ocus++;
						}
						if(l == as.size() - 1 && ocus > 0) {
							ocum++;
							Matricula v = new Matricula(n, s.get(y));
							am.add(v);
						}
					}
				}
				System.out.println("El alumno con NIP " + n + " ha sido matriculado en " + ocum + " asignaturas");
			}
			
			int o = 0;
			while (true) {
				f.writeInt(am.get(o).getNip());
				f.writeInt(am.get(o).getCod());
				o++;
			}
		} catch (Exception e) {
			
		}
		return am;
	}
}
