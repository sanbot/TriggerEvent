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
public class Ciudad {

    String Codigo;
    String Nombre;
    String Departamento;
    String Mensaje;

    cone conexion = new cone();

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

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public Ciudad() {
        Connection conn = conexion.conectar();
    }

    public boolean setRegistrarCiudad(String nombre, String departamento) {
        Connection conn = conexion.conectar();
        String Est = "";
        PreparedStatement pr = null;
        String codigo = "CIU";
        int numerocodigo = this.CantidadRegistroCiudad();
        codigo += numerocodigo;
        pr = null;
        String sql = "INSERT INTO tb_ciudad (Codigo, Nombre, Codigo_Departamento)";
        sql += "VALUES(?,?,?)";
        try {

            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            pr.setString(2, nombre);
            pr.setString(3, departamento);

            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.print(ex.toString());
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al insertar la ciudad. Estamos trabajando para solucionar este problema.");
        return false;
    }

    public int CantidadRegistroCiudad() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select * "
                + "From  tb_ciudad";
        try {
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int i = 0;
            while (rs.next()) {

                i++;
            }
            return i + 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return 0;
    }
    /**por quitar*/
    public String[][] BuscarDatosCiudadTodos() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT c.Codigo Codigo, c.Nombre Ciudad, c.Codigo_Departamento Departamento, d.Nombre Nombre \n"
                + "FROM  `tb_ciudad` c "
                + "Join tb_departamento d on c.Codigo_Departamento = d.Codigo \n";

        try {
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            String[][] Datos = new String[rows][4];
            rs.beforeFirst();
            rows = 0;
            String NombreDepar;
            while (rs.next()) {
                Ciudad ciu = new Ciudad();
                ciu.setCodigo(rs.getString("Codigo"));
                ciu.setNombre(rs.getString("Ciudad"));
                ciu.setDepartamento(rs.getString("Departamento"));
                NombreDepar = rs.getString("Nombre");
                Datos[rows][0] = ciu.getCodigo();
                Datos[rows][1] = ciu.getNombre();
                Datos[rows][2] = ciu.getDepartamento();
                Datos[rows][3] = NombreDepar;

                rows++;

            }
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }
    
    public String[][] BuscarDatosCiudadTodos(String CodigoDepartamento) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT c.Codigo Codigo, c.Nombre Ciudad \n"
                + "FROM  `tb_ciudad` c "
                + "Join tb_departamento d on c.Codigo_Departamento = d.Codigo "
                + " And d.Codigo = ? ORDER BY Ciudad\n";

        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, CodigoDepartamento);
            rs = pr.executeQuery();

            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            String[][] Datos = new String[rows][2];
            rs.beforeFirst();
            rows = 0;
            String NombreDepar;
            while (rs.next()) {
                Datos[rows][0] = rs.getString("Codigo");
                Datos[rows][1] = rs.getString("Ciudad");

                rows++;

            }
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }

    public boolean actualizardatosCiudad(String codigo, String nombre, String departamento) {
        Connection conn = conexion.conectar();
        PreparedStatement prs = null;
        String sql = "UPDATE `tb_ciudad` SET `Nombre` = '" + nombre + "', `Codigo_Departamento` = '" + departamento + "' WHERE `Codigo` = '" + codigo + "'";
        try {
            prs = conn.prepareStatement(sql);
            //prs.setString(1, codigo);
            //prs.setString(2, nombre);
            //prs.setString(3, departamento);
            //int i = prs.executeUpdate();
            if (prs.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            System.out.printf(ex.toString());
        } finally {
            try {
                prs.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al modificar la ciudad. Estamos trabajando para solucionar este problema.");
        return false;
    }
}
