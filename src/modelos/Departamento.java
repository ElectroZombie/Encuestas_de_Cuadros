/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import base_de_datos.Conexion;
import base_de_datos.GestionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    
    public String getPersonaObjetivo(){
        String obj="";
        int idEncuesta = getIdEncuestaDepartamento();
        try {
            
            Conexion C = new Conexion();

            C.conectar();
            String stat ="select persona_objetivo from encuesta where id_encuesta = "+idEncuesta;
            ResultSet rs = C.getConsulta().executeQuery(stat);
            obj=rs.getString(1);
            C.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
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
    
    public static Departamento getDepartamento(String nombreDepartamento) {
       Conexion C = new Conexion();
        try {
            C.conectar();
            
            String stat = "select * from departamento where nombre_departamento = '" + nombreDepartamento + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            Departamento D = new Departamento(RS.getInt("id_departamento"), RS.getString("nombre_departamento"));

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
                
                if(RS.next()){
                do{
                    estXdep.elementAt(i)[RS.getInt("seleccion_pregunta")]++;
                }while(RS.next());
                }
                
                stat = "select * from trabajadores_x_departamento where ano_encuesta = " + ano + " and id_departamento = " + (i+1);
                RS = C.getConsulta().executeQuery(stat);
                
                if(RS.next()){
                do{
                    
                    estXdep.elementAt(i)[3]=RS.getInt("cantidad_trabajadores");
                    estXdep.elementAt(i)[4]=RS.getInt("cantidad_encuestados");
                    
                }while(RS.next());
                }
            }
            
            
            C.desconectar();
        } catch (SQLException e) {
        }
        return estXdep;
    }
    
     public static void actualizarTotalTrabajadores(String departamento, int anno, int nuevoTotal) {
         
         int idD = getIdDepartamentoXNombre(departamento);
         
         Conexion C = new Conexion();
         
         try {
             C.conectar();         
             
             String stat = "select id_departamento from trabajadores_x_departamento where id_departamento = " + idD + " and ano_encuesta = " + LocalDate.now().getYear();
             ResultSet RS = C.getConsulta().executeQuery(stat);
             
             if(RS.next()){
             stat = "update trabajadores_x_departamento set cantidad_trabajadores = " + nuevoTotal + " where id_departamento = " + idD + " and ano_encuesta = " + anno;
             C.getConsulta().execute(stat);
             }
             else{
                 stat = "insert into trabajadores_x_departamento values(" + idD + ", " + nuevoTotal + ", 0, " + LocalDate.now().getYear() + ")";
                 C.getConsulta().execute(stat);
             }
             
             C.desconectar();
         } catch (SQLException e) {
         }
     }
     
      public static Tupla<Tupla<Integer, Integer>,Tupla<Object[], Object[]>> getEstadisticasDepartamento(String nombreDepartamento, int anno) {
          
          Object[] preguntas = new Object[14];
          Object[] preguntasSecundarias = new Object[13];
          int cantE = 0;
          int cantT = 0;
          
          try {
          int idD = getIdDepartamentoXNombre(nombreDepartamento);
          if(idD==-1){
            throw new SQLException();
          }
          
          Conexion C = new Conexion();
          C.conectar();
          
          String stat = "select cantidad_trabajadores, cantidad_encuestados from trabajadores_x_departamento where ano_encuesta = " + anno + " and id_departamento = " + idD;
          ResultSet RS = C.getConsulta().executeQuery(stat);
          cantT = RS.getInt("cantidad_trabajadores");
          cantE = RS.getInt("cantidad_encuestados");
          
          
          
          stat = "select * from encuesta_resuelta join preguntas_x_encuesta_resuelta on encuesta_resuelta.id_encuesta_resuelta = preguntas_x_encuesta_resuelta.id_encuesta_resuelta where encuesta_resuelta.ano_encuesta = " + anno + " and encuesta_resuelta.id_departamento = " + idD;
          RS = C.getConsulta().executeQuery(stat);
          
          if(RS.next()){
              do{
                
                  int idEncuesta = RS.getInt("id_encuesta");
                  int idPregunta = RS.getInt("id_pregunta");
                  int seleccion = RS.getInt("seleccion_pregunta");
                  String argumentacion = RS.getString("argumento_pregunta");
                  
                  Tupla T;
                  
                  if(idEncuesta <= 4){
                      if(preguntas[idPregunta]==null){
                          T = new Tupla<Integer[], Vector<String>>(new Integer[3], new Vector<>());
                          Integer[] I = (Integer[])T.getElemento1();
                          for(int i = 0; i < I.length; i++){
                              I[i] = 0;
                          }
                      }
                      else{
                          T = (Tupla<Integer[], Vector<String>>)preguntas[idPregunta];
                      }
                      
                      Integer[] I = (Integer[])T.getElemento1();
                          
                          if(seleccion == 0){
                              I[0]++;
                          }
                          else if(seleccion == 1){
                              I[1]++;
                          }
                          else{
                              I[2]++;
                          }
                          
                          Vector<String> V = (Vector<String>)T.getElemento2();
                          
                          if(!argumentacion.equals("")){
                          V.add(argumentacion);
                          }
                          
                          T.setElemento1(I);
                          T.setElemento2(V);
                          preguntas[idPregunta] = T;
                      
                  }
                  else{
                      if(preguntasSecundarias[idPregunta]==null){
                          T = new Tupla<Integer[], Vector<String>>(new Integer[3], new Vector<>());
                          Integer[] I = (Integer[])T.getElemento1();
                          for(int i = 0; i < I.length; i++){
                              I[i] = 0;
                          }
                      }
                      else{
                          T = (Tupla<Integer[], Vector<String>>)preguntasSecundarias[idPregunta];
                      }
                      
                      Integer[] I = (Integer[])T.getElemento1();
                          
                          if(seleccion == 0){
                              I[0]++;
                          }
                          else if(seleccion == 1){
                              I[1]++;
                          }
                          else{
                              I[2]++;
                          }
                          
                          Vector<String> V = (Vector<String>)T.getElemento2();
                          
                          if(!argumentacion.equals("")){
                          V.add(argumentacion);
                          }
                          
                          T.setElemento1(I);
                          T.setElemento2(V);
                          preguntasSecundarias[idPregunta] = T;
                  }
                  
                  
              }while(RS.next());
          }
          
          
          C.desconectar();
          } catch (SQLException ex) {
                  Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
              }
          
          return new Tupla<>(new Tupla<>(cantT, cantE),new Tupla<>(preguntas, preguntasSecundarias));
      }
      
      public static int getIdDepartamentoXNombre(String nombreDepartamento){
          Conexion C = new Conexion();
          try {
              C.conectar();
              
              String stat = "select id_departamento from departamento where nombre_departamento = '" + nombreDepartamento + "'";
              ResultSet RS = C.getConsulta().executeQuery(stat);
              int idD = RS.getInt("id_departamento");
              
              C.desconectar();
              return idD;
          } catch (SQLException e) {
          }
          return -1;
      }

    public boolean removeAllEncuestas() {
        Conexion C = new Conexion();
        
        try {
            C.conectar();
            
            String stat = "delete from encuesta_resuelta";
            C.getConsulta().execute(stat);
            stat = "delete from preguntas_x_encuesta_resuelta";
            C.getConsulta().execute(stat);
            stat = "delete from trabajadores_x_departamento";
            C.getConsulta().execute(stat);
            
            C.desconectar();
        } catch (SQLException e) {
            C.desconectar();
            return false;
        }
        return true;
    }
    
    public boolean removeEncuestasXAno(int ano){
        Conexion C = new Conexion();
        
        try {
            C.conectar();
            
            String stat = "delete from encuesta_resuelta where ano_encuesta = " + ano + " and id_departamento = " + this.getIdDepartamento();
            C.getConsulta().execute(stat);
            stat = "delete from trabajadores_x_departamento where ano_encuesta = " + ano + " and id_departamento = " + this.getIdDepartamento();
            C.getConsulta().execute(stat);
            
            C.desconectar();
        } catch (SQLException e) {
            C.desconectar();
            return false;
        }
        return true;
    }
}
