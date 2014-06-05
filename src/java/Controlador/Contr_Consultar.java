/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Evento;
import Modelo.Cls_Satisfaccion;

/**
 *
 * @author santi
 */
public class Contr_Consultar {
    
    /**Metodo pra buscar los datos de un evento pendiente*/
    public String[] getBuscarDatosDetalladosEventoPendiente(String CodigoEvento) {
        Evento eve = new Evento();
        String[] Datos = eve.BuscarDatosDetalladosEventosPendiente(CodigoEvento);
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
    public String getComentarioOCalificacion(String tipo, String CodigoUsuario, String codigoEvento) {
        Cls_Satisfaccion sat = new Cls_Satisfaccion();
        String dato = sat.getComentarioOCalificacion(tipo, CodigoUsuario, codigoEvento);
        return dato;
    }
}
