package presentacion.vistas.vendedor;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VendedorView {
	public void show(Stage st) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Vendedor.fxml"));
		Scene sc = new Scene(root);
		st.setScene(sc);
		st.show();
	}

}
