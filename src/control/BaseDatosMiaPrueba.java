package control;


	import java.sql.Connection ;
	import java.sql.DriverManager ;
	import java.sql.SQLException ;
	import java.sql.Statement ;
	
	public class BaseDatosMiaPrueba {
		//atributos de la clase relativos a la bbdd
		private static String db = " Nombre Base de Datos ";
		private static String login = " Usuario de la Base de Datos ";
		private static String password = " Contrasenia ";
		private static String url = " jdbc:mysql:// ip_servidor /" + db ;
		//atributos privados para almacenar la conexion MySQL
		private static Connection connection ;
		private static Statement st = null ;
		
		public BaseDatosMiaPrueba(){
			try {
				// obtener el driver para MySQL
				Class.forName("com.mysql.cj.jdbc.Driver");
				//obtener la conexion
				connection = DriverManager.getConnection (url,login,password );
				//comprobar la conexion correcta
				if(connection != null){
					st = connection.createStatement ();
					System.out.println(" Conexion a base de	datos " + db + " correcta .");
				} else
				System.out.println(" Conexion fallida .");
			} catch( SQLException e) {
			  }
			  catch( ClassNotFoundException e) {
			  }
			  catch( Exception e) {
			  }
		}

		public static String getDb() {
			return db;
		}

		public static void setDb(String db) {
			BaseDatosMiaPrueba.db = db;
		}

		public static String getLogin() {
			return login;
		}

		public static void setLogin(String login) {
			BaseDatosMiaPrueba.login = login;
		}

		public static String getPassword() {
			return password;
		}

		public static void setPassword(String password) {
			BaseDatosMiaPrueba.password = password;
		}

		public static String getUrl() {
			return url;
		}

		public static void setUrl(String url) {
			BaseDatosMiaPrueba.url = url;
		}

		public static Connection getConnection() {
			return connection;
		}

		public static void setConnection(Connection connection) {
			BaseDatosMiaPrueba.connection = connection;
		}

		public static Statement getSt() {
			return st;
		}

		public static void setSt(Statement st) {
			BaseDatosMiaPrueba.st = st;
		}
	}

	/*// copia seguridad enlace a bbdd realizado semana santa 2019
 	//attr clase
 	private String host;
	private String dbname;
	private String dbuser;
	private String dbpassword;
	private Connection conexion;
	private Statement st = null;
	
	//constructor conector con bbdd
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
			// System.out.println(e.getMessage()); //muestra el error que se esta capturando
		} catch (ClassNotFoundException e){
			System.out.println("Clase no encontrada");
		}   	
	}

	public BaseDatos() {
		super();
	}
	*/
