package datos.objetos;

public class Usuarios {
	
	static String usuario;
	static String contrasena;
	static String rol;
	
	public static void inicializar(String user, String pass, String role) {
        usuario = user;
        contrasena = pass;
        rol = role;
    }

	public static String getUsuario() {
		return usuario;
	}

	public static void setUsuario(String usuario) {
		Usuarios.usuario = usuario;
	}

	public static String getContrasena() {
		return contrasena;
	}

	public static void setContrasena(String contrasena) {
		Usuarios.contrasena = contrasena;
	}

	public static String getRol() {
		return rol;
	}

	public static void setRol(String rol) {
		Usuarios.rol = rol;
	}	
	

}
