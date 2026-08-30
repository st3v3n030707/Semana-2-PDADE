package ni.edu.uam.sesion4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Al extender (extends) de Application, esta clase se convierte en una app de JavaFX.
public class GradeApplication extends Application {

    // El método start es el punto de inicio de la interfaz gráfica.
    // "stage" es la ventana principal (el marco exterior de Windows).
    public void start(Stage stage) throws IOException {

        // El FXMLLoader es como un lector. Va a buscar tu archivo de diseño (.fxml).
        // NOTA: Recuerda que lo cambiamos a "login-view.fxml" para que la app arranque pidiendo contraseña.
        FXMLLoader fxmlLoader = new FXMLLoader(GradeApplication.class.getResource("login-view.fxml"));

        // Creamos una Escena (el contenido interno de la ventana) y le decimos que mida 500x500 pixeles.
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);

        // Le ponemos el título a la parte de arriba de la ventana.
        stage.setTitle("Universidad Americana");

        // Le colocamos la escena a la ventana.
        stage.setScene(scene);

        // Finalmente, mostramos la ventana en pantalla.
        stage.show();
    }
}