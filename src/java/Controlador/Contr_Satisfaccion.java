/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cls_Satisfaccion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 *
 * @author santiago
 */
public class Contr_Satisfaccion extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //declaramos las variables necesarias para todo el proyecto
        String Mensaje, Comentario, Rating, CodigoEvento, CodigoUsuario;
        HttpSession session = request.getSession(true);
        Cls_Satisfaccion sat = new Cls_Satisfaccion();

        //evaluamos las peticiones realizadas
        if (request.getParameter("RegistrarSatisfaccion") != null) {
            //incializamos las varaibles para codigo de evento y usuario
            CodigoEvento = request.getParameter("CodigoEvento");
            CodigoUsuario = (String) session.getAttribute("Codigo");

            //evaluamos el contendio de rating
            if ((request.getParameter("Rating") != null && !request.getParameter("Rating").equals("0")) && (request.getParameter("comentario") != null && !request.getParameter("comentario").equals(""))) {
                //gudardamos el contenido de rating y comentario
                Rating = request.getParameter("Rating");
                Comentario = request.getParameter("comentario");

                //realizamos los metodos necesarios para registar la calificacion
                if (sat.setRegistrarCalificacionYComentarioSatisfaccion(CodigoUsuario, CodigoEvento, Rating, Comentario)) {
                    //guardamos el mensaje correspondiente
                    session.setAttribute("Mensaje", "La crítica ha sido registrada correctamente.");
                    session.setAttribute("TipoMensaje", "Dio");
                } else {
                    //guardamos el mensaje correspondiente
                    session.setAttribute("Mensaje", sat.getMensaje());
                    session.setAttribute("TipoMensaje", "NODio");
                }
                //retornamos a la vista
                response.sendRedirect("View/DetalleEvento.jsp?CodigoEvento=" + CodigoEvento);

            } else if (request.getParameter("Rating") != null && !request.getParameter("Rating").equals("0")) {
                //gudardamos el contenido de rating 
                Rating = request.getParameter("Rating");

                //realizamos los metodos necesarios para registar la calificacion
                if (sat.setRegistrarCalificacionSatisfaccion(CodigoUsuario, CodigoEvento, Rating)) {
                    //guardamos el mensaje correspondiente
                    session.setAttribute("Mensaje", "La crítica ha sido registrada correctamente.");
                    session.setAttribute("TipoMensaje", "Dio");
                } else {
                    //guardamos el mensaje correspondiente
                    session.setAttribute("Mensaje", sat.getMensaje());
                    session.setAttribute("TipoMensaje", "NODio");
                }
                //retornamos a la vista
                response.sendRedirect("View/DetalleEvento.jsp?CodigoEvento=" + CodigoEvento);

            } else if (request.getParameter("comentario") != null && !request.getParameter("comentario").equals("")) {

                Comentario = request.getParameter("comentario");
                //realizamos los metodos necesarios para registar la calificacion
                if (sat.setRegistrarComentarioSatisfaccion(CodigoUsuario, CodigoEvento, Comentario)) {
                    //guardamos el mensaje correspondiente
                    session.setAttribute("Mensaje", "La crítica ha sido registrada correctamente.");
                    session.setAttribute("TipoMensaje", "Dio");
                } else {
                    //guardamos el mensaje correspondiente
                    session.setAttribute("Mensaje", sat.getMensaje());
                    session.setAttribute("TipoMensaje", "NODio");
                }
                //retornamos a la vista
                response.sendRedirect("View/DetalleEvento.jsp?CodigoEvento=" + CodigoEvento);
            } else {
                //guardamos el mensaje correspondiente
                session.setAttribute("Mensaje", "Debe seleccionar una puntuación o escribir un comentario.");
                session.setAttribute("TipoMensaje", "NODio");
                response.sendRedirect("View/DetalleEvento.jsp?CodigoEvento=" + CodigoEvento);
            }

        } else if (request.getParameter("accion").equals("comentarios_aleatorios")) {
                String Datos[][] = sat.BuscarComentariosAleatorios();
                JSONObject obj = new JSONObject();
                int i = 0;
                for (String row[] : Datos) {
                    JSONObject ob = new JSONObject();

                    ob.put("usuario", row[0]);
                    ob.put("evento", row[1]);
                    ob.put("comentario", row[2]);
                    obj.put(i, ob);
                    i++;
                }
                /*Se imprime el resultado*/
                PrintWriter out = response.getWriter();
                out.print(obj);
                out.close();
            }else {
            /*Se redirecciona si no se realizo ninguna peticion*/
            response.sendRedirect("View/ConsultaEvento.jsp");
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
