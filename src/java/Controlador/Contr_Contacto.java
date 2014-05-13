/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Mensajeria;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author santi
 */
public class Contr_Contacto extends HttpServlet {

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
        /*Se declara la codifiacion del servidor*/
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        /*Se declaran las variables necesarias*/
        String Nombre, Correo, Asunto, Categoria, Ciudad, Comentario, Contenido, script, url;
        /*Varriable para la sesion*/
        HttpSession session = request.getSession(true);
        /*Clase para realizar mensajes*/
        Mensajeria msm = new Mensajeria();
        /*Se evalua cada uno de las posibles peticiones que se le puedan hacer al servidor*/
        if (request.getParameter("Contactenos") != null) {
            /**Para contactenos re toman los siguientes valores y se guardan en un string*/
            Nombre = request.getParameter("Nombre");
            Correo = request.getParameter("Correo");
            Asunto = request.getParameter("Asunto");
            Categoria = request.getParameter("Categoria");
            Ciudad = request.getParameter("Ciudad");
            Comentario = request.getParameter("Comentario");
            /*Se crea la parte centrar del mensaje*/
            Contenido = "Nuestro equipo técnico le notifica que el usuario " + Nombre + " de la ciudad de " + Ciudad + " con el correo " + Correo + " envió un(a): " + Categoria + " con el siguiente asunto " + Asunto
                    + "<br/> El/La " + Categoria + " es la siguiente: \"" + Comentario + "\".";
            /*Se manda a ejecutar en la clase de mensajeria y se evalua el resulado*/
            boolean b = msm.contactenos(Contenido, Asunto, "Administrador");
            if (b) {
                /*Se retorna un mensaje por medio de la sesion y se redirecciona*/
                session.setAttribute("Mensaje", "Tu correo ha sido enviado satisfactoriamente al administrador del sitio, te responderemos lo más pronto posible.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/index.jsp";
                response.sendRedirect(url);
            } else {
                /*Se retorna un mensaje por medio de la sesion y se redirecciona*/
                session.setAttribute("Mensaje", "Ocurrió un problema inesperado al enviar su mensaje. Estamos trabajando para solucionar este problema.");
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/index.jsp";
                response.sendRedirect(url);
            }
        } else {
            /*Se redirecciona si no se realizo ninguna peticion*/
            url = "View/index.jsp";
            response.sendRedirect(url);
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
