/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import base_de_datos.Conexion;
import base_de_datos.GestionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
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
            
            String stat = "select * from trabajadores_x_departamento where ano_encuesta = " + LocalDate.now().getYear();
            ResultSet RS = C.getConsulta().executeQuery(stat);
            
            if(RS.next()){
                
                int cant_enc = RS.getInt("cantidad_encuestados");
                int ano = RS.getInt("ano_encuesta");
                int idD = RS.getInt("id_departamento");
                stat = "update trabajadores_x_departamento set cantidad_encuestados = " + cant_enc + " where ano_encuesta = " + ano + " and id_departamento = " + idD;
                C.getConsulta().execute(stat);
            }
            else{
                stat = "insert into trabajadores_x_departamento values(" + D.getIdDepartamento() + ", 1, 0, " + LocalDate.now().getYear()+ ")";
                C.getConsulta().execute(stat);
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
    
    public Encuesta getEncuestaSec(){
        Conexion c= new Conexion();
        try {
            c.conectar();
            String stat ="select id_encuesta from encuesta where nombre_encuesta ='"+this.nombreEncuesta+" (Secundaria)'";
            ResultSet rs = c.getConsulta().executeQuery(stat);
            int id_encuesta= rs.getInt(0);
            c.desconectar();
            
        } catch (Exception e) {
        }
        return getEncuesta(idEncuesta);
    }

    
}
