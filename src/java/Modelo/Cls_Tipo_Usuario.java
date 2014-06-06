/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author santi_000
 */
public class Cls_Tipo_Usuario {

    /*Se crean los metodos set y get las varibales y la clase de conexion necesarias*/
    Cls_Conexion conexion = new Cls_Conexion();

    public Cls_Tipo_Usuario() {
        Connection conn = conexion.conectar();
    }

    String Codigo;
    String Tipo_Usuario;
    String Descripcion;

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getTipo_Usuario() {
        return Tipo_Usuario;
    }

    public void setTipo_Usuario(String Tipo_Usuario) {
        this.Tipo_Usuario = Tipo_Usuario;
    }

    /*MEtodo para buscar todos los datos de tipos de usuario*/
    public String[][] BuscarDatosTipoUsuarioTodos() {
        /*Se crea e instancia las variables y clases de conxion necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se traen todos los datos de tipos de usuario con esta sentenica*/
        String sql = "Select * "
                + "From  tb_tipo_usuario";

        try {
            /*Se prepara la sentencia y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            int rows = 0;
            /*Se cuentan los registros para crear un array posteriormente*/
            while (rs.next()) {
                rows++;
            }
            rs.beforeFirst();
            /*Se crea el array y se guardan los datos*/
            String[][] Datos = new String[rows][2];
            rows = 0;
            while (rs.next()) {
                Datos[rows][0] = rs.getString("Codigo");
                Datos[rows][1] = rs.getString("Tipo");
                rows++;
            }
            /*Se retornan los resultados*/
            return Datos;
        } catch (Exception ex) {
            /*Se muestran los resultados*/
            ex.printStackTrace();
        } finally {
            /*Se cierra todo*/
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se retorna null en caso de error*/
        return null;
    }
}
