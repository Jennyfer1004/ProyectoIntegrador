package datos.DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import datos.ConexionDB;
import datos.objetos.Vendedor;

public class VendedorDAO {
	
	
	//-------------------------OBETENER TODOS LOS DATOS DEL VENDEDOR-------------------------//
	
	
		public LinkedList<Vendedor> getDatos(){
			LinkedList<Vendedor> vendedorDatos = new LinkedList<>();
			ConexionDB conn = new ConexionDB();
			Connection connection = conn.obtenerConexion();
			if (connection != null) {
				try {
					Statement st = connection.createStatement();
					String query = "select * from empleado";
					ResultSet result = st.executeQuery(query);
					while(result.next()) {
						Vendedor c = new Vendedor(result.getString(1),result.getString(2),result.getString(3), result.getString(4), result.getString(5));
						//System.out.println(result.getString(1) + result.getString(2) + result.getString(3) + result.getString(4));
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
		
		public boolean registrarVendedor(String cedula, String nombreCompleto, String correo, String telefono, String direccion){

			ConexionDB conn = new ConexionDB();
			Connection connection = conn.obtenerConexion();
			if (connection != null) {
				try {
					String query = "INSERT INTO empleado (cedula, nombre_completo, correo, telefono, direccion) VALUES (?,?,?,?,?) ";
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
		
		
		
		
		
		
		
		
	     //---------------------ELIMINAR UN VENDEDOR----------------------//
		
		
		public boolean eliminarVendedor(String cedula) {
		    ConexionDB conn = new ConexionDB();
		    Connection connection = conn.obtenerConexion();
		    if (connection != null) {
		        try {

		            String query = "DELETE FROM empleado WHERE cedula = ?";
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
		
		//-------------------------MODIFICAR-EDITAR-ACTUALIZAR VENDEDOR------------------------------//
		
		public boolean editarVendedor(Vendedor vendedor) {
		    ConexionDB conn = new ConexionDB();
		    Connection connection = conn.obtenerConexion();
		    if (connection != null) {
		        try {
		            String query = "UPDATE empleado SET nombre_completo = ?, correo = ?, direccion = ? WHERE cedula = ?";
		            PreparedStatement preparedStatement = connection.prepareStatement(query);
		            preparedStatement.setString(1, vendedor.getNombreCompleto());
		            preparedStatement.setString(2, vendedor.getCorreo());
		            preparedStatement.setString(3, vendedor.getDireccion());
		            preparedStatement.setString(4, vendedor.getCedula());
		            
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
		
		//-----------------------------BUSCAR VENDEDOR POR NOMBRE O POR CEDULA--------------------------//
		public LinkedList<Vendedor> getBuscarVendedor(String parametro) {
		    ConexionDB conn = new ConexionDB();
		    Connection connection = conn.obtenerConexion();
		    LinkedList<Vendedor> vendedoresEncontrados = new LinkedList<>();
		    
		    if (connection != null) {
		        try {
		                String query = "SELECT * FROM empleado WHERE UPPER(cedula) LIKE UPPER(? || '%') OR  UPPER(nombre_completo) LIKE UPPER(? || '%')";
		                PreparedStatement preparedStatement = connection.prepareStatement(query);
		                preparedStatement.setString(1, parametro);
		                preparedStatement.setString(2, parametro);
		                ResultSet result = preparedStatement.executeQuery();
		                
		                while (result.next()) {
		                    Vendedor vendedor =  new Vendedor(result.getString(1),result.getString(2),result.getString(3), result.getString(4), result.getString(5));
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
