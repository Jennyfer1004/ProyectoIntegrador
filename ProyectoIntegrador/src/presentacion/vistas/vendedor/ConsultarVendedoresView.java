package presentacion.vistas.vendedor;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConsultarVendedoresView {
	
	public void show(Stage st) throws IOException {
		//esto bloque que se pueda cambiar el tamaño//
		st.setResizable(false);
		Parent root = FXMLLoader.load(getClass().getResource("ConsultarVendedores.fxml"));
		Scene sc = new Scene(root);
		st.setScene(sc);
		st.show();
	}

}