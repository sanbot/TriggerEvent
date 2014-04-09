/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADSI
 */
public class Evento {
    String Codigo;
    String Mensaje;
    String Nombre;
    String Fecha;
    String Descipcion;
    String Imagen;
    String Creador;
    String Rango;
    Date FechaDate;
    cone conexion = new cone();
    String Direccion;
    String Ciudad;

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public Date getFechaDate() {
        return FechaDate;
    }

    public void setFechaDate(Date FechaDate) {
        this.FechaDate = FechaDate;
    }

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

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getDescipcion() {
        return Descipcion;
    }

    public void setDescipcion(String Descipcion) {
        this.Descipcion = Descipcion;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public String getCreador() {
        return Creador;
    }

    public void setCreador(String Creador) {
        this.Creador = Creador;
    }

    public String getRango() {
        return Rango;
    }

    public void setRango(String Rango) {
        this.Rango = Rango;
    }
    
    
    public boolean ConvertirFecha(String FechaDate)
    {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            Date nuevaFecha;
            nuevaFecha = formatoFecha.parse(FechaDate);
            this.setFechaDate(nuevaFecha);
            return true;
        } catch (ParseException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean ValidarDosDiasFecha(Date FechaInicial)
    {
        Date FechaSistema = new Date();
        if(FechaInicial.getDate() >= FechaSistema.getDate()+2)
        {
            return true;
        }
        return false;
    }
    
    public boolean setRegistrarEvento(String imagen, String nombre, Date fecha, String descripcion, String rango, String creador, String ciudad, String direccion)
    {
        Connection conn = conexion.conectar();
        String Est = "Pendiente";
        PreparedStatement pr=null;
        String codigo = "EVE";
        int numerocodigo = this.CantidadRegistroEvento();
        codigo+=numerocodigo;
        pr=null;
        String sql="INSERT INTO tb_evento(Codigo, Imagen, Nombre, Fecha, Descripcion, Rango_Precios, NIT, Codigo_Ciudad, Direccion, Estado)";
        sql+="VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        FileInputStream is= null;
        
        try {
            is = new FileInputStream(imagen);
        } catch (FileNotFoundException ex) {
            this.setMensaje(ex.getMessage().toString());
        }
        
        try{
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr=conn.prepareStatement(sql);
            pr.setString(1, codigo);
            pr.setBlob(2, is);
            pr.setString(3, nombre);
            pr.setTimestamp(4, sqlDate);
            pr.setString(5, descripcion);
            pr.setString(6, rango);
            pr.setString(7, creador);
            pr.setString(8, ciudad);
            pr.setString(9, direccion);
            pr.setString(10, Est);
            
            
            if(pr.executeUpdate()==1){
                return true;
            }
        }catch(SQLException ex){
            if(ex.toString().indexOf("uk_nombre_codigociudad_fecha")>0)
            {
                this.setMensaje("No se puede registrar este evento ya que existe un evento con el mismo nombre, la misma fecha y en la misma ciudad");
            }
            return false;
        }finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al tratar de insertar los datos del evento, por favor, inténtelo de nuevo.");
        return false;
    }
    
    public int CantidadRegistroEvento(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select * "+
                   "From  tb_evento";
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
    
    public int CantidadEventoPendiente(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select Count(Codigo) "+
                   "From  tb_evento"
                + " Where Estado = 'Pendiente'";
        try{
            pr=conn.prepareStatement(sql);
            rs=pr.executeQuery();
            
            int i = 0;
            while(rs.next()){
                
                i++;
            }
            return i;
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
    
    public String[][] BuscarDatosPrincipalesEventos(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad \n" +
                    "FROM  `tb_evento` e \n"+
                    "JOIN tb_usuario u on u.No_Documento = e.NIT \n"+
                    "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"+
                    "Where e.Estado = 'Aprobado' AND "
                  + "Fecha >= ?";
        
        try{
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr=conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs=pr.executeQuery();
            
            int rows = 0;
            while(rs.next())
            {
                rows ++;
            }
            String [][] Datos = new String[rows][5];
            rs.beforeFirst();
            rows = 0;
            while(rs.next()){
                Evento eve = new Evento();
                eve.setCodigo(rs.getString("Codigo"));
                eve.setNombre(rs.getString("Nombre"));
                eve.setFecha(rs.getString("Fecha"));
                eve.setCreador(rs.getString("NombreEmpresa"));
                eve.setCiudad(rs.getString("NombreCiudad"));
                Datos[rows][0] = eve.getCodigo();
                Datos[rows][1] = eve.getNombre();
                Datos[rows][2] = eve.getFecha();
                Datos[rows][3] = eve.getCreador();
                Datos[rows][4] = eve.getCiudad();
                
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
    
    public Blob getImagenEvento(String codigo){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select Imagen "+
                   "From  tb_evento Where Codigo = ?";
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
}
