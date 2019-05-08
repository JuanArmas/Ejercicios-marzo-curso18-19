package modeloDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import control.BaseDatos;

public class IntroducirDatosTabla {

	public static boolean validaLogin(String usuario, String pass) {
		// conectar a bbdd
		// preparar consulta contra liga.usuarios (select)
		// si la bbdd esta bien no puede devolver mas de un registro
		// el resultado es un resultset que puede estar a 0, si no se encuentra
		// si no estuviera el usuario -> como saber si existe o no. lo dejo entrar o no
		// el metodo finalizado habrá que llamarlo desde el controlador del formulario (desde el actionTarget)
		// en el controlador habrá que llamar a este metodo
		
		try {
			BaseDatos bd = new BaseDatos("localhost:3306","liga", "root", "");
			Connection conexion = bd.getConexion();
			Statement stmt = conexion.createStatement();						// mirar los separadores para las variables al meterle las comillas
			ResultSet rst = stmt.executeQuery("select nombre, clave from usuarios where nombre like" + "\"" + 
			usuario + "\"" + "and clave like"+ "\"" + pass +"\";");		
				if (rst != null && rst.next()) {
					System.out.println(rst.getString(1) + " " + rst.getString(2));
				}else {
					System.out.println("Usuario o contraseña incorrecta");
				}
			
			rst.close();
			stmt.close();
			conexion.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	
	
	
	
	
	
	
	
	
	
	
}
