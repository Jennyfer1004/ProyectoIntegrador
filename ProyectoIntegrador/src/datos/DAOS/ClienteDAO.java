package datos.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import datos.ConexionDB;
import datos.objetos.Cliente;

public class ClienteDAO {

    //-------------------------OBTENER TODOS LOS DATOS DEL CLIENTE-------------------------//
    
    /**
     * Obtiene todos los datos de los clientes almacenados en la base de datos.
     * @return Una lista enlazada de objetos Cliente que contiene todos los datos de los clientes.
     */
    public LinkedList<Cliente> getDatos(){
        LinkedList<Cliente> clienteDatos = new LinkedList<>();
        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexion();
        if (connection != null) {
            try {
                Statement st = connection.createStatement();
                String query = "select * from cliente";
                ResultSet result = st.executeQuery(query);
                while(result.next()) {
                    Cliente c = new Cliente(result.getString(1),result.getString(2),result.getString(3), result.getString(4), result.getString(5));
                    clienteDatos.add(c);
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return clienteDatos;
    }
    
    //----------------------REGISTRAR CLIENTE------------------------------//
    
    /**
     * Registra un nuevo cliente en la base de datos.
     * @param cedula Cédula del cliente.
     * @param nombreCompleto Nombre completo del cliente.
     * @param correo Correo electrónico del cliente.
     * @param telefono Teléfono del cliente.
     * @param direccion Dirección del cliente.
     * @return true si el cliente se registró correctamente, false de lo contrario.
     */
    public boolean registrarCliente(String cedula, String nombreCompleto, String correo, String telefono, String direccion){

        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexion();
        if (connection != null) {
            try {
                String query = "INSERT INTO cliente (cedula, nombre_completo, correo, telefono, direccion) VALUES (?,?,?,?,?) ";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, cedula);
                preparedStatement.setString(2, nombreCompleto);
                preparedStatement.setString(3, correo);
                preparedStatement.setString(4, telefono);
                preparedStatement.setString(5, direccion);
                int filasInsertadas = preparedStatement.executeUpdate();
                return filasInsertadas > 0;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    //---------------------ELIMINAR UN CLIENTE----------------------//
    
    /**
     * Elimina un cliente de la base de datos.
     * @param cedula Cédula del cliente a eliminar.
     * @return true si el cliente se eliminó correctamente, false de lo contrario.
     */
    public boolean eliminarCliente(String cedula) {
        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexion();
        if (connection != null) {
            try {
                String query = "DELETE FROM cliente WHERE cedula = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, cedula);
                
                int filasEliminadas = preparedStatement.executeUpdate();
                return filasEliminadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    //-------------------------MODIFICAR-EDITAR-ACTUALIZAR CLIENTE------------------------------//
    
    /**
     * Modifica los datos de un cliente en la base de datos.
     * @param cliente Objeto Cliente con los nuevos datos a actualizar.
     * @return true si los datos del cliente se actualizaron correctamente, false de lo contrario.
     */
    public boolean editarCliente(Cliente cliente) {
        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexion();
        if (connection != null) {
            try {
                String query = "UPDATE cliente SET nombre_completo = ?, correo = ?, direccion = ? WHERE cedula = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, cliente.getNombreCompleto());
                preparedStatement.setString(2, cliente.getCorreo());
                preparedStatement.setString(3, cliente.getDireccion());
                preparedStatement.setString(4, cliente.getCedula());
                
                int filasModificadas = preparedStatement.executeUpdate();
                return filasModificadas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    //-----------------------------BUSCAR CLIENTE POR NOMBRE O POR CEDULA--------------------------//
    /**
     * Busca clientes por nombre o por cédula en la base de datos.
     * @param parametro Nombre o cédula del cliente a buscar.
     * @return Una lista enlazada de objetos Cliente que contienen los resultados de la búsqueda.
     */
    public LinkedList<Cliente> getBuscarCliente(String parametro) {
        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexion();
        LinkedList<Cliente> clientesEncontrados = new LinkedList<>();
        
        if (connection != null) {
            try {
                    String query = "SELECT * FROM cliente WHERE UPPER(cedula) LIKE UPPER(? || '%') OR  UPPER(nombre_completo) LIKE UPPER(? || '%')";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, parametro);
                    preparedStatement.setString(2, parametro);
                    ResultSet result = preparedStatement.executeQuery();
                    
                    while (result.next()) {
                        Cliente cliente =  new Cliente(result.getString(1),result.getString(2),result.getString(3), result.getString(4), result.getString(5));
                        clientesEncontrados.add(cliente);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return clientesEncontrados;
    }
}
