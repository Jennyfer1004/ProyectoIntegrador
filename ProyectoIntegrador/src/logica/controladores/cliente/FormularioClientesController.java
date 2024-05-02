package logica.controladores.cliente;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

import datos.DAOS.ClienteDAO;
import datos.objetos.Cliente;
import datos.objetos.Usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logica.ClienteService;
import presentacion.vistas.administrador.AdministradorView;
import presentacion.vistas.cliente.FormularioClientesView;
import presentacion.vistas.cliente.GestionClientesView;
import presentacion.vistas.gerente.GerenteView;
import presentacion.vistas.iniciarSesion.IniciarSesionView;
import presentacion.vistas.vendedor.VendedorView;

public class FormularioClientesController implements Initializable{

	@FXML
    private Button botonVolverPrincipal;
	
	@FXML
    private Button botonCancelar;

    @FXML
    private Button botonRegistrar;

    @FXML
    private TextField inputCedula;

    @FXML
    private TextField inputCorreo;

    @FXML
    private TextField inputDireccion;

    @FXML
    private TextField inputNomCompleto;

    @FXML
    private TextField inputTelefono;
    
    private ClienteService clienteService;
    
    private ClienteDAO clienteDAO;
    
    GestionClientesView gcv = new GestionClientesView();

    @FXML
    void onRegistrarCliente(MouseEvent event) {
    	
    	String cedula = inputCedula.getText();
    	String nombreCompleto = inputNomCompleto.getText();
    	String correo = inputCorreo.getText();
    	String telefono = inputTelefono.getText();
    	String direccion = inputDireccion.getText();
    	
    	boolean insercionExitosa = clienteDAO.registrarCliente(cedula, nombreCompleto, correo, telefono, direccion);
        
        // Si la inserción fue exitosa, agregar el cliente a la lista observable
        if (insercionExitosa) {
            clienteService.agregarCliente(cedula, nombreCompleto, correo, telefono, direccion);
        }
    	

    }


    @FXML
    public void volverAprincipal(MouseEvent event) throws IOException {
        if (Usuarios.getRol().equals("administrador")) {
        	gcv.show((Stage)botonVolverPrincipal.getScene().getWindow());
        }else if (Usuarios.getRol().equals("vendedor")) {
        	gcv.show((Stage)botonVolverPrincipal.getScene().getWindow());
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
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	 clienteService = new ClienteService();
    	 clienteDAO = new ClienteDAO();
    }

    
}
