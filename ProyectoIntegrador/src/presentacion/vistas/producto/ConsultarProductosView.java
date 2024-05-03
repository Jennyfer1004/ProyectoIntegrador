package presentacion.vistas.producto;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConsultarProductosView {
	
	public void show(Stage st) throws IOException {
		//esto bloque que se pueda cambiar el tamaño//
		st.setResizable(false);
		Parent root = FXMLLoader.load(getClass().getResource("ConsultarProductos.fxml"));
		Scene sc = new Scene(root);
		st.setScene(sc);
		st.show();
	}

}
