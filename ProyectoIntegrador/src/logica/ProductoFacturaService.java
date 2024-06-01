package logica;

import java.util.LinkedList;
import datos.DAOS.ProductoFacturaDAO;
import datos.objetos.ProductoFactura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductoFacturaService {
	
	public ProductoFacturaService() {
		this.productoFacturaDAO = new ProductoFacturaDAO();
	}
	
	public ProductoFacturaDAO productoFacturaDAO;
    ObservableList<ProductoFactura> productosFactura = FXCollections.observableArrayList();
	
	public ObservableList<ProductoFactura> getProductosFactura() {
    	
    	LinkedList<ProductoFactura> data1 = productoFacturaDAO.getDatosProductoFactura();
        for(ProductoFactura p: data1) {
            this.productosFactura.add(p);
        }
        return this.productosFactura;
    }
	
}
