/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Ciudad;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author santiago
 */
public class Contr_Ciudad extends HttpServlet {

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
        String Nombre, Codigo, Mensaje, url, Departamento;
        Ciudad ciu = new Ciudad();
        /*Se crea una variable para la sesion*/
        HttpSession session = request.getSession(true);
        /*Se evalua cada una de las posibles peticiones*/
        if (request.getParameter("RegistrarCiudad") != null) {

            /*Si se va a registrar una ciudad se guarda el nombre y el departamento*/
            Nombre = request.getParameter("Nombre");
            Departamento = request.getParameter("Departamento");

            boolean b = ciu.setRegistrarCiudad(Nombre, Departamento);
            if (b) {
                /*Se retorna un mensaje por medio de la sesion y se redirecciona*/
                session.setAttribute("Mensaje", "La ciudad se registró correctamente.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/ConsultaCiudad.jsp";
                response.sendRedirect(url);
            } else {
                /*Se retorna un mensaje por medio de la sesion y se redirecciona*/
                session.setAttribute("Mensaje", ciu.getMensaje());
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/ConsultaCiudad.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("ModificarCiudad") != null) {
            /*Si se va a modificar se guardan las variables codigo nombre y departametno*/
            Codigo = request.getParameter("Codigo");
            Nombre = request.getParameter("Nombre");
            Departamento = request.getParameter("Departamento");

            /*Se evalua el boolean que retorna el metodo de la clase modelo*/
            boolean b = ciu.actualizardatosCiudad(Codigo, Nombre, Departamento);
            if (b) {
                /*Se retorna un mensaje por medio de la sesion y se redirecciona*/

                session.setAttribute("Mensaje", "La ciudad ha sido modificada correctamente.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/ConsultaCiudad.jsp";
                response.sendRedirect(url);
            } else {
                /*Se retorna un mensaje por medio de la sesion y se redirecciona*/
                session.setAttribute("Mensaje", ciu.getMensaje());
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/ConsultaCiudad.jsp";
                response.sendRedirect(url);
            }
        } else {
            /*Si no es ninguna de las anteriores peticiones de redirecciona al index.jsp*/
            url = "index.jsp";
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
