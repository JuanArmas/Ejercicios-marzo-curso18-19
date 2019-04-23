package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Equipo;
import modelo.Estudiante;
import modelo.Jugador;
import modelo.Partido;
import modelo.Persona;

public class Ejercicios {
	// 21 marzo 2019	
	// practica enum
	// mirar clase nueva de este mismo paquete
	// reflexion -> uso de reflexion ---> se usa en los frameworks, (uno de java spring, orm, etc.)
	 // le das un objeto o u objeto de clase y sabra todas las caracteristicas del objeto. 
	
	
	/****************************************************/
	
	//20 marzo 2019
	// Ejemplo de recorrido recursivo de un �rbol de directorios
	// esto es perfecto para crear un gestor de ficheros
	// sirve para recorrer y consultar las carpetas y su contenido...�?
	// revisar, verificar y aumentar la info
	
	public static void traverse(File parentNode, String leftIndent) {
	    if (parentNode.isDirectory()) {
	        System.out.println(leftIndent + parentNode.getName());
	        leftIndent += "     ";
	        File childNodes[] = parentNode.listFiles();
	        for (File childNode : childNodes) {
	            traverse(childNode, leftIndent);
	        }
	    } else {
	        System.out.println(leftIndent +"|   --> "+ parentNode.getName());
	    }
	}
	
	// 12 marzo 2019
	//inicio practicas orientadas a sesion de evaluaci�n.
		// modificar el metodo crearMapaEquipos para que devuelva un ArrayList<Equipo>
		// el fichero a leer ser� equipos.txt
	
