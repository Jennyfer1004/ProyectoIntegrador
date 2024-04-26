package datos.objetos;

public class Proveedor {
	private String id;
    private String nombreEmpresa;
    private String direccion;
    private String telefono;
    private String correo;
    private String productosSuministrados;
    private boolean editado;

	public Proveedor(String id, String nombreEmpresa, String direccion, String telefono, String correo, String productosSuministrados) {
		// TODO Auto-generated constructor stub
		this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.productosSuministrados = productosSuministrados;
        this.editado = false; // Inicialmente el proveedor no ha sido editado
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public boolean isEditado() {
		return editado;
	}

	public void setEditado(boolean editado) {
		this.editado = editado;
	}


	
    
     
}
