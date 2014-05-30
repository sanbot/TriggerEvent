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

    /*Creacion de las variables, instancia de las clases necesarias y metodos set y get*/
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
    String Departamento;

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }

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

    public Usuario() {
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

    /*Metodo para iniciar sesion*/
    public boolean getlogin(String correo, String contrasenia) {
        /*Se crean las varariables para la conexion, la sentenica preprarada y para recorrer los resultados*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Sentencia pra traer todos los datos del usuario que se logea*/
        String sql = "Select u.Codigo codigo, tu.Tipo Tipo, u.Tipo_Documento tipo_documento, u.No_Documento documento ,u.Nombre nombre, u.Telefono telefono,"
                + " u.No_Celular Celular, c.Nombre NombreCiudad , d.Nombre NombreDepartamento, u.Correo Correo,u.Direccion direccion, u.Estado Estado\n"
                + "From  tb_usuario u Join tb_tipo_usuario tu on u.Codigo_Tipo = tu.Codigo "
                + " Join tb_ciudad c on c.Codigo = u.Codigo_Ciudad "
                + " Join tb_departamento d on d.Codigo = c.Codigo_Departamento \n"
                + "Where u.Correo = ? AND u.Contrasenia = ?";
        try {
            /*Se prepara la sentencia y se le mandan los valores*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, correo);
            pr.setString(2, contrasenia);
            rs = pr.executeQuery();
            /*Se guardan los datos en la clase*/
            while (rs.next()) {

                this.setCodigo(rs.getString("Codigo"));
                this.setTipo(rs.getString("Tipo"));
                this.setTipo_Documento(rs.getString("tipo_documento"));
                this.setNo_Documento(rs.getString("documento"));
                this.setNombre(rs.getString("Nombre"));
                this.setTelefono(rs.getString("telefono"));
                this.setCelular(rs.getString("Celular"));
                this.setCiudad(rs.getString("NombreCiudad"));
                this.setDepartamento(rs.getString("NombreDepartamento"));
                this.setCorreo(correo);
                this.setDireccion(rs.getString("direccion"));
                this.setEstado(rs.getString("Estado"));

                /*Se evaluan la condicion del estado para ver si puede entrar al aplicativo*/
                if (this.getCorreo().equals(correo) && this.getEstado().equals("Aprobado")) {
                    return true;
                }
                /*Se guarda el mensaje en la clase y se retorna false*/
                if (this.getEstado().equals("Desaprobado")) {
                    this.setMensaje("Lo sentimos, no puede ingresar al aplicativo, su cuenta fue desaprobada.");
                    return false;
                } else if (this.getEstado().equals("Pendiente")) {
                    this.setMensaje("Lo sentimos, no puede ingresar al aplicativo, su cuenta aún está pendiente por aprobación.");
                    return false;
                }
            }
        } catch (Exception ex) {
            /*En caso de error se muestra el error*/
            ex.printStackTrace();
        } finally {
            /*Se cierra todo*/
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
        }
        /*Se envia un mensaje de error a la clase y se retorna false*/
        this.setMensaje("La dirección de correo electrónico o la contraseña que ha introducido no son correctas.");
        return false;
    }

    /*Metodo para guardar los datos del usuario, y mandar un mensaje 
     posteriormente al cambiar el estado de este*/
    public boolean getDatosParaEstado(String Codigo) {
        /*Se instancia la clase necesaria, las variables para
         la sentencia preparada y para recorrer los datos*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea la sentencia en un string*/
        String sql = "Select Tipo_Documento tipo_documento, No_Documento documento ,Nombre nombre, Telefono telefono,"
                + " No_Celular Celular, Correo Correo, Direccion direccion \n"
                + "From  tb_usuario \n"
                + "Where Codigo = ? ";
        try {
            /*Se prepara la sentencia y se le envian los valores necesarios*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, Codigo);
            rs = pr.executeQuery();
            /*Se guardan los datos en la clase y se retorna true*/
            if (rs.next()) {

                this.setTipo_Documento(rs.getString("tipo_documento"));
                this.setNo_Documento(rs.getString("documento"));
                this.setNombre(rs.getString("nombre"));
                this.setTelefono(rs.getString("telefono"));
                this.setCelular(rs.getString("Celular"));
                this.setCorreo(rs.getString("Correo"));
                this.setDireccion(rs.getString("direccion"));
                return true;
            }
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            ex.printStackTrace();
        } finally {
            /*Se cierra todo*/
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
        }
        /*Se retorna false en caso de error*/
        return false;
    }

    /*Metodo para obtener los datos del usuario que inicio sesion*/
    public String[] getDatosUsuario(String Codigo) {
        /*Se crean e instancian las clases y varaibles necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea la sentenica en un string*/
        String sql = "Select u.Codigo codigo, u.Codigo_Tipo CodigoTipo, tu.Tipo Tipo, u.Tipo_Documento tipo_documento, u.No_Documento documento ,u.Nombre nombre, u.Telefono telefono,"
                + " u.No_Celular Celular, u.codigo_ciudad CodCiudad, c.nombre NombreCiudad, d.Codigo CodigoDepartamento, d.Nombre NombreDepartamento, u.Correo Correo,u.Direccion direccion, u.Estado Estado\n"
                + "From  tb_usuario u"
                + " Join tb_tipo_usuario tu on u.Codigo_Tipo = tu.Codigo "
                + " Join tb_ciudad c on c.codigo = u.codigo_ciudad "
                + " Join tb_departamento d on c.Codigo_Departamento = d.Codigo \n"
                + "Where u.Codigo = ?";
        /*Se crea un array*/
        String[] Usuario = new String[15];
        try {
            /*Se prepara la sentenica, se le envian los valores necesarios y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, Codigo);
            rs = pr.executeQuery();
            /*Se almacenan los datos en el array*/
            while (rs.next()) {
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
                Usuario[12] = rs.getString("CodigoDepartamento");
                Usuario[13] = rs.getString("NombreDepartamento");
                Usuario[14] = rs.getString("CodCiudad");

            }
            /*Se retorna el array*/
            return Usuario;
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            ex.printStackTrace();
        } finally {
            /*Se cierra todo*/
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {
                ex.getMessage();
            }
        }
        /*Se retorna null en caso de error*/
        return null;
    }

    /*MEtodo para obtener los datos para posteriormente mandar un mensaje de recuperacion de la contraseña*/
    public boolean getrecordarContrasenia(String correo) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia en un string*/
        String sql = "Select Nombre Nombre, Contrasenia Contrasenia\n"
                + "From  tb_usuario\n"
                + "Where Correo = ?";
        try {
            /*Se prepara la sentenica, se le envian los datos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, correo);
            rs = pr.executeQuery();
            int i = 0;
            /*Se guardan los datos en la clase*/
            while (rs.next()) {
                this.setNombre(rs.getString("Nombre"));
                this.setContrasenia(rs.getString("Contrasenia"));
                i++;
            }
            /*Se retorna verdadero en caso de encontrar registros*/
            if (i > 0) {
                return true;
            }
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            ex.printStackTrace();
        } finally {
            /**
             * Se cierra todo
             */
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se retorna false en caso de no encontrar nada o en caso de error*/
        return false;
    }

    /*Metodo para actualizar los datos de un usuario*/
    public boolean actualizardatos(String Codigo, String Tipo_Documento, String No_Documento, String Nombre, String Telefono, String Celular, String Correo, String Direccion, String Ciudad) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea la sentencia en un string*/
        String sql = "UPDATE tb_usuario SET Tipo_Documento = ?, No_Documento = ?, Nombre = ?, Telefono = ?, No_Celular = ? , Codigo_Ciudad = ?  , Correo= ? , Direccion = ? ";
        sql += "WHERE Codigo=?";
        ResultSet rs = null;
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, Tipo_Documento);
            pr.setString(2, No_Documento);
            pr.setString(3, Nombre);
            pr.setString(4, Telefono);
            pr.setString(5, Celular);
            pr.setString(6, Ciudad);
            pr.setString(7, Correo);
            pr.setString(8, Direccion);
            pr.setString(9, Codigo);
            /*Se ejecuta la consulta*/
            int i = pr.executeUpdate();

            /*Se verifica si la consulta ejecuto correctamente*/
            if (i == 1) {
                /*Se traen los datos actualizados del usuario*/
                pr = conn.prepareStatement("Select c.Nombre NombreCiudad, d.Nombre NombreDepartamento "
                        + "From tb_ciudad  c "
                        + "Join tb_departamento d on d.Codigo = c.Codigo_Departamento "
                        + " Where c.Codigo = ?");
                pr.setString(1, Ciudad);
                rs = pr.executeQuery();
                /*Se guardan los datos en la case*/
                while (rs.next()) {
                    this.setCiudad(rs.getString("NombreCiudad"));
                    this.setDepartamento(rs.getString("NombreDepartamento"));
                }
                /*Se retorna verdadero si todo funciona*/
                return true;
            }
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            System.out.printf(ex.toString());
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se retorna false en caso de error*/
        return false;
    }

    /*Metodo para actualizar los datos de un usuario*/
    public boolean actualizardatos(String Codigo, String Telefono, String Direccion, String Ciudad) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea la sentencia en un string*/
        String sql = "UPDATE tb_usuario SET Telefono = ?, Codigo_Ciudad = ?, Direccion = ? ";
        sql += "WHERE Codigo=?";
        ResultSet rs = null;
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, Telefono);
            pr.setString(2, Ciudad);
            pr.setString(3, Direccion);
            pr.setString(4, Codigo);
            /*Se ejecuta la consulta*/
            int i = pr.executeUpdate();

            /*Se verifica si la consulta ejecuto correctamente*/
            if (i == 1) {
                /*Se retorna verdadero si todo funciona*/
                return true;
            }
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            System.out.printf(ex.toString());
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se retorna false en caso de error*/
        return false;
    }

    /*Metodo apra cambiar el estado del usaurio*/
    public boolean setCambiarEstadoUsaurio(String Codigo, String Estado) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea la sentencia sql en un string*/
        String sql = "UPDATE tb_usuario SET Estado = ? ";
        sql += "WHERE Codigo=?";
        try {
            /*Se prepara la sentenica, se le envian los datos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, Estado);
            pr.setString(2, Codigo);
            /*Si se ejecuta correctamente se retorna verdadero*/
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            /*Se muesta un mensaje en caso de error*/
            System.out.printf(ex.toString());
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*En caso de error se retorna falso*/
        return false;
    }

    /*Metodo apra generar un numero de codigo de verificacion aleatorio*/
    public String CodVer() {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        /*Se concatenan 4 numeros aleatorios de un calendario*/
        while (i < 4) {
            char c = (char) r.nextInt(255);
            if (c >= '0' && c <= '9') {
                cadenaAleatoria += c;
                i++;
            }
        }
        /*Se retorna el codigo de verificacion*/
        return cadenaAleatoria;
    }

    /*Se consulta la cantidad de usuarios que hay en el sistema*/
    public int CantidadRegistroUsuario() {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea la sentencia sql en un string*/
        String sql = "Select * "
                + "From  tb_usuario";
        try {
            /*Se prepara la sentencia y se ejecuta*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            /*Se cuentan todos los datos*/
            int i = 0;
            while (rs.next()) {

                i++;
            }
            //*Se retorna la cantidad de usuarios mas uno para el registro nuevo*/
            return i + 1;
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            ex.printStackTrace();
        } finally {
            /*Se cierra todo*/
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se retorna 0 en caso de error*/
        return 0;
    }

    /*MEtodo para obtener el tipo de usuario segun un codigo*/
    public String getEstadoUsuario(String Rol) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        String rol = "";
        /*Se crea la sentencia sql en un string*/
        String preconsulta = "Select Tipo From tb_tipo_usuario Where Codigo = ?";
        PreparedStatement pr = null;
        ResultSet rs = null;
        try {
            /*Se prepara la sentenica, se le envian los datos necesarios y se ejecuta posteriormetne*/
            pr = conn.prepareStatement(preconsulta);
            pr.setString(1, Rol);
            rs = pr.executeQuery();

            /*Se guarda el estado en una variable*/
            while (rs.next()) {
                if (!rs.getString("Tipo").equals("Empresa")) {
                    rol = "Aprobado";
                } else {
                    rol = "Pendiente";
                }
            }
        } catch (SQLException ex) {
            //*En caso de error se retorna nulo*/
            return null;
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se retorna el Rol*/
        return rol;
    }

    /*Metodo para registrar un usuario (administrador)*/
    public boolean ingresarUsuario(String Rol, String Tipo_Documento, String No_Documento, String Nombre, String Telefono, String Celular, String Correo, String Direccion, String Password, String Ciudad) {
        /*Se crean e instancia las clases y variables necesarias*/
        String Est = this.getEstadoUsuario(Rol);
        String Codi = "USU";
        int numerocodigo = this.CantidadRegistroUsuario();
        /*Se concatena el codigo para el nuevo registro*/
        Codi += numerocodigo;
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea la sentencia sql en un string*/
        String sql = "INSERT INTO tb_usuario (Codigo, Codigo_Tipo, Tipo_Documento, No_Documento, Nombre, Contrasenia, Telefono, No_Celular, Codigo_Ciudad, Correo, Direccion, Estado)";
        sql += "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {

            /*Se prepara la sentencia, se envian los datos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
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

            /*En caso de funcionar correctamente se retorna verdadero*/
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            /*Si hay un error se evalua el motivo*/
            if (ex.toString().indexOf("Duplicate") > 0) {
                if (ex.toString().indexOf("No_Documento") > 0) {
                    this.setMensaje("Ya existe una cuenta registrada con este número de documento.");
                }
                if (ex.toString().indexOf(Celular) > 0) {
                    this.setMensaje("Ya existe una cuenta registrada con este número de celular.");
                }
                if (ex.toString().indexOf(Correo) > 0) {
                    this.setMensaje("Ya existe una cuenta registrada con este correo electrónico.");
                }
            }

            /*Se retorna falso e caso de error*/
            return false;
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se guarda un mensaje y se retorna falso en caso de error*/
        this.setMensaje("Ocurrió un problema inesperado al registrar los datos del usuario. Estamos trabajando para solucionar este problema.");
        return false;
    }

    /*Metodo para buscar los datos de los usuarios pendientes*/
    public String[][] BuscarDatosUsuarioPendienteTodos() {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea un string con la sentencia sql*/
        String sql = "SELECT u.Codigo, tu.Tipo, u.Tipo_Documento, u.No_Documento, u.Nombre, u.Contrasenia, u.Telefono, u.No_Celular, c.Nombre Ciudad, u.Correo, u.Direccion, u.Estado\n"
                + "FROM  `tb_usuario` u\n"
                + "JOIN tb_tipo_usuario tu ON u.Codigo_Tipo = tu.Codigo "
                + "Join tb_ciudad c on c.codigo = u.codigo_ciudad Where u.Estado != 'Pendiente'";

        try {
            /*Se prepara la sentencia y se ejecuta posterioemente*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int rows = 0;
            /*Se cuentan los registros para crear un array posteriormente*/
            while (rs.next()) {
                rows++;
            }
            /*Se crea un array y se guardan los datos en este*/
            String[][] Datos = new String[rows][11];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
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
                Datos[rows][8] = usu.getCorreo();
                Datos[rows][9] = usu.getDireccion();
                Datos[rows][10] = usu.getEstado();
                rows++;
            }
            /*Se retorna el array*/
            return Datos;
        } catch (Exception ex) {
            /*En caso de error se muestra*/
            ex.printStackTrace();
        } finally {
            /*Se cierra todo*/
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*En caso de error se retorna nulo*/
        return null;
    }

    /*Metodo para buscar los datos de la emrpesa para crear un evento a su nombre el administrador*/
    public String[][] BuscarDatosEmpresa() {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en uns tring*/
        String sql = "SELECT u.No_Documento, u.Nombre \n"
                + "FROM  `tb_usuario` u\n"
                + "JOIN tb_tipo_usuario tu ON u.Codigo_Tipo = tu.Codigo And tu.Tipo = 'Empresa' "
                + "Where u.Estado = 'Aprobado'";

        try {
            /*Se prepara la sentencia sql y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int rows = 0;
            /*Se cuenta los resultado de la consulta para crear un array posteriormente*/
            while (rs.next()) {
                rows++;
            }
            /*Se crea un array y se guardan los resultados*/
            String[][] Datos = new String[rows][2];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setCodigo(rs.getString("No_Documento"));
                usu.setNombre(rs.getString("Nombre"));
                Datos[rows][0] = usu.getCodigo();
                Datos[rows][1] = usu.getNombre();
                rows++;
            }
            /*Se retorna el array*/
            return Datos;
        } catch (Exception ex) {
            /*Se muestra un emsnaje en caso de error*/
            ex.printStackTrace();
        } finally {
            /*Se cierra todo*/
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se retorna nulo en caso de error */
        return null;
    }

    /*Metodo para obtener los datos de los usuarios pendientes*/
    public String[][] BuscarDatosUsuarioPendientes() {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en uns tring*/
        String sql = "SELECT u.Codigo, tu.Tipo, u.Tipo_Documento, u.No_Documento, u.Nombre, u.Contrasenia, u.Telefono, u.No_Celular, c.Nombre Ciudad, u.Correo, u.Direccion, u.Estado\n"
                + "FROM  `tb_usuario` u\n"
                + "JOIN tb_tipo_usuario tu ON u.Codigo_Tipo = tu.Codigo "
                + " Join tb_ciudad c on c.codigo = u.codigo_ciudad"
                + " Where u.Estado = 'Pendiente'";

        try {
            /*Se prepara la sentencia y se ejecuta*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int rows = 0;
            /*Se cuentan los regsistros para hacer un array posteriormente*/
            while (rs.next()) {
                rows++;
            }
            /*Se crea un array y se guardan los datos en este*/
            String[][] Datos = new String[rows][11];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
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
                Datos[rows][8] = usu.getCorreo();
                Datos[rows][9] = usu.getDireccion();
                Datos[rows][10] = usu.getEstado();
                rows++;
            }
            /*Se retorna el array*/
            return Datos;
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            ex.printStackTrace();
        } finally {
            /*Se cierra todo*/
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se retorna nulo en caso de error*/
        return null;
    }

    /*Modificar los datos de un usuario*/
    public boolean actualizardatosUsuario(String Codigo, String Nombre, String Rol, String Tipo_Documento, String No_Documento, String Telefono, String celular, String correo, String Direccion, String Estado, String Ciudad) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea una sentencia sql en uns tring*/
        String sql = "UPDATE tb_usuario SET Codigo_Tipo = ? ,Tipo_Documento = ?, No_Documento = ?, Nombre = ?, Telefono = ?, No_Celular = ? , Codigo_Ciudad = ?, Correo= ? , Direccion = ?, Estado = ? ";
        sql += "WHERE Codigo= ? ";
        try {
            /*Se prepara la sentencia, se envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
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
            /*Si se ejecuta correctamente se retorna verdadero*/
            int i = pr.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            System.out.printf(ex.toString());
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se retorna falso en caso de error*/
        return false;
    }

    /*Metodo para obtner la candidad de usuarios pendientes*/
    public String getCantidadPendientes() {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en uns tring*/
        String sql = "Select Count(Codigo) Cantidad "
                + "From  tb_usuario Where Estado = 'Pendiente'";
        try {
            /*Se prepara la sentencia y se ejecuta*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            /*Si funciona correctamente se retorna verdaero*/
            if (rs.next()) {
                return rs.getString("Cantidad");
            }
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            ex.printStackTrace();
        } finally {
            /*Finalmente se cierra todo*/
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*En caso de error se retorna nulo*/
        return null;
    }

    /*MEtodo para verificar las contraseñas del modificar contraseña del usuario*/
    public boolean getConfirmarContrasenia(String codigo, String contra) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en uns tring*/
        String sql = "Select Contrasenia "
                + "From  tb_usuario Where Codigo = ?";
        try {
            /*Se prepara la sentencia, se envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs = pr.executeQuery();

            /*Si todo funciona correctamente se retorna verdadero*/
            if (rs.next()) {
                if (contra.equals(rs.getString("Contrasenia"))) {
                    return true;
                }
            }
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            ex.printStackTrace();
        } finally {
            /*Se cierra todo*/
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*En caso de error se retorna falso*/
        return false;
    }

    /*Metodo para cambiar la contraseña*/
    public boolean setCambioContrasenia(String codigo, String contra) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea una sentencia sql en uns string*/
        String sql = "UPDATE tb_usuario SET Contrasenia = ? "
                + "Where Codigo = ?";
        try {
            /*Se prepara la sentencia, se envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, contra);
            pr.setString(2, codigo);

            /*Si todo funciona correctamente se retorna un verdadero*/
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            /*Se guarda un mensaje en la clase en caso de error*/
            this.setMensaje(ex.toString());
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se retorna falaso en caso de error*/
        return false;
    }
}
