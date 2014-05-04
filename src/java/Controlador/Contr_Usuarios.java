/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Mensajeria;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 *
 * @author santi
 */
public class Contr_Usuarios extends HttpServlet {

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
        Usuario usu = new Usuario();
        Mensajeria msm = new Mensajeria();
        String script, Nombre, Codigo, Tipo_Documento, Estado, No_Documento, Telefono, Direccion, correo, celular, url, CodVer, CodigoVerificacion, Ciudad, Departamento;
        String Password, REPassword, Rol, Contrasenia;
        HttpSession session = request.getSession(true);

        boolean b = false;
        if (request.getParameter("login") != null) {
            correo = request.getParameter("correo");
            Contrasenia = request.getParameter("contrasenia");
            b = usu.getlogin(correo, Contrasenia);
            if (b) {
                Codigo = usu.getCodigo();
                Tipo_Documento = usu.getTipo_Documento();
                No_Documento = usu.getNo_Documento();
                Nombre = usu.getNombre();
                Telefono = usu.getTelefono();
                Direccion = usu.getDireccion();
                celular = usu.getCelular();
                Rol = usu.getTipo();
                Ciudad = usu.getCiudad();
                Departamento = usu.getDepartamento();

                session.setAttribute("Codigo", Codigo);
                session.setAttribute("Rol", Rol);
                session.setAttribute("Tipo_Documento", Tipo_Documento);
                session.setAttribute("No_Documento", No_Documento);
                session.setAttribute("Nombre", Nombre);
                session.setAttribute("Telefono", Telefono);
                session.setAttribute("Celular", celular);
                session.setAttribute("Correo", correo);
                session.setAttribute("Direccion", Direccion);
                session.setAttribute("Ciudad", Ciudad);
                session.setAttribute("Departamento", Departamento);

                url = "View/index.jsp";
                response.sendRedirect(url);
            } else {
                session.setAttribute("Mensaje", usu.getMensaje());
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/index.jsp";
                response.sendRedirect(url);
            }

        } else if (request.getParameter("ModificarPerfil") != null) {
            Nombre = request.getParameter("Nombre");
            Codigo = (String) session.getAttribute("Codigo");
            Tipo_Documento = request.getParameter("Tipo_Documento");
            No_Documento = request.getParameter("No_Documento");
            Telefono = request.getParameter("Telefono");
            celular = request.getParameter("Celular");
            correo = request.getParameter("Correo");
            Direccion = request.getParameter("Direccion");
            Rol = request.getParameter("TipoUsuario");
            Ciudad = request.getParameter("Ciudad");

            b = usu.actualizardatos(Codigo, Tipo_Documento, No_Documento, Nombre, Telefono, celular, correo, Direccion, Ciudad);
            if (b) {
                session.setAttribute("Rol", Rol);
                session.setAttribute("Tipo_Documento", Tipo_Documento);
                session.setAttribute("No_Documento", No_Documento);
                session.setAttribute("Nombre", Nombre);
                session.setAttribute("Telefono", Telefono);
                session.setAttribute("Celular", celular);
                session.setAttribute("Correo", correo);
                session.setAttribute("Direccion", Direccion);
                session.setAttribute("Ciudad", usu.getCiudad());
                session.setAttribute("Departamento", usu.getDepartamento());

                session.setAttribute("Mensaje", "Sus datos han sido modificados correctamente");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/Perfil.jsp";
                response.sendRedirect(url);
            } else {
                session.setAttribute("Mensaje", "Ocurrió un problema inesperado al modificar sus datos.");
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/ModificarPerfil.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("EnviarCodigoVer") != null) {
            Nombre = request.getParameter("Nombre");
            celular = request.getParameter("Celular");
            correo = request.getParameter("Correo");
            CodVer = usu.CodVer();

            b = msm.EnviarCodVer(correo, celular, Nombre, CodVer);
            if (b) {
                session.setAttribute("Registrar_Nombre", Nombre);
                session.setAttribute("Registrar_Celular", celular);
                session.setAttribute("Registrar_Correo", correo);
                session.setAttribute("Registrar_CodigoVer", CodVer);

                session.setAttribute("Mensaje", "Verifique su correo, se le ha enviado el código de verificación.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/RegistrarUsuario.jsp";
                response.sendRedirect(url);
            } else {
                session.setAttribute("Mensaje", "Ocurrió un problema inesperado al enviar el código de verificación.");
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/RegistrarUsuario.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("REnviarCodigoVer") != null) {
            Nombre = (String) session.getAttribute("Registrar_Nombre");
            celular = (String) session.getAttribute("Registrar_Celular");
            correo = (String) session.getAttribute("Registrar_Correo");
            CodVer = (String) session.getAttribute("Registrar_CodigoVer");

            b = msm.EnviarCodVer(correo, celular, Nombre, CodVer);
            if (b) {
                session.setAttribute("Mensaje", "Verifique su correo, se le ha enviado el código de verificación.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/RegistrarUsuario.jsp";
                response.sendRedirect(url);
            } else {
                session.setAttribute("Mensaje", "currió un problema inesperado al enviar el código de verificación.");
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/RegistrarUsuario.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("LimpiarDatosUsuario") != null) {
            session.setAttribute("Registrar_Nombre", null);
            session.setAttribute("Registrar_Celular", null);
            session.setAttribute("Registrar_Correo", null);
            session.setAttribute("Registrar_CodigoVer", null);

            url = "View/RegistrarUsuario.jsp";
            response.sendRedirect(url);

        } else if (request.getParameter("RegistrarUsuario") != null) {

            Nombre = (String) session.getAttribute("Registrar_Nombre");
            celular = (String) session.getAttribute("Registrar_Celular");
            correo = (String) session.getAttribute("Registrar_Correo");
            CodVer = (String) session.getAttribute("Registrar_CodigoVer");

            CodigoVerificacion = request.getParameter("codver");
            Rol = request.getParameter("Tipo_Usuario");
            Tipo_Documento = request.getParameter("Tipo_Documento");
            No_Documento = request.getParameter("No_Documento");
            Telefono = request.getParameter("Telefono");
            Direccion = request.getParameter("Direccion");
            Password = request.getParameter("Password");
            REPassword = request.getParameter("REPassword");
            Ciudad = request.getParameter("Ciudad");

            if (Password.equals(REPassword)) {
                if (CodVer.equals(CodigoVerificacion)) {
                    b = usu.ingresarUsuario(Rol, Tipo_Documento, No_Documento, Nombre, Telefono, celular, correo, Direccion, Password, Ciudad);
                    if (b) {
                        session.setAttribute("Registrar_Nombre", null);
                        session.setAttribute("Registrar_Celular", null);
                        session.setAttribute("Registrar_Correo", null);
                        session.setAttribute("Registrar_CodigoVer", null);

                        session.setAttribute("Mensaje", "Su cuenta ha sido creada satisfactoriamente.");
                        session.setAttribute("TipoMensaje", "Dio");
                        url = "View/RegistrarUsuario.jsp";
                        response.sendRedirect(url);
                    } else {
                        session.setAttribute("Mensaje", usu.getMensaje());
                        session.setAttribute("TipoMensaje", "NODio");
                        url = "View/RegistrarUsuario.jsp";
                        response.sendRedirect(url);
                    }
                } else {
                    session.setAttribute("Mensaje", "El código de verificación no coincide con el enviado.");
                    session.setAttribute("TipoMensaje", "NODio");
                    url = "View/RegistrarUsuario.jsp";
                    response.sendRedirect(url);
                }
            } else {
                session.setAttribute("Mensaje", "ErrorPass");
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/RegistrarUsuario.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("recucontrasenia") != null) {
            correo = request.getParameter("correo");
            b = usu.getrecordarContrasenia(correo);
            if (b) {
                String contra = usu.getContrasenia();
                Nombre = usu.getNombre();
                boolean c = msm.recordarcontrasenia(correo, contra, Nombre);
                if (c) {
                    session.setAttribute("Mensaje", "Verifique su correo, se le ha enviado su contraseña.");
                    session.setAttribute("TipoMensaje", "Dio");
                    url = "View/index.jsp";
                    response.sendRedirect(url);
                } else {
                    session.setAttribute("Mensaje", "Ocurrió un problema inesperado al enviar su contraseña.");
                    session.setAttribute("TipoMensaje", "NODio");
                    url = "View/index.jsp";
                    response.sendRedirect(url);
                }
            } else {
                session.setAttribute("Mensaje", "El correo diligenciado no concuerda con ninguna cuenta registrada.");
                session.setAttribute("TipoMensaje", "NODio");

                url = "View/index.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("ModificarUsuarioTodos") != null) {
            Nombre = request.getParameter("Nombre");
            Codigo = request.getParameter("Codigo");
            Rol = request.getParameter("Tipo");
            Tipo_Documento = request.getParameter("Tipo_Documento");
            No_Documento = request.getParameter("No_Documento");
            Telefono = request.getParameter("Telefono");
            celular = request.getParameter("Celular");
            correo = request.getParameter("Correo");
            Direccion = request.getParameter("Direccion");
            Estado = request.getParameter("Estado");
            Ciudad = request.getParameter("Ciudad");

            b = usu.actualizardatosUsuario(Codigo, Nombre, Rol, Tipo_Documento, No_Documento, Telefono, celular, correo, Direccion, Estado, Ciudad);
            if (b) {

                session.setAttribute("Mensaje", "Sus datos han sido modificados correctamente.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/ConsultaUsuario.jsp";
                response.sendRedirect(url);
            } else {
                session.setAttribute("Mensaje", "Ocurrió un problema inesperado al modificar sus datos.");
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/MUsuario.jsp?Codigo=" + Codigo;
                response.sendRedirect(url);
            }
        } else if (request.getParameter("RegistrarTodoUsuario") != null) {

            Nombre = request.getParameter("Nombre");
            celular = request.getParameter("Celular");
            correo = request.getParameter("Correo");

            Rol = request.getParameter("Tipo_Usuario");
            Tipo_Documento = request.getParameter("Tipo_Documento");
            No_Documento = request.getParameter("No_Documento");
            Telefono = request.getParameter("Telefono");
            Direccion = request.getParameter("Direccion");
            Password = request.getParameter("Password");
            REPassword = request.getParameter("REPassword");
            Ciudad = request.getParameter("Ciudad");

            if (Password.equals(REPassword)) {
                b = usu.ingresarUsuario(Rol, Tipo_Documento, No_Documento, Nombre, Telefono, celular, correo, Direccion, Password, Ciudad);
                if (b) {
                    session.setAttribute("Registrar_Nombre", null);
                    session.setAttribute("Registrar_Celular", null);
                    session.setAttribute("Registrar_Correo", null);
                    session.setAttribute("Registrar_CodigoVer", null);

                    session.setAttribute("Mensaje", "El usuario ha sido registrado correctamente.");
                    session.setAttribute("TipoMensaje", "Dio");
                    url = "View/ConsultaUsuario.jsp";
                    response.sendRedirect(url);
                } else {
                    session.setAttribute("TipoMensaje", "NODio");
                    session.setAttribute("Mensaje", usu.getMensaje());
                    url = "View/ConsultaUsuario.jsp";
                    response.sendRedirect(url);
                }
            } else {
                session.setAttribute("Mensaje", "Las contraseñas no coinciden.");
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/ConsultaUsuario.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("CambiarContrasenia") != null) {
            Codigo = (String) session.getAttribute("Codigo");
            String Contr = request.getParameter("ContraseniaActual");
            String ContrNueva = request.getParameter("ContraseniaNueva");
            String ContrReContra = request.getParameter("ContraseniaRepetir");
            if (ContrNueva.equals(ContrReContra)) {
                if (usu.getConfirmarContrasenia(Codigo, Contr)) {
                    if (usu.setCambioContrasenia(Codigo, ContrNueva)) {
                        session.setAttribute("Mensaje", "La contraseña ha sido modificada exitosamente.");
                        session.setAttribute("TipoMensaje", "Dio");
                    } else {
                        //session.setAttribute("Mensaje", usu.getMensaje());
                        session.setAttribute("Mensaje", "Ocurrió un problema inesperado al modificar la contraseña");
                        session.setAttribute("TipoMensaje", "NODio");
                    }
                } else {
                    session.setAttribute("Mensaje", "La contraseña actual no coincide con su cuenta");
                    session.setAttribute("TipoMensaje", "NODio");
                }
            } else {
                session.setAttribute("Mensaje", "Error al tratar de modificar la contraseña, las contraseñas no coinciden");
                session.setAttribute("TipoMensaje", "NODio");
            }
            response.sendRedirect("View/Perfil.jsp");
        } else if (request.getParameter("accion").equals("cambiarestadousuario")) {
            Codigo = (String) request.getParameter("codigousuario");
            Estado = (String) request.getParameter("estadousuario");
            JSONObject obj = new JSONObject();
            JSONObject ob = new JSONObject();
            b = usu.setCambiarEstadoUsaurio(Codigo, Estado);
            if (b) {
                boolean p = usu.getDatosParaEstado(Codigo);
                if (p) {
                    Tipo_Documento = usu.getTipo_Documento();
                    No_Documento = usu.getNo_Documento();
                    Nombre = usu.getNombre();
                    Telefono = usu.getTelefono();
                    Direccion = usu.getDireccion();
                    celular = usu.getCelular();
                    correo = usu.getCorreo();
                    if (Estado.equals("Aprobado")) {
                        b = msm.setMensajeModificarAprobar(correo, celular, Nombre, Tipo_Documento, No_Documento, Telefono, Direccion);
                        if (b) {
                            ob.put("mensaje", "Se ha modificado el estado del usuario correctamente.");
                            ob.put("tipomensaje", "Dio");

                        } else {
                            ob.put("mensaje", "Se ha modificado el estado del usuario, pero no se logro mandar una notificación a dicho usuario.");
                            ob.put("tipomensaje", "NODio");
                        }
                    } else if (Estado.equals("Desaprobado")) {
                        b = msm.setMensajeModificarDesaprobar(correo, celular, Nombre, Tipo_Documento, No_Documento, Telefono, Direccion);
                        if (b) {
                            ob.put("mensaje", "Se ha modificado el estado del usuario correctamente.");
                            ob.put("tipomensaje", "Dio");
                        } else {
                            ob.put("mensaje", "Se ha modificado el estado del usuario, pero no se logro mandar una notificación a dicho usuario.");
                            ob.put("tipomensaje", "NODio");
                        }
                    }
                }
            } else {
                ob.put("mensaje", "Ocurrio un error al modificar el estado del usuario.");
                ob.put("tipomensaje", "NODio");
            }
            PrintWriter out = response.getWriter();
            obj.put("1", ob);
            out.print(obj);
            out.close();
        } else if (request.getParameter("accion").equals("usuariospendientes")) {
            String[][] Datos = usu.BuscarDatosUsuarioPendientes();
            JSONObject obj = new JSONObject();
            int i = 0;
            for (String row[] : Datos) {
                JSONObject ob = new JSONObject();

                ob.put("codigo", row[0]);
                ob.put("tipo", row[1]);
                ob.put("tipodocumento", row[2]);
                ob.put("numerodocumento", row[3]);
                ob.put("nombre", row[4]);
                ob.put("telefono", row[5]);
                ob.put("celular", row[6]);
                ob.put("ciudad", row[7]);
                ob.put("correo", row[8]);
                ob.put("direccion", row[9]);
                ob.put("estado", row[10]);
                obj.put(Integer.toString(i), ob);

                i++;
            }
            PrintWriter out = response.getWriter();
            out.println(obj);
            out.close();
        } else if (request.getParameter("accion").equals("todosusuarios")) {
            String[][] Datos = usu.BuscarDatosUsuarioPendienteTodos();
            JSONObject obj = new JSONObject();
            int i = 0;
            for (String row[] : Datos) {
                JSONObject ob = new JSONObject();

                ob.put("codigo", row[0]);
                ob.put("tipo", row[1]);
                ob.put("tipodocumento", row[2]);
                ob.put("numerodocumento", row[3]);
                ob.put("nombre", row[4]);
                ob.put("telefono", row[5]);
                ob.put("celular", row[6]);
                ob.put("ciudad", row[7]);
                ob.put("correo", row[8]);
                ob.put("direccion", row[9]);
                ob.put("estado", row[10]);
                obj.put(Integer.toString(i), ob);

                i++;
            }
            PrintWriter out = response.getWriter();
            out.println(obj);
            out.close();
        } else if (request.getParameter("accion").equals("loginmodal")) {
            correo = request.getParameter("correo");
            Contrasenia = request.getParameter("contrasenia");
            JSONObject obj = new JSONObject();
            JSONObject ob = new JSONObject();
            b = usu.getlogin(correo, Contrasenia);
            if (b) {
                Codigo = usu.getCodigo();
                Tipo_Documento = usu.getTipo_Documento();
                No_Documento = usu.getNo_Documento();
                Nombre = usu.getNombre();
                Telefono = usu.getTelefono();
                Direccion = usu.getDireccion();
                celular = usu.getCelular();
                Rol = usu.getTipo();
                Ciudad = usu.getCiudad();
                Departamento = usu.getDepartamento();

                session.setAttribute("Codigo", Codigo);
                session.setAttribute("Rol", Rol);
                session.setAttribute("Tipo_Documento", Tipo_Documento);
                session.setAttribute("No_Documento", No_Documento);
                session.setAttribute("Nombre", Nombre);
                session.setAttribute("Telefono", Telefono);
                session.setAttribute("Celular", celular);
                session.setAttribute("Correo", correo);
                session.setAttribute("Direccion", Direccion);
                session.setAttribute("Ciudad", Ciudad);
                session.setAttribute("Departamento", Departamento);
                obj.put("1", b);
                PrintWriter out = response.getWriter();
                out.println(obj);
                out.close();
            } else {
                obj.put("mensaje", usu.getMensaje());
                ob.put("0", obj);
                PrintWriter out = response.getWriter();
                out.println(ob);
                out.close();
            }

        } else {
            url = "View/login.jsp";
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
