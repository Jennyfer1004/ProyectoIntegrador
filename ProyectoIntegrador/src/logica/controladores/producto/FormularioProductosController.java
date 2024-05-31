package logica.controladores.producto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import datos.DAOS.ProductoDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.ProductoService;
import presentacion.vistas.producto.GestionProductosView;

public class FormularioProductosController implements Initializable{
	@FXML
	private Button botonVolverPrincipal;
	@FXML
	private Button botonCancelar;
	@FXML
	private Button botonRegistrar;
	@FXML
	private TextField inputCodigo;
	@FXML
	private TextField inputNombre;
	@FXML
	private TextField inputDescripcion;
	@FXML
	private TextField inputValorCompra;
	@FXML
	private TextField inputStock;
	@FXML
	private TextField inputEstado;
	@FXML
	private TextField inputProveedor;
	@FXML
	private Label Codigotexfild;
	@FXML
	private Label Nombretexfield;
	@FXML
	private Label Valorcompratexfield;
	@FXML
	private Label stockfield;
	@FXML
	private Label Descripciotexfield;
	
	private ProductoService productoService;
    
    private ProductoDAO productoDAO;
	
	
	 @FXML
	 void crearProductoClicked(MouseEvent event) {
		String codigo = inputCodigo.getText();
		String nombre = inputNombre.getText();
	    String descripcion = inputDescripcion.getText();
	    String valorCompra = inputValorCompra.getText();
	    String stock = inputStock.getText();
	    String estado = inputEstado.getText();
	    String nombreProveedor = inputProveedor.getText();
	    
        boolean insercionExitosa = productoDAO.registrarProducto(codigo, nombre, descripcion, valorCompra, stock, estado, nombreProveedor);
        
        // Si la inserción fue exitosa, agregar el producto a la lista observable
        if (insercionExitosa) {
        	mostrarAlerta("CONFIRMACION", "Se creo el producto correctamente" ,AlertType.CONFIRMATION);
        	productoService.agregarProducto(codigo, nombre, descripcion, valorCompra, stock, estado, nombreProveedor);
            
        }
        else {
        	mostrarAlerta("Registro fallido", "No se pudo crear, intentalo de nuevo " ,AlertType.ERROR);
        }

    }
	
	GestionProductosView gpv = new GestionProductosView();

	// Event Listener on Button[#botonVolverPrincipal].onMouseClicked
	@FXML
	public void volverAprincipal(MouseEvent event) throws IOException {
		gpv.show((Stage) botonVolverPrincipal.getScene().getWindow());
		
	}
	
	//metodo que muestra una alerta en pantalla
    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		productoService = new ProductoService();
		productoDAO = new ProductoDAO();
		// TODO Auto-generated method stub
		
	}
}
