package presentacion.vistas.iniciarSesion;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IniciarSesionView {
	public void show(Stage st) throws IOException {
		//esto bloque que se pueda cambiar el tamaño//
		st.setResizable(false);
		Parent root = FXMLLoader.load(getClass().getResource("IniciarSesion.fxml"));
		Scene sc = new Scene(root, 1000,500);
		st.setScene(sc);
		st.show();
	}

}
