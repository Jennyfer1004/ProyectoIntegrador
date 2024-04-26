package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	
	private String stringConexion = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private String username = "stylebiker";
	private String password = "stylebiker";
	
	
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
	
}
