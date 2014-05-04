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
