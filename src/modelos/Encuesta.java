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

/**
 *
 * @author joan
 */
public class Encuesta {

    private Vector<Pregunta> preguntas;
    private final Vector<Respuesta> respuestas;
    private final String nombreEncuesta;
    private final String TextoEncuesta;
    private final String objEncuesta;
    private final int idEncuesta;
    
    public Encuesta(int idEncuesta, String nombreEncuesta,String TextoEncuesta, String objEncuesta) {
        respuestas = new Vector<>();
        preguntas = new Vector<>();
        this.TextoEncuesta=TextoEncuesta;
        this.nombreEncuesta=nombreEncuesta;
        this.objEncuesta=objEncuesta;
        this.idEncuesta = idEncuesta;
        setPreguntas();
    }

    public String getTextoEncuesta() {
        return TextoEncuesta;
    }
    
    public String getNombreEncuesta(){
        return nombreEncuesta;
    }
    
    public int getIdEncuesta(){
        return idEncuesta;
    }

    public Vector<Pregunta> getPreguntas() {
        return preguntas;
    }
    
    public Vector<Respuesta> getRespuestas(){
        return respuestas;
    }
    
    private void setPreguntas(){
        Vector<Integer> idPreguntas = getIdPreguntas();
        preguntas = getPreguntasEncuesta(idPreguntas);
    }
    
    public void setRespuesta(Respuesta respuesta) {
        respuestas.add(respuesta);
    }
    
    public String getObjEncuesta(){
        return objEncuesta;
    }
    
    //BD
    
