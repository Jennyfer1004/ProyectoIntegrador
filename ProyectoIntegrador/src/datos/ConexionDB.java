package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	// string de conexion a la base de datos stylebiker
	private String stringConexion = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String username = "STYLEBIKER";
	private String password = "STYLEBIKER";
	
	//metodo para obtener la conexion a la base de datos
	public Connection obtenerConexion() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.stringConexion, this.username, this.password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return conn;
		
	}
	
	
	public void cerrarConexion(Connection conn) {
	    if (conn != null) {
	        try {
	            conn.close();
	        } catch (SQLException e) {
	        	// TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	}
	
}
