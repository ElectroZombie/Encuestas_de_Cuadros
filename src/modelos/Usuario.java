/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import base_de_datos.Conexion;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author electro-zombie
 */
public class Usuario {


public static boolean verifyUsuario(String user, String password){
        Conexion C = new Conexion();
            
        String U = "";
        String P = "";
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(user.getBytes());
            int size = b.length;
            StringBuffer h = new StringBuffer(size);
            for(int i = 0; i < size; i++){
                int u = b[i] & 255;
                if(u < 16){
                    h.append("0").append(Integer.toHexString(u));
                }
                else{
                    h.append(Integer.toHexString(u));
                }
            }
            U = h.toString();
            
            b = md.digest(password.getBytes());
            size = b.length;
            h = new StringBuffer(size);
            for(int i = 0; i < size; i++){
                int u = b[i] & 255;
                if(u < 16){
                    h.append("0").append(Integer.toHexString(u));
                }
                else{
                    h.append(Integer.toHexString(u));
                }
            }
            P = h.toString();
        
        } catch (NoSuchAlgorithmException ex) {

        }
        
        try {
            C.conectar();
            
            String stat = "select * from usuario where nombre_usuario = '" + U + "'";
            ResultSet RS = C.getConsulta().executeQuery(stat);
            
            if(RS.next()){
                String Pass = RS.getString("contrasena_usuario");
                
                if(Pass.equals(P)){
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
