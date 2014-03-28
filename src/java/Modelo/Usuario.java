/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author santi
 */
public class Usuario {

    
    String Celular;
    String Correo;
    String Tipo;
    String Estado;
    String Nombre;
    String Codigo;
    String Tipo_Documento;
    String No_Documento;
    String Telefono;
    String Direccion;
    String Contrasenia;
    String Mensaje;

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }
    
    cone conexion = new cone();
    
    public Usuario(){
        Connection conn = conexion.conectar();
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getTipo_Documento() {
        return Tipo_Documento;
    }

    public void setTipo_Documento(String Tipo_Documento) {
        this.Tipo_Documento = Tipo_Documento;
    }

    public String getNo_Documento() {
        return No_Documento;
    }

    public void setNo_Documento(String No_Documento) {
        this.No_Documento = No_Documento;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }
    
    public boolean getlogin(String correo, String contrasenia){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select u.Codigo codigo, tu.Tipo Tipo, u.Tipo_Documento tipo_documento, u.No_Documento documento ,Nombre nombre, Telefono telefono,"
                 + " u.No_Celular Celular, u.Correo Correo,u.Direccion direccion, u.Estado Estado\n" +
                    "From  tb_usuario u Join tb_tipo_usuario tu on u.Codigo_Tipo = tu.Codigo \n" +
                    "Where u.Correo = ? AND u.Contrasenia = ?";
        try{
            
            pr=conn.prepareStatement(sql);
            pr.setString(1, correo);
            pr.setString(2, contrasenia);
            rs=pr.executeQuery();
            while(rs.next()){
                
                this.setCodigo(rs.getString("Codigo"));
                this.setTipo(rs.getString("Tipo"));
                this.setTipo_Documento(rs.getString("tipo_documento"));
                this.setNo_Documento(rs.getString("documento"));
                this.setNombre(rs.getString("Nombre"));
                this.setTelefono(rs.getString("telefono"));
                this.setCelular(rs.getString("Celular"));
                this.setCorreo(correo);
                this.setDireccion(rs.getString("direccion"));
                this.setEstado(rs.getString("Estado"));
                
                if(this.getCorreo().equals(correo) && this.getEstado().equals("Aprobado")){
                    return true;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                rs.close();
                pr.close();
                conn.close();
            }catch(Exception ex){
                    ex.getMessage();
            }
        }
        return false;
    }
      
    public boolean getDatosParaEstado(String Codigo){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Tipo_Documento tipo_documento, No_Documento documento ,Nombre nombre, Telefono telefono,"
                 + " No_Celular Celular, Correo Correo, Direccion direccion \n" +
                    "From  tb_usuario \n" +
                    "Where Codigo = ? ";
        try{
            
            pr=conn.prepareStatement(sql);
            pr.setString(1, Codigo);
            rs=pr.executeQuery();
            while(rs.next()){
                
                this.setTipo_Documento(rs.getString("tipo_documento"));
                this.setNo_Documento(rs.getString("documento"));
                this.setNombre(rs.getString("nombre"));
                this.setTelefono(rs.getString("telefono"));
                this.setCelular(rs.getString("Celular"));
                this.setCorreo("Correo");
                this.setDireccion(rs.getString("direccion"));
                
                
                return true;
               
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                rs.close();
                pr.close();
                conn.close();
            }catch(Exception ex){
                    ex.getMessage();
            }
        }
        return false;
    }
    
    public String[] getDatosUsuario(String Codigo){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select u.Codigo codigo, u.Codigo_Tipo CodigoTipo, tu.Tipo Tipo, u.Tipo_Documento tipo_documento, u.No_Documento documento ,u.Nombre nombre, u.Telefono telefono,"
                 + " u.No_Celular Celular, u.Correo Correo,u.Direccion direccion, u.Estado Estado\n" +
                   "From  tb_usuario u"
                + " Join tb_tipo_usuario tu on u.Codigo_Tipo = tu.Codigo \n" +
                    "Where u.Codigo = ?";
        String[] Usuario = new String[11];
        try{
            
            pr=conn.prepareStatement(sql);
            pr.setString(1, Codigo);
            rs=pr.executeQuery();
            while(rs.next()){
                    Usuario[0] = rs.getString("Codigo");
                    Usuario[1] = rs.getString("CodigoTipo");
                    Usuario[2] = rs.getString("Tipo");
                    Usuario[3] = rs.getString("Tipo_Documento");
                    Usuario[4] = rs.getString("Documento");
                    Usuario[5] = rs.getString("Nombre");
                    Usuario[6] = rs.getString("Telefono");
                    Usuario[7] = rs.getString("Celular");
                    Usuario[8] = rs.getString("Correo");
                    Usuario[9] = rs.getString("Direccion");
                    Usuario[10] = rs.getString("Estado");
                    
            }
            return Usuario;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try{
                rs.close();
                pr.close();
                conn.close();
            }catch(Exception ex){
                    ex.getMessage();
            }
        }
        return null;
    }
    
    public boolean getrecordarnumero(String correo){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select Nombre Nombre, Contrasenia Contrasenia\n" +
                    "From  TB_USUARIO\n" +
                    "Where Correo = ?";
        try{
            
            pr=conn.prepareStatement(sql);
            pr.setString(1, correo);
            rs=pr.executeQuery();
            int i=0;
            while(rs.next()){
                this.setNombre(rs.getString("Nombre"));
                this.setContrasenia(rs.getString("Contrasenia"));
                i++;
            }
            if(i>0){
                return true;
            }
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
        return false;
    }
    
    public boolean actualizardatos(String Codigo, String Tipo_Documento, String No_Documento, String Nombre, String Telefono, String Celular, String Correo, String Direccion){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        String sql="UPDATE TB_USUARIO SET Tipo_Documento = ?, No_Documento = ?, Nombre = ?, Telefono = ?, No_Celular = ? , Correo= ? , Direccion = ? ";
                sql += "WHERE Codigo=?";
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, Tipo_Documento);
            pr.setString(2, No_Documento);
            pr.setString(3, Nombre);
            pr.setString(4, Telefono);
            pr.setString(5, Celular);
            pr.setString(6, Correo);
            pr.setString(7, Direccion);
            pr.setString(8, Codigo);
            if(pr.executeUpdate()==1){
                return true;
            }
        }catch(Exception ex){
            System.out.printf(ex.toString());
        }finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        return false;
    }
    
    public boolean setdesaprobarUsaurio(String Codigo){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        String sql="UPDATE TB_USUARIO SET Estado = 'Desaprobado' ";
                sql += "WHERE Codigo=?";
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, Codigo);
            if(pr.executeUpdate()==1){
                return true;
            }
        }catch(Exception ex){
            System.out.printf(ex.toString());
        }finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        return false;
    }
    
    public boolean setaprobarUsaurio(String Codigo){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        String sql="UPDATE TB_USUARIO SET Estado = 'Aprobado' ";
                sql += "WHERE Codigo=?";
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, Codigo);
            if(pr.executeUpdate()==1){
                return true;
            }
        }catch(Exception ex){
            System.out.printf(ex.toString());
        }finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        return false;
    }
    
    public String CodVer(){
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while ( i < 4){
            char c = (char)r.nextInt(255);
            if (c >= '0' && c <='9' ){
                cadenaAleatoria += c;
                i ++;
            }
        }
        return cadenaAleatoria;
    }
    
    public int CantidadRegistroUsuario(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select * "+
                   "From  TB_USUARIO";
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
    
    public boolean ingresarUsuario(String Rol, String Tipo_Documento, String No_Documento, String Nombre, String Telefono, String Celular, String Correo, String Direccion, String Password){
        Connection conn = conexion.conectar();
        String Est = "Aprobado";
        String Codi = "USU";
        int numerocodigo = this.CantidadRegistroUsuario();
        Codi+=numerocodigo;
        PreparedStatement pr=null;
        String sql="INSERT INTO TB_USUARIO (Codigo, Codigo_Tipo, Tipo_Documento, No_Documento, Nombre,Contrasenia, Telefono, No_Celular, Correo, Direccion, Estado)";
        sql+="VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try{
            
            pr=conn.prepareStatement(sql);
            pr.setString(1, Codi);
            pr.setString(2, Rol);
            pr.setString(3, Tipo_Documento);
            pr.setString(4, No_Documento);
            pr.setString(5, Nombre);
            pr.setString(6, Password);
            pr.setString(7, Telefono);
            pr.setString(8, Celular);
            pr.setString(9, Correo);
            pr.setString(10, Direccion);
            pr.setString(11, Est);
            
            
            if(pr.executeUpdate()==1){
                return true;
            }
        }catch(SQLException ex){
            if(ex.toString().indexOf("Duplicate")>0)
            {
                if(ex.toString().indexOf("No_Documento")>0)
                {
                    this.setMensaje("Ya existe una cuenta registrada con este número de documento.");
                }
                if(ex.toString().indexOf(Celular)>0)
                {
                    this.setMensaje("Ya existe una cuenta registrada con este celular.");
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
        this.setMensaje("Ocurrió un problema inesperado al tratar de insertar los datos del usuario, por favor, inténtelo de nuevo.");
        return false;
    }
    public String[][] BuscarDatosUsuarioTodos(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="SELECT u.Codigo, tu.Tipo, u.Tipo_Documento, u.No_Documento, u.Nombre, u.Contrasenia, u.Telefono, u.No_Celular, u.Correo, u.Direccion, u.Estado\n" +
                    "FROM  `tb_usuario` u\n" +
                    "JOIN tb_tipo_usuario tu ON u.Codigo_Tipo = tu.Codigo\n" +
                    "LIMIT 0 , 30";
        
        try{
            pr=conn.prepareStatement(sql);
            rs=pr.executeQuery();
            
            int rows = 0;
            while(rs.next())
            {
                rows ++;
            }
            String [][] Datos = new String[rows][10];
            rs.beforeFirst();
            rows = 0;
            while(rs.next()){
                Usuario usu = new Usuario();
                usu.setCodigo(rs.getString("Codigo"));
                usu.setTipo(rs.getString("Tipo"));
                usu.setTipo_Documento(rs.getString("Tipo_Documento"));
                usu.setNo_Documento(rs.getString("No_Documento"));
                usu.setNombre(rs.getString("Nombre"));
                usu.setTelefono(rs.getString("Telefono"));
                usu.setCelular(rs.getString("No_Celular"));
                usu.setCorreo(rs.getString("Correo"));
                usu.setDireccion(rs.getString("Direccion"));
                usu.setEstado(rs.getString("Estado"));
                Datos[rows][0] = usu.getCodigo();
                Datos[rows][1] = usu.getTipo();
                Datos[rows][2] = usu.getTipo_Documento();
                Datos[rows][3] = usu.getNo_Documento();
                Datos[rows][4] = usu.getNombre();
                Datos[rows][5] = usu.getTelefono();
                Datos[rows][6] = usu.getCelular();
                Datos[rows][7] = usu.getCelular();
                Datos[rows][8] = usu.getCorreo();
                Datos[rows][9] = usu.getEstado();
                
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

    public boolean actualizardatosUsuario(String Codigo, String Nombre, String Rol, String Tipo_Documento, String No_Documento, String Telefono, String celular, String correo, String Direccion, String Estado) {
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        String sql="UPDATE TB_USUARIO SET Codigo_Tipo = ? ,Tipo_Documento = ?, No_Documento = ?, Nombre = ?, Telefono = ?, No_Celular = ? , Correo= ? , Direccion = ?, Estado = ? ";
                sql += "WHERE Codigo=?";
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, Rol);
            pr.setString(2, Tipo_Documento);
            pr.setString(3, No_Documento);
            pr.setString(4, Nombre);
            pr.setString(5, Telefono);
            pr.setString(6, celular);
            pr.setString(7, correo);
            pr.setString(8, Direccion);
            pr.setString(9, Estado);
            pr.setString(10, Codigo);
            if(pr.executeUpdate()==1){
                return true;
            }
        }catch(Exception ex){
            System.out.printf(ex.toString());
        }finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        return false;
    }
}
