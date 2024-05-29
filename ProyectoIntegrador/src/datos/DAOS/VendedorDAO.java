package datos.DAOS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import datos.ConexionDB;
import datos.objetos.Vendedor;

public class VendedorDAO {
    
    //-------------------------OBTENER TODOS LOS DATOS DEL VENDEDOR-------------------------//
    
    /**
     * Obtiene todos los datos de los vendedores almacenados en la base de datos.
     * @return Una lista enlazada de objetos Vendedor que contiene todos los datos de los vendedores.
     */
    public LinkedList<Vendedor> getDatos(){
        LinkedList<Vendedor> vendedorDatos = new LinkedList<>();
        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexionAdmin();
        if (connection != null) {
            try {
                Statement st = connection.createStatement();
                String query = "select * from stylebiker.empleado";
                ResultSet result = st.executeQuery(query);
                while(result.next()) {
                    Vendedor c = new Vendedor(result.getString(1),result.getString(2),result.getString(3), result.getString(4), result.getString(5), result.getString(6));
                    vendedorDatos.add(c);
                    
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
        return vendedorDatos;
    }
    
    //----------------------REGISTRAR VENDEDOR------------------------------//
    
    /**
     * Registra un nuevo vendedor en la base de datos.
     * @param cedula Cédula del vendedor.
     * @param nombreCompleto Nombre completo del vendedor.
     * @param correo Correo electrónico del vendedor.
     * @param telefono Teléfono del vendedor.
     * @param direccion Dirección del vendedor.
     * @return true si el vendedor se registró correctamente, false de lo contrario.
     */
    public boolean registrarVendedor(String cedula, String nombreCompleto, String direccion, String correo, String telefono, String estado){

        ConexionDB conn = new ConexionDB();
        try (Connection connection = conn.obtenerConexionAdmin();
                CallableStatement callableStatement = connection.prepareCall("{ call stylebiker.INS_EMPLEADO(?, ?, ?, ?, ?, ?) }")) {

               callableStatement.setString(1, cedula);
               callableStatement.setString(2, nombreCompleto);
               callableStatement.setString(3, direccion);
               callableStatement.setString(4, correo);
               callableStatement.setString(5, telefono);
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
    
    //---------------------ELIMINAR UN VENDEDOR----------------------//
    
    /**
     * Elimina un vendedor de la base de datos.
     * @param cedula Cédula del vendedor a eliminar.
     * @return true si el vendedor se eliminó correctamente, false de lo contrario.
     */
    public boolean eliminarVendedor(String cedula) {
        ConexionDB conn = new ConexionDB();
        try (Connection connection = conn.obtenerConexionAdmin();
        		CallableStatement callableStatement = connection.prepareCall("{ call stylebiker.elim_empleado(?, ?) }")) {

           
            callableStatement.setString(1, cedula);
            callableStatement.setString(2, "Inactivo");

            callableStatement.execute();

            // No hay advertencias, pero verifica si se lanzó una excepción 
            System.out.println("realizado");
            return true;           
            } catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
    }
    
    //-------------------------MODIFICAR-EDITAR-ACTUALIZAR VENDEDOR------------------------------//
    
    /**
     * Modifica los datos de un vendedor en la base de datos.
     * @param vendedor Objeto Vendedor con los nuevos datos a actualizar.
     * @return true si los datos del vendedor se actualizaron correctamente, false de lo contrario.
     */
    public boolean editarVendedor(Vendedor vendedor) {
        ConexionDB conn = new ConexionDB();
        try (Connection connection = conn.obtenerConexionAdmin();
        		CallableStatement callableStatement = connection.prepareCall("{ call stylebiker.MOD_EMPLEADO(?, ?, ?, ?, ?) }")) {
        	
        	callableStatement.setString(1, vendedor.getCedula());
            callableStatement.setString(2, vendedor.getNombreCompleto());
            callableStatement.setString(3, vendedor.getDireccion());
            callableStatement.setString(4, vendedor.getCorreo());
            callableStatement.setString(5, vendedor.getTelefono());

            callableStatement.execute();

            // No hay advertencias, pero verifica si se lanzó una excepción
            System.out.println("realizado");
            return true;           
            }catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
    }
    
    //-----------------------------BUSCAR VENDEDOR POR NOMBRE O POR CEDULA--------------------------//
    /**
     * Busca vendedores por nombre o por cédula en la base de datos.
     * @param parametro Nombre o cédula del vendedor a buscar.
     * @return Una lista enlazada de objetos Vendedor que contienen los resultados de la búsqueda.
     */
    public LinkedList<Vendedor> getBuscarVendedor(String parametro) {
        ConexionDB conn = new ConexionDB();
        Connection connection = conn.obtenerConexionAdmin();
        LinkedList<Vendedor> vendedoresEncontrados = new LinkedList<>();
        
        if (connection != null) {
            try {
                String query = "SELECT * FROM stylebiker.empleado WHERE UPPER(cedula) LIKE UPPER(? || '%') OR  UPPER(nombre_completo) LIKE UPPER(? || '%')";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, parametro);
                preparedStatement.setString(2, parametro);
                ResultSet result = preparedStatement.executeQuery();
                
                while (result.next()) {
                    Vendedor vendedor =  new Vendedor(result.getString(1),result.getString(2),result.getString(3), result.getString(4), result.getString(5), result.getString(6));
                    vendedoresEncontrados.add(vendedor);
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
        return vendedoresEncontrados;
    }

}
