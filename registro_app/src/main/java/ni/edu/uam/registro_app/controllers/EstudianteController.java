package ni.edu.uam.registro_app.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ni.edu.uam.registro_app.dao.EstudianteDao;
import ni.edu.uam.registro_app.modelos.Estudiante;

import java.time.LocalDate;
import java.util.List;

public class EstudianteController {

    EstudianteDao listado = new EstudianteDao();

    // Lista especial para la tabla un ObservableList tiene "superpoderes".
    //    // Si agregas o quitas un estudiante de esta lista, la tabla en la pantalla
    //    // se actualiza automáticamente al instante
    private ObservableList<Estudiante> listaEstudiantesTabla = FXCollections.observableArrayList();

    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private DatePicker dpFechaNac;
    @FXML private CheckBox chkTieneBeca;
    @FXML private Label lblRegistros;
    @FXML private ComboBox<String> cmbCarrera;
    @FXML private RadioButton rbPresencial;
    @FXML private RadioButton rbVirtual;
    @FXML private ToggleGroup grupoModalidad;
    @FXML private ListView<String> lvActividades;

    // New controladores de la tabla
    // 2. DECLARACIÓN DE LAS COLUMNAS
    // Nota que usamos genéricos: <Estudiante, String>.
    // osea significa : "Esta columna leerá un objeto Estudiante,
    // //y extraerá un texto (String)".
    @FXML private TableView<Estudiante> tvEstudiantes;
    @FXML private TableColumn<Estudiante, String> colNombres;
    @FXML private TableColumn<Estudiante, String> colApellidos;
    @FXML private TableColumn<Estudiante, String> colCarrera;
    @FXML private TableColumn<Estudiante, String> colModalidad;
    @FXML private TableColumn<Estudiante, LocalDate> colFechaNac;
    @FXML private TableColumn<Estudiante, Boolean> colBeca;
    @FXML private TableColumn<Estudiante, List<String>> colActividades;

    @FXML
    public void initialize() {
        cmbCarrera.getItems().addAll("Ingeniería en Sistemas", "Medicina", "Administración", "Diseño Gráfico");
        lvActividades.getItems().addAll("Fútbol", "Voleibol", "Teatro", "Inglés");
        lvActividades.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        txtNombres.setOnAction(event -> txtApellidos.requestFocus());
        txtApellidos.setOnAction(event -> cmbCarrera.requestFocus());
        cmbCarrera.setOnAction(event -> dpFechaNac.requestFocus());
        dpFechaNac.setOnAction(event -> chkTieneBeca.requestFocus());

        // CONFIGURACIÓN DE LA TABLA: Vinculamos cada columna a su variable en Estudiante.java
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        colModalidad.setCellValueFactory(new PropertyValueFactory<>("modalidad"));
        colFechaNac.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colBeca.setCellValueFactory(new PropertyValueFactory<>("tieneBeca"));
        colActividades.setCellValueFactory(new PropertyValueFactory<>("actividades"));

        // Le pasamos la lista observable a la tabla
        tvEstudiantes.setItems(listaEstudiantesTabla);
    }

    @FXML
    protected void guardarOnClick(){
        leerDatos();
        contarRegistros();
        limpiarCampos();
    }

    private void leerDatos(){
        String nombre = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        LocalDate fechaNac = dpFechaNac.getValue();
        Boolean tieneBeca = chkTieneBeca.isSelected();
        String carrera = cmbCarrera.getValue();
        String modalidad = rbPresencial.isSelected() ? "Presencial" : "Virtual";
        List<String> actividades = lvActividades.getSelectionModel().getSelectedItems();

        agregarDatos(new Estudiante(nombre, apellidos, carrera, fechaNac, tieneBeca, modalidad, actividades));
    }

    private void agregarDatos(Estudiante estudiante){
        listado.agregar(estudiante); // Lo guarda en tu base de datos Dao osea la lista invisible
        listaEstudiantesTabla.add(estudiante); // Lo muestra en la tabla visualmente
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
}