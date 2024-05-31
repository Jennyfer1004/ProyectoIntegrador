package logica.controladores.producto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import datos.objetos.Producto;
import datos.objetos.Usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.ProductoService;
import presentacion.vistas.administrador.AdministradorView;
import presentacion.vistas.gerente.GerenteView;
import presentacion.vistas.producto.FormularioProductosView;
import presentacion.vistas.vendedor.VendedorView;

public class GestionProductosController implements Initializable{
	

    @FXML
    private TableView<Producto> tabla;
    
    @FXML
    private TableColumn<Producto, String> codigoColumna;
    
    @FXML
    private TableColumn<Producto, String> nombreColumna;

    @FXML
    private TableColumn<Producto, String> descripcionColumna;
    
    @FXML
    private TableColumn<Producto, String> valorColumna;
   
    @FXML
    private TableColumn<Producto, String> cantidadStockColumna;
    
    @FXML
    private TableColumn<Producto, String> estadoColumna;
    
    @FXML
    private TableColumn<Producto, String> proveedorColumna;

    
    @FXML
    private Button Eliminarprodutobtn;

    @FXML
    private Button botonVolverPrincipal;
    
    @FXML
    private Button buscarBoton;

    @FXML
    private Button cargarDatosBoton;

    @FXML
    private Button crearproductoBoton;

    @FXML
    private TextField inputBuscar;
    
    private ProductoService productoService;

    AdministradorView administradorView = new AdministradorView();
    GerenteView gerenteView = new GerenteView();
    VendedorView vendedorView = new VendedorView();

    @FXML
    void onBuscarProductoClicked(MouseEvent event) {
    	
    	String parametro = inputBuscar.getText();
        
        if (!parametro.isEmpty()) {
        	this.tabla.getItems().clear();
            this.tabla.setItems(this.productoService.buscarProducto(parametro));
        } else {
            mostrarAlerta("Entrada Inválida", "Por favor ingrese el nombre o el codigo del producto a buscar.", AlertType.WARNING);
        }

    }

    @FXML
    void onCargarDatosClicked(MouseEvent event) {
    	this.tabla.getItems().clear();
        this.tabla.setItems(this.productoService.getProductos());

    }
    
    
    
    FormularioProductosView fpv = new FormularioProductosView();
    @FXML
    void onCrearProductoClicked(MouseEvent event) throws IOException {
    	fpv.show((Stage) crearproductoBoton.getScene().getWindow());

    }

    @FXML
    void onEliminarProductoClicked(MouseEvent event) {
    	Producto productoSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
        	String nombre = productoSeleccionado.getNombre();
        	String estado = productoSeleccionado.getEstado();
        	if ("Inactivo".equals(estado)) {
                mostrarAlerta("Error", "El producto ya está inactivo y no se puede eliminar nuevamente.", AlertType.ERROR);
                return; // Salir del método sin continuar con la eliminación
            }
        	//System.out.println(clienteSeleccionado.getCedula());
            boolean eliminado = productoService.eliminarProducto(nombre);
            if (eliminado) {

                mostrarAlerta("Eliminación Exitosa", "El producto ha sido eliminado correctamente.", AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se pudo eliminar el producto.", AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error", "Por favor, seleccione un producto para eliminar.", AlertType.WARNING);
        }

    }

    @FXML
    void onGuardarCambiosClicked(MouseEvent event) {
    	
    	Producto productoSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
        	if (productoSeleccionado.isEditado()) {
        		//boolean actualizado = true;
        		//System.out.println(clienteSeleccionado.getCedula()+clienteSeleccionado.getNombreCompleto()+clienteSeleccionado.getCorreo()+clienteSeleccionado.getDireccion());
        		boolean actualizado = productoService.editarProducto(productoSeleccionado);
        	
        		if (actualizado) {
        			mostrarAlerta("Actualización Exitosa", "El producto se actualizó correctamente.", AlertType.INFORMATION);
        			productoSeleccionado.setEditado(false);
        		} else {
        			mostrarAlerta("Error", "No se pudo actualizar el producto.", AlertType.ERROR);
        		}
        	}
        	else {
        		mostrarAlerta("Información", "No hay cambios para guardar.", AlertType.INFORMATION);
        		}
 
        }
        else {
            mostrarAlerta("Error", "Por favor, seleccione un producto para actualizar.", AlertType.WARNING);
        }

    }
	
    

    @FXML
    void volverAprincipal(MouseEvent event) throws IOException {
    	if (Usuarios.getRol().equals("administrador")) {
        	administradorView.show((Stage)botonVolverPrincipal.getScene().getWindow());
        }else if (Usuarios.getRol().equals("gerente")) {
        	gerenteView.show((Stage)botonVolverPrincipal.getScene().getWindow());
        }else if (Usuarios.getRol().equals("vendedor")) {
        	vendedorView.show((Stage)botonVolverPrincipal.getScene().getWindow());
        }else{
        	mostrarAlerta("Inicio de Sesión Fallido", "Credenciales incorrectas para " ,AlertType.ERROR);
        }

    }
    
    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		tabla.setEditable(true);
        this.productoService = new ProductoService();
        ObservableList<Producto> productos = FXCollections.observableArrayList(); 
        
        //codigoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        nombreColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        descripcionColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        valorColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        cantidadStockColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        //proveedorColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        //estadoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        
        // Asignar los valores de las celdas a las propiedades del objeto Producto
        codigoColumna.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcionColumna.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        valorColumna.setCellValueFactory(new PropertyValueFactory<>("valorCompra"));
        cantidadStockColumna.setCellValueFactory(new PropertyValueFactory<>("cantidadStock"));
        proveedorColumna.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
        estadoColumna.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        nombreColumna.setOnEditCommit(event -> {
            Producto producto = event.getRowValue();
            producto.setNombre(event.getNewValue());
            producto.setEditado(true);
        });
        descripcionColumna.setOnEditCommit(event -> {
        	Producto producto = event.getRowValue();
        	producto.setDescripcion(event.getNewValue());
        	producto.setEditado(true);
        });
        valorColumna.setOnEditCommit(event -> {
        	Producto producto = event.getRowValue();
        	producto.setValorCompra(event.getNewValue());
        	producto.setEditado(true);
        });
        cantidadStockColumna.setOnEditCommit(event -> {
        	Producto producto = event.getRowValue();
        	producto.setCantidadStock(event.getNewValue());
        	producto.setEditado(true);
        });
        tabla.setItems(productos);
        
        
	}

}
