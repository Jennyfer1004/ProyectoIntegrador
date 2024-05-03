package presentacion.vistas.ventas;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FormularioVentasView {
	
	public void show(Stage st) throws IOException {
		//esto bloque que se pueda cambiar el tamaño//
		st.setResizable(false);
		Parent root = FXMLLoader.load(getClass().getResource("FormularioVentas.fxml"));
		Scene sc = new Scene(root);
		st.setScene(sc);
		st.show();
	}

}
