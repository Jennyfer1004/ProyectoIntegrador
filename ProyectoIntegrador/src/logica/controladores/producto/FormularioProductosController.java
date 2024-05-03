package logica.controladores.producto;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.producto.GestionProductosView;

public class FormularioProductosController {
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
	private Label Codigotexfild;
	@FXML
	private Label Nombretexfield;
	@FXML
	private Label Valorcompratexfield;
	@FXML
	private Label stockfield;
	@FXML
	private Label Descripciotexfield;
	
	GestionProductosView gpv = new GestionProductosView();

	// Event Listener on Button[#botonVolverPrincipal].onMouseClicked
	@FXML
	public void volverAprincipal(MouseEvent event) throws IOException {
		gpv.show((Stage) botonVolverPrincipal.getScene().getWindow());
		
	}
}