    public static Encuesta getEncuesta(int idEncuesta){
        Conexion C = new Conexion();
        Encuesta e = null;
        try {
            C.conectar();
            String stat="select * from encuesta where id_encuesta="+idEncuesta;
            ResultSet rs = C.getConsulta().executeQuery(stat);
            e= new Encuesta(rs.getInt("id_encuesta"),rs.getString("nombre_encuesta"), rs.getString("texto_encuesta"), rs.getString("objetivo_encuesta"));
            C.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GestionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
    
    private Vector<Integer> getIdPreguntas(){
        Conexion C = new Conexion();
        Vector<Integer> idsPreguntas = new Vector<>();
        try {
            C.conectar();
            String stat="select id_pregunta from preguntas_x_encuesta where id_encuesta="+this.idEncuesta;
            ResultSet rs = C.getConsulta().executeQuery(stat);
            while (rs.next()) {                
                idsPreguntas.add(rs.getInt("id_pregunta"));
            }
            C.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GestionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idsPreguntas;
    }
    
    private Vector<Pregunta> getPreguntasEncuesta(Vector<Integer> idPreguntas ){
        Conexion C = new Conexion();
        Vector<Pregunta> pregs= new Vector<>();
        C.conectar();
        String stat = "select * from pregunta where id_pregunta = ";
        for (Integer idPregunta : idPreguntas) {
            try {
                String stat2 = stat + idPregunta;
                ResultSet rs = C.getConsulta().executeQuery(stat2);
                Pregunta p = new Pregunta(rs.getInt("id_pregunta"), rs.getString("texto_pregunta"), rs.getBoolean("argumentacion_permitida"));
                pregs.add(p);
           } catch (SQLException ex) {
                Logger.getLogger(GestionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        C.desconectar();
        return pregs;
    }
    
    public void setEncuestaResuelta(Departamento D) {
        Conexion C = new Conexion();
        try {
            C.conectar();
            
            String stat = "";
            ResultSet RS;
            
            if(!this.nombreEncuesta.contains("(Secundaria)")){
            
            stat = "select * from trabajadores_x_departamento where ano_encuesta = " + LocalDate.now().getYear() + " and id_departamento = " + D.getIdDepartamento();
            RS = C.getConsulta().executeQuery(stat);
            
            if(RS.next()){
                
                int cant_enc = RS.getInt("cantidad_encuestados");
                int cant_trab = RS.getInt("cantidad_trabajadores");
                int ano = RS.getInt("ano_encuesta");
                int idD = RS.getInt("id_departamento");
                cant_enc++;
                if(cant_enc < cant_trab){
                stat = "update trabajadores_x_departamento set cantidad_encuestados = " + cant_enc + " where ano_encuesta = " + ano + " and id_departamento = " + idD;
                C.getConsulta().execute(stat);
                }
                else{
                stat = "update trabajadores_x_departamento set cantidad_encuestados = " + cant_enc + ", cantidad_trabajadores = " + cant_enc + " where ano_encuesta = " + ano + " and id_departamento = " + idD;
                C.getConsulta().execute(stat);
                    
                }
            }
            else{
                stat = "insert into trabajadores_x_departamento values(" + D.getIdDepartamento() + ", 1, 1, " + LocalDate.now().getYear()+ ")";
                C.getConsulta().execute(stat);
            }
            
            }
            
            
            stat = "insert into encuesta_resuelta values(null, " + this.idEncuesta + ", " + D.getIdDepartamento() + ", " + LocalDate.now().getYear() + ")";
            C.getConsulta().execute(stat);
            
            stat = "select max(id_encuesta_resuelta) from encuesta_resuelta where id_encuesta = " + this.idEncuesta + " and id_departamento = " + D.getIdDepartamento();
            RS = C.getConsulta().executeQuery(stat);
            
            int idER = RS.getInt(1);
            
            for(int i = 0; i < respuestas.size(); i++){
                stat = "insert into preguntas_x_encuesta_resuelta values(" + idER + ", " + respuestas.elementAt(i).getId_pregunta() + ", " + respuestas.elementAt(i).getSeleccion() + ", '" + respuestas.elementAt(i).getArgumentacion() + "')";
                C.getConsulta().execute(stat);
            }
    
            C.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(GestionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Vector<Integer> getAnnosEncuestas(){
        Conexion C = new Conexion();
        
        Vector<Integer> annos = new Vector<>();
        
        try {
            C.conectar();
            
            String stat = "select distinct ano_encuesta from trabajadores_x_departamento order by ano_encuesta asc";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            
            if(RS.next()){
                do{
                    annos.add(RS.getInt("ano_encuesta"));
                }while(RS.next());
            }
            
            C.desconectar();
        } catch (SQLException e) {
        }
        return annos;
    }

    public Encuesta getEncuestaSec(){
        Conexion c= new Conexion();
        int id_encuesta = 0;
        try {
            c.conectar();
            String stat ="select id_encuesta from encuesta where nombre_encuesta ='"+this.nombreEncuesta+" (Secundaria)'";
            ResultSet rs = c.getConsulta().executeQuery(stat);
            id_encuesta= rs.getInt(1);
            c.desconectar();
            
        } catch (Exception e) {
        }
        return getEncuesta(id_encuesta);
    }
    
      public static int getEncuestasRealizadas() {
          Conexion C = new Conexion();
          int cont = 0;
          try {
              C.conectar();
              
              String stat = "select count(id_encuesta_resuelta) from encuesta_resuelta where ano_encuesta = " + LocalDate.now().getYear();
              ResultSet RS = C.getConsulta().executeQuery(stat);
              
              if(RS.next()){
                  cont = RS.getInt(1);
              }
              C.desconectar();
          } catch (Exception e) {
          }
          return cont;
    }

    public static int getEncuestasRealizadas(int idD) {
        Conexion C = new Conexion();
          int cont = 0;
          try {
              C.conectar();
              
              String stat = "select count(id_encuesta_resuelta) from encuesta_resuelta where ano_encuesta = " + LocalDate.now().getYear() + " and id_departamento = " + idD;
              ResultSet RS = C.getConsulta().executeQuery(stat);
              
              if(RS.next()){
                  cont = RS.getInt(1);
              }
              C.desconectar();
          } catch (Exception e) {
          }
          return cont;
    }

    
}
