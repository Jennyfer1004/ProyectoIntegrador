package datos.objetos;

public class Producto {
	private String codigo;
	private String nombre;
	private String descripcion;
	private String valorCompra;
	private String cantidadStock;
	private String idProveedor;
	private String estado;
	private boolean editado;
	
	public Producto(String codigo, String nombre, String descripcion, String valorCompra, String cantidadStock, String idProveedor, String estado) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valorCompra = valorCompra;
		this.cantidadStock = cantidadStock;
		this.idProveedor = idProveedor;
		this.estado = estado;
		this.editado = false; // Inicialmente el producto no ha sido editado
	}
	
	
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(String valorCompra) {
		this.valorCompra = valorCompra;
	}
	public String getCantidadStock() {
		return cantidadStock;
	}
	public void setCantidadStock(String cantidadStock) {
		this.cantidadStock = cantidadStock;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}
	public boolean isEditado() {
        return editado;
    }

    public void setEditado(boolean editado) {
        this.editado = editado;
    }
	

}
