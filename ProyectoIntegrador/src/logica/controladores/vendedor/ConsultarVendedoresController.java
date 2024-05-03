package logica.controladores.vendedor;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.gerente.GerenteView;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ConsultarVendedoresController {
	@FXML
	private TableView tabla;
	@FXML
	private TableColumn cedulaColumna;
	@FXML
	private TableColumn nombreCompletoColumna;
	@FXML
	private TableColumn correoColumna;
	@FXML
	private TableColumn telefonoColumna;
	@FXML
	private TableColumn direccionColumna;
	@FXML
	private Button botonConsultarVendedores;
	@FXML
	private Button botonVolver;

	
	GerenteView gv = new GerenteView();
	@FXML
	public void onConsultarVendedoresClicked(MouseEvent event) {
	}
	
	@FXML
	public void onVolverClicked(MouseEvent event) throws IOException {
		gv.show((Stage) botonVolver.getScene().getWindow());
	}
}
