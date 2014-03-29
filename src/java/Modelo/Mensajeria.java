/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author santi
 */
public class Mensajeria {
    private final Properties properties = new Properties();
    private Session sess;
    public void Init (){
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.mail.sender", "boterosantiago6@gmail.com");
        properties.put("mail.smtp.password", "28165817");
        properties.put("mail.smtp.user", "boterosantiago6@gmail.com");
        properties.put("mail.smtp.auth", "true");
        sess = Session.getDefaultInstance(properties);
    }
   
    public boolean recordarcontrasenia(String correo, String Contra, String Nombre){
        
        String msgBody = "<table style=\"width:100%;border:0;padding:0;font-family:Arial,Helvetica,sans-serif;border-spacing:0;font-size:13px;color:#333333\">"
                + "<tbody>"
                + "<tr>"
                + "<td colspan=\"2\" style=\"color: #1f5daa; font-size: 36px;\"><b>Trigger Event</b></td>"
                + "</tr>"
                + "<td>"
                + "Hola "+Nombre+"."
                + "</td>"
                + "<td></td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"2\">"
                + "Nuestro equipo técnico ha recibido su solicitud para el envío de contraseña a su correo, a continuación se le mostrará su contraseña, guárdela un lugar seguro, gracias."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Contraseña: "
                + "</td>"
                + "<td>"
                + Contra +"."
                + "</td>"
                + "</tr>"
                + "</table>";

        try{
            Init();
            
            MimeMessage message = new MimeMessage(sess);  
            message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));  
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));  
            message.setSubject("Recuperación contraseña Trigger Event");  
            message.setContent(msgBody,"text/html");  
            Transport t = sess.getTransport("smtp");  
            t.connect((String) properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.password"));  
            t.sendMessage(message, message.getAllRecipients());  
            t.close(); 
            return true;
        }catch (MessagingException me){
            System.out.print(me.toString());
        }  
        return  false;
    }
    
    public boolean EnviarCodVer(String correo, String Numero, String Nombre, String CodVer){
        
        String msgBody = "<table style=\"width:100%;border:0;padding:0;font-family:Arial,Helvetica,sans-serif;border-spacing:0;font-size:13px;color:#333333\">"
                + "<tbody>"
                + "<tr>"
                + "<td colspan=\"2\" style=\"color: #1f5daa; font-size: 36px;\"><b>Trigger Event</b></td>"
                + "</tr>"
                + "<td>"
                + "Hola "+Nombre+"."
                + "</td>"
                + "<td></td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"2\">"
                + "Nuestro equipo técnico ha recibido su solicitud para el envío de código de verificación a su correo, a continuación se le mostrará los sus datos registrados hasta el momento: "
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Nombre de usuario:"
                + "</td>"
                + "<td>"
                + Nombre +"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Número:"
                + "</td>"
                + "<td>"
                + Numero+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Correo:"
                + "</td>"
                + "<td>"
                + correo+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Código de verificación:"
                + "</td>"
                + "<td>"
                + CodVer+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"2\">"
                + "Atentamente, "
                + "</td>"
                + "<td>"
                + "Equipo técnico Trigger Event. "
                + "</td>"
                + "</tr>"
                + "</table>";

        try{
            Init();
            
            MimeMessage message = new MimeMessage(sess);  
            message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));  
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));  
            message.setSubject("Código de verificación Trigger Event");  
            message.setContent(msgBody,"text/html");  
            Transport t = sess.getTransport("smtp");  
            t.connect((String) properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.password"));  
            t.sendMessage(message, message.getAllRecipients());  
            t.close(); 
            return true;
        }catch (MessagingException me){  
                        //Aqui se deberia o mostrar un mensaje de error o en lugar  
                        //de no hacer nada con la excepcion, lanzarla para que el modulo  
                        //superior la capture y avise al usuario con un popup, por ejemplo. 
            System.out.print(me.toString());
        }  
        return  false;
    }
    
    public boolean setMensajeModificarAprobar(String correo, String celular, String Nombre, String Tipo_Documento, String No_Documento, String Telefono,String Direccion)
    {
        String msgBody = "<table style=\"width:100%;border:0;padding:0;font-family:Arial,Helvetica,sans-serif;border-spacing:0;font-size:13px;color:#333333\">"
                + "<tbody>"
                + "<tr>"
                + "<td colspan=\"2\" style=\"color: #1f5daa; font-size: 36px;\"><b>Trigger Event</b></td>"
                + "</tr>"
                + "<td>"
                + "Hola "+Nombre+"."
                + "</td>"
                + "<td></td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"2\">"
                + "Nuestro equipo técnico le notifica que su cuenta ha sido aprobada satisfactoriamente, a continuación le recordaremos los datos con los que se registró en la aplicación"
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Su nombre: "
                + "</td>"
                + "<td>"
                + Nombre +"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Tipo de documento: "
                + "</td>"
                + "<td>"
                + Tipo_Documento+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Número de documento: "
                + "</td>"
                + "<td>"
                + No_Documento+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Número teléfonico: "
                + "</td>"
                + "<td>"
                + Telefono+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Número celular: "
                + "</td>"
                + "<td>"
                + celular+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Correo: "
                + "</td>"
                + "<td>"
                + correo+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Dirección: "
                + "</td>"
                + "<td>"
                + Direccion+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"2\">"
                + "Atentamente, "
                + "</td>"
                + "<td>"
                + "Equipo técnico Trigger Event. "
                + "</td>"
                + "</tr>"
                + "</table>";

        try{
            Init();
            
            MimeMessage message = new MimeMessage(sess);  
            message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));  
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));  
            message.setSubject("Aprobación de la cuenta en Trigger Event");  
            message.setContent(msgBody,"text/html");  
            Transport t = sess.getTransport("smtp");  
            t.connect((String) properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.password"));  
            t.sendMessage(message, message.getAllRecipients());  
            t.close(); 
            return true;
        }catch (MessagingException me){  
                        //Aqui se deberia o mostrar un mensaje de error o en lugar  
                        //de no hacer nada con la excepcion, lanzarla para que el modulo  
                        //superior la capture y avise al usuario con un popup, por ejemplo. 
            System.out.print(me.toString());
        }  
        return false;
    }
    public boolean setMensajeModificarDesaprobar(String correo, String celular, String Nombre, String Tipo_Documento, String No_Documento, String Telefono,String Direccion)
    {
        String msgBody = "<table style=\"width:100%;border:0;padding:0;font-family:Arial,Helvetica,sans-serif;border-spacing:0;font-size:13px;color:#333333\">"
                + "<tbody>"
                + "<tr>"
                + "<td colspan=\"2\" style=\"color: #1f5daa; font-size: 36px;\"><b>Trigger Event</b></td>"
                + "</tr>"
                + "<td>"
                + "Hola "+Nombre+"."
                + "</td>"
                + "<td></td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"2\">"
                + "Nuestro equipo técnico le notifica que su cuenta ha sido desaprobada, a continuación le recordaremos los datos con los que se registró en la aplicación"
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Su nombre: "
                + "</td>"
                + "<td>"
                + Nombre +"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Tipo de documento: "
                + "</td>"
                + "<td>"
                + Tipo_Documento+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Número de documento: "
                + "</td>"
                + "<td>"
                + No_Documento+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Número teléfonico: "
                + "</td>"
                + "<td>"
                + Telefono+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Número celular: "
                + "</td>"
                + "<td>"
                + celular+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Correo: "
                + "</td>"
                + "<td>"
                + correo+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td>"
                + "Dirección: "
                + "</td>"
                + "<td>"
                + Direccion+"."
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan=\"2\">"
                + "Atentamente, "
                + "</td>"
                + "<td>"
                + "Equipo técnico Trigger Event. "
                + "</td>"
                + "</tr>"
                + "</table>";

        try{
            Init();
            
            MimeMessage message = new MimeMessage(sess);  
            message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));  
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));  
            message.setSubject("Desaprobacion de la cuenta Trigger Event");  
            message.setContent(msgBody,"text/html");  
            Transport t = sess.getTransport("smtp");  
            t.connect((String) properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.password"));  
            t.sendMessage(message, message.getAllRecipients());  
            t.close(); 
            return true;
        }catch (MessagingException me){  
                        //Aqui se deberia o mostrar un mensaje de error o en lugar  
                        //de no hacer nada con la excepcion, lanzarla para que el modulo  
                        //superior la capture y avise al usuario con un popup, por ejemplo. 
            System.out.print(me.toString());
        }  
        return false;
    }
    
    public boolean contactenos(String contenido, String asunto){
      
        try{
            Init();
            
            MimeMessage message = new MimeMessage(sess);  
            message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));  
            message.addRecipient(Message.RecipientType.TO, new InternetAddress((String) properties.get("mail.smtp.user")));  
            message.setSubject(asunto);  
            message.setContent(contenido,"text/html");  
            Transport t = sess.getTransport("smtp");  
            t.connect((String) properties.get("mail.smtp.user"), (String) properties.get("mail.smtp.password"));  
            t.sendMessage(message, message.getAllRecipients());  
            t.close(); 
            return true;
        }catch (MessagingException me){  
                        //Aqui se deberia o mostrar un mensaje de error o en lugar  
                        //de no hacer nada con la excepcion, lanzarla para que el modulo  
                        //superior la capture y avise al usuario con un popup, por ejemplo. 
            System.out.print(me.toString());
        }  
        return  false;
    }
}
