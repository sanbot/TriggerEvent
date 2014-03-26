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
    public ArrayList<Usuario> BuscarDatosUsuariosTodos()
    {
        Usuario usu = new Usuario();
        ArrayList<Usuario> Datos = usu.BuscarDatosUsuarioTodos();
                
        return Datos;
    }
    public ArrayList<Tipo_Usuario> BuscarDatosTipoUsuariosTodos()
    {
        Tipo_Usuario Tusu = new Tipo_Usuario();
        ArrayList<Tipo_Usuario> Datos = Tusu.BuscarDatosTipoUsuarioTodos();
                
        return Datos;
    }
}
