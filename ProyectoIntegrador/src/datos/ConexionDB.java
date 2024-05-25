package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	// string de conexion a la base de datos stylebiker
	private String stringConexion = "jdbc:oracle:thin:@127.0.0.1:1521:XE";

	// conexion para el login
	public Connection obtenerConexionPrincipal() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.stringConexion, "STYLEBIKER","STYLEBIKER");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return conn;
		
	}
	
	public Connection obtenerConexionAdmin() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.stringConexion, "admint", "admint");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

    }

	public Connection obtenerConexionGerente() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.stringConexion, "manager", "manager");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;

    }
	public Connection obtenerConexionVendedor() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.stringConexion, "seller", "seller");
			
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
