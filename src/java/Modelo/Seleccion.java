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
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

/**
 *
 * @author santiago
 */
public class Seleccion {

    /*Se crean las variables necesarias con metodos set y get y se instancia la clase de conexion*/
    String Codigo;
    String Mensaje;
    String Nombre;
    String Tipo;
    String Imagen;
    String Estado;
    String TypeImg;

    public String getTypeImg() {
        return TypeImg;
    }

    public void setTypeImg(String TypeImg) {
        this.TypeImg = TypeImg;
    }
   

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
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

    public Seleccion() {
        Connection conn = conexion.conectar();
    }

    /*Metodo para registrar un gusto o ambiente*/
    public boolean setRegistrarSeleccion(String nombre, String tipo, String imagen) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        FileInputStream is = null;
        String estado = "Aprobado";
        String codigo = "SEL";
        int numerocodigo = this.CantidadRegistroSeleccion();
        /*Se concatena el codigo para el nuevo registro*/
        codigo += numerocodigo;
        this.setCodigo(codigo);
        pr = null;
        /*Se crea una sentencia sql en un string*/
        String sql = "INSERT INTO tb_seleccion (Codigo, Imagen, Nombre, Tipo, Estado)";
        sql += "VALUES(?,?,?,?, ?)";
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            pr.setString(2, imagen);
            pr.setString(3, nombre);
            pr.setString(4, tipo);
            pr.setString(5, estado);

