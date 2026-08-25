package ni.edu.uam.sesion4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.sesion4.DAO.GradeDao;
import ni.edu.uam.sesion4.models.Student;

public class GradeController {

    // Creamos una instancia de nuestro almacén (DAO) para poder usarlo aquí.
    GradeDao grades = new GradeDao();

    // La etiqueta @FXML le avisa a Java: "Oye, esta variable viene conectada desde el archivo de diseño visual".
    @FXML
    private TextField txtName; // La caja de texto del nombre

    @FXML
    private TextField txtMajor; // La caja de texto de la carrera

    @FXML
    private TextField txtGrade; // La caja de texto de la nota

    @FXML
    private Label lblCount; // El texto que muestra cuántos registros llevamos

    @FXML
    private Button btnSave; // El botón de guardar

    // Este método es el que se ejecuta cuando el usuario le da clic al botón "Guardar".
    // Está conectado en el FXML con la propiedad: onAction="#saveButtonAction"
    @FXML
    protected void saveButtonAction(){
        addGrade(); // Primero llama al método que extrae los datos y guarda.
        countGrade(); // Después llama al método que actualiza el texto del contador.
    }

    // Método que extrae la información de la pantalla.
    private void addGrade(){
        // .getText() extrae lo que el usuario escribió en la cajita y lo guarda en una variable.
        String name = txtName.getText();
        String major = txtMajor.getText();

        // Aquí pasa algo clave: txtGrade.getText() devuelve un Texto (String).
        // Pero nuestra nota tiene que ser un número. Integer.parseInt() convierte ese texto a número entero.
        int grade = Integer.parseInt(txtGrade.getText());

        // Creamos un nuevo Estudiante con esos tres datos y se lo pasamos al método saveGrade.
        saveGrade(new Student(name, major, grade));
    }

    // Método que recibe al estudiante recién creado y lo envía al DAO para guardarlo en la lista.
    private void saveGrade(Student student){
        grades.addGrade(student);
    }

    // Método que actualiza la etiqueta de la pantalla.
    private void countGrade(){
        // grades.getGrades().size() nos dice exactamente cuántos elementos hay guardados en la lista.
        // Lo concatenamos con un texto y lo mostramos en la etiqueta lblCount.
        lblCount.setText("Registros guardados : " + grades.getGrades().size());
    }
}