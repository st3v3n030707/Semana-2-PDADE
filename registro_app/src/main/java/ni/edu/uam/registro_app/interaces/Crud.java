package ni.edu.uam.registro_app.interaces;

import java.util.List;

public interface Crud <T> {

    public void agregar (T entidad);

    public List<T> obtenerRegistros();

}
