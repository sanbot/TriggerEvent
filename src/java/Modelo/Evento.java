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
    String Estado;
    String Motivo;
    String Latitud;
    String Longitud;

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

    public boolean ConvertirFecha(String FechaDate) {
        try {
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            Date nuevaFecha;
            nuevaFecha = formatoDeFecha.parse(FechaDate);
            this.setFechaDate(nuevaFecha);
            return true;
        } catch (ParseException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean ValidarDosDiasFecha(Date FechaInicial) {
        Date FechaSistema = new Date();
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar.setTime(FechaSistema); // Configuramos la fecha que se recibe
        calendar1.setTime(FechaInicial);
        calendar.add(Calendar.DAY_OF_YEAR, 2);

        if (calendar1.getTime().after(calendar.getTime())) {
            return true;
        }
        return false;
    }

    public boolean setRegistrarEvento(String imagen, String nombre, Date fecha, String descripcion, String rango, String creador, String ciudad, String direccion, String latitud, String longitud) {

        String Est = "Incompleto";
        PreparedStatement pr = null;
        String codigo = "EVE";
        int numerocodigo = this.CantidadRegistroEvento();
        codigo += numerocodigo;
        Connection conn = conexion.conectar();
        this.setCodigo(codigo);
        pr = null;
        String sql = "INSERT INTO tb_evento(Codigo, Imagen, Nombre, Fecha, Descripcion, Rango_Precios, NIT, Codigo_Ciudad, Direccion, Estado, Latitud, Longitud)";
        sql += "VALUES(?,?,?,?,?,?,?,?,?,?, ?,?)";

        FileInputStream is = null;

        try {
            is = new FileInputStream(imagen);
        } catch (FileNotFoundException ex) {
            this.setMensaje(ex.getMessage().toString());
        }

        try {
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr = conn.prepareStatement(sql);
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
            pr.setString(11, latitud);
            pr.setString(12, longitud);

            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            if (ex.toString().indexOf("uk_nombre_codigociudad_fecha") > 0) {
                this.setMensaje("No se puede registrar este evento porque existe otro con el mismo nombre, la misma fecha y en la misma ciudad");
            }
            return false;
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al registrar el evento.  Estamos trabajando para solucionar este problema.");
        return false;
    }

    public int CantidadRegistroEvento() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select Count(Codigo) Cantidad "
                + "From  tb_evento";
        try {
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int i = 0;
            if (rs.next()) {

                i = rs.getInt("Cantidad");
            }
            return i + 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return 0;
    }

    public int CantidadEventoPendiente() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select Count(Codigo) Cantidad "
                + "From  tb_evento"
                + " Where Estado = 'Pendiente' AND Fecha > CURDATE()";
        try {
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int i = 0;
            if (rs.next()) {

                i = Integer.parseInt(rs.getString("Cantidad"));
            }
            return i;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return 0;
    }

    public String[][] BuscarDatosPrincipalesEventos() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, "
                + "(Select Count(Comentario) From tb_satisfaccion Where Id_Evento = e.Codigo) Comentarios, "
                + "Round((Select Avg(Calificacion) From tb_satisfaccion Where Id_Evento = e.Codigo),2) Calificacion \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.No_Documento = e.NIT \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where e.Estado = 'Aprobado' AND "
                + "Fecha >= ? ORDER BY Fecha";

        try {
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();

            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            String[][] Datos = new String[rows][8];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Evento eve = new Evento();
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
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }
    
    public String[][] BuscarDatosPrincipalesEventos(int Limite, int Cantidad) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, "
                + "(Select Count(Comentario) From tb_satisfaccion Where Id_Evento = e.Codigo) Comentarios, "
                + "Round((Select Avg(Calificacion) From tb_satisfaccion Where Id_Evento = e.Codigo),2) Calificacion \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.No_Documento = e.NIT \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where e.Estado = 'Aprobado' AND "
                + "Fecha >= ? ORDER BY Fecha LIMIT ?,?";

        try {
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            pr.setInt(2, Limite);
            pr.setInt(3, Cantidad);
            rs = pr.executeQuery();

            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            String[][] Datos = new String[rows][8];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Evento eve = new Evento();
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
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }

    public String[][] BuscarDatosEventosProximos() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, "
                + "(Select Count(Comentario) From tb_satisfaccion Where Id_Evento = e.Codigo) Comentarios, "
                + "Round((Select Avg(Calificacion) From tb_satisfaccion Where Id_Evento = e.Codigo),2) Calificacion \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.No_Documento = e.NIT \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where e.Estado = 'Aprobado' AND "
                + "Fecha >= ? Order by Fecha LIMIT 0,3";

        try {
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();

            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            String[][] Datos = new String[rows][8];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Evento eve = new Evento();
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

                rows++;

            }
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }

    public String[][] BuscarDatosEventosDestacados() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, "
                + "(Select Count(Comentario) From tb_satisfaccion Where Id_Evento = e.Codigo) Comentarios, "
                + "Round((Select Avg(Calificacion) From tb_satisfaccion Where Id_Evento = e.Codigo),2) Calificacion \n"
                + "FROM  `tb_evento` e JOIN tb_usuario u on u.No_Documento = e.NIT \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where e.Estado = 'Aprobado' AND Fecha >= ? \n"
                + "Order by Calificacion DESC Limit 0,3 ";

        try {
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();

            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            String[][] Datos = new String[rows][8];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Evento eve = new Evento();
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

                rows++;

            }
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }

    public String[][] BuscarDatosEventosComentado() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, "
                + "(Select Count(Comentario) From tb_satisfaccion Where Id_Evento = e.Codigo) Comentarios, "
                + "Round((Select Avg(Calificacion) From tb_satisfaccion Where Id_Evento = e.Codigo),2) Calificacion \n"
                + "FROM  `tb_evento` e JOIN tb_usuario u on u.No_Documento = e.NIT \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where e.Estado = 'Aprobado' AND Fecha >= ? \n"
                + "Order by Comentarios DESC Limit 0,3 ";

        try {
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();

            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            String[][] Datos = new String[rows][8];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Evento eve = new Evento();
                eve.setCodigo(rs.getString("Codigo"));
                eve.setNombre(rs.getString("Nombre"));
                eve.setFecha(rs.getString("Fecha"));
                eve.setCreador(rs.getString("NombreEmpresa"));
                eve.setCiudad(rs.getString("NombreCiudad"));
                Datos[rows][0] = eve.getCodigo();
                Datos[rows][1] = eve.getNombre().toUpperCase();
                Datos[rows][2] = rs.getTime("Fecha").toString();
                Datos[rows][3] = eve.getCreador();
                Datos[rows][4] = eve.getCiudad();
                Datos[rows][5] = rs.getString("Comentarios");
                Datos[rows][6] = rs.getTime("Fecha").toString();
                Datos[rows][7] = rs.getString("Calificacion");

                rows++;

            }
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }

    public String[] BuscarDatosDetalladosEventos(String codigoEvento) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT u.Nombre NombreEmpresa, e.Nombre, e.Rango_Precios, "
                + "c.Codigo_Departamento CodigoDepartamento, d.Nombre NombreDepartamento, "
                + "c.Codigo CodigoCiudad, c.Nombre NombreCiudad, e.Direccion, e.Fecha, e.Descripcion, "
                + "e.Latitud, e.Longitud \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.No_Documento = e.NIT \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "JOIN tb_departamento d on d.Codigo = c.Codigo_Departamento \n"
                + "Where Fecha >= ? AND e.Codigo = ?";

        try {
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            pr.setString(2, codigoEvento);
            rs = pr.executeQuery();

            String[] Datos = new String[13];
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
            }
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }
//    también sirve para eventos incompletos
    public String[] BuscarDatosDetalladosEventosPendiente(String codigoEvento) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT u.Nombre NombreEmpresa, e.Nombre, e.Rango_Precios, "
                + "c.Codigo_Departamento CodigoDepartamento, d.Nombre NombreDepartamento, "
                + "c.Codigo CodigoCiudad, c.Nombre NombreCiudad, e.Direccion, e.Fecha, e.Descripcion, "
                + "e.Motivo, e.Estado, e.Latitud, e.Longitud \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.No_Documento = e.NIT \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "JOIN tb_departamento d on d.Codigo = c.Codigo_Departamento \n"
                + "Where Fecha >= ? AND e.Codigo = ?";

        try {
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            pr.setString(2, codigoEvento);
            rs = pr.executeQuery();

            String[] Datos = new String[15];
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
            }
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }

    public int[] getCalificacionEvento(String codigoEvento) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sqluno = "SELECT Count(Calificacion) Cantidad \n"
                + "FROM  `tb_satisfaccion` e \n"
                + "Where Calificacion = '5' AND Id_Evento = ?";
        String sqldos = "SELECT Count(Calificacion) Cantidad \n"
                + "FROM  `tb_satisfaccion` e \n"
                + "Where Calificacion = '4' AND Id_Evento = ?";
        String sqltres = "SELECT Count(Calificacion) Cantidad \n"
                + "FROM  `tb_satisfaccion` e \n"
                + "Where Calificacion = '3' AND Id_Evento = ?";
        String sqlcua = "SELECT Count(Calificacion) Cantidad \n"
                + "FROM  `tb_satisfaccion` e \n"
                + "Where Calificacion = '2' AND Id_Evento = ?";
        String sqlcin = "SELECT Count(Calificacion) Cantidad \n"
                + "FROM  `tb_satisfaccion` e \n"
                + "Where Calificacion = '1' AND Id_Evento = ?";
        int uno = 0, dos = 0, tres = 0, cua = 0, cin = 0;

        int[] Datos = new int[5];
        try {
            pr = conn.prepareStatement(sqluno);
            pr.setString(1, codigoEvento);

            rs = pr.executeQuery();
            if (rs.next()) {
                uno = rs.getInt("Cantidad");
            }
            pr = conn.prepareStatement(sqldos);
            pr.setString(1, codigoEvento);

            rs = pr.executeQuery();
            if (rs.next()) {
                dos = rs.getInt("Cantidad");
            }
            pr = conn.prepareStatement(sqltres);
            pr.setString(1, codigoEvento);

            rs = pr.executeQuery();
            if (rs.next()) {
                tres = rs.getInt("Cantidad");
            }
            pr = conn.prepareStatement(sqlcua);
            pr.setString(1, codigoEvento);

            rs = pr.executeQuery();
            if (rs.next()) {
                cua = rs.getInt("Cantidad");
            }
            pr = conn.prepareStatement(sqlcin);
            pr.setString(1, codigoEvento);

            rs = pr.executeQuery();
            if (rs.next()) {
                cin = rs.getInt("Cantidad");
            }
            Datos[0] = uno;
            Datos[1] = dos;
            Datos[2] = tres;
            Datos[3] = cua;
            Datos[4] = cin;

            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }

    public String[] BuscarEventoParaMensaje(String codigoEvento) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT u.Nombre NombreEmpresa, e.Nombre, e.Direccion, e.Fecha, u.Correo \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.No_Documento = e.NIT \n"
                + "Where e.Codigo = ?";

        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoEvento);
            rs = pr.executeQuery();

            String[] Datos = new String[5];
            rs.beforeFirst();

            while (rs.next()) {
                Datos[0] = rs.getString("NombreEmpresa");
                Datos[1] = rs.getString("Nombre");
                Datos[2] = rs.getString("Direccion");
                Datos[3] = rs.getString("Fecha");
                Datos[4] = rs.getString("Correo");
            }
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }
    
    public String[][] BuscarDatosMisEventos(String nit) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, e.Estado \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.No_Documento = e.NIT \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where Fecha >= ? AND NIT = ?";

        try {
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            pr.setString(2, nit);
            rs = pr.executeQuery();

            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            String[][] Datos = new String[rows][7];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Evento eve = new Evento();
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

                rows++;

            }
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }
    
    public String[][] BuscarDatosTodosEventos() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad, e.Estado \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.No_Documento = e.NIT \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where Fecha >= ? Order by Fecha";

        try {
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();

            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            String[][] Datos = new String[rows][7];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Evento eve = new Evento();
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

                rows++;

            }
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }

    public String[][] BuscarDatosPrincipalesEventosPendientes() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT e.Codigo, e.Nombre, e.Fecha, u.Nombre NombreEmpresa, c.Nombre NombreCiudad \n"
                + "FROM  `tb_evento` e \n"
                + "JOIN tb_usuario u on u.No_Documento = e.NIT \n"
                + "JOIN tb_ciudad c on c.Codigo = e.Codigo_Ciudad \n"
                + "Where e.Estado = 'Pendiente' AND "
                + "Fecha >= ?";

        try {
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();

            int rows = 0;
            while (rs.next()) {
                rows++;
            }
            String[][] Datos = new String[rows][6];
            rs.beforeFirst();
            rows = 0;
            while (rs.next()) {
                Evento eve = new Evento();
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

                rows++;

            }
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }

    public Blob getImagenEvento(String codigo) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select Imagen "
                + "From  tb_evento Where Codigo = ?";
        Blob Datos = null;
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs = pr.executeQuery();

            while (rs.next()) {
                Datos = rs.getBlob("Imagen");
            }
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return null;
    }

    public boolean setEstadoPendiente(String codigoEvento) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        String sql = "UPDATE tb_evento SET Estado = 'Pendiente' "
                + "Where Codigo = ?";
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoEvento);
            if (pr.executeUpdate() == 1) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurruó un problema al completar el registro del evento.  Estamos trabajando para solucionar este problema.");
        return false;
    }

    public boolean setCambioEstadoEvento(String codigoEvento, String estado) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        String sql = "UPDATE tb_evento SET Estado = ? ";
        sql += "WHERE Codigo = ? ";
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, estado);
            pr.setString(2, codigoEvento);
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            ex.getMessage().toString();
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al modificar el evento.  Estamos trabajando para solucionar este problema.");
        return false;
    }

    public boolean setDesaprobarEvento(String codigoEvento, String motivo) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        String sql = "UPDATE tb_evento SET Motivo = ?, Estado = ? ";
        sql += "WHERE Codigo = ? ";
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, motivo);
            pr.setString(2, "Desaprobado");
            pr.setString(3, codigoEvento);
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            ex.getMessage().toString();
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al desaprobar el evento,  Estamos trabajando para solucionar este problema..");
        return false;
    }

    public int getCantidadEventoIncompleto(String NIT) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select Count(Estado) Cantidad FROM tb_evento Where Estado = 'Incompleto' AND NIT = ?";
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, NIT);
            rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getInt("Cantidad");
            }
        } catch (Exception ex) {
            ex.getMessage().toString();
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        return 0;
    }

}
