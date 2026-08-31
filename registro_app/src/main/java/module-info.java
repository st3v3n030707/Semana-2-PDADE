module ni.edu.uam.registro_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    // Permite que la vista cargue los controladores
    opens ni.edu.uam.registro_app.controllers to javafx.fxml;

    // le da permisos al motor de javafk para que lea las variables de la clase
    //estudiante, sin esto no pudiera leer y la tabla estaria vacia
    opens ni.edu.uam.registro_app.modelos to javafx.base;

    exports ni.edu.uam.registro_app;
}