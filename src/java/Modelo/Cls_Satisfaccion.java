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
import java.util.Date;

/**
 *
 * @author santiago
 */
public class Cls_Satisfaccion {
    String Codigo;
    String Id_Evento;
    String Id_Usuario;
    String Comentario;
    String Calificacion;
    String Mensaje;

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getId_Evento() {
        return Id_Evento;
    }

    public void setId_Evento(String Id_Evento) {
        this.Id_Evento = Id_Evento;
    }

    public String getId_Usuario() {
        return Id_Usuario;
    }

    public void setId_Usuario(String Id_Usuario) {
        this.Id_Usuario = Id_Usuario;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }

    public String getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(String Calificacion) {
        this.Calificacion = Calificacion;
    }
    
    cone conexion = new cone();
    
    public Cls_Satisfaccion(){
        Connection conn = conexion.conectar();
    }
    
    public boolean setRegistrarCalificacionYComentarioSatisfaccion (String codigoUsuario, String codigoEvento, String Rating, String comentario)
    {
        //incializamos los valores necesarios
        int numerocodigo = this.CantidadRegistroSatisfaccion();
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs;
        String codigo = "SAT";
        codigo+=numerocodigo;
        
        //string para consulta, insert y update
        String insert = "Insert into tb_satisfaccion (Codigo, Id_Evento, Id_Usuario, Calificacion, Comentario, Fecha) Values(?,?,?,?,?,?)";
       
        Date fecha = new Date();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
        try
        {
            
            //preparamos la sentencia para insertar los datos
            pr = conn.prepareStatement(insert);

            //enviamos los parametros necesarios para ejecutar la sentencia
            pr.setString(1, codigo);
            pr.setString(2, codigoEvento);
            pr.setString(3, codigoUsuario);
            pr.setString(4, Rating);
            pr.setString(5, comentario);
            pr.setTimestamp(6, sqlDate);

            //ejecutamos la sentencia y evaluamos el resultado
            if(pr.executeUpdate()==1)
            {
                //si se insertaron los datos retornamso un verdadero
                return true;
            }
            else
            {
                //sino se inserta la calificacion, retornamos un falso y guardamos un mensaje encapsulado
                this.setMensaje("Ocurrió un problema inesperado al tratar de registrar la crítica para el evento.");
                return false;
            }
        }
        catch(SQLException ex)
        {
            this.setMensaje(ex.getMessage().toString());
        }
        finally
        {
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){
                this.setMensaje(ex.getMessage().toString());
            }
        }
        
