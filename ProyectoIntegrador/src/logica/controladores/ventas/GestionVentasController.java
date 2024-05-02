package logica.controladores.ventas;

import java.io.IOException;

import datos.objetos.Usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.administrador.AdministradorView;
import presentacion.vistas.gerente.GerenteView;
import presentacion.vistas.vendedor.VendedorView;

public class GestionVentasController {
	@FXML
    private Button botonVolverPrincipal;
	
	AdministradorView administradorView = new AdministradorView();
    GerenteView gerenteView = new GerenteView();
    VendedorView vendedorView = new VendedorView();

    @FXML
    void volverAprincipal(MouseEvent event) throws IOException {
    	if (Usuarios.getRol().equals("administrador")) {
        	administradorView.show((Stage)botonVolverPrincipal.getScene().getWindow());
        }else if (Usuarios.getRol().equals("gerente")) {
        	gerenteView.show((Stage)botonVolverPrincipal.getScene().getWindow());
        }else if (Usuarios.getRol().equals("vendedor")) {
        	vendedorView.show((Stage)botonVolverPrincipal.getScene().getWindow());
        }else{
        	mostrarAlerta("Inicio de Sesión Fallido", "Credenciales incorrectas para " ,AlertType.ERROR);
        }

    }
    
    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }



}
