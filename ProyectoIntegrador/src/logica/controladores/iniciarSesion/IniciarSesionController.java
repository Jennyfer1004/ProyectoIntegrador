package logica.controladores.iniciarSesion;

import datos.ConexionDB;
import datos.DAOS.Login;
import datos.objetos.Usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.administrador.AdministradorView;
import presentacion.vistas.gerente.GerenteView;
import presentacion.vistas.vendedor.VendedorView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class IniciarSesionController {

    @FXML
    private Label labelIniciarSesion;

    @FXML
    private TextField inputUsuario;

    @FXML
    private PasswordField inputContrasena;

    @FXML
    private Button botonIngresar;

    @FXML
    private RadioButton radioButtonAdmin;

    @FXML
    private RadioButton radioButtonGerente;

    @FXML
    private RadioButton radioButtonVendedor;
    
    @FXML
    private ToggleGroup grupoRadios;
    
    
    private Connection currentConnection;
    
    
    private Login login;
    private ConexionDB conexion;

    public IniciarSesionController() {
        this.login = new Login();
        this.conexion = new ConexionDB();
    }

    @FXML
    void onIngresarClicked(MouseEvent event) throws IOException {
    	
    	//capturo el usuario y la contraseña que ingresa el usuario//
        String usuario = inputUsuario.getText();
        String contrasena = inputContrasena.getText();
        
        //guardo en la variable tipoUsuario el string que me retorna el metodo seleecionarRol //
        String tipoUsuario = seleccionarRol(event);
        
        //pregunto que si tipoUsuario es nulo pues que me muestre la alerta//
        if (tipoUsuario == null) {
            mostrarAlerta("Error", "Debes seleccionar un tipo de usuario. ", AlertType.ERROR);
            return;
        }
        
        try {
            boolean credencialesValidas = login.validarCredenciales(usuario, contrasena);
            if (credencialesValidas) {
                Usuarios.inicializar(usuario, contrasena, tipoUsuario);
                if (currentConnection != null) {
                    conexion.cerrarConexion(currentConnection);
                }
                currentConnection = login.obtenerConexionUsuario(tipoUsuario);
                System.out.println("Inicio de sesión exitoso con el usuario de tipo: " + tipoUsuario);
                cargarVistaUsuario(tipoUsuario);
            } else {
                mostrarAlerta("Inicio de Sesión Fallido", "Credenciales incorrectas para " + tipoUsuario, Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Inicio de Sesión Fallido", "Error al validar las credenciales.", Alert.AlertType.ERROR);
        }
    }
  //instancio estas clases para llamar los metodos .show() que me va a cargar las ventanas//
    AdministradorView administradorView = new AdministradorView();
    GerenteView gerenteView = new GerenteView();
    VendedorView vendedorView = new VendedorView();
        
        private void cargarVistaUsuario(String tipoUsuario) throws IOException {
        	if (Usuarios.getRol().equals("administrador")) {
            	administradorView.show((Stage)botonIngresar.getScene().getWindow());
            }else if (Usuarios.getRol().equals("gerente")) {
            	gerenteView.show((Stage) botonIngresar.getScene().getWindow());
            }else if (Usuarios.getRol().equals("vendedor")) {
            	vendedorView.show((Stage) botonIngresar.getScene().getWindow());
            }
        }
        
        
        public void stop() {
            if (currentConnection != null) {
                try {
                    currentConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        
        @FXML
        public void initialize() {
            grupoRadios = new ToggleGroup();
            radioButtonAdmin.setToggleGroup(grupoRadios);
            radioButtonGerente.setToggleGroup(grupoRadios);
            radioButtonVendedor.setToggleGroup(grupoRadios);
        }
        
        @FXML
        public String seleccionarRol(MouseEvent event) {
        
        	RadioButton selectedRadioButton = (RadioButton) grupoRadios.getSelectedToggle();
            if (selectedRadioButton == radioButtonAdmin) {
                return "administrador";
            } else if (selectedRadioButton == radioButtonGerente) {
                return "gerente";
            } else if (selectedRadioButton == radioButtonVendedor) {
                return "vendedor";
            } else {
                return null;
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