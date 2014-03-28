/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
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
        return usu.setaprobarUsaurio(Codigo);
    }
    public boolean setDesaprobarUsaurio(String Codigo)
    {
        Usuario usu = new Usuario();
        return usu.setdesaprobarUsaurio(Codigo);
    }
}
