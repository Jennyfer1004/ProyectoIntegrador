package logica;

import java.util.LinkedList;
import datos.DAOS.ProveedorDAO;
import datos.objetos.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProveedorService {

    public ProveedorDAO proveedorDAO;
    ObservableList<Proveedor> proveedores = FXCollections.observableArrayList();

    /**
     * Constructor de la clase ProveedorService.
     */
    public ProveedorService() {
        this.proveedorDAO = new ProveedorDAO();
    }

    /**
     * Obtiene la lista de proveedores desde la base de datos.
     * @return Una lista observable de proveedores.
     */
    public ObservableList<Proveedor> getProveedores() {
        LinkedList<Proveedor> data1 = proveedorDAO.getDatos();
        for (Proveedor p : data1) {
            this.proveedores.add(p);
        }
        return this.proveedores;
    }

    /**
     * Agrega un nuevo proveedor.
     * @param id Identificador del proveedor.
     * @param nombreEmpresa Nombre de la empresa del proveedor.
     * @param direccion Dirección del proveedor.
     * @param telefono Teléfono del proveedor.
     * @param correo Correo electrónico del proveedor.
     * @param productosSuministrados Productos suministrados por el proveedor.
     */
    public void agregarProveedor(String nombreEmpresa, String direccion, String telefono, String correo, String productosSuministrados, String estado) {
        Proveedor nuevoProveedor = new Proveedor(nombreEmpresa, direccion, telefono, correo, productosSuministrados, estado);
        this.proveedores.add(nuevoProveedor);
    }

    /**
     * Elimina un proveedor.
     * @param proveedor Proveedor a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     */
    public boolean eliminarProveedor(String nombre) {
    	return proveedorDAO.eliminarProveedor(nombre);
    }

    /**
     * Edita un proveedor.
     * @param proveedor Proveedor a editar.
     * @return true si se editó correctamente, false de lo contrario.
     */
    public boolean editarProveedor(Proveedor proveedor) {
        return proveedorDAO.editarProveedor(proveedor);
    }

    /**
     * Busca proveedores por nombre o ID.
     * @param parametro Nombre o ID del proveedor a buscar.
     * @return Una lista observable de proveedores que coinciden con el parámetro de búsqueda.
     */
    public ObservableList<Proveedor> buscarProveedor(String parametro) {
        LinkedList<Proveedor> data1 = proveedorDAO.getBuscarProveedor(parametro);
        for (Proveedor p : data1) {
            this.proveedores.add(p);
        }
        return this.proveedores;
    }
}
