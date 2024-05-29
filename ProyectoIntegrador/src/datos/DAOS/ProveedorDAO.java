package datos.DAOS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import datos.ConexionDB;
import datos.objetos.Proveedor;

public class ProveedorDAO {
    

    //-------------------------OBTENER TODOS LOS DATOS DEL PROVEEDOR-------------------------//
    
    /**
     * Obtiene todos los datos de los proveedores almacenados en la base de datos.
     * @return Una lista enlazada de objetos Proveedor que contiene todos los datos de los proveedores.
     */
    public LinkedList<Proveedor> getDatos(){
        LinkedList<Proveedor> proveedorDatos = new LinkedList<>();
        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexionAdmin();
        if (connection != null) {
            try {
                Statement st = connection.createStatement();
                String query = "select * from stylebiker.proveedor";
                ResultSet result = st.executeQuery(query);
                while(result.next()) {
                    Proveedor p = new Proveedor(result.getString(2),result.getString(3),result.getString(4), result.getString(5), result.getString(6), result.getString(7));
                    proveedorDatos.add(p);
                    
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
        return proveedorDatos;
    }
    
    //----------------------REGISTRAR PROVEEDOR------------------------------//
    
    /**
     * Registra un nuevo proveedor en la base de datos.
     * @param id ID del proveedor.
     * @param nombreEmpresa Nombre de la empresa del proveedor.
     * @param direccion Dirección del proveedor.
     * @param telefono Teléfono del proveedor.
     * @param correo Correo electrónico del proveedor.
     * @param productosSuministrados Productos suministrados por el proveedor.
     * @return true si el proveedor se registró correctamente, false de lo contrario.
     */
    public boolean registrarProveedor(String nombreEmpresa, String direccion, String telefono, String correo,String productosSuministrados, String estado){

        ConexionDB conn = new ConexionDB();
        try (Connection connection = conn.obtenerConexionAdmin();
                CallableStatement callableStatement = connection.prepareCall("{ call stylebiker.ins_proveedor(?, ?, ?, ?, ?, ?) }")) {

               callableStatement.setString(1, nombreEmpresa);
               callableStatement.setString(2, direccion);
               callableStatement.setString(3, telefono);
               callableStatement.setString(4, correo);
               callableStatement.setString(5, productosSuministrados);
               callableStatement.setString(6, estado);

               callableStatement.execute();

               // No hay advertencias, pero verifica si se lanzó una excepción
               System.out.println("realizado");
               return true; // Si no hubo excepciones, asume que se realizó correctamente
           } catch (SQLException e) {
               if (e.getErrorCode() == 1) { // 1 es el código de error para violación de clave única
                   System.err.println("Error: El codigo ya existe.");
               } else {
               	System.err.println("Error de SQL: " + e.getMessage());
                   e.printStackTrace();
               }
               return false;
           }
    }
    
    //---------------------ELIMINAR UN PROVEEDOR----------------------//
    
    /**
     * Elimina un proveedor de la base de datos.
     * @param id ID del proveedor a eliminar.
     * @return true si el proveedor se eliminó correctamente, false de lo contrario.
     */
    public boolean eliminarProveedor(String id) {
        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexionAdmin();
        if (connection != null) {
            try {
                String query = "DELETE FROM proveedor WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, id);
                
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
    
    //-------------------------MODIFICAR-EDITAR-ACTUALIZAR PROVEEDOR------------------------------//
    
    /**
     * Modifica los datos de un proveedor en la base de datos.
     * @param proveedor Objeto Proveedor con los nuevos datos a actualizar.
     * @return true si los datos del proveedor se actualizaron correctamente, false de lo contrario.
     */
    public boolean editarProveedor(Proveedor proveedor) {
        ConexionDB conn = new ConexionDB();
        try (Connection connection = conn.obtenerConexionAdmin();
        		CallableStatement callableStatement = connection.prepareCall("{ call stylebiker.MOD_PROVEEDOR(?, ?, ?, ?, ?) }")) {

            callableStatement.setString(1, proveedor.getNombreEmpresa());
            callableStatement.setString(2, proveedor.getDireccion());
            callableStatement.setString(3, proveedor.getTelefono());
            callableStatement.setString(4, proveedor.getCorreo());
            callableStatement.setString(5, proveedor.getProductosSuministrados());

            callableStatement.execute();

            // No hay advertencias, pero verifica si se lanzó una excepción
            System.out.println("realizado");
            return true;           
            }catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
    }
    
    //-----------------------------BUSCAR PROVEEDOR POR NOMBRE O POR ID--------------------------//
    /**
     * Busca proveedores por nombre o por ID en la base de datos.
     * @param parametro Nombre o ID del proveedor a buscar.
     * @return Una lista enlazada de objetos Proveedor que contienen los resultados de la búsqueda.
     */
    public LinkedList<Proveedor> getBuscarProveedor(String parametro) {
        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexionAdmin();
        LinkedList<Proveedor> proveedoresEncontrados = new LinkedList<>();
        
        if (connection != null) {
            try {
                    String query = "SELECT * FROM stylebiker.proveedor WHERE UPPER(id) LIKE UPPER(? || '%') OR  UPPER(NOMBRE_EMPRESA) LIKE UPPER(? || '%')";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, parametro);
                    preparedStatement.setString(2, parametro);
                    ResultSet result = preparedStatement.executeQuery();
                    
                    while (result.next()) {
                        Proveedor proveedor =  new Proveedor(result.getString(1),result.getString(2),result.getString(3), result.getString(4), result.getString(5), result.getString(6));
                        proveedoresEncontrados.add(proveedor);
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
        return proveedoresEncontrados;
    }
    
}
