/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cls_Evento;

/**
 *
 * @author santi
 */
public class Contr_Consultar {
    
    /**Metodo pra buscar los datos de un evento pendiente*/
    public String[] getBuscarDatosDetalladosEventoPendiente(String CodigoEvento) {
        Cls_Evento eve = new Cls_Evento();
        String[] Datos = eve.BuscarDatosDetalladosEventosPendiente(CodigoEvento);
        return Datos;
    }
}
