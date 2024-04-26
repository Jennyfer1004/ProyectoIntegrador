package logica;

import java.util.LinkedList;

import datos.DAOS.ClienteDAO;
import datos.objetos.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteService {
	
	public ClienteDAO clienteDAO;
	ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }
    
    

    public ObservableList<Cliente> getClientes() {
    	LinkedList<Cliente> data1 = clienteDAO.getDatos();
		for(Cliente c: data1) {
			this.clientes.add(c);
		}
		return this.clientes;
    }
    
    public void agregarCliente(String cedula, String nombreCompleto, String correo, String telefono, String direccion) {
        Cliente nuevoCliente = new Cliente(cedula, nombreCompleto, correo, telefono, direccion);

        this.clientes.add(nuevoCliente);
    }
    
    
    
    public boolean eliminarCliente(Cliente cliente) {
		return clienteDAO.eliminarCliente(cliente.getCedula());
    }
    
    
    
    public boolean editarCliente(Cliente cliente) {
		return clienteDAO.editarCliente(cliente);
    }
    
    public ObservableList<Cliente> buscarCliente(String parametro) {
    	LinkedList<Cliente> data1 = clienteDAO.getBuscarCliente(parametro);
		for(Cliente c: data1) {
			this.clientes.add(c);
		}
		return this.clientes;
    }
	
	
	


}
