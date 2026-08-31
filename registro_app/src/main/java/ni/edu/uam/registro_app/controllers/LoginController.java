package ni.edu.uam.registro_app.controllers;
// Creador Raul
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    // Los nombres coinciden exactamente con los fx:id del FXML
    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private CheckBox chkNoRobot;

    @FXML
    private Button btnIngresar;

    @FXML
    private Label lblError;

    @FXML
    private void handleLogin(ActionEvent event) {
        String usuario = txtUser.getText().trim();
        String password = txtPassword.getText().trim();

        // 1. Validar que la casilla "No soy un robot" esté seleccionada
        if (!chkNoRobot.isSelected()) {
            lblError.setText("Por favor, confirma que no eres un robot.");
            lblError.setVisible(true);
            return;
        }

        // 2. Validar credenciales
        if ("admin".equals(usuario) && "1234".equals(password)) {
            lblError.setVisible(false);

            // 3. Redirigir a la siguiente vista
            cambiarAVistaSiguiente(event);
        } else {
            lblError.setText("ERROR EN CREDENCIALES");
            lblError.setVisible(true);
        }
    }

    private void cambiarAVistaSiguiente(ActionEvent event) {
        try {
            // nombre de la vista
            String fxmlPath = "/ni/edu/uam/registro_app/estudiante-view.fxml";

            var resource = getClass().getResource(fxmlPath);
            if (resource == null) {
                System.err.println("ERROR: No se encontró el archivo FXML en la ruta: " + fxmlPath);
                lblError.setText("Error: Archivo FXML no encontrado.");
                lblError.setVisible(true);
                return;
            }

            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            lblError.setText("Error al cargar la siguiente pantalla.");
            lblError.setVisible(true);
        }
    }
}