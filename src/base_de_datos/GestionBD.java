/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package base_de_datos;

import modelos.Encuesta;
import modelos.Departamento;

import java.util.Vector;
import modelos.Usuario;
import utiles.Tupla;


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

    public static Vector<int[]> getEstadisticasDepartamentosXAno(int ano) {
        return Departamento.getEstadisticasDepartamentosXAno(ano);
    }
    

    public static Vector<Integer> getAnnosEncuestas(){
        return Encuesta.getAnnosEncuestas();
    }

    public static void actualizarTotalTrabajadores(String departamento, int anno, int nuevoTotal) {
        Departamento.actualizarTotalTrabajadores(departamento, anno, nuevoTotal);
    }

    public static Encuesta getEncuestaSecundaria(Encuesta e){
        return e.getEncuestaSec();

    }

    public static void integrateDatabase(String direccion) {
        
    }

    public static int getEncuestasRealizadas() {
        return Encuesta.getEncuestasRealizadas();
    }

    public static int getEncuestasRealizadasDep(int idD) {
        return Encuesta.getEncuestasRealizadas(idD);
    }

    public static Tupla<Tupla<Integer, Integer>,Tupla<Object[], Object[]>> getEstadisticasDepartamento(String nombreDepartamento, int anno) {
        return Departamento.getEstadisticasDepartamento(nombreDepartamento, anno);
    }
    
} 
