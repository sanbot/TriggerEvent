/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Modelo.Mensajeria;
import Modelo.Tipo_Usuario;
import Modelo.Departamento;
import Modelo.Ciudad;
import Modelo.Seleccion;
import Modelo.Evento;
import java.sql.Blob;

/**
 *
 * @author santi
 */
public class Contr_Consultar {
    
    String Mensaje;

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }
    
    
    
    public String[][] BuscarDatosUsuariosTodos()
    {
        Usuario usu = new Usuario();
        String[][] Datos = usu.BuscarDatosUsuarioTodos();
                
        return Datos;
    }
    
    public String[][] BuscarDatosUsuariosPendientes()
    {
        Usuario usu = new Usuario();
        String[][] Datos = usu.BuscarDatosUsuarioPendientes();
                
        return Datos;
    }
    
    public String[] BuscarDatosUsuario(String Codigo)
    {
        Usuario usu = new Usuario();
        String[] Datos = usu.getDatosUsuario(Codigo);
                
        return Datos;
    }
    public String[][] BuscarDatosTipoUsuariosTodos()
    {
        Tipo_Usuario Tusu = new Tipo_Usuario();
        String[][] Datos = Tusu.BuscarDatosTipoUsuarioTodos();
                
        return Datos;
    }
    
    public String[][] BuscarDatosEmpresa()
    {
        Usuario usu = new Usuario();
        String[][] Datos = usu.BuscarDatosEmpresa();
                
        return Datos;
    }
    
    public boolean setCambiarEstadoUsaurio(String Codigo, String Estado)
    {
        Usuario usu = new Usuario();
        Mensajeria msm = new Mensajeria();
        boolean b;
        if(Estado.equals("true"))
        {
            b = usu.setaprobarUsaurio(Codigo);
            if(b)
            {
                boolean p = usu.getDatosParaEstado(Codigo);
                if(p)
                {
                    String Tipo_Documento = usu.getTipo_Documento();
                    String No_Documento = usu.getNo_Documento();
                    String Nombre = usu.getNombre();
                    String Telefono = usu.getTelefono();
                    String Direccion = usu.getDireccion();
                    String celular = usu.getCelular();
                    String correo = usu.getCorreo();
                    b = msm.setMensajeModificarAprobar(correo, celular, Nombre, Tipo_Documento, No_Documento, Telefono, Direccion);
                    if(b)
                    {
                        this.setMensaje("Se ha modificado el estado del usuario correctamente.");
                    }
                    else
                    {
                        this.setMensaje("Se ha modificado el estado del usuario, pero no se logro mandar una notificación a dicho usuario.");
                    }
                }
            }
            else 
            {
                this.setMensaje("Ocurrio un error al tratar de modificar el estado del usuario.");
            }
        }
        else if (Estado.equals("false"))
        {
            b = usu.setdesaprobarUsaurio(Codigo);
            if(b)
            {
                boolean p = usu.getDatosParaEstado(Codigo);
                if(p)
                {
                    String Tipo_Documento = usu.getTipo_Documento();
                    String No_Documento = usu.getNo_Documento();
                    String Nombre = usu.getNombre();
                    String Telefono = usu.getTelefono();
                    String Direccion = usu.getDireccion();
                    String celular = usu.getCelular();
                    String correo = usu.getCorreo();
                    b = msm.setMensajeModificarDesaprobar(correo, celular, Nombre, Tipo_Documento, No_Documento, Telefono, Direccion);
                    if(b)
                    {
                        this.setMensaje("Se ha modificado el estado del usuario correctamente.");
                    }
                    else
                    {
                        this.setMensaje("Se ha modificado el estado del usuario, pero no se logro mandar una notificación a dicho usuario.");
                    }
                }

            }
            else 
            {
                this.setMensaje("Ocurrio un error al tratar de modificar el estado del usuario.");
            }
        }
        else
        {
            b = false;
            this.setMensaje("");
        }
        return b;
    }
    public String[][] BuscarDatosDepartamentoTodos()
    {
        Departamento dep = new Departamento();
        String[][] Datos = dep.BuscarDatosDepartamentoTodos();
                
        return Datos;
    }
    
    public String[][] BuscarDatosCuidadTodos()
    {
        Ciudad ciu = new Ciudad();
        String[][] Datos = ciu.BuscarDatosCiudadTodos();
                
        return Datos;
    }
    public String[][] BuscarDatosSeleccion()
    {
        Seleccion sel = new Seleccion();
        String Datos[][] = sel.getDatosSeleccion();
        return Datos;
    }
    public Blob BuscarImagenes(String Codigo)
    {
        Seleccion sel = new Seleccion();
        Blob Datos = sel.getImagenSeleccion(Codigo);
        return Datos;
    }
    public Blob BuscarImagenesEvento(String Codigo)
    {
        Evento eve = new Evento();
        Blob Datos = eve.getImagenEvento(Codigo);
        return Datos;
    }
    
    public String[][] getGustosNuevos(String Codigo)
    {
        Seleccion sel = new Seleccion();
        String Datos[][] = sel.getGustosNuevos(Codigo);
        return Datos;
    }
    public String[][] getMisGustos(String Codigo)
    {
        Seleccion sel = new Seleccion();
        String Datos[][] = sel.getMisGustos(Codigo);
        return Datos;
    }
    
    public boolean AddRemoveGustos(String Codigo, String CodigoUsuario, String Accion)
    {
        Seleccion sel = new Seleccion();
        boolean b ;
        if(Accion.equals("Nuevo"))
        {
            b = sel.AddGusto(Codigo, CodigoUsuario);
            if(b)
            {
               this.setMensaje("Se agrego el gusto satisfactoriamente.");
            }
            else
            {
                this.setMensaje("Ocurrió un error al tratar de agregar el gusto a tus gustos, por favor inténtelo de nuevo.");
            }
        }
        else
        {
            b = sel.RemoveGusto(Codigo, CodigoUsuario);
            if(b)
            {
                this.setMensaje("Se quito el gusto de tus gustos existosamente.");
            }
            else
            {
                this.setMensaje("Ocurrió un error al tratar de remover este gusto de tus gustos, por favor inténtelo nuevamente.");
            }
        }
        return b;
    }
    
    public String getCantidadPendientes()
    {
        Usuario usu = new Usuario();
        String Dato = usu.getCantidadPendientes();
        return Dato;
    }
    public int getCantidadEventosPendientes()
    {
        Evento eve = new Evento();
        int Dato = eve.CantidadEventoPendiente();
        return Dato;
    }
    
    public String[][] getBuscarDatosPrincipalesEvento()
    {
        Evento eve = new Evento();
        String [][]Datos = eve.BuscarDatosPrincipalesEventos();
        return Datos;
    }
    public String[][] getBuscarDatosPrincipalesEventoPendiente()
    {
        Evento eve = new Evento();
        String [][]Datos = eve.BuscarDatosPrincipalesEventosPendientes();
        return Datos;
    }
}
