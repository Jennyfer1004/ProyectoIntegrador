package datos.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.ConexionDB;

public class Login {
    
    private ConexionDB conexionDb;

    /**
     * Constructor de la clase UsuarioDAO.
     */
    public Login() {
        this.conexionDb = new ConexionDB();
    }

    /**
     * Valida las credenciales de un usuario en la base de datos.
     * @param usuario Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @param tipoUsuario Rol o tipo de usuario.
     * @return true si las credenciales son válidas, false si son incorrectas.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    public boolean validarCredenciales(String usuario, String contrasena, String tipoUsuario) throws SQLException {
        Connection conn = null;
    	PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	conn = this.conexionDb.obtenerConexion();
            ps = conn.prepareStatement("SELECT * FROM usuario WHERE usuario_dado = ? AND contrasena = ? AND rol = ?");
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            ps.setString(3, tipoUsuario);
            rs = ps.executeQuery();
            return rs.next();
          }finally {
            // Cerrar recursos
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            
        }
    }       
}
