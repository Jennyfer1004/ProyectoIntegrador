package datos.DAOS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import datos.ConexionDB;
import datos.objetos.Producto;

public class ProductoDAO {
	
//-------------------------OBTENER TODOS LOS DATOS DEL PRODUCTO-------------------------//
    
   
    public LinkedList<Producto> getDatos(){ 
        LinkedList<Producto> productoDatos = new LinkedList<>();
        ConexionDB conn = new ConexionDB();
        
        try(Connection connection = conn.obtenerConexionAdmin();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM stylebiker.producto")) {
            while(result.next()) {
            	  Producto p = new Producto(result.getString(1),result.getString(2),result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7));
                  productoDatos.add(p);
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            } 
        
        return productoDatos;
    }
    
//----------------------REGISTRAR PRODUCTO------------------------------//
   

    public boolean  registrarProducto(String codigo, String nombre, String descripcion, String valorCompra, String stock,  String estado, String nombreProveedor) {

        ConexionDB conn = new ConexionDB();
        
       try (Connection connection = conn.obtenerConexionAdmin();
             CallableStatement callableStatement = connection.prepareCall("{ call stylebiker.insertar_producto(?, ?, ?, ?, ?, ?, ?) }")) {
    	    callableStatement.setString(1, codigo);
            callableStatement.setString(2, nombre);
            callableStatement.setString(3, descripcion);
            callableStatement.setString(4, valorCompra);
            callableStatement.setString(5, stock);
            callableStatement.setString(6, estado);
            callableStatement.setString(7, nombreProveedor);

            callableStatement.execute();

            // No hay advertencias, pero verifica si se lanzó una excepción
            System.out.println("realizado");
            return true; // Si no hubo excepciones, asume que se realizó correctamente
       } catch (SQLException e) {
           if (e.getErrorCode() == 20001) {
               System.out.println("El proveedor no existe: " + e.getMessage());
           } else {
               System.out.println("Error al insertar producto: " + e.getMessage());
           }
       }
       return false;
   }
    
    
  //----------------------EDITAR PRODUCTO------------------------------//
    
    public boolean editarProducto(Producto producto) {
        ConexionDB conn = new ConexionDB();
        try (Connection connection = conn.obtenerConexionAdmin();
        		CallableStatement callableStatement = connection.prepareCall("{ call stylebiker.MOD_PRODUCTO(?, ?, ?, ?) }")) {

            
            callableStatement.setString(1, producto.getNombre());
            callableStatement.setString(2, producto.getDescripcion());
            callableStatement.setString(3, producto.getValorCompra());
            callableStatement.setString(4, producto.getCantidadStock());

            callableStatement.execute();

            // No hay advertencias, pero verifica si se lanzó una excepción
            System.out.println("realizado");
            return true;           } catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
       }
    
    
  //----------------------ELIMINAR PRODUCTO------------------------------//
    
    public boolean eliminarProducto(String nombre) {
        ConexionDB conn = new ConexionDB();
        try (Connection connection = conn.obtenerConexionAdmin();
        		CallableStatement callableStatement = connection.prepareCall("{ call stylebiker.elim_producto(?, ?) }")) {

           
            callableStatement.setString(1, nombre);
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
    
    
  //-----------------------------BUSCAR PRODUCTO POR NOMBRE O POR CODIGO--------------------------//
    /**
     * Busca clientes por nombre o por cédula en la base de datos.
     * @param parametro Nombre o cédula del cliente a buscar.
     * @return Una lista enlazada de objetos Cliente que contienen los resultados de la búsqueda.
     */
    public LinkedList<Producto> getBuscarProducto(String parametro) {
        ConexionDB conn = new ConexionDB();
        LinkedList<Producto> productosEncontrados = new LinkedList<>();
        
        try (Connection connection = conn.obtenerConexionAdmin();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM stylebiker.producto WHERE UPPER(codigo) LIKE UPPER(? || '%') OR  UPPER(nombre) LIKE UPPER(? || '%')")) {

               preparedStatement.setString(1, parametro);
               preparedStatement.setString(2, parametro);

               try (ResultSet result = preparedStatement.executeQuery()) {
                   while (result.next()) {
                	   Producto producto = new Producto(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7));
                       productosEncontrados.add(producto);
                   }
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }

           return productosEncontrados;
       }
    
    
        
}
    
    
    


