package ni.edu.uam.sesion4.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok es una herramienta que escribe código invisible por nosotros.
@Getter // Crea automáticamente los métodos para "obtener" datos (ej. getName).
@Setter // Crea automáticamente los métodos para "modificar" datos (ej. setName).
@AllArgsConstructor // Crea un constructor que pide TODOS los datos al mismo tiempo.
@NoArgsConstructor // Crea un constructor vacío, por si queremos crear un estudiante sin datos aún.

public class Student {

    // Estas son las variables o "características" que tendrá cada estudiante.
    private String name;   // Texto: para guardar el nombre.
    private String major;  // Texto: para guardar la carrera.
    private int grade;     // Número entero: para guardar la nota (ej. 90, 85).

}