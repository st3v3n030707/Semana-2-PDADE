module ni.edu.uam.registro_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    // Permite que la vista cargue los controladores
    opens ni.edu.uam.registro_app.controllers to javafx.fxml;

    // NUEVA LÍNEA: Permite que la tabla lea los datos de tus modelos
    opens ni.edu.uam.registro_app.modelos to javafx.base;

    exports ni.edu.uam.registro_app;
}