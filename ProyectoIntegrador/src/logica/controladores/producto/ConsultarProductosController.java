package logica.controladores.producto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import datos.objetos.Producto;
import datos.objetos.Usuarios;
import datos.objetos.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.ProductoService;
import presentacion.vistas.gerente.GerenteView;
import presentacion.vistas.vendedor.VendedorView;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn;

public class ConsultarProductosController implements Initializable{
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
    private TableColumn<Producto, String> stockColumna;
    
    @FXML
    private TableColumn<Producto, String> estadoColumna;
    
    @FXML
    private TableColumn<Producto, String> proveedorColumna;
	@FXML
	private Button btnconsultar;
	@FXML
	private Button botonVolver;
	
	private ProductoService productoService;
	private  ObservableList<Producto> listaProductos;
	GerenteView gerenteView = new GerenteView();
    VendedorView vendedorView = new VendedorView();

	@FXML
	public void onCargarDatosClicked(MouseEvent event) {
		
		this.tabla.getItems().clear();
        this.tabla.setItems(this.productoService.getProductos());
		
		
	}
	@FXML
	public void onVolverClicked(MouseEvent event) throws IOException {
		if (Usuarios.getRol().equals("gerente")) {
        	gerenteView.show((Stage) botonVolver.getScene().getWindow());
        }else if (Usuarios.getRol().equals("vendedor")) {
        	vendedorView.show((Stage) botonVolver.getScene().getWindow());
        }
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
        this.productoService = new ProductoService();
        this.listaProductos =  FXCollections.observableArrayList();  
        
        //codigoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        nombreColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        descripcionColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        valorColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        stockColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        //proveedorColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        //estadoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        
        // Asignar los valores de las celdas a las propiedades del objeto Producto
        codigoColumna.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        nombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcionColumna.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        valorColumna.setCellValueFactory(new PropertyValueFactory<>("valorCompra"));
        stockColumna.setCellValueFactory(new PropertyValueFactory<>("cantidadStock"));
        proveedorColumna.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
        estadoColumna.setCellValueFactory(new PropertyValueFactory<>("estado"));
	}
}
