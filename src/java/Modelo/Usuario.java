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
    String Ciudad;

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

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
        String sql="Select u.Codigo codigo, tu.Tipo Tipo, u.Tipo_Documento tipo_documento, u.No_Documento documento ,u.Nombre nombre, u.Telefono telefono,"
                 + " u.No_Celular Celular, c.Nombre NombreCiudad , u.Correo Correo,u.Direccion direccion, u.Estado Estado\n" +
                    "From  tb_usuario u Join tb_tipo_usuario tu on u.Codigo_Tipo = tu.Codigo "
                  + " Join tb_ciudad c on c.Codigo = u.Codigo_Ciudad \n" +
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
                this.setCiudad(rs.getString("NombreCiudad"));
                this.setCorreo(correo);
                this.setDireccion(rs.getString("direccion"));
                this.setEstado(rs.getString("Estado"));
                
                if(this.getCorreo().equals(correo) && this.getEstado().equals("Aprobado")){
                    return true;
                }
                if(this.getEstado().equals("Desaprobado"))
                {
                    this.setMensaje("Lo sentimos, no hemos podido conectar tu cuenta al aplicativo, ya que está desaprobada.");
                    return false;
                }
                else if(this.getEstado().equals("Pendiente"))
                {
                    this.setMensaje("Lo sentimos, no hemos podido conectar tu cuenta al aplicativo, ya que está pendiente por aprobación.");
                    return false;
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
        this.setMensaje("El correo/contraseña es incorrecta. Inténtelo de nuevo.");
        return false;
    }
      
    public boolean getDatosParaEstado(String Codigo){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select Tipo_Documento tipo_documento, No_Documento documento ,Nombre nombre, Telefono telefono,"
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
                this.setCorreo(rs.getString("Correo"));
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
                 + " u.No_Celular Celular, u.codigo_ciudad CodCiudad, c.nombre NombreCiudad, c.Codigo_Departamento DepCiudad, u.Correo Correo,u.Direccion direccion, u.Estado Estado\n" +
                   "From  tb_usuario u"
                + " Join tb_tipo_usuario tu on u.Codigo_Tipo = tu.Codigo "
                + " Join tb_ciudad c on c.codigo = u.codigo_ciudad \n" +
                    "Where u.Codigo = ?";
        String[] Usuario = new String[14];
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
                    Usuario[11] = rs.getString("NombreCiudad");
                    Usuario[12] = rs.getString("DepCiudad");
                    Usuario[13] = rs.getString("CodCiudad");
                    
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
    
    public boolean getrecordarContrasenia(String correo){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select Nombre Nombre, Contrasenia Contrasenia\n" +
                    "From  tb_usuario\n" +
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
    
    public boolean actualizardatos(String Codigo, String Tipo_Documento, String No_Documento, String Nombre, String Telefono, String Celular, String Correo, String Direccion, String Ciudad){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        String sql="UPDATE tb_usuario SET Tipo_Documento = ?, No_Documento = ?, Nombre = ?, Telefono = ?, No_Celular = ? , Codigo_Ciudad = ?  , Correo= ? , Direccion = ? ";
                sql += "WHERE Codigo=?";
        ResultSet rs= null;
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, Tipo_Documento);
            pr.setString(2, No_Documento);
            pr.setString(3, Nombre);
            pr.setString(4, Telefono);
            pr.setString(5, Celular);
            pr.setString(6, Ciudad);
            pr.setString(7, Correo);
            pr.setString(8, Direccion);
            pr.setString(9, Codigo);
            int i = pr.executeUpdate();
            
            if(i==1){
                pr=conn.prepareStatement("Select Nombre From tb_ciudad Where Codigo = ?");
                pr.setString(1, Ciudad);
                rs=pr.executeQuery();

                while (rs.next())
                {
                    this.setCiudad(rs.getString("Nombre"));
                }
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
        String sql="UPDATE tb_usuario SET Estado = 'Desaprobado' ";
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
        String sql="UPDATE tb_usuario SET Estado = 'Aprobado' ";
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
                   "From  tb_usuario";
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
    public String getEstadoUsuario(String Rol)
    {
        Connection conn = conexion.conectar();
        String Est ="";
        String preconsulta = "Select Tipo From tb_tipo_usuario Where Codigo = ?";
        PreparedStatement pr=null;
        ResultSet rs = null;
        try{
            
            pr=conn.prepareStatement(preconsulta);
            pr.setString(1, Rol);
            rs=pr.executeQuery();
            
            while (rs.next())
            {
                if(!rs.getString("Tipo").equals("Empresa"))
                {
                    Est= "Aprobado";
                }
                else
                {
                    Est = "Pendiente";
                }
            }
        }catch(SQLException ex){
            return null;
        }
        finally{
            try{
                pr.close();
                conn.close();
            }catch(Exception ex){

            }
        }
        return Est;
    }
    public boolean ingresarUsuario(String Rol, String Tipo_Documento, String No_Documento, String Nombre, String Telefono, String Celular, String Correo, String Direccion, String Password, String Ciudad){
        Connection conn = conexion.conectar();
        String Est = this.getEstadoUsuario(Rol);
        String Codi = "USU";
        int numerocodigo = this.CantidadRegistroUsuario();
        Codi+=numerocodigo;
        PreparedStatement pr=null;
        String sql="INSERT INTO tb_usuario (Codigo, Codigo_Tipo, Tipo_Documento, No_Documento, Nombre,Contrasenia, Telefono, No_Celular, Codigo_Ciudad, Correo, Direccion, Estado)";
        sql+="VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pr.setString(9, Ciudad);
            pr.setString(10, Correo);
            pr.setString(11, Direccion);
            pr.setString(12, Est);
            
            
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
        String sql="SELECT u.Codigo, tu.Tipo, u.Tipo_Documento, u.No_Documento, u.Nombre, u.Contrasenia, u.Telefono, u.No_Celular, c.Nombre Ciudad, u.Correo, u.Direccion, u.Estado\n" +
                    "FROM  `tb_usuario` u\n" +
                    "JOIN tb_tipo_usuario tu ON u.Codigo_Tipo = tu.Codigo "
                + "Join tb_ciudad c on c.codigo = u.codigo_ciudad";
        
        try{
            pr=conn.prepareStatement(sql);
            rs=pr.executeQuery();
            
            int rows = 0;
            while(rs.next())
            {
                rows ++;
            }
            String [][] Datos = new String[rows][11];
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
                usu.setCiudad(rs.getString("Ciudad"));
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
                Datos[rows][7] = usu.getCiudad();
                Datos[rows][8] = usu.getCelular();
                Datos[rows][9] = usu.getCorreo();
                Datos[rows][10] = usu.getEstado();
               
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
    
    public String[][] BuscarDatosEmpresa(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="SELECT u.Codigo, u.Nombre \n" +
                    "FROM  `tb_usuario` u\n" +
                    "JOIN tb_tipo_usuario tu ON u.Codigo_Tipo = tu.Codigo And tu.Tipo = 'Empresa' " +
                    "Where u.Estado = 'Aprobado'";
        
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
                Usuario usu = new Usuario();
                usu.setCodigo(rs.getString("Codigo"));
                usu.setNombre(rs.getString("Nombre"));
                Datos[rows][0] = usu.getCodigo();
                Datos[rows][1] = usu.getNombre();
               
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
    
    public String[][] BuscarDatosUsuarioPendientes(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="SELECT u.Codigo, tu.Tipo, u.Tipo_Documento, u.No_Documento, u.Nombre, u.Contrasenia, u.Telefono, u.No_Celular, c.Nombre Ciudad, u.Correo, u.Direccion, u.Estado\n" +
                    "FROM  `tb_usuario` u\n" +
                    "JOIN tb_tipo_usuario tu ON u.Codigo_Tipo = tu.Codigo "
                 + " Join tb_ciudad c on c.codigo = u.codigo_ciudad"
                 + " Where u.Estado = 'Pendiente'";
        
        try{
            pr=conn.prepareStatement(sql);
            rs=pr.executeQuery();
            
            int rows = 0;
            while(rs.next())
            {
                rows ++;
            }
            String [][] Datos = new String[rows][11];
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
                usu.setCiudad(rs.getString("Ciudad"));
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
                Datos[rows][7] = usu.getCiudad();
                Datos[rows][8] = usu.getCelular();
                Datos[rows][9] = usu.getCorreo();
                Datos[rows][10] = usu.getEstado();
               
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

    public boolean actualizardatosUsuario(String Codigo, String Nombre, String Rol, String Tipo_Documento, String No_Documento, String Telefono, String celular, String correo, String Direccion, String Estado, String Ciudad) {
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        String sql="UPDATE tb_usuario SET Codigo_Tipo = ? ,Tipo_Documento = ?, No_Documento = ?, Nombre = ?, Telefono = ?, No_Celular = ? , Codigo_Ciudad = ?, Correo= ? , Direccion = ?, Estado = ? ";
                sql += "WHERE Codigo=?";
        try{
            pr=conn.prepareStatement(sql);
            pr.setString(1, Rol);
            pr.setString(2, Tipo_Documento);
            pr.setString(3, No_Documento);
            pr.setString(4, Nombre);
            pr.setString(5, Telefono);
            pr.setString(6, celular);
            pr.setString(7, Ciudad);
            pr.setString(8, correo);
            pr.setString(9, Direccion);
            pr.setString(10, Estado);
            pr.setString(11, Codigo);
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
    
    public String getCantidadPendientes(){
        Connection conn = conexion.conectar();
        PreparedStatement pr=null;
        ResultSet rs=null;
        String sql="Select Count(Codigo) Cantidad "+
                   "From  tb_usuario Where Estado = 'Pendiente'";
        try{
            pr=conn.prepareStatement(sql);
            rs=pr.executeQuery();
            
            if(rs.next())
            {
                return rs.getString("Cantidad");
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
        return null;
    }
}
