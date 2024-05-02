module ProyectoIntegrador {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
	requires javafx.base;

    opens application to javafx.graphics, javafx.fxml;
    
    exports logica.controladores.administrador;
    opens logica.controladores.administrador to javafx.fxml;
    
    exports logica.controladores.cliente;
    opens logica.controladores.cliente to javafx.fxml;
    
    exports logica.controladores.gerente;
    opens logica.controladores.gerente to javafx.fxml;
    
    
    exports logica.controladores.iniciarSesion;
    opens logica.controladores.iniciarSesion to javafx.fxml;
    
    exports logica.controladores.proveedor;
    opens logica.controladores.proveedor to javafx.fxml;
    
    exports logica.controladores.producto;
    opens logica.controladores.producto to javafx.fxml;
    
    exports logica.controladores.reportes;
    opens logica.controladores.reportes to javafx.fxml;
    
    exports logica.controladores.usuarios;
    opens logica.controladores.usuarios to javafx.fxml;
    
    exports logica.controladores.vendedor;
    opens logica.controladores.vendedor to javafx.fxml;
    
    exports logica.controladores.ventas;
    opens logica.controladores.ventas to javafx.fxml;
    
    opens datos.objetos to javafx.base;
    
}
   

    

