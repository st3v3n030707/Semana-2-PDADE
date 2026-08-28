package ni.edu.uam.registro_app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ni.edu.uam.registro_app.dao.EstudianteDao;
import ni.edu.uam.registro_app.modelos.Estudiante;

import java.time.LocalDate;
import java.util.List;

public class EstudianteController {

    EstudianteDao listado = new EstudianteDao();


    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtCarrera;
    @FXML
    private DatePicker dpFechaNac;
    @FXML
    private CheckBox chkTieneBeca;
    @FXML
    private Label lblRegistros;

    //nuevos controles que son de los nuevos botones
    @FXML private ComboBox<String> cmbCarrera;
    @FXML private RadioButton rbPresencial;
    @FXML private RadioButton rbVirtual;
    @FXML private ToggleGroup grupoModalidad;
    @FXML private ListView<String> lvActividades;

    @FXML // arranca al abrir la ventana, es para configurar los controles
    public void initialize(){

        cmbCarrera.getItems().addAll("Ingeneria en Sistemas","Diplomacia y Relaciones Internacionales", "Medicina", "Administracion de Empresas", "Diseño Grafico");
        lvActividades.getItems().addAll("Futbol","Voleibol","Teatro","Ingles", "Jujitsu");
        lvActividades.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        txtNombres.setOnAction(event -> txtApellidos.requestFocus());

        txtApellidos.setOnAction(event -> cmbCarrera.requestFocus());

        cmbCarrera.setOnAction(event -> dpFechaNac.requestFocus());

        dpFechaNac.setOnAction(event -> chkTieneBeca.requestFocus());


    }


    @FXML
    protected void guardarOnClick(){
        if (validarCampos()) {
            leerDatos();
            contarRegistros();
            limpiarCampos();
        }
    }

    private void leerDatos(){
        // <-- CAMBIADO: Usamos formatearNombrePropio en lugar de .getText() directo
        String nombre = formatearNombrePropio(txtNombres.getText());
        String apellidos = formatearNombrePropio(txtApellidos.getText());

        String carrera = cmbCarrera.getValue();
        LocalDate fechaNac = dpFechaNac.getValue();
        Boolean tieneBeca = chkTieneBeca.isSelected();

        String modalidad = rbPresencial.isSelected() ? "Presencial" : "Virtual";
        List<String> actividades = lvActividades.getSelectionModel().getSelectedItems();

        agregarDatos(new Estudiante(nombre, apellidos, carrera, fechaNac, tieneBeca, modalidad, actividades));
    }

    private void agregarDatos(Estudiante estudiante){
        listado.agregar(estudiante);
    }

    private void contarRegistros(){
        lblRegistros.setText("Registros almacenados: " + listado.obtenerRegistros().size());
    }
    private void limpiarCampos(){
        txtNombres.clear();
        txtApellidos.clear();

        dpFechaNac.setValue(null);
        chkTieneBeca.setSelected(false);
        cmbCarrera.setValue(null);
        rbPresencial.setSelected(true);
        lvActividades.getSelectionModel().clearSelection();

        txtNombres.requestFocus();

    }
    private boolean validarCampos() {
        String regexSoloLetras = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";

        if (txtNombres.getText() == null || txtNombres.getText().trim().isEmpty()) {
            mostrarAlerta("Campo Obligatorio", "Por favor ingresa los nombres del estudiante.");
            txtNombres.requestFocus();
            return false;
        }
        if (!txtNombres.getText().trim().matches(regexSoloLetras)) {
            mostrarAlerta("Formato Incorrecto", "Los nombres solo deben contener letras.");
            txtNombres.requestFocus();
            return false;
        }

        if (txtApellidos.getText() == null || txtApellidos.getText().trim().isEmpty()) {
            mostrarAlerta("Campo Obligatorio", "Por favor ingresa los apellidos del estudiante.");
            txtApellidos.requestFocus();
            return false;
        }
        if (!txtApellidos.getText().trim().matches(regexSoloLetras)) {
            mostrarAlerta("Formato Incorrecto", "Los apellidos solo deben contener letras.");
            txtApellidos.requestFocus();
            return false;
        }

        if (cmbCarrera.getValue() == null) {
            mostrarAlerta("Campo Obligatorio", "Debe seleccionar una carrera universitaria.");
            cmbCarrera.requestFocus();
            return false;
        }

        if (dpFechaNac.getValue() == null) {
            mostrarAlerta("Campo Obligatorio", "Debe seleccionar una fecha de nacimiento.");
            dpFechaNac.requestFocus();
            return false;
        }

        if (dpFechaNac.getValue().isAfter(LocalDate.now())) {
            mostrarAlerta("Fecha Inválida", "La fecha de nacimiento no puede ser una fecha futura.");
            dpFechaNac.requestFocus();
            return false;
        }

        int edad = java.time.Period.between(dpFechaNac.getValue(), LocalDate.now()).getYears();
        if (edad < 15) {
            mostrarAlerta("Edad Inválida", "El estudiante debe ser mayor de 15 años.");
            dpFechaNac.requestFocus();
            return false;
        }

        if (lvActividades.getSelectionModel().getSelectedItems().isEmpty()) {
            mostrarAlerta("Campo Obligatorio", "Debe seleccionar al menos una actividad extracurricular.");
            lvActividades.requestFocus();
            return false;
        }

        return true;
    }
    private String formatearNombrePropio(String texto) {
        if (texto == null || texto.trim().isEmpty()) return "";
        String[] palabras = texto.trim().toLowerCase().split("\\s+");
        StringBuilder resultado = new StringBuilder();
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                resultado.append(Character.toUpperCase(palabra.charAt(0)))
                        .append(palabra.substring(1))
                        .append(" ");
            }
        }
        return resultado.toString().trim();
    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validación de Registro");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}