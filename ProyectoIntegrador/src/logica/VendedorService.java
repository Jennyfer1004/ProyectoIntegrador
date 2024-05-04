package logica;

import java.util.LinkedList;
import datos.DAOS.VendedorDAO;
import datos.objetos.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VendedorService {

    public VendedorDAO vendedorDAO;
    ObservableList<Vendedor> vendedores = FXCollections.observableArrayList();

    /**
     * Constructor de la clase VendedorService.
     */
    public VendedorService() {
        this.vendedorDAO = new VendedorDAO();
    }

    /**
     * Obtiene la lista de vendedores desde la base de datos.
     * @return Una lista observable de vendedores.
     */
    public ObservableList<Vendedor> getVendedores() {
        LinkedList<Vendedor> data1 = vendedorDAO.getDatos();
        for (Vendedor v : data1) {
            this.vendedores.add(v);
        }
        return this.vendedores;
    }

    /**
     * Agrega un nuevo vendedor.
     * @param cedula Cédula del vendedor.
     * @param nombreCompleto Nombre completo del vendedor.
     * @param correo Correo electrónico del vendedor.
     * @param telefono Teléfono del vendedor.
     * @param direccion Dirección del vendedor.
     */
    public void agregarVendedor(String cedula, String nombreCompleto, String correo, String telefono, String direccion) {
        Vendedor nuevoVendedor = new Vendedor(cedula, nombreCompleto, correo, telefono, direccion);
        this.vendedores.add(nuevoVendedor);
    }

    /**
     * Elimina un vendedor.
     * @param vendedor Vendedor a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     */
    public boolean eliminarVendedor(Vendedor vendedor) {
        return vendedorDAO.eliminarVendedor(vendedor.getCedula());
    }

    /**
     * Edita un vendedor.
     * @param vendedor Vendedor a editar.
     * @return true si se editó correctamente, false de lo contrario.
     */
    public boolean editarVendedor(Vendedor vendedor) {
        return vendedorDAO.editarVendedor(vendedor);
    }

    /**
     * Busca vendedores por nombre o cédula.
     * @param parametro Nombre o cédula del vendedor a buscar.
     * @return Una lista observable de vendedores que coinciden con el parámetro de búsqueda.
     */
    public ObservableList<Vendedor> buscarVendedor(String parametro) {
        LinkedList<Vendedor> data1 = vendedorDAO.getBuscarVendedor(parametro);
        for (Vendedor v : data1) {
            this.vendedores.add(v);
        }
        return this.vendedores;
    }
}
