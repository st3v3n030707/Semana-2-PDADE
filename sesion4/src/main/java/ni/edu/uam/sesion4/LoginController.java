package ni.edu.uam.sesion4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblError;

    @FXML
    private Button btnEnter;

    @FXML
    protected void loginAction(ActionEvent event) throws IOException {
        String user = txtUser.getText();
        String password = txtPassword.getText();

        // Validamos que el usuario y contraseña sean correctos (ej: admin / admin)
        if (user.equals("admin") && password.equals("admin")) {

            // 1. Obtenemos la ventana actual a partir del botón
            Stage stage = (Stage) btnEnter.getScene().getWindow();

            // 2. Cargamos el archivo FXML de la siguiente pantalla
            FXMLLoader fxmlLoader = new FXMLLoader(GradeApplication.class.getResource("grade-view.fxml"));

            // 3. Creamos la nueva escena y se la asignamos a la ventana
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            stage.setTitle("Registro de Notas");
            stage.setScene(scene);
            stage.show();

        } else {
            // Si se equivoca, mostramos el mensaje de error en rojo
            lblError.setVisible(true);
        }
    }
}