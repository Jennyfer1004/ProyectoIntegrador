package presentacion.controladores.vendedor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import datos.DAOS.VendedorDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logica.VendedorService;
import presentacion.vistas.vendedor.GestionVendedoresView;

public class FormularioVendedorController implements Initializable{
	@FXML
	private Button botonVolverPrincipal;
	@FXML
	private Button botonCancelar;
	@FXML
	private Button botonRegistrar;
	@FXML
	private TextField inputCedula;
	@FXML
	private TextField inputNomCompleto;
	@FXML
	private TextField inputCorreo;
	@FXML
	private TextField inputTelefono;
	@FXML
	private TextField inputDireccion;
	
    private VendedorDAO vendedorDAO;
    
    private VendedorService vendedorService;
    
    GestionVendedoresView gvv = new GestionVendedoresView();

	@FXML
	public void volverAprincipal(MouseEvent event) throws IOException {
		
		gvv.show((Stage)botonVolverPrincipal.getScene().getWindow());
	}
	@FXML
	public void onRegistrarVendedor(MouseEvent event) {
		
		String cedula = inputCedula.getText();
    	String nombreCompleto = inputNomCompleto.getText();
    	String correo = inputCorreo.getText();
    	String telefono = inputTelefono.getText();
    	String direccion = inputDireccion.getText();
    	
    	boolean insercionExitosa = vendedorDAO.registrarVendedor(cedula, nombreCompleto, correo, telefono, direccion);
        
        // Si la inserción fue exitosa, agregar el cliente a la lista observable
        if (insercionExitosa) {
        	vendedorService.agregarVendedor(cedula, nombreCompleto, correo, telefono, direccion);
        }
		
	}
	
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	vendedorService = new VendedorService();
    	vendedorDAO = new VendedorDAO();
    }
}
