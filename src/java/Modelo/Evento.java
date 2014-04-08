/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADSI
 */
public class Evento {
    String Codigo;
    String Mensaje;
    String Nombre;
    String Fecha;
    String Descipcion;
    String Imagen;
    String Creador;
    String Rango;
    Date FechaDate;

    public Date getFechaDate() {
        return FechaDate;
    }

    public void setFechaDate(Date FechaDate) {
        this.FechaDate = FechaDate;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getDescipcion() {
        return Descipcion;
    }

    public void setDescipcion(String Descipcion) {
        this.Descipcion = Descipcion;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public String getCreador() {
        return Creador;
    }

    public void setCreador(String Creador) {
        this.Creador = Creador;
    }

    public String getRango() {
        return Rango;
    }

    public void setRango(String Rango) {
        this.Rango = Rango;
    }
    
    
    public boolean ConvertirFecha(String FechaDate)
    {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
            Date nuevaFecha;
            nuevaFecha = formatoFecha.parse(FechaDate);
            this.setFechaDate(nuevaFecha);
            return true;
        } catch (ParseException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean ValidarDosDiasFecha(Date FechaInicial)
    {
        Date FechaSistema = new Date();
        if(FechaInicial.getDate() >= FechaSistema.getDate()+2)
        {
            return true;
        }
        return false;
    }
}
