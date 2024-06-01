package logica.controladores.vendedor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import datos.objetos.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.VendedorService;
import presentacion.vistas.gerente.GerenteView;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn;

public class ConsultarVendedoresController implements Initializable{
	@FXML
	private TableView <Vendedor>tabla;
	@FXML
	private TableColumn <Vendedor,String> cedulaColumna;
	@FXML
	private TableColumn <Vendedor,String> nombreCompletoColumna;
	@FXML
	private TableColumn <Vendedor,String> correoColumna;
	@FXML
	private TableColumn <Vendedor,String> telefonoColumna;
	@FXML
	private TableColumn <Vendedor,String> direccionColumna;
	@FXML
	private TableColumn <Vendedor,String> estadoColumna;
	@FXML
	private Button botonConsultarVendedores;
	@FXML
	private Button botonVolvera;
	private VendedorService vendedorService;
	@SuppressWarnings("unused")
	private  ObservableList<Vendedor> listaVendedores;
	
	GerenteView gv = new GerenteView();
	@FXML
	public void onConsultarVendedoresClicked(@SuppressWarnings("exports") MouseEvent event) {
		this.tabla.getItems().clear();
        this.tabla.setItems(this.vendedorService.getVendedores());
	}
	
	@FXML
	public void onVolverClicked(@SuppressWarnings("exports") MouseEvent event) throws IOException {
		gv.show((Stage) botonVolvera.getScene().getWindow());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.vendedorService = new VendedorService();
        this.listaVendedores =  FXCollections.observableArrayList(); 
        
        nombreCompletoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        direccionColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        correoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        telefonoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
        estadoColumna.setCellFactory(TextFieldTableCell.forTableColumn());
		
        cedulaColumna.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        nombreCompletoColumna.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        direccionColumna.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        correoColumna.setCellValueFactory(new PropertyValueFactory<>("correo"));
        telefonoColumna.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        estadoColumna.setCellValueFactory(new PropertyValueFactory<>("estado"));
	}
}
