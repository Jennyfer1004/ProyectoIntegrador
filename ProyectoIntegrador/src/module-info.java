module ProyectoIntegrador {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
	requires javafx.base;

    opens application to javafx.graphics, javafx.fxml;
    
    exports presentacion.controladores.administrador;
    opens presentacion.controladores.administrador to javafx.fxml;
    
    exports presentacion.controladores.cliente;
    opens presentacion.controladores.cliente to javafx.fxml;
    
    exports presentacion.controladores.gerente;
    opens presentacion.controladores.gerente to javafx.fxml;
    
    
    exports presentacion.controladores.iniciarSesion;
    opens presentacion.controladores.iniciarSesion to javafx.fxml;
    
    exports presentacion.controladores.proveedor;
    opens presentacion.controladores.proveedor to javafx.fxml;
    
    exports presentacion.controladores.producto;
    opens presentacion.controladores.producto to javafx.fxml;
    
    exports presentacion.controladores.reportes;
    opens presentacion.controladores.reportes to javafx.fxml;
    
    exports presentacion.controladores.usuarios;
    opens presentacion.controladores.usuarios to javafx.fxml;
    
    exports presentacion.controladores.vendedor;
    opens presentacion.controladores.vendedor to javafx.fxml;
    
    exports presentacion.controladores.ventas;
    opens presentacion.controladores.ventas to javafx.fxml;
    
    opens datos.objetos to javafx.base;
    
}
   

    

