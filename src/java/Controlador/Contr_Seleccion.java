/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cls_Seleccion;
import java.io.*;
import java.io.IOException;
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
        /*Se detalla el contenido que tendra el servlet*/
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        /*Se crea una variable para la sesion*/
        HttpSession session = request.getSession(true);

        boolean b;
        try {
            /*Se declaran las variables necesarias*/
            Cls_Seleccion sel = new Cls_Seleccion();
            String Codigo = "", Mensaje = "", Nombre = "", Tipo = "", Imagen = "", url, Peti;
            String urlsalidaimg;
            urlsalidaimg = "/media/santiago/Santiago/IMGTE/";
            //urlsalidaimg = "D:\\IMGTE\\";
            String urlimgservidor = this.getServletContext().getRealPath("/Libs/Customs/images/Seleccion");
            /*FileItemFactory es una interfaz para crear FileItem*/
            FileItemFactory file_factory = new DiskFileItemFactory();

            /*ServletFileUpload esta clase convierte los input file a FileItem*/
            ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
            /*sacando los FileItem del ServletFileUpload en una lista */
            servlet_up.setHeaderEncoding("UTF-8");
            List items = servlet_up.parseRequest(request);
            Iterator it = items.iterator();

            /*Se evalua cada una de las posibles peticiones y los posibles campos que envien*/
            while (it.hasNext()) {
                FileItem item = (FileItem) it.next();
                if (item.isFormField()) {
                    //Plain request parameters will come here. 

                    String name = item.getFieldName();
                    if (name.equals("Codigo")) {
                        /*Se guarda el campo en la clase*/
                        sel.setCodigo(item.getString());
                    } else if (name.equals("Nombre")) {
                        /**
                         * Se guarda el campo en la clase
                         */
                        sel.setNombre(item.getString());
                    } else if (name.equals("Tipo")) {
                        /**
                         * Se guarda el campo en la clase
                         */
                        sel.setTipo(item.getString());
                    } else if (name.equals("Estado")) {
                        /**
                         * Se guarda el campo en la clase
                         */
                        sel.setEstado(item.getString());
                    } else if (name.equals("RegistrarSeleccion")) {
                        /*Se evalua si se mando una iamgen, cuando se va a registrar un evento*/
                        if (!sel.getImagen().equals("")) {
                            /*Si se envia una imagen obtiene la imagen para guardarla en el server luego*/
                            File img = new File(sel.getImagen());
                            /*Se ejecuta el metodo de registrar usuario que se encuentra, en la clase modelo
                             con los datos que se encuentran en la clase*/

                            b = sel.setRegistrarSeleccion(sel.getNombre(), sel.getTipo(), sel.getTypeImg());
                            if (b) {
                                /*Se guarda un mensaje mediante las sesiones
                                 y se redirecciona*/
                                File imagedb = new File(urlimgservidor + "/" + sel.getCodigo() + sel.getTypeImg());
                                img.renameTo(imagedb);
                                session.setAttribute("Mensaje", "El gusto o ambiente ha sido registrado correctamente.");
                                session.setAttribute("TipoMensaje", "Dio");
                                url = "View/ConsultaSeleccion.jsp";
                                response.sendRedirect(url);
                            } else {
                                img.delete();
                                /*Se guarda un mensaje de error mediante las sesiones
                                 y se redirecciona*/
                                session.setAttribute("Mensaje", sel.getMensaje());
                                session.setAttribute("TipoMensaje", "NODio");
                                url = "View/ConsultaSeleccion.jsp";
                                response.sendRedirect(url);
                            }
                        } else {
                            /*Se guarda un mensaje de error mediante las sesiones
                             y se redirecciona*/
                            session.setAttribute("Mensaje", "Seleccione una imagen, para registrar el ambiente o gusto.");
                            session.setAttribute("TipoMensaje", "NODio");
                        }
                    } else if (name.equals("ModificarSeleccion")) {
                        if (sel.getImagen().equals("")) {
                            /*Se ejecuta el metodo de actualizar los datos de la seleccion usuario que se encuentra, en la clase modelo
                             con los datos que se encuentran en la clase*/
                            b = sel.actualizardatosSeleccion(sel.getCodigo(), sel.getNombre(), sel.getTipo(), sel.getEstado());
                            if (b) {
                                /*Se guarda un mensaje mediante las sesiones
                                 y se redirecciona*/
                                session.setAttribute("Mensaje", "El gusto o ambiente ha sido registrada correctamente.");
                                session.setAttribute("TipoMensaje", "Dio");
                                url = "View/ConsultaSeleccion.jsp";
                                response.sendRedirect(url);
                            } else {
                                /*Se guarda un mensaje mediante las sesiones
                                 y se redirecciona*/
                                session.setAttribute("Mensaje", sel.getMensaje());
                                session.setAttribute("TipoMensaje", "NODio");
                                url = "View/ConsultaSeleccion.jsp";
                                response.sendRedirect(url);
                            }
                        } else {
                            /*Se ejecuta el metodo de actualizar los datos de la seleccion usuario que se encuentra, en la clase modelo
                             con los datos que se encuentran en la clase*/
                            File img = new File(sel.getImagen());
                            b = sel.actualizardatosSeleccion(sel.getCodigo(), sel.getNombre(), sel.getTipo(), sel.getTypeImg(), sel.getEstado());
                            if (b) {
                                File imagedb = new File(urlimgservidor + "/" + sel.getCodigo() + sel.getTypeImg());
                                img.renameTo(imagedb);
                                /*Se guarda un mensaje mediante las sesiones
                                 y se redirecciona*/
                                session.setAttribute("Mensaje", "El gusto o ambiente ha sido modificado correctamente.");
                                session.setAttribute("TipoMensaje", "Dio");
                                url = "View/ConsultaSeleccion.jsp";
                                response.sendRedirect(url);
                            } else {
                                img.delete();
                                /*Se guarda un mensaje mediante las sesiones
                                 y se redirecciona*/
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

                        if (sizeInBytes > 1000000) {
                            /*Se muestra un mensaje en caso de pesar mas de 3 MB*/
                            session.setAttribute("Mensaje", "El tamaño límite de la imagen es: 1 MB");
                            session.setAttribute("TipoMensaje", "NODio");
                            /*Se redirecciona*/
                            response.sendRedirect("View/ConsultaSeleccion.jsp");
                        } else {
                            if (contentType.indexOf("jpeg") > 0 || contentType.indexOf("png") > 0) {
                                if (contentType.indexOf("jpeg") > 0) {
                                    contentType = ".jpg";
                                } else {
                                    contentType = ".png";
                                }
                                /*Se crea la imagne*/
                                File archivo_server = new File(urlimgservidor + "/" + item.getName());
                                /*Se guardael nombre y tipo de imagen en la clase*/
                                sel.setImagen(urlimgservidor + "/" + item.getName());
                                sel.setTypeImg(contentType);
                                /*Se guarda la imagen*/
                                item.write(archivo_server);
                            } else {
                                session.setAttribute("Mensaje", "Solo se pueden registrar imagenes JPG o PNG");
                                session.setAttribute("TipoMensaje", "NODio");
                            }
                        }
                    } else {
                        /*Se guarda el url de la imagen en la clase*/
                        sel.setImagen("");
                    }
                }
            }

            /*Se redirecciona sino se recive ninguna peticion*/
            response.sendRedirect("View/index.jsp");
        } catch (FileUploadException ex) {
            /*Se muestra un mensaje en caso de error*/
            System.out.print(ex.getMessage().toString());
        } catch (Exception ex) {
            /*Se muestra un mensaje en caso de error*/
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
