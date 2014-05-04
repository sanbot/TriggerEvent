/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Modelo.Mensajeria;
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

    public String[][] BuscarDatosSeleccion() {
        Seleccion sel = new Seleccion();
        String Datos[][] = sel.getDatosSeleccion();
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

    public String[][] getGustosNuevos(String Codigo) {
        Seleccion sel = new Seleccion();
        String Datos[][] = sel.getGustosNuevos(Codigo);
        return Datos;
    }

    public String[][] getClasifiacionNuevos(String Codigo) {
        Seleccion sel = new Seleccion();
        String Datos[][] = sel.getClasificacionNuevos(Codigo);
        return Datos;
    }

    public String[][] getMisGustos(String Codigo) {
        Seleccion sel = new Seleccion();
        String Datos[][] = sel.getMisGustos(Codigo);
        return Datos;
    }

    public String[][] getClasificacionEvento(String Codigo) {
        Seleccion sel = new Seleccion();
        String Datos[][] = sel.getClasificacionEvento(Codigo);
        return Datos;
    }

    public boolean AddRemoveGustos(String Codigo, String CodigoUsuario, String Accion) {
        Seleccion sel = new Seleccion();
        boolean b;
        if (Accion.equals("Nuevo")) {
            b = sel.AddGusto(Codigo, CodigoUsuario);
            if (b) {
                this.setMensaje("Se agrego el gusto satisfactoriamente.");
            } else {
                this.setMensaje("Ocurrió un error al agregar el gusto de su cuenta. Estamos trabajando para solucionar este problema.");
            }
        } else {
            b = sel.CantidadGustosAmbientesPreRemove(Codigo, CodigoUsuario);
            if (b) {
                b = sel.RemoveGusto(Codigo, CodigoUsuario);
                if (b) {
                    this.setMensaje("Se quito el gusto de tus gustos existosamente.");
                } else {
                    this.setMensaje("Ocurrió un error al remover el gusto de su cuenta. Estamos trabajando para solucionar este problema.");
                }
            } else {
                this.setMensaje(sel.getMensaje());
            }
        }
        return b;
    }

    public boolean AprobarDesaprobarEvento(String CodigoEvento) {
        Evento eve = new Evento();
        Mensajeria sms = new Mensajeria();
        boolean b;

        b = eve.setCambioEstadoEvento(CodigoEvento, "Aprobado");
        if (b) {
            String[] Datos = eve.BuscarEventoParaMensaje(CodigoEvento);
            if (sms.EnviarMensajeCambioEstadoEvento(Datos, "Aprobado")) {
                this.setMensaje("Se aprobó el evento satisfactoriamente.");
            } else {
                this.setMensaje("Se aprobó el evento, pero no se logró enviar la notificación al correo electrónico de la empresa.");
            }
        } else {
            this.setMensaje("Ocurrió un error al agregar el gusto a su cuenta. Estamos trabajando para solucionar este problema.");
        }

        return b;
    }

    public boolean AprobarDesaprobarSeleccion(String CodigoSeleccion, String Accion) {
        Seleccion sel = new Seleccion();
        boolean b;
        if (Accion.equals("Aprobar")) {
            b = sel.AprobarSeleccion(CodigoSeleccion);
            if (b) {
                this.setMensaje("Se aprobó el gusto/ambiente satisfactoriamente.");
            } else {
                this.setMensaje(sel.getMensaje());
            }
        } else {
            b = sel.CantidadUsoAmbienteGusto(CodigoSeleccion);
            if (b) {
                b = sel.DesaprobarSeleccion(CodigoSeleccion);
                if (b) {
                    this.setMensaje("El gusto o ambiente ha sido desaprobado satisfactoriamente.");
                } else {
                    this.setMensaje(sel.getMensaje());
                }
            } else {
                this.setMensaje("No se puede desaprobar, porque hay usuarios o eventos usando este ambiente o gusto.");
            }
        }
        return b;
    }

    public boolean AddClasificacionEvento(String CodigoSeleccion, String Accion, String CodigoEvento) {
        Seleccion sel = new Seleccion();
        boolean b = false;
        if (Accion.equals("Nuevo")) {
            b = sel.AddClasificacionEvento(CodigoSeleccion, CodigoEvento);
            if (b) {
                this.setMensaje("Se agregó el gusto/ambiente a la calificación satisfactoriamente.");
                if (sel.ComprobarRegistroCompletoUSuario(CodigoEvento)) {
                    Evento eve = new Evento();
                    if (eve.setEstadoPendiente(CodigoEvento)) {
                        String mensaje = this.getMensaje();
                        mensaje += " Se han cumplido los requisitos mínimos para el registro y el evento está en la lista de espera por aprobación.";
                        this.setMensaje(mensaje);
                    }

                }
            } else {
                this.setMensaje(sel.getMensaje());
            }
        }
        return b;
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

    public int getCantidadComentariosEventos(String CodigoEvento) {
        Cls_Satisfaccion sat = new Cls_Satisfaccion();
        int row = sat.getCantidadComentariosEvento(CodigoEvento);
        return row;
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

    public boolean getComprobacionRegistroEvento(String CodigoEvento) {
        boolean b;
        Seleccion sel = new Seleccion();
        b = sel.ComprobarRegistroCompletoUSuario(CodigoEvento);
        return b;
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
