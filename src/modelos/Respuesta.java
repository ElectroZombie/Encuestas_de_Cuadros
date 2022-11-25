

package modelos;

/**
 *
 * @author joan
 */
public class Respuesta {
    private int id_pregunta;
    private int seleccion;
    private String argumentacion;

    public Respuesta(int seleccion,int id_pregunta) {
        this.seleccion = seleccion;
        this.id_pregunta=id_pregunta;
        argumentacion="";
    }

    public void setArgumentacion(String argumentacion) {
        this.argumentacion = argumentacion;
    }

    public int getSeleccion() {
        return seleccion;
    }

    public String getArgumentacion() {
        return argumentacion;
    }

    public int getId_pregunta() {
        return id_pregunta;
    }
    
    
}
