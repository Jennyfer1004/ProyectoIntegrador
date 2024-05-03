package logica.controladores.proveedor;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.gerente.GerenteView;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ConsultarProveedoresController {
	@FXML
	private TableView tabla;
	@FXML
	private TableColumn nomempresacolumn;
	@FXML
	private TableColumn direccioncolumn;
	@FXML
	private TableColumn telefonocolumn;
	@FXML
	private TableColumn correocolumn;
	@FXML
	private TableColumn productossumicolumn;
	@FXML
	private Button Consultarbtn;
	@FXML
	private Button botonVolver;

	GerenteView gv = new GerenteView();
	@FXML
	public void onVolverClicked(MouseEvent event) throws IOException {
		gv.show((Stage) botonVolver.getScene().getWindow());
		
		
	}
}
