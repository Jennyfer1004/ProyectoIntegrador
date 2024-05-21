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
import presentacion.vistas.gerente.GerenteView;
import presentacion.vistas.iniciarSesion.IniciarSesionView;
import presentacion.vistas.vendedor.VendedorView;

public class GestionClientesController implements Initializable{
	

    @FXML
    private TableColumn<Cliente, String> cedulaColumna;

    @FXML
    private TableColumn<Cliente, String> nombreCompletoColumna;

    @FXML
    private TableColumn<Cliente, String> correoColumna;
    
    @FXML
    private TableColumn<Cliente, String> telefonoColumna;

    @FXML
    private TableColumn<Cliente, String> direccionColumna;
    
    @FXML
    private TableView<Cliente> tabla;    
    
    
	@FXML
    private Button botonVolverPrincipal;
	
	@FXML
    private Button crearClienteBoton;

    
    @FXML
    private Button eliminarClienteBoton;

    @FXML
    private Button cargarDatosBoton;
   
    @FXML
    private Button buscarBoton;
    
    private  ObservableList<Cliente> listaClientes;

    @FXML
    private TextField inputBuscar;
   	
   	private ClienteDAO clienteDAO;
   	
   	private ClienteService clienteService;

    @FXML
    void onCargarDatosClicked(MouseEvent event) {
    	
    	this.tabla.getItems().clear();
        this.tabla.setItems(this.clienteService.getClientes());


    }
    FormularioClientesView fcv = new FormularioClientesView();
    @FXML
    void onCrearClienteClicked(MouseEvent event) throws IOException {
    	
    	fcv.show((Stage)crearClienteBoton.getScene().getWindow());
    	
    }
    @FXML
    void onGuardarCambiosClicked(MouseEvent event) {
    	
        Cliente clienteSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
        	if (clienteSeleccionado.isEditado()) {
        		//boolean actualizado = true;
        		//System.out.println(clienteSeleccionado.getCedula()+clienteSeleccionado.getNombreCompleto()+clienteSeleccionado.getCorreo()+clienteSeleccionado.getDireccion());
        		boolean actualizado = clienteService.editarCliente(clienteSeleccionado);
        	
        		if (actualizado) {
        			mostrarAlerta("Actualización Exitosa", "El cliente se actualizó correctamente.", AlertType.INFORMATION);
        			clienteSeleccionado.setEditado(false);
        		} else {
        			mostrarAlerta("Error", "No se pudo actualizar el cliente.", AlertType.ERROR);
        		}
        	}
        	else {
        		mostrarAlerta("Información", "No hay cambios para guardar.", AlertType.INFORMATION);
        		}
 
        }
        else {
            mostrarAlerta("Error", "Por favor, seleccione un cliente para actualizar.", AlertType.WARNING);
        }
    }
    @FXML
    void onEliminarClienteClicked(MouseEvent event) {

        Cliente clienteSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
        	//System.out.println(clienteSeleccionado.getCedula());
            boolean eliminado = clienteService.eliminarCliente(clienteSeleccionado);
            if (eliminado) {

                tabla.getItems().remove(clienteSeleccionado);
                mostrarAlerta("Eliminación Exitosa", "El cliente ha sido eliminado correctamente.", AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se pudo eliminar el cliente.", AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error", "Por favor, seleccione un cliente para eliminar.", AlertType.WARNING);
        }
    }
    

    @FXML
    void onBuscarClienteClicked(MouseEvent event) {
        String parametro = inputBuscar.getText();
        
        if (!parametro.isEmpty()) {
        	this.tabla.getItems().clear();
            this.tabla.setItems(this.clienteService.buscarCliente(parametro));
        } else {
            mostrarAlerta("Entrada Inválida", "Por favor ingrese el nombre o la cédula del cliente a buscar.", AlertType.WARNING);
        }
    }

    AdministradorView administradorView = new AdministradorView();
    GerenteView gerenteView = new GerenteView();
    VendedorView vendedorView = new VendedorView();

    @FXML
    public void volverAprincipal(MouseEvent event) throws IOException {
        if (Usuarios.getRol().equals("administrador")) {
        	administradorView.show((Stage)botonVolverPrincipal.getScene().getWindow());
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
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	tabla.setEditable(true);
        this.clienteService = new ClienteService();
        this.listaClientes =  FXCollections.observableArrayList(); 
        cedulaColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        nombreCompletoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        correoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        telefonoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        direccionColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        
        // Asignar los valores de las celdas a las propiedades del objeto Cliente
        cedulaColumna.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        nombreCompletoColumna.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        correoColumna.setCellValueFactory(new PropertyValueFactory<>("correo"));
        telefonoColumna.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        direccionColumna.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        // Agregar manejadores para la edición de celdas
        cedulaColumna.setOnEditCommit(event -> {
            Cliente cliente = event.getRowValue();
            cliente.setCedula(event.getNewValue());
            cliente.setEditado(true);
        });
        nombreCompletoColumna.setOnEditCommit(event -> {
            Cliente cliente = event.getRowValue();
            cliente.setNombreCompleto(event.getNewValue());
            cliente.setEditado(true);
        });
        correoColumna.setOnEditCommit(event -> {
            Cliente cliente = event.getRowValue();
            cliente.setCorreo(event.getNewValue());
            cliente.setEditado(true);
        });
        telefonoColumna.setOnEditCommit(event -> {
            Cliente cliente = event.getRowValue();
            cliente.setCorreo(event.getNewValue());
            cliente.setEditado(true);
        });
        direccionColumna.setOnEditCommit(event -> {
            Cliente cliente = event.getRowValue();
            cliente.setDireccion(event.getNewValue());
            cliente.setEditado(true);
        });
        
    }

    
}
