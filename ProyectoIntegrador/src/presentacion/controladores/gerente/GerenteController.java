package presentacion.controladores.gerente;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.iniciarSesion.IniciarSesionView;
import presentacion.vistas.producto.GestionProductosView;
import presentacion.vistas.proveedor.GestionProveedoresView;
import presentacion.vistas.reportes.GestionReportesView;
import presentacion.vistas.vendedor.GestionVendedoresView;
import presentacion.vistas.ventas.ConsultarVentasView;

public class GerenteController {
	@FXML
	private Button botonCerrarSesion;
	
	@FXML
    private Button botonGestVendedores;

    @FXML
    private Button botonGestProductos;

    @FXML
    private Button botonGestProveedores;

    @FXML
    private Button boronConsFacturas;

    @FXML
    private Button botonConsulReportes;
	
	IniciarSesionView iniciarSesionView = new IniciarSesionView();
	GestionProductosView gestionProductosView = new GestionProductosView();
	GestionProveedoresView gestionProveedoresView = new GestionProveedoresView();
	GestionReportesView gestionReportesView = new GestionReportesView();
	GestionVendedoresView gestionVendedoresView = new GestionVendedoresView();
	ConsultarVentasView consultarVentasView = new ConsultarVentasView();
	
	@FXML
	public void cerrarSesion(MouseEvent event) throws IOException {
		iniciarSesionView.show((Stage)botonCerrarSesion.getScene().getWindow());
	}
	
	@FXML
    void irConsulFacturas(MouseEvent event) throws IOException {
		consultarVentasView.show((Stage)boronConsFacturas.getScene().getWindow());

    }

    @FXML
    void irConsulReportes(MouseEvent event) throws IOException {
    	gestionReportesView.show((Stage)botonConsulReportes.getScene().getWindow());

    }

    @FXML
    void irGestionProductos(MouseEvent event) throws IOException {
    	gestionProductosView.show((Stage)botonGestProductos.getScene().getWindow());

    }

    @FXML
    void irGestionProveedores(MouseEvent event) throws IOException {
    	gestionProveedoresView.show((Stage)botonGestProveedores.getScene().getWindow());

    }

    @FXML
    void irGestionVendedores(MouseEvent event) throws IOException {
    	gestionVendedoresView.show((Stage)botonGestVendedores.getScene().getWindow());

    }
}
