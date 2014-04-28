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

     // CONEXION A LA BASE DE DATOS
        try {
            //MYSQL Con el driver
            Class.forName("com.mysql.jdbc.Driver");
            //String de conexion con mySQL
            String MYSQL = "jdbc:mysql://localhost:3306/trigger_event?zeroDateTimeBehavior=convertToNull";
            //Conexion usuario y contraseña
            conn = DriverManager.getConnection(MYSQL, "root", "0000");

        } catch (ClassNotFoundException ex)// problemas con sql o bd, usuario,contraseña
        {
            System.out.println("Error: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return conn;
    }
}
