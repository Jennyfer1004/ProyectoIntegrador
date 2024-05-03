package logica.controladores.ventas;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.ventas.GestionVentasView;

public class FormularioVentasController {
	@FXML
	private Button botonVolverPrincipal;
	@FXML
	private Button botonCancelar;
	@FXML
	private Button botonRegistrar;
	@FXML
	private TextField inputCodigo;
	@FXML
	private TextField inputFecha;
	@FXML
	private TextField inputCantidad;
	@FXML
	private TextField inputValorUnidad;
	@FXML
	private Label Codigotexfild;
	@FXML
	private Label Nombretexfield;
	@FXML
	private Label Valorcompratexfield;
	@FXML
	private Label Descripciotexfield;
	
	GestionVentasView gvv = new GestionVentasView();

	@FXML
	public void volverAprincipal(MouseEvent event) throws IOException {
		gvv.show((Stage) botonVolverPrincipal.getScene().getWindow());
		
	}
}
