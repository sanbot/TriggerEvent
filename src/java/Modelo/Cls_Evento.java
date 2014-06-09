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
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADSI
 */
public class Cls_Evento {

    /*Se crean las variables con metodos set y get y se instancian las clases necesarias*/
    String Codigo;
    String Mensaje;
    String Nombre;
    String Fecha;
    String Descipcion;
    String Imagen;
    String Creador;
    String Rango;
    Date FechaDate;
    Cls_Conexion conexion = new Cls_Conexion();
    String Direccion;
    String Ciudad;
    String Estado;
    String Motivo;
    String Latitud;
    String Longitud;
    String Typeimg;
    String RangoMaximo;

    public String getRangoMaximo() {
        return RangoMaximo;
    }

    public void setRangoMaximo(String RangoMaximo) {
        this.RangoMaximo = RangoMaximo;
    }

    public String getTypeimg() {
        return Typeimg;
    }

    public void setTypeimg(String Typeimg) {
        this.Typeimg = Typeimg;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String Latitud) {
        this.Latitud = Latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String Longitud) {
        this.Longitud = Longitud;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

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

    /*Metodo para convertir la fecha de string a date*/
    public boolean ConvertirFecha(String FechaDate) {
        try {
            /*Se le da el formato a la fecha*/
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            /*Se crea la variable para la nueva fecha*/
            Date nuevaFecha;
            /*Se gurda la nueva fecha con el formato*/
            nuevaFecha = formatoDeFecha.parse(FechaDate);
            this.setFechaDate(nuevaFecha);
            /*Se retorna verdadero*/
            return true;
        } catch (ParseException ex) {
            /*En caso de error se muestra*/
            Logger.getLogger(Cls_Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /*Metodo para validar dos dias antes del inicio del evento*/
    public boolean ValidarDosDiasFecha(Date FechaInicial) {
        /**
         * Se fecha una fecha para traer la fecha del sistema
         */
        Date FechaSistema = new Date();
        /*Se establece un calendario para guardar las fechas*/
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar.setTime(FechaSistema); // Configuramos la fecha que se recibe
        calendar1.setTime(FechaInicial);
        /*Se le agregan dos dias a la fecha*/
        calendar.add(Calendar.DAY_OF_YEAR, 2);

        /*Se evelua los dos dias y encaso de ser verdadero se retorna true*/
        if (calendar1.getTime().after(calendar.getTime())) {
            return true;
        }
        return false;
    }

    /*Metodo para validar que no se puede cancelar un evento a un día de realización*/
    public boolean validar_Cancelar_Evento_Un_Dia(String codigo) {

        Date FechaSistema = new Date();

        PreparedStatement pr = null;
        ResultSet rs = null;
        Connection conn = conexion.conectar();

        String sql = "Select Fecha From tb_evento Where Codigo = ? ";
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);

            rs = pr.executeQuery();
            if (rs.next()) {
                Date fechainicio = rs.getDate("Fecha");

                Calendar cal = Calendar.getInstance();
                Calendar calendar = Calendar.getInstance();

                calendar.setTime(FechaSistema);
                cal.setTime(fechainicio);

                calendar.add(Calendar.DAY_OF_YEAR, 1);

                /*Se evelua los dos dias y encaso de ser verdadero se retorna true*/
                if (cal.getTime().after(calendar.getTime())) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            /*En caso de error se evalua el porque y se retorna falso*/
            if (ex.toString().indexOf("uk_nombre_codigociudad_fecha") > 0) {
                this.setMensaje("No se puede registrar este evento porque existe otro con el mismo nombre, la misma fecha y en la misma ciudad");
            }
            return false;
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return false;
    }

    /*MEtodo para registrar un evento*/
    public boolean setRegistrarEvento(String imagen, String nombre, Date fecha, String descripcion, String rango, String creador, String ciudad, String direccion, String latitud, String longitud) {
        /*Se crean variables necesarias*/
        String Est = "Incompleto";
        PreparedStatement pr = null;
        String codigo = "EVE";
        /*se concatena el codigo para nuevos registros*/
        int numerocodigo = this.CantidadRegistroEvento();
        codigo += numerocodigo;
        Connection conn = conexion.conectar();
        this.setCodigo(codigo);
        pr = null;
        /*Se crea una sentencia sql en string*/
        String sql = "INSERT INTO tb_evento(Codigo, Imagen, Nombre, Fecha, Descripcion, Rango_Precios, Id_Usuario, Codigo_Ciudad, Direccion, Estado, Latitud, Longitud)";
        sql += "VALUES(?,?,?,?,?,?,?,?,?,?, ?,?)";

        try {
            /*Se crea la zona de tiempo, se prepara la sentencia, se le envian los datos y se ejecuta posteriormente*/
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            pr.setString(2, imagen);
            pr.setString(3, nombre);
            pr.setTimestamp(4, sqlDate);
            pr.setString(5, descripcion);
            pr.setString(6, rango);
            pr.setString(7, creador);
            pr.setString(8, ciudad);
            pr.setString(9, direccion);
            pr.setString(10, Est);
            pr.setString(11, latitud);
            pr.setString(12, longitud);

            /*Si se ejecuta correctamente se retorna true*/
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            /*En caso de error se evalua el porque y se retorna falso*/
            if (ex.toString().indexOf("uk_nombre_codigociudad_fecha") > 0) {
                this.setMensaje("No se puede registrar este evento porque existe otro con el mismo nombre, la misma fecha y en la misma ciudad");
            }
            return false;
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se guarda el mensaje de error en la clase y se retorna falso*/
        this.setMensaje("Ocurrió un problema inesperado al registrar el evento.  Estamos trabajando para solucionar este problema.");
        return false;
    }

    /*Metodo para contar la cantidad de registros*/
    public int CantidadRegistroEvento() {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "Select Count(Codigo) Cantidad "
                + "From  tb_evento";
        try {
            /*Se prepra la sentenica y se ejecuta posteriormente*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            /*Se guarda la cantidad retornada por la consulta en un int*/
            int i = 0;
            if (rs.next()) {
                i = rs.getInt("Cantidad");
            }
            /*Se retorna el entero +1 para el nuevo registro (Se llama este metodo la mayoria de ocaciones
             para un nuevo registro)*/
            return i + 1;
        } catch (Exception ex) {
            /*Se muestra un mensaje de error*/
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
        /*Se retorna 0 en caso de error*/
        return 0;
    }

    /*Metodo para contar la cantidad de eventos pendientes*/
    public int CantidadEventoPendiente() {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "Select Count(Codigo) Cantidad "
                + "From  tb_evento"
                + " Where Estado = 'Pendiente' AND Fecha > CURDATE()";
        try {
            /*Se prepara la sentencia y se ejecuta*/
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            /*Se guarda el valor en un estero*/
            int i = 0;
            if (rs.next()) {
                i = Integer.parseInt(rs.getString("Cantidad"));
            }
            /*Se retorna el valor*/
            return i;
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

    /*Metodo para buscar los datos principales de los evento*/
    public String[][] BuscarDatosPrincipalesEventos() {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, "
                + "(Select Count(Comentario) From tb_satisfaccion Where Id_Evento = e.Codigo) Comentarios, "
                + "Round((Select Avg(Calificacion) From tb_satisfaccion Where Id_Evento = e.Codigo),2) Calificacion \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.Codigo = e.Id_Usuario \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where e.Estado = 'Aprobado' AND "
                + "Fecha >= ? ORDER BY Fecha";

        try {
            /*Se crea una variable date para la fecha*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la sentencia, se le envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();

            /*Se ceuntan los registros que tiene la sentencia*/
            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            /*Se crea un array y se guardan los datos*/
            String[][] Datos = new String[rows][8];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Cls_Evento eve = new Cls_Evento();
                eve.setCodigo(rs.getString("Codigo"));
                eve.setNombre(rs.getString("Nombre"));
                eve.setFecha(rs.getString("Fecha"));
                eve.setCreador(rs.getString("NombreEmpresa"));
                eve.setCiudad(rs.getString("NombreCiudad"));
                Datos[rows][0] = eve.getCodigo();
                Datos[rows][1] = eve.getNombre();
                Datos[rows][2] = rs.getDate("Fecha").toString();
                Datos[rows][3] = eve.getCreador();
                Datos[rows][4] = eve.getCiudad();
                Datos[rows][5] = rs.getTime("Fecha").toString();
                Datos[rows][6] = rs.getString("Calificacion");
                Datos[rows][7] = rs.getString("Comentarios");
                rows++;
            }
            /*Se retornan los datos*/
            return Datos;
        } catch (Exception ex) {
            /*En caso de error se muestra el mensaje*/
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
        /*Se retorna nulo en caso de error */
        return null;
    }

    /*Metodo para buscar los principales datos de los eventos con limite*/
    public String[][] BuscarDatosPrincipalesEventos(int Limite, int Cantidad) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, "
                + "(Select Count(Comentario) From tb_satisfaccion Where Id_Evento = e.Codigo) Comentarios, "
                + "Round((Select Avg(Calificacion) From tb_satisfaccion Where Id_Evento = e.Codigo),2) Calificacion, e.Imagen \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.Codigo = e.Id_Usuario \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where e.Estado = 'Aprobado' AND "
                + "Fecha >= ? ORDER BY Fecha LIMIT ?,?";

        try {
            /*Se crea una variable fecha para la fecha del sistema*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la sentencia, se le envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            pr.setInt(2, Limite);
            pr.setInt(3, Cantidad);
            rs = pr.executeQuery();

            int rows = 0;
            /*Se cuentan los registros para crear un array*/
            while (rs.next()) {
                rows++;
            }
            String[][] Datos = new String[rows][9];
            rs.beforeFirst();
            rows = 0;
            /*Despues de crear el array se guardan los datos*/
            while (rs.next()) {
                Cls_Evento eve = new Cls_Evento();
                eve.setCodigo(rs.getString("Codigo"));
                eve.setNombre(rs.getString("Nombre"));
                eve.setFecha(rs.getString("Fecha"));
                eve.setCreador(rs.getString("NombreEmpresa"));
                eve.setCiudad(rs.getString("NombreCiudad"));
                Datos[rows][0] = eve.getCodigo();
                Datos[rows][1] = eve.getNombre();
                Datos[rows][2] = rs.getDate("Fecha").toString();
                Datos[rows][3] = eve.getCreador();
                Datos[rows][4] = eve.getCiudad();
                Datos[rows][5] = rs.getTime("Fecha").toString();
                Datos[rows][6] = rs.getString("Calificacion");
                Datos[rows][7] = rs.getString("Comentarios");
                Datos[rows][8] = rs.getString("Imagen");
                rows++;
            }
            /*Se retorna el array*/
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

    /*Metodo para buscar los datos de los eventos cercanos a la fecha de hoy*/
    public String[][] BuscarDatosEventosProximos() {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, "
                + "(Select Count(Comentario) From tb_satisfaccion Where Id_Evento = e.Codigo) Comentarios, "
                + "Round((Select Avg(Calificacion) From tb_satisfaccion Where Id_Evento = e.Codigo),2) Calificacion, e.Imagen \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.Codigo = e.Id_Usuario \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where e.Estado = 'Aprobado' AND "
                + "Fecha >= ? Order by Fecha LIMIT 0,2";

        try {
            /*Se crea una variable para capturar la fecha de hoy*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la sentenica, se le envian datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();

            int rows = 0;
            String[][] Datos = null;
            while (rs.next()) {
                rows++;
            }
            Datos = new String[rows][9];
            rows = 0;
            rs.beforeFirst();
            /*Se guardan los datos*/
            while (rs.next()) {
                Cls_Evento eve = new Cls_Evento();
                eve.setCodigo(rs.getString("Codigo"));
                eve.setNombre(rs.getString("Nombre"));
                eve.setFecha(rs.getString("Fecha"));
                eve.setCreador(rs.getString("NombreEmpresa"));
                eve.setCiudad(rs.getString("NombreCiudad"));
                Datos[rows][0] = eve.getCodigo();
                Datos[rows][1] = eve.getNombre().toUpperCase();
                Datos[rows][2] = rs.getDate("Fecha").toString();
                Datos[rows][3] = eve.getCreador();
                Datos[rows][4] = eve.getCiudad();
                Datos[rows][5] = rs.getTime("Fecha").toString();
                Datos[rows][6] = rs.getString("Calificacion");
                Datos[rows][7] = rs.getString("Comentarios");
                Datos[rows][8] = rs.getString("Imagen");
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
        /*En caso de error se retorna nulo*/
        return null;
    }

    /*Metodo para buscar los datos mas destacados*/
    public String[][] BuscarDatosEventosDestacados() {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, "
                + "(Select Count(Comentario) From tb_satisfaccion Where Id_Evento = e.Codigo) Comentarios, "
                + "Round((Select Avg(Calificacion) From tb_satisfaccion Where Id_Evento = e.Codigo),2) Calificacion, e.Imagen \n"
                + "FROM  `tb_evento` e JOIN tb_usuario u on u.Codigo = e.Id_Usuario \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where e.Estado = 'Aprobado' AND Fecha >= ? \n"
                + "Order by Calificacion DESC Limit 0,2 ";

        try {
            /*Se crea una variable fecha para guardar la fecha del sistema*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la sentencia, se le envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();

            int rows = 0;
            /*Se crea un array para guardar los datos posteriormente*/
            String[][] Datos = null;
            while (rs.next()) {
                rows++;
            }
            Datos = new String[rows][9];
            rows = 0;
            rs.beforeFirst();
            while (rs.next()) {
                Cls_Evento eve = new Cls_Evento();
                eve.setCodigo(rs.getString("Codigo"));
                eve.setNombre(rs.getString("Nombre"));
                eve.setFecha(rs.getString("Fecha"));
                eve.setCreador(rs.getString("NombreEmpresa"));
                eve.setCiudad(rs.getString("NombreCiudad"));
                Datos[rows][0] = eve.getCodigo();
                Datos[rows][1] = eve.getNombre().toUpperCase();
                Datos[rows][2] = rs.getDate("Fecha").toString();
                Datos[rows][3] = eve.getCreador();
                Datos[rows][4] = eve.getCiudad();
                Datos[rows][5] = rs.getString("Calificacion");
                Datos[rows][6] = rs.getTime("Fecha").toString();
                Datos[rows][7] = rs.getString("Comentarios");
                Datos[rows][8] = rs.getString("Imagen");
                rows++;
            }
            /*Se retornan los datos del array*/
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
        /*En caso de error se retorna nulo*/
        return null;
    }

    /*MEtodo apra buscar los datos de los eventos mas comentados*/
    public String[][] BuscarDatosEventosComentado() {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, "
                + "(Select Count(Comentario) From tb_satisfaccion Where Id_Evento = e.Codigo) Comentarios, "
                + "Round((Select Avg(Calificacion) From tb_satisfaccion Where Id_Evento = e.Codigo),2) Calificacion, e.Imagen \n"
                + "FROM  `tb_evento` e JOIN tb_usuario u on u.Codigo = e.Id_Usuario \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where e.Estado = 'Aprobado' AND Fecha >= ? \n"
                + "Order by Comentarios DESC Limit 0,2 ";

        try {
            /**
             * Se crea una variable fecha para capturar la fecha del sistema
             */
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /**
             * Se prepara la sentenica, se envian los datos y se ejecuta
             */
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();

            int rows = 0;
            /*Se crea un array y se guardan los datos posteriormente*/
            String[][] Datos = null;
            while (rs.next()) {
                rows++;
            }
            Datos = new String[rows][9];
            rows = 0;
            rs.beforeFirst();
            while (rs.next()) {
                Cls_Evento eve = new Cls_Evento();
                eve.setCodigo(rs.getString("Codigo"));
                eve.setNombre(rs.getString("Nombre"));
                eve.setFecha(rs.getString("Fecha"));
                eve.setCreador(rs.getString("NombreEmpresa"));
                eve.setCiudad(rs.getString("NombreCiudad"));
                Datos[rows][0] = eve.getCodigo();
                Datos[rows][1] = eve.getNombre().toUpperCase();
                Datos[rows][2] = rs.getDate("Fecha").toString();
                Datos[rows][3] = eve.getCreador();
                Datos[rows][4] = eve.getCiudad();
                Datos[rows][5] = rs.getString("Comentarios");
                Datos[rows][6] = rs.getTime("Fecha").toString();
                Datos[rows][7] = rs.getString("Calificacion");
                Datos[rows][8] = rs.getString("Imagen");
                rows++;
            }
            /*Se retornan los datos*/
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
        /*Se retorna nulo*/
        return null;
    }

    /*Metodo para buscar todos los datos de un evento*/
    public String[] BuscarDatosDetalladosEventos(String codigoEvento) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "SELECT u.Nombre NombreEmpresa, e.Nombre, e.Rango_Precios, "
                + "c.Codigo_Departamento CodigoDepartamento, d.Nombre NombreDepartamento, "
                + "c.Codigo CodigoCiudad, c.Nombre NombreCiudad, e.Direccion, e.Fecha, e.Descripcion, "
                + "e.Latitud, e.Longitud, e.Imagen \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.Codigo = e.Id_Usuario \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "JOIN tb_departamento d on d.Codigo = c.Codigo_Departamento \n"
                + "Where Fecha >= ? AND e.Codigo = ?";

        try {
            /*Se crea una variable fecha para guardar la fecha del sistema*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la sentenica, se le envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            pr.setString(2, codigoEvento);
            rs = pr.executeQuery();

            /*Se crea un array para guardar los datos posteriormente*/
            String[] Datos = new String[14];
            rs.beforeFirst();

            while (rs.next()) {
                Datos[0] = rs.getString("NombreEmpresa");
                Datos[1] = rs.getString("Nombre");
                Datos[2] = rs.getString("Rango_Precios");
                Datos[3] = rs.getString("CodigoDepartamento");
                Datos[4] = rs.getString("NombreDepartamento");
                Datos[5] = rs.getString("CodigoCiudad");
                Datos[6] = rs.getString("NombreCiudad");
                Datos[7] = rs.getString("Direccion");
                Datos[8] = rs.getDate("Fecha").toString();
                Datos[9] = rs.getString("Descripcion");
                Datos[10] = rs.getTime("Fecha").toString();
                Datos[11] = rs.getString("Latitud");
                Datos[12] = rs.getString("Longitud");
                Datos[13] = rs.getString("Imagen");
            }
            /*Se guardan los datos en un array*/
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

    /*Metodo para buscar todos los datos de un evento*/
    public String[] BuscarDatosDetalleEventoAndroid(String codigoEvento) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "SELECT e.Imagen, e.Fecha, e.Nombre NombreEvento, u.Nombre NombreEmpresa, d.Nombre NombreDepartamento, "
                + "c.Nombre NombreCiudad, e.Direccion, e.Rango_Precios, e.Descripcion, "
                + "(SELECT Count(Calificacion) Cantidad FROM  `tb_satisfaccion` e Where Calificacion = '5' AND Id_Evento = ?) Cinco, "
                + "(SELECT Count(Calificacion) Cantidad FROM  `tb_satisfaccion` e Where Calificacion = '4' AND Id_Evento = ?) Cuatro, "
                + "(SELECT Count(Calificacion) Cantidad FROM  `tb_satisfaccion` e Where Calificacion = '3' AND Id_Evento = ?) Tres, "
                + "(SELECT Count(Calificacion) Cantidad FROM  `tb_satisfaccion` e Where Calificacion = '2' AND Id_Evento = ?) Dos, "
                + "(SELECT Count(Calificacion) Cantidad FROM  `tb_satisfaccion` e Where Calificacion = '1' AND Id_Evento = ?) Uno \n"
                + "FROM tb_evento e \n"
                + "JOIN tb_usuario u ON e.Id_Usuario = u.Codigo \n"
                + "JOIN tb_ciudad c ON e.Codigo_Ciudad = c.Codigo \n"
                + "JOIN tb_departamento d ON c.Codigo_Departamento = d.Codigo \n"
                + "WHERE e.Codigo = ?";

        try {
            /*Se prepara la sentenica, se le envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoEvento);
            pr.setString(2, codigoEvento);
            pr.setString(3, codigoEvento);
            pr.setString(4, codigoEvento);
            pr.setString(5, codigoEvento);
            pr.setString(6, codigoEvento);
            rs = pr.executeQuery();

            /*Se crea un array para guardar los datos posteriormente*/
            String[] Datos = new String[14];
            rs.beforeFirst();

            while (rs.next()) {
                Datos[0] = rs.getString("Imagen");
                Datos[1] = rs.getString("Fecha");
                Datos[2] = rs.getString("NombreEvento");
                Datos[3] = rs.getString("NombreEmpresa");
                Datos[4] = rs.getString("NombreDepartamento");
                Datos[5] = rs.getString("NombreCiudad");
                Datos[6] = rs.getString("Direccion");
                Datos[7] = rs.getString("Rango_Precios");
                Datos[8] = rs.getString("Descripcion");
                Datos[9] = rs.getString("Cinco");
                Datos[10] = rs.getString("Cuatro");
                Datos[11] = rs.getString("Tres");
                Datos[12] = rs.getString("Dos");
                Datos[13] = rs.getString("Uno");
            }
            /*Se guardan los datos en un array*/
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
//    también sirve para eventos incompletos
    /*Metodo para buscar todos los datos de los eventos pendientes e incompletos*/

    public String[] BuscarDatosDetalladosEventosPendiente(String codigoEvento) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "SELECT u.Nombre NombreEmpresa, e.Nombre, e.Rango_Precios, "
                + "c.Codigo_Departamento CodigoDepartamento, d.Nombre NombreDepartamento, "
                + "c.Codigo CodigoCiudad, c.Nombre NombreCiudad, e.Direccion, e.Fecha, e.Descripcion, "
                + "e.Motivo, e.Estado, e.Latitud, e.Longitud, e.Imagen \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.Codigo = e.Id_Usuario \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "JOIN tb_departamento d on d.Codigo = c.Codigo_Departamento \n"
                + "Where Fecha >= ? AND e.Codigo = ?";

        try {
            /*Se crea una variable fecha para guardar la fecha del sistema*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la setnencia, se le envian los datos y se ejecuta posteriormetne*/
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            pr.setString(2, codigoEvento);
            rs = pr.executeQuery();

            /*Se crea un array y se guardan los datos*/
            String[] Datos = new String[16];
            rs.beforeFirst();

            while (rs.next()) {
                Datos[0] = rs.getString("NombreEmpresa");
                Datos[1] = rs.getString("Nombre");
                Datos[2] = rs.getString("Rango_Precios");
                Datos[3] = rs.getString("CodigoDepartamento");
                Datos[4] = rs.getString("NombreDepartamento");
                Datos[5] = rs.getString("CodigoCiudad");
                Datos[6] = rs.getString("NombreCiudad");
                Datos[7] = rs.getString("Direccion");
                Datos[8] = rs.getDate("Fecha").toString();
                Datos[9] = rs.getString("Descripcion");
                Datos[10] = rs.getTime("Fecha").toString();
                Datos[11] = rs.getString("Motivo");
                Datos[12] = rs.getString("Estado");
                Datos[13] = rs.getString("Latitud");
                Datos[14] = rs.getString("Longitud");
                Datos[15] = rs.getString("Imagen");
            }
            /*Se retornan los datos*/
            return Datos;
        } catch (Exception ex) {
            /*Se muestra el error en caso de error*/
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

    /*Metodo para obtener los datos del evento para enviar un mensaje al cambio de estado*/
    public String[] BuscarEventoParaMensaje(String codigoEvento) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "SELECT u.Nombre NombreEmpresa, e.Nombre, e.Direccion, e.Fecha, u.Correo \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.Codigo = e.Id_Usuario \n"
                + "Where e.Codigo = ?";

        try {
            /*Se prepara la sentencia, se envia los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoEvento);
            rs = pr.executeQuery();

            /*Se crea un array y se guardan los datos*/
            String[] Datos = new String[5];
            rs.beforeFirst();

            while (rs.next()) {
                Datos[0] = rs.getString("NombreEmpresa");
                Datos[1] = rs.getString("Nombre");
                Datos[2] = rs.getString("Direccion");
                Datos[3] = rs.getString("Fecha");
                Datos[4] = rs.getString("Correo");
            }
            /**
             * Se guarda el array
             */
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
        /*En caso de error se retorna nulo*/
        return null;
    }

    /**
     * Metodo para buscar los datos de los eventos que ha creado la empresa
     */
    public String[][] BuscarDatosMisEventos(String Id_Usuario) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, e.Estado, e.Imagen \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.Codigo = e.Id_Usuario \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where Fecha >= ? AND Id_Usuario = ?";

        try {
            /*Se guarda la fecha en una variable tipo date*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /**
             * Se prepara la sentencia, se le envian los datos y se ejecuta
             */
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            pr.setString(2, Id_Usuario);
            rs = pr.executeQuery();

            /*Se cuenta los registros para luego crear un array*/
            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            /*Se crea un array para guardar los datos*/
            String[][] Datos = new String[rows][8];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Cls_Evento eve = new Cls_Evento();
                eve.setCodigo(rs.getString("Codigo"));
                eve.setNombre(rs.getString("Nombre"));
                eve.setCreador(rs.getString("NombreEmpresa"));
                eve.setCiudad(rs.getString("NombreCiudad"));
                eve.setEstado(rs.getString("Estado"));
                Datos[rows][0] = eve.getCodigo();
                Datos[rows][1] = eve.getNombre();
                Datos[rows][2] = rs.getDate("Fecha").toString();
                Datos[rows][3] = eve.getCreador();
                Datos[rows][4] = eve.getCiudad();
                Datos[rows][5] = eve.getEstado();
                Datos[rows][6] = rs.getTime("Fecha").toString();
                Datos[rows][7] = rs.getString("Imagen");
                rows++;
            }
            /**
             * Se retornan los datos
             */
            return Datos;
        } catch (Exception ex) {
            /**
             * Se muestra un mensaje en caso de error
             */
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
        /**
         * Se retorna nulo en caso de error
         */
        return null;
    }

    /**
     * Metodo para buscar todos los datos de un evento
     */
    public String[][] BuscarDatosTodosEventos() {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, "
                + "c.Nombre NombreCiudad, e.Estado, e.Imagen\n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.Codigo = e.Id_Usuario \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where Fecha >= ? Order by Fecha";

        try {
            /*Se guarda la fecha en la variable date*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la sentencia, se le envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();

            /*Se cuentan los resultados para crear el array*/
            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            /*Se crea el array y se guardan los datos*/
            String[][] Datos = new String[rows][8];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Cls_Evento eve = new Cls_Evento();
                eve.setCodigo(rs.getString("Codigo"));
                eve.setNombre(rs.getString("Nombre"));
                eve.setCreador(rs.getString("NombreEmpresa"));
                eve.setCiudad(rs.getString("NombreCiudad"));
                eve.setEstado(rs.getString("Estado"));
                Datos[rows][0] = eve.getCodigo();
                Datos[rows][1] = eve.getNombre();
                Datos[rows][2] = rs.getDate("Fecha").toString();
                Datos[rows][3] = eve.getCreador();
                Datos[rows][4] = eve.getCiudad();
                Datos[rows][5] = eve.getEstado();
                Datos[rows][6] = rs.getTime("Fecha").toString();
                Datos[rows][7] = rs.getString("Imagen");
                rows++;
            }
            return Datos;
        } catch (Exception ex) {
            /*en caso de error se muestra el mensaje*/
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

    /*Metodo para buscar los principales datos de los eventos pendientes*/
    public String[][] BuscarDatosPrincipalesEventosPendientes() {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, e.Imagen \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.Codigo = e.Id_Usuario \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where e.Estado = 'Pendiente' AND "
                + "Fecha >= ?";

        try {
            /*Se guarda la fecha en una variable date*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la sentencia, se envia los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();

            int rows = 0;
            /*Se cuenta la cantidad de registros para luego crear un array*/
            while (rs.next()) {
                rows++;
            }
            /**
             * Se crea el array y se guardan los datos
             */
            String[][] Datos = new String[rows][7];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Cls_Evento eve = new Cls_Evento();
                eve.setCodigo(rs.getString("Codigo"));
                eve.setNombre(rs.getString("Nombre"));
                eve.setCreador(rs.getString("NombreEmpresa"));
                eve.setCiudad(rs.getString("NombreCiudad"));
                Datos[rows][0] = eve.getCodigo();
                Datos[rows][1] = eve.getNombre();
                Datos[rows][2] = rs.getDate("Fecha").toString();
                Datos[rows][3] = eve.getCreador();
                Datos[rows][4] = eve.getCiudad();
                Datos[rows][5] = rs.getTime("Fecha").toString();
                Datos[rows][6] = rs.getString("Imagen");
                rows++;
            }
            /*Se retornan los datos*/
            return Datos;
        } catch (Exception ex) {
            /*Se muestra el error en caso de error*/
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

    public Blob getImagenEvento(String codigo) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "Select Imagen "
                + "From  tb_evento Where Codigo = ?";
        /*Se crea una variable blob para guardar la imagen*/
        Blob Datos = null;
        try {
            /*Se prepara la sentencia, se le envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs = pr.executeQuery();

            /*Se guarda la imagen en la variable*/
            while (rs.next()) {
                Datos = rs.getBlob("Imagen");
            }
            /*Se retorna la imagen*/
            return Datos;
        } catch (Exception ex) {
            /*Se muestra la imagen en caso de error*/
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

    /*MEtodo para cambiar el estado del evento a pendiente*/
    public boolean setEstadoPendiente(String codigoEvento) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea una sentencia sql en string*/
        String sql = "UPDATE tb_evento SET Estado = 'Pendiente' "
                + "Where Codigo = ?";
        try {
            /*Se prepara la sentencia, se le envia los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoEvento);
            /**
             * Se ejecuta y si funciona bien se retorna verdadero
             */
            if (pr.executeUpdate() == 1) {
                return true;
            }
            /*sino se retorna falso*/
            return false;
        } catch (Exception ex) {
            /**
             * Se muestra un mensaje en caso de error
             */
            ex.printStackTrace();
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se guarda un mensaje en la clase en caso de error y se retorna falso*/
        this.setMensaje("Ocurruó un problema al completar el registro del evento.  Estamos trabajando para solucionar este problema.");
        return false;
    }

    /*MEtodo para cambiar el estado del evento*/
    public boolean setCambioEstadoEvento(String codigoEvento, String estado) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea una sentencia sql en string*/
        String sql = "UPDATE tb_evento SET Estado = ? ";
        sql += "WHERE Codigo = ? ";
        try {
            /**
             * Se prepara la sentenica, se le envian los datos y se ejecuta
             */
            pr = conn.prepareStatement(sql);
            pr.setString(1, estado);
            pr.setString(2, codigoEvento);
            /**
             * Si se ejecuta correctamente se retorna verdadero
             */
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            /*En caso de error se muestra un mensaje*/
            ex.getMessage().toString();
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*En caso de error se guarda el mensaje en la clase y se retorna falso*/
        this.setMensaje("Ocurrió un problema inesperado al modificar el evento.  Estamos trabajando para solucionar este problema.");
        return false;
    }

    /*Metodo para desaprobar un evento*/
    public boolean setDesaprobarEvento(String codigoEvento, String motivo) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea una sentencia sql en string*/
        String sql = "UPDATE tb_evento SET Motivo = ?, Estado = ? ";
        sql += "WHERE Codigo = ? ";
        try {
            /*Se prepara la sentenci, se le envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, motivo);
            pr.setString(2, "Desaprobado");
            pr.setString(3, codigoEvento);
            /*Si se ejecuta correctamente se retorna verdadero*/
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            ex.getMessage().toString();
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se guarda un mensaje en caso de error y se retorna flaso*/
        this.setMensaje("Ocurrió un problema inesperado al desaprobar el evento,  Estamos trabajando para solucionar este problema..");
        return false;
    }

    /*Metodo para desaprobar un evento*/
    public boolean setCancelarEvento(String codigoEvento, String motivo) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        /*Se crea una sentencia sql en string*/
        String sql = "UPDATE tb_evento SET Motivo = ?, Estado = ? ";
        sql += "WHERE Codigo = ? ";
        try {
            /*Se prepara la sentenci, se le envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, motivo);
            pr.setString(2, "Cancelado");
            pr.setString(3, codigoEvento);
            /*Si se ejecuta correctamente se retorna verdadero*/
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            ex.getMessage().toString();
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se guarda un mensaje en caso de error y se retorna flaso*/
        this.setMensaje("Ocurrió un problema inesperado al desaprobar el evento,  Estamos trabajando para solucionar este problema..");
        return false;
    }

    /*MEtodo para obtener la camtidad de eventos incompletos*/
    public int getCantidadEventoIncompleto(String Id_Usuario) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "Select Count(Estado) Cantidad FROM tb_evento Where Estado = 'Incompleto' AND Id_Usuario = ?";
        try {
            /*Se prepara la sentencia, se le envia los datos y se ejecuta*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, Id_Usuario);
            rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getInt("Cantidad");
            }
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
            ex.getMessage().toString();
        } finally {
            /*Se cierra todo*/
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se retorna 0 en caso de error*/
        return 0;
    }

    /*Metodo apra obtener los eventos recomendatos*/
    public String[][] geteventosRecomendados(String codigoUsuario, int Limite, int Cantidad) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sqlevento = "SELECT DISTINCT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, "
                + "(Select Count(Comentario) From tb_satisfaccion Where Id_Evento = e.Codigo) Comentarios, "
                + "Round((Select Avg(Calificacion) From tb_satisfaccion Where Id_Evento = e.Codigo),2) Calificacion, e.Imagen \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.Codigo = e.Id_Usuario \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "JOIN tb_seleccion_evento se on se.Id_Evento = e.Codigo \n"
                + "Where e.Estado = 'Aprobado' AND "
                + "Fecha >= ? AND se.Id_Seleccion IN (Select Id_Seleccion From tb_seleccion_usuario WHERE"
                + " Id_Usuario = ? AND Estado = 'Activo') ORDER BY Fecha LIMIT ?,?";
        try {

            /*Se guarda la fecha en un date*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la sentencia, se le envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sqlevento);
            pr.setTimestamp(1, sqlDate);
            pr.setString(2, codigoUsuario);
            pr.setInt(3, Limite);
            pr.setInt(4, Cantidad);
            rs = pr.executeQuery();
            /*Se cuenta la cantidad de resultados para crear un array*/
            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            /*Se crea un array y se guardan los datos*/
            String[][] Datos = new String[rows][9];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Datos[rows][0] = rs.getString("Codigo");
                Datos[rows][1] = rs.getString("Nombre");
                Datos[rows][2] = rs.getDate("Fecha").toString();
                Datos[rows][3] = rs.getString("NombreEmpresa");
                Datos[rows][4] = rs.getString("NombreCiudad");
                Datos[rows][5] = rs.getTime("Fecha").toString();
                Datos[rows][6] = rs.getString("Calificacion");
                Datos[rows][7] = rs.getString("Comentarios");
                Datos[rows][8] = rs.getString("Imagen");
                rows++;
            }
            /*Se retornan los datos*/
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
        /*En caso de error se retorna nulo*/
        return null;
    }

    /*Metodo apra contar la cantidad de eventos recomendados*/
    public int getcantidadeventosRecomendados(String codigoUsuario) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sqlevento = "SELECT Count(DISTINCT e.Codigo) Cantidad "
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_seleccion_evento se on se.Id_Evento = e.Codigo \n"
                + "Where e.Estado = 'Aprobado' AND "
                + "Fecha >= ? AND se.Id_Seleccion IN ("
                + "Select Id_Seleccion "
                + "From tb_seleccion_usuario "
                + "WHERE Id_Usuario = ? AND Estado = 'Activo') "
                + "ORDER BY Fecha ";
        try {
            /*Se guarda la fecha del sistema en un date*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la sentencia, se envia los datos a la sentencia y se ejceuta*/
            pr = conn.prepareStatement(sqlevento);
            pr.setTimestamp(1, sqlDate);
            pr.setString(2, codigoUsuario);
            rs = pr.executeQuery();
            int rows = 0;
            /*Se guarda el dato en un string*/
            if (rs.next()) {
                rows = rs.getInt("Cantidad");
            }
            /*Se retorna el entero*/
            return rows;
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
        /*En caso de error se retorna cero*/
        return 0;
    }

    /*Metodo apra contar la cantidad de eventos recomendados*/
    public int getcantidadeventosGeneralAndroid() {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sqlevento = "SELECT Count(DISTINCT Codigo) Cantidad "
                + "FROM  `tb_evento` \n"
                + "Where Estado = 'Aprobado' AND "
                + "Fecha >= ? "
                + "ORDER BY Fecha ";
        try {
            /*Se guarda la fecha del sistema en un date*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la sentencia, se envia los datos a la sentencia y se ejceuta*/
            pr = conn.prepareStatement(sqlevento);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();
            int rows = 0;
            /*Se guarda el dato en un string*/
            if (rs.next()) {
                rows = rs.getInt("Cantidad");
            }
            /*Se retorna el entero*/
            return rows;
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
        /*En caso de error se retorna cero*/
        return 0;
    }

    /*Metodo apra obtener la ubicacion de los eventos*/
    public String[][] getubicacioneventos() {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sqlevento = "SELECT Codigo, Nombre, Latitud, Longitud "
                + "FROM tb_evento "
                + "Where Fecha < last_day(CURDATE()) "
                + "AND Estado = 'Aprobado' "
                + "AND Fecha > CURDATE()";
        try {

            /*Se prepara la sentencia y se ejecuta*/
            pr = conn.prepareStatement(sqlevento);
            rs = pr.executeQuery();
            /*Se cuenta la cantidad de resultados para crear un array*/
            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            /*Se crea un array y se guardan los datos*/
            String[][] Datos = new String[rows][4];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Datos[rows][0] = rs.getString("Codigo");
                Datos[rows][1] = rs.getString("Nombre");
                Datos[rows][2] = rs.getString("Latitud");
                Datos[rows][3] = rs.getString("Longitud");
                rows++;
            }
            /*Se retornan los datos*/
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
        /*En caso de error se retorna nulo*/
        return null;
    }

    /*Metodo apra obtener los eventos recomendatos*/
    public String[][] geteventosRecomendadosAndroid(String codigoUsuario, int Cantidad) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sqlevento = "SELECT DISTINCT e.Codigo, e.Imagen,  e.Nombre, e.Fecha, c.Nombre NombreCiudad, d.Nombre NombreDepto \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_seleccion_evento se on se.Id_Evento = e.Codigo \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "JOIN tb_departamento d on d.Codigo = c.Codigo_Departamento "
                + "Where e.Estado = 'Aprobado' AND "
                + "Fecha >= ? AND se.Id_Seleccion IN ("
                + "Select Id_Seleccion "
                + "From tb_seleccion_usuario "
                + "WHERE Id_Usuario = ? AND Estado = 'Activo') "
                + "ORDER BY Fecha  Limit ?,5";
        try {

            /*Se guarda la fecha en un date*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la sentencia, se le envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sqlevento);
            pr.setTimestamp(1, sqlDate);
            pr.setString(2, codigoUsuario);
            pr.setInt(3, Cantidad);
            rs = pr.executeQuery();
            /*Se cuenta la cantidad de resultados para crear un array*/
            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            /*Se crea un array y se guardan los datos*/
            String[][] Datos = new String[rows][6];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Datos[rows][0] = rs.getString("Codigo");
                Datos[rows][1] = rs.getString("Imagen");
                Datos[rows][2] = rs.getString("Nombre");
                Datos[rows][3] = rs.getString("Fecha");
                Datos[rows][4] = rs.getString("NombreCiudad");
                Datos[rows][5] = rs.getString("NombreDepto");
                rows++;
            }
            /*Se retornan los datos*/
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
        /*En caso de error se retorna nulo*/
        return null;
    }
    /*Metodo apra obtener los eventos recomendatos*/

    public String[][] geteventosGeneralAndroid(int Cantidad) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sqlevento = "SELECT DISTINCT e.Codigo, e.Imagen, e.Nombre, e.Fecha, c.Nombre NombreCiudad, d.Nombre NombreDepto \n"
                + "       From tb_evento e\n"
                + "       JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "       JOIN tb_departamento d on d.Codigo = c.Codigo_Departamento\n"
                + "       WHERE e.Fecha >= ? AND e.Estado = 'Aprobado' ORDER BY e.Fecha Limit ?,5";
        try {

            /*Se guarda la fecha en un date*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la sentencia, se le envian los datos y se ejecuta*/
            pr = conn.prepareStatement(sqlevento);
            pr.setTimestamp(1, sqlDate);
            pr.setInt(2, Cantidad);
            rs = pr.executeQuery();
            /*Se cuenta la cantidad de resultados para crear un array*/
            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            /*Se crea un array y se guardan los datos*/
            String[][] Datos = new String[rows][6];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Datos[rows][0] = rs.getString("Codigo");
                Datos[rows][1] = rs.getString("Imagen");
                Datos[rows][2] = rs.getString("Nombre");
                Datos[rows][3] = rs.getString("Fecha");
                Datos[rows][4] = rs.getString("NombreCiudad");
                Datos[rows][5] = rs.getString("NombreDepto");
                rows++;
            }
            /*Se retornan los datos*/
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
        /*En caso de error se retorna nulo*/
        return null;
    }

    /**
     * Metodo para buscar los datos de los eventos que ha creado la empresa
     */
    public String[][] BuscarDatosMisEventosAndroid(String codigo, int cantidad) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sql = "SELECT DISTINCT e.Codigo, e.Imagen, e.Nombre, e.Fecha, c.Nombre NombreCiudad, d.Nombre NombreDepto \n"
                + "From tb_evento e \n"
                + "Join tb_usuario u ON u.Codigo = e.Id_Usuario \n"
                + "AND u.Codigo = ? \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "JOIN tb_departamento d on d.Codigo = c.Codigo_Departamento \n"
                + "WHERE e.Fecha >= ? ORDER BY e.Fecha Limit ?, 5";

        try {
            /*Se guarda la fecha en una variable tipo date*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /**
             * Se prepara la sentencia, se le envian los datos y se ejecuta
             */
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            pr.setTimestamp(2, sqlDate);
            pr.setInt(3, cantidad);
            rs = pr.executeQuery();

            /*Se cuenta los registros para luego crear un array*/
            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            /*Se crea un array para guardar los datos*/
            String[][] Datos = new String[rows][6];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Datos[rows][0] = rs.getString("Codigo");
                Datos[rows][1] = rs.getString("Imagen");
                Datos[rows][2] = rs.getString("Nombre");
                Datos[rows][3] = rs.getString("Fecha");
                Datos[rows][4] = rs.getString("NombreCiudad");
                Datos[rows][5] = rs.getString("NombreDepto");
                rows++;
            }
            /**
             * Se retornan los datos
             */
            return Datos;
        } catch (Exception ex) {
            /**
             * Se muestra un mensaje en caso de error
             */
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
        /**
         * Se retorna nulo en caso de error
         */
        return null;
    }

    /*Metodo apra contar la cantidad de los eventos de la empresa*/
    public int getcantidadMisEventosAndroid(String codigo) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sqlevento = "SELECT COUNT(DISTINCT e.Codigo) \n"
                + "From tb_evento e \n"
                + "Join tb_usuario u ON u.Codigo = e.Id_Usuario \n"
                + "AND u.Codigo = ? \n"
                + "WHERE e.Fecha >= ? ";
        try {
            /*Se guarda la fecha del sistema en un date*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            /*Se prepara la sentencia, se envia los datos a la sentencia y se ejceuta*/
            pr = conn.prepareStatement(sqlevento);
            pr.setString(1, codigo);
            pr.setTimestamp(2, sqlDate);
            rs = pr.executeQuery();
            int rows = 0;
            /*Se guarda el dato en un string*/
            if (rs.next()) {
                rows = rs.getInt("Cantidad");
            }
            /*Se retorna el entero*/
            return rows;
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
        /*En caso de error se retorna cero*/
        return 0;
    }

    /*Metodo apra obtener la ubicacion de los eventos*/
    public String[] getUbicacionAndroid(String codigo) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una sentencia sql en string*/
        String sqlevento = "SELECT Latitud, Longitud, Nombre FROM tb_evento where Codigo= ? ";
        try {

            /*Se prepara la sentencia y se ejecuta*/
            pr = conn.prepareStatement(sqlevento);
            pr.setString(1, codigo);
            rs = pr.executeQuery();
            String[] Datos = new String[3];
            if (rs.next()) {
                Datos[0] = rs.getString("Latitud");
                Datos[1] = rs.getString("Longitud");
                Datos[2] = rs.getString("Nombre");
            }
            /*Se retornan los datos*/
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
        /*En caso de error se retorna nulo*/
        return null;
    }
}
