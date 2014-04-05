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
    
    public Departamento(){
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
    
    public boolean setRegistrarDepartamento (String nombre)
    {
        Connection conn = conexion.conectar();
        String Est = "";
        PreparedStatement pr=null;
        String codigo = "DEP";
        int numerocodigo = this.CantidadRegistroDepartamento();
        codigo+=numerocodigo;
        pr=null;
        String sql="INSERT INTO tb_departamento(Codigo, Nombre)";
        sql+="VALUES(?,?)";
        try{
            
            pr=conn.prepareStatement(sql);
            pr.setString(1, codigo);
            pr.setString(2, nombre);
            
            
            if(pr.executeUpdate()==1){
                return true;
            }
        }catch(SQLException ex){
            if(ex.toString().indexOf("Duplicate")>0)
            {
                if(ex.toString().indexOf("Nombre")>0)
                {
                    this.setMensaje("Ya existe un departamento registrado con este nombre.");
                }
            }
            return false;
        }finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al tratar de insertar los datos del departamento, por favor, inténtelo de nuevo.");
        return false;
    }
    
    public int CantidadRegistroDepartamento(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select * "+
                   "From  tb_departamento";
        try{
            pr=conn.prepareStatement(sql);
            rs=pr.executeQuery();
            
            int i = 0;
            while(rs.next()){
                
                i++;
            }
            return i+1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                rs.close();
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        return 0;
    }
    
    public String[][] BuscarDatosDepartamentoTodos(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="SELECT Codigo, Nombre \n" +
                    "FROM  `tb_departamento` \n";
        
        try{
            pr=conn.prepareStatement(sql);
            rs=pr.executeQuery();
            
            int rows = 0;
            while(rs.next())
            {
                rows ++;
            }
            String [][] Datos = new String[rows][2];
            rs.beforeFirst();
            rows = 0;
            while(rs.next()){
                Departamento dep = new Departamento();
                dep.setCodigo(rs.getString("Codigo"));
                dep.setNombre(rs.getString("Nombre"));
                Datos[rows][0] = dep.getCodigo();
                Datos[rows][1] = dep.getNombre();
                
                rows++;
                
            }
            return Datos;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                rs.close();
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        return null;
    }
    
    public boolean actualizardatosDepartamento(String codigo, String nombre) {
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        String sql="UPDATE tb_departamento SET Nombre = ? ";
                sql += "WHERE Codigo = ?";
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, nombre);
            pr.setString(2, codigo);
            if(pr.executeUpdate()==1){
                return true;
            }
        }catch(Exception ex){
            if(ex.toString().indexOf("Duplicate")>0)
            {
                if(ex.toString().indexOf("Nombre")>0)
                {
                    this.setMensaje("Ya existe un departamento registrado con este nombre.");
                }
            }
            return false;
        }finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al tratar de modificar los datos del departamento, por favor, inténtelo de nuevo.");
        return false;
    }
}
