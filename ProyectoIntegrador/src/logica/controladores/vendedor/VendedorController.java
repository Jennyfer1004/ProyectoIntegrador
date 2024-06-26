package logica.controladores.vendedor;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.iniciarSesion.IniciarSesionView;
import presentacion.vistas.producto.ConsultarProductosView;
import presentacion.vistas.cliente.GestionClientesView;
import presentacion.vistas.ventas.GestionVentasView;

public class VendedorController {
	@FXML
	private Button botonCerrarSesion;
	
	@FXML
    private Button botonGestionClientes;

    @FXML
    private Button botonGestionVentas;

    @FXML
    private Button botonConsulProductos;

	IniciarSesionView iniciarSesionView = new IniciarSesionView();
	GestionClientesView gestionClientesView = new GestionClientesView();
	GestionVentasView gestionVentasView = new GestionVentasView();
	ConsultarProductosView cpv = new ConsultarProductosView();

	@FXML
	public void cerrarSesion(@SuppressWarnings("exports") MouseEvent event) throws IOException {
		iniciarSesionView.show((Stage)botonCerrarSesion.getScene().getWindow());
	}
	
	@FXML
    void irConsulProductos(MouseEvent event) throws IOException {
		cpv.show((Stage) botonConsulProductos.getScene().getWindow());
	
    }

    @FXML
    void irGestionClientes(MouseEvent event) throws IOException {
    	gestionClientesView.show((Stage)botonGestionClientes.getScene().getWindow());

    }

    @FXML
    void irGestionVentas(MouseEvent event) throws IOException {
    	gestionVentasView.show((Stage)botonGestionVentas.getScene().getWindow());

    }
}
