package datos.DAOS;

import java.sql.CallableStatement;
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
        
        try(Connection connection = conn.obtenerConexionAdmin();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM stylebiker.cliente")) {
            while(result.next()) {
                  Cliente c = new Cliente(result.getString(1),result.getString(2),result.getString(3), result.getString(4), result.getString(5), result.getString(6));
                  clienteDatos.add(c);
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
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
    public boolean  registrarCliente(String cedula, String nombreCompleto, String correo, String telefono, String direccion, String estado) {

        ConexionDB conn = new ConexionDB();

        try (Connection connection = conn.obtenerConexionAdmin();
             CallableStatement callableStatement = connection.prepareCall("{ call stylebiker.ins_cliente(?, ?, ?, ?, ?, ?) }")) {

            callableStatement.setString(1, cedula);
            callableStatement.setString(2, nombreCompleto);
            callableStatement.setString(3, correo);
            callableStatement.setString(4, telefono);
            callableStatement.setString(5, direccion);
            callableStatement.setString(6, estado);

            callableStatement.execute();

            // No hay advertencias, pero verifica si se lanzó una excepción
            System.out.println("realizado");
            return true; // Si no hubo excepciones, asume que se realizó correctamente
        } catch (SQLException e) {
            if (e.getErrorCode() == 1) { // 1 es el código de error para violación de clave única
                System.err.println("Error: La cédula ya existe.");
            } else {
            	System.err.println("Error de SQL: " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }




    
    //---------------------ELIMINAR UN CLIENTE----------------------//
    
    /**
     * Elimina un cliente de la base de datos.
     * @param cedula Cédula del cliente a eliminar.
     * @return true si el cliente se eliminó correctamente, false de lo contrario.
     */
    public boolean eliminarCliente(String cedula) {
        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexionAdmin();
        if (connection != null) {
            try {
                String query = "DELETE FROM stylebiker.cliente WHERE cedula = ?";
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
        try (Connection connection = conn.obtenerConexionAdmin();
        		CallableStatement callableStatement = connection.prepareCall("{ call stylebiker.MOD_CLIENTE(?, ?, ?, ?, ?) }")) {

            callableStatement.setString(1, cliente.getCedula());
            callableStatement.setString(2, cliente.getNombreCompleto());
            callableStatement.setString(3, cliente.getCorreo());
            callableStatement.setString(4, cliente.getTelefono());
            callableStatement.setString(5, cliente.getDireccion());

            callableStatement.execute();

            // No hay advertencias, pero verifica si se lanzó una excepción
            System.out.println("realizado");
            return true;           } catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
       }
    
    //-----------------------------BUSCAR CLIENTE POR NOMBRE O POR CEDULA--------------------------//
    /**
     * Busca clientes por nombre o por cédula en la base de datos.
     * @param parametro Nombre o cédula del cliente a buscar.
     * @return Una lista enlazada de objetos Cliente que contienen los resultados de la búsqueda.
     */
    public LinkedList<Cliente> getBuscarCliente(String parametro) {
        ConexionDB conn = new ConexionDB();
        LinkedList<Cliente> clientesEncontrados = new LinkedList<>();
        
        try (Connection connection = conn.obtenerConexionAdmin();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM stylebiker.cliente WHERE UPPER(cedula) LIKE UPPER(? || '%') OR  UPPER(nombre_completo) LIKE UPPER(? || '%')")) {

               preparedStatement.setString(1, parametro);
               preparedStatement.setString(2, parametro);

               try (ResultSet result = preparedStatement.executeQuery()) {
                   while (result.next()) {
                       Cliente cliente = new Cliente(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
                       clientesEncontrados.add(cliente);
                   }
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }

           return clientesEncontrados;
       }
}
