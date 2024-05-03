package logica.controladores.ventas;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.gerente.GerenteView;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ConsultarVentasController {
	@FXML
	private TableView tabla;
	@FXML
	private TableColumn coodigocolumn;
	@FXML
	private TableColumn fecharealizadacolumn;
	@FXML
	private TableColumn cantidadproductoscolumn;
	@FXML
	private TableColumn valorunidadcolumn;
	@FXML
	private Button Consultarbtn;
	
	@FXML
	private Button botonVolver;
	
	GerenteView gerenteView = new GerenteView();


	@FXML
	public void consultarclicked(MouseEvent event) {
	}
	
	@FXML
	public void onVolverClicked(MouseEvent event) throws IOException {
    	gerenteView.show((Stage) botonVolver.getScene().getWindow());

	}
}
