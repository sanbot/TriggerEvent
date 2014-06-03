/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Mensajeria;
import Modelo.Tipo_Usuario;
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
        //se encodifica las peticiones
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //se instancia las clases necesarias
        Usuario usu = new Usuario();
        Mensajeria msm = new Mensajeria();
        //se declaran las variables necesarias
        String script, Nombre, Codigo, Tipo_Documento, Estado, No_Documento, Telefono, Direccion, correo, celular, url, CodVer, CodigoVerificacion, Ciudad, Departamento;
        String Password, REPassword, Rol, Contrasenia;
        //se crea una variable para manejar las sesiones
        HttpSession session = request.getSession(true);
        //se declara un boolean para los resultados
        boolean b = false;
        /*Se evalua cada una de las posibles peticiones*/
        if (request.getParameter("login") != null) {

            //se declaran las variables necesarias y se obtienen los datos
            correo = request.getParameter("correo");
            Contrasenia = request.getParameter("contrasenia");
            /*Se ejecuta el metodo para iniciar sesion de la clase modelo*/
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

                /*Se guardan los datos del usuario en la sesion*/
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

                url = "View/EventoRecomendado.jsp";
                response.sendRedirect(url);
            } else {
                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                session.setAttribute("Mensaje", usu.getMensaje());
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/index.jsp";
                response.sendRedirect(url);
            }

        } else if (request.getParameter("ModificarPerfil") != null) {
            //se declaran las variables necesarias y se obtienen los datos
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

            /*Se ejecuta el metodo actualizar datos de la clase modelo*/
            b = usu.actualizardatos(Codigo, Tipo_Documento, No_Documento, Nombre, Telefono, celular, correo, Direccion, Ciudad);
            if (b) {
                /*Se guardan los datos del usuario en la sesion*/
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

                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                session.setAttribute("Mensaje", "Sus datos han sido modificados correctamente");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/Perfil.jsp";
                response.sendRedirect(url);
            } else {
                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                session.setAttribute("Mensaje", "Ocurrió un problema inesperado al modificar sus datos. Estamos trabajando para solucionar este problema.");
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/ModificarPerfil.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("EnviarCodigoVer") != null) {
            //se declaran las variables necesarias y se obtienen los datos
            Nombre = request.getParameter("Nombre");
            celular = request.getParameter("Celular");
            correo = request.getParameter("Correo");
            CodVer = usu.CodVer();

            /*Se ejecuta el metodo para enviar codigo de verifiacion de la clase modelo*/
            b = msm.EnviarCodVer(correo, celular, Nombre, CodVer);
            if (b) {
                /*Se envian datos a la session para poder que no cambien en el registro*/
                session.setAttribute("Registrar_Nombre", Nombre);
                session.setAttribute("Registrar_Celular", celular);
                session.setAttribute("Registrar_Correo", correo);
                session.setAttribute("Registrar_CodigoVer", CodVer);

                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                session.setAttribute("Mensaje", "Verifique su correo, se le ha enviado el código de verificación.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/RegistrarUsuario.jsp";
                response.sendRedirect(url);
            } else {
                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                session.setAttribute("Mensaje", "Ocurrió un problema inesperado al enviar el código de verificación. Estamos trabajando para solucionar este problema.");
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/RegistrarUsuario.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("REnviarCodigoVer") != null) {
            //se declaran las variables necesarias y se obtienen los datos
            Nombre = (String) session.getAttribute("Registrar_Nombre");
            celular = (String) session.getAttribute("Registrar_Celular");
            correo = (String) session.getAttribute("Registrar_Correo");
            CodVer = (String) session.getAttribute("Registrar_CodigoVer");

            /*Se ejecuta el metodo para enviar codigo de verifiacion de la clase modelo*/
            b = msm.EnviarCodVer(correo, celular, Nombre, CodVer);
            if (b) {
                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                session.setAttribute("Mensaje", "Verifique su correo, se le ha enviado el código de verificación.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/RegistrarUsuario.jsp";
                response.sendRedirect(url);
            } else {
                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                session.setAttribute("Mensaje", "currió un problema inesperado al enviar el código de verificación. Estamos trabajando para solucionar este problema.");
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/RegistrarUsuario.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("LimpiarDatosUsuario") != null) {
            /**
             * Se limpian los datos de la sesion
             */
            session.setAttribute("Registrar_Nombre", null);
            session.setAttribute("Registrar_Celular", null);
            session.setAttribute("Registrar_Correo", null);
            session.setAttribute("Registrar_CodigoVer", null);

            /*Se redirecciona*/
            url = "View/RegistrarUsuario.jsp";
            response.sendRedirect(url);

        } else if (request.getParameter("RegistrarUsuario") != null) {
            //se declaran las variables necesarias y se obtienen los datos

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
                    /*Se ejecuta el metodo para registrar al usuario de la clase modelo*/
                    b = usu.ingresarUsuario(Rol, Tipo_Documento, No_Documento, Nombre, Telefono, celular, correo, Direccion, Password, Ciudad);
                    if (b) {
                        session.setAttribute("Registrar_Nombre", null);
                        session.setAttribute("Registrar_Celular", null);
                        session.setAttribute("Registrar_Correo", null);
                        session.setAttribute("Registrar_CodigoVer", null);

                        /*Se guarda un mensaje mediante las sesiones
                         y se redirecciona*/
                        session.setAttribute("Mensaje", "Su cuenta ha sido creada satisfactoriamente.");
                        session.setAttribute("TipoMensaje", "Dio");
                        url = "View/RegistrarUsuario.jsp";
                        response.sendRedirect(url);
                    } else {
                        /*Se guarda un mensaje mediante las sesiones
                         y se redirecciona*/
                        session.setAttribute("Mensaje", usu.getMensaje());
                        session.setAttribute("TipoMensaje", "NODio");
                        url = "View/RegistrarUsuario.jsp";
                        response.sendRedirect(url);
                    }
                } else {
                    /*Se guarda un mensaje mediante las sesiones
                     y se redirecciona*/
                    session.setAttribute("Mensaje", "El código de verificación no coincide con el enviado.");
                    session.setAttribute("TipoMensaje", "NODio");
                    url = "View/RegistrarUsuario.jsp";
                    response.sendRedirect(url);
                }
            } else {
                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                session.setAttribute("Mensaje", "ErrorPass");
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/RegistrarUsuario.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("recucontrasenia") != null) {
            //se declaran las variables necesarias y se obtienen los datos
            correo = request.getParameter("correo");
            b = usu.getrecordarContrasenia(correo);
            if (b) {
                String contra = usu.getContrasenia();
                Nombre = usu.getNombre();
                /*Se ejecuta el metodo para enviar la contraseña al correo, el metodo se encuentra
                 en la clase modelo*/
                boolean c = msm.recordarcontrasenia(correo, contra, Nombre);
                if (c) {
                    /*Se guarda un mensaje mediante las sesiones
                     y se redirecciona*/
                    session.setAttribute("Mensaje", "Verifique su correo, se le ha enviado su contraseña.");
                    session.setAttribute("TipoMensaje", "Dio");
                    url = "View/index.jsp";
                    response.sendRedirect(url);
                } else {
                    /*Se guarda un mensaje mediante las sesiones
                     y se redirecciona*/
                    session.setAttribute("Mensaje", "Ocurrió un problema inesperado al enviar su contraseña. Estamos trabajando para solucionar este problema.");
                    session.setAttribute("TipoMensaje", "NODio");
                    url = "View/index.jsp";
                    response.sendRedirect(url);
                }
            } else {
                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                session.setAttribute("Mensaje", "El correo diligenciado no concuerda con ninguna cuenta registrada.");
                session.setAttribute("TipoMensaje", "NODio");

                url = "View/index.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("ModificarUsuarioTodos") != null) {
            //se declaran las variables necesarias y se obtienen los datos
            Nombre = request.getParameter("Nombre");
            Codigo = request.getParameter("Codigo");
            Rol = request.getParameter("Tipo");
            Tipo_Documento = request.getParameter("Tipo_Documento");
            No_Documento = request.getParameter("No_Documento");
            Telefono = request.getParameter("Telefono");
            celular = request.getParameter("Celular");
            correo = request.getParameter("Correo");
            Direccion = request.getParameter("Direccion");
            Ciudad = request.getParameter("Ciudad");

            /*Se ejecuta el metodo para acturalizar los datos del usario en la clase modelo*/
            b = usu.actualizardatosUsuario(Codigo, Nombre, Rol, Tipo_Documento, No_Documento, Telefono, celular, correo, Direccion, Ciudad);
            if (b) {

                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                session.setAttribute("Mensaje", "Sus datos han sido modificados correctamente.");
                session.setAttribute("TipoMensaje", "Dio");
                url = "View/ConsultaUsuario.jsp";
                response.sendRedirect(url);
            } else {
                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                session.setAttribute("Mensaje", "Ocurrió un problema inesperado al modificar sus datos. Estamos trabajando para solucionar este problema.");
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/MUsuario.jsp?Codigo=" + Codigo;
                response.sendRedirect(url);
            }
        } else if (request.getParameter("RegistrarTodoUsuario") != null) {
            //se declaran las variables necesarias y se obtienen los datos

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
                /*Se ejecuta el metodo para registrar los datos del usario en la clase modelo*/
                b = usu.ingresarUsuario(Rol, Tipo_Documento, No_Documento, Nombre, Telefono, celular, correo, Direccion, Password, Ciudad);
                if (b) {
                    session.setAttribute("Registrar_Nombre", null);
                    session.setAttribute("Registrar_Celular", null);
                    session.setAttribute("Registrar_Correo", null);
                    session.setAttribute("Registrar_CodigoVer", null);

                    /*Se guarda un mensaje mediante las sesiones
                     y se redirecciona*/
                    session.setAttribute("Mensaje", "El usuario ha sido registrado correctamente.");
                    session.setAttribute("TipoMensaje", "Dio");
                    url = "View/ConsultaUsuario.jsp";
                    response.sendRedirect(url);
                } else {
                    /*Se guarda un mensaje mediante las sesiones
                     y se redirecciona*/
                    session.setAttribute("TipoMensaje", "NODio");
                    session.setAttribute("Mensaje", usu.getMensaje());
                    url = "View/ConsultaUsuario.jsp";
                    response.sendRedirect(url);
                }
            } else {
                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                session.setAttribute("Mensaje", "Las contraseñas no coinciden.");
                session.setAttribute("TipoMensaje", "NODio");
                url = "View/ConsultaUsuario.jsp";
                response.sendRedirect(url);
            }
        } else if (request.getParameter("CambiarContrasenia") != null) {
            //se declaran las variables necesarias y se obtienen los datos
            Codigo = (String) session.getAttribute("Codigo");
            String Contr = request.getParameter("ContraseniaActual");
            String ContrNueva = request.getParameter("ContraseniaNueva");
            String ContrReContra = request.getParameter("ContraseniaRepetir");
            if (ContrNueva.equals(ContrReContra)) {
                /*Se ejecuta el metodo para comprobar las contraseñas del usario en la clase modelo*/
                if (usu.getConfirmarContrasenia(Codigo, Contr)) {
                    /*Se ejecuta el metodo para cambiar la contraseña del usario en la clase modelo*/
                    if (usu.setCambioContrasenia(Codigo, ContrNueva)) {
                        session.setAttribute("Mensaje", "La contraseña ha sido modificada exitosamente.");
                        session.setAttribute("TipoMensaje", "Dio");
                    } else {
                        /*Se guarda un mensaje mediante las sesiones
                         y se redirecciona*/
                        session.setAttribute("Mensaje", "Ocurrió un problema inesperado al modificar la contraseña. Estamos trabajando para solucionar este problema.");
                        session.setAttribute("TipoMensaje", "NODio");
                    }
                } else {
                    /*Se guarda un mensaje mediante las sesiones
                     y se redirecciona*/
                    session.setAttribute("Mensaje", "La contraseña actual no coincide con su cuenta");
                    session.setAttribute("TipoMensaje", "NODio");
                }
            } else {
                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                session.setAttribute("Mensaje", "Error al tratar de modificar la contraseña, las contraseñas no coinciden");
                session.setAttribute("TipoMensaje", "NODio");
            }
            /*Se redirecciona*/
            response.sendRedirect("View/Perfil.jsp");
        } else if (request.getParameter("accion").equals("cambiarestadousuario")) {
            //se declaran las variables necesarias y se obtienen los datos
            Codigo = (String) request.getParameter("codigousuario");
            Estado = (String) request.getParameter("estadousuario");
            /*Se declaran JSON Para imprimir los datos*/
            JSONObject obj = new JSONObject();
            JSONObject ob = new JSONObject();
            /*Se ejecuta el metodo para cambiar el estado del usario en la clase modelo*/
            b = usu.setCambiarEstadoUsaurio(Codigo, Estado);
            if (b) {
                /*Se ejecuta el metodo para obtener los datos del usario en la clase modelo*/
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
                        /*Se ejecuta el metodo para enviar un correo al usario en la clase modelo*/
                        b = msm.setMensajeModificarAprobar(correo, celular, Nombre, Tipo_Documento, No_Documento, Telefono, Direccion);
                        if (b) {
                            /*Se encodifica a JSON*/
                            ob.put("mensaje", "Se ha modificado el estado del usuario correctamente.");
                            ob.put("tipomensaje", "Dio");

                        } else {
                            /*Se encodifica a JSON*/
                            ob.put("mensaje", "Se ha modificado el estado del usuario, pero no se logro mandar una notificación a dicho usuario.");
                            ob.put("tipomensaje", "NODio");
                        }
                    } else if (Estado.equals("Desaprobado")) {
                        /*Se ejecuta el metodo para enviar un correo al usario en la clase modelo*/
                        b = msm.setMensajeModificarDesaprobar(correo, celular, Nombre, Tipo_Documento, No_Documento, Telefono, Direccion);
                        if (b) {
                            /*Se encodifica a JSON*/
                            ob.put("mensaje", "Se ha modificado el estado del usuario correctamente.");
                            ob.put("tipomensaje", "Dio");
                        } else {
                            /*Se encodifica a JSON*/
                            ob.put("mensaje", "Se ha modificado el estado del usuario, pero no se logro mandar una notificación a dicho usuario.");
                            ob.put("tipomensaje", "NODio");
                        }
                    }
                }
            } else {
                /*Se encodifica a JSON*/
                ob.put("mensaje", "Ocurrio un error al modificar el estado del usuario. Estamos trabajando para solucionar este problema.");
                ob.put("tipomensaje", "NODio");
            }
            /*Se declara lo necesario para imprimir el json y se imprime*/
            PrintWriter out = response.getWriter();
            obj.put("1", ob);
            out.print(obj);
            out.close();
        } else if (request.getParameter("accion").equals("usuariospendientes")) {
            //se declaran las variables necesarias y se obtienen los datos
            JSONObject obj = new JSONObject();
            /*Se ejecuta el metodo para biscar los datos de los usarios pendientes en la clase modelo*/
            String[][] Datos = usu.BuscarDatosUsuarioPendientes();
            int i = 0;
            for (String row[] : Datos) {
                JSONObject ob = new JSONObject();

                /*Se encodifica a JSON*/
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
            /*Se declara lo necesario para imprimir el json y se imprime*/
            PrintWriter out = response.getWriter();
            out.println(obj);
            out.close();
        } else if (request.getParameter("accion").equals("todosusuarios")) {
            //se declaran las variables necesarias y se obtienen los datos
            JSONObject obj = new JSONObject();
            /*Se ejecuta el metodo para buscar todos los datos de los usarios en la clase modelo*/
            String[][] Datos = usu.BuscarDatosUsuarioPendienteTodos();
            int i = 0;
            for (String row[] : Datos) {
                JSONObject ob = new JSONObject();

                /*Se encodifica a JSON*/
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
            /*Se declara lo necesario para imprimir el json y se imprime*/
            PrintWriter out = response.getWriter();
            out.println(obj);
            out.close();
        } else if (request.getParameter("accion").equals("loginmodal")) {
            //se declaran las variables necesarias y se obtienen los datos
            correo = request.getParameter("correo");
            Contrasenia = request.getParameter("contrasenia");
            /*Se declaran JSON Para imprimir los datos*/
            JSONObject obj = new JSONObject();
            JSONObject ob = new JSONObject();
            /*Se ejecuta el metodo para iniciar sesion de usario en la clase modelo*/
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

                /*Se guardan los del usuario en la sesion*/
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
                /*Se encodifica a JSON*/
                obj.put("1", b);
                /*Se declara lo necesario para imprimir el json y se imprime*/
                PrintWriter out = response.getWriter();
                out.println(obj);
                out.close();
            } else {
                /*Se encodifica a JSON*/
                obj.put("mensaje", usu.getMensaje());
                ob.put("0", obj);
                /*Se declara lo necesario para imprimir el json y se imprime*/
                PrintWriter out = response.getWriter();
                out.println(ob);
                out.close();
            }

        } else if (request.getParameter("accion").equals("tipos_usuarios")) {
            Tipo_Usuario Tusu = new Tipo_Usuario();
            String[][] Datos = Tusu.BuscarDatosTipoUsuarioTodos();
            /*Se declaran JSON Para imprimir los datos*/
            JSONObject obj = new JSONObject();

            PrintWriter out = response.getWriter();
            int i = 0;
            for (String[] rows : Datos) {
                JSONObject ob = new JSONObject();
                ob.put("codigo", rows[0]);
                ob.put("tipo", rows[1]);
                obj.put(i, ob);
                i++;
            }
            out.print(obj);
            out.close();
        } else if (request.getParameter("accion").equals("listar_empresas")) {
            String[][] Datos = usu.BuscarDatosEmpresa();
            /*Se declaran JSON Para imprimir los datos*/
            JSONObject obj = new JSONObject();

            PrintWriter out = response.getWriter();
            int i = 0;
            for (String[] rows : Datos) {
                JSONObject ob = new JSONObject();
                ob.put("no_documento", rows[0]);
                ob.put("nombre", rows[1]);
                obj.put(i, ob);
                i++;
            }
            out.print(obj);
            out.close();
        } else if (request.getParameter("accion").equals("datos_usuario")) {
            Codigo = request.getParameter("codigo");
            String Datos[] = usu.getDatosUsuario(Codigo);
            JSONObject obj = new JSONObject();
            JSONObject ob = new JSONObject();

            ob.put("codigo", Datos[0]);
            ob.put("codigo_tipo", Datos[1]);
            ob.put("tipo", Datos[2]);
            ob.put("tipo_documento", Datos[3]);
            ob.put("no_documento", Datos[4]);
            ob.put("nombre", Datos[5]);
            ob.put("telefono", Datos[6]);
            ob.put("celular", Datos[7]);
            ob.put("correo", Datos[8]);
            ob.put("direccion", Datos[9]);
            ob.put("estado", Datos[10]);
            ob.put("nombre_ciudad", Datos[11]);
            ob.put("codigo_departamento", Datos[12]);
            ob.put("nombre_departamento", Datos[13]);
            ob.put("codigo_ciudad", Datos[14]);

            obj.put(1, ob);

            PrintWriter out = response.getWriter();
            out.print(obj);
            out.close();
        } else if (request.getParameter("accion").equals("cantidad_usuario_pendiente")) {
            PrintWriter out = response.getWriter();
            String Dato = usu.getCantidadPendientes();
            out.print(Dato);
            out.close();
        } else if (request.getParameter("accion").equals("login_android")) {
            //se declaran las variables necesarias y se obtienen los datos
            correo = request.getParameter("correo");
            Contrasenia = request.getParameter("contrasenia");
            /*Se declaran JSON Para imprimir los datos*/
            JSONObject obj = new JSONObject();
            JSONObject ob = new JSONObject();
            /*Se ejecuta el metodo para iniciar sesion de usario en la clase modelo*/
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
                Estado = usu.getEstado();

                /*Se encodifica a JSON*/
                obj.put("Codigo", Codigo);
                obj.put("Tipo", Rol);
                obj.put("Tipo_Documento", Tipo_Documento);
                obj.put("No_Documento", No_Documento);
                obj.put("Nombre", Nombre);
                obj.put("Contrasenia", Contrasenia);
                obj.put("Telefono", Telefono);
                obj.put("No_Celular", celular);
                obj.put("Direccion", Direccion);
                obj.put("Estado", Estado);
                /*Se declara lo necesario para imprimir el json y se imprime*/
                PrintWriter out = response.getWriter();
                out.println(obj);
                out.close();
            } else {
                /*Se declara lo necesario para imprimir el json y se imprime*/
                PrintWriter out = response.getWriter();
                out.println("null");
                out.close();
            }

        } else if (request.getParameter("accion").equals("registro_android")) {
            //se declaran las variables necesarias y se obtienen los datos

            Rol = request.getParameter("tipo");
            Tipo_Documento = request.getParameter("tipo_documento");
            No_Documento = request.getParameter("no_documento");
            Nombre = request.getParameter("nombre");
            Password = request.getParameter("contrasenia");
            Telefono = request.getParameter("telefono");
            celular = request.getParameter("celular");
            correo = request.getParameter("correo");
            Direccion = request.getParameter("direccion");
            Ciudad = request.getParameter("ciudad");

            /*Se ejecuta el metodo para registrar los datos del usario en la clase modelo*/
            b = usu.ingresarUsuario(Rol, Tipo_Documento, No_Documento, Nombre, Telefono, celular, correo, Direccion, Password, Ciudad);
            if (b) {
                PrintWriter out = response.getWriter();
                out.println("Se ha registrado exitosamente");
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                out.println(usu.getMensaje());
                out.close();
            }
        } else if (request.getParameter("accion").equals("recup_contra_android")) {
            //se declaran las variables necesarias y se obtienen los datos
            correo = request.getParameter("correo");
            b = usu.getrecordarContrasenia(correo);
            if (b) {
                String contra = usu.getContrasenia();
                Nombre = usu.getNombre();
                /*Se ejecuta el metodo para enviar la contraseña al correo, el metodo se encuentra
                 en la clase modelo*/
                boolean c = msm.recordarcontrasenia(correo, contra, Nombre);
                if (c) {
                    /*Se guarda un mensaje mediante las sesiones
                     y se redirecciona*/
                    PrintWriter out = response.getWriter();
                    out.println("Verifique su correo, se le ha enviado su contraseña.");
                    out.close();
                } else {
                    /*Se guarda un mensaje mediante las sesiones
                     y se redirecciona*/
                    PrintWriter out = response.getWriter();
                    out.println("Ocurrió un problema inesperado al enviar su contraseña. Estamos trabajando para solucionar este problema.");
                    out.close();
                }
            } else {
                /*Se guarda un mensaje mediante las sesiones
                 y se redirecciona*/
                PrintWriter out = response.getWriter();
                out.println("El correo diligenciado no coincide con ningún correo registrado.");
                out.close();
            }
        } else if (request.getParameter("accion").equals("consultar_perfil_android")) {
            //se declaran las variables necesarias y se obtienen los datos
            Codigo = request.getParameter("codigo");
            String[] Datos = usu.getDatosUsuario(Codigo);
            JSONObject obj = new JSONObject();

            obj.put("Tipo", Datos[2]);
            obj.put("Tipo_Documento", Datos[3]);
            obj.put("No_Documento", Datos[4]);
            obj.put("Nombre", Datos[5]);
            obj.put("Telefono", Datos[6]);
            obj.put("No_Celular", Datos[7]);
            obj.put("Correo", Datos[8]);
            obj.put("Direccion", Datos[9]);
            obj.put("NombreCiudad", Datos[11]);
            obj.put("NombreDepartamento", Datos[13]);
            obj.put("CodigoCiudad", Datos[14]);
            obj.put("CodigoDepartamento", Datos[12]);

            PrintWriter out = response.getWriter();
            out.println(obj);
            out.close();
        } else if (request.getParameter("accion").equals("modificar_contra_android")) {
            //se declaran las variables necesarias y se obtienen los datos
            Codigo = request.getParameter("Codigo");
            String Contr = request.getParameter("ContraseniaActual");
            String ContrNueva = request.getParameter("ContraseniaNueva");
            String ContrReContra = request.getParameter("ContraseniaRepetir");
            if (ContrNueva.equals(ContrReContra)) {
                /*Se ejecuta el metodo para comprobar las contraseñas del usario en la clase modelo*/
                if (usu.getConfirmarContrasenia(Codigo, Contr)) {
                    /*Se ejecuta el metodo para cambiar la contraseña del usario en la clase modelo*/
                    if (usu.setCambioContrasenia(Codigo, ContrNueva)) {
                        PrintWriter out = response.getWriter();
                        out.println("La contraseña ha sido modificada exitosamente.");
                        out.close();
                    } else {
                        PrintWriter out = response.getWriter();
                        out.println("Ocurrió un problema inesperado al modificar la contraseña. Estamos trabajando para solucionar este problema.");
                        out.close();
                    }
                } else {
                    PrintWriter out = response.getWriter();
                    out.println("La contraseña actual no coincide con su cuenta");
                    out.close();
                }
            } else {
                PrintWriter out = response.getWriter();
                out.println("Error al tratar de modificar la contraseña, las contraseñas no coinciden");
                out.close();
            }
        } else if (request.getParameter("accion").equals("modificar_perfil_android")) {
            //se declaran las variables necesarias y se obtienen los datos
            Codigo = request.getParameter("Codigo");
            Telefono = request.getParameter("Telefono");
            Direccion = request.getParameter("Direccion");
            Ciudad = request.getParameter("Ciudad");

            /*Se ejecuta el metodo actualizar datos de la clase modelo*/
            b = usu.actualizardatos(Codigo, Telefono, Direccion, Ciudad);
            if (b) {
                PrintWriter out = response.getWriter();
                out.println("Sus datos han sido modificados correctamente");
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                out.println("Ocurrió un problema inesperado al modificar sus datos. Estamos trabajando para solucionar este problema.");
                out.close();
            }
        } else {
            /*Se redirecciona sino se da ninguna de las peticiones anteriores*/
            url = "View/index.jsp";
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
