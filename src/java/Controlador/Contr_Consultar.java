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
    Usuario usu = new Usuario();
    public ArrayList<Usuario> BuscarDatosUsuariosTodos()
    {
        
        ArrayList<Usuario> Datos = usu.BuscarDatosUsuarioTodos();
                
        return Datos;
    }
    
    public String[] BuscarDatosUsuario(String Codigo)
    {
        String[] Datos = usu.getDatosUsuario(Codigo);
                
        return Datos;
    }
    public ArrayList<Tipo_Usuario> BuscarDatosTipoUsuariosTodos()
    {
        Tipo_Usuario Tusu = new Tipo_Usuario();
        ArrayList<Tipo_Usuario> Datos = Tusu.BuscarDatosTipoUsuarioTodos();
                
        return Datos;
    }
}
