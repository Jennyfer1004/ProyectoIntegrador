package logica.controladores.usuarios;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.administrador.AdministradorView;
import presentacion.vistas.gerente.GestionGerentesView;
import presentacion.vistas.vendedor.GestionVendedoresView;

public class GestionUsuariosController {
	@FXML
	private Button botonVolverPrincipal;
	@FXML
	private Button botonGestGerentes;
	@FXML
	private Button botonGestVendedores;
	
	AdministradorView administradorView = new AdministradorView();
	GestionGerentesView gestionGerentesView = new GestionGerentesView();
	GestionVendedoresView gestionVendedoresView = new GestionVendedoresView();
	
	@FXML
	public void volverPrincipal(@SuppressWarnings("exports") MouseEvent event) throws IOException {
		administradorView.show((Stage)botonVolverPrincipal.getScene().getWindow());
	}
	@FXML
	public void irGestionGerentes(@SuppressWarnings("exports") MouseEvent event) throws IOException {
		gestionGerentesView.show((Stage)botonGestGerentes.getScene().getWindow());
	}
	@FXML
	public void irGestionVendedores(@SuppressWarnings("exports") MouseEvent event) throws IOException {
		gestionVendedoresView.show((Stage)botonGestVendedores.getScene().getWindow());
	}
}
