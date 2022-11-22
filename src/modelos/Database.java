/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import base_de_datos.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;



/**
 *
 * @author electro-zombie
 */
public class Database {
 
    
    public static void integrateDatabase(String ruta){
        Conexion CE = new Conexion(ruta);
        
        Conexion C = new Conexion();
        
        try {
            CE.conectar();
            C.conectar();
            
            String stat = "select max(id_encuesta_resuelta) from encuesta_resuelta";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            int cont = 1;
            if(RS.next()){
                cont = RS.getInt(1);
            }
            
            stat = "select count{id_encuesta_resuelta}, * from encuesta_resuelta";
            RS = CE.getConsulta().executeQuery(stat);
            
            HashMap<Integer, Integer> id_integracion = new HashMap<>();

            int[] encuesta_resuelta = new int[4];
            Vector<int[]> encuestas = new Vector<>();
            int contEncuestas = RS.getInt(1);
            
            if(RS.next()){
                do{
                    encuesta_resuelta[0] = RS.getInt("id_encuesta_resuelta");
                    encuesta_resuelta[1] = RS.getInt("id_encuesta");
                    encuesta_resuelta[2] = RS.getInt("id_departamento");
                    encuesta_resuelta[3] = RS.getInt("ano_encuesta");
                    encuestas.add(encuesta_resuelta);
                }while(RS.next());
            }
            else{
                C.desconectar();
                CE.desconectar();
                return;
            }
            
            stat = "select * from trabajadores_x_encuesta where ano_encuesta = " + encuestas.elementAt(0)[3] + " and id_departamento = " + encuestas.elementAt(0)[2];
            RS = C.getConsulta().executeQuery(stat);
            if(RS.next()){
                C.desconectar();
                CE.desconectar();
                return;
            }
            
            for(int i = 0; i < encuestas.size();i++){
                stat = "insert into encuesta_resuelta values(null, " + encuestas.elementAt(i)[1] + ", " + encuestas.elementAt(i)[2] + ", " + encuestas.elementAt(i)[3] + ")";
                C.getConsulta().execute(stat);
                cont++;
                id_integracion.put(encuestas.elementAt(i)[0], cont);
            }
            
            stat = "select * from preguntas_encuesta_resuelta";
            RS = CE.getConsulta().executeQuery(stat);
            while(RS.next()){
                stat = "insert into preguntas_encuesta_resuelta values(" + id_integracion.get(RS.getInt("id_encuesta_resuelta")) + ", " + RS.getInt("id_pregunta") + ", " + RS.getInt("seleccion_pregunta") + ", '" + RS.getInt("argumento_pregunta") + "')";
                C.getConsulta().execute(stat);
            }
            
            stat = "insert into trabajadores_x_departamento values(" + encuesta_resuelta[2] + ", " + contEncuestas + " , " + contEncuestas + ", " + encuesta_resuelta[3] + ")";
            C.getConsulta().execute(stat);
            
            
            C.desconectar();
            CE.desconectar();
        } catch (SQLException e) {
        }
    }
    
}
