package presentacion.controladores.proveedor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import datos.objetos.Cliente;
import datos.objetos.Proveedor;
import datos.objetos.Usuarios;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import logica.ClienteService;
import logica.ProveedorService;
import presentacion.vistas.administrador.AdministradorView;
import presentacion.vistas.cliente.FormularioClientesView;
import presentacion.vistas.gerente.GerenteView;
import presentacion.vistas.proveedor.FormularioProveedorView;
import presentacion.vistas.vendedor.VendedorView;

public class GestionProveedoresController implements Initializable{
	@FXML
	private Button botonVolverPrincipal;
	AdministradorView administradorView = new AdministradorView();
    GerenteView gerenteView = new GerenteView();
    VendedorView vendedorView = new VendedorView();
	@FXML
	private Button crearProveedorBoton;
	@FXML
	private TableView <Proveedor> tabla;
	@FXML
	private TableColumn <Proveedor, String> idColumna;
	@FXML
	private TableColumn <Proveedor, String> nombreEmpresaColumna;
	@FXML
	private TableColumn <Proveedor, String> direccionColumna;
	@FXML
	private TableColumn <Proveedor, String> telefonoColumna;
	@FXML
	private TableColumn <Proveedor, String> correoColumna;
	@FXML
	private TableColumn <Proveedor, String> productosSuministradosColumna;
	@FXML
	private Button eliminarProveedorBoton;
	@FXML
	private TextField inputBuscar;
	@FXML
	private Button buscarBoton;
	@FXML
	private Button cargarDatosBoton;
	
	private ProveedorService proveedorService;
	private  ObservableList<Proveedor> listaProveedores;

	// Event Listener on Button[#botonVolverPrincipal1].onMouseClicked
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
	    void onBuscarProveedorClicked(MouseEvent event) {
		 String parametro = inputBuscar.getText();
	        
	        if (!parametro.isEmpty()) {
	        	this.tabla.getItems().clear();
	            this.tabla.setItems(this.proveedorService.buscarProveedor(parametro));
	        } else {
	            mostrarAlerta("Entrada Inválida", "Por favor ingrese el nombre o el id del Proveedor a buscar.", AlertType.WARNING);
	        }
	    }

	    @FXML
	    void onCargarDatosClicked(MouseEvent event) {
	    	this.tabla.getItems().clear();
	        this.tabla.setItems(this.proveedorService.getProveedores());
	    }
	    FormularioProveedorView fpv = new FormularioProveedorView();
	    @FXML
	    void onCrearProveedorClicked(MouseEvent event) throws IOException {
	    	fpv.show((Stage)crearProveedorBoton.getScene().getWindow());
	    }

	    @FXML
	    void onEliminarProveedorClicked(MouseEvent event) {
	        Proveedor  proveedorSeleccionado= tabla.getSelectionModel().getSelectedItem();
	        if (proveedorSeleccionado != null) {
	        	//System.out.println(clienteSeleccionado.getCedula());
	            boolean eliminado = proveedorService.eliminarProveedor(proveedorSeleccionado);
	            if (eliminado) {

	                tabla.getItems().remove(proveedorSeleccionado);
	                mostrarAlerta("Eliminación Exitosa", "El Proveedor ha sido eliminado correctamente.", AlertType.INFORMATION);
	            } else {
	                mostrarAlerta("Error", "No se pudo eliminar el Proveedor.", AlertType.ERROR);
	            }
	        } else {
	            mostrarAlerta("Error", "Por favor, seleccione un Proveedor para eliminar.", AlertType.WARNING);
	        }
	    }

	    @FXML
	    void onGuardarCambiosClicked(MouseEvent event) {
	    	  Proveedor proveedorSeleccionado = tabla.getSelectionModel().getSelectedItem();
	          if (proveedorSeleccionado != null) {
	          	if (proveedorSeleccionado.isEditado()) {
	          		//boolean actualizado = true;
	          		//System.out.println(clienteSeleccionado.getCedula()+clienteSeleccionado.getNombreCompleto()+clienteSeleccionado.getCorreo()+clienteSeleccionado.getDireccion());
	          		boolean actualizado = proveedorService.editarProveedor(proveedorSeleccionado);
	          	
	          		if (actualizado) {
	          			mostrarAlerta("Actualización Exitosa", "El Proveedor se actualizó correctamente.", AlertType.INFORMATION);
	          			proveedorSeleccionado.setEditado(false);
	          		} else {
	          			mostrarAlerta("Error", "No se pudo actualizar el Proveedor.", AlertType.ERROR);
	          		}
	          	}
	          	else {
	          		mostrarAlerta("Información", "No hay cambios para guardar.", AlertType.INFORMATION);
	          		}
	   
	          }
	          else {
	              mostrarAlerta("Error", "Por favor, seleccione un Proveedor para actualizar.", AlertType.WARNING);
	          }
	    }
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			tabla.setEditable(true);
	        this.proveedorService = new ProveedorService();
	        this.listaProveedores =  FXCollections.observableArrayList(); 
	        idColumna.setCellFactory(TextFieldTableCell.forTableColumn());
	        nombreEmpresaColumna.setCellFactory(TextFieldTableCell.forTableColumn());
	        direccionColumna.setCellFactory(TextFieldTableCell.forTableColumn());
	        telefonoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
	        correoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
	        productosSuministradosColumna.setCellFactory(TextFieldTableCell.forTableColumn());
	        
	        // Asignar los valores de las celdas a las propiedades del objeto Proveedor
	        idColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
	        nombreEmpresaColumna.setCellValueFactory(new PropertyValueFactory<>("nombreEmpresa"));
	        direccionColumna.setCellValueFactory(new PropertyValueFactory<>("direccion"));
	        telefonoColumna.setCellValueFactory(new PropertyValueFactory<>("telefono"));
	        correoColumna.setCellValueFactory(new PropertyValueFactory<>("correo"));
	        productosSuministradosColumna.setCellValueFactory(new PropertyValueFactory<>("productosSuministrados"));

	        // Agregar manejadores para la edición de celdas
	        idColumna.setOnEditCommit(event -> {
	            Proveedor proveedor = event.getRowValue();
	            proveedor.setId(event.getNewValue());
	            proveedor.setEditado(true);
	        });
	        nombreEmpresaColumna.setOnEditCommit(event -> {
	        	Proveedor proveedor = event.getRowValue();
	        	proveedor.setNombreEmpresa(event.getNewValue());
	        	proveedor.setEditado(true);
	        });
	        direccionColumna.setOnEditCommit(event -> {
	        	Proveedor proveedor = event.getRowValue();
	        	proveedor.setDireccion(event.getNewValue());
	        	proveedor.setEditado(true);
	        });
	        telefonoColumna.setOnEditCommit(event -> {
	        	Proveedor proveedor = event.getRowValue();
	        	proveedor.setTelefono(event.getNewValue());
	        	proveedor.setEditado(true);
	        });
	        correoColumna.setOnEditCommit(event -> {
	        	Proveedor proveedor = event.getRowValue();
	        	proveedor.setCorreo(event.getNewValue());
	        	proveedor.setEditado(true);
	        });
	        productosSuministradosColumna.setOnEditCommit(event -> {
	        	Proveedor proveedor = event.getRowValue();
	        	proveedor.setProductosSuministrados(event.getNewValue());
	        	proveedor.setEditado(true);
	        });
	        
			
		}
}











