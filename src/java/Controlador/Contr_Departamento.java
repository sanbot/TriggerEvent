/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import Modelo.Departamento;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author santiago
 */
public class Contr_Departamento extends HttpServlet {

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
        String Nombre, Codigo, Mensaje, url;
        Departamento dep = new Departamento();
        HttpSession session = request.getSession(true);
        if (request.getParameter("RegistrarDepartamento") != null) {

            Nombre = request.getParameter("Nombre");

            boolean b = dep.setRegistrarDepartamento(Nombre);
            if (b) {

                session.setAttribute("Mensaje", "El departamento ha sido registradó correctamente.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/ConsultaDepartamento.jsp";
                response.sendRedirect(url);
            } else {
                session.setAttribute("Mensaje", dep.getMensaje());
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/ConsultaDepartamento.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("ModificarDepartamento") != null) {
            Codigo = request.getParameter("Codigo");
            Nombre = request.getParameter("Nombre");

            boolean b = dep.actualizardatosDepartamento(Codigo, Nombre);
            if (b) {

                session.setAttribute("Mensaje", "El departamento ha sido modificadó correctamente.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/ConsultaDepartamento.jsp";
                response.sendRedirect(url);
            } else {
                session.setAttribute("Mensaje", dep.getMensaje());
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/ConsultaDepartamento.jsp";
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
