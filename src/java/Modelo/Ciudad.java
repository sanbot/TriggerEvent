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
    /*Se declaran las variables necesarias para realizar las operaciones de esta clase*/
    String Codigo;
    String Nombre;
    String Departamento;
    String Mensaje;
    /*Se instancia la clase cone para la conexion a la base de datos*/
    cone conexion = new cone();

    /*Se crean los metodos set y get*/
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
    
    /*conectamos la base de datos en el contructor*/
    public Ciudad() {
        Connection conn = conexion.conectar();
    }

    /*Creamos un metodo para registrar la ciudad*/
    public boolean setRegistrarCiudad(String nombre, String departamento) {
        Connection conn = conexion.conectar();
        /*Conectamos a la base de datos*/
        /*Creamos la primera parte del codigo*/
        String codigo = "CIU";
        /*Se concatena el codigo con un numero retornado por el metodo cantidad regisro ciudad*/
        int numerocodigo = this.CantidadRegistroCiudad();
        codigo += numerocodigo;
        
        /*Creamos una variable para la sentencia preparada*/
        PreparedStatement pr = null;
        
        /*Creamos un string para insertar los datoss*/
        String sql = "INSERT INTO tb_ciudad (Codigo, Nombre, Codigo_Departamento)";
        sql += "VALUES(?,?,?)";
        try {
            /*preparamos la sentencia y evniamos los datos*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            pr.setString(2, nombre);
            pr.setString(3, departamento);

            /*Ejecutamos la sentencia*/
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            /*Se muestra por consola en caso de error*/
            System.out.print(ex.toString());
        } finally {
            /*Finalmente se cierra la conexion y la sentenica preparada*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*En caso de error se muestra este mensaje y retornamos falso*/
        this.setMensaje("Ocurrió un problema inesperado al insertar la ciudad. Estamos trabajando para solucionar este problema.");
        return false;
    }

    /*Metodo para contar la cantidad de registros en la base de datos*/
    public int CantidadRegistroCiudad() {
        /*Se conecta a la base de datos*/
        Connection conn = conexion.conectar();
        /*Se crea la variable para la sentencia preparada*/
        PreparedStatement pr = null;
        /*Se crea la variable para recorrer los resultados*/
        ResultSet rs = null;
        /*Se crea un string con la sentencia sql*/
        String sql = "Select * "
                + "From  tb_ciudad";
        try {
            /*Se prepara la sentencia sql y se ejecuta*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int i = 0;
            while (rs.next()) {
                /*Se cuentan los resultados*/
                i++;
            }
            return i + 1;
        } catch (Exception ex) {
            /*Se muestra el error en dicho caso*/
            ex.printStackTrace();
        } finally {
            try {
                /*Se cierra el resultset, la sentencia preparada y la conexion*/
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*En caso de no encontrar resultados retorna 0*/
        return 0;
    }
    /*Buscar todos los datos de la ciudad*/
    public String[][] BuscarDatosCiudadTodos() {
        /*Se conecta a la base de datos*/
        Connection conn = conexion.conectar();
        /*Se crea una variable para preparar la sentencia y otra para recorrerla, 
        ademas se crea la sentencia en un string*/
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT c.Codigo Codigo, c.Nombre Ciudad, c.Codigo_Departamento Departamento, d.Nombre Nombre \n"
                + "FROM  `tb_ciudad` c "
                + "Join tb_departamento d on c.Codigo_Departamento = d.Codigo \n";

        try {
            /*se prepara la sentencia SQL y se ejecuta*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int rows = 0;
            while (rs.next()) {
                /*Se cuentan los datos para crear el array*/
                rows++;
            }
            /*Se crea el array para retornar los datos*/
            String[][] Datos = new String[rows][4];
            /*Se devuelve a el resultset antes de la primera posicion*/
            rs.beforeFirst();
            rows = 0;
            String NombreDepar;
            while (rs.next()) {
                /*Se recorre y se guardan los resultados en el array*/
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
            /*Se retorna el array*/
            return Datos;
        } catch (Exception ex) {
            /*Se muestra el error en caso de error*/
            ex.printStackTrace();
        } finally {
            try {
                /*Finalmente se cierra la coxion, los resultados y la sentencia preparada*/
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }
    
    public String[][] BuscarDatosCiudadTodos(String CodigoDepartamento) {
        /*Se conecta a la base de datos*/
        Connection conn = conexion.conectar();
        /*Se crea una variable para preparar la sentencia y otra para recorrerla, 
        ademas se crea la sentencia en un string*/
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT c.Codigo Codigo, c.Nombre Ciudad \n"
                + "FROM  `tb_ciudad` c "
                + "Join tb_departamento d on c.Codigo_Departamento = d.Codigo "
                + " And d.Codigo = ? ORDER BY Ciudad\n";

        try {
            /*se prepara la sentencia SQL, Se le envian las variables para la consulta
            y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, CodigoDepartamento);
            rs = pr.executeQuery();

            int rows = 0;
            while (rs.next()) {
                rows++;
                /*Se cuentan los registros para crear el array*/
            }
            /*Se crea el array para retornar los datos*/
            String[][] Datos = new String[rows][2];
            rs.beforeFirst();
            rows = 0;
            String NombreDepar;
            while (rs.next()) {
                /*Se recorre y se guardan los resultados en el array*/
                Datos[rows][0] = rs.getString("Codigo");
                Datos[rows][1] = rs.getString("Ciudad");
                rows++;
            }
            /*Se retorna el array con los datos*/
            return Datos;
        } catch (Exception ex) {
            /*En caso de error se muestra*/
            ex.printStackTrace();
        } finally {
            try {
                /*Se cierra la conexion, la sentencia preparada, y el resultset*/
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {
            }
        }
        /*En caso de error se retorna null*/
        return null;
    }

    public boolean actualizardatosCiudad(String codigo, String nombre, String departamento) {
        /*Se conecta a la base de datos*/
        Connection conn = conexion.conectar();
        /*Se crea una sentencia preparada*/
        PreparedStatement prs = null;
        /*Se crea la sentencia en un string*/
        String sql = "UPDATE `tb_ciudad` SET `Nombre` = ?, `Codigo_Departamento` = ? "
                + "WHERE `Codigo` = ?";
        try {
            /*Se prepara la sentencia y se le envian las variables para ejecutarla*/
            prs = conn.prepareStatement(sql);
            prs.setString(1, nombre);
            prs.setString(2, departamento);
            prs.setString(3, codigo);
            int i = prs.executeUpdate();
            /*Se ejecuta la sentenica*/
            if (prs.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            /*Se mustra el error en caso de error*/
            System.out.printf(ex.toString());
        } finally {
            try {
                /*Se cierra la sentenica preparada y la conexion*/
                prs.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*En caso de error se retorna falso y se muestra este mensaje*/
        this.setMensaje("Ocurrió un problema inesperado al modificar la ciudad. Estamos trabajando para solucionar este problema.");
        return false;
    }
}