        this.setMensaje("Ocurrió un problema inesperado al tratar de insertar los datos de la selección, por favor, inténtelo de nuevo.");
        return false;
    }
    
    public boolean setRegistrarCalificacionSatisfaccion (String codigoUsuario, String codigoEvento, String Rating)
    {
        //incializamos los valores necesarios
        int numerocodigo = this.CantidadRegistroSatisfaccion();
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs;
        String codigo = "SAT";
        codigo+=numerocodigo;
        
        //string para consulta, insert y update
        String select = "Select Codigo From tb_satisfaccion where Id_Usuario = ? AND Id_Evento = ?";
        String insert = "Insert into tb_satisfaccion (Codigo, Id_Evento, Id_Usuario, Calificacion) Values(?,?,?,?)";
        String update = "UPDATE tb_satisfaccion SET Calificacion = ? Where Codigo = ?";
       
        try
        {
            //preparamos la sentencia
            pr = conn.prepareStatement(select);
            
            //enviamos los parametros necesarios para la ejecucion
            pr.setString(1, codigoUsuario);
            pr.setString(2, codigoEvento);
            
            //ejecutamos la sentencia
            rs = pr.executeQuery();
            
            //evaluamos si se encontraron resultados
            if(rs.next())
            {
                //preparamos la sentencia para modificar los datos
                pr = conn.prepareStatement(update);
                
                pr.setString(1, Rating);
                pr.setString(2, rs.getString("Codigo"));
                //ejecutamos la sentencia y evaluamos el resultado
                if(pr.executeUpdate()==1)
                {
                    //si se insertaron los datos retornamso un verdadero
                    return true;
                }
                else
                {
                    //sino se inserta el comentario, retornamos un falso y guardamos un mensaje encapsulado
                    this.setMensaje("Ocurrió un problema inesperado al tratar de registrar el comentario para el evento.");
                    return false;
                }
            }
            else
            {
                //preparamos la sentencia para insertar los datos
                pr = conn.prepareStatement(insert);
                
                //enviamos los parametros necesarios para ejecutar la sentencia
                pr.setString(1, codigo);
                pr.setString(2, codigoEvento);
                pr.setString(3, codigoUsuario);
                pr.setString(4, Rating);
                
                //ejecutamos la sentencia y evaluamos el resultado
                if(pr.executeUpdate()==1)
                {
                    //si se insertaron los datos retornamso un verdadero
                    return true;
                }
                else
                {
                    //sino se inserta la calificacion, retornamos un falso y guardamos un mensaje encapsulado
                    this.setMensaje("Ocurrió un problema inesperado al tratar de registrar la calificación para el evento.");
                    return false;
                }
            }
        }
        catch(SQLException ex)
        {
            this.setMensaje(ex.getMessage().toString());
        }
        finally
        {
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){
                this.setMensaje(ex.getMessage().toString());
            }
        }
        
        this.setMensaje("Ocurrió un problema inesperado al tratar de insertar los datos de la selección, por favor, inténtelo de nuevo.");
        return false;
    }
    
    public boolean setRegistrarComentarioSatisfaccion (String codigoUsuario, String codigoEvento, String comentario)
    {
        //incializamos los valores necesarios
        int numerocodigo = this.CantidadRegistroSatisfaccion();
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs;
        String codigo = "SAT";
        codigo+=numerocodigo;
        
        //string para consulta, insert y update
        String select = "Select Codigo From tb_satisfaccion where Id_Usuario = ? AND Id_Evento = ?";
        String insert = "Insert into tb_satisfaccion (Codigo, Id_Evento, Id_Usuario, Comentario, Fecha) Values(?,?,?,?,?)";
        String update = "UPDATE tb_satisfaccion SET Comentario = ?, Fecha = ? Where Codigo = ?";
       
        Date fecha = new Date();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
        
        try
        {
            //preparamos la sentencia
            pr = conn.prepareStatement(select);
            
            //enviamos los parametros necesarios para la ejecucion
            pr.setString(1, codigoUsuario);
            pr.setString(2, codigoEvento);
            
            //ejecutamos la sentencia
            rs = pr.executeQuery();
            
            //evaluamos si se encontraron resultados
            if(rs.next())
            {
                //preparamos la sentencia para modificar los datos
                pr = conn.prepareStatement(update);
                
                pr.setString(1, comentario);
                pr.setTimestamp(2, sqlDate);
                pr.setString(3, rs.getString("Codigo"));
                
                //ejecutamos la sentencia y evaluamos el resultado
                if(pr.executeUpdate()==1)
                {
                    //si se insertaron los datos retornamso un verdadero
                    return true;
                }
                else
                {
                    //sino se inserta el comentario, retornamos un falso y guardamos un mensaje encapsulado
                    this.setMensaje("Ocurrió un problema inesperado al tratar de registrar el comentario para el evento.");
                    return false;
                }
            }
            else
            {
                //preparamos la sentencia para insertar los datos
                pr = conn.prepareStatement(insert);
                
                //enviamos los parametros necesarios para ejecutar la sentencia
                pr.setString(1, codigo);
                pr.setString(2, codigoEvento);
                pr.setString(3, codigoUsuario);
                pr.setString(4, comentario);
                pr.setTimestamp(5, sqlDate);
                
                //ejecutamos la sentencia y evaluamos el resultado
                if(pr.executeUpdate()==1)
                {
                    //si se insertaron los datos retornamso un verdadero
                    return true;
                }
                else
                {
                    //sino se inserta el comentario, retornamos un falso y guardamos un mensaje encapsulado
                    this.setMensaje("Ocurrió un problema inesperado al tratar de registrar el comentario para el evento.");
                    return false;
                }
            }
        }
        catch(SQLException ex)
        {
            this.setMensaje(ex.getMessage().toString());
        }
        finally
        {
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){
                this.setMensaje(ex.getMessage().toString());
            }
        }
        
        this.setMensaje("Ocurrió un problema inesperado al tratar de insertar los datos de la selección, por favor, inténtelo de nuevo.");
        return false;
    }
    
    public int CantidadRegistroSatisfaccion(){
        //inicializamos la variables necesarias
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        int i = 0;
        
        //creamos un string para la consulta
        String consulta ="Select Count(Codigo) Cantidad From  tb_satisfaccion";
        
        try{
            //preparamos la consulta
            pr=conn.prepareStatement(consulta);
            
            //ejecutamos la consulta
            rs=pr.executeQuery();
            
            //evaluamos el resultado de la consulta
            if(rs.next()){
                //guardamos la cantidad de datos que hay en la base de datos
                i = Integer.parseInt(rs.getString("Cantidad"));
            }
            //retornamos el numero de datos mas uno
            return i+1;
        }catch(Exception ex){
            //en caso de error
            ex.printStackTrace();
        }finally{
            try{
                //cerramos todo
                rs.close();
                pr.close();
                conn.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return 0;
    }
    
    public boolean ComprobarCalificacionRegistrada(String codigoEvento, String codigoUsuario, String tipo)
    {
        //incializamos los valores necesarios
        int numerocodigo = this.CantidadRegistroSatisfaccion();
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs = null;
        
        //creamos el string para la consulta
        String consulta = "Select Codigo From tb_satisfaccion Where  Id_Evento = ? AND Id_Usuario = ?";
        String consultauno = "Select Count(Codigo) Cantidad From tb_satisfaccion Where "+tipo+ " IS NULL AND Id_Evento = ? AND Id_Usuario = ?";
        
        try
        {
            //preparemos la consulta para la ejecucion
            pr = conn.prepareStatement(consulta);
            
            //se envian los parametros necesarios
            pr.setString(1,codigoEvento);
            pr.setString(2,codigoUsuario);
            
            //ejecutamos la consulta
            rs = pr.executeQuery();
            
            //evaluamos el contenido de la consulta
            if(rs.next())
            {
               //preparemos la consulta para la ejecucion
                pr = conn.prepareStatement(consultauno);

                //se envian los parametros necesarios
                pr.setString(1,codigoEvento);
                pr.setString(2,codigoUsuario);
                
                //ejecutamos la consulta
                rs = pr.executeQuery();

                //evaluamos el contenido de la consulta
                if(rs.next())
                {
                    if(rs.getString("Cantidad").equals("1"))
                    {
                        return true;
                    }
                    else 
                    {
                        return false;
                    }
                }
            }
            else
            {
                return true;
            }
            
            
        }catch(Exception ex){
            //en caso de error
            ex.printStackTrace();
        }finally{
            try{
                //cerramos todo
                rs.close();
                pr.close();
                conn.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        return false;
    }
    
    public String[][] BuscarComentariosAleatorios(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="SELECT u.Nombre NombreUsuario, (Select Nombre From tb_evento Where Codigo = sa.Id_Evento) NombreEmpresa, sa.Comentario \n" +
                    "FROM  `tb_satisfaccion` sa \n"+
                    "JOIN tb_usuario u on u.Codigo = sa.Id_Usuario "+
                    "JOIN tb_evento e on e.Codigo = sa.Id_Evento "+
                    "Where e.Fecha >= ? AND sa.Comentario IS NOT NULL Order by Rand() LIMIT 0,5";
        
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
            String [][] Datos = new String[rows][3];
            rs.beforeFirst();
            rows = 0;
            while(rs.next()){
                Evento eve = new Evento();
                Datos[rows][0] = rs.getString("NombreUsuario");
                Datos[rows][1] = rs.getString("NombreEmpresa");
                Datos[rows][2] = rs.getString("Comentario");
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
    public String[][] BuscarComentariosEvento(String codigoEvento, int Limite){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="SELECT u.Nombre NombreUsuario, (Select Nombre From tb_evento Where Codigo = sa.Id_Evento) NombreEmpresa, sa.Comentario \n" +
                    "FROM  `tb_satisfaccion` sa \n"+
                    "JOIN tb_usuario u on u.Codigo = sa.Id_Usuario "+
                    "Where sa.Comentario IS NOT NULL AND sa.Id_Evento = ? Order by sa.Fecha LIMIT 0,?";
        
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, codigoEvento);
            pr.setInt(2, Limite);
            rs=pr.executeQuery();
            
            int rows = 0;
            while(rs.next())
            {
                rows ++;
            }
            String [][] Datos = new String[rows][3];
            rs.beforeFirst();
            rows = 0;
            while(rs.next()){
                Datos[rows][0] = rs.getString("NombreUsuario");
                Datos[rows][1] = rs.getString("NombreEmpresa");
                Datos[rows][2] = rs.getString("Comentario");
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
    
    public int getCantidadComentariosEvento(String codigoEvento, int Limite){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="SELECT Count(Codigo) From tb_satisfaccion " +
                    "Where Comentario IS NOT NULL AND Id_Evento = ? Order by Fecha LIMIT 0,?";
        
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, codigoEvento);
            pr.setInt(2, Limite);
            
            rs=pr.executeQuery();
            
            int rows = 0;
            while(rs.next())
            {
                rows ++;
            }
            return rows;
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
