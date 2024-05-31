package logica;

import java.util.LinkedList;

import datos.DAOS.ProductoDAO;
import datos.objetos.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductoService {
	
	public ProductoService() {
		this.productoDAO = new ProductoDAO();
	}
	
	public ProductoDAO productoDAO;
    ObservableList<Producto> productos = FXCollections.observableArrayList();
	
	public ObservableList<Producto> getProductos() {
    	
    	LinkedList<Producto> data1 = productoDAO.getDatos();
        for(Producto p: data1) {
            this.productos.add(p);
        }
        return this.productos;
    }
	
	public void agregarProducto(String codigo, String nombre, String descripcion, String valorCompra, String stock, String idProveedor, String estado) {
        Producto nuevoProducto = new Producto(codigo, nombre, descripcion, valorCompra, stock, idProveedor, estado);
        this.productos.add(nuevoProducto);
    }
	
	public boolean eliminarProducto(String nombre) {
        return productoDAO.eliminarProducto(nombre);
    }
	
	public boolean editarProducto(Producto producto) {
        return productoDAO.editarProducto(producto);
    }
    
	public ObservableList<Producto> buscarProducto(String parametro) {
        LinkedList<Producto> data1 = productoDAO.getBuscarProducto(parametro);
        for(Producto p: data1) {
            this.productos.add(p);
        }
        return this.productos;
    }

}
