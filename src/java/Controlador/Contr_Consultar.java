/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import Modelo.Mensajeria;
import Modelo.Tipo_Usuario;
import java.util.ArrayList;

/**
 *
 * @author santi
 */
public class Contr_Consultar {
    
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
    public boolean setAprobarUsaurio(String Codigo)
    {
        Usuario usu = new Usuario();
        Mensajeria msm = new Mensajeria();
        boolean b = usu.setaprobarUsaurio(Codigo);
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
            }
        }
        return b;
    }
    public boolean setDesaprobarUsaurio(String Codigo)
    {
        Usuario usu = new Usuario();
        Mensajeria msm = new Mensajeria();
        boolean b = usu.setdesaprobarUsaurio(Codigo);
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
            }
        }
        return b;
    }
}
