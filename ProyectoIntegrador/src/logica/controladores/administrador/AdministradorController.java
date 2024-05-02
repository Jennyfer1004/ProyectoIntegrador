package logica.controladores.administrador;

import java.io.IOException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentacion.vistas.iniciarSesion.IniciarSesionView;
import presentacion.vistas.cliente.GestionClientesView;
import presentacion.vistas.producto.GestionProductosView;
import presentacion.vistas.proveedor.GestionProveedoresView;
import presentacion.vistas.reportes.GestionReportesView;
import presentacion.vistas.usuarios.GestionUsuariosView;
import presentacion.vistas.vendedor.GestionVendedoresView;
import presentacion.vistas.ventas.GestionVentasView;

public class AdministradorController {
	@FXML
	private Button botonCerrarSesion;
	@FXML
	private Button botonGestVendedor;
	@FXML
	private Button botonGestProductos;
	@FXML
	private Button botonGestCliente;
	@FXML
	private Button botonGestProveedores;
	@FXML
	private Button botonGestVentas;
	@FXML
	private Button botonConsulReportes;
	
	IniciarSesionView iniciarSesionView = new IniciarSesionView();
	GestionProductosView gestionProductosView = new GestionProductosView();
	GestionClientesView gestionClientesView = new GestionClientesView();
	GestionProveedoresView gestionProveedoresView = new GestionProveedoresView();
	GestionVentasView gestionVentasView = new GestionVentasView();
	GestionReportesView gestionReportesView = new GestionReportesView();
	GestionUsuariosView gestionUsuariosView = new GestionUsuariosView();
	GestionVendedoresView  gestionVendedoresView = new GestionVendedoresView();

	@FXML
	public void cerrarSesion(MouseEvent event) throws IOException {
		iniciarSesionView.show((Stage)botonCerrarSesion.getScene().getWindow());
	}
	@FXML
	public void irGestionUsuarios(MouseEvent event) throws IOException {
		gestionVendedoresView.show((Stage)botonGestVendedor.getScene().getWindow());
	}
	@FXML
	public void irGestionProductos(MouseEvent event) throws IOException {
		gestionProductosView.show((Stage)botonGestProductos.getScene().getWindow());
	}
	@FXML
	public void irGestionClientes(MouseEvent event) throws IOException {
		gestionClientesView.show((Stage)botonGestCliente.getScene().getWindow());
	}
	@FXML
	public void irGestionProveedores(MouseEvent event) throws IOException {
		gestionProveedoresView.show((Stage)botonGestProveedores.getScene().getWindow());
	}
	@FXML
	public void irGestionVentas(MouseEvent event) throws IOException {
		gestionVentasView.show((Stage)botonGestVentas.getScene().getWindow());
	}
	@FXML
	public void irConsultarReportes(MouseEvent event) throws IOException {
		gestionReportesView.show((Stage)botonConsulReportes.getScene().getWindow());
	}
}
