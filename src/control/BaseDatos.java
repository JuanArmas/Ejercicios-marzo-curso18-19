package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {
	// build path - library - indicar la ruta del archivo de la libreria
	// desde dentro del proyecto se usara jdbc -> java.sql
	// propiedades seran visibles en toda la bbdd---> host - dbname(schema) - dbuser - dbpasword
	// host donde esta la bbdd, el servidor, donde esta y que puerto escucha
	// clases jdbc 
		// clase connection
	
	private String host;
	private String dbname;
	private String dbuser;
	private String dbpassword;
	private Connection conexion;
	private Statement st = null;

	public BaseDatos(String host, String dbname, String dbuser, String dbpassword) {
		super();
		this.host = host;
		this.dbname = dbname;
		this.dbuser = dbuser;
		this.dbpassword = dbpassword;
		
		try {      // Cadena de conexión al driver de la base de datos
        	Class.forName("com.mysql.cj.jdbc.Driver");           	

         // Base de datos? &K1=V1 & K2=V2 & K3 = V3   	 
         this.conexion = DriverManager.getConnection(host + dbname,  dbuser, dbpassword);  
					// localhost puede ser la direccion IP		
			System.out.println("Driver encontrado");
			if(conexion != null){
				st = conexion.createStatement ();
				System.out.println(" Conexion a base de datos " +"'" + dbname +"'"+ " correcta .");
			} else
			System.out.println(" Conexion fallida .");
		} catch (SQLException e){
			System.out.println(e.getMessage()); //muestra el error que se esta capturando
		} catch (ClassNotFoundException e){
			System.out.println("Clase no encontrada");
		}   	
	}

	public BaseDatos() {
		super();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getDbuser() {
		return dbuser;
	}

	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}

	public String getDbpassword() {
		return dbpassword;
	}

	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}
	
	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

}
