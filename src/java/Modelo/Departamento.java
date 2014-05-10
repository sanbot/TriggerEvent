/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author santiago
 */
public class Departamento {

    /*Se crean las variables necesarias y se instancia la clase cone para la conexion a la base de datos*/
    String Codigo;
    String Nombre;
    cone conexion = new cone();
    String Mensaje;

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public Departamento() {
        Connection conn = conexion.conectar();
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /*Metodo para registrar el departamento*/
    public boolean setRegistrarDepartamento(String nombre) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea el codigo para el registro*/
        String codigo = "DEP";
        int numerocodigo = this.CantidadRegistroDepartamento();
        codigo += numerocodigo;
        pr = null;
        /*Se crea la sentenica sql en un string*/
        String sql = "INSERT INTO tb_departamento(Codigo, Nombre)";
        sql += "VALUES(?,?)";
        try {
            /*Se prepara la sentencia y se envian los datos para ejecutarla*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            pr.setString(2, nombre);

            if (pr.executeUpdate() == 1) {
                return true;
            }
            /*En caso de error entra al cath*/
        } catch (SQLException ex) {
            /*Se evalua cual fue el error de la insercion*/
            if (ex.toString().indexOf("Duplicate") > 0) {
                if (ex.toString().indexOf("Nombre") > 0) {
                    this.setMensaje("Ya existe un departamento registrado con este nombre.");
                }
            }
            /*Se retorna falso*/
            return false;
        } finally {
            try {
                /*Se cierra todo*/
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se retorna false y crea el mensaje que se muestra en la interfaz*/
        this.setMensaje("Ocurrió un problema inesperado al registrar el departamento. Estamos trabajando para solucionar este problema.");
        return false;
    }
    /*Metodo para contar los registros que se tienen en la base de  datos*/
    public int CantidadRegistroDepartamento() {
        /*Se crea e intancia las vlases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea un string con la sentencia sql*/
        String sql = "Select * "
                + "From  tb_departamento";
        try {
            /*Se prepara y ejecuta la sentencia*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int i = 0;
            /*Se cuentan los regisrtos*/
            while (rs.next()) {
                i++;
            }
            /*Se retorna el registro mas 1*/
            return i + 1;
        } catch (Exception ex) {
            /*En caso de error se muestra*/
            ex.printStackTrace();
        } finally {
            try {
                /*Se cierra todo*/
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {
            }
        }
        /*En caso de error retorna 0*/
        return 0;
    }
    /*Metodo para mostrar todos los datos */
    public String[][] BuscarDatosDepartamentoTodos() {
        /*Se crea e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea un string para la sentencia*/
        String sql = "SELECT Codigo, Nombre \n"
                + "FROM  `tb_departamento` \n";

        try {
            /**Se prepara y ejecuta la sentencia*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int rows = 0;
            /*Se cuentan los registros para crear un array*/
            while (rs.next()) {
                rows++;
            }
            /*Se crea el array para guardar los datos*/
            String[][] Datos = new String[rows][2];
            rs.beforeFirst();
            rows = 0;
            /*Se guardan los datos*/
            while (rs.next()) {
                Departamento dep = new Departamento();
                dep.setCodigo(rs.getString("Codigo"));
                dep.setNombre(rs.getString("Nombre"));
                Datos[rows][0] = dep.getCodigo();
                Datos[rows][1] = dep.getNombre();
                rows++;
            }
            /*Se retorna el array*/
            return Datos;
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            ex.printStackTrace();
        } finally {
            try {
                /*Se cierra todo*/
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {
            }
        }
        /*Se retorna null en caso de error**/
        return null;
    }
    /*Metodo para actualizar los datos*/
    public boolean actualizardatosDepartamento(String codigo, String nombre) {
        /*Se crea e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea un string con la sentencia sql*/
        String sql = "UPDATE tb_departamento SET Nombre = ? ";
        sql += "WHERE Codigo = ?";
        try {
            /*Se prepara la sentencia, se envian los datos y se ejecuta la sentencia*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, nombre);
            pr.setString(2, codigo);
            /*Se ejecuta y se retorna true si no tiene ningun problema*/
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            /*En caso de error se evalua la causa*/
            if (ex.toString().indexOf("Duplicate") > 0) {
                if (ex.toString().indexOf("Nombre") > 0) {
                    this.setMensaje("Ya existe un departamento registrado con este nombre.");
                }
            }
            /*Se retorna falso despues de saber la causa del error*/
            return false;
        } finally {
            try {
                /*Se cierra todo*/
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se muestra un mensaje en caso de error y se retorna false*/
        this.setMensaje("Ocurrió un problema inesperado al modificar el departamento. Estamos trabajando para solucionar este problema.");
        return false;
    }
}
