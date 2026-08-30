package ni.edu.uam.sesion4.DAO;

import ni.edu.uam.sesion4.models.Student;

import java.util.ArrayList;
import java.util.List;

public class GradeDao {

    // Creamos una lista llamada "grades" que solo aceptará objetos del tipo "Student".
    // Imagínalo como una caja donde iremos metiendo a los estudiantes.
    List<Student> grades;

    // Este es el constructor. Se ejecuta automáticamente cuando usamos la clase.
    public GradeDao(){
        // Aquí "inicializamos" la caja. Decimos que será un ArrayList (una lista elástica que puede crecer).
        grades = new ArrayList<>();
    }

    // Método para GUARDAR un estudiante.
    // Recibe a un "Student" y lo mete a la lista usando el comando .add()
    public void addGrade(Student student){
        grades.add(student);
    }

    // Método para VER TODOS los estudiantes.
    // Simplemente devuelve la caja completa con todo lo que tiene adentro.
    public List<Student> getGrades(){
        return grades;
    }

    // Método para BUSCAR un estudiante específico por su nombre.
    public Student getGradeForName(String name){
        // Esto es un ciclo "For-Each". Significa:
        // "Por cada estudiante (student) que esté dentro de la lista (grades), haz lo siguiente..."
        for(Student student : grades){

            // Si el nombre del estudiante actual es exactamente igual al nombre que estamos buscando...
            if(student.getName().equals(name)){
                // ...entonces devuélveme a ese estudiante y termina la búsqueda.
                return student;
            }
        }
        // Si termina de revisar toda la lista y no encontró a nadie con ese nombre, devuelve "nada" (null).
        return null;
    }
}