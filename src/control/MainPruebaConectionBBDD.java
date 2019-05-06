package control;

public class MainPruebaConectionBBDD {

	public static void main(String[] args) {
		BaseDatos bd = new BaseDatos("jdbc:mysql://" + "localhost:3306/", "losalmacenes", "root", "");
		
		System.out.println(bd.getConexion());
		System.out.println(bd.getSt());
	
		
		System.out.println("Fin del programa");
		System.exit(0);
	}
	
	
	
	
	
}