	public ArrayList<Equipo> crearListaEquipo(String rutaFicheroEquipos){
		ArrayList<Equipo> listadoEquipos= new ArrayList<Equipo>();
		try {
			FileReader fr = new FileReader(rutaFicheroEquipos);
			BufferedReader br = new BufferedReader(fr);		
			String fichero;
			while((fichero = br.readLine()) !=null) {
				String[] unaLinea = fichero.split("#");
				Integer id = Integer.parseInt(unaLinea[0]);
				String nombreCorto = unaLinea[1];
				String nombre = unaLinea[2];			
				Integer pj = Integer.parseInt(unaLinea[3]);			
				Integer puntos = Integer.parseInt(unaLinea[4]);			
				Integer pg = Integer.parseInt(unaLinea[5]);			
				Integer pe = Integer.parseInt(unaLinea[6]);			
				Integer pp = Integer.parseInt(unaLinea[7]);			
				Integer gf = Integer.parseInt(unaLinea[8]);			
				Integer gc = Integer.parseInt(unaLinea[9]);			
				/*
				 * otra forma de a�adir los valores al AL con set
				 * unEquipo.set(id);
				 * unEquipo.set(nombreCorto);
				 * unEquipo.set(nombre);
				 * unEquipo.set(pj);
				 * unEquipo.set(puntos);
				 * unEquipo.set(pg);
				 * unEquipo.set(pe);
				 * unEquipo.set(pp);
				 * unEquipo.set(gf);
				 * unEquipo.set(gc);
				 * */
				Equipo unEquipo = new Equipo(id, nombreCorto, nombre, pj, puntos, pg, pe, pp, gf, gc);
				listadoEquipos.add(unEquipo);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error FileNotFound");
		} catch (IOException e) {
			System.out.println("Error I/O");
		}		
		return listadoEquipos;
	}

	// algoritmo busqueda dicotomica o binaria // algoritmo de prueba de limites
	// buscar en un array de numeros (int[]pajar, int aguja), a la hora de introducir el array habr� que crearlo de forma ascendente, 2,4,6,8, etc...
	// devolvera la posicion entre 0 y n-1 de hallarla, si no estuviera devolveria -1 (-1== "el numero no se encuentra")
	// implementacion: hacer lista de las variables a usar, locales al metodo
	// variables-> izq, drch, medio (el que se busca esta entre algun rango? no, seguimos, si cortamos y rebuscamos en la seccion restante)
	// medio = (drch-izq/2) + izq
	// todo esto estara dentro de un bucle, el cual finalizara cuando...
	// primera salida-> if pajar[medio]==aguja) ->lo ha encontrado por lo que return medio
	// segunda salida-> if aguja<pajar[medio]) -> drch = medio; el medio pasa a ser derecho acortandose la busqueda
	// else izq = med;
	
	public int busquedaBinaria(int[] pajar, int aguja) {
		int numeroNoEncontrado = -1;
		int drch = pajar.length -1; //extension maxima del array hacia la derecha
		int izq = 0; // extension minima del arraylist
		int med = 0; // punto medio que se convertir� en drch o izq seg�n sea el num escogido (este m�s cerca de la drch o de la izq)
		while(izq <= drch){
			med = ((drch-izq) / 2) + izq; // dara el punto medio (med)
			if(pajar[med] == aguja) {// si el numero en el punto medio es igual al num escogido hacer...
				System.out.println("Numero " + aguja + " encontrado en POSICION " + med);
				return med;
			}else if(aguja < pajar[med]) {				
				drch = med - 1;
			}else {
				izq = med + 1;
			}
		}
		System.out.println(" Numero no encontrado " + aguja);
		return numeroNoEncontrado;
	}
	
	// equipo es el nombre corto XYZ,RMA
	// recorrer la LISTA de equipos, preguntando si el nombre corto coincide con alguno
	// si no encontramos ninguno, devolver� null...
	public Equipo buscarEquipo(String equipo, ArrayList<Equipo>equipos) {	
		for(int i = 0; i < equipos.size(); i++) {
		String nombreCorto = equipos.get(i).getNombreCorto();
			if(nombreCorto.equals(equipo)) {
				System.out.println("El Equipo escogido es: " + equipo);
			}
		}
		return null;
	}
	// crear la lista.
	// crearListaJugadores a partir de un fichero
	// recorrer secuencialmente el fichero
	// crear objeto Jugador por cada registro del fichero
	// a�adir jugador a la lista
	// al terminar el fichero, devolver la lista
	// si hay cuaquier excepcion, capturarla y devolver null
	public ArrayList<Jugador> creaListaJugadores(String RutaFicheroJugadores){
		ArrayList<Jugador> listaJugadores= new ArrayList<Jugador>();
		try {
			FileReader fr = new FileReader(RutaFicheroJugadores);
			BufferedReader br = new BufferedReader(fr);		
			String fichero;
				while((fichero = br.readLine()) !=null) {
					String[] unaLinea = fichero.split("#");
					Integer id = Integer.parseInt(unaLinea[0]);
					String nombre = unaLinea[1];				
					Integer dorsal = Integer.parseInt(unaLinea[2]);
					Integer idEq = Integer.parseInt(unaLinea[3]);
					Jugador unJugador = new Jugador(id, null, nombre, 0, null, ' ', dorsal, idEq);
					listaJugadores.add(unJugador);
				}
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return listaJugadores;
	}
	
	// crearMapaJugadores a partir de un fichero
	// K-V, K es el nif
	// sehir los mismos pasos que con el anterior pero siendo un hashmap
	public HashMap<String, Jugador> creaMapaJugadores(String RutaFicheroJugadores){
		HashMap<String, Jugador> mapaJugadores= new HashMap<String, Jugador>();
		try {
			FileReader fr = new FileReader(RutaFicheroJugadores);
			BufferedReader br = new BufferedReader(fr);		
			String fichero;
				while((fichero = br.readLine()) !=null) {
					String[] unaLinea = fichero.split("#");
					Integer id = Integer.parseInt(unaLinea[0]);
					String nif = unaLinea[1];
					String nombre = unaLinea[2];
					Integer dorsal = Integer.parseInt(unaLinea[6]);
					Integer idEq = Integer.parseInt(unaLinea[7]);
					Jugador unJugador = new Jugador(id, nif, nombre, 0, null, ' ', dorsal, idEq);
					mapaJugadores.put(nif, unJugador);				
				}
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}		
		return mapaJugadores;
	}
	
	// ordenarListaPorNombre
	// 
	public void ordenarListaJugadores(ArrayList<Jugador> listaJugadores) {		
		Collections.sort(listaJugadores, new Comparator<Jugador>() {
			@Override
			public int compare(Jugador jugador1, Jugador jugador2) {
				return jugador1.getNombre().compareTo(jugador2.getNombre());
				
			}	
		});
			for(Jugador jugador: listaJugadores){
			    System.out.println(jugador.getNombre());
			}	
	}
	
	public void ordenarMapaJugadores(HashMap<String, Jugador> mapaJugadores) {
		Set<Entry<String, Jugador>> set = mapaJugadores.entrySet();
		List<Entry<String, Jugador>> listado = new ArrayList<Entry<String, Jugador>>(set);		
		Collections.sort(listado, new Comparator<Map.Entry<String, Jugador>>() {
			@Override
			public int compare(Entry<String, Jugador> Jugador1, Entry<String, Jugador> Jugador2) {
				return (Jugador1.getValue().getNombre()).compareTo(Jugador2.getValue().getNombre());
			}			
		});
		for (Map.Entry<String, Jugador> clavesMapa : listado) {
			System.out.println(clavesMapa.getKey() + " ==== " + clavesMapa.getValue());
		}	
	}
	/**********************************************************/
	// inicio practicas escribir ficheros

	// ejercicio
	// dado un equipo mostrar sus jugadores.

	// algun ejercicio relacionado con el acceso y escritura de objetos

	// 21 febrero 2019
	public void crearJugadoresPorEquipo() {
		// el string ser� la clave nombre largo de equipos
		HashMap<String, ArrayList<Jugador>> mapaListadoJugadoresEquipos = new HashMap<String, ArrayList<Jugador>>();
		try {
			FileOutputStream salida = new FileOutputStream("ficheros/jugadoresEquipos.obj");
			ObjectOutputStream objetos = new ObjectOutputStream(salida);
			HashMap<String, Equipo> mapaEquipos = crearMapaEquipos("ficheros/equipos.txt");
			HashMap<Integer, Jugador> mapaJugadores = crearMapaJugadores("ficheros/jugadores.txt");
			Set<String> clavesEquipos = mapaEquipos.keySet();
			for (String unaClaveEquipo : clavesEquipos) {
				Equipo valoresEquipos = mapaEquipos.get(unaClaveEquipo);
				String nombreLargoEquipo = valoresEquipos.getNombre();
				mapaListadoJugadoresEquipos.put(nombreLargoEquipo, new ArrayList<Jugador>());
			}

			Collection<Jugador> valoresMapaJugadores = mapaJugadores.values();
			for (Jugador unJugador : valoresMapaJugadores) {
				int idEquipoJugador = unJugador.getIdEquipo();
				String nombreLargoUnEquipo = null;
				Collection<Equipo> valoresMapaEquipos = mapaEquipos.values();
				for (Equipo unEquipo : valoresMapaEquipos) {
					int idEquipo = unEquipo.getId();
					if (idEquipo == idEquipoJugador) {
						nombreLargoUnEquipo = unEquipo.getNombre();
						break;
					}
				}
				mapaListadoJugadoresEquipos.get(nombreLargoUnEquipo).add(unJugador);
			}
			objetos.writeObject(mapaListadoJugadoresEquipos);
			objetos.close();
			salida.close();

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

	}
	
	public void mostrarListadoJugadoresEquipo(String rutaFichero) {
		HashMap<String, ArrayList<Jugador>> listadoJugadoresEquipos= new HashMap<String, ArrayList<Jugador>>();
		try {
			FileInputStream fis = new FileInputStream(rutaFichero);
			ObjectInputStream ois = new ObjectInputStream(fis);
			listadoJugadoresEquipos = (HashMap<String, ArrayList<Jugador>>) ois.readObject();
			
			Set<String> clavesListadoJugadoresEquipos = listadoJugadoresEquipos.keySet();
			for( String nombreLargoEquipos : clavesListadoJugadoresEquipos) {
				System.out.println(nombreLargoEquipos);
				ArrayList<Jugador> jugadoresUnEquipo = listadoJugadoresEquipos.get(nombreLargoEquipos);
				for(int i=0; i < jugadoresUnEquipo.size(); i++) {
					System.out.println(jugadoresUnEquipo.get(i));
				}
			}
			System.out.println();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("estoy que no encuentro el fichero");
		} catch (IOException e) {
			System.out.println("IO");
		} catch (ClassNotFoundException e) {
			System.out.println("cero clases");
		}
		
	
	}

	public HashMap<Integer, Jugador> crearMapaJugadores(String rutaFichero) {
		try {
			HashMap<Integer, Jugador> mapaJugadores = new HashMap<Integer, Jugador>();
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro = " ";
			int idEquipoExistente = 0;

			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				ArrayList<Jugador> listadoJugadores;
				int idJugador = Integer.parseInt(campos[0]);
				String nif = campos[1];
				String nombre = campos[2];
				int longPasos = Integer.parseInt(campos[3]);
				String fechaNac = campos[4];
				char sexo = campos[5].charAt(0);
				int dorsal = Integer.parseInt(campos[6]);
				int idEquipo = Integer.parseInt(campos[7]);

				Jugador unJugador = new Jugador(idJugador, nif, nombre, longPasos, fechaNac, sexo, dorsal, idEquipo);
				mapaJugadores.put(idJugador, unJugador);
				System.out.println();

			}
			fichero.close();
			System.out.println("Fin de la lectura del fichero");
			return mapaJugadores;
		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		return null;
	}

	// 20 febrero 2019
	// -----------------------------------------

	// grabar cada registro
	// 1ero buscar en java clases que diga object (io, string, output�?)
	// Serializable -> interfaz que sirve para ?�
	// a partir del fichero Equipos.txt crear un fichero binario.
	// en la clase equipo habr� que implementar la clase serializable
	// leer el objeto equipo y coger un tutorial para... �?
	// el fichero de salida sera un objeto de clase equipo
	// cada vez que lea el fichero txt crea un fichero equipo y se graba en el
	// fichero salida.
	// recorre equipos.txt creando objetos equipo y grabandolos en objetos
	// cuando se lea siempre devuelve un object, se deber� castear.
	// read object -> devuelve un object. se pondra un casting de equipo (Equipo)
	// asi se podra trabajar con las propiedades del objeto.
	public void creaFicheroObjetoEquipos() {
		try {
			FileOutputStream salida = new FileOutputStream("ficheros/equipos.obj");
			ObjectOutputStream objetos = new ObjectOutputStream(salida);
			// ObjectOutputStream objetos = new ObjectOutputStream(new
			// FileOutputStream("ficheros/equipos.obj"));
			// recorre equipos.txt, creando objetos equipo y grabandolos en objetos
			HashMap<String, Equipo> mapaEquipos = crearMapaEquipos("ficheros/equipos.txt");

			Set<String> clavesMapaEquipos = mapaEquipos.keySet(); // por claves
			for (String claveEquipo : clavesMapaEquipos) {
				Equipo objetoEquipo = mapaEquipos.get(claveEquipo);
				objetos.writeObject(objetoEquipo);
				// objetos.writeObject(" " + objetoEquipo); // graba cada objeto como string

			}
			/*
			 * for(Equipo unEquipo : mapaEquipos.values()) { //por valores
			 * objetos.writeObject(unEquipo); }
			 */
			objetos.close();
			salida.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error FileNotFound " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error I/O " + e.getMessage());
		}
	}

	public void mostrarFicheroObjetoEquipo(String rutaEquipos) {
		FileInputStream fis = null;
		ObjectInputStream lecturaFicheroObjeto = null;
		try {
			fis = new FileInputStream("ficheros/equipos.obj" /* rutaEquipos */);
			lecturaFicheroObjeto = new ObjectInputStream(fis);
			int i = 0;
			// fis es el acceso al fichero, el metodo available pregunta cuantos bytes
			// quedan por leer
			// de datos en el fichero, cuando llegue a 0 byte daria false en la comparacion.
			while (fis.available() > 0) {
				Equipo unEquipo = (Equipo) lecturaFicheroObjeto.readObject();
				System.out.println(i + " Id:" + unEquipo.getId() + " -- " + unEquipo.getNombreCorto() + " -- "
						+ unEquipo.getNombre());
				i++;
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error FileNotFound " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error I/O " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error ClassNotFound " + e.getMessage());
		} finally {
			try {
				lecturaFicheroObjeto.close();
			} catch (IOException e) {
				System.out.println("Error I/O finally" + e.getMessage());
			}
		}
	}

	public void grabarTiradasDado(int cuantas) {
		try {
			// "tiradasDado.txt"
			// abrir fichero de salida
			BufferedWriter fichero;
			fichero = new BufferedWriter(new FileWriter("ficheros/tiradasDado.txt"));
			Random rnd = new Random();
			int acum = 0;
			for (int i = 0; i < cuantas; i++) {
				int numero = 1 + rnd.nextInt(6);
				acum += numero;
				fichero.write(numero + "\n");
			}
			System.out.printf("media = %.2f \n", (float) acum / cuantas);
			System.out.println("Proceso terminado...");
			fichero.close();
		} catch (IOException ex) {
			System.out.println("Error I/O " + ex.getMessage());

		}

	}

	public void entradaTecladoAFichero(String rutaFichero) {
		try {
			BufferedWriter fichero;
			fichero = new BufferedWriter(new FileWriter(rutaFichero));
			Scanner teclado = new Scanner(System.in);
			String tecleado;
			while ((tecleado = teclado.nextLine()).compareToIgnoreCase("x") != 0) {
				System.out.println("Teclee sus datos..(x|X) para terminar");
				fichero.write(tecleado + "\n");
			}
			fichero.close();
		} catch (IOException ex) {
			System.out.println("Error I/O " + ex.getMessage());
		}
		System.out.println("fin entrada de datos..");
	}

	// 19 febrero 2019

	public void muestraClasificacion() {
		JFrame ventana;
		ventana = new JFrame("Clasificacion");

		JPanel panel = new JPanel();
		ventana.add(panel);

		ArrayList<Equipo> equipos = this.generaClasificacion("ficheros/partidos.txt", "ficheros/equipos.txt");

		String[] columnas = { "EQUIPO", "PUNTOS", "PJ", "PG", "PE", "PP", "GF", "GC" };
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

		modelo.addRow(columnas);
		for (Equipo equipo : equipos) {
			Object[] vector = { equipo.getNombre(), equipo.getPuntos(), equipo.getPj(), equipo.getPg(), equipo.getPe(),
					equipo.getPp(), equipo.getGf(), equipo.getGc() };
			modelo.addRow(vector);
		}
		JTable tabla = new JTable(modelo);
		panel.add(tabla);
		ventana.pack();
		ventana.setVisible(true);
	}

	// 14 febrero 19

	public Equipo buscarEquipoEnLista(String nombreCorto, ArrayList<Equipo> equipos) {
		Equipo resultado;
		for (Equipo equipo : equipos) {
			if (equipo.getNombreCorto().equals(nombreCorto))
				return equipo;
		}
		System.out.println("Ooops.. algo falla");
		return null;
	}

	// 13 febrero
	public void actualizaEquipos(Partido partido, ArrayList<Equipo> equipos) {
		String nCortoL = partido.geteL();
		String nCortoV = partido.geteV();
		Equipo eL = buscarEquipoEnLista(nCortoL, equipos);
		Equipo eV = buscarEquipoEnLista(nCortoV, equipos);

		// logica del resultado del partido
		if (partido.getgL() > partido.getgV()) {
			eL.setPuntos(eL.getPuntos() + 3);
			eL.setPg(eL.getPg() + 1);
			eV.setPp(eV.getPp() + 1);
		} else if (partido.getgL() < partido.getgV()) {
			eV.setPuntos(eV.getPuntos() + 3);
			eV.setPg(eV.getPg() + 1);
			eL.setPp(eL.getPp() + 1);
		} else {
			eL.setPuntos(eL.getPuntos() + 1);
			eV.setPuntos(eV.getPuntos() + 1);
			eV.setPe(eV.getPe() + 1);
			eL.setPe(eL.getPe() + 1);
		}
		eL.setGf(eL.getGf() + partido.getgL());
		eL.setGc(eL.getGc() + partido.getgV());

		eV.setGf(eV.getGf() + partido.getgV());
		eV.setGc(eV.getGc() + partido.getgL());

		eL.setPj(eL.getPj() + 1);
		eV.setPj(eV.getPj() + 1);
	}

	public Partido creaPartido(String linea) {
		Partido partido = new Partido();
		String[] campos = linea.split("#");
		partido.setId(Integer.parseInt(campos[0]));
		partido.setJornada(Integer.parseInt(campos[1]));
		partido.seteL(campos[2]);
		partido.seteV(campos[4]);

		try {
			partido.setgL(Integer.parseInt(campos[3]));
			partido.setgV(Integer.parseInt(campos[5]));
		} catch (NumberFormatException e) {
			return null;
		}

		return partido;

	}

	public ArrayList<Equipo> generaClasificacion(String rutaPartidos, String rutaEquipos) {
		ArrayList<Equipo> resultado;
		try {
			// crear lista equipos desde fichero equipos.txt
			resultado = crearListaEquipos(rutaEquipos);
			//
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaPartidos));
			String registro;
			Partido partido;
			while ((registro = fichero.readLine()) != null) {
				partido = creaPartido(registro);
				if (partido == null) // ultimo partido jugado..
					break;
				// actualiza lista Equipos
				actualizaEquipos(partido, resultado);
			}
			Collections.sort(resultado, null);
			fichero.close();
			return resultado;
		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		return null;
	}

	// 5 de febrero 2019

	// Obtener un ArrayList ORDENADA por nombre LAARgo del equipo
	// a partir de la lista obtenida en el m�todo
	// crearListaEquipos

	// 5 de febrero 2019
	
	// Obtener un ArrayList ORDENADA por nombre LAARgo del equipo
	// a partir de la lista obtenida en el m�todo
	// crearListaEquipos
	
	public ArrayList<Equipo> equiposListaOrdenadaNombre(String rutaFichero) {
		ArrayList<Equipo> lista;
		lista = crearListaEquipos(rutaFichero);
	
		/*
		 * lista.sort(new Comparator<Equipo>() {
		 * 
		 * 
		 * @Override public int compare(Equipo eq1, Equipo eq2) {
		 * 
		 * return eq1.getNombre().compareTo(eq2.getNombre()); }
		 * 
		 * 
		 * public int compare(Equipo eq1, Equipo eq2) {
		 * 
		 * if (eq1.getId() < eq2.getId()) return 1; else if (eq1.getId() > eq2.getId())
		 * return -1; else return 0; } });
		 */
		lista.sort(null);
	
		return lista;
	
	}

	public void ordenarMapaPuntosEquipos(HashMap<String, Integer> puntosEquipos) {
		Set<Entry<String, Integer>> set = puntosEquipos.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		for (Map.Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey() + " ==== " + entry.getValue());
		}
	}

	// 30 enero 2019
	public HashMap<String, Integer> generaPuntosEquipos(HashMap<String, ArrayList<Integer>> partidos_GEP) {
		HashMap<String, Integer> resultado = new HashMap<String, Integer>();
		for (String clave : partidos_GEP.keySet()) {
			ArrayList<Integer> datos = partidos_GEP.get(clave);
			int puntos = datos.get(0) * 3 + datos.get(1);
			resultado.put(clave, puntos);
		}
		return resultado;
	}

	// pruebita de SWING (MVC) modelo vista controlador

	public void pruebaSWING() {
		JFrame ventana;
		ventana = new JFrame("Mi primer SWING");
		JButton boton = new JButton("pulsaMe!");
		JPanel panel = new JPanel();
		ventana.add(panel);

		ArrayList<Equipo> equipos = this.crearListaEquipos("ficheros/equipos.txt");

		Equipo[] arrayEquipos = equipos.toArray(new Equipo[equipos.size()]);

		JComboBox lista = new JComboBox(arrayEquipos);
		panel.add(lista);
		panel.add(boton);
		boton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Pulsado");
			}
		});

		ventana.pack();
		ventana.setVisible(true);
	}

	// 29 enero 2019

	public void muestraPuntosEquipos(HashMap<String, ArrayList<Integer>> resultados) {
		// recorrer el HashMap...
		// obtenemos la lista de claves (K)
		for (String clave : resultados.keySet()) {
			ArrayList<Integer> datos = resultados.get(clave);
			int puntos = datos.get(0) * 3 + datos.get(1);
			System.out.println(clave + " => " + puntos);
		}
	}

	public HashMap<String, ArrayList<Integer>> resultadosEquipos(String rutaPartidos)
	// devuelve un mapa de equipos
	// por cada equipo hay una lista de contadores
	// que representan VICTORIAS, EMPATES Y DERROTAS
	{
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaPartidos));
			String registro;
			HashMap<String, ArrayList<Integer>> equipos = new HashMap<String, ArrayList<Integer>>();
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				if (campos[3].equals("")) // ultimo partido jugado..
					break;
				String eL = campos[2];
				String gL = campos[3];
				String eV = campos[4];
				String gV = campos[5];

				// gracias Byron..!!
				// si no existe eL, eV lo a�adimos al mapa..

				if (!equipos.containsKey(eL))
					equipos.put(eL, new ArrayList<Integer>(Arrays.asList(0, 0, 0)));

				if (!equipos.containsKey(eV))
					equipos.put(eV, new ArrayList<Integer>(Arrays.asList(0, 0, 0)));

				// cual fue el resultado ..?

				if (gL.compareTo(gV) > 0) {// gana Local
					equipos.get(eL).set(0, equipos.get(eL).get(0) + 1);
					equipos.get(eV).set(2, equipos.get(eV).get(2) + 1);

				} else if (gL.compareTo(gV) < 0) // gana Visitante
				{// gana Local
					equipos.get(eL).set(2, equipos.get(eL).get(2) + 1);
					equipos.get(eV).set(0, equipos.get(eV).get(0) + 1);
				} else { // empate

					equipos.get(eL).set(1, equipos.get(eL).get(1) + 1);
					equipos.get(eV).set(1, equipos.get(eV).get(1) + 1);
				}

			}
			fichero.close();
			System.out.println("Fin de la lectura del fichero");
			return equipos;

		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		return null;

	}

	public void mostrarNumeroPartidosJugadosTry(String rutaPartidos) {
		BufferedReader fichero = null;
		int contador = 0;
		try {

			fichero = new BufferedReader(new FileReader(rutaPartidos));
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				try {
					Integer.parseInt(campos[3]);
					contador++;
				} catch (NumberFormatException e) {
					break;
				}
			}
			fichero.close();
			System.out.println(contador);
			System.out.println("Fin de la lectura del fichero");

		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
	}

	// 23 enero 2019

	public void mostrarNumeroPartidosJugados(String rutaPartidos) {

		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaPartidos));
			String registro;
			int contador = 0;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				if (!campos[3].equals("")) {
					Integer.parseInt(campos[3]);
					contador++;

				} else
					break;
			}
			fichero.close();
			System.out.println(contador);
			System.out.println("Fin de la lectura del fichero");

		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
	}

	// Mapa de equipos

	public HashMap<String, Equipo> crearMapaEquipos(String rutaFichero) {
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			Equipo equipo = null;
			HashMap<String, Equipo> equipos = new HashMap<String, Equipo>();
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				equipo = new Equipo(Integer.parseInt(campos[0]), campos[1], campos[2]);
				equipos.put(campos[1], equipo);
			}
			fichero.close();
			System.out.println("Fin de la lectura del fichero");
			return equipos;

		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		return null;
	}
	// lista de equipos

	public ArrayList<Equipo> crearListaEquipos(String rutaFichero) {
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			Equipo equipo = null;
			ArrayList<Equipo> equipos = new ArrayList<Equipo>();
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				equipo = new Equipo(Integer.parseInt(campos[0]), campos[1], campos[2]);
				equipo.setGc(0);
				equipo.setGf(0);
				equipo.setPe(0);
				equipo.setPg(0);
				equipo.setPp(0);
				equipo.setPuntos(0);
				equipos.add(equipo);
			}
			fichero.close();
			System.out.println("Fin de la lectura del fichero");
			return equipos;

		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		return null;
	}

	// 22 enero 2019

	public HashMap<String, Integer> comprobarPartidos(String rutaFichero) {
		try {
			HashMap<String, Integer> mapaEquipos;
			mapaEquipos = new HashMap<String, Integer>();
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				for (int i = 2; i < campos.length; i += 2) {
					if (mapaEquipos.containsKey(campos[i])) {
						mapaEquipos.replace(campos[i], (mapaEquipos.get(campos[i]) + 1));
					} else {
						mapaEquipos.put(campos[i], 1);
					}
				}

			}
			fichero.close();
			return mapaEquipos;

		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		return null;
	}

	// 14 enero 2019

	public ArrayList<Persona> creaListaPersonas(String rutaFichero, String separador) {
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));

			ArrayList<Persona> listaPersonas;
			listaPersonas = new ArrayList<Persona>();

			String registro;
			Persona persona;
			while ((registro = fichero.readLine()) != null) {
				// System.out.println(registro);

				// romper la cadena registro
				String[] campos = registro.split(separador);
				for (int i = 0; i < campos.length; i++)
					System.out.print(campos[i] + " , ");
				System.out.println("");

				// crear objeto de la clase Persona
				// a�adirlo a la listaPersonas
				persona = new Persona(campos[0], campos[1], Integer.parseInt(campos[2]), campos[3],
						campos[4].charAt(0));

				listaPersonas.add(persona);

			}
			fichero.close();
			System.out.println("Creada la lista de personas...");

			return listaPersonas;

		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		return null;
	}
	// 10 enero 2019

	public void leerFichero(String rutaFichero) {
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			while ((registro = fichero.readLine()) != null) {
				System.out.println(registro);

				// ................

			}
			fichero.close();
			System.out.println("Fin de la lectura del fichero");

		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
	}

	// 9/1/2019
	public void introMapas() {
		// DECLARAR EL MAPA (HashMap) que almacena
		// objetos de la clase Estudiante, La clave es el nif..
		// INICIALIZAR EL MAPA
		// INSERTAR (put) una serie de estudiantes.
		HashMap<String, Estudiante> mapa;

		mapa = new HashMap<String, Estudiante>();
		Estudiante est1 = new Estudiante("123", "pepe", 124, "19991001", 'M');

		mapa.put(est1.getNif(), est1);
		mapa.put("12345678F", new Estudiante());
		mapa.put("3", new Estudiante("3", "Maria", 135, "19850915", 'F'));
		mapa.put("001", new Estudiante("001", "Isabel", 135, "20121121", 'F'));
		if (!mapa.containsKey("3"))
			mapa.put("3", null);

		// recorrer mapa

		Set<String> clavesMapa = mapa.keySet();

		for (String clave : clavesMapa)
			System.out.println(mapa.get(clave).getNombre());

		System.out.println("fin mapa");
	}

	// 18 diciembre 18

	public void introListas() {

		ArrayList<Object> listaGenerica = new ArrayList<Object>();
		// System.out.println("Lista generica tiene " + listaGenerica.size());

		listaGenerica.add("Gran Canaria");
		String nombre = "Pepe";
		listaGenerica.add(nombre);
		listaGenerica.add(123);
		listaGenerica.add(Math.PI);
		listaGenerica.add(123.5f);
		listaGenerica.add(true);
		listaGenerica.add(new Persona());

		// for (Object elemento: listaGenerica)
		// System.out.println(elemento);

		for (int i = 0; i < listaGenerica.size(); i++)
			System.out.println(listaGenerica.get(i));

		// System.out.println("Lista generica tiene " + listaGenerica.size());

		ArrayList<Persona> listaPersonas;

		listaPersonas = new ArrayList<Persona>();

		Persona persona1 = new Persona();

		listaPersonas.add(persona1);

		listaPersonas.add(new Persona());

		listaPersonas.add(new Persona("44321654F", "Pepe", 145, "19790125", 'M'));

		listaPersonas.add(1, new Persona("nuevoNif", "Pepe", 145, "20001021", 'M'));

		// System.out.println(listaPersonas.get(1).getNombre());

		System.out.println("fin listas");
	}

	// 13 dic 2018 Intro a la Herencia

	// crear un Estudiante

	public void crearEstudiante() {

		Estudiante estudiante = new Estudiante("43781230V", "Pedro Garcia", 153, "19971225", 'M');

	}

	private Persona[] personas;

	/*
	 * public void hijosPersonas() { for (int i = 0; i < personas.length; i++) {
	 * Persona[] hijos = personas[i].getHijosBiologicos();
	 * System.out.println("PROGENITOR -> " + personas[i].getNombre()); if ( hijos !=
	 * null) { for (int j = 0; j < hijos.length; j++) {
	 * System.out.println("Hijo -> " + hijos[j].getNombre()); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 */
	// 27 noviembre 18

	public int[] matrizToArrayOrdenado(int[][] matriz) {
		int[] result = new int[1];
		ordenaFilasMatriz(matriz);
		for (int i = 0; i < matriz.length; i++)
			// Se crea un nuevo array con la uni�n de matriz[i] y result
			// Y se guarda en result
			result = mezclaListasOrdenadas(result, matriz[i]);
		return result;

	}

	// 21 noviembre 2018

	public void ordenaFilasMatriz(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++)
			ordenaArrayNumeros(matriz[i]);
	}

	// 20 noviembre 2018

	// Ejercicios con arrays

	// 1. Dada una lista (array) de enteros (int), obtener la lista INVERTIDA sobre
	// si misma

	public void invertirLista(int[] lista) {
		int aux;
		for (int i = 0; i < lista.length / 2; i++) {
			aux = lista[i];
			lista[i] = lista[lista.length - i - 1];
			lista[lista.length - i - 1] = aux;

		}

	}

	public int[] invertirLista2(int[] lista) {
		int[] resultado = new int[lista.length];
		for (int i = 0; i < lista.length; i++)
			resultado[lista.length - 1 - i] = lista[i];
		return resultado;
	}

	// 2. Dadas dos listas PREVIAMENTE ORDENADAS, se pide obtener la lista MEZCLA de
	// ambas

	public int[] mezclaListasOrdenadas(int[] l1, int[] l2) {
		int[] resultado = new int[l1.length + l2.length];
		int i = 0;
		int j = 0;
		int k = 0;
		while (k < l1.length + l2.length) {
			try {
				if (l1[i] < l2[j]) {
					resultado[k] = l1[i];
					i++;
				} else {
					resultado[k] = l2[j];
					j++;
				}
				k++;
			} catch (ArrayIndexOutOfBoundsException excepcion) {
				if (i == l1.length) // fin de l1..
					l1[--i] = Integer.MAX_VALUE;
				else
					l2[--j] = Integer.MAX_VALUE;
			}
		}
		return resultado;
	}

	// 3. Dada una cadena , obtener la cadena INVIRTIENDO sus caracteres (char)
	// usar charAt() o toCharArray()

	public String ivertirCaracteres(String cadena) {
		return cadena;

	}

	// 14 noviembre 2018
	public int[] sumaColumnasMatrizHeterogenea(int[][] matriz) {

		int numColMAX = 0;
		for (int i = 0; i < matriz.length; i++)
			if (matriz[i].length > numColMAX)
				numColMAX = matriz[i].length;

		int[] resultado = new int[numColMAX];
		// recorrer matriz por COLUMNAS

		for (int j = 0; j < numColMAX; j++) {
			for (int i = 0; i < matriz.length; i++)
				try {
					resultado[j] += matriz[i][j];
				} catch (ArrayIndexOutOfBoundsException e) {

				}
		}
		return resultado;
	}

	public void ordenaArrayCadenas(String[] cadenas) {
		for (int i = 0; i < cadenas.length - 1; i++)
			for (int j = i + 1; j < cadenas.length; j++)
				if (cadenas[i].compareTo(cadenas[j]) < 0) {
					String aux = cadenas[i];
					cadenas[i] = cadenas[j];
					cadenas[j] = aux;
				}
	}

	// 13 nov 2018
	public void ordenaArrayNumeros(int[] a) {
		for (int i = 0; i < a.length - 1; i++)
			for (int j = i + 1; j < a.length; j++)
				if (a[i] > a[j]) {
					int aux = a[i];
					a[i] = a[j];
					a[j] = aux;
				}
	}

	public void listaDesgloseMoneda(int cantidad) {
		System.out.println("CANTIDAD : " + cantidad);
		int[] monedas = { 500, 200, 100, 50, 20, 10, 5, 2, 1 };
		int[] resultado = desgloseMoneda(cantidad);
		for (int i = 0; i < resultado.length; i++) {
			if (resultado[i] != 0)
				System.out.println(monedas[i] + " -> " + resultado[i]);
		}

	}

	public int[] desgloseMoneda(int cantidad) {
		int[] monedas = { 500, 200, 100, 50, 20, 10, 5, 2, 1 };
		int[] resultado = new int[monedas.length];
		// bucle
		int indice = 0;
		while (cantidad % monedas[indice] != 0) {
			resultado[indice] = cantidad / monedas[indice];
			cantidad = cantidad % monedas[indice];
			indice++;
		}

		// Para que cuando el resto sea 0 coja el valor
		resultado[indice] = cantidad / monedas[indice];

		return resultado;
	}

	// 8 noviembre 2018

	public boolean esPrimo(int n) {
		for (int i = 2; i < n; i++)
			if (n % i == 0)
				return false;
		return true;
	}

	public void listarPrimos(int desde, int hasta) {

		for (int i = desde; i < hasta; i++) {
			if (esPrimo(i))
				System.out.print(i + ", ");
		}
	}

	public void listarPrimos2(int desde, int cuantos) {
		int contador = 0;
		while (contador < cuantos)
			if (esPrimo(desde++)) {
				System.out.print(desde + ", ");
				contador++;
			}
	}

	public int[] listarPrimos3(int desde, int cuantos) {
		int[] resultado = new int[cuantos];
		int contador = 0;
		while (contador < cuantos)
			if (esPrimo(desde)) {
				resultado[contador] = desde++;
				contador++;
			}
		return resultado;
	}

	// 7 noviembre 2018
	// 3 bucles anidados for , un reloj

	public void mostrarHMS() {
		for (int h = 0; h < 1; h++)
			for (int m = 0; m < 60; m++)
				for (int s = 0; s < 60; s++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(h + " : " + m + " : " + s);
				}
	}

	// 25/10/18

	public void pruebaCadenas() {

		String nombre = "LAS PALMAS DE GRAN CANARIA";
		// iterar en la cadena mostrando sus caracteres
		// for (int i = 0; i < nombre.length(); i++)
		// System.out.println(nombre.charAt(i));

		String c1 = "abcd";
		String c2 = "abcdaaaaaaaaa";
		System.out.println(c1.compareTo(c2));

	}

	public float calcularSaldoFinal(float saldoInicial, float[] movimientos) {

		float saldoFinal = saldoInicial;
		// recorrer el array movimientos
		// sumando dichas cantidades a saldoFinal.
		for (int i = 0; i < movimientos.length; i++)
			saldoFinal += movimientos[i];
		return saldoFinal;
	}

	// 23/10/18

	// metodo para devolver una lista de n p�rsonas

	Persona[] creaListaPersonas(int n) {
		Persona[] lista = null;

		lista = new Persona[n];
		for (int i = 0; i < lista.length; i++)
			lista[i] = new Persona();
		return lista;
	}

	// --------------- 04/10/2018 ACTIVIDAD: BUSCAR EN LA API
	// -------------------------------

	public void pruebasAPI() {
		/*
		 * 1. imprime por consola el valor de 2^3 - Usa la clase est�tica Math y su
		 * m�todo pow (Cuando es est�tico no hace falta el new)
		 * 
		 * 2. Imprime un valor aleatorio entre 1 y 100 - Usa la clase java.util.Random y
		 * el m�todo rnd 3. En la cadena "LAS PALMAS DE GRAN CANARIA", imprime "GRAN"
		 * -Usa el m�todo substring de la clase java.lang.String
		 */

		// 1.
		System.out.println("1. " + Math.pow(2, 3));

		// 2.
		Random rnd = new Random();
		System.out.println("2. " + rnd.nextInt(100));

		// 3.
		System.out.println("3. " + "LAS PALMAS DE GRAN CANARIA".substring(14, 18));
	}

	// ACTIVIDAD: Mostrar por consola los n�meros comprendidos entre dos
	// enteros, a y b

	// 1Parte m�todo:. prototipo o cabecera
	public void listaIntervaloEnteros(int primero, int ultimo) {
		// 2Parte m�todo: cuerpo o implementaci�n del m�todo
		/*
		 * int i=primero; while(i < ultimo) System.out.println(++i); i++; i=i+1;
		 */
		for (int j = primero; j <= ultimo; j++)
			System.out.println(j++);

		// true /false
		boolean condicion = true;
		if (condicion) {
			// en caso de verdadero
		} else {
			// la otra opci�n, falso
		}

	}

	// ---------------------- ACTIVIDAD
	// ----------------------------------------------------- 10/10/2018
	/*
	 * Dado una �cadena� (String) devolver el valor en forma de n�mero entero
	 * correspondiente. Ejempo String: �ab95f1�
	 */

	// String cadena ="ab95f1";
	/*
	 * public static int converString(String cadena) { int numeros =
	 * Integer.parseInt(cadena); return numeros; }
	 */

	// ---------------------- ACTIVIDAD : Serie Fibonacci
	// --------------------------------------- 11/10/2018

	public static void serieFibonacci(int cuantos) {
		int num1 = 0;
		int num2 = 1;
		int total = 1;

		System.out.print(num1 + " ," + num2);
		for (int i = 1; i < cuantos; i++) {
			total = num1 + num2;
			System.out.print(" ," + total);
			num1 = num2;
			num2 = total;
		}
	}

	// ---------------------- ACTIVIDAD : Factorial (1.-Deberes
	// Casa)-------------------------------- 11/10/2018

	public static void factorial(int numFact) {
		int num1 = 1;
		if (numFact == 0 | numFact == 1) {
			System.out.println("El factorial de " + "'" + numFact + "'" + " es :  1");
		} else {
			for (int i = 2; i <= numFact; i++) {
				num1 = num1 * i;
			}
			System.out.println("El factorial de " + "'" + numFact + "'" + " es :" + num1);
		}
	}

	// ---------------------- ACTIVIDAD : Devolver el n�mero menor de tres
	// (2.-Deberes Casa)----------- 11/10/2018

	public int calNumMenor(int x, int y, int z) {
		if (x < y)
			if (x < z)
				return x;
			else
				return z;
		else if (y < z)
			return y;
		else
			return z;

	}

	public int calNumMenor(int x, int y, int z, int w) {
		if (x < y)
			if (x < z)
				if (x < w)
					return x;
				else
					return w;

			else if (z < w)
				return z;
			else
				return w;

		else if (y < z)
			if (y < w)
				return y;
			else
				return w;
		else if (z < w)
			return z;
		else
			return w;

	}

	// IMPRIME POR CONSOLA n n�meros enteros aleatorios
	// entre inferior y superior

	public void imprimeAleatorios(int n, int inferior, int superior) // n,
																		// cuantos
																		// numeros
	{
		Random rnd = new Random();
		int numero = 0;
		for (int i = 0; i < n; i++) {
			numero = inferior + rnd.nextInt(superior - inferior + 1);
			System.out.println(i + 1 + ".- " + numero);
		}
	}

	// Devuelve un array de n�meros enteros aleatorios, basandonos en el m�todo
	// anterior
	public int[] generaListaAleatorios(int n, int inferior, int superior) {
		int[] resultado; // DECLARACION EN LA TABLA DE SIMBOLOS
		resultado = new int[n]; // INICIALIAZACION
		Random rnd = new Random();
		int numero = 0;
		for (int i = 0; i < n; i++)
			resultado[i] = inferior + rnd.nextInt(superior - inferior + 1);
		return resultado;
	}

	// 30 octubre 2018
	public int sumaAleatorios(int n, int inferior, int superior) {
		int resultado; // DECLARACION EN LA TABLA DE SIMBOLOS
		resultado = 0; // INICIALIAZACION
		Random rnd = new Random();
		int numero = 0;
		for (int i = 0; i < n; i++)
			resultado += inferior + rnd.nextInt(superior - inferior + 1);
		return resultado / n;
	}

	public int[] generaEstadisticaAparicion(int n, int inferior, int superior) {
		{
			int[] resultado; // DECLARACION EN LA TABLA DE SIMBOLOS
			resultado = new int[superior - inferior + 1]; // INICIALIAZACION
			Random rnd = new Random();
			int numero = 0;
			for (int i = 0; i < n; i++) {
				numero = inferior + rnd.nextInt(superior - inferior + 1);
				resultado[numero - 1]++;
			}
			return resultado;
		}
	}

	public float[] resumenVendedor(float[][] ventas) {
		// float acumVendedor = 0;
		float[] resultado = new float[ventas.length];
		// iterar por filas/columnas acumulando ventas de cada vendedor en un
		// a�o
		for (int i = 0; i < ventas.length; i++) {
			// acumVendedor = 0;
			for (int j = 0; j < ventas[i].length; j++)
				resultado[i] += ventas[i][j]; // acumVendedor += ventas[i][j];;
			// resultado[i] = acumVendedor;
		}
		return resultado;
	}

	public void mostrarVentasVendedor() {
		String[] nombresVendedor = { "JUAN CARLOS", "ISABEL", "MARTA" };
		float[][] ventasYear = {
				// hay 3 filas (vendedores) y 12 columnas (meses)
				{ 12.5f, 13.5f, 8.5f, 5.0f, 10.5f, 9.5f, 20.5f, 10.5f, 4.0f, 6.5f, 5.5f, 6.0f },
				{ 10.5f, 15.5f, 3.5f, 2.0f, 10.5f, 15.5f, 2.5f, 10.5f, 4.0f, 6.5f, 5.5f, 6.0f },
				{ 11.5f, 10.5f, 4.5f, 1.0f, 9.5f, 5.5f, 3.5f, 10.5f, 4.0f, 6.5f, 5.5f, 6.0f }

		};
		float[] ventas = resumenVendedor(ventasYear);
		for (int i = 0; i < ventas.length; i++)
			System.out.println(nombresVendedor[i] + " : " + ventas[i]);

	}

	public float[] resumenVentasPorMes(float[][] ventas) {
		// float acumVendedor = 0;
		float[] resultado = new float[ventas[0].length];
		// iterar por filas/columnas acumulando ventas de cada vendedor en un
		// a�o
		for (int j = 0; j < ventas[0].length; j++) {
			// acumVendedor = 0;
			for (int i = 0; i < ventas.length; i++)
				resultado[j] += ventas[i][j]; // acumVendedor += ventas[i][j];;

		}
		return resultado;
	}

	public int[] convierteCadenas(String[] cadenas) {
		// el array de salida tiene el MISMO numero
		// de elementos que el de entrada
		// si un numero no es valido, se pone -1.
		int[] resultado = new int[cadenas.length];
		for (int i = 0; i < cadenas.length; i++)
			try {
				resultado[i] = Integer.parseInt(cadenas[i]);
				System.out.println("LLega aqui");
			} catch (NumberFormatException e) {
				resultado[i] = -1;
			}

		return resultado;
	}

	public static void main(String[] args) {
		Ejercicios ejercicios = new Ejercicios();
		
		BaseDatos bd = new BaseDatos("jdbc:mysql://" + "localhost:3306/", "losalmacenes", "root", "");
		//Para comprobar si el driver entra correctamente
		
		// System.out.println(bd.getConexion());
		// System.out.println(bd.getSt());
		
		
		
		/*****************INICIO PRACTICA RECURSIVIDAD*********************/
		//  File inputFolder = new File("C:\\Users");
		//  traverse(inputFolder, "");
		/******************************************************************/
			
		/***************INICIO PRACTICAS EVALUACION************************/
		// ArrayList<Equipo> listadoEquipos = ejercicios.crearListaEquipo("ficheros/equipos.txt");
		// System.out.println(listadoEquipos);
		//ArrayList<Equipo> eqOrdenados = ejercicios.equiposListaOrdenadaNombre("ficheros/equipos.txt");
		//ejercicios.buscarEquipo("RMA", eqOrdenados);
		//int[] busquedaBinaria = {1,2,3,4,5,6,7,8,9,10};
		//int ejercicioBusquedaBi = ejercicios.busquedaBinaria(busquedaBinaria, 5);
		// System.out.println(ejercicioBusquedaBi);
		ArrayList<Jugador> listaJugadores = ejercicios.creaListaJugadores("ficheros/jugadoresJordan.txt");
		HashMap<String, Jugador> mapaJugadores = ejercicios.creaMapaJugadores("ficheros/jugadores.txt");
		// System.out.println(listaJugadores);
		// System.out.println(mapaJugadores);
		// ejercicios.ordenarListaJugadores(listaJugadores);
		// ejercicios.ordenarMapaJugadores(mapaJugadores); // revisar
		/***************************************/
		// ejercicios.crearJugadoresPorEquipo();
		// ejercicios.mostrarListadoJugadoresEquipo("ficheros/jugadoresEquipos.obj");
		// HashMap<Integer, Jugador> mapaJugadores = ejercicios.crearMapaJugadores("ficheros/jugadores.txt");
		// System.out.println(mapaJugadores);
		// ejercicios.creaFicheroObjetoEquipos();
		// ejercicios.mostrarFicheroObjetoEquipo("ficheros/equipos.obj");
		// ejercicios.grabarTiradasDado(10);
		// HashMap<String, ArrayList<Integer>> resultados =
		// ejercicios.resultadosEquipos("ficheros/partidos.txt");
		// HashMap<String, Integer> puntosEquipos =
		// ejercicios.generaPuntosEquipos(resultados);
		// ejercicios.muestraClasificacion();
		// ejercicios.entradaTecladoAFichero("ficheros/teclado.txt");

		// ejercicios.ordenarMapaPuntosEquipos(puntosEquipos);

		// ejercicios.pruebaSWING();
		// ejercicios.introListas();
		// ejercicios.introMapas();
		// ejercicios.leerFichero("ficheros/datos.txt");
		// ArrayList<Persona> listaPersonas =
		// ejercicios.creaListaPersonas("ficheros/personas.txt", "##");
		// HashMap<String, Integer> equipos =
		// HashMap<String, Integer> x =
		// HashMap<String, Integer> x =
		// ejercicios.comprobarPartidos("ficheros/partidos.txt");
		// ArrayList<Equipo> equipos =
		// ArrayList<Equipo> listaEquipos =
		// ejercicios.crearListaEquipos("ficheros/equipos.txt");
		// HashMap<String, Equipo> equipos =
		// ejercicios.crearMapaEquipos("ficheros/equipos.txt");
		// System.out.println(equipos);
		// ejercicios.mostrarNumeroPartidosJugadosTry("ficheros/partidos.txt");
		// HashMap<String, ArrayList<Integer>> x =
		// ejercicios.resultadosEquipos("ficheros/partidos.txt");

		// ejercicios.muestraPuntosEquipos(x);

		// System.exit(0); // finaliza el programa ...

		int[] lista1 = { 2, 4, 8, 9 };
		int[] lista2 = { 1, 3, 5, 23, 45, 67, 89, 123 };

		// int [] listasMezcladas = ejercicios.mezclaListasOrdenadas(lista1, lista2);
		// ejercicios.invertirLista(lista2);
		// int[] listaInvertida = ejercicios.invertirLista2(lista2);
		// ejercicios.crearEstudiante();

		int[][] datos = { { 7, 4, 6 }, { 6 }, { 5, 2, 3 }, { 4, 2, 5, 1, 9, 0, 3 }, { 5, 6, 1, 3 } };

		int[] vector = ejercicios.matrizToArrayOrdenado(datos);
		// ejercicios.ordenaFilasMatriz(datos);

		int[] acumuladoColumnas = ejercicios.sumaColumnasMatrizHeterogenea(datos);

		// int[] x = {78,34,1,34,54,29,3};
		// String[] nombres = { "carlos", "maria", "luis", "nuria", "roberto",
		// "alejandro" };
		// ejercicios.ordenaArrayCadenas(nombres);
		// int[] lista = ejercicios.generaListaAleatorios(1000, 1, 5000);
		// ejercicios.ordenaArrayNumeros(lista);
		// int[] monedas = ejercicios.desgloseMoneda(1234);
		// ejercicios.listaDesgloseMoneda(3);
		// System.out.println(ejercicios.esPrimo(99525));

		/*
		 * int inicio = 500; int cuantos = 5; ejercicios.listarPrimos2(inicio, cuantos);
		 */
		// int[] numeros = ejercicios.listarPrimos3(inicio, cuantos);
		// ejercicios.mostrarHMS();
		// String[] datos = { "123", "8k8", "9811", "xyz" };
		// int[] numeros = ejercicios.convierteCadenas(datos);

		/*
		 * float[][] ventasYear = { // hay 3 filas (vendedores) y 12 columnas (meses) {
		 * 12.5f, 13.5f, 8.5f, 5.0f, 10.5f, 9.5f, 20.5f, 10.5f, 4.0f, 6.5f, 5.5f, 6.0f
		 * }, { 10.5f, 15.5f, 3.5f, 2.0f, 10.5f, 15.5f, 2.5f, 10.5f, 4.0f, 6.5f, 5.5f,
		 * 6.0f }, { 11.5f, 10.5f, 4.5f, 1.0f, 9.5f, 5.5f, 3.5f, 10.5f, 4.0f, 6.5f,
		 * 5.5f, 6.0f }
		 * 
		 * };
		 */
		// float[] resumenVendedor;
		// resumenVendedor = ejercicios.resumenVendedor(ventasYear);
		// float[] reumenPorMes = ejercicios.resumenVentasPorMes(ventasYear);
		// crear e inicializar un array, varias opciones.
		// float[] movs = { 10.0f, -5.0f, 20.0f, -10.0f };
		// float saldoI = 100.0f;
		// ejercicios.pruebaCadenas();
		// System.out.println("Saldo final :"
		// + ejercicios.calcularSaldoFinal(saldoI, movs));
		// String[] diasSemana = {"LUNES", "MARTES"};
		/*
		 * movs = new float[4];
		 * 
		 * movs[0] = 10.0f; movs[1] = -5.0f; movs[2] = 20.5f; movs[3] = -10.5f;
		 */

		// int a = 10, b = 17, c = 14, d = 11;
		// int menor= Ejercicios.calNumMenor(a, b, c, d);
		// int menor = ejercicios.calNumMenor(a, b, c, d);
		// System.out.println("el menor es : " + menor);

		// ejercicios.imprimeAleatorios(6, 1, 6);
		// int[] estadisticaDados = ejercicios.generaEstadisticaAparicion(1000, 1, 6);

		// Persona[] listaPersonas = ejercicios.creaListaPersonas(10);
		// factorial(5);

		// serieFibonacci(8);

		// Act: 10/10/2018
		// System.out.println(converString("8fgf"));

		/*
		 * Ejercicios ejercicios = new Ejercicios(); int x=10; int y= 30;
		 * ejercicios.listaIntervaloEnteros(x, y);
		 */

		// Ejercicios.pruebasAPI();
		// new Ejercicios().pruebasAPI();

		// Persona juan = new Persona();
		// int pasos = juan.caminar(20);

		// Persona juan;
		// juan = new Persona("4545455X", "Juan Luis", 47,null);
		// Persona persona1 = new Persona();

		System.out.println("Fin del programa");
	}

}
