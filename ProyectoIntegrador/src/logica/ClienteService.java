package logica;

import java.util.LinkedList;

import datos.DAOS.ClienteDAO;
import datos.objetos.Cliente;
import datos.objetos.Usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClienteService {
    
    public ClienteDAO clienteDAO;
    ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    /**
     * Constructor de la clase ClienteService.
     */
    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }
    
    /**
     * Obtiene la lista de clientes desde la base de datos.
     * @return Una lista observable de clientes.
     */
    public ObservableList<Cliente> getClientes() {
    	
    	//String tipoUsuario = "administrador"; // o cualquier otro tipo de usuario que desees obtener
    	String tipoUsuario = Usuarios.getRol();
    	LinkedList<Cliente> data1 = clienteDAO.getDatos();
        for(Cliente c: data1) {
            this.clientes.add(c);
        }
        return this.clientes;
    }
    
    /**
     * Agrega un nuevo cliente.
     * @param cedula Cédula del cliente.
     * @param nombreCompleto Nombre completo del cliente.
     * @param correo Correo electrónico del cliente.
     * @param telefono Teléfono del cliente.
     * @param direccion Dirección del cliente.
     */
    public void agregarCliente(String cedula, String nombreCompleto, String correo, String telefono, String direccion, String estado) {
        Cliente nuevoCliente = new Cliente(cedula, nombreCompleto, correo, telefono, direccion, estado);
        this.clientes.add(nuevoCliente);
    }
    
    /**
     * Elimina un cliente.
     * @param cliente Cliente a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     */
    public boolean eliminarCliente(Cliente cliente) {
        return clienteDAO.eliminarCliente(cliente.getCedula());
    }
    
    /**
     * Edita un cliente.
     * @param cliente Cliente a editar.
     * @return true si se editó correctamente, false de lo contrario.
     */
    public boolean editarCliente(Cliente cliente) {
        return clienteDAO.editarCliente(cliente);
    }
    
    /**
     * Busca clientes por nombre o cédula.
     * @param parametro Nombre o cédula del cliente a buscar.
     * @return Una lista observable de clientes que coinciden con el parámetro de búsqueda.
     */
    public ObservableList<Cliente> buscarCliente(String parametro) {
        LinkedList<Cliente> data1 = clienteDAO.getBuscarCliente(parametro);
        for(Cliente c: data1) {
            this.clientes.add(c);
        }
        return this.clientes;
    }
}
