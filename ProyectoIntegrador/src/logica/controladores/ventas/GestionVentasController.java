package logica.controladores.ventas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import datos.objetos.Producto;
import datos.objetos.ProductoFactura;
import logica.ProductoFacturaService;
import datos.objetos.Usuarios;
import javafx.collections.FXCollections;
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
import presentacion.vistas.vendedor.VendedorView;
import presentacion.vistas.ventas.FormularioVentasView;

public class GestionVentasController implements Initializable{
	
    @FXML
    private Button buscarBoton;

	@FXML
    private TableView<ProductoFactura> tabla;
    
    @FXML
    private TableColumn<ProductoFactura, String> codigoFacturaColumn;

    @FXML
    private TableColumn<ProductoFactura, String> idProductoFacturaColumn;

    @FXML
    private TableColumn<ProductoFactura, String> fechaRealizadaColumn;

    @FXML
    private TableColumn<ProductoFactura, String> cantidadProductoColumn;

    @FXML
    private TableColumn<ProductoFactura, String> precioPorUnidadColumn;

    @FXML
    private TableColumn<ProductoFactura, String> cedulaVendedorColumn;

    @FXML
    private TableColumn<ProductoFactura, String> cedulaClienteColumn;
	
	@FXML
    private Button botonVolverPrincipal;
	
	@FXML
    private Button crearVentaBoton;

	@FXML
    private Button cargarDatosBoton;
	
	 private ProductoFacturaService ProductoFacturaService;
	
	AdministradorView administradorView = new AdministradorView();
    GerenteView gerenteView = new GerenteView();
    VendedorView vendedorView = new VendedorView();

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
    
    @FXML
    void onBuscarClienteClicked(MouseEvent event) {

    }

    @FXML
    void onCargarDatosClicked(MouseEvent event) {
    	this.tabla.getItems().clear();
        this.tabla.setItems(this.ProductoFacturaService.getProductosFactura());
    }
    FormularioVentasView fvv = new FormularioVentasView();
    @FXML
    void onCrearVentaClicked(MouseEvent event) throws IOException {
    	fvv.show((Stage) crearVentaBoton.getScene().getWindow());

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.ProductoFacturaService = new ProductoFacturaService();
		codigoFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("codigoFactura"));
		idProductoFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("idProductoFactura"));
		fechaRealizadaColumn.setCellValueFactory(new PropertyValueFactory<>("fechaRealizada"));
		cantidadProductoColumn.setCellValueFactory(new PropertyValueFactory<>("cantidadProducto"));
		precioPorUnidadColumn.setCellValueFactory(new PropertyValueFactory<>("precioPorUnidad"));
        cedulaVendedorColumn.setCellValueFactory(new PropertyValueFactory<>("cedulaVendedor"));
        cedulaClienteColumn.setCellValueFactory(new PropertyValueFactory<>("cedulaCliente"));
	}


}
