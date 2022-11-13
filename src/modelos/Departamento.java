/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import base_de_datos.Conexion;
import base_de_datos.GestionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author electro-zombie
 */
public class Departamento {

    private final int idDepartamento;
    private final String nombreDepartamento;
    
    public Departamento(int idDepartamento, String nombreDepartamento){
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }
    
    //BD
    
    public static Vector<String> getDepartamentos() {
        Conexion C = new Conexion();
        Vector<String> V = new Vector<>();
        try {
            C.conectar();

            String S = "select nombre_departamento from departamento";
            ResultSet rs = C.getConsulta().executeQuery(S);

            while (rs.next()) {
                V.add(rs.getString("nombre_departamento"));
            }

        } catch (SQLException ex) {
            C.desconectar();
            Logger.getLogger(GestionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        C.desconectar();
        return V;
    }
    
    public int getIdEncuestaDepartamento(){
       Conexion C = new Conexion();
        int idEncuesta = 0;
        try {
            C.conectar();
            String stat = "select id_encuesta from departamentos_x_encuesta where id_departamento = "+this.idDepartamento;
            ResultSet rs = C.getConsulta().executeQuery(stat);
            idEncuesta= rs.getInt("id_encuesta");
            C.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GestionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idEncuesta;
    }
    
    public static Departamento getDepartamento(int idDepartamento) {
       Conexion C = new Conexion();
        try {
            C.conectar();
            
            String stat = "select * from departamento where id_departamento = " + idDepartamento;
            ResultSet RS = C.getConsulta().executeQuery(stat);
            Departamento D = new Departamento(idDepartamento, RS.getString("nombre_departamento"));

            C.desconectar();
            return D;
        } catch (SQLException e) {
        }
        return null;
    }
    
    public static Vector<int[]> getEstadisticasDepartamento(int ano) {

        Vector<int[]> estXdep = new Vector<>();
        
        Conexion C = new Conexion();
        try {
            C.conectar();
            
            String stat = "select * from departamento";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            do{
                estXdep.add(new int[5]);
            }while(RS.next());
            
            for(int i = 0; i < estXdep.size(); i++){
                stat = "select * from encuesta_resuelta join preguntas_x_encuesta_resuelta on encuesta_resuelta.id_encuesta_resuelta = preguntas_x_encuesta_resuelta.id_encuesta_resuelta where id_departamento = " + (i+1) + " and ano_encuesta = " + ano;
                RS = C.getConsulta().executeQuery(stat);
                
                do{
                    estXdep.elementAt(i)[RS.getInt("seleccion_pregunta")]++;
                }while(RS.next());
                
                stat = "select * from trabajadores_x_departamento where ano_encuesta = " + ano + " and id_departamento = " + (i+1);
                RS = C.getConsulta().executeQuery(stat);
                
                do{
                    
                    estXdep.elementAt(i)[3]=RS.getInt("cantidad_trabajadores");
                    estXdep.elementAt(i)[4]=RS.getInt("cantidad_encuestados");
                    
                }while(RS.next());
            }
            
            
            C.desconectar();
        } catch (SQLException e) {
        }
        return estXdep;
    }
}
