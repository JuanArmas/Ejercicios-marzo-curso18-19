package modeloDAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import control.BaseDatos;
import modelo.Equipo;


public class AccesoDatos {
	
	// 30/04/2019 
	// crear bbdd Liga -> dentro crear las tablas equipo, jugadores, partidos
	// coger los ficheros equipos.txt, etc. e introducirlos en las tablas
	
	/*
	 * 		Abrir fichero - ok
			Conectar bbdd - ok
				Recorrer fichero -ok
					Mientras halla registros en el fichero hacer un bucle - ok
					Preparar los campos a insertar - ok
					Preparar la sentencia sql (insert)(se necesitaran las clases Connection, Statement)
					No se va a leer asi que no se usara el executeQuery sino el executeUpdate en el Statement
			Ejecutar sql ()
			Cerrar todo
	 * 
	 * // insert into equipos(id,nombreCorto,nombre) values(1,"RMA","Real Madrid");
	 * // recordar que la consula en vez de executeQuery sera executeUpdate
	 * // inserta registros
	 * */
	
	// ejercicios
	// hacer lo mismo para jugadores y partidos
	// crear las dos tablas y cargarlas
	// recuperar en una lista los jugadores, partidos y equipos
	
	public void pruebaInsertaPartidosDesdeFichero(String rutaPartidos) {
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaPartidos));
			BaseDatos bd = new BaseDatos("localhost:3306", "liga", "root", "");
			Connection conexion = bd.getConexion();
			Statement stmt = conexion.createStatement();
			
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				int id = Integer.parseInt(campos[0]);
				int jornada = Integer.parseInt(campos[1]);
				String eL = campos[2];
				int gL = Integer.parseInt(campos[3]);
				String eV = campos[4];
				int gV = Integer.parseInt(campos[5]);
				
				String sql = "insert into partidos(id,jornada,eL,gL,eV,GV) values";
				sql+= "(" + id + "," + jornada + ",\"" + eL + "\"," + gL + ",\"" + eV + "\"," + gV + ")";
				System.out.println(sql);	
				stmt.executeUpdate(sql);				
			}	
			stmt.close();
			conexion.close();
			fichero.close();
			System.out.println("Fin de la lectura del fichero");
		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (NumberFormatException e) {
			
		}
	}
	
	public void insertaPartidosDesdeFichero(String rutaPartidos) {
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaPartidos));
			BaseDatos bd = new BaseDatos("localhost:3306", "liga", "root", "");
			Connection conexion = bd.getConexion();
			Statement stmt = conexion.createStatement();
			
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				int id = Integer.parseInt(campos[0]);
				int jornada = Integer.parseInt(campos[1]);
				String eL = campos[2];
				int gL = Integer.parseInt(campos[3]);
				String eV = campos[4];
				int gV = Integer.parseInt(campos[5]);
				
				String sql = "insert into partidos(id,jornada,eL,gL,eV,GV) values";
				sql+= "(" + id + "," + jornada + ",\"" + eL + "\"," + gL + ",\"" + eV + "\"," + gV + ")";
				System.out.println(sql);	
				stmt.executeUpdate(sql);				
			}	
			stmt.close();
			conexion.close();
			fichero.close();
			System.out.println("Fin de la lectura del fichero");
		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertaJugadoresDesdeFichero(String rutaJugadores) {
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaJugadores));
			BaseDatos bd = new BaseDatos("localhost:3306", "liga", "root", "");
			Connection conexion = bd.getConexion();
			Statement stmt = conexion.createStatement();
			
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				int id = Integer.parseInt(campos[0]);
				String nif = campos[1];
				String nombre = campos[2];
				int longitudPaso = Integer.parseInt(campos[3]);
				String fecha_nac = campos[4];
				char sexo = campos[5].charAt(0);
				int dorsal = Integer.parseInt(campos[6]);
				int idEq = Integer.parseInt(campos[7]);
				
				String sql = "insert into jugadores(id, nif, nombre, longitudPaso, fecha_nac, sexo,  dorsal, idEq) values";
				sql+= "(" + id + ",\"" + nif + "\",\"" + nombre + "\"," + longitudPaso + ",\"" + fecha_nac + "\",\'" + sexo + "\'," + dorsal + "," + idEq +")";
				System.out.println(sql);	
				stmt.executeUpdate(sql);				
			}	
			stmt.close();
			conexion.close();
			fichero.close();
			System.out.println("Fin de la lectura del fichero");
		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertaEquiposDesdeFichero(String rutaEquipos) {
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaEquipos));
			BaseDatos bd = new BaseDatos("localhost:3306", "liga", "root", "");
			Connection conexion = bd.getConexion();
			Statement stmt = conexion.createStatement();
			
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				int id = Integer.parseInt(campos[0]);
				String nombreCorto = campos[1];
				String nombre = campos[2];
				String sql = "insert into equipos(id,nombreCorto,nombre) values";
				sql+= "(" + id + ",\"" + nombreCorto + "\",\"" + nombre + "\")";
				System.out.println(sql);	
				stmt.executeUpdate(sql);				
			}	
			stmt.close();
			conexion.close();
			fichero.close();
			System.out.println("Fin de la lectura del fichero");
		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	// 
	public void recorreTabla(String bddatos, String tabla){
		// mostrar por consola todos los actores de la bbdd sakila2
		// 1- conectar a la bbdd. (por medio de la clase bbdd, obviamente)
		try {
			BaseDatos bd = new BaseDatos("localhost:3306",  bddatos, "root", "");
			Connection conexion = bd.getConexion();
			Statement stmt = conexion.createStatement();
			ResultSet rst = stmt.executeQuery("select * from " + tabla + " where 1;");
			ResultSetMetaData rstMeta = rst.getMetaData(); // 
			rstMeta.getColumnCount(); // dice cuantas columnas hay en la tabla (importante para recorrela sin saber su extension de primera mano)
			// rstMeta.getColumnName(column); // dandole la columna, dará el nombre de la misma
			for(int i = 1; i < rstMeta.getColumnCount(); i++) {
				System.out.print(rstMeta.getColumnName(i) + "\t\t"); //esto dará los nombres de las columnas	
			}
			System.out.println();
			while(rst.next()) { // devuelve una linea de la consulta, es decir, una fila de la tabla
				for(int i = 1; i < rstMeta.getColumnCount(); i++) { // recorre la filas de la tabla y las imprime
					System.out.print(rst.getString(i) + "\t\t");
				}System.out.println();
				// System.out.println(rst.getString(1) + "\t\t" + rst.getString(2));
				// si se leyera cualquier tabla sin saber couantas columnas tiene como se accederia? investigar los metadatos resultset		
			}
			rst.close();
			stmt.close();
			conexion.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<ArrayList<Equipo>> crearListadoEquipos(String bddatos, String tabla){
		ArrayList<ArrayList<Equipo>> listadoEquipos = new ArrayList<ArrayList<Equipo>>();
		try {
			BaseDatos bd = new BaseDatos("localhost:3306",  bddatos, "root", "");
			Connection conexion = bd.getConexion();
			Statement stmt = conexion.createStatement();
			ResultSet rst = stmt.executeQuery("select * from " + tabla + " where 1;");
			ResultSetMetaData rstMeta = rst.getMetaData(); // 
			rstMeta.getColumnCount(); 
			while(rst.next()) { // devuelve una linea de la consulta, es decir, una fila de la tabla
				ArrayList<Equipo> unEquipo = new ArrayList<Equipo>();			
				int id = Integer.parseInt(rst.getString(1));
				String nombreCorto = rst.getString(2);
				String nombre = rst.getString(3);
				int pj = Integer.parseInt(rst.getString(4));
				int puntos = Integer.parseInt(rst.getString(5));
				int pg = Integer.parseInt(rst.getString(6));
				int pe = Integer.parseInt(rst.getString(7));
				int pp = Integer.parseInt(rst.getString(8));
				int gf = Integer.parseInt(rst.getString(9));
				int gc = Integer.parseInt(rst.getString(10));
				Equipo equipo = new Equipo(id, nombreCorto, nombre, pj, puntos, pg, pe, pp, gf, gc);
				unEquipo.add(equipo);
				listadoEquipos.add(unEquipo);			
			}
			for(int i = 0; i < listadoEquipos.size(); i++) {
				ArrayList<Equipo> unEquipo = listadoEquipos.get(i);
				for (int j = 0; j < unEquipo.size(); j++) {
					System.out.println(unEquipo.get(j));
				}
			}
		
			rst.close();
			stmt.close();
			conexion.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return listadoEquipos;
	}
	
	
}
