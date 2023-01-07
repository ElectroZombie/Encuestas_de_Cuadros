
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base_de_datos;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion{
    
	Connection conexion;

    public Statement getConsulta() {
        return consulta;
    }

	Statement consulta;
	public String ruta;


    
    public Conexion()
    {
        
 ruta = System.getProperty("user.dir")+System.getProperty("file.separator")+"database"+System.getProperty("file.separator")+"EncuestasDB.db";    
    ///RUTA PARA EL JAR SI NO NO CORRE
 //  ruta = "Database" + System.getProperty("file.separator") + "EncuestasDB.db";
        
    }
    
    public Conexion(String ruta){
        this.ruta = ruta;
    }
    
     public Connection conectar(){
		try {
	            Class.forName("org.sqlite.JDBC");
	        }
	        catch (ClassNotFoundException e) {
	            JOptionPane.showMessageDialog(null, e.getMessage());
	        }	 
			try {
                           
                            conexion = DriverManager.getConnection("jdbc:sqlite:"+ruta);
                            
                            consulta=conexion.createStatement();
                            
                            
			} catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage());
                            
                            System.out.println(e.getMessage());
                        }
            return conexion;
                        
                       
	}
    public void desconectar(){
        
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    


    
   
    
}
