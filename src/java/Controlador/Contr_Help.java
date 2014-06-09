/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cls_Ciudad;
import Modelo.Cls_Satisfaccion;
import Modelo.Cls_Departamento;
import Modelo.Cls_Evento;
import Modelo.Cls_Mensajeria;
import Modelo.Cls_Seleccion;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sun.misc.BASE64Encoder;

/**
 *
 * @author ADSI
 */
@WebServlet(name = "Contr_Help", urlPatterns = {"/Contr_Help"})
public class Contr_Help extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*Se detalla el contenido que tendra el servlet*/
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        /*Se declaran las variables necesarias*/
        PrintWriter out = response.getWriter();
        try {
            /*Se evelua la peticion que se realizo al servidor*/
            if (request.getParameter("accion").equals("vermas")) {
                /*Si se realiza la peticion de ver mas comentarios*/
                /*Se declaran las variables necesarias*/
                Cls_Satisfaccion sat = new Cls_Satisfaccion();
                JSONObject obj = new JSONObject();
                /*Se obtienen los datos del metodo de modelo de los comentarios, se le pone un
                 limite y una cantidad para solo traer los datos que faltan*/
                String[][] Datos = sat.BuscarComentariosEvento(request.getParameter("codigoevento"), Integer.parseInt(request.getParameter("limite")), Integer.parseInt(request.getParameter("cantidad")));
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("usuario", row[0]);
                    ob.put("empresa", row[1]);
                    ob.put("comentario", row[2]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                /*Se imprimen los datos*/
                out.println(obj);
            } else if (request.getParameter("accion").equals("total")) {
                /*Si se realiza la peticion de total de  comentarios*/
                /*Se declaran las variables necesarias*/
                Cls_Satisfaccion sat = new Cls_Satisfaccion();
                /*Se obtiene el total de comentarios de un evento, del metodo de modelo*/
                int row = sat.getCantidadComentariosEvento(request.getParameter("codigoevento"));
                /*Se imprime el resultado*/
                out.println(row);
            } else if (request.getParameter("accion").equals("totalevento")) {
                /*Si se realiza la peticion de total de eventos*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();

                /*Se obtiene el todal de comentarios de un evento, del metodo de modelo*/
                int row = eve.CantidadRegistroEvento();
                /*Se imprime el resultado*/
                out.println(row - 1);

            } else if (request.getParameter("accion").equals("totaleventorecomendado")) {
                /*Si se realiza la peticion de total de eventos recomendados*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();
                String codigoUsuario = request.getParameter("idusuario");
                int row = eve.getcantidadeventosRecomendados(codigoUsuario);
                /*Se imprime el resultado*/
                out.println(row);
            } else if (request.getParameter("accion").equals("getciudad")) {
                /*Si se realiza la peticion de ver todas las ciudades*/
                /*Se declaran las variables necesarias*/
                Cls_Ciudad ciu = new Cls_Ciudad();
                JSONObject obj = new JSONObject();
                /*Se obtiene los datos de la ciudad, del metodo de modelo*/
                String[][] Datos = ciu.BuscarDatosCiudadTodos(request.getParameter("codigodepartamento"));

                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("ciudad", row[1]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                /*Se imprime el resultado*/
                out.println(obj);
            } else if (request.getParameter("accion").equals("getdepartamentos")) {
                /*Si se realiza la peticion de ver todos los departamentos*/
                /*Se declaran las variables necesarias*/
                Cls_Departamento dep = new Cls_Departamento();
                JSONObject obj = new JSONObject();
                /*Se obtiene los datos de los departamentos, del metodo de modelo*/
                String[][] Datos = dep.BuscarDatosDepartamentoTodos();
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("departamento", row[1]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                /*Se imprime el resultado*/
                out.println(obj);
            } else if (request.getParameter("accion").equals("getciudades")) {
                /*Si se realiza la peticion de ver todos los datos de la ciudad*/
                /*Se declaran las variables necesarias*/
                Cls_Ciudad ciu = new Cls_Ciudad();
                JSONObject obj = new JSONObject();
                /*Se obtiene los datos de la ciudad, del metodo de modelo*/
                String[][] Datos = ciu.BuscarDatosCiudadTodos();
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("ciudad", row[1]);
                    ob.put("iddepto", row[2]);
                    ob.put("nomdepto", row[3]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                /*Se imprime el resultado*/
                out.println(obj);
            } else if (request.getParameter("accion").equals("aprobarevento")) {
                /*Si se realiza la peticion de aprobar un evento*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();
                Cls_Mensajeria sms = new Cls_Mensajeria();
                JSONObject obj = new JSONObject();
                JSONObject ob = new JSONObject();
                String Codigo = request.getParameter("codigoevento");
                /*Se cambia el estado del evento, en el metodo de modelo*/
                boolean b = eve.setCambioEstadoEvento(Codigo, "Aprobado");
                if (b) {
                    String[] Datos = eve.BuscarEventoParaMensaje(Codigo);
                    if (sms.EnviarMensajeCambioEstadoEvento(Datos, "Aprobado")) {
                        /*Se encodifican los datos en JSON*/
                        ob.put("mensaje", "Se aprobó el evento satisfactoriamente.");
                        ob.put("tipomensaje", "Dio");
                    } else {
                        /*Se encodifican los datos en JSON*/
                        ob.put("mensaje", "Se aprobó el evento, pero no se logró enviar la notificación al correo electrónico de la empresa.");
                        ob.put("tipomensaje", "NODio");
                    }
                } else {
                    ob.put("mensaje", "Ocurrió un error al agregar el gusto a su cuenta. Estamos trabajando para solucionar este problema.");
                    ob.put("tipomensaje", "NODio");
                }
                obj.put("1", ob);
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("geteventospendientes")) {
                /*Si se realiza la peticion de obtener los datos de los eventos pendientes*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();
                JSONObject obj = new JSONObject();
                /*Se obtienen los datos del evento, en el metodo de modelo*/
                String[][] Datos = eve.BuscarDatosPrincipalesEventosPendientes();
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("fecha", row[2]);
                    ob.put("creador", row[3]);
                    ob.put("ciudad", row[4]);
                    ob.put("hora", row[5]);
                    ob.put("image", row[0] + row[6]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("getmiseventos")) {
                /*Si se realiza la peticion de ver todos los eventos de una empresa*/
                /*Se declaran las variables necesarias*/
                String nit = request.getParameter("nit");
                Cls_Evento eve = new Cls_Evento();
                JSONObject obj = new JSONObject();
                /*Se obtienen los datos de los eventos de una empresa, en el metodo de modelo*/
                String[][] Datos = eve.BuscarDatosMisEventos(nit);
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("fecha", row[2]);
                    ob.put("creador", row[3]);
                    ob.put("ciudad", row[4]);
                    ob.put("estado", row[5]);
                    ob.put("hora", row[6]);
                    ob.put("image", row[0] + row[7]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("getmiseventostodos")) {
                /*Si se realiza la peticion de obtener todos los datos de los eventos propios*/
                /*Se declaran las variables necesarias*/
                String nit = request.getParameter("nit");
                Cls_Evento eve = new Cls_Evento();
                JSONObject obj = new JSONObject();
                String[][] Datos = eve.BuscarDatosTodosEventos();
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("fecha", row[2]);
                    ob.put("creador", row[3]);
                    ob.put("ciudad", row[4]);
                    ob.put("estado", row[5]);
                    ob.put("hora", row[6]);
                    ob.put("image", row[0] + row[7]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("agregargusto")) {
                /*Si se realiza la peticion de agregar un gusto*/
                /*Se declaran las variables necesarias*/
                Cls_Seleccion sel = new Cls_Seleccion();
                JSONObject obj = new JSONObject();
                String Codigo = request.getParameter("codigo");
                String CodigoUsuario = request.getParameter("idusuario");
                boolean b;
                /*Se agrega el gusto, en el metodo de modelo*/
                b = sel.AddGusto(Codigo, CodigoUsuario);
                if (b) {
                    /*Se encodifican los datos en JSON*/
                    obj.put("1", "Se agregó el gusto satisfactoriamente.");
                } else {
                    /*Se encodifican los datos en JSON*/
                    obj.put("0", "Ocurrió un error al agregar el gusto de su cuenta. Estamos trabajando para solucionar este problema.");
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("removergusto")) {
                /*Si se realiza la peticion de remover un gusto*/
                /*Se declaran las variables necesarias*/
                Cls_Seleccion sel = new Cls_Seleccion();
                JSONObject obj = new JSONObject();
                String Codigo = request.getParameter("codigo");
                String CodigoUsuario = request.getParameter("idusuario");
                boolean b;
                /*Se verifica que se pueda remover un gusto, en el metodo de modelo*/
                b = sel.CantidadGustosAmbientesPreRemove(Codigo, CodigoUsuario);
                if (b) {
                    /*Se remueve el gusto, en el metodo de modelo*/
                    b = sel.RemoveGusto(Codigo, CodigoUsuario);
                    if (b) {
                        /*Se encodifican los datos en JSON*/
                        obj.put("1", "Se quitó el gusto de tus gustos existosamente.");
                    } else {
                        /*Se encodifican los datos en JSON*/
                        obj.put("0", "Ocurrió un error al remover el gusto de su cuenta. Estamos trabajando para solucionar este problema.");
                    }
                } else {
                    obj.put("0", sel.getMensaje());
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("getgustosnuevos")) {
                /*Si se realiza la peticion de obtener los gustos nuevos*/
                /*Se declaran las variables necesarias*/
                String Codigo = request.getParameter("codigo");
                Cls_Seleccion sel = new Cls_Seleccion();
                JSONObject obj = new JSONObject();
                /*Se obtienen los datos de los gustos nuevos, en el metodo de modelo*/
                String[][] Datos = sel.getGustosNuevos(Codigo);
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("tipo", row[2]);
                    ob.put("image", row[0] + row[3]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("getgustos")) {
                /*Si se realiza la peticion de obtener los gustos del usuario*/
                /*Se declaran las variables necesarias*/
                String Codigo = request.getParameter("codigo");
                Cls_Seleccion sel = new Cls_Seleccion();
                JSONObject obj = new JSONObject();
                /*Se obtienen los datos de los gustos de un usuario, en el metodo de modelo*/
                String[][] Datos = sel.getMisGustos(Codigo);
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("tipo", row[2]);
                    ob.put("image", row[0] + row[3]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("getseleccion")) {
                /*Si se realiza la peticion de obtener los datos de la seleccion*/
                /*Se declaran las variables necesarias*/
                Cls_Seleccion sel = new Cls_Seleccion();
                JSONObject obj = new JSONObject();
                /*Se obtienen los datos de la seleccion, en el metodo de modelo*/
                String[][] Datos = sel.getDatosSeleccion();
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("tipo", row[2]);
                    ob.put("estado", row[3]);
                    ob.put("image", row[0] + row[4]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("getclasificacion")) {
                /*Si se realiza la peticion de obtener la califiacion de un evento*/
                /*Se declaran las variables necesarias*/
                String Codigo = request.getParameter("idevento");
                Cls_Seleccion sel = new Cls_Seleccion();
                JSONObject obj = new JSONObject();
                /*Se obtienen los datos de la clasificacion de un evento, en el metodo de modelo*/
                String Datos[][] = sel.getClasificacionEvento(Codigo);
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("tipo", row[2]);
                    ob.put("image", row[0] + row[3]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("getclasificacionnuevos")) {
                /*Si se realiza la peticion de obtener nuevos datos para la calificacion de un evento*/
                /*Se declaran las variables necesarias*/
                Cls_Seleccion sel = new Cls_Seleccion();
                String Codigo = request.getParameter("idevento");
                JSONObject obj = new JSONObject();
                /*Se obtienen los nuevos datos para la clasficicacion de un evento, en el metodo de modelo*/
                String[][] Datos = sel.getClasificacionNuevos(Codigo);
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("tipo", row[2]);
                    ob.put("image", row[0] + row[3]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("aprobarseleccion")) {
                /*Si se realiza la peticion de aprobar una seleccion*/
                /*Se declaran las variables necesarias*/
                JSONObject obj = new JSONObject();
                Cls_Seleccion sel = new Cls_Seleccion();
                String CodigoSeleccion = request.getParameter("idseleccion");
                boolean b;
                /*Se aprueba la seleccion, en el metodo de modelo*/
                b = sel.AprobarSeleccion(CodigoSeleccion);
                if (b) {
                    obj.put("1", "Se aprobó el gusto/ambiente satisfactoriamente.");
                } else {
                    obj.put("0", sel.getMensaje());
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("desaprobarseleccion")) {
                /*Si se realiza la peticion de desaprobar una seleccion*/
                /*Se declaran las variables necesarias*/
                Cls_Seleccion sel = new Cls_Seleccion();
                JSONObject obj = new JSONObject();
                String CodigoSeleccion = request.getParameter("idseleccion");
                /*Se verifica que los usuarios no tengan este gustos o ambientes seleccionado, en el metodo de modelo*/
                boolean b = sel.CantidadUsoAmbienteGusto(CodigoSeleccion);
                if (b) {
                    /*Se desapruba la seleccion, en el metodo de modelo*/
                    b = sel.DesaprobarSeleccion(CodigoSeleccion);
                    if (b) {
                        /*Se encodifican los datos en JSON*/
                        obj.put("1", "El gusto o ambiente ha sido desaprobado satisfactoriamente.");
                    } else {
                        /*Se encodifican los datos en JSON*/
                        obj.put("0", sel.getMensaje());
                    }
                } else {
                    obj.put("0", "No se puede desaprobar, porque hay usuarios o eventos usando este ambiente o gusto.");
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("agregarclasificacion")) {
                /*Si se realiza la peticion de agregar una clasificacion a un evento*/
                /*Se declaran las variables necesarias*/
                Cls_Seleccion sel = new Cls_Seleccion();
                JSONObject obj = new JSONObject();
                String CodigoSeleccion = request.getParameter("idseleccion");
                String CodigoEvento = request.getParameter("idevento");
                /*Se agrega la clasficacion de un evento, en el metodo de modelo*/
                boolean b = sel.AddClasificacionEvento(CodigoSeleccion, CodigoEvento);
                if (b) {
                    /*Se encodifican los datos en JSON*/
                    obj.put("1", "Se agregó el gusto/ambiente a la calificación satisfactoriamente.");
                    if (sel.ComprobarRegistroCompletoUSuario(CodigoEvento)) {
                        Cls_Evento eve = new Cls_Evento();
                        if (eve.setEstadoPendiente(CodigoEvento)) {
                            /*Se encodifican los datos en JSON*/
                            String mensaje = "Se han cumplido los requisitos mínimos para el registro y el evento está en la lista de espera por aprobación.";
                            obj.put("1", mensaje);
                            obj.put("2", "true");
                        }
                    }
                } else {
                    /*Se encodifican los datos en JSON*/
                    obj.put("1", sel.getMensaje());
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("comprobarregistroevento")) {
                /*Si se realiza la peticion de comprobar el registro de un evento*/
                /*Se declaran las variables necesarias*/
                Cls_Seleccion sel = new Cls_Seleccion();
                String CodigoEvento = request.getParameter("idevento");
                /*Se comprueba el registro de un evento, en el metodo de modelo*/
                boolean dato = sel.ComprobarRegistroCompletoUSuario(CodigoEvento);
                /*Se imprime el resultado*/
                out.print(dato);
            } else if (request.getParameter("accion").equals("eventodatosprincipales")) {
                /*Si se realiza la peticion de obtener todos los datos de los eventos principales*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();
                JSONObject obj = new JSONObject();
                int limite = Integer.parseInt(request.getParameter("limite"));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                /*Se ontienen todos los datos de los eventos principales con limtie y cantidad, 
                 en el metodo de modelo*/
                String[][] Datos = eve.BuscarDatosPrincipalesEventos(limite, cantidad);
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("fecha", row[2]);
                    ob.put("creador", row[3]);
                    ob.put("ciudad", row[4]);
                    ob.put("hora", row[5]);
                    ob.put("calificacion", row[6]);
                    ob.put("comentario", row[7]);
                    ob.put("image", row[0] + row[8]);
                    obj.put(Integer.toString(i), ob);
                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("eventodatosrecomendados")) {
                /*Si se realiza la peticion de obtener los datos de los eventos recomendados*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();
                JSONObject obj = new JSONObject();
                int limite = Integer.parseInt(request.getParameter("limite"));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                String codigoUsuario = request.getParameter("idusuario");
                /*Se obtienen los datos de los eventos recomendados, en el metodo de modelo*/
                String[][] Datos = eve.geteventosRecomendados(codigoUsuario, limite, cantidad);
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("fecha", row[2]);
                    ob.put("creador", row[3]);
                    ob.put("ciudad", row[4]);
                    ob.put("hora", row[5]);
                    ob.put("calificacion", row[6]);
                    ob.put("comentario", row[7]);
                    ob.put("image", row[0] + row[8]);
                    obj.put(Integer.toString(i), ob);
                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("ubicacioneventos")) {
                /*Si se realiza la peticion de obtener los datos de la ubicacion de los eventos*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();
                JSONObject obj = new JSONObject();
                /*Se obtienen los datos de la ubicacion de los eventos, en el metodo de modelo*/
                String[][] Datos = eve.getubicacioneventos();
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("latitud", row[2]);
                    ob.put("longitud", row[3]);
                    obj.put(Integer.toString(i), ob);
                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("datos_evento_detalle")) {
                /*Si se realiza la peticion de obtener los datos de la ubicacion de los eventos*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();
                String codigo = request.getParameter("idevento");
                JSONObject obj = new JSONObject();
                /*Se obtienen los datos de la ubicacion de los eventos, en el metodo de modelo*/
                String[] row = eve.BuscarDatosDetalladosEventos(codigo);
                /*Se encodifican los datos en JSON*/
                JSONObject ob = new JSONObject();

                ob.put("empresa", row[0]);
                ob.put("evento", row[1]);
                ob.put("rango", row[2]);
                ob.put("codigo_departamento", row[3]);
                ob.put("nombre_departamento", row[4]);
                ob.put("codigo_ciudad", row[5]);
                ob.put("nombre_ciudad", row[6]);
                ob.put("direccion", row[7]);
                ob.put("fecha", row[8]);
                ob.put("descripcion", row[9]);
                ob.put("hora", row[10]);
                ob.put("latitud", row[11]);
                ob.put("longitud", row[12]);
                ob.put("imagen", codigo + row[13]);

                obj.put(1, ob);
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("eventos_destacados")) {
                Cls_Evento eve = new Cls_Evento();
                String Datos[][] = eve.BuscarDatosEventosDestacados();
                JSONObject obj = new JSONObject();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("fecha", row[2]);
                    ob.put("empresa", row[3]);
                    ob.put("ciudad", row[4]);
                    ob.put("calificacion", row[5]);
                    ob.put("hora", row[6]);
                    ob.put("comentario", row[7]);
                    ob.put("imagen", row[0] + row[8]);
                    obj.put(i, ob);
                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("eventos_comentados")) {
                Cls_Evento eve = new Cls_Evento();
                String Datos[][] = eve.BuscarDatosEventosComentado();
                JSONObject obj = new JSONObject();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("fecha", row[2]);
                    ob.put("empresa", row[3]);
                    ob.put("ciudad", row[4]);
                    ob.put("comentario", row[5]);
                    ob.put("hora", row[6]);
                    ob.put("calificacion", row[7]);
                    ob.put("imagen", row[0] + row[8]);
                    obj.put(i, ob);
                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("eventos_proximos")) {
                Cls_Evento eve = new Cls_Evento();
                String Datos[][] = eve.BuscarDatosEventosProximos();
                JSONObject obj = new JSONObject();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("fecha", row[2]);
                    ob.put("empresa", row[3]);
                    ob.put("ciudad", row[4]);
                    ob.put("comentario", row[7]);
                    ob.put("hora", row[5]);
                    ob.put("calificacion", row[6]);
                    ob.put("imagen", row[0] + row[8]);
                    obj.put(i, ob);
                    i++;
                }
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("cantidad_evento_pendiente")) {
                Cls_Evento eve = new Cls_Evento();
                int Dato = eve.CantidadEventoPendiente();
                out.print(Dato);
            } else if (request.getParameter("accion").equals("ciudades_android")) {
                /*Si se realiza la peticion de ver todas las ciudades*/
                /*Se declaran las variables necesarias*/
                Cls_Ciudad ciu = new Cls_Ciudad();
                JSONArray list = new JSONArray();
                /*Se obtiene los datos de la ciudad, del metodo de modelo*/
                String[][] Datos = ciu.BuscarDatosCiudadTodos(request.getParameter("codigo_dept"));

                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("ciudad", row[1]);
                    list.add(ob);

                    i++;
                }
                /*Se imprime el resultado*/
                out.println(list);
            } else if (request.getParameter("accion").equals("departamentos_android")) {
                /*Si se realiza la peticion de ver todos los departamentos*/
                /*Se declaran las variables necesarias*/
                Cls_Departamento dep = new Cls_Departamento();
                JSONArray list = new JSONArray();
                /*Se obtiene los datos de los departamentos, del metodo de modelo*/
                String[][] Datos = dep.BuscarDatosDepartamentoAndroid();
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("departamento", row[1]);
                    list.add(ob);

                    i++;
                }
                /*Se imprime el resultado*/
                out.println(list);
            } else if (request.getParameter("accion").equals("gustos_nuevos_android")) {
                /*Si se realiza la peticion de obtener los gustos nuevos*/
                /*Se declaran las variables necesarias*/
                String Codigo = request.getParameter("codigo");
                int Cantidad = Integer.parseInt(request.getParameter("cantidad"));
                Cls_Seleccion sel = new Cls_Seleccion();
                JSONObject obj = new JSONObject();
                JSONArray list = new JSONArray();
                BufferedImage img;

                String urlimgservidor = this.getServletContext().getRealPath("/Libs/Customs/images/Seleccion");
                /*Se obtienen los datos de los gustos nuevos, en el metodo de modelo*/
                String[][] Datos = sel.getGustosNuevosAndroid(Codigo, Cantidad);
                int i = 0;
                try {
                    /*Se encodifican los datos en JSON*/
                    for (String row[] : Datos) {
                        JSONObject ob = new JSONObject();

                        img = ImageIO.read(new File(urlimgservidor + "/" + row[0] + row[3]));
                        String typeimg = row[3].substring(1, row[3].length());

                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        ImageIO.write(img, typeimg, bos);
                        byte[] imageBytes = bos.toByteArray();

                        BASE64Encoder encoder = new BASE64Encoder();
                        String imageSTring = encoder.encode(imageBytes);

                        bos.close();

                        ob.put("Codigo", row[0]);
                        ob.put("Nombre", row[1]);
                        ob.put("Tipo", row[2]);
                        ob.put("Imagen", imageSTring);
                        list.add(ob);

                        i++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                obj.put("gustos", list);
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("mis_gustos_android")) {

                /*Si se realiza la peticion de obtener los gustos del usuario*/
                /*Se declaran las variables necesarias*/
                String Codigo = request.getParameter("codigo");
                int Cantidad = Integer.parseInt(request.getParameter("cantidad"));
                Cls_Seleccion sel = new Cls_Seleccion();
                JSONObject obj = new JSONObject();
                JSONArray list = new JSONArray();

                BufferedImage img;

                String urlimgservidor = this.getServletContext().getRealPath("/Libs/Customs/images/Seleccion");

                /*Se obtienen los datos de los gustos de un usuario, en el metodo de modelo*/
                String[][] Datos = sel.getMisGustosAndroid(Codigo, Cantidad);
                int i = 0;

                try {
                    /*Se encodifican los datos en JSON*/
                    for (String row[] : Datos) {

                        JSONObject ob = new JSONObject();

                        img = ImageIO.read(new File(urlimgservidor + "/" + row[0] + row[3]));
                        String typeimg = row[3].substring(1, row[3].length());

                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        ImageIO.write(img, typeimg, bos);
                        byte[] imageBytes = bos.toByteArray();

                        BASE64Encoder encoder = new BASE64Encoder();
                        String imageSTring = encoder.encode(imageBytes);

                        bos.close();

                        ob.put("Codigo", row[0]);
                        ob.put("Nombre", row[1]);
                        ob.put("Tipo", row[2]);
                        ob.put("Imagen", imageSTring);
                        list.add(ob);

                        i++;
                    }
                    obj.put("gustos", list);
                    /*Se imprime el resultado*/
                    out.print(obj);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (request.getParameter("accion").equals("cantidad_gustos_nuevos_android")) {
                /*Si se realiza la peticion de obtener la cantidad de gustos que puede tener el usuario*/
                /*Se declaran las variables necesarias*/
                String Codigo = request.getParameter("codigo");
                Cls_Seleccion sel = new Cls_Seleccion();
                /*Se obtienen los datos de la cantidad de gustos de un usuario, en el método de modelo*/
                int Datos = sel.CantidadGustosNuevosAndroid(Codigo);
                /*Se imprime el resultado*/
                out.print(Datos);
            } else if (request.getParameter("accion").equals("cantidad_gustos_android")) {
                /*Si se realiza la peticion de obtener la cantidad de gustos del usuario*/
                /*Se declaran las variables necesarias*/
                String Codigo = request.getParameter("codigo");
                Cls_Seleccion sel = new Cls_Seleccion();
                /*Se obtienen los datos de la cantidad de gustos de un usuario, en el método de modelo*/
                int Datos = sel.CantidadGustosAndroid(Codigo);
                /*Se imprime el resultado*/
                out.print(Datos);
            } else if (request.getParameter("accion").equals("agregar_gusto_android")) {
                /*Si se realiza la peticion de agregar un gusto*/
                /*Se declaran las variables necesarias*/
                Cls_Seleccion sel = new Cls_Seleccion();
                String Mensaje;
                String Codigo = request.getParameter("codigo");
                String CodigoUsuario = request.getParameter("codigousuario");
                boolean b;
                /*Se agrega el gusto, en el metodo de modelo*/
                b = sel.AddGusto(Codigo, CodigoUsuario);
                if (b) {
                    /*Se encodifican los datos en JSON*/
                    Mensaje = "Se agregó el gusto satisfactoriamente.";
                } else {
                    /*Se encodifican los datos en JSON*/
                    Mensaje = "Ocurrió un error al agregar el gusto de su cuenta. Estamos trabajando para solucionar este problema.";
                }
                /*Se imprime el resultado*/
                out.print(Mensaje);
            } else if (request.getParameter("accion").equals("remover_gusto_android")) {
                /*Si se realiza la peticion de remover un gusto*/
                /*Se declaran las variables necesarias*/
                Cls_Seleccion sel = new Cls_Seleccion();
                String Mensaje;
                String Codigo = request.getParameter("codigo");
                String CodigoUsuario = request.getParameter("codigousuario");
                boolean b;
                /*Se verifica que se pueda remover un gusto, en el metodo de modelo*/
                b = sel.CantidadGustosAmbientesPreRemove(Codigo, CodigoUsuario);
                if (b) {
                    /*Se remueve el gusto, en el metodo de modelo*/
                    b = sel.RemoveGusto(Codigo, CodigoUsuario);
                    if (b) {
                        /*Se encodifican los datos en JSON*/
                        Mensaje = "Se quitó el gusto de tus gustos existosamente.";
                    } else {
                        /*Se encodifican los datos en JSON*/
                        Mensaje = "Ocurrió un error al remover el gusto de su cuenta. Estamos trabajando para solucionar este problema.";
                    }
                } else {
                    Mensaje = sel.getMensaje();
                }
                /*Se imprime el resultado*/
                out.print(Mensaje);
            } else if (request.getParameter("accion").equals("cantidad_eventos_recomendados_android")) {
                /*Si se realiza la peticion de total de eventos recomendados*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();
                String codigoUsuario = request.getParameter("codigo");
                int row = eve.getcantidadeventosRecomendados(codigoUsuario);
                /*Se imprime el resultado*/
                out.println(row);
            } else if (request.getParameter("accion").equals("eventos_recomendados_android")) {
                /*Si se realiza la peticion de obtener los datos de los eventos recomendados*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();
                JSONObject obj = new JSONObject();
                JSONArray list = new JSONArray();
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                String codigoUsuario = request.getParameter("codigo");

                BufferedImage img;

                String urlimgservidor = this.getServletContext().getRealPath("/Libs/Customs/images/Evento");
                /*Se obtienen los datos de los eventos recomendados, en el metodo de modelo*/
                String[][] Datos = eve.geteventosRecomendadosAndroid(codigoUsuario, cantidad);
                int i = 0;
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    img = ImageIO.read(new File(urlimgservidor + "/" + row[0] + row[1]));
                    String typeimg = row[1].substring(1, row[1].length());

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ImageIO.write(img, typeimg, bos);
                    byte[] imageBytes = bos.toByteArray();

                    BASE64Encoder encoder = new BASE64Encoder();
                    String imageSTring = encoder.encode(imageBytes);

                    bos.close();
                    ob.put("Codigo", row[0]);
                    ob.put("Imagen", imageSTring);
                    ob.put("Nombre", row[2]);
                    ob.put("Fecha", row[3]);
                    ob.put("NombreCiudad", row[4]);
                    ob.put("NombreDepto", row[5]);
                    list.add(ob);
                    i++;
                }
                obj.put("eventos", list);
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("cantidad_eventos_general_android")) {
                /*Si se realiza la peticion de total de eventos recomendados*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();
                int row = eve.getcantidadeventosGeneralAndroid();
                /*Se imprime el resultado*/
                out.println(row);
            } else if (request.getParameter("accion").equals("eventos_general_android")) {
                /*Si se realiza la peticion de obtener los datos de los eventos recomendados*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();
                JSONObject obj = new JSONObject();
                JSONArray list = new JSONArray();
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));

                BufferedImage img;

                String urlimgservidor = this.getServletContext().getRealPath("/Libs/Customs/images/Evento");
                /*Se obtienen los datos de los eventos recomendados, en el metodo de modelo*/
                String[][] Datos = eve.geteventosGeneralAndroid(cantidad);
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    img = ImageIO.read(new File(urlimgservidor + "/" + row[0] + row[1]));
                    String typeimg = row[1].substring(1, row[1].length());

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ImageIO.write(img, typeimg, bos);
                    byte[] imageBytes = bos.toByteArray();

                    BASE64Encoder encoder = new BASE64Encoder();
                    String imageSTring = encoder.encode(imageBytes);

                    bos.close();
                    ob.put("Codigo", row[0]);
                    ob.put("Imagen", imageSTring);
                    ob.put("Nombre", row[2]);
                    ob.put("Fecha", row[3]);
                    ob.put("NombreCiudad", row[4]);
                    ob.put("NombreDepto", row[5]);
                    list.add(ob);
                }
                obj.put("eventos", list);
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("cantidad_mis_eventos_android")) {
                /*Si se realiza la peticion de total de eventos recomendados*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();
                String codigo = request.getParameter("codigo");
                int row = eve.getcantidadMisEventosAndroid(codigo);
                /*Se imprime el resultado*/
                out.println(row);
            } else if (request.getParameter("accion").equals("mis_eventos_android")) {
                /*Si se realiza la peticion de ver todos los eventos de una empresa*/
                /*Se declaran las variables necesarias*/
                String codigo = request.getParameter("codigo");
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                Cls_Evento eve = new Cls_Evento();
                JSONObject obj = new JSONObject();
                JSONArray list = new JSONArray();
                /*Se obtienen los datos de los eventos de una empresa, en el metodo de modelo*/
                String[][] Datos = eve.BuscarDatosMisEventosAndroid(codigo, cantidad);
                int i = 0;
                BufferedImage img;

                String urlimgservidor = this.getServletContext().getRealPath("/Libs/Customs/images/Evento");
                /*Se encodifican los datos en JSON*/
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    img = ImageIO.read(new File(urlimgservidor + "/" + row[0] + row[1]));
                    String typeimg = row[1].substring(1, row[1].length());

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ImageIO.write(img, typeimg, bos);
                    byte[] imageBytes = bos.toByteArray();

                    BASE64Encoder encoder = new BASE64Encoder();
                    String imageSTring = encoder.encode(imageBytes);

                    bos.close();
                    ob.put("Codigo", row[0]);
                    ob.put("Imagen", imageSTring);
                    ob.put("Nombre", row[2]);
                    ob.put("Fecha", row[3]);
                    ob.put("NombreCiudad", row[4]);
                    ob.put("NombreDepto", row[5]);
                    list.add(ob);
                    i++;
                }
                obj.put("eventos", list);
                /*Se imprime el resultado*/
                out.print(obj);
            } else if (request.getParameter("accion").equals("ubicacion_evento_android")) {
                Cls_Evento eve = new Cls_Evento();
                JSONObject ob = new JSONObject();
                String codigo = request.getParameter("codigo");

                String[] Dato = eve.getUbicacionAndroid(codigo);

                ob.put("Latitud", Dato[0]);
                ob.put("Longitud", Dato[1]);
                ob.put("Nombre", Dato[2]);

                out.print(ob);
            } else if (request.getParameter("accion").equals("datos_evento_detalle_android")) {
                /*Si se realiza la peticion de obtener los datos de la ubicacion de los eventos*/
                /*Se declaran las variables necesarias*/
                Cls_Evento eve = new Cls_Evento();
                String codigo = request.getParameter("idevento");
                JSONObject obj = new JSONObject();
                JSONArray list = new JSONArray();
                JSONObject ob = new JSONObject();
                BufferedImage img;
                String urlimgservidor = this.getServletContext().getRealPath("/Libs/Customs/images/Evento");

                /*Se obtienen los datos de la ubicacion de los eventos, en el metodo de modelo*/
                String[] row = eve.BuscarDatosDetalleEventoAndroid(codigo);

                img = ImageIO.read(new File(urlimgservidor + "/" + codigo + row[0]));
                String typeimg = row[0].substring(1, row[0].length());

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(img, typeimg, bos);
                byte[] imageBytes = bos.toByteArray();

                BASE64Encoder encoder = new BASE64Encoder();
                String imageSTring = encoder.encode(imageBytes);
                /*Se encodifican los datos en JSON*/
                ob.put("Imagen", imageSTring);
                ob.put("Fecha", row[1]);
                ob.put("NombreEvento", row[2]);
                ob.put("NombreEmpresa", row[3]);
                ob.put("NombreCiudad", row[5]);
                ob.put("NombreDepartamento", row[4]);
                ob.put("Direccion", row[6]);
                ob.put("Rango_Precios", row[7]);
                ob.put("Descripcion", row[8]);
                ob.put("Cinco", row[9]);
                ob.put("Cuatro", row[10]);
                ob.put("Tres", row[11]);
                ob.put("Dos", row[12]);
                ob.put("Uno", row[13]);
                list.add(ob);
                obj.put("eventos", list);
                /*Se imprime el resultado*/
                out.print(obj);
            } else {
                /*Si no se recibe alguna de las anteriores peticiones se retorna la vista de indece*/
                response.sendRedirect("View/index.jsp");
            }

        } finally {
            /*Se ciera todo*/
            out.close();
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
