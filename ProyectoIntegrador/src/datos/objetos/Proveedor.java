package datos.objetos;

public class Proveedor {
    private String nombreEmpresa;
    private String direccion;
    private String telefono;
    private String correo;
    private String productosSuministrados;
    private String estado;
    private boolean editado;

	public Proveedor(String nombreEmpresa, String direccion, String telefono, String correo, String productosSuministrados, String estado) {
		// TODO Auto-generated constructor stub
        this.nombreEmpresa = nombreEmpresa;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.productosSuministrados = productosSuministrados;
        this.estado = estado;
        this.editado = false; // Inicialmente el proveedor no ha sido editado
	}

	

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getProductosSuministrados() {
		return productosSuministrados;
	}

	public void setProductosSuministrados(String productosSuministrados) {
		this.productosSuministrados = productosSuministrados;
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
