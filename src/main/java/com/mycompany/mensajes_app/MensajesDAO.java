/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mensajes_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jarvis1.1
 */
public class MensajesDAO {
    public static void crearMensjaesDB(Mensajes mensajes){
        Conexion db_connect = new Conexion();//llamamos la funcion para hacer la conexion a nuestra base de datos 
        try(Connection conexion = db_connect.get_connection()){// verificamos si se hizo la conexion
            PreparedStatement ps= null;//prepara para entrar a mysql
            try{
               String query="INSERT INTO mensajes (mensaje, autor_mensaje) VALUES (?, ?)"; 
               ps= conexion.prepareStatement(query);//se ace la conexion del comando 
               ps.setString(1, mensajes.getMensajes());//se pasa eñ primer valor
               ps.setString(2, mensajes.getAutor_mensaje());//se pasa el segundo valor
               ps.executeUpdate();//se usa para trasferir datos 
               System.out.println("menasje creado");
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public static void leerMensajesDB(){
        Conexion db_connect = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;//para hacer consultas
        try(Connection conexion = db_connect.get_connection()){
            String query="SELECT * FROM mensajes";
            ps= conexion.prepareStatement(query);// se pasa los comandos para mysql
            rs = ps.executeQuery();//se usa para consulta solamente
            while(rs.next()){//usaremos este iterador para mostrar todos los mensjes 
                System.out.println("Id: "+rs.getInt("id_mensajes"));//se mostrara cada uno de los id
                System.out.println("Mensaje: "+rs.getString("mensaje"));//se mostrara cada uno de los mensajes
                System.out.println("Autor: "+rs.getString("autor_mensaje"));//se mostrara cada uno de los autores
                System.out.println("Fecha: "+rs.getString("fecha_mensaje"));//se mostrara cada una de las fechas
                System.out.println("");//un salto de line para separar cada mensaje
            }
        }catch(SQLException em){
            System.out.println("No se pudieron recuperar los mensajes");
            System.out.println(em);
        }
    }
    public static void borrarMensajesDB(int id_mensajes){
        
        Conexion db_connect = new Conexion();
        try(Connection conexion = db_connect.get_connection()){
            PreparedStatement ps=null;
            try{
                String query="DELETE FROM mensajes WHERE id_mensajes = ?";
                ps = conexion.prepareStatement(query);
                ps.setInt(1, id_mensajes);
                ps.executeUpdate();//transaccion de base de datos
                System.out.println("el mensaje fue borrado");
            }catch(SQLException e){
                System.out.println(e);
                System.out.println("no se pudo borrar el mensaje");
            }
        }catch(SQLException m){
            System.out.println(m);
        }
    }
    public static void actualizarMensajesDB(Mensajes mensajes){
        Conexion db_connect = new Conexion();
        try(Connection conexion = db_connect.get_connection()){
            PreparedStatement ps=null;
            
            try{
                String query="UPDATE mensajes SET mensaje = ? WHERE id_mensajes = ?";
                ps= conexion.prepareStatement(query);
                ps.setString(1, mensajes.getMensajes());
                ps.setInt(2, mensajes.getId_mensajes());
                ps.executeUpdate();
                System.out.println("el mesnaje se actualizo correctamente ");
            }catch(SQLException ex){
                System.out.println(ex);
            }
        }catch(SQLException em){
            System.out.println(em);
            System.out.println("no se pudo actualizar el mensaje ");

        }
    }
}
