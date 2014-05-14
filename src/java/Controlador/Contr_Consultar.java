/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Modelo.Tipo_Usuario;
import Modelo.Seleccion;
import Modelo.Evento;
import java.sql.Blob;
import Modelo.Cls_Satisfaccion;

/**
 *
 * @author santi
 */
public class Contr_Consultar {

    /**Se declaran variables con metodo set y get*/
    String Mensaje;

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    /*Metodo para buscar los datos del usuario*/
    public String[] BuscarDatosUsuario(String Codigo) {
        Usuario usu = new Usuario();
        String[] Datos = usu.getDatosUsuario(Codigo);
        return Datos;
    }

    /*Buscar datos de tipo de usuarios*/
    //pendiente por pasar a ajax
    public String[][] BuscarDatosTipoUsuariosTodos() {
        Tipo_Usuario Tusu = new Tipo_Usuario();
        String[][] Datos = Tusu.BuscarDatosTipoUsuarioTodos();
        return Datos;
    }

    /*Buscar los datos de una empresa*/
    public String[][] BuscarDatosEmpresa() {
        Usuario usu = new Usuario();
        String[][] Datos = usu.BuscarDatosEmpresa();
        return Datos;
    }

    /*MEtodo apra buscar el blob de la seleccion*/
    public Blob BuscarImagenes(String Codigo) {
        Seleccion sel = new Seleccion();
        Blob Datos = sel.getImagenSeleccion(Codigo);
        return Datos;
    }

    /*MEtodo apra buscar el blob del evento*/
    public Blob BuscarImagenesEvento(String Codigo) {
        Evento eve = new Evento();
        Blob Datos = eve.getImagenEvento(Codigo);
        return Datos;
    }

    /*MEtodo para obtener la cantidad de usuarios empresa pendiente*/
    public String getCantidadPendientes() {
        Usuario usu = new Usuario();
        String Dato = usu.getCantidadPendientes();
        return Dato;
    }

    /*MEtodo para obtener la cantidad de eventos pendientes*/
    //pendiente por poner en ajax
    public int getCantidadEventosPendientes() {
        Evento eve = new Evento();
        int Dato = eve.CantidadEventoPendiente();
        return Dato;
    }

    /*Metodo para buscar los datos de los proximos eventos*/
    //pendiente por poner en ajax
    public String[][] getBuscarDatosEventosProximos() {
        Evento eve = new Evento();
        String[][] Datos = eve.BuscarDatosEventosProximos();
        return Datos;
    }

    /*Metodo apra buscar los datos de los eventos destacados*/
    //pendiente por poner en ajax
    public String[][] getBuscarDatosEventosDestacados() {
        Evento eve = new Evento();
        String[][] Datos = eve.BuscarDatosEventosDestacados();
        return Datos;
    }

    /*MEtodo para buscar los comentarios aleatorios*/
    //pendiente por poner en ajax
    public String[][] getBuscarComentariosAleatorios() {
        Cls_Satisfaccion sat = new Cls_Satisfaccion();
        String[][] Datos = sat.BuscarComentariosAleatorios();
        return Datos;
    }

    /*Metodo para buscar los datos de los eventos comentados*/
    //pendiente por poner en ajax
    public String[][] getBuscarDatosEventosComentado() {
        Evento eve = new Evento();
        String[][] Datos = eve.BuscarDatosEventosComentado();
        return Datos;
    }

    /*MEtodo para buscar los datos de un evetno*/
    public String[] getBuscarDatosDetalladosEvento(String CodigoEvento) {
        Evento eve = new Evento();
        String[] Datos = eve.BuscarDatosDetalladosEventos(CodigoEvento);
        return Datos;
    }
    
    /**Metodo pra buscar los datos de un evento pendiente*/
    public String[] getBuscarDatosDetalladosEventoPendiente(String CodigoEvento) {
        Evento eve = new Evento();
        String[] Datos = eve.BuscarDatosDetalladosEventosPendiente(CodigoEvento);
        return Datos;
    }

    /*Metodo para obtener la calificacion de un evento*/
    public int[] getCalificacionEvento(String CodigoEvento) {
        Evento eve = new Evento();
        int[] Datos = eve.getCalificacionEvento(CodigoEvento);
        return Datos;
    }

    /**Metodo apra comprobar la calificacion y comentarios de un evetno*/
    public boolean getComprobacionCalificacionYComentario(String CodigoEvento, String CodigoUsuario, String Tipo) {
        boolean b;
        Cls_Satisfaccion sat = new Cls_Satisfaccion();
        b = sat.ComprobarCalificacionRegistrada(CodigoEvento, CodigoUsuario, Tipo);
        return b;
    }

    /*Metodo para obtener los comentarios o calificacion de un evento*/
    public String getComentarioOCalificacion(String tipo, String CodigoUsuario) {
        Cls_Satisfaccion sat = new Cls_Satisfaccion();
        String dato = sat.getComentarioOCalificacion(tipo, CodigoUsuario);
        return dato;
    }
}
