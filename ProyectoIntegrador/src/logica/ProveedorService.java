package logica;

import java.util.LinkedList;
import datos.DAOS.ProveedorDAO;
import datos.objetos.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProveedorService {

	public ProveedorDAO proveedorDAO;
	ObservableList<Proveedor> proveedores= FXCollections.observableArrayList();
	public ProveedorService() {
		this.proveedorDAO = new ProveedorDAO();
	}
	public ObservableList<Proveedor> getProveedores(){
		LinkedList<Proveedor>data1 = proveedorDAO.getDatos();
		for(Proveedor p: data1 ) {
			this.proveedores.add(p);
		}
		return this.proveedores;
		
	}
	public void agregarProveedor(String id, String nombreEmpresa, String direccion, String telefono, String correo,String productosSuministrados)
	{
	Proveedor nuevoProveedor = new Proveedor(id,nombreEmpresa,direccion,telefono,correo,productosSuministrados);
	this.proveedores.add(nuevoProveedor);
	}
	
	public boolean eliminarProveedor(Proveedor proveedor)
	{
		return proveedorDAO.eliminarProveedor(proveedor.getId());
	}
	
	public boolean editarProveedor(Proveedor proveedor)
	{
		return proveedorDAO.editarProveedor(proveedor);
	}
	
	public ObservableList<Proveedor> buscarProveedor(String parametro)
	{
		LinkedList<Proveedor> data1 = proveedorDAO.getBuscarProveedor(parametro);
		for(Proveedor p: data1 ) {
			this.proveedores.add(p);
		}
		return this.proveedores;
	}
		
}
