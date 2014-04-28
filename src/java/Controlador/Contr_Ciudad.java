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
        request.setCharacterEncoding("UTF-8");
        String Nombre, Codigo, Mensaje, url, Departamento;
        Ciudad ciu = new Ciudad();
        HttpSession session = request.getSession(true);
        if (request.getParameter("RegistrarCiudad") != null) {

            Nombre = request.getParameter("Nombre");
            Departamento = request.getParameter("Departamento");

            boolean b = ciu.setRegistrarCiudad(Nombre, Departamento);
            if (b) {

                session.setAttribute("Mensaje", "Los datos del departamento han sido registrados correctamente.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/ConsultaCiudad.jsp";
                response.sendRedirect(url);
            } else {
                session.setAttribute("Mensaje", ciu.getMensaje());
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/ConsultaCiudad.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("ModificarCiudad") != null) {
            Codigo = request.getParameter("Codigo");
            Nombre = request.getParameter("Nombre");
            Departamento = request.getParameter("Departamento");

            boolean b = ciu.actualizardatosCiudad(Codigo, Nombre, Departamento);
            if (b) {

                session.setAttribute("Mensaje", "Los datos del departamento han sido modificados correctamente.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/ConsultaCiudad.jsp";
                response.sendRedirect(url);
            } else {
                session.setAttribute("Mensaje", ciu.getMensaje());
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/ConsultaCiudad.jsp";
                response.sendRedirect(url);
            }
        } else {
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