            /*Si todo funciona correctamente se retorna verdadero*/
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            /*En caso de error se evalua la causa*/
            if (ex.toString().indexOf("Duplicate") > 0) {
                if (ex.toString().indexOf("Nombre") > 0) {
                    this.setMensaje("Ya hay un " + tipo + " con este nombre.");
                    return false;
                }
            }
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se guarda el mensaje de error en la clase y se retorna falso*/
        this.setMensaje("Ocurrió un problema inesperado al registrar el " + tipo + ". Estamos trabajando para solucionar este problema.");
        return false;
    }

    /*Metodo para contar la cantidad de registros*/
    public int CantidadRegistroSeleccion() {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en un string*/
        String sql = "Select Count(Codigo) Cantidad "
                + "From  tb_seleccion";
        try {
            /*Se prepara la sentencia y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int i = 0;
            /*Se evalua si hay registros*/
            if (rs.next()) {
                /*Se captura el resultado del query*/
                i = Integer.parseInt(rs.getString("Cantidad"));
            }
            /*Se retorna el valor de la consulta*/
            return i + 1;
        } catch (Exception ex) {
            /*Si hay error se muestra el error*/
            ex.printStackTrace();
        } finally {
            try {
                /*Se cierra todo*/
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*En caso de error se retorna falso*/
        return 0;
    }

    //con este metodo se sabe cuantas personas usan el gusto o ambiente para poder eliminarlo
    public boolean CantidadUsoAmbienteGusto(String codigoSeleccion) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en un string, esta sentencia es para los usuarios*/
        String sqluno = "Select Count(Codigo) Cantidad "
                + "From  tb_seleccion_usuario "
                + "Where Estado = 'Activo'"
                + " AND Id_Seleccion = ? ";
        /*Se crea una sentencia sql en un string, esta sentencia es para los eventos*/
        String sqldos = "Select Count(se.Codigo) Cantidad "
                + "From  tb_seleccion_evento se "
                + "Join tb_evento e on se.Id_Evento = e.Codigo "
                + "Where se.Estado = 'Activo' "
                + " AND se.Id_Seleccion = ? AND e.Fecha > CURDATE()";
        try {
            /*Se prepara la sentenica, se le envian los datos y se le envia la consulta*/
            pr = conn.prepareStatement(sqluno);
            pr.setString(1, codigoSeleccion);
            rs = pr.executeQuery();

            /*Se evalua si hay resultados*/
            int uno = 0;
            if (rs.next()) {
                /*Se captura el resultado de la consulta*/
                uno = Integer.parseInt(rs.getString("Cantidad"));
            }
            /*Se prepara la segunda sentenica, se le envian los datos y se ejecuta la segunda consulta*/
            pr = conn.prepareStatement(sqldos);
            pr.setString(1, codigoSeleccion);
            rs = pr.executeQuery();

            /*Se evalua si hay resultados*/
            int dos = 0;
            if (rs.next()) {
                /*Se captura el resultado de la consulta*/
                dos = Integer.parseInt(rs.getString("Cantidad"));
            }
            /*Se determina si no hay usuarios o eventos que usen el gusto o ambiente y se retorna verdadero
             en este caso*/
            if (uno == 0 && dos == 0) {
                return true;
            }
            /*En caso contrario se retorna falso*/
            return false;
        } catch (Exception ex) {
            /*Se muestra un error en caso de tenerlo*/
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
        /*Se retorna falso*/
        return false;
    }

    /*Metodo para obtener los datos de la seleccion*/
    public String[][] getDatosSeleccion() {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea un strin para la sentencia*/
        String sql = "Select Codigo, Nombre, Tipo, Estado, Imagen "
                + "From  tb_seleccion";
        /*Se crea un array para guardar los datos*/
        String[][] Datos = null;
        try {
            /*Se prepara la sentencia sql y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int i = 0;
            /*Se cuenta la cantidad de registros para el tamaño del array*/
            while (rs.next()) {
                i++;
            }
            Datos = new String[i][5];
            rs.beforeFirst();
            i = 0;
            /*Se guardan los datos en el array*/
            while (rs.next()) {
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                Datos[i][3] = rs.getString("Estado");
                Datos[i][4] = rs.getString("Imagen");
                i++;
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

    /*Metodo para retornar la imagen*/
    public Blob getImagenSeleccion(String codigo) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en un string*/
        String sql = "Select Imagen "
                + "From  tb_seleccion Where Codigo = ?";
        /*Se crea una variable tipo blob para la imagen*/
        Blob Datos = null;
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs = pr.executeQuery();
            /*Se guarda la imagen en el blob*/
            while (rs.next()) {
                Datos = rs.getBlob("Imagen");
            }
            /*Se retorna la imagen*/
            return Datos;
        } catch (Exception ex) {
            /*En caso de error se muestra el mensaje*/
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

    /*Metodo para actualizar el gusto o ambiente*/
    public boolean actualizardatosSeleccion(String codigo, String nombre, String tipo, String estado) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea una sentencia sql en uns string*/
        String sql = "UPDATE tb_seleccion SET Nombre = ?, Tipo = ?, Estado = ? ";
        sql += "WHERE Codigo = ?";
        try {
            /*Se prepara la sentenica, se le envian los datos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, nombre);
            pr.setString(2, tipo);
            pr.setString(3, estado);
            pr.setString(4, codigo);
            /*Si se ejecuta correctamente se retorna verdadero*/
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            /*En caso de error se evalua el motivo*/
            if (ex.toString().indexOf("Duplicate") > 0) {
                if (ex.toString().indexOf("Nombre") > 0) {
                    this.setMensaje("Ya hay un " + tipo + " con este nombre.");
                    return false;
                }
            }
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se guarda en la clase un mensaje de error y se retorna falso*/
        this.setMensaje("Ocurrió un problema inesperado al modificar el " + tipo + ".  Estamos trabajando para solucionar este problema.");
        return false;
    }

    /*Metodo para actualizar los datos de la seleccion incluyendo imagen*/
    public boolean actualizardatosSeleccion(String codigo, String nombre, String tipo, String imagen, String estado) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        FileInputStream is = null;
        /*Se crea la sentencia sql en un string*/
        String sql = "UPDATE tb_seleccion SET Nombre = ?, Tipo = ?, Imagen = ?, Estado = ?";
        sql += "WHERE Codigo = ?";
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, nombre);
            pr.setString(2, tipo);
            pr.setString(3, imagen);
            pr.setString(4, estado);
            pr.setString(5, codigo);
            /*Si la sentencia se ejecuta correctamente se retorna verdadero*/
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            /*Si hay error se evalua el motivo y se guarda el mensaje*/
            if (ex.toString().indexOf("Duplicate") > 0) {
                if (ex.toString().indexOf("Nombre") > 0) {
                    this.setMensaje("Ya hay un " + tipo + " con este nombre.");
                    return false;
                }
            }
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se guarda un mensaje de error y se retorna falso*/
        this.setMensaje("Ocurrió un problema inesperado al  modificar el " + tipo + ".  Estamos trabajando para solucionar este problema.");
        return false;
    }

    /*Metodo para obtener todos los gustos que no esten asociados a un usuario*/
    public String[][] getGustosNuevos(String codigo) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en un string*/
        String sql = "Select Codigo, Nombre, Tipo, Imagen "
                + "From  tb_seleccion "
                + "Where Codigo NOT IN (select Id_Seleccion From tb_seleccion_usuario Where Id_Usuario = ? And Estado = 'Activo')"
                + "AND Estado = 'Aprobado'";
        /*Se crea un array para guardar los datos*/
        String[][] Datos = null;
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecuta posteriormetne*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs = pr.executeQuery();

            /**
             * Se cuantan los registros y se le da tamaño al array
             */
            int i = 0;
            while (rs.next()) {
                i++;
            }
            Datos = new String[i][4];
            rs.beforeFirst();
            i = 0;
            /*Se guardan los datos en el array*/
            while (rs.next()) {
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                Datos[i][3] = rs.getString("Imagen");
                i++;
            }
            /*Se retorna los datos*/
            return Datos;
        } catch (Exception ex) {
            /*En caso de error se muestra el mensaje*/
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

    /*Metodo para obtener la clasificacion que no este asociada a un evento*/
    public String[][] getClasificacionNuevos(String codigo) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en un string*/
        String sql = "Select Codigo, Nombre, Tipo, Imagen "
                + "From  tb_seleccion "
                + "Where Codigo NOT IN (select Id_Seleccion From tb_seleccion_evento Where Id_Evento = ? And Estado = 'Activo')"
                + "AND Estado = 'Aprobado'";
        /*Se crea un array para guardar los datos*/
        String[][] Datos = null;
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs = pr.executeQuery();

            /*Se cuenta los registros que se retornan en la consulta para darle un tamaño al array*/
            int i = 0;
            while (rs.next()) {
                i++;
            }
            Datos = new String[i][4];
            rs.beforeFirst();
            i = 0;
            /*Se guardan los datos en el array y se retorna*/
            while (rs.next()) {
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                Datos[i][3] = rs.getString("Imagen");
                i++;
            }
            return Datos;
        } catch (Exception ex) {
            /*En caso de error se muestra el mensaje*/
            ex.printStackTrace();
        } finally {
            /*Se cierra finalmente*/
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

    /*Metodo para obtener los gustos del usuario*/
    public String[][] getMisGustos(String codigo) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea la sentencia sql en un string*/
        String sql = "Select Codigo, Nombre, Tipo, Imagen "
                + "From  tb_seleccion "
                + "Where Codigo IN (select Id_Seleccion From tb_seleccion_usuario Where Id_Usuario = ?"
                + " And Estado = 'Activo')";
        /*Se crea un array para guardar los datos*/
        String[][] Datos = null;
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecua posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs = pr.executeQuery();

            /*Se cuenta la cantidad de registros*/
            int i = 0;
            while (rs.next()) {
                i++;
            }
            /*Se le da un tamaño a el array y se guarda los datos*/
            Datos = new String[i][4];
            rs.beforeFirst();
            i = 0;
            while (rs.next()) {
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                Datos[i][3] =rs.getString("Imagen");
                i++;
            }
            /*Se retorna los datos*/
            return Datos;
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
        /*Se retorna nulo en caso de error*/
        return null;
    }

    /*Metodo para obtener la clasificacion de un evento*/
    public String[][] getClasificacionEvento(String codigoEvento) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en uns string*/
        String sql = "Select Codigo, Nombre, Tipo, Imagen "
                + "From  tb_seleccion "
                + "Where Codigo IN (select Id_Seleccion From tb_seleccion_evento Where Id_Evento = ?"
                + " And Estado = 'Activo')";
        /*Se crea un array para guardar los datos*/
        String[][] Datos = null;
        try {
            /*Se prepara la sentenica, se le envian los datos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoEvento);
            rs = pr.executeQuery();

            int i = 0;
            /*Se cuenta la cantidad de registros*/
            while (rs.next()) {
                i++;
            }
            /*Se da un tamaño a el array y se guardan los datos*/
            Datos = new String[i][4];
            rs.beforeFirst();
            i = 0;
            while (rs.next()) {
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                Datos[i][3] = rs.getString("Imagen");
                i++;
            }
            /*Se retornan los datos*/
            return Datos;
        } catch (Exception ex) {
            /*En caso de error se muestra un mensaje*/
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

    /*Metodo apra agregar un gusto*/
    public boolean AddGusto(String codigoSeleccion, String CodigoUsuario) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String codigo = "GUS";
        /*Se concatena el codigo para el nuevo registro*/
        codigo += this.CantidadGustos();
        /*Se crea una serie de sentencias sql en un string*/
        /*Sentencia para traer un registro si existe*/
        String sqlc = "Select Codigo From tb_seleccion_usuario Where Id_Seleccion = ? and Id_Usuario = ? ";
        /*Sentencia para registrar el gusto si no existe*/
        String sqli = "Insert Into tb_seleccion_usuario (Codigo, Id_Seleccion, Id_Usuario, Estado)"
                + " Values (?,?,?,?)";
        /*Sentencia para modificar el gusto si existe*/
        String sqlu = "UPDATE tb_seleccion_usuario SET Estado = 'Activo' Where Codigo = ?";

        try {
            /*Se prepara la sentencia de consulta, se le envian los datos y posteriormente se ejecuta*/
            pr = conn.prepareStatement(sqlc);
            pr.setString(1, codigoSeleccion);
            pr.setString(2, CodigoUsuario);
            rs = pr.executeQuery();
            /*Se evalua si la consulta tiena algun registro*/
            if (rs.next()) {
                /*Se prepara la sentenica para modificar el registro exitente y se ejecuta posteriormente*/
                pr = conn.prepareStatement(sqlu);
                pr.setString(1, rs.getString("Codigo"));
                if (pr.executeUpdate() == 1) {
                    return true;
                }
            } else {
                /*Se prepara la sentencia de registro en caso de no existir el registro*/
                pr = conn.prepareStatement(sqli);
                pr.setString(1, codigo);
                pr.setString(2, codigoSeleccion);
                pr.setString(3, CodigoUsuario);
                pr.setString(4, "Activo");
                /*Se ejecuta la sentencia y se retorna verdadero en caso de funcionar correctamente*/
                if (pr.executeUpdate() == 1) {
                    return true;
                }
            }

        } catch (Exception ex) {
            /*En caso de error se evalua el porque, se guarda en una variable y se retorna falso*/
            if (ex.toString().indexOf("Duplicate") > 0) {
                if (ex.toString().indexOf("UK_Id_Seleccion_Id_Usuario") > 0) {
                    this.setMensaje("Ya tiene este gusto agregado. No puede volverlo a agregar.");
                    return false;
                }
            }
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se guarda un mensaje en la clase en caso de error y se retorna falso*/
        this.setMensaje("Ocurrió un problema inesperado al agregar el gusto a su cuenta.  Estamos trabajando para solucionar este problema.");
        return false;
    }

    /*Metodo apra remover un gusto*/
    public boolean RemoveGusto(String codigoSeleccion, String CodigoUsuario) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una serie de sentencias sql en un string*/
        /*Sentenia para consultar el codigo del gusto*/
        String sqlc = "Select Codigo From tb_seleccion_usuario Where Id_Seleccion = ? and Id_Usuario = ? ";
        /*Sentencia para modificar el gusto*/
        String sqlu = "UPDATE tb_seleccion_usuario SET Estado = 'Inactivo' Where Codigo = ?";

        try {
            /*Se prepara la consulta, se le envian los dato y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sqlc);
            pr.setString(1, codigoSeleccion);
            pr.setString(2, CodigoUsuario);
            rs = pr.executeQuery();
            /*Se evalua si se trajo algun registro*/
            if (rs.next()) {
                /*Se prepara la sentencia de modificar, se le envian los datos y se retorna verdadero
                 encaso de funcionar correctamente*/
                pr = conn.prepareStatement(sqlu);
                pr.setString(1, rs.getString("Codigo"));
                if (pr.executeUpdate() == 1) {
                    return true;
                }
            }

        } catch (Exception ex) {
            /*En caso de error se muestra el error*/
            System.out.print(ex.toString());
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se guarda el mensaje en la clase y se retorna falso*/
        this.setMensaje("Ocurrió un problema inesperado al remover el gusto de su cuenta.  Estamos trabajando para solucionar este problema.");
        return false;
    }

    /*Metodo para contar la cantidad de gustos*/
    public int CantidadGustos() {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea la sentencia sql en un string*/
        String sql = "Select * "
                + "From  tb_seleccion_usuario";
        try {
            /*Se prepara la sentencia y se ejecuta*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();
            /*Se cuentan los registros de la consulta*/
            int i = 0;
            while (rs.next()) {
                i++;
            }
            /*Se retorna la cantidad de registros*/
            return i + 1;
        } catch (Exception ex) {
            /*En caso de error se muestra el mensaje*/
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
        return 0;
    }

    /*MEtodo para contar la cantidad de gustos para verificar si al menos tiene uno antes de removerlo*/
    public boolean CantidadGustosAmbientesPreRemove(String codigoSeleccion, String codigoUsuario) {
        /*Se crean e instancian las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una serie de sentencias sql en un string*/
        /*Sentenica para contar los gustos o ambientes seleccionados*/
        String sql = "SELECT Count(su.Codigo) Cantidad FROM `tb_seleccion_usuario` su "
                + "JOIN tb_seleccion s on s.Codigo = su.Id_Seleccion "
                + "AND s.Tipo = (Select Tipo From tb_seleccion Where Codigo = ?) "
                + "WHERE Id_Usuario = ? AND su.Estado = 'Activo'";
        /*Sentencia para consultar el tipo de seleccion(gusto o ambiente)*/
        String consulta = "Select Tipo From tb_seleccion Where Codigo = ?";
        try {
            /*Se prepara la sentencia, se le envian los dtos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoSeleccion);
            pr.setString(2, codigoUsuario);
            rs = pr.executeQuery();

            /*Se crea un int para saber la cantidad de registros*/
            int i = 0;
            if (rs.next()) {
                i = Integer.parseInt(rs.getString("Cantidad"));
            }
            /*Se evalua si contiene mas de un resultado para poder ser eliminado*/
            if (i > 1) {
                return true;
            } else {
                /*Si solo hay un registro se muestra un mensaje de error*/
                pr = conn.prepareStatement(consulta);
                pr.setString(1, codigoSeleccion);
                rs = pr.executeQuery();
                if (rs.next()) {
                    this.setMensaje("No se puede remover este " + rs.getString("Tipo") + ", porque es el único que tiene registrado.");
                } else {
                    this.setMensaje("Ocurrió un error inesperado al remover el " + rs.getString("Tipo") + " de su cuenta.  Estamos trabajando para solucionar este problema.");
                }
                return false;
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
        /*Se retorna flaso en caso de error*/
        return false;
    }

    /*Metodo para desaprobar un gusto o ambiente*/
    public boolean DesaprobarSeleccion(String codigoSeleccion) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea una sentencia sql en uns string*/
        String sql = "UPDATE tb_seleccion SET Estado = 'Desaprobado' ";
        sql += "WHERE Codigo = ?";
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecuta posterioremente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoSeleccion);
            /*Si se ejecuta correctamente se retorna verdadero*/
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            /*Se guarda el mensaje en la calse en caso de error*/
            this.setMensaje(ex.getMessage().toString());
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se guarda el mensaje en la calse en caso de error y se retorna falso*/
        this.setMensaje("Ocurrió un problema inesperado al desactivar este gusto o ambiente.");
        return false;
    }

    /*MEtodo para aprobar la seleccion*/
    public boolean AprobarSeleccion(String codigoSeleccion) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea una sentencia sql en uns string*/
        String sql = "UPDATE tb_seleccion SET Estado = 'Aprobado' ";
        sql += "WHERE Codigo = ?";
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoSeleccion);
            /*Si se ejecuta correctamente se retorna verdadero*/
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            /*En caso de error se guarda el mensaje en la clase*/
            this.setMensaje(ex.getMessage().toString());
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se guarda un mensaje de error en la clase y se retorna falso*/
        this.setMensaje("Ocurrió un problema inesperado al activar este gusto o ambiente.");
        return false;
    }

    /*MEtodo para agregar una clasificacion a un evento*/
    public boolean AddClasificacionEvento(String codigoSeleccion, String codigoEvento) {
        /*Se concatena el codigo para el nuevo registro*/
        String codigo = "CLA" + this.CantidadRegistroEventoSeleccion();
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String Est = "Activo";

        /*Se crea una serie de sentencias sql en uns string*/
        /*Sentencia para registrar*/
        String sqli = "Insert into tb_seleccion_evento (Codigo, Id_Seleccion , Id_Evento , Estado) ";
        sqli += "VALUES (?,?,?,?)";
        /*Sentencia para consultar si ya existe el registro*/
        String sqlc = "Select Codigo From tb_seleccion_evento Where Id_Seleccion = ? and Id_Evento = ? ";
        /*Sentencia para modificar el registro*/
        String sqlu = "UPDATE tb_seleccion_evento SET Estado = 'Activo' Where Codigo = ?";
        try {
            /*Se prepara la sentencia, se envian los datos y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sqlc);
            pr.setString(1, codigoSeleccion);
            pr.setString(2, codigoEvento);
            rs = pr.executeQuery();
            /*Se evalua si hay registros*/
            if (rs.next()) {
                /*En caso de tener un registro se modifica*/
                /*Se prepara la sentencia para modificar, se le envian los datos y se ejecuta*/
                pr = conn.prepareStatement(sqlu);
                pr.setString(1, rs.getString("Codigo"));
                /*Se retorna verdadero en caso de funcianr correctamente*/
                if (pr.executeUpdate() == 1) {
                    return true;
                }
            } else {
                /*En caso de no tener registros se registra*/
                /*Se prepara la sentenica para registrar, se le envian los datos y se ejecuta*/
                pr = conn.prepareStatement(sqli);
                pr.setString(1, codigo);
                pr.setString(2, codigoSeleccion);
                pr.setString(3, codigoEvento);
                pr.setString(4, Est);
                /*Se retorna verdadero en caso de funcianr correctamente*/
                if (pr.executeUpdate() == 1) {
                    return true;
                }
            }
        } catch (Exception ex) {
            /*En caso de error se evalua el motivo*/
            if (ex.toString().indexOf("Duplicate") > 0) {
                if (ex.toString().indexOf("UK_IdSeleccion_IdEvento_SeleccionEvento") > 0) {
                    this.setMensaje("Este gusto o ambiente ya se encuentra agregado a la clasificacion de este evento.");
                    return false;
                }
            }
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se guarda el mensaje en la clase y se retorna falso*/
        this.setMensaje("Ocurrió un problema inesperado al agregar este gusto o ambiente a la clasificación del evento.  Estamos trabajando para solucionar este problema.");
        return false;
    }

    /*MEtodo para contar la cantidad de registros en la clasificacion del evento*/
    public int CantidadRegistroEventoSeleccion() {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en uns string*/
        String sql = "Select Count(Codigo) Cantidad "
                + "From  tb_seleccion_evento";
        try {
            /*Se prepara la sentencia y se ejecuta*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            /*Se guardan los registros en el int*/
            int i = 0;
            if (rs.next()) {
                i = Integer.parseInt(rs.getString("Cantidad"));
            }
            /*Se retorna los registros*/
            return i + 2;
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

    /*MEtodo apra comprar si se completo el registro de evento*/
    public boolean ComprobarRegistroCompletoUSuario(String codigoevento) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una serie de sentencias sql en uns string*/
        /*Sentencia para obtener el estado del evento*/
        String consultaevento = "Select Estado"
                + " From tb_evento"
                + " Where Codigo = ?";
        /*Sentencia para obtener la cantidad de gustos*/
        String sqla = "Select Count(se.Codigo) Cantidad "
                + "From  tb_seleccion_evento se "
                + "JOIN tb_seleccion s on  se.Id_Seleccion = s.Codigo "
                + "WHERE se.Id_Evento = ? "
                + "AND s.Tipo = 'Gusto' "
                + "AND se.Estado = 'Activo'";
        /*Sentencia para obtener la cantidad de ambientes*/
        String sqlb = "Select Count(se.Codigo) Cantidad "
                + "From  tb_seleccion_evento se "
                + "JOIN tb_seleccion s on  se.Id_Seleccion = s.Codigo "
                + "WHERE se.Id_Evento = ? "
                + "AND s.Tipo = 'Ambiente' "
                + "AND se.Estado = 'Activo'";
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecuta*/
            pr = conn.prepareStatement(consultaevento);
            pr.setString(1, codigoevento);
            rs = pr.executeQuery();
            /*Se evalua si se trae resultados en la consulta*/
            if (rs.next()) {
                /*Se evalua si el estado ya no esta pendiente*/
                if (!rs.getString("Estado").equals("Pendiente")) {
                    /*Se prepara la primer sentencia, se le envian los datos y se ejecuta*/
                    pr = conn.prepareStatement(sqla);
                    pr.setString(1, codigoevento);
                    rs = pr.executeQuery();

                    int uno = 0;
                    /*Se guardan los datos en este entero*/
                    if (rs.next()) {
                        uno = Integer.parseInt(rs.getString("Cantidad"));
                    }
                    /*Se prepara la segunda sentencia, se le envian los datos y se ejecuta*/
                    pr = conn.prepareStatement(sqlb);
                    pr.setString(1, codigoevento);
                    rs = pr.executeQuery();

                    /*Se guanda los datos en este entero*/
                    int dos = 0;
                    if (rs.next()) {
                        dos = Integer.parseInt(rs.getString("Cantidad"));
                    }
                    /*Se evalua si se tiene seleccionado minimo un ambiente y un gusto para completar el registro*/
                    if (uno >= 1 && dos >= 1) {
                        /*Se retorna verdaero en caso de estar compelto*/
                        return true;
                    }
                } else {
                    /*En caso de estar pendiente se retorna falso*/
                    return false;
                }
            }
        } catch (Exception ex) {
            /*En caso de error se muestra un mensaje*/
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
        /*Se retorna falso en caso de error*/
        return false;
    }

    /*Metodo para obtener todos los gustos que no esten asociados a un usuario*/
    public String[][] getGustosNuevosAndroid(String codigo, int cantidad) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en un string*/
        String sql = "Select Codigo, Nombre, Tipo, to_base64(Imagen) Imagen "
                + "From  tb_seleccion "
                + "Where Codigo NOT IN ("
                + "select Id_Seleccion "
                + "From tb_seleccion_usuario "
                + "Where Id_Usuario = ? And Estado = 'Activo'"
                + ") "
                + "AND Estado = 'Aprobado' "
                + "limit 0, ?";
        /*Se crea un array para guardar los datos*/
        String[][] Datos = null;
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecuta posteriormetne*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            pr.setInt(2, cantidad);
            rs = pr.executeQuery();

            /**
             * Se cuantan los registros y se le da tamaño al array
             */
            int i = 0;
            while (rs.next()) {
                i++;
            }
            Datos = new String[i][4];
            rs.beforeFirst();
            i = 0;
            /*Se guardan los datos en el array*/
            while (rs.next()) {
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                Datos[i][3] = rs.getString("Imagen");
                i++;
            }
            /*Se retorna los datos*/
            return Datos;
        } catch (Exception ex) {
            /*En caso de error se muestra el mensaje*/
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

    /*Metodo para obtener los gustos del usuario*/
    public String[][] getMisGustosAndroid(String codigo, int cantidad) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        BASE64Encoder encoder = new BASE64Encoder();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea la sentencia sql en un string*/
        String sql = "Select Codigo, Nombre, Tipo, Imagen "
                + "From  tb_seleccion "
                + "Where Codigo IN ("
                + "select Id_Seleccion "
                + "From tb_seleccion_usuario "
                + "Where Id_Usuario = ? "
                + "And Estado = 'Activo') "
                + "Limit 0, ?";
        /*Se crea un array para guardar los datos*/
        String[][] Datos = null;
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecua posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            pr.setInt(2, cantidad);
            rs = pr.executeQuery();

            /*Se cuenta la cantidad de registros*/
            int i = 0;
            while (rs.next()) {
                i++;
            }
            /*Se le da un tamaño a el array y se guarda los datos*/
            Datos = new String[i][4];
            rs.beforeFirst();
            i = 0;
            while (rs.next()) {
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                //Datos[i][3] = rs.getString("Imagen");
                Blob imagen = rs.getBlob("Imagen");
                byte[] imgData = imagen.getBytes(1, (int) imagen.length());
                
                 Datos[i][3] = encoder.encode(imgData);
                i++;
            }
            /*Se retorna los datos*/
            return Datos;
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
        /*Se retorna nulo en caso de error*/
        return null;
    }
    /*Metodo para contar la cantidad de gustos*/

    public int CantidadGustosNuevosAndroid(String codigo) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea la sentencia sql en un string*/
        String sql = "Select COUNT(Codigo) cantidad "
                + "From  tb_seleccion "
                + "Where Codigo NOT IN ("
                + "select Id_Seleccion "
                + "From tb_seleccion_usuario "
                + "Where Id_Usuario = ?"
                + " And Estado = 'Activo') "
                + "AND Estado = 'Aprobado'";
        try {
            /*Se prepara la sentencia y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs = pr.executeQuery();
            /*Se cuentan los registros de la consulta*/
            int i = 0;
            if (rs.next()) {
                i = rs.getInt("cantidad");
            }
            /*Se retorna la cantidad de registros*/
            return i;
        } catch (Exception ex) {
            /*En caso de error se muestra el mensaje*/
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
        return 0;
    }
    /*Metodo para contar la cantidad de gustos*/

    public int CantidadGustosAndroid(String codigo) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea la sentencia sql en un string*/
        String sql = "SELECT COUNT(*) cantidad "
                + "FROM tb_seleccion s "
                + "JOIN tb_seleccion_usuario su ON su.Id_Seleccion = s.Codigo "
                + "where su.Id_Usuario = ? "
                + "and su.Estado = 'Activo'";
        try {
            /*Se prepara la sentencia y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs = pr.executeQuery();
            /*Se cuentan los registros de la consulta*/
            int i = 0;
            if (rs.next()) {
                i = rs.getInt("cantidad");
            }
            /*Se retorna la cantidad de registros*/
            return i;
        } catch (Exception ex) {
            /*En caso de error se muestra el mensaje*/
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
        return 0;
    }
}
