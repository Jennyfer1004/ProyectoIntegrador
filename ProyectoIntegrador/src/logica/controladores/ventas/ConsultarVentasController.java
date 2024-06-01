package logica.controladores.ventas;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.gerente.GerenteView;
import logica.ProductoFacturaService;
import datos.objetos.ProductoFactura;

public class ConsultarVentasController {
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
    private Button consultarBtn;

    @FXML
    private Button botonVolver;

    private GerenteView gerenteView = new GerenteView();
    private ProductoFacturaService productoFacturaService = new ProductoFacturaService();
    private ObservableList<ProductoFactura> productosFactura ;

    @FXML
    public void initialize() {
    	this.productoFacturaService = new ProductoFacturaService();
        codigoFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        idProductoFacturaColumn.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        fechaRealizadaColumn.setCellValueFactory(new PropertyValueFactory<>("fechaRealizada"));
        cantidadProductoColumn.setCellValueFactory(new PropertyValueFactory<>("cantidadProducto"));
        precioPorUnidadColumn.setCellValueFactory(new PropertyValueFactory<>("precioPorUnidad"));
        cedulaVendedorColumn.setCellValueFactory(new PropertyValueFactory<>("cedulaVendedor"));
        cedulaClienteColumn.setCellValueFactory(new PropertyValueFactory<>("cedulaCliente"));

        cargarDatos();
    }

    @FXML
    public void consultarClicked(MouseEvent event) {
        cargarDatos();
    }

    @FXML
    public void onVolverClicked(MouseEvent event) throws IOException {
        gerenteView.show((Stage) botonVolver.getScene().getWindow());
    }

    private void cargarDatos() {
        productosFactura.clear();
        productosFactura.addAll(productoFacturaService.getProductosFactura());
        tabla.setItems(productosFactura);
    }
}
