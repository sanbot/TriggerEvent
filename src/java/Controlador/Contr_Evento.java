/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.Evento;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author santiago
 */
@WebServlet(name = "Contr_Evento", urlPatterns = {"/Contr_Evento"})
public class Contr_Evento extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();
        
        boolean b;
        try
        {
            Evento eve = new Evento();
            String Codigo = "", Mensaje = "", Nombre = "", Tipo = "", Imagen = "", url, Peti;
        /*FileItemFactory es una interfaz para crear FileItem*/
            FileItemFactory file_factory = new DiskFileItemFactory();
            
            /*ServletFileUpload esta clase convierte los input file a FileItem*/
            ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
            /*sacando los FileItem del ServletFileUpload en una lista */
            
            List items = servlet_up.parseRequest(request);
            Iterator it = items.iterator();
            
            while (it.hasNext()) {
                FileItem item = (FileItem) it.next();
                if (item.isFormField()) {
                    //Plain request parameters will come here. 
                    
                    String name = item.getFieldName();
                    if(name.equals("Creador"))
                    {
                        eve.setCreador(item.getString());
                    }
                    else if(name.equals("Nombre"))
                    {
                        eve.setNombre(item.getString());
                    }
                    else if(name.equals("Rango"))
                    {
                        eve.setRango(item.getString());
                    }
                    else if(name.equals("Fecha"))
                    {
                        eve.setFecha(item.getString());
                    }
                    else if(name.equals("Descripcion"))
                    {
                        eve.setDescipcion(item.getString());
                    }
                    else if(name.equals("RegistrarEvento"))
                    {
                        if(eve.ConvertirFecha(eve.getFecha()))
                        {
                            if(eve.ValidarDosDiasFecha(eve.getFechaDate()))
                            {
                                if(!eve.getImagen().equals(""))
                                {
                                    
                                }
                                else
                                {
                                    session.setAttribute("Mensaje", "Por favor, Seleccione una imagen e inténtelo de nuevo");
                                    session.setAttribute("TipoMensaje", "NODIO");
                                }
                                
                            }
                            else
                            {
                                session.setAttribute("Mensaje", "Ocurrió un error, no se puede registrar un evento que inicie antes de dos días");
                                session.setAttribute("TipoMensaje", "NODIO");
                            }
                        }
                        else
                        {
                            session.setAttribute("Mensaje", "No se pudo convertir el formato de fecha a Date");
                            session.setAttribute("TipoMensaje", "NODIO");
                        }
                        response.sendRedirect("RegistrarEvento.jsp");
                    }
                   
                } else {
                    if(!item.getName().equals(""))
                    {
                        //uploaded files will come here.
                        FileItem file = item;
                        String fieldName = item.getFieldName();
                        String fileName = item.getName();
                        String contentType = item.getContentType();
                        boolean isInMemory = item.isInMemory();
                        long sizeInBytes = item.getSize();
                        //File archivo_server = new File("/media/santiago/Santiago/IMGTE/"+item.getName());
                        //eve.setImagen("/media/santiago/Santiago/IMGTE/"+item.getName());
                        File archivo_server = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\"+item.getName());
                        eve.setImagen("C:\\Users\\Public\\Pictures\\Sample Pictures\\"+item.getName());
                        /*y lo escribimos en el servido*/
                        item.write(archivo_server);
                    }
                    else
                    {
                        eve.setImagen("");
                    }
                }
            }
            
            response.sendRedirect("View/index.jsp");
        } catch (FileUploadException ex) {  
            System.out.print(ex.getMessage().toString());
        } catch (Exception ex) {
            Logger.getLogger(Contr_Seleccion.class.getName()).log(Level.SEVERE, null, ex);
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
