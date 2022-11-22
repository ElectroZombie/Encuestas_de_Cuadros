/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import base_de_datos.Conexion;
import base_de_datos.GestionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import utiles.Tupla;

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
    
    public static Vector<int[]> getEstadisticasDepartamentosXAno(int ano) {

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
    
     public static void actualizarTotalTrabajadores(String departamento, int anno, int nuevoTotal) {
         Conexion C = new Conexion();
         
         try {
             C.conectar();
             
             String stat = "update trabajadores_x_departamento join departamento on trabajadores.id_departamento = departamento.id_departamento set cantidad_trabajadores = " + nuevoTotal + " where departamento.nombre_departamento = '" + departamento + "' and ano_departamento = " + anno;
             C.getConsulta().execute(stat);
             
             C.desconectar();
         } catch (SQLException e) {
         }
     }
     
      public static Vector<Tupla<Integer[], String[]>> getEstadisticasDepartamento(String nombreDepartamento, int anno) {
          
          Vector<Tupla<Integer[], String[]>> preguntas = new Vector<>();
          
          try {
          int idD = getIdDepartamentoXNombre(nombreDepartamento);
          if(idD==-1){
            throw new SQLException();
          }
          
          Conexion C = new Conexion();
          C.conectar();
          
          String stat = "select * from trabajadores_x_departamento join encuesta_resuelta join preguntas_x_encuesta_resuelta on trabajadores_x_departamento.id_departamento = encuesta_resuelta.id_departamento and trabajadores_x_departamento.ano_encuesta = encuesta_resuelta.ano_encuesta and encuesta_resuelta.id_encuesta_resuelta = preguntas_x_encuesta_resuelta.id_encuesta_resuelta where trabajadores_x_departamento.ano_encuesta = " + anno + " and trabajadores_x_departamento.id_departamento = " + idD;
          ResultSet RS = C.getConsulta().executeQuery(stat);
          
          if(RS.next()){
              do{
                  
                  int cantTrabajadores = RS.getInt(2);
                  int cantEncuestados = RS.getInt(3);
                  int idPregunta = RS.getInt("id_pregunta");
                  int seleccion = RS.getInt("seleccion_pregunta");
                  String argumentacion = RS.getString("argumento_pregunta");
                  
                  
              }while(RS.next());
          }
          
          
          C.desconectar();
          } catch (SQLException ex) {
                  Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
              }
          
          return preguntas;
      }
      
      public static int getIdDepartamentoXNombre(String nombreDepartamento){
          Conexion C = new Conexion();
          try {
              C.conectar();
              
              String stat = "get id_departamento from departamento where nombre_departamento = '" + nombreDepartamento + "'";
              ResultSet RS = C.getConsulta().executeQuery(stat);
              int idD = RS.getInt("id_departamento");
              
              C.desconectar();
              return idD;
          } catch (Exception e) {
          }
          return -1;
      }
}
