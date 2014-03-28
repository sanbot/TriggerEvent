/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author santi_000
 */
public class Tipo_Usuario {
    
    cone conexion = new cone();
    public Tipo_Usuario(){
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
    public String[][] BuscarDatosTipoUsuarioTodos(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select * "+
                   "From  TB_TIPO_USUARIO";
        
        try{
            pr=conn.prepareStatement(sql);
            rs=pr.executeQuery();
            int rows = 0;
             while(rs.next()){
                rows ++;
            }
            rs.beforeFirst();
            String [][] Datos = new String[rows][2];
            rows = 0;
            while(rs.next()){
                
                Datos[rows][0] = rs.getString("Codigo");
                Datos[rows][1] = rs.getString("Tipo");
                
                
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
}
