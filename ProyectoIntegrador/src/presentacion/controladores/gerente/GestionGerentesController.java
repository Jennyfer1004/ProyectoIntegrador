package presentacion.controladores.gerente;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.usuarios.GestionUsuariosView;

public class GestionGerentesController {
	@FXML
	private Button botonVolverPrincipal;
	
	GestionUsuariosView gestionUsuariosView = new GestionUsuariosView();

	@FXML
	public void volverAprincipal(MouseEvent event) throws IOException {
		gestionUsuariosView.show((Stage)botonVolverPrincipal.getScene().getWindow());
		
	}
}
