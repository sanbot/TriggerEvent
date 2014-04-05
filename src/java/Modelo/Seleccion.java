/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Blob;

/**
 *
 * @author santiago
 */
public class Seleccion {
    String Codigo;
    String Mensaje;
    String Nombre;
    String Tipo;
    String Imagen;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }
    
    cone conexion = new cone();
    
    public Seleccion(){
        Connection conn = conexion.conectar();
    }
    
    public boolean setRegistrarSeleccion (String nombre, String tipo, String imagen)
    {
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        String codigo = "SEL";
        int numerocodigo = this.CantidadRegistroSeleccion();
        codigo+=numerocodigo;
        pr=null;
        String sql="INSERT INTO tb_seleccion (Codigo, Imagen, Nombre, Tipo)";
        sql+="VALUES(?,?,?,?)";
        FileInputStream is= null;
        
        try {
            is = new FileInputStream(imagen);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Seleccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            
            pr=conn.prepareStatement(sql);
            pr.setString(1, codigo);
            pr.setBlob(2, is);
            pr.setString(3, nombre);
            pr.setString(4, tipo);
            
            
            if(pr.executeUpdate()==1){
                return true;
            }
        }catch(SQLException ex){
            if(ex.toString().indexOf("Duplicate")>0)
            {
                if(ex.toString().indexOf("Nombre")>0)
                {
                    this.setMensaje("Ya hay un ambiente o gusto con este nombre.");
                    return false;
                }
            }
        }finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al tratar de insertar los datos de la selección, por favor, inténtelo de nuevo.");
        return false;
    }
    
    public int CantidadRegistroSeleccion(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select * "+
                   "From  tb_seleccion";
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
    
    public String[][] getDatosSeleccion(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select * "+
                   "From  tb_seleccion";
        String [][] Datos = null;
        try{
            pr=conn.prepareStatement(sql);
            rs=pr.executeQuery();
            
            int i = 0;
            while(rs.next()){
                
                i++;
            }
            Datos = new String[i][3];
            rs.beforeFirst();
            i = 0;
            while(rs.next()){
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                i++;
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
    
    public Blob getImagenSeleccion(String codigo){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select Imagen "+
                   "From  tb_seleccion Where Codigo = ?";
        Blob Datos = null;
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs=pr.executeQuery();
            
            while(rs.next())
            {
                Datos = rs.getBlob("Imagen");
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
    public boolean actualizardatosSeleccion(String codigo, String nombre, String tipo) {
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        String sql="UPDATE tb_seleccion SET Nombre = ?, Tipo = ? ";
                sql += "WHERE Codigo = ?";
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, nombre);
            pr.setString(2, tipo);
            pr.setString(3, codigo);
            if(pr.executeUpdate()==1){
                return true;
            }
        }catch(Exception ex){
            if(ex.toString().indexOf("Duplicate")>0)
            {
                if(ex.toString().indexOf("Nombre")>0)
                {
                    this.setMensaje("Ya hay un ambiente o gusto con este nombre.");
                    return false;
                }
            }
        }finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al tratar de modificar los datos de la selección, por favor, inténtelo de nuevo.");
        return false;
    }
    
    public boolean actualizardatosSeleccion(String codigo, String nombre, String tipo, String imagen) {
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        String sql="UPDATE tb_seleccion SET Nombre = ?, Tipo = ?, Imagen = ? ";
                sql += "WHERE Codigo = ?";
        FileInputStream is= null;
        
        try {
            is = new FileInputStream(imagen);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Seleccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, nombre);
            pr.setString(2, tipo);
            pr.setBlob(3, is);
            pr.setString(4, codigo);
            if(pr.executeUpdate()==1){
                return true;
            }
        }catch(Exception ex){
            if(ex.toString().indexOf("Duplicate")>0)
            {
                if(ex.toString().indexOf("Nombre")>0)
                {
                    this.setMensaje("Ya hay un ambiente o gusto con este nombre.");
                    return false;
                }
            }
        }finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al tratar de modificar los datos de la selección, por favor, inténtelo de nuevo.");
        return false;
    }
    
    public String[][] getGustosNuevos(String codigo){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select Codigo, Nombre, Tipo, Imagen "+
                   "From  tb_seleccion "
                +  "Where Codigo NOT IN (select Id_Seleccion From tb_seleccion_usuario Where Id_Usuario = ? And Estado = 'Activo')";
        String [][] Datos = null;
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs=pr.executeQuery();
            
            int i = 0;
            while(rs.next()){
                
                i++;
            }
            Datos = new String[i][3];
            rs.beforeFirst();
            i = 0;
            while(rs.next()){
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                i++;
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
    
    public String[][] getMisGustos (String codigo){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select Codigo, Nombre, Tipo, Imagen "+
                   "From  tb_seleccion "
                +  "Where Codigo IN (select Id_Seleccion From tb_seleccion_usuario Where Id_Usuario = ?"
                + " And Estado = 'Activo')";
        String [][] Datos = null;
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs=pr.executeQuery();
            
            int i = 0;
            while(rs.next()){
                
                i++;
            }
            Datos = new String[i][3];
            rs.beforeFirst();
            i = 0;
            while(rs.next()){
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                i++;
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
    
    public boolean AddGusto(String codigoSeleccion, String CodigoUsuario) {
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs = null;
        String codigo = "GUS";
        codigo+= this.CantidadGustos();
        String sqlc = "Select Codigo From tb_seleccion_usuario Where Id_Seleccion = ? and Id_Usuario = ? ";
        String sqli="Insert Into tb_seleccion_usuario (Codigo, Id_Seleccion, Id_Usuario, Estado)"
                + " Values (?,?,?,?)";
        String sqlu = "UPDATE tb_seleccion_usuario SET Estado = 'Activo' Where Codigo = ?";
        
        try{
            pr = conn.prepareStatement(sqlc);
            pr.setString(1, codigoSeleccion);
            pr.setString(2, CodigoUsuario);
            rs = pr.executeQuery();
            if(rs.next())
            {
                pr=conn.prepareStatement(sqlu);
                pr.setString(1, rs.getString("Codigo"));
                if(pr.executeUpdate()==1)
                {
                    return true;
                }
            }
            else
            {
                pr=conn.prepareStatement(sqli);
                pr.setString(1, codigo);
                pr.setString(2, codigoSeleccion);
                pr.setString(3, CodigoUsuario);
                pr.setString(4, "Activo");
                if(pr.executeUpdate()==1)
                {
                    return true;
                }
            }
            
        }catch(Exception ex){
            if(ex.toString().indexOf("Duplicate")>0)
            {
                if(ex.toString().indexOf("UK_Id_Seleccion_Id_Usuario")>0)
                {
                    this.setMensaje("Ya existe este gusto registrado.");
                    return false;
                }
            }
        }finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al tratar de modificar los datos de la selección, por favor, inténtelo de nuevo.");
        return false;
    }
    
    public boolean RemoveGusto(String codigoSeleccion, String CodigoUsuario) {
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs = null;
        String codigo = "GUS";
        codigo+= this.CantidadGustos();
        String sqlc = "Select Codigo From tb_seleccion_usuario Where Id_Seleccion = ? and Id_Usuario = ? ";
        String sqlu = "UPDATE tb_seleccion_usuario SET Estado = 'Desactivo' Where Codigo = ?";
        
        try{
            pr = conn.prepareStatement(sqlc);
            pr.setString(1, codigoSeleccion);
            pr.setString(2, CodigoUsuario);
            rs = pr.executeQuery();
            if(rs.next())
            {
                pr=conn.prepareStatement(sqlu);
                pr.setString(1, rs.getString("Codigo"));
                if(pr.executeUpdate()==1)
                {
                    return true;
                }
            }
            
        }catch(Exception ex){
            System.out.print(ex.toString());
        }finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al tratar de modificar los datos de la selección, por favor, inténtelo de nuevo.");
        return false;
    }
    
    public int CantidadGustos(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select * "+
                   "From  tb_seleccion_usuario";
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
}
