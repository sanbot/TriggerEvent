/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Ciudad;
import Modelo.Cls_Satisfaccion;
import Modelo.Departamento;
import Modelo.Evento;
import Modelo.Mensajeria;
import Modelo.Seleccion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

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

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("accion").equals("vermas")) {
                Cls_Satisfaccion sat = new Cls_Satisfaccion();
                String[][] Datos = sat.BuscarComentariosEvento(request.getParameter("codigoevento"), Integer.parseInt(request.getParameter("limite")), Integer.parseInt(request.getParameter("cantidad")));
                JSONObject obj = new JSONObject();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("usuario", row[0]);
                    ob.put("empresa", row[1]);
                    ob.put("comentario", row[2]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                out.println(obj);
            } else if (request.getParameter("accion").equals("total")) {
                Cls_Satisfaccion sat = new Cls_Satisfaccion();
                int row = sat.getCantidadComentariosEvento(request.getParameter("codigoevento"));
                out.println(row);
            } else if (request.getParameter("accion").equals("totalevento")) {
                Evento eve = new Evento();
                int row = eve.CantidadRegistroEvento();
                out.println(row-1);
            } else if (request.getParameter("accion").equals("getciudad")) {
                Ciudad ciu = new Ciudad();
                String[][] Datos = ciu.BuscarDatosCiudadTodos(request.getParameter("codigodepartamento"));
                JSONObject obj = new JSONObject();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("ciudad", row[1]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                out.println(obj);
            } else if (request.getParameter("accion").equals("getdepartamentos")) {
                Departamento dep = new Departamento();
                String[][] Datos = dep.BuscarDatosDepartamentoTodos();
                JSONObject obj = new JSONObject();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("departamento", row[1]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                out.println(obj);
            } else if (request.getParameter("accion").equals("getciudades")) {
                Ciudad ciu = new Ciudad();
                String[][] Datos = ciu.BuscarDatosCiudadTodos();
                JSONObject obj = new JSONObject();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("ciudad", row[1]);
                    ob.put("iddepto", row[2]);
                    ob.put("nomdepto", row[3]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                out.println(obj);
            } else if (request.getParameter("accion").equals("aprobarevento")) {
                Evento eve = new Evento();
                Mensajeria sms = new Mensajeria();
                String Codigo = request.getParameter("codigoevento");
                JSONObject obj = new JSONObject();
                JSONObject ob = new JSONObject();
                boolean b = eve.setCambioEstadoEvento(Codigo, "Aprobado");
                if (b) {
                    String[] Datos = eve.BuscarEventoParaMensaje(Codigo);
                    if (sms.EnviarMensajeCambioEstadoEvento(Datos, "Aprobado")) {
                        ob.put("mensaje", "Se aprobó el evento satisfactoriamente.");
                        ob.put("tipomensaje", "Dio");
                    } else {
                        ob.put("mensaje", "Se aprobó el evento, pero no se logró enviar la notificación al correo electrónico de la empresa.");
                        ob.put("tipomensaje", "NODio");
                    }
                } else {
                    ob.put("mensaje", "Ocurrió un error al agregar el gusto a su cuenta. Estamos trabajando para solucionar este problema.");
                    ob.put("tipomensaje", "NODio");
                }
                obj.put("1", ob);
                out.print(obj);
            } else if (request.getParameter("accion").equals("geteventospendientes")) {
                Evento eve = new Evento();
                JSONObject obj = new JSONObject();
                String[][] Datos = eve.BuscarDatosPrincipalesEventosPendientes();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("fecha", row[2]);
                    ob.put("creador", row[3]);
                    ob.put("ciudad", row[4]);
                    ob.put("hora", row[5]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                out.print(obj);
            } else if (request.getParameter("accion").equals("getmiseventos")) {
                String nit = request.getParameter("nit");
                Evento eve = new Evento();
                JSONObject obj = new JSONObject();
                String[][] Datos = eve.BuscarDatosMisEventos(nit);
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("fecha", row[2]);
                    ob.put("creador", row[3]);
                    ob.put("ciudad", row[4]);
                    ob.put("estado", row[5]);
                    ob.put("hora", row[6]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                out.print(obj);
            } else if (request.getParameter("accion").equals("getmiseventostodos")) {
                String nit = request.getParameter("nit");
                Evento eve = new Evento();
                JSONObject obj = new JSONObject();
                String[][] Datos = eve.BuscarDatosTodosEventos();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("fecha", row[2]);
                    ob.put("creador", row[3]);
                    ob.put("ciudad", row[4]);
                    ob.put("estado", row[5]);
                    ob.put("hora", row[6]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                out.print(obj);
            } else if (request.getParameter("accion").equals("agregargusto")) {
                Seleccion sel = new Seleccion();
                String Codigo = request.getParameter("codigo");
                String CodigoUsuario = request.getParameter("idusuario");
                JSONObject obj = new JSONObject();
                boolean b;
                b = sel.AddGusto(Codigo, CodigoUsuario);
                if (b) {
                    obj.put("1", "Se agrego el gusto satisfactoriamente.");
                } else {
                    obj.put("0", "Ocurrió un error al agregar el gusto de su cuenta. Estamos trabajando para solucionar este problema.");
                }
                out.print(obj);
            } else if (request.getParameter("accion").equals("removergusto")) {
                Seleccion sel = new Seleccion();
                String Codigo = request.getParameter("codigo");
                String CodigoUsuario = request.getParameter("idusuario");
                JSONObject obj = new JSONObject();
                boolean b;
                b = sel.CantidadGustosAmbientesPreRemove(Codigo, CodigoUsuario);
                if (b) {
                    b = sel.RemoveGusto(Codigo, CodigoUsuario);
                    if (b) {
                        obj.put("1", "Se quito el gusto de tus gustos existosamente.");
                    } else {
                        obj.put("0", "Ocurrió un error al remover el gusto de su cuenta. Estamos trabajando para solucionar este problema.");
                    }
                } else {
                    obj.put("0", sel.getMensaje());
                }
                out.print(obj);
            } else if (request.getParameter("accion").equals("getgustosnuevos")) {
                String Codigo = request.getParameter("codigo");
                Seleccion sel = new Seleccion();
                String[][] Datos = sel.getGustosNuevos(Codigo);
                JSONObject obj = new JSONObject();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("tipo", row[2]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                out.print(obj);
            } else if (request.getParameter("accion").equals("getgustos")) {
                String Codigo = request.getParameter("codigo");
                Seleccion sel = new Seleccion();
                String[][] Datos = sel.getMisGustos(Codigo);
                JSONObject obj = new JSONObject();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("tipo", row[2]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                out.print(obj);
            } else if (request.getParameter("accion").equals("getseleccion")) {
                Seleccion sel = new Seleccion();
                String[][] Datos = sel.getDatosSeleccion();
                JSONObject obj = new JSONObject();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("tipo", row[2]);
                    ob.put("estado", row[3]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                out.print(obj);
            } else if (request.getParameter("accion").equals("getclasificacion")) {
                String Codigo = request.getParameter("idevento");
                Seleccion sel = new Seleccion();
                String Datos[][] = sel.getClasificacionEvento(Codigo);
                JSONObject obj = new JSONObject();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("tipo", row[2]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                out.print(obj);
            } else if (request.getParameter("accion").equals("getclasificacionnuevos")) {
                Seleccion sel = new Seleccion();
                String Codigo = request.getParameter("idevento");
                String[][] Datos = sel.getClasificacionNuevos(Codigo);
                JSONObject obj = new JSONObject();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("codigo", row[0]);
                    ob.put("nombre", row[1]);
                    ob.put("tipo", row[2]);
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                out.print(obj);
            } else if (request.getParameter("accion").equals("aprobarseleccion")) {
                String CodigoSeleccion = request.getParameter("idseleccion");
                Seleccion sel = new Seleccion();
                JSONObject obj = new JSONObject();
                boolean b;
                b = sel.AprobarSeleccion(CodigoSeleccion);
                if (b) {
                    obj.put("1", "Se aprobó el gusto/ambiente satisfactoriamente.");
                } else {
                    obj.put("0", sel.getMensaje());
                }
                out.print(obj);
            } else if (request.getParameter("accion").equals("desaprobarseleccion")) {
                String CodigoSeleccion = request.getParameter("idseleccion");
                Seleccion sel = new Seleccion();
                JSONObject obj = new JSONObject();
                boolean b = sel.CantidadUsoAmbienteGusto(CodigoSeleccion);
                if (b) {
                    b = sel.DesaprobarSeleccion(CodigoSeleccion);
                    if (b) {
                        obj.put("1", "El gusto o ambiente ha sido desaprobado satisfactoriamente.");
                    } else {
                        obj.put("0", sel.getMensaje());
                    }
                } else {
                    obj.put("0", "No se puede desaprobar, porque hay usuarios o eventos usando este ambiente o gusto.");
                }
                out.print(obj);
            } else if (request.getParameter("accion").equals("agregarclasificacion")) {
                String CodigoSeleccion = request.getParameter("idseleccion");
                String CodigoEvento = request.getParameter("idevento");
                Seleccion sel = new Seleccion();
                JSONObject obj = new JSONObject();
                boolean b = sel.AddClasificacionEvento(CodigoSeleccion, CodigoEvento);
                if (b) {
                    obj.put("1", "Se agregó el gusto/ambiente a la calificación satisfactoriamente.");
                    if (sel.ComprobarRegistroCompletoUSuario(CodigoEvento)) {
                        Evento eve = new Evento();
                        if (eve.setEstadoPendiente(CodigoEvento)) {
                            String mensaje = "Se han cumplido los requisitos mínimos para el registro y el evento está en la lista de espera por aprobación.";
                            obj.put("1", mensaje);
                            obj.put("2", "true");
                        }
                    }
                } else {
                    obj.put("1", sel.getMensaje());
                }
                out.print(obj);
            } else if (request.getParameter("accion").equals("comprobarregistroevento")) {
                String CodigoEvento = request.getParameter("idevento");
                Seleccion sel = new Seleccion();
                boolean dato = sel.ComprobarRegistroCompletoUSuario(CodigoEvento);
                out.print(dato);
            } else if (request.getParameter("accion").equals("eventodatosprincipales")) {
                Evento eve = new Evento();
                int limite = Integer.parseInt(request.getParameter("limite"));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                String[][] Datos = eve.BuscarDatosPrincipalesEventos(limite, cantidad);
                JSONObject obj = new JSONObject();
                int i = 0;
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
                    obj.put(Integer.toString(i), ob);

                    i++;
                }
                out.print(obj);
            } else {
                response.sendRedirect("View/index.jsp");
            }

        } finally {
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
