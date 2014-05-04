/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Seleccion;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

/**
 *
 * @author ADSI
 */
public class Contr_Seleccion extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();

        boolean b;
        try {
            Seleccion sel = new Seleccion();
            String Codigo = "", Mensaje = "", Nombre = "", Tipo = "", Imagen = "", url, Peti;
            String urlsalidaimg;
            //urlsalidaimg = "/media/santiago/Santiago/IMGTE/";
            urlsalidaimg = "D:\\IMGTE\\";

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
                    if (name.equals("Codigo")) {
                        sel.setCodigo(item.getString());
                    } else if (name.equals("Nombre")) {
                        sel.setNombre(item.getString());
                    } else if (name.equals("Tipo")) {
                        sel.setTipo(item.getString());
                    } else if (name.equals("Estado")) {
                        sel.setEstado(item.getString());
                    } else if (name.equals("RegistrarSeleccion")) {
                        if (!sel.getImagen().equals("")) {
                            File img = new File(sel.getImagen());
                            b = sel.setRegistrarSeleccion(sel.getNombre(), sel.getTipo(), sel.getImagen());
                            if (b) {
                                if (!img.delete()) {
                                    String sda = "no";
                                }
                                session.setAttribute("Mensaje", "El gusto o ambiente ha sido registrado correctamente.");
                                session.setAttribute("TipoMensaje", "Dio");
                                url = "View/ConsultaSeleccion.jsp";
                                response.sendRedirect(url);
                            } else {
                                if (!img.delete()) {
                                    String sda = "no";
                                }
                                session.setAttribute("Mensaje", sel.getMensaje());
                                session.setAttribute("TipoMensaje", "NODio");
                                url = "View/ConsultaSeleccion.jsp";
                                response.sendRedirect(url);
                            }
                        } else {
                            session.setAttribute("Mensaje", "Seleccione una imagen, para registrar el ambiente o gusto.");
                            session.setAttribute("TipoMensaje", "NODio");
                        }
                    } else if (name.equals("ModificarSeleccion")) {
                        if (sel.getImagen().equals("")) {
                            File img = new File(sel.getImagen());
                            b = sel.actualizardatosSeleccion(sel.getCodigo(), sel.getNombre(), sel.getTipo(), sel.getEstado());
                            if (b) {
                                img.delete();
                                session.setAttribute("Mensaje", "El gusto o ambiente ha sido registrada correctamente.");
                                session.setAttribute("TipoMensaje", "Dio");
                                url = "View/ConsultaSeleccion.jsp";
                                response.sendRedirect(url);
                            } else {
                                img.delete();
                                session.setAttribute("Mensaje", sel.getMensaje());
                                session.setAttribute("TipoMensaje", "NODio");
                                url = "View/ConsultaSeleccion.jsp";
                                response.sendRedirect(url);
                            }
                        } else {
                            b = sel.actualizardatosSeleccion(sel.getCodigo(), sel.getNombre(), sel.getTipo(), sel.getImagen(), sel.getEstado());
                            if (b) {
                                session.setAttribute("Mensaje", "El gusto o ambiente ha sido modificado correctamente.");
                                session.setAttribute("TipoMensaje", "Dio");
                                url = "View/ConsultaSeleccion.jsp";
                                response.sendRedirect(url);
                            } else {
                                session.setAttribute("Mensaje", sel.getMensaje());
                                session.setAttribute("TipoMensaje", "NODio");
                                url = "View/ConsultaSeleccion.jsp";
                                response.sendRedirect(url);
                            }
                        }
                    }

                } else {
                    if (!item.getName().equals("")) {
                        //uploaded files will come here.
                        FileItem file = item;
                        String fieldName = item.getFieldName();
                        String fileName = item.getName();
                        String contentType = item.getContentType();
                        boolean isInMemory = item.isInMemory();
                        long sizeInBytes = item.getSize();

                        if (sizeInBytes > 3145728) {
                            session.setAttribute("Mensaje", "El tamaño límite de la imagen es: 3 MB");
                            session.setAttribute("TipoMensaje", "NODio");
                            response.sendRedirect("View/ConsultaSeleccion.jsp");
                        } else {
                            File archivo_server = new File(urlsalidaimg + item.getName());
                            sel.setImagen(urlsalidaimg + item.getName());
                            item.write(archivo_server);
                        }
                    } else {
                        sel.setImagen("");
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
