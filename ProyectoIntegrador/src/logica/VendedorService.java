package logica;

import java.util.LinkedList;

import datos.DAOS.VendedorDAO;
import datos.objetos.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VendedorService {

	
	public VendedorDAO vendedorDAO;
	ObservableList<Vendedor> vendedores = FXCollections.observableArrayList();

    public VendedorService() {
        this.vendedorDAO = new VendedorDAO();
    }
    
    

    public ObservableList<Vendedor> getVendedores() {
    	LinkedList<Vendedor> data1 = vendedorDAO.getDatos();
		for(Vendedor v: data1) {
			this.vendedores.add(v);
		}
		return this.vendedores;
    }
    
    public void agregarVendedor(String cedula, String nombreCompleto, String correo, String telefono, String direccion) {
        Vendedor nuevoVendedor = new Vendedor(cedula, nombreCompleto, correo, telefono, direccion);

        this.vendedores.add(nuevoVendedor);
    }
    
    
    
    public boolean eliminarVendedor(Vendedor vendedor) {
		return vendedorDAO.eliminarVendedor(vendedor.getCedula());
    }
    
    
    
    public boolean editarVendedor(Vendedor vendedor) {
		return vendedorDAO.editarVendedor(vendedor);
    }
    
    public ObservableList<Vendedor> buscarVendedor(String parametro) {
    	LinkedList<Vendedor> data1 = vendedorDAO.getBuscarVendedor(parametro);
		for(Vendedor v: data1) {
			this.vendedores.add(v);
		}
		return this.vendedores;
    }
}
