/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Evento;
import Modelo.Mensajeria;
import java.io.File;
import java.io.IOException;
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
        /*Se detalla el contenido que tendra el servlet*/
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        /*Se crea una variable para la sesion*/
        HttpSession session = request.getSession(true);

        boolean b;
        try {
            /*Se declaran las variables necesarias*/
            Evento eve = new Evento();
            Mensajeria sms = new Mensajeria();
            String Codigo = "", Mensaje = "", Nombre = "", Tipo = "", Imagen = "", url, Peti;
            String urlsalidaimg;
            urlsalidaimg = "/media/santiago/Santiago/IMGTE/";
            //urlsalidaimg = "I:\\IMGTE\\";

            /*FileItemFactory es una interfaz para crear FileItem*/
            FileItemFactory file_factory = new DiskFileItemFactory();

            /*ServletFileUpload esta clase convierte los input file a FileItem*/
            ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
            /*sacando los FileItem del ServletFileUpload en una lista */

            List items = servlet_up.parseRequest(request);
            Iterator it = items.iterator();

            /*Se evalua cada una de las posibles peticiones y los posibles campos que envien*/
            while (it.hasNext()) {
                FileItem item = (FileItem) it.next();
                if (item.isFormField()) {
                    //Plain request parameters will come here. 

                    String name = item.getFieldName();
                    if (name.equals("Creador")) {
                        /*Se guarda el campo en la clase*/
                        eve.setCreador(item.getString());
                    } else if (name.equals("Nombre")) {
                        /*Se guarda el campo en la clase*/
                        eve.setNombre(item.getString());
                    } else if (name.equals("Codigo")) {
                        /*Se guarda el campo en la clase*/
                        eve.setCodigo(item.getString());
                    } else if (name.equals("Rango")) {
                        /*Se guarda el campo en la clase*/
                        eve.setRango(item.getString());
                    } else if (name.equals("Fecha")) {
                        /*Se guarda el campo en la clase*/
                        eve.setFecha(item.getString());
                    } else if (name.equals("Descripcion")) {
                        /*Se guarda el campo en la clase*/
                        eve.setDescipcion(item.getString());
                    } else if (name.equals("Ciudad")) {
                        /*Se guarda el campo en la clase*/
                        eve.setCiudad(item.getString());
                    } else if (name.equals("Direccion")) {
                        /*Se guarda el campo en la clase*/
                        eve.setDireccion(item.getString());
                    } else if (name.equals("Motivo")) {
                        /*Se guarda el campo en la clase*/
                        eve.setMotivo(item.getString());
                    } else if (name.equals("Latitud")) {
                        /*Se guarda el campo en la clase*/
                        eve.setLatitud(item.getString());
                    } else if (name.equals("Longitud")) {
                        /*Se guarda el campo en la clase*/
                        eve.setLongitud(item.getString());
                    } else if (name.equals("RegistrarEvento")) {
                        /*Se convierte la fecha a date*/
                        if (eve.ConvertirFecha(eve.getFecha())) {
                            /*Se evalua si la fecha tiene dos dias mas a la fecha de hoy*/
                            if (eve.ValidarDosDiasFecha(eve.getFechaDate())) {
                                /*Se evalua si se mando una iamgen*/
                                if (!eve.getImagen().equals("")) {
                                    /*Se ejecuta el metodo de registrar evento, en la clase modelo
                                     con los datos que se encuentran en la clase*/
                                    b = eve.setRegistrarEvento(eve.getImagen(), eve.getNombre(), eve.getFechaDate(), eve.getDescipcion(), eve.getRango(), eve.getCreador(), eve.getCiudad(), eve.getDireccion(), eve.getLatitud(), eve.getLongitud());
                                    if (b) {
                                        /*Se guarda un mensaje mediante las sesiones
                                         y se redirecciona*/
                                        session.setAttribute("Mensaje", "Se registro el evento satisfactoriamente.");
                                        session.setAttribute("TipoMensaje", "Dio");
                                        response.sendRedirect("View/RClasificacionEvento.jsp?CodigoEvento=" + eve.getCodigo());
                                    } else {
                                        /*Se guarda un mensaje mediante las sesiones
                                         y se redirecciona*/
                                        session.setAttribute("Mensaje", eve.getMensaje());
                                        session.setAttribute("TipoMensaje", "NODio");
                                        response.sendRedirect("View/RegistrarEvento.jsp");
                                    }
                                } else {
                                    /*Se guarda un mensaje mediante las sesiones
                                     y se redirecciona*/
                                    session.setAttribute("Mensaje", "Seleccione una imagen para registrar el evento");
                                    session.setAttribute("TipoMensaje", "NODio");
                                    response.sendRedirect("View/RegistrarEvento.jsp");
                                }

                            } else {
                                /*Se guarda un mensaje mediante las sesiones
                                 y se redirecciona*/
                                session.setAttribute("Mensaje", "No se puede registrar un evento que inicie antes de dos días");
                                session.setAttribute("TipoMensaje", "NODio");
                                response.sendRedirect("View/RegistrarEvento.jsp");
                            }
                        } else {
                            /*Se guarda un mensaje mediante las sesiones
                             y se redirecciona*/
                            session.setAttribute("Mensaje", "Ocurrió un problema inesperado con la fecha del evento. Estamos trabajando para solucionar este problema.");
                            session.setAttribute("TipoMensaje", "NODio");
                            response.sendRedirect("View/RegistrarEvento.jsp");
                        }

                    } else if (name.equals("DesactivarEvento")) {
                        /*Se ejecuta el metodo de desaprobar evento, en la clase modelo
                         con los datos que se encuentran en la clase*/
                        if (eve.setDesaprobarEvento(eve.getCodigo(), eve.getMotivo())) {
                            String[] Datos = eve.BuscarEventoParaMensaje(eve.getCodigo());
                            if (sms.EnviarMensajeCambioEstadoEvento(Datos, "Desaprobado", eve.getMotivo())) {
                                /*Se guarda un mensaje mediante las sesiones
                                 y se redirecciona*/
                                session.setAttribute("Mensaje", "Se canceló el evento satisfactoriamente.");
                                session.setAttribute("TipoMensaje", "Dio");
                            } else {
                                /*Se guarda un mensaje mediante las sesiones
                                 y se redirecciona*/
                                session.setAttribute("Mensaje", "Se canceló el evento, pero no se logró enviar la notificación al correo electrónico de la empresa.");
                                session.setAttribute("TipoMensaje", "NODio");
                            }
                        } else {
                            /*Se guarda un mensaje mediante las sesiones
                             y se redirecciona*/
                            session.setAttribute("Mensaje", "Ocurrió un error al cancelar el evento. Estamos trabajando para solucionar este problema.");
                            session.setAttribute("TipoMensaje", "NODio");
                        }
                        response.sendRedirect("View/CEventoPendiente.jsp");
                    } else if (name.equals("DesactivarEventoAdmin")) {
                        /*Se ejecuta el metodo de desaprobar evento, en la clase modelo
                         con los datos que se encuentran en la clase(administradir)*/
                        if (eve.setDesaprobarEvento(eve.getCodigo(), eve.getMotivo())) {
                            String[] Datos = eve.BuscarEventoParaMensaje(eve.getCodigo());
                            if (sms.EnviarMensajeCambioEstadoEvento(Datos, "Desaprobado", eve.getMotivo())) {
                                /*Se guarda un mensaje mediante las sesiones
                                 y se redirecciona*/
                                session.setAttribute("Mensaje", "Se desaprobó el evento satisfactoriamente.");
                                session.setAttribute("TipoMensaje", "Dio");
                            } else {
                                /*Se guarda un mensaje mediante las sesiones
                                 y se redirecciona*/
                                session.setAttribute("Mensaje", "Se desaprobó el evento, pero no se logró enviar la notificación al correo electrónico de la empresa.");
                                session.setAttribute("TipoMensaje", "NODio");
                            }
                        } else {
                            /*Se guarda un mensaje mediante las sesiones
                             y se redirecciona*/
                            session.setAttribute("Mensaje", "Ocurrió un error al desaprobar el evento. Estamos trabajando para solucionar este problema.");
                            session.setAttribute("TipoMensaje", "NODio");
                        }
                        response.sendRedirect("View/ConsultaTodosEventos.jsp");
                    } else if (name.equals("DesactivarEventoEmpresa")) {
                        /*Se ejecuta el metodo de desaprobar evento, en la clase modelo
                         con los datos que se encuentran en la clase(Empresa)*/
                        if (eve.setCancelarEvento(eve.getCodigo(), eve.getMotivo())) {
                                session.setAttribute("Mensaje", "Se canceló el evento satisfactoriamente.");
                                session.setAttribute("TipoMensaje", "Dio");
                        } else {
                            /*Se guarda un mensaje mediante las sesiones
                             y se redirecciona*/
                            session.setAttribute("Mensaje", "Ocurrió un error al cancelar el evento. Estamos trabajando para solucionar este problema.");
                            session.setAttribute("TipoMensaje", "NODio");
                        }
                        response.sendRedirect("View/MisEventos.jsp");
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
                            /*Se muestra un mensaje en caso de pesar mas de 3 MB*/
                            session.setAttribute("Mensaje", "El tamaño límite de la imagen es: 3 MB");
                            session.setAttribute("TipoMensaje", "NODio");
                            /*Se redirecciona*/
                            response.sendRedirect("View/ConsultaSeleccion.jsp");
                        } else {
                            /*Se crea la imagne*/
                            File archivo_server = new File(urlsalidaimg + item.getName());
                            /**
                             * Se guardael url de la imagen en la clase
                             */
                            eve.setImagen(urlsalidaimg + item.getName());
                            /*Se guarda la imagen*/
                            item.write(archivo_server);
                        }
                    } else {
                        /**
                         * Se guardael url de la imagen en la clase
                         */
                        eve.setImagen("");
                    }
                }
            }

            response.sendRedirect(
                    "View/index.jsp");
        } catch (FileUploadException ex) {
            System.out.print(ex.getMessage().toString());

        } catch (Exception ex) {
            Logger.getLogger(Contr_Seleccion.class
                    .getName()).log(Level.SEVERE, null, ex);
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
