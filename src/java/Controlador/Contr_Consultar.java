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

    String Mensaje;

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }


    public String[][] BuscarDatosUsuariosPendientes() {
        Usuario usu = new Usuario();
        String[][] Datos = usu.BuscarDatosUsuarioPendientes();

        return Datos;
    }

    public String[] BuscarDatosUsuario(String Codigo) {
        Usuario usu = new Usuario();
        String[] Datos = usu.getDatosUsuario(Codigo);

        return Datos;
    }

    public String[][] BuscarDatosTipoUsuariosTodos() {
        Tipo_Usuario Tusu = new Tipo_Usuario();
        String[][] Datos = Tusu.BuscarDatosTipoUsuarioTodos();

        return Datos;
    }

    public String[][] BuscarDatosEmpresa() {
        Usuario usu = new Usuario();
        String[][] Datos = usu.BuscarDatosEmpresa();

        return Datos;
    }

    public Blob BuscarImagenes(String Codigo) {
        Seleccion sel = new Seleccion();
        Blob Datos = sel.getImagenSeleccion(Codigo);
        return Datos;
    }

    public Blob BuscarImagenesEvento(String Codigo) {
        Evento eve = new Evento();
        Blob Datos = eve.getImagenEvento(Codigo);
        return Datos;
    }

    public String[][] getClasificacionEvento(String Codigo) {
        Seleccion sel = new Seleccion();
        String Datos[][] = sel.getClasificacionEvento(Codigo);
        return Datos;
    }

    public String getCantidadPendientes() {
        Usuario usu = new Usuario();
        String Dato = usu.getCantidadPendientes();
        return Dato;
    }

    public int getCantidadEventosPendientes() {
        Evento eve = new Evento();
        int Dato = eve.CantidadEventoPendiente();
        return Dato;
    }

    public String[][] getBuscarDatosPrincipalesEvento() {
        Evento eve = new Evento();
        String[][] Datos = eve.BuscarDatosPrincipalesEventos();
        return Datos;
    }

    public String[][] getBuscarDatosEventosProximos() {
        Evento eve = new Evento();
        String[][] Datos = eve.BuscarDatosEventosProximos();
        return Datos;
    }

    public String[][] getBuscarDatosEventosDestacados() {
        Evento eve = new Evento();
        String[][] Datos = eve.BuscarDatosEventosDestacados();
        return Datos;
    }

    public String[][] getBuscarComentariosAleatorios() {
        Cls_Satisfaccion sat = new Cls_Satisfaccion();
        String[][] Datos = sat.BuscarComentariosAleatorios();
        return Datos;
    }

    public String[][] getBuscarDatosEventosComentado() {
        Evento eve = new Evento();
        String[][] Datos = eve.BuscarDatosEventosComentado();
        return Datos;
    }

    public String[] getBuscarDatosDetalladosEvento(String CodigoEvento) {
        Evento eve = new Evento();
        String[] Datos = eve.BuscarDatosDetalladosEventos(CodigoEvento);
        return Datos;
    }

    public int[] getCalificacionEvento(String CodigoEvento) {
        Evento eve = new Evento();
        int[] Datos = eve.getCalificacionEvento(CodigoEvento);
        return Datos;
    }

    public String[][] getBuscarDatosMisEventos(String nit) {
        Evento eve = new Evento();
        String[][] Datos = eve.BuscarDatosMisEventos(nit);
        return Datos;
    }

    public String[][] getBuscarDatosPrincipalesEventoPendiente() {
        Evento eve = new Evento();
        String[][] Datos = eve.BuscarDatosPrincipalesEventosPendientes();
        return Datos;
    }

    public boolean getComprobacionCalificacionYComentario(String CodigoEvento, String CodigoUsuario, String Tipo) {
        boolean b;
        Cls_Satisfaccion sat = new Cls_Satisfaccion();
        b = sat.ComprobarCalificacionRegistrada(CodigoEvento, CodigoUsuario, Tipo);
        return b;
    }

    public String getComentarioOCalificacion(String tipo, String CodigoUsuario) {
        Cls_Satisfaccion sat = new Cls_Satisfaccion();
        String dato = sat.getComentarioOCalificacion(tipo, CodigoUsuario);
        return dato;
    }
}
