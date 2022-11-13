/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package base_de_datos;

import modelos.Encuesta;
import modelos.Departamento;

import java.util.Vector;
import modelos.Usuario;


/**
 *
 * @author joan
 */
public class GestionBD {

    public static Vector<String> getDepartamentos() {
        return Departamento.getDepartamentos();
    }
    
    public static int getIdEncuestaDepartamento(Departamento dep){
       return dep.getIdEncuestaDepartamento();
    }
    
    public static Encuesta getEncuesta(int idEncuesta){
        return Encuesta.getEncuesta(idEncuesta);
    }

    public static void setEncuestaResuelta(Encuesta E, Departamento D) {
      E.setEncuestaResuelta(D);
    }

    public static Departamento getDepartamento(int idDepartamento) {
        return Departamento.getDepartamento(idDepartamento);
    }
    
    public static boolean verifyUsuario(String usuario, String password){
        return Usuario.verifyUsuario(usuario, password);
    }

    public static Vector<int[]> getEstadisticasDepartamento(int ano) {
        return Departamento.getEstadisticasDepartamento(ano);
    }
    
} 
