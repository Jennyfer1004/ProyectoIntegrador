package logica.controladores.proveedor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import datos.objetos.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.ProveedorService;
import presentacion.vistas.gerente.GerenteView;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn;

public class ConsultarProveedoresController implements Initializable {
	@FXML
	private TableView <Proveedor> tabla;
	@FXML
	private TableColumn <Proveedor, String> nombreempresacolumn;
	@FXML
	private TableColumn <Proveedor, String> direccioncolumn;
	@FXML
	private TableColumn <Proveedor, String> telefonocolumn;
	@FXML
	private TableColumn <Proveedor, String> correocolumn;
	@FXML
	private TableColumn <Proveedor, String> productossumicolumn;
	@FXML
	private TableColumn <Proveedor, String> estadocolumn;
	@FXML
	private Button Consultarbtn;
	@FXML
	private Button botonVolver;
	
	private ProveedorService proveedorService;
	@SuppressWarnings("unused")
	private  ObservableList<Proveedor> listaProveedores;

	GerenteView gv = new GerenteView();
	@FXML
	public void onVolverClicked(@SuppressWarnings("exports") MouseEvent event) throws IOException {
		gv.show((Stage) botonVolver.getScene().getWindow());
		
		
	}
	@FXML
    void onConsultarClicked(MouseEvent event) {
		 System.out.println("consulta");
		 this.tabla.getItems().clear();
	     this.tabla.setItems(this.proveedorService.getProveedores());
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.proveedorService = new ProveedorService();
        this.listaProveedores =  FXCollections.observableArrayList(); 
        
        nombreempresacolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        direccioncolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telefonocolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        correocolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        productossumicolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        estadocolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        nombreempresacolumn.setCellValueFactory(new PropertyValueFactory<>("nombreEmpresa"));
        direccioncolumn.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        telefonocolumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        correocolumn.setCellValueFactory(new PropertyValueFactory<>("correo"));
        productossumicolumn.setCellValueFactory(new PropertyValueFactory<>("productosSuministrados"));
        estadocolumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
		
	}
}
