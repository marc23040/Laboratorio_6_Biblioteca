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
    public static Usuarios encontrarUsuario(int cedula, ArrayList<Usuarios> misUsuarios) throws IOException{
        for (Usuarios u: misUsuarios){
            if (u.getCedula()==cedula){
                return u; 
            }
        }
        return null; 
    }
    
    public static String ingresoUsuarios(int cedula,String contrasenia, ArrayList<Usuarios> misUsuarios) throws IOException{
        for (Usuarios u: misUsuarios){
            if (u.getCedula()==cedula && u.getContrasena().equals(contrasenia)){
                return u.getNombre();
            }
        }
        return "no";
    }
    public static String listar(ServletContext context, String terminoBusqueda, String cedula, String buscar){
        Biblioteca libros= new Biblioteca();
        libros=PersistenciaArchivo.deserializarBiblioteca(context);
        if(libros==null){
            libros= new Biblioteca();
        }
        String tabla="";

        if(terminoBusqueda==null && buscar==null){
            tabla= libros.librosLogin();
        }else if(terminoBusqueda!=null){
            if(terminoBusqueda.equals("disponibles")){
                  tabla= libros.librosDisponibles();

            }else if(terminoBusqueda.equals("alquilados")){
                tabla=libros.librosPrestados(Integer.parseInt(cedula));
            } else if(terminoBusqueda.equals("antiguos")){
                tabla=libros.librosOrdenadosPorAnoAscendente();
            }else if(terminoBusqueda.equals("recientes")){
                tabla=libros.librosOrdenadosPorAnoDescendente();
            }
          
        }else if(buscar!=null){
            tabla=libros.librosLoginBus(buscar);
        }
        return tabla ;
    }
    public static String buscar(ServletContext context, String terminoBusqueda, String cedula){
        Biblioteca libros= new Biblioteca();
        libros=PersistenciaArchivo.deserializarBiblioteca(context);
        if(libros==null){
            libros= new Biblioteca();
        }
        String tabla="";

        if(terminoBusqueda==null){
            tabla= libros.librosLogin();
        }else if(terminoBusqueda!=null){
            if(terminoBusqueda.equals("disponibles")){
                  tabla= libros.librosDisponibles();

            }else if(terminoBusqueda.equals("alquilados")){
                tabla=libros.librosPrestados(Integer.parseInt(cedula));
            } else if(terminoBusqueda.equals("antiguos")){
                tabla=libros.librosOrdenadosPorAnoAscendente();
            }else if(terminoBusqueda.equals("recientes")){
                tabla=libros.librosOrdenadosPorAnoDescendente();
            }
          
        }
        return tabla ;
    }
}
