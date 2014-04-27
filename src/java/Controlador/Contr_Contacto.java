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
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding( "UTF-8" );
        String Nombre, Correo, Asunto,Categoria, Ciudad, Comentario, Contenido, script, url;
        HttpSession session = request.getSession(true);
        Mensajeria msm = new Mensajeria();
        if(request.getParameter("Contactenos")!=null){
            Nombre=request.getParameter("Nombre"); 
            Correo=request.getParameter("Correo");
            Asunto=request.getParameter("Asunto");
            Categoria=request.getParameter("Categoria");
            Ciudad=request.getParameter("Ciudad");
            Comentario=request.getParameter("Comentario");
            Contenido = "Nuestro equipo técnico le notifica que el usuario "+Nombre+" de la ciudad de "+Ciudad+" con el correo "+Correo+" envió un(a): "+Categoria+ " con el siguiente asunto "+ Asunto+
                        "<br/> El/La "+Categoria+" es la siguiente: \""+Comentario+"\".";
            boolean b = msm.contactenos(Contenido, Asunto, "Administrador");
            if(b){
                session.setAttribute("Mensaje", "Tu correo ha sido enviado satisfactoriamente al administrador, te responderemos lo más pronto posible.");
                session.setAttribute("TipoMensaje", "Dio");
                url="View/index.jsp" ;
                response.sendRedirect(url);
            }else{
                session.setAttribute("Mensaje", "Ocurrió un problema inesperado al tratar de enviar su mensaje, por favor inténtelo de nuevo.");
                session.setAttribute("TipoMensaje", "NODio");
                url="View/index.jsp" ;
                response.sendRedirect(url);
            }
        }else{
            url="View/index.jsp" ;
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
