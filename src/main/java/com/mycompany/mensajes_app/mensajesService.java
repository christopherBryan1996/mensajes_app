/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mensajes_app;

import java.util.Scanner;

/**
 *
 * @author jarvis1.1
 */
public class mensajesService {
    public static void crearMensaje(){
        Scanner sc = new Scanner(System.in);//funcion para entrada de datos
        System.out.println("Escribe tu mensaje");
        String mensaje = sc.nextLine();
        
        System.out.println("Tu nombre");
        String nombre = sc.nextLine();
        
        Mensajes registro =  new Mensajes();
        registro.setMensajes(mensaje);// se guarda las variable 
        registro.setAutor_mensaje(nombre);// se guarda las variable
        MensajesDAO.crearMensjaesDB(registro);// se manda a la base de datos
    }
    public static void listarMensajes(){
        MensajesDAO.leerMensajesDB();
    }
    public static void borrarMensajes(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indica el Id del mensaje a borrar");
        int id_mensaje = sc.nextInt();
        MensajesDAO.borrarMensajesDB(id_mensaje);
    }
    public static void editarMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("escribe tu nuevo mensaje");
        String mensaje = sc.nextLine();
        System.out.println("indica el ID del mensaje a borrar");
        int id_mensaje = sc.nextInt();
        Mensajes actualizacion = new Mensajes();
        actualizacion.setMensajes(mensaje);
        actualizacion.setId_mensajes(id_mensaje);
        MensajesDAO.actualizarMensajesDB(actualizacion);
    }
}
