package ni.edu.uam.registro_app.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
    private String nombre;
    private String apellidos;
    private String carrera;
    private LocalDate fechaNacimiento;
    private Boolean tieneBeca;

    private String modalidad;
    private List<String> actividades;


}
