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
}
