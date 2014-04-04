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
    public String[][] BuscarDatosSelccion()
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
}
