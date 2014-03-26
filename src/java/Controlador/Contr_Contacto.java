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
        String Nombre, Correo, Asunto,Categoria, Ciudad, Comentario, Contenido, script, url;
        Mensajeria msm = new Mensajeria();
        if(request.getParameter("Contactenos")!=null){
            Nombre=request.getParameter("Nombre"); 
            Correo=request.getParameter("Correo");
            Asunto=request.getParameter("Asunto");
            Categoria=request.getParameter("Categoria");
            Ciudad=request.getParameter("Ciudad");
            Comentario=request.getParameter("Comentario");
            
            Contenido = "El usuario: "+ Nombre+"\n Con correo: "+ Correo +"\n Con el asunto: \""+ Asunto +"\" clasificado en"
                    + " la categoria: " +Categoria+"\n De la ciudad de: "+Ciudad+"\n Comentó: \""+Comentario+"\".";
            boolean b = msm.contactenos(Contenido, Asunto);
            if(b){
                script = "$.pnotify({\n" +
"				title: 'Trigger Event',\n" +
"				text: 'Tu correo ha sido enviado satisfactoriamente al administrador, te responderemos lo más pronto posible.',\n" +
"				type: 'success',\n" +
"				nonblock: true,\n" +
"				nonblock_opacity: .2,\n" +
"				icon: 'picon picon-logo'\n" +
"			});";
                        url="View/index.jsp?men=" + script ;
                        response.sendRedirect(url);
            }else{
                script = "$.pnotify({\n" +
"				title: 'Trigger Event',\n" +
"				text: 'Ocurrió un problema inesperado al tratar de enviar su mensaje, por favor inténtelo de nuevo.',\n" +
"				type: 'error',\n" +
"				nonblock: true,\n" +
"				nonblock_opacity: .2,\n" +
"				icon: 'picon picon-logo'\n" +
"			});";
                url="View/Contactenos.jsp?men=" + script ;
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
