package logica.controladores.producto;

import java.io.IOException;

import datos.objetos.Usuarios;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.gerente.GerenteView;
import presentacion.vistas.vendedor.VendedorView;
import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ConsultarProductosController {
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
	private Button btnconsultar;
	@FXML
	private Button botonVolver;
	
	GerenteView gerenteView = new GerenteView();
    VendedorView vendedorView = new VendedorView();

	@FXML
	public void onCargarDatosClicked(MouseEvent event) {
	}
	@FXML
	public void onVolverClicked(MouseEvent event) throws IOException {
		if (Usuarios.getRol().equals("gerente")) {
        	gerenteView.show((Stage) botonVolver.getScene().getWindow());
        }else if (Usuarios.getRol().equals("vendedor")) {
        	vendedorView.show((Stage) botonVolver.getScene().getWindow());
        }
	}
}
