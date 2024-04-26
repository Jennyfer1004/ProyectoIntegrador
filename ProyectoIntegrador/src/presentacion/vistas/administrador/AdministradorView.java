package presentacion.vistas.administrador;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdministradorView {
	
	public void show(Stage st) throws IOException {
		//esto bloquea que se pueda cambiar el tamaño//
		st.setResizable(false);
		Parent root = FXMLLoader.load(getClass().getResource("Administrador.fxml"));
		Scene sc = new Scene(root);
		st.setScene(sc);
		st.show();
	}


}
