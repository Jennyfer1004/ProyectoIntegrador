package presentacion.controladores.ventas;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.gerente.GerenteView;

public class ConsultarVentasController {
	@FXML
	private Button botonVolverPrincipal;

	GerenteView gerenteView = new GerenteView();
	
	@FXML
	public void volverAprincipal(MouseEvent event) throws IOException {
		//if (Usuarios.getRol().equals("administrador")) {
        //	administradorView.show((Stage)botonVolverPrincipal.getScene().getWindow());
        //}else if (Usuarios.getRol().equals("gerente")) {
        gerenteView.show((Stage)botonVolverPrincipal.getScene().getWindow());
        //}else if (Usuarios.getRol().equals("vendedor")) {
        //	vendedorView.show((Stage)botonVolverPrincipal.getScene().getWindow());
        //}else{
        //	mostrarAlerta("Inicio de Sesión Fallido", "Credenciales incorrectas para " ,AlertType.ERROR);
       // }
	}
}
