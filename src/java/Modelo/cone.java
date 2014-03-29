/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class cone {
    Connection conn;
  
  public Connection conectar() {
   
      // CONEXION A BD
    
    try {
                        //SQL SERVER JUNTO KON EL DRIVER
     Class.forName("com.mysql.jdbc.Driver");
     //DriverManager.getConnection(server,user,password);
         //llamar driver en libreria para establecer conexion
    /* String Santiago = "jdbc:sqlserver://SANTI-PC\\SANTIAGO:1433;databaseName=Trigger_Event;user=sa;password=12345678";
     String Sergio = "jdbc:sqlserver://SERGIO-PC\\SQLEXPRESS:1433;databaseName=Trigger_Event;user=sa;password=123456789";
     */
     String MYSQL = "jdbc:mysql://localhost:3306/trigger_event?zeroDateTimeBehavior=convertToNull";
     conn = DriverManager.getConnection(MYSQL, "root", "0000");
     
   }
      catch (ClassNotFoundException ex)// problemas con sql o bd, usuario,contraseña
      {
       System.out.println("Error: " + ex.getMessage());
       
     }
     catch (SQLException ex)
     {
      System.out.println("Error: " + ex.getMessage());
    }
    return conn;
    
  }
}
