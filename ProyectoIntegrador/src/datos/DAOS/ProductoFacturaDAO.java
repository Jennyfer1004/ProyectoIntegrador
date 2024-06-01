package datos.DAOS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import datos.ConexionDB;
import datos.objetos.ProductoFactura;

public class ProductoFacturaDAO {
    
    public LinkedList<ProductoFactura> 	getDatosProductoFactura(){ 
        LinkedList<ProductoFactura> productoFacturaDatos = new LinkedList<>();
        ConexionDB conn = new ConexionDB();
        
        try(Connection connection = conn.obtenerConexionPrincipal();
                Statement st = connection.createStatement();
                ResultSet result = st.executeQuery("SELECT f.CODIGO_FACTURA, pf.ID, f.FECHA_REALIZADA, pf.CANPRODUCTOS_COMPRADOS, pf.VALOR_POR_UNIDAD, f.CED_EMPLEADO, f.CEDULA_CLIENTE FROM stylebiker.factura f JOIN stylebiker.PRODUCTO_FACTURA pf ON f.CODIGO_FACTURA = pf.CODIGO_FACTURA")) {
                while(result.next()) {
                	  ProductoFactura pf = new ProductoFactura(result.getString(1),result.getString(2),result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7));
                	  
                	  productoFacturaDatos.add(pf);
                        
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            
            return productoFacturaDatos;
    }
}
