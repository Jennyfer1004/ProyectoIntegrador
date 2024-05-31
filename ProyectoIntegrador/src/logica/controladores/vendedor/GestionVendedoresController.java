package logica.controladores.vendedor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import datos.objetos.Cliente;
import datos.objetos.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.ClienteService;
import logica.VendedorService;
import presentacion.vistas.administrador.AdministradorView;
import presentacion.vistas.cliente.FormularioClientesView;
import presentacion.vistas.vendedor.FormularioVendedorView;

public class GestionVendedoresController implements Initializable{
	
	
	@FXML
    private TableView<Vendedor> tabla;
	
	@FXML
    private TableColumn<Vendedor, String> cedulaColumna;
	
    @FXML
    private TableColumn<Vendedor, String> nombreCompletoColumna;

    @FXML
    private TableColumn<Vendedor, String> direccionColumna;
    
	@FXML
    private TableColumn<Vendedor, String> correoColumna;
	
    @FXML
    private TableColumn<Vendedor, String> telefonoColumna;
	

    @FXML
    private TableColumn<Vendedor, String> estadoColumna;

    @FXML
    private Button crearVendedorBoton;

    @FXML
	private Button botonVolverPrincipal;

    @FXML
    private Button editarVendedorBoton;

    @FXML
    private Button eliminarVendedorBoton;
    
    @FXML
    private Button buscarBoton;
    
    @FXML
    private Button cargarDatosBoton;

    @FXML
    private TextField inputBuscar;
    
    private  ObservableList<Vendedor> listaVendedor;
    
    private VendedorService vendedorService;
 
    AdministradorView adminView = new AdministradorView();
    
    private String cedulaOriginal;

    

    @FXML
    void onBuscarVendedorClicked(MouseEvent event) {
    	
    	String parametro = inputBuscar.getText();
        
        if (!parametro.isEmpty()) {
        	this.tabla.getItems().clear();
            this.tabla.setItems(this.vendedorService.buscarVendedor(parametro));
        } else {
            mostrarAlerta("Entrada Inválida", "Por favor ingrese el nombre o la cédula del vendedor a buscar.", AlertType.WARNING);
        }

    }

    @FXML
    void onCargarDatosClicked(MouseEvent event) {
    	
    	this.tabla.getItems().clear();
        this.tabla.setItems(this.vendedorService.getVendedores());

    }

    
    FormularioVendedorView fvv = new FormularioVendedorView();
    @FXML
    void onCrearVendedorClicked(MouseEvent event) throws IOException {
    	
    	fvv.show((Stage)crearVendedorBoton.getScene().getWindow());

    }

    @FXML
    void onEliminarVendedorClicked(MouseEvent event) {
    	
        Vendedor vendedorSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (vendedorSeleccionado != null) {
        	String cedula = vendedorSeleccionado.getCedula();
        	String estado = vendedorSeleccionado.getEstado();
        	if ("Inactivo".equals(estado)) {
                mostrarAlerta("Error", "El vendedor ya está inactivo y no se puede eliminar nuevamente.", AlertType.ERROR);
                return; // Salir del método sin continuar con la eliminación
            }
        	//System.out.println(clienteSeleccionado.getCedula());
            boolean eliminado = vendedorService.eliminarVendedor(cedula);
            if (eliminado) {

                mostrarAlerta("Eliminación Exitosa", "El vendedor ha sido eliminado correctamente.", AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se pudo eliminar el vendedor.", AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error", "Por favor, seleccione un vendedor para eliminar.", AlertType.WARNING);
        }

    }

    
    
    
    @FXML
    void onGuardarCambiosClicked(MouseEvent event) {
    	
        Vendedor vendedorSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (vendedorSeleccionado != null) {
        	
        	
        	if (vendedorSeleccionado.isEditado()) {
        		//boolean actualizado = true;
        		//System.out.println(clienteSeleccionado.getCedula()+clienteSeleccionado.getNombreCompleto()+clienteSeleccionado.getCorreo()+clienteSeleccionado.getDireccion());
        		boolean actualizado = vendedorService.editarVendedor(vendedorSeleccionado);
        	
        		if (actualizado) {
        			mostrarAlerta("Actualización Exitosa", "El vendedor se actualizó correctamente.", AlertType.INFORMATION);
        			vendedorSeleccionado.setEditado(false);
        		} else {
        			mostrarAlerta("Error", "No se pudo actualizar el vendedor.", AlertType.ERROR);
        		}
        	}
        	else {
        		mostrarAlerta("Información", "No hay cambios para guardar.", AlertType.INFORMATION);
        		}
 
        }
        else {
            mostrarAlerta("Error", "Por favor, seleccione un vendedor para actualizar.", AlertType.WARNING);
        }

    }
	

	// Event Listener on Button[#botonVolverPrincipal].onMouseClicked
	@FXML
	public void volverAprincipal(MouseEvent event) throws IOException {
		adminView.show((Stage)botonVolverPrincipal.getScene().getWindow());
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
        this.vendedorService = new VendedorService();
        this.listaVendedor =  FXCollections.observableArrayList(); 
       // cedulaColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        nombreCompletoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        direccionColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        correoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        telefonoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        estadoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        
        // Asignar los valores de las celdas a las propiedades del objeto Cliente
        cedulaColumna.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        nombreCompletoColumna.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        direccionColumna.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        correoColumna.setCellValueFactory(new PropertyValueFactory<>("correo"));
        telefonoColumna.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        estadoColumna.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        // Agregar manejadores para la edición de celdas
        //cedulaColumna.setOnEditCommit(event -> {
        //    Vendedor vendedor = event.getRowValue();
        //    vendedor.setCedula(event.getNewValue());
        //    vendedor.setEditado(true);
        //});
        nombreCompletoColumna.setOnEditCommit(event -> {
        	Vendedor vendedor = event.getRowValue();
        	vendedor.setNombreCompleto(event.getNewValue());
        	vendedor.setEditado(true);
        });

        direccionColumna.setOnEditCommit(event -> {
        	Vendedor vendedor = event.getRowValue();
        	vendedor.setDireccion(event.getNewValue());
        	vendedor.setEditado(true);
        });
        correoColumna.setOnEditCommit(event -> {
        	Vendedor vendedor = event.getRowValue();
        	vendedor.setCorreo(event.getNewValue());
        	vendedor.setEditado(true);
        });
        telefonoColumna.setOnEditCommit(event -> {
        	Vendedor vendedor = event.getRowValue();
        	vendedor.setTelefono(event.getNewValue());
        	vendedor.setEditado(true);
        });
        estadoColumna.setOnEditCommit(event -> {
        	Vendedor vendedor = event.getRowValue();
        	vendedor.setEstado(event.getNewValue());
        	vendedor.setEditado(true);
        });
	}
}
