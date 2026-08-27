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

    @FXML // arranca al abrir la ventana , es para configurar los controles
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
        leerDatos();
        contarRegistros();
        limpiarCampos();
    }

    private void leerDatos(){
        // Usamos las variables actualizadas con "s"
        String nombre = txtNombres.getText();
        String apellidos = txtApellidos.getText();
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
}