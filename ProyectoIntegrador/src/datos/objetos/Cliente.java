package datos.objetos;

public class Cliente {
    private String cedula;
    private String nombreCompleto;
    private String correo;
    private String telefono;
    private String direccion;
    private String estado;
    private boolean editado;

    public Cliente(String cedula, String nombreCompleto, String correo, String telefono, String direccion, String estado) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
        this.editado = false; // Inicialmente el cliente no ha sido editado
    }
    public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public boolean isEditado() {
        return editado;
    }

    public void setEditado(boolean editado) {
        this.editado = editado;
    }
}