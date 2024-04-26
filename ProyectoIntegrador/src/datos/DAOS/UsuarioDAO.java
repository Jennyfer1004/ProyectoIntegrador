package datos.DAOS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.ConexionDB;

public class UsuarioDAO {
	
	
    private ConexionDB conexionDb;

    public UsuarioDAO() {
        this.conexionDb = new ConexionDB();
    }

	public boolean validarCredenciales(String usuario, String contrasena, String tipoUsuario) throws SQLException {
    	PreparedStatement ps = null;
    	ResultSet rs = null;
        try {
        	ps = this.conexionDb.obtenerConexion().prepareStatement("SELECT * FROM usuario WHERE usuario_dado = ? AND contrasena = ? AND rol = ?");
        	ps.setString(1, usuario);
        	ps.setString(2, contrasena);
        	ps.setString(3, tipoUsuario);
        	rs = ps.executeQuery();

        	if (rs.next()) {
                // Las credenciales son válidas
                return true;
            } else {
                // Las credenciales son incorrectas
                return false;
            }
        } finally {
            // Cerrar recursos
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            this.conexionDb.obtenerConexion().close();
        }
    }
        
}

	
  
