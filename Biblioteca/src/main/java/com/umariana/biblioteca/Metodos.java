/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import static jdk.jpackage.internal.Arguments.CLIOptions.context;

/**
 *
 * @author maria
 */
public class Metodos {
    
    public static boolean usuarioIg(int cedula, ArrayList<Usuarios> misUsuarios) throws IOException{
        for (Usuarios u: misUsuarios){
            if (u.getCedula()==cedula){
                return false; 
            }
        }
        return true; 
    }
    
    public static String ingresoUsuarios(int cedula,String contrasenia, ArrayList<Usuarios> misUsuarios) throws IOException{
        for (Usuarios u: misUsuarios){
            if (u.getCedula()==cedula && u.getContrasena().equals(contrasenia)){
                return u.getNombre();
            }
        }
        return "no";
    }
    public static String listar(ServletContext context, String terminoBusqueda){
        Biblioteca libros= new Biblioteca();
        libros=PersistenciaArchivo.deserializarBiblioteca(context);
        if(libros==null){
            libros= new Biblioteca();
        }
        String tabla="";

        if(terminoBusqueda==null){
            tabla= libros.tabla();
        }else if(terminoBusqueda!=null){
            tabla= libros.tablaBusqueda(terminoBusqueda);
        }
        return tabla ;
    }
}
