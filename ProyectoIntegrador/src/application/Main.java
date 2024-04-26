package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import presentacion.vistas.iniciarSesion.IniciarSesionView;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			IniciarSesionView iniciarSesionView = new IniciarSesionView();
			iniciarSesionView.show(primaryStage);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
