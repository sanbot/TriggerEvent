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

    /*Se crean las variables necesarias y se les hace el metodo set y get además se instancia la clase conexion*/
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

    public Cls_Satisfaccion() {
        Connection conn = conexion.conectar();
    }

    /*Metodo para registrar la calificacion y comentario de la satisfaccion del usuario*/
    public boolean setRegistrarCalificacionYComentarioSatisfaccion(String codigoUsuario, String codigoEvento, String Rating, String comentario) {
        //incializamos los valores necesarios 
        /*Se un numero para crar el codigo primario*/
        int numerocodigo = this.CantidadRegistroSatisfaccion();
        /*Se conecta a la base de datos*/
        Connection conn = conexion.conectar();
        /*Se crea la variable para la sentenica preparada y para recorrer los resultados*/
        PreparedStatement pr = null;
        /*Se crea el codio con el numero retornado por el metodo*/
        String codigo = "SAT";
        codigo += numerocodigo;

        //string para consulta, insert y update
        String insert = "Insert into tb_satisfaccion (Codigo, Id_Evento, Id_Usuario, Calificacion, Comentario, Fecha) Values(?,?,?,?,?,?)";

        Date fecha = new Date();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
        try {

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
            if (pr.executeUpdate() == 1) {
                //si se insertaron los datos retornamso un verdadero
                return true;
            } else {
                //sino se inserta la calificacion, retornamos un falso y guardamos un mensaje encapsulado
                this.setMensaje("Ocurrió un problema inesperado al registrar la crítica al evento. Estamos trabajando para solucionar este problema.");
                return false;
            }
        } catch (SQLException ex) {
            /*Se retorna el mensaje de error*/
            this.setMensaje(ex.getMessage().toString());
        } finally {
            try {
                /*Finalmente se cierra la sentenica preparada y la conexion*/
                pr.close();
                conn.close();
            } catch (Exception ex) {
                this.setMensaje(ex.getMessage().toString());
            }
        }
        /*Se retorna un mensaje de error en caso de fallar*/
        this.setMensaje("Ocurrió un problema inesperado al registrar la crítica.  Estamos trabajando para solucionar este problema.");
        return false;
    }
    /*Registrar la calificacion a un evento*/
    public boolean setRegistrarCalificacionSatisfaccion(String codigoUsuario, String codigoEvento, String Rating) {
        //incializamos los valores necesarios
        /*Se un numero para crar el codigo primario*/
        int numerocodigo = this.CantidadRegistroSatisfaccion();
        /*Se conecta a la base de datos*/
        Connection conn = conexion.conectar();
        /*Se crea la variable para la sentenica preparada y para recorrer los datos*/
        PreparedStatement pr = null;
        ResultSet rs;
        /*Se crea el codigo para el registro*/
        String codigo = "SAT";
        codigo += numerocodigo;

        //string para consulta, insert y update
        String select = "Select Codigo From tb_satisfaccion where Id_Usuario = ? AND Id_Evento = ?";
        String insert = "Insert into tb_satisfaccion (Codigo, Id_Evento, Id_Usuario, Calificacion) Values(?,?,?,?)";
        String update = "UPDATE tb_satisfaccion SET Calificacion = ? Where Codigo = ?";

        try {
            //preparamos la sentencia
            pr = conn.prepareStatement(select);

            //enviamos los parametros necesarios para la ejecucion
            pr.setString(1, codigoUsuario);
            pr.setString(2, codigoEvento);

            //ejecutamos la sentencia
            rs = pr.executeQuery();

            //evaluamos si se encontraron resultados
            if (rs.next()) {
                //preparamos la sentencia para modificar los datos
                pr = conn.prepareStatement(update);

                pr.setString(1, Rating);
                pr.setString(2, rs.getString("Codigo"));
                //ejecutamos la sentencia y evaluamos el resultado
                if (pr.executeUpdate() == 1) {
                    //si se insertaron los datos retornamso un verdadero
                    return true;
                } else {
                    //sino se inserta el comentario, retornamos un falso y guardamos un mensaje encapsulado
                    this.setMensaje("Ocurrió un problema inesperado al registrar la puntuación al evento.  Estamos trabajando para solucionar este problema.");
                    return false;
                }
            } else {
                //preparamos la sentencia para insertar los datos
                pr = conn.prepareStatement(insert);

                //enviamos los parametros necesarios para ejecutar la sentencia
                pr.setString(1, codigo);
                pr.setString(2, codigoEvento);
                pr.setString(3, codigoUsuario);
                pr.setString(4, Rating);

                //ejecutamos la sentencia y evaluamos el resultado
                if (pr.executeUpdate() == 1) {
                    //si se insertaron los datos retornamso un verdadero
                    return true;
                } else {
                    //sino se inserta la calificacion, retornamos un falso y guardamos un mensaje encapsulado
                    this.setMensaje("Ocurrió un problema inesperado al registrar la puntuación al evento. Estamos trabajando para solucionar este problema.");
                    return false;
                }
            }
        } catch (SQLException ex) {
            /*Si hay algun error se muestra*/
            this.setMensaje(ex.getMessage().toString());
        } finally {
            try {
                /*Se cierran todas las conexiones y la sentencia preparada*/
                pr.close();
                conn.close();
            } catch (Exception ex) {
                this.setMensaje(ex.getMessage().toString());
            }
        }
        /*Se muestra un mensaje de error y se retorna false*/
        this.setMensaje("Ocurrió un problema inesperado al registrar la puntuación al evento. Estamos trabajando para solucionar este problema.");
        return false;
    }

    /*Metodo para registrar el comentario*/
    public boolean setRegistrarComentarioSatisfaccion(String codigoUsuario, String codigoEvento, String comentario) {
        //incializamos los valores necesarios
        int numerocodigo = this.CantidadRegistroSatisfaccion();
        /*Se conecta a la base de datos*/
        Connection conn = conexion.conectar();
        /*Se crea una variable para la sentencia preparada y recorrer los datos*/
        PreparedStatement pr = null;
        ResultSet rs;
        /*Se crea el codigo para el registro*/
        String codigo = "SAT";
        codigo += numerocodigo;

        //string para consulta, insert y update
        String select = "Select Codigo From tb_satisfaccion where Id_Usuario = ? AND Id_Evento = ?";
        String insert = "Insert into tb_satisfaccion (Codigo, Id_Evento, Id_Usuario, Comentario, Fecha) Values(?,?,?,?,?)";
        String update = "UPDATE tb_satisfaccion SET Comentario = ?, Fecha = ? Where Codigo = ?";

        /*Se convierte la fecha*/
        Date fecha = new Date();
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());

        try {
            //preparamos la sentencia
            pr = conn.prepareStatement(select);

            //enviamos los parametros necesarios para la ejecucion
            pr.setString(1, codigoUsuario);
            pr.setString(2, codigoEvento);

            //ejecutamos la sentencia
            rs = pr.executeQuery();

            //evaluamos si se encontraron resultados
            if (rs.next()) {
                //preparamos la sentencia para modificar los datos
                pr = conn.prepareStatement(update);

                pr.setString(1, comentario);
                pr.setTimestamp(2, sqlDate);
                pr.setString(3, rs.getString("Codigo"));

                //ejecutamos la sentencia y evaluamos el resultado
                if (pr.executeUpdate() == 1) {
                    //si se insertaron los datos retornamso un verdadero
                    return true;
                } else {
                    //sino se inserta el comentario, retornamos un falso y guardamos un mensaje encapsulado
                    this.setMensaje("Ocurrió un problema inesperado al registrar el comentario al evento. Estamos trabajando para solucionar este problema.");
                    return false;
                }
            } else {
                //preparamos la sentencia para insertar los datos
                pr = conn.prepareStatement(insert);

                //enviamos los parametros necesarios para ejecutar la sentencia
                pr.setString(1, codigo);
                pr.setString(2, codigoEvento);
                pr.setString(3, codigoUsuario);
                pr.setString(4, comentario);
                pr.setTimestamp(5, sqlDate);

                //ejecutamos la sentencia y evaluamos el resultado
                if (pr.executeUpdate() == 1) {
                    //si se insertaron los datos retornamso un verdadero
                    return true;
                } else {
                    //sino se inserta el comentario, retornamos un falso y guardamos un mensaje encapsulado
                    this.setMensaje("Ocurrió un problema inesperado al registrar el comentario al evento. Estamos trabajando para solucionar este problema.");
                    return false;
                }
            }
        } catch (SQLException ex) {
            /*Se muestra el mensaje encaso de error en la ejecucion*/
            this.setMensaje(ex.getMessage().toString());
        } finally {
            try {
                /*Se cierra la conexion y ademas se cierra la sentencia preparada*/
                pr.close();
                conn.close();
            } catch (Exception ex) {
                this.setMensaje(ex.getMessage().toString());
            }
        }
        /*Se muestra un  mensaje de error y se retorna false*/
        this.setMensaje("Ocurrió un problema inesperado al registrar el comentario al evento. Estamos trabajando para solucionar este problema.");
        return false;
    }

    /*metodo para contar los registros de satisfaccion*/
    public int CantidadRegistroSatisfaccion() {
        //inicializamos la variables necesarias
        
        /*Se conecta a la base de datos*/
        Connection conn = conexion.conectar();
        /*Se crea las variables de la sentencia preparada y para recorrer los resultados*/
        PreparedStatement pr = null;
        ResultSet rs = null;
        int i = 0;

        //creamos un string para la consulta
        String consulta = "Select Count(Codigo) Cantidad From  tb_satisfaccion";

        try {
            //preparamos la consulta
            pr = conn.prepareStatement(consulta);

            //ejecutamos la consulta
            rs = pr.executeQuery();

            //evaluamos el resultado de la consulta
            if (rs.next()) {
                //guardamos la cantidad de datos que hay en la base de datos
                i = Integer.parseInt(rs.getString("Cantidad"));
            }
            //retornamos el numero de datos mas uno
            return i + 1;
        } catch (Exception ex) {
            //en caso de error
            ex.printStackTrace();
        } finally {
            try {
                //cerramos todo
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public boolean ComprobarCalificacionRegistrada(String codigoEvento, String codigoUsuario, String tipo) {
        //incializamos los valores necesarios
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;

        //creamos el string para la consulta
        String consulta = "Select Codigo From tb_satisfaccion Where  Id_Evento = ? AND Id_Usuario = ?";
        String consultauno = "Select Count(Codigo) Cantidad From tb_satisfaccion Where " + tipo + " IS NULL AND Id_Evento = ? AND Id_Usuario = ?";

        try {
            //preparemos la consulta para la ejecucion
            pr = conn.prepareStatement(consulta);

            //se envian los parametros necesarios
            pr.setString(1, codigoEvento);
            pr.setString(2, codigoUsuario);

            //ejecutamos la consulta
            rs = pr.executeQuery();

            //evaluamos el contenido de la consulta
            if (rs.next()) {
                //preparemos la consulta para la ejecucion
                pr = conn.prepareStatement(consultauno);

                //se envian los parametros necesarios
                pr.setString(1, codigoEvento);
                pr.setString(2, codigoUsuario);

                //ejecutamos la consulta
                rs = pr.executeQuery();

                //evaluamos el contenido de la consulta
                if (rs.next()) {
                    if (rs.getString("Cantidad").equals("1")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                return true;
            }

        } catch (Exception ex) {
            //en caso de error
            ex.printStackTrace();
        } finally {
            try {
                //cerramos todo
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    /*Metodo para buscar comentarios aleatorios*/
    public String[][] BuscarComentariosAleatorios() {
        /*Se crean las variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea el string para la consulta*/
        String sql = "SELECT u.Nombre NombreUsuario, (Select Nombre From tb_evento Where Codigo = sa.Id_Evento) NombreEvento, sa.Comentario \n"
                + "FROM  `tb_satisfaccion` sa \n"
                + "JOIN tb_usuario u on u.Codigo = sa.Id_Usuario "
                + "JOIN tb_evento e on e.Codigo = sa.Id_Evento "
                + "Where e.Fecha >= ? AND sa.Comentario IS NOT NULL Order by Rand() LIMIT 0,5";

        try {
            /*Se convierta la fecha, se prepara la consulta, se le envian los datos y se ejecuta*/
            Date fecha = new Date();
            java.sql.Timestamp sqlDate = new java.sql.Timestamp(fecha.getTime());
            pr = conn.prepareStatement(sql);
            pr.setTimestamp(1, sqlDate);
            rs = pr.executeQuery();

            int rows = 0;
            /*Se cuentan los registros retornados para crear una rray*/
            while (rs.next()) {
                rows++;
            }
            
            /*Se crea un array*/
            String[][] Datos = new String[rows][3];
            rs.beforeFirst();
            rows = 0;
            /*Se guardan los datos en el array*/
            while (rs.next()) {
                Datos[rows][0] = rs.getString("NombreUsuario");
                Datos[rows][1] = rs.getString("NombreEvento");
                Datos[rows][2] = rs.getString("Comentario");
                rows++;
            }
            /*Se retornan los resultados*/
            return Datos;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                /**Se cierra todo*/
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*Se retorna null en caso de error*/
        return null;
    }

    /*Metodo para buscar los comentarios de los eventos*/
    public String[][] BuscarComentariosEvento(String codigoEvento, int Limite, int cantidad) {
        /*Se crean e instancias las clases y variables necesarioas*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea el string para la consulta*/
        String sql = "SELECT u.Nombre NombreUsuario, (Select Nombre From tb_evento Where Codigo = sa.Id_Evento) NombreEmpresa, sa.Comentario \n"
                + "FROM  `tb_satisfaccion` sa \n"
                + "JOIN tb_usuario u on u.Codigo = sa.Id_Usuario "
                + "Where sa.Comentario IS NOT NULL AND sa.Id_Evento = ? Order by sa.Fecha LIMIT ?,?";

        try {
            /*Se prepara la sentenica y se enviar los datos necesarios para ejecutarla posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoEvento);
            pr.setInt(2, Limite);
            pr.setInt(3, cantidad);
            rs = pr.executeQuery();

            int rows = 0;
            /*Se cuentan los resultados para crear el array*/
            while (rs.next()) {
                rows++;
            }
            /*Se crea el array*/
            String[][] Datos = new String[rows][3];
            rs.beforeFirst();
            rows = 0;
            /*Se llena el array despues de volver antes del primer valor del result set*/
            while (rs.next()) {
                Datos[rows][0] = rs.getString("NombreUsuario");
                Datos[rows][1] = rs.getString("NombreEmpresa");
                Datos[rows][2] = rs.getString("Comentario");
                rows++;

            }
            /*Se retorna el array con los datos*/
            return Datos;
        } catch (Exception ex) {
            /*Se muestra  un mensaje en caso de tenerlo*/
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
        /*En caso de error se retorna nulo*/
        return null;
    }

    /*Metodo para contar los comentarios del evento*/
    public int getCantidadComentariosEvento(String codigoEvento) {
        /*Se crean e instancias las variables y clases necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea un string ocn la consulta*/
        String sql = "SELECT Count(Codigo) Cantidad From tb_satisfaccion "
                + "Where Comentario IS NOT NULL AND Id_Evento = ? Order by Fecha";

        try {
            /**Se prepara la sentencia y ademas se envia los datos para ejecutarla luego*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoEvento);

            rs = pr.executeQuery();

            /*Se guarda el resultado*/
            int rows = 0;
            if (rs.next()) {
                rows = rs.getInt("Cantidad");
            }
            /*retornamos la cuenta*/
            return rows;
        } catch (Exception ex) {
            /*En caso de error se muestra*/
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
        /*En caso de error retornamos 0*/
        return 0;
    }

    /*Obtener el cometnario o la calificacion de un usuario*/
    public String getComentarioOCalificacion(String tipo, String codigoUsuario, String codigoEvento) {
        /*Se crean e instancia las clases y variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea un string para la consulta*/
        String sql = "Select " + tipo + " FROM tb_satisfaccion Where Id_Usuario = ? AND Id_Evento = ?";
        try {
            /*Se prepara la sentencia y se le envian los datos para ejecutarlo posteriormente*/
            pr = conn.prepareStatement(sql);
            pr.setString(1, codigoUsuario);
            pr.setString(2, codigoEvento);
            rs = pr.executeQuery();
            /*Se retorna el resultado*/
            if (rs.next()) {
                return rs.getString(tipo);
            }
            /*En caso de error se retorna null*/
            return null;
        } catch (Exception ex) {
            /*Se muestra el error*/
            ex.printStackTrace();
        } finally {
            try {
            /*Se cierra todo*/
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /*En caso de error se muestra un mensaje y se retorna null*/
        this.setMensaje("Ocurruó un problema al buscar el/la " + tipo + " del evento. Estamos trabajando para solucionar este problema.");
        return null;
    }
    
     /*Metodo para obtener la califiacion de un evento*/
    public int[] getCalificacionEvento(String codigoEvento) {
        /*Se crean variables necesarias*/
        Connection conn = conexion.conectar();
        PreparedStatement pr = null;
        ResultSet rs = null;
        /*Se crea una serie de sentencias sql en string*/
        /*Sentencia para 5*/
        String sqluno = "SELECT Count(Calificacion) Cantidad \n"
                + "FROM  `tb_satisfaccion` e \n"
                + "Where Calificacion = '5' AND Id_Evento = ?";
        /*Sentencia para 4*/
        String sqldos = "SELECT Count(Calificacion) Cantidad \n"
                + "FROM  `tb_satisfaccion` e \n"
                + "Where Calificacion = '4' AND Id_Evento = ?";
        /*Sentencia para 3*/
        String sqltres = "SELECT Count(Calificacion) Cantidad \n"
                + "FROM  `tb_satisfaccion` e \n"
                + "Where Calificacion = '3' AND Id_Evento = ?";
        /*Sentencia para 2*/
        String sqlcua = "SELECT Count(Calificacion) Cantidad \n"
                + "FROM  `tb_satisfaccion` e \n"
                + "Where Calificacion = '2' AND Id_Evento = ?";
        /*Sentencia para 1*/
        String sqlcin = "SELECT Count(Calificacion) Cantidad \n"
                + "FROM  `tb_satisfaccion` e \n"
                + "Where Calificacion = '1' AND Id_Evento = ?";
        int uno = 0, dos = 0, tres = 0, cua = 0, cin = 0;

        /*Se crea un array  para guardar los datos despuues de ejecutar cada consulta*/
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

            /*Se retorna el array con los datos*/
            return Datos;
        } catch (Exception ex) {
            /*En caso de error se muestra un mensaje*/
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                pr.close();
                conn.close();
            } catch (Exception ex) {

            }
        }
        /**
         * En caso de error se retorna null
         */
        return null;
    }
}
