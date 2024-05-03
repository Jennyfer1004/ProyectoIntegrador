package logica.controladores.gerente;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.iniciarSesion.IniciarSesionView;
import presentacion.vistas.producto.ConsultarProductosView;
import presentacion.vistas.proveedor.ConsultarProveedoresView;
import presentacion.vistas.reportes.ConsultarReportesView;
import presentacion.vistas.vendedor.ConsultarVendedoresView;
import presentacion.vistas.ventas.ConsultarVentasView;

public class GerenteController {
	@FXML
	private Button botonCerrarSesion;
	@FXML
	private Button botonConsultarVendedores;
	@FXML
	private Button botonConsultarProductos;
	@FXML
	private Button botonConsultarProveedores;
	@FXML
	private Button botonConsFacturas;
	@FXML
	private Button botonConsulReportes;
	
	
	
	IniciarSesionView iniciarSesionView = new IniciarSesionView();
	ConsultarProductosView cpv = new ConsultarProductosView();
	ConsultarReportesView crv = new ConsultarReportesView();
	ConsultarVentasView cvv = new ConsultarVentasView();
	ConsultarProveedoresView cprv = new ConsultarProveedoresView();
	ConsultarVendedoresView cvev = new ConsultarVendedoresView();

	@FXML
	public void cerrarSesion(MouseEvent event) throws IOException {
		iniciarSesionView.show((Stage)botonCerrarSesion.getScene().getWindow());
	}
	@FXML
	public void onConsultarVendedoresClicked(MouseEvent event) throws IOException {
		cvev.show((Stage) botonConsultarVendedores.getScene().getWindow());
		
	}
	@FXML
	public void onConsultarProductosClicked(MouseEvent event) throws IOException {
		cpv.show((Stage) botonConsultarProductos.getScene().getWindow());
	}
	@FXML
	public void onConsultarProveedoresClicked(MouseEvent event) throws IOException {
		cprv.show((Stage) botonConsultarProveedores.getScene().getWindow());
	}
	@FXML
	public void onConsultarFacturasClicked(MouseEvent event) throws IOException {
		cvv.show((Stage) botonConsFacturas.getScene().getWindow());
	}
	@FXML
	public void onConsultarReportesClicked(MouseEvent event) throws IOException {
		crv.show((Stage) botonConsulReportes.getScene().getWindow());
	}
}
