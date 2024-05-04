package datos.DAOS;

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
        Connection connection = conn.obtenerConexion();
        if (connection != null) {
            try {
                Statement st = connection.createStatement();
                String query = "select * from proveedor";
                ResultSet result = st.executeQuery(query);
                while(result.next()) {
                    Proveedor p = new Proveedor(result.getString(1),result.getString(2),result.getString(3), result.getString(4), result.getString(5), result.getString(6));
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
    public boolean registrarProveedor(String id, String nombreEmpresa, String direccion, String telefono, String correo,String productosSuministrados){

        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexion();
        if (connection != null) {
            try {
                String query = "INSERT INTO proveedor (ID, NOMBRE_EMPRESA , DIRECCION , TELEFONO , CORREO , PRODUCTOS_SUMINISTRADOS ) VALUES (?,?,?,?,?,?) ";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, nombreEmpresa);
                preparedStatement.setString(3, direccion);
                preparedStatement.setString(4, telefono);
                preparedStatement.setString(5, correo);
                preparedStatement.setString(6, productosSuministrados);
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
    
    //---------------------ELIMINAR UN PROVEEDOR----------------------//
    
    /**
     * Elimina un proveedor de la base de datos.
     * @param id ID del proveedor a eliminar.
     * @return true si el proveedor se eliminó correctamente, false de lo contrario.
     */
    public boolean eliminarProveedor(String id) {
        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexion();
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
        Connection connection = conn.obtenerConexion();
        if (connection != null) {
            try {
                String query = "UPDATE proveedor SET NOMBRE_EMPRESA = ?, DIRECCION = ?, TELEFONO = ?,CORREO = ?, PRODUCTOS_SUMINISTRADOS = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, proveedor.getNombreEmpresa());
                preparedStatement.setString(2, proveedor.getDireccion());
                preparedStatement.setString(3, proveedor.getTelefono());
                preparedStatement.setString(4, proveedor.getCorreo());
                preparedStatement.setString(5, proveedor.getProductosSuministrados());
                preparedStatement.setString(6, proveedor.getId());
                
                
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
    
    //-----------------------------BUSCAR PROVEEDOR POR NOMBRE O POR ID--------------------------//
    /**
     * Busca proveedores por nombre o por ID en la base de datos.
     * @param parametro Nombre o ID del proveedor a buscar.
     * @return Una lista enlazada de objetos Proveedor que contienen los resultados de la búsqueda.
     */
    public LinkedList<Proveedor> getBuscarProveedor(String parametro) {
        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexion();
        LinkedList<Proveedor> proveedoresEncontrados = new LinkedList<>();
        
        if (connection != null) {
            try {
                    String query = "SELECT * FROM proveedor WHERE UPPER(id) LIKE UPPER(? || '%') OR  UPPER(NOMBRE_EMPRESA) LIKE UPPER(? || '%')";
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
