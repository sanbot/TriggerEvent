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
import java.sql.Blob;

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
    String Estado;

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

    public boolean setRegistrarSeleccion(String nombre, String tipo, String imagen) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        String estado = "Aprobado";
        String codigo = "SEL";
        int numerocodigo = this.CantidadRegistroSeleccion();
        codigo += numerocodigo;
        pr = null;
        String sql = "INSERT INTO tb_seleccion (Codigo, Imagen, Nombre, Tipo, Estado)";
        sql += "VALUES(?,?,?,?, ?)";
        FileInputStream is = null;

        try {
            is = new FileInputStream(imagen);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Seleccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            pr.setBlob(2, is);
            pr.setString(3, nombre);
            pr.setString(4, tipo);
            pr.setString(5, estado);

            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException ex) {
            if (ex.toString().indexOf("Duplicate") > 0) {
                if (ex.toString().indexOf("Nombre") > 0) {
                    this.setMensaje("Ya hay un "+tipo+" con este nombre.");
                    return false;
                }
            }
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al registrar el "+tipo+". Estamos trabajando para solucionar este problema.");
        return false;
    }

    public int CantidadRegistroSeleccion() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select Count(Codigo) Cantidad "
                + "From  tb_seleccion";
        try {
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int i = 0;
            if (rs.next()) {

                i = Integer.parseInt(rs.getString("Cantidad"));
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

    //con este metodo se sabe cuantas personas usan el gusto o ambiente para poder eliminarlo
    public boolean CantidadUsoAmbienteGusto(String codigoSeleccion) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sqluno = "Select Count(Codigo) Cantidad "
                + "From  tb_seleccion_usuario "
                + "Where Estado = 'Activo'"
                + " AND Id_Seleccion = ? ";
        String sqldos = "Select Count(Codigo) Cantidad "
                + "From  tb_seleccion_evento Where Estado = 'Activo'"
                + " AND ID_Seleccion = ?";
        try {
            pr = conn.prepareStatement(sqluno);
            pr.setString(1, codigoSeleccion);
            rs = pr.executeQuery();

            int uno = 0;
            if (rs.next()) {

                uno = Integer.parseInt(rs.getString("Cantidad"));
            }
            pr = conn.prepareStatement(sqldos);
            pr.setString(1, codigoSeleccion);
            rs = pr.executeQuery();

            int dos = 0;
            if (rs.next()) {

                dos = Integer.parseInt(rs.getString("Cantidad"));
            }
            if (uno == 0 && dos == 0) {
                return true;
            }
            return false;
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
        return false;
    }

    public String[][] getDatosSeleccion() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select Codigo, Nombre, Tipo, Estado "
                + "From  tb_seleccion";
        String[][] Datos = null;
        try {
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int i = 0;
            while (rs.next()) {

                i++;
            }
            Datos = new String[i][4];
            rs.beforeFirst();
            i = 0;
            while (rs.next()) {
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                Datos[i][3] = rs.getString("Estado");
                i++;
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

    public Blob getImagenSeleccion(String codigo) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select Imagen "
                + "From  tb_seleccion Where Codigo = ?";
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

    public boolean actualizardatosSeleccion(String codigo, String nombre, String tipo, String estado) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        String sql = "UPDATE tb_seleccion SET Nombre = ?, Tipo = ?, Estado = ? ";
        sql += "WHERE Codigo = ?";
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, nombre);
            pr.setString(2, tipo);
            pr.setString(3, estado);
            pr.setString(4, codigo);
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            if (ex.toString().indexOf("Duplicate") > 0) {
                if (ex.toString().indexOf("Nombre") > 0) {
                    this.setMensaje("Ya hay un "+tipo+" con este nombre.");
                    return false;
                }
            }
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al modificar el "+tipo+".  Estamos trabajando para solucionar este problema.");
        return false;
    }

    public boolean actualizardatosSeleccion(String codigo, String nombre, String tipo, String imagen, String estado) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        String sql = "UPDATE tb_seleccion SET Nombre = ?, Tipo = ?, Imagen = ? ";
        sql += "WHERE Codigo = ?";
        FileInputStream is = null;

        try {
            is = new FileInputStream(imagen);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Seleccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, nombre);
            pr.setString(2, tipo);
            pr.setBlob(3, is);
            pr.setString(4, estado);
            pr.setString(5, codigo);
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            if (ex.toString().indexOf("Duplicate") > 0) {
                if (ex.toString().indexOf("Nombre") > 0) {
                    this.setMensaje("Ya hay un "+tipo+" con este nombre.");
                    return false;
                }
            }
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al  modificar el "+tipo+".  Estamos trabajando para solucionar este problema.");
        return false;
    }

    public String[][] getGustosNuevos(String codigo) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select Codigo, Nombre, Tipo, Imagen "
                + "From  tb_seleccion "
                + "Where Codigo NOT IN (select Id_Seleccion From tb_seleccion_usuario Where Id_Usuario = ? And Estado = 'Activo')"
                + "AND Estado = 'Aprobado'";
        String[][] Datos = null;
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs = pr.executeQuery();

            int i = 0;
            while (rs.next()) {

                i++;
            }
            Datos = new String[i][3];
            rs.beforeFirst();
            i = 0;
            while (rs.next()) {
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                i++;
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

    public String[][] getClasificacionNuevos(String codigo) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select Codigo, Nombre, Tipo, Imagen "
                + "From  tb_seleccion "
                + "Where Codigo NOT IN (select Id_Seleccion From tb_seleccion_evento Where Id_Evento = ? And Estado = 'Activo')"
                + "AND Estado = 'Aprobado'";
        String[][] Datos = null;
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs = pr.executeQuery();

            int i = 0;
            while (rs.next()) {

                i++;
            }
            Datos = new String[i][3];
            rs.beforeFirst();
            i = 0;
            while (rs.next()) {
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                i++;
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

    public String[][] getMisGustos(String codigo) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select Codigo, Nombre, Tipo "
                + "From  tb_seleccion "
                + "Where Codigo IN (select Id_Seleccion From tb_seleccion_usuario Where Id_Usuario = ?"
                + " And Estado = 'Activo')";
        String[][] Datos = null;
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigo);
            rs = pr.executeQuery();

            int i = 0;
            while (rs.next()) {

                i++;
            }
            Datos = new String[i][3];
            rs.beforeFirst();
            i = 0;
            while (rs.next()) {
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                i++;
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

    public String[][] getClasificacionEvento(String codigoEvento) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select Codigo, Nombre, Tipo "
                + "From  tb_seleccion "
                + "Where Codigo IN (select Id_Seleccion From tb_seleccion_evento Where Id_Evento = ?"
                + " And Estado = 'Activo')";
        String[][] Datos = null;
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoEvento);
            rs = pr.executeQuery();

            int i = 0;
            while (rs.next()) {

                i++;
            }
            Datos = new String[i][3];
            rs.beforeFirst();
            i = 0;
            while (rs.next()) {
                Datos[i][0] = rs.getString("Codigo");
                Datos[i][1] = rs.getString("Nombre");
                Datos[i][2] = rs.getString("Tipo");
                i++;
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

    public boolean AddGusto(String codigoSeleccion, String CodigoUsuario) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String codigo = "GUS";
        codigo += this.CantidadGustos();
        String sqlc = "Select Codigo From tb_seleccion_usuario Where Id_Seleccion = ? and Id_Usuario = ? ";
        String sqli = "Insert Into tb_seleccion_usuario (Codigo, Id_Seleccion, Id_Usuario, Estado)"
                + " Values (?,?,?,?)";
        String sqlu = "UPDATE tb_seleccion_usuario SET Estado = 'Activo' Where Codigo = ?";

        try {
            pr = conn.prepareStatement(sqlc);
            pr.setString(1, codigoSeleccion);
            pr.setString(2, CodigoUsuario);
            rs = pr.executeQuery();
            if (rs.next()) {
                pr = conn.prepareStatement(sqlu);
                pr.setString(1, rs.getString("Codigo"));
                if (pr.executeUpdate() == 1) {
                    return true;
                }
            } else {
                pr = conn.prepareStatement(sqli);
                pr.setString(1, codigo);
                pr.setString(2, codigoSeleccion);
                pr.setString(3, CodigoUsuario);
                pr.setString(4, "Activo");
                if (pr.executeUpdate() == 1) {
                    return true;
                }
            }

        } catch (Exception ex) {
            if (ex.toString().indexOf("Duplicate") > 0) {
                if (ex.toString().indexOf("UK_Id_Seleccion_Id_Usuario") > 0) {
                    this.setMensaje("Ya tiene este gusto agregado. No puede volverlo a agregar.");
                    return false;
                }
            }
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al agregar el gusto a su cuenta.  Estamos trabajando para solucionar este problema.");
        return false;
    }

    public boolean RemoveGusto(String codigoSeleccion, String CodigoUsuario) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String codigo = "GUS";
        codigo += this.CantidadGustos();
        String sqlc = "Select Codigo From tb_seleccion_usuario Where Id_Seleccion = ? and Id_Usuario = ? ";
        String sqlu = "UPDATE tb_seleccion_usuario SET Estado = 'Inactivo' Where Codigo = ?";

        try {
            pr = conn.prepareStatement(sqlc);
            pr.setString(1, codigoSeleccion);
            pr.setString(2, CodigoUsuario);
            rs = pr.executeQuery();
            if (rs.next()) {
                pr = conn.prepareStatement(sqlu);
                pr.setString(1, rs.getString("Codigo"));
                if (pr.executeUpdate() == 1) {
                    return true;
                }
            }

        } catch (Exception ex) {
            System.out.print(ex.toString());
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al remover el gusto de su cuenta.  Estamos trabajando para solucionar este problema.");
        return false;
    }

    public int CantidadGustos() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select * "
                + "From  tb_seleccion_usuario";
        try {
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int i = 0;
            while (rs.next()) {

                i++;
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

    public boolean CantidadGustosAmbientesPreRemove(String codigoSeleccion, String codigoUsuario) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT Count(su.Codigo) Cantidad FROM `tb_seleccion_usuario` su "
                + "JOIN tb_seleccion s on s.Codigo = su.Id_Seleccion "
                + "AND s.Tipo = (Select Tipo From tb_seleccion Where Codigo = ?) "
                + "WHERE Id_Usuario = ? AND su.Estado = 'Activo'";
        String consulta = "Select Tipo From tb_seleccion Where Codigo = ?";
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoSeleccion);
            pr.setString(2, codigoUsuario);
            rs = pr.executeQuery();

            int i = 0;
            if (rs.next()) {
                i = Integer.parseInt(rs.getString("Cantidad"));
            }
            if (i > 1) {
                return true;
            } else {
                pr = conn.prepareStatement(consulta);
                pr.setString(1, codigoSeleccion);
                rs = pr.executeQuery();
                if (rs.next()) {
                    this.setMensaje("No se puede remover este " + rs.getString("Tipo") + ", porque es el único que tiene registrado.");
                } else {
                    this.setMensaje("Ocurrió un error inesperado al remover el "+rs.getString("Tipo")+" de su cuenta.  Estamos trabajando para solucionar este problema.");
                }
                return false;
            }
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
        return false;
    }

    public boolean DesaprobarSeleccion(String codigoSeleccion) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        String sql = "UPDATE tb_seleccion SET Estado = 'Desaprobado' ";
        sql += "WHERE Codigo = ?";
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoSeleccion);
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            this.setMensaje(ex.getMessage().toString());
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al desactivar este gusto o ambiente.");
        return false;
    }

    public boolean AprobarSeleccion(String codigoSeleccion) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        String sql = "UPDATE tb_seleccion SET Estado = 'Aprobado' ";
        sql += "WHERE Codigo = ?";
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoSeleccion);
            if (pr.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception ex) {
            this.setMensaje(ex.getMessage().toString());
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al activar este gusto o ambiente.");
        return false;
    }

    public boolean AddClasificacionEvento(String codigoSeleccion, String codigoEvento) {
        String codigo = "CLA" + this.CantidadRegistroEventoSeleccion();

        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String Est = "Activo";

        String sqli = "Insert into tb_seleccion_evento (Codigo, Id_Seleccion , Id_Evento , Estado) ";
        sqli += "VALUES (?,?,?,?)";
        String sqlc = "Select Codigo From tb_seleccion_evento Where Id_Seleccion = ? and Id_Evento = ? ";
        String sqlu = "UPDATE tb_seleccion_evento SET Estado = 'Activo' Where Codigo = ?";
        try {
            pr = conn.prepareStatement(sqlc);
            pr.setString(1, codigoSeleccion);
            pr.setString(2, codigoEvento);
            rs = pr.executeQuery();
            if (rs.next()) {
                pr = conn.prepareStatement(sqlu);
                pr.setString(1, rs.getString("Codigo"));
                if (pr.executeUpdate() == 1) {
                    return true;
                }
            } else {
                pr = conn.prepareStatement(sqli);
                pr.setString(1, codigo);
                pr.setString(2, codigoSeleccion);
                pr.setString(3, codigoEvento);
                pr.setString(4, Est);
                if (pr.executeUpdate() == 1) {
                    return true;
                }
            }
        } catch (Exception ex) {
            if (ex.toString().indexOf("Duplicate") > 0) {
                if (ex.toString().indexOf("UK_IdSeleccion_IdEvento_SeleccionEvento") > 0) {
                    this.setMensaje("Este gusto o ambiente ya se encuentra agregado a la clasificacion de este evento.");
                }
            }
        } finally {
            try {
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        this.setMensaje("Ocurrió un problema inesperado al agregar este gusto o ambiente a la clasificación del evento.  Estamos trabajando para solucionar este problema.");
        return false;
    }

    public int CantidadRegistroEventoSeleccion() {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "Select Count(Codigo) Cantidad "
                + "From  tb_seleccion_evento";
        try {
            pr = conn.prepareStatement(sql);
            rs = pr.executeQuery();

            int i = 0;
            if (rs.next()) {

                i = Integer.parseInt(rs.getString("Cantidad"));
            }
            return i + 2;
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

    public boolean ComprobarRegistroCompletoUSuario(String codigoevento) {
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String consultaevento = "Select Estado"
                + " From tb_evento"
                + " Where Codigo = ?";
        String sqla = "Select Count(se.Codigo) Cantidad "
                + "From  tb_seleccion_evento se "
                + "JOIN tb_seleccion s on  se.Id_Seleccion = s.Codigo "
                + "WHERE se.Id_Evento = ? "
                + "AND s.Tipo = 'Gusto' "
                + "AND se.Estado = 'Activo'";
        String sqlb = "Select Count(se.Codigo) Cantidad "
                + "From  tb_seleccion_evento se "
                + "JOIN tb_seleccion s on  se.Id_Seleccion = s.Codigo "
                + "WHERE se.Id_Evento = ? "
                + "AND s.Tipo = 'Ambiente' "
                + "AND se.Estado = 'Activo'";
        try {
            pr = conn.prepareStatement(consultaevento);
            pr.setString(1, codigoevento);
            rs = pr.executeQuery();
            if(rs.next() && (!rs.getString("Estado").equals("Pendiente")))
            {
                pr = conn.prepareStatement(sqla);
                pr.setString(1, codigoevento);
                rs = pr.executeQuery();

                int uno = 0;
                if (rs.next()) {

                    uno = Integer.parseInt(rs.getString("Cantidad"));
                }

                pr = conn.prepareStatement(sqlb);
                pr.setString(1, codigoevento);
                rs = pr.executeQuery();
                int dos = 0;
                if (rs.next()) {
                    dos = Integer.parseInt(rs.getString("Cantidad"));
                }
                if (uno >= 1 && dos >= 1) {
                    return true;
                }
            }else{
                
            }
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
        return false;
    }
}
