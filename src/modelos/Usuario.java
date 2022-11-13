/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import base_de_datos.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author electro-zombie
 */
public class Usuario {


public static boolean verifyUsuario(String user, String password){
    Conexion C = new Conexion();
    try {
        C.conectar();
        
        String stat = "select * from usuario where nombre_usuario = '" + user + "'";
        ResultSet RS = C.getConsulta().executeQuery(stat);
        
        if(RS.next()){
            String P = RS.getString("contrasena_usuario");
            
            if(P.equals(password)){
                C.desconectar();
                return true;
            }
        }
        
        C.desconectar();
    } catch (SQLException e) {
    }
    return false;
}
    
}
