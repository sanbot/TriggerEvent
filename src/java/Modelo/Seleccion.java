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
import java.io.File;

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
        int numerocodigo = this.CantidadRegistroCiudad();
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
            System.out.print(ex.toString());
        }finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al tratar de insertar los datos de la ciudad, por favor, inténtelo de nuevo.");
        return false;
    }
    
    public int CantidadRegistroCiudad(){
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
}
