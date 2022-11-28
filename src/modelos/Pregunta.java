/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author joan
 */
public class Pregunta {
    private String pregunta;
    private boolean argumentacion;
    private int id_pregunta;

    public Pregunta(int id_pregunta, String pregunta, boolean argumentacion) {
        this.pregunta = pregunta;
        this.argumentacion = argumentacion;
        this.id_pregunta = id_pregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public boolean isArgumentacion() {
        return argumentacion;
    }

    public int getId_pregunta() {
        return id_pregunta;
    }
    
    
}
