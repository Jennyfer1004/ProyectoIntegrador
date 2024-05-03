package logica.controladores.ventas;

import java.io.IOException;

import datos.objetos.Usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.administrador.AdministradorView;
import presentacion.vistas.gerente.GerenteView;
import presentacion.vistas.vendedor.VendedorView;

public class GestionVentasController {
	
    @FXML
    private Button buscarBoton;

    @FXML
    private TableColumn<?, ?> cantidaddeproductoscolumn;

    @FXML
    private Button cargarDatosBoton;

    @FXML
    private TableColumn<?, ?> codigocolumn;

    @FXML
    private Button crearVentaBoton;

    @FXML
    private Button eliminarVentaBoton;

    @FXML
    private TableColumn<?, ?> fecharealizadacolumn;

    @FXML
    private TextField inputBuscar;

    @FXML
    private TableView<?> tabla;

    @FXML
    private TableColumn<?, ?> valorporunidadcolumn;
	
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
    
    @FXML
    void onBuscarClienteClicked(MouseEvent event) {

    }

    @FXML
    void onCargarDatosClicked(MouseEvent event) {

    }

    @FXML
    void onCrearVentaClicked(MouseEvent event) {

    }

    @FXML
    void onEliminarVentaClicked(MouseEvent event) {

    }

    @FXML
    void onGuardarCambiosClicked(MouseEvent event) {

    }



}
