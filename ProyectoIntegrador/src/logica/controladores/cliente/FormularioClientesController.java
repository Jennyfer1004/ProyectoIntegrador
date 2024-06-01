package logica.controladores.cliente;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import datos.DAOS.ClienteDAO;
import datos.objetos.Usuarios;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.ClienteService;
import presentacion.vistas.cliente.GestionClientesView;

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
    
    @FXML
    private TextField inputEstado;
    
    private ClienteService clienteService;
    
    private ClienteDAO clienteDAO;
    
    GestionClientesView gcv = new GestionClientesView();

    @FXML
    void onRegistrarCliente(MouseEvent event) throws IOException {
    	
    	String cedula = inputCedula.getText();
    	String nombreCompleto = inputNomCompleto.getText();
    	String correo = inputCorreo.getText();
    	String telefono = inputTelefono.getText();
    	String direccion = inputDireccion.getText();
    	String estado = inputEstado.getText();
    	boolean insercionExitosa = clienteDAO.registrarCliente(cedula, nombreCompleto, correo, telefono, direccion, estado);
        
        // Si la inserción fue exitosa, agregar el cliente a la lista observable
        if (insercionExitosa) {
        	mostrarAlerta("CONFIRMACION", "Se registro el cliente correctamente" ,AlertType.CONFIRMATION);
            clienteService.agregarCliente(cedula, nombreCompleto, correo, telefono, direccion, estado);
            
        }
        else {
        	mostrarAlerta("Registro fallido", "No se pudo crear, verifica los campos " ,AlertType.ERROR);
        }
    	

    }


    @FXML
    public void volverAprincipal(@SuppressWarnings("exports") MouseEvent event) throws IOException {
        if (Usuarios.getRol().equals("administrador")) {
        	gcv.show((Stage)botonVolverPrincipal.getScene().getWindow());
        }else if (Usuarios.getRol().equals("vendedor")) {
        	gcv.show((Stage)botonVolverPrincipal.getScene().getWindow());
        }else{
        	mostrarAlerta("Inicio de Sesión Fallido", "Credenciales incorrectas para " ,AlertType.ERROR);
        }
    }
    //metodo que muestra una alerta en pantalla
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
