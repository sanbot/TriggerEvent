/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cls_Departamento;
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
        /*Se declara la codifiacion del servidor*/
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        /*Se declaran las variables necesarias*/
        String Nombre, Codigo, Mensaje, url;
        Cls_Departamento dep = new Cls_Departamento();
        /*Varriable para la sesion*/
        HttpSession session = request.getSession(true);
        /*Se evalua cada uno de las posibles peticiones que se le puedan hacer al servidor*/
        if (request.getParameter("RegistrarDepartamento") != null) {

            /*Si re va a registrar se obtiene el nombre*/
            Nombre = request.getParameter("Nombre");

            /*Se crea un boolean para evaluar el retorno de la clase de modelo*/
            boolean b = dep.setRegistrarDepartamento(Nombre);
            if (b) {

                /*Si funciono se retorna un mensaje por medio de la sesion y se redirecciona*/
                session.setAttribute("Mensaje", "El departamento ha sido registradó correctamente.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/ConsultaDepartamento.jsp";
                response.sendRedirect(url);
            } else {
                /*Si no funciona se retorna un mensaje por medio de la sesion y se redirecciona*/
                session.setAttribute("Mensaje", dep.getMensaje());
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/ConsultaDepartamento.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("ModificarDepartamento") != null) {
            /*Si se va a moficiar el departamento se guardan los datos necesarios como codigo y nombre*/
            Codigo = request.getParameter("Codigo");
            Nombre = request.getParameter("Nombre");

            /*Se crea una variable boolean para evaluar el resultado de la calse modelo*/
            boolean b = dep.actualizardatosDepartamento(Codigo, Nombre);
            if (b) {

                /*Si no funciona se retorna un mensaje por medio de la sesion y se redirecciona*/
                session.setAttribute("Mensaje", "El departamento ha sido modificadó correctamente.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/ConsultaDepartamento.jsp";
                response.sendRedirect(url);
            } else {
                /*Si no funciona se retorna un mensaje por medio de la sesion y se redirecciona*/
                session.setAttribute("Mensaje", dep.getMensaje());
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/ConsultaDepartamento.jsp";
                response.sendRedirect(url);
            }
        } else {
            /*Se redirecciona si no se realizo ninguna peticion*/
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
