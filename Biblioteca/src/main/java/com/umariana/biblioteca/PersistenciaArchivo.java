/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.biblioteca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 *
 * @author maria
 */
public class PersistenciaArchivo {
    
       public static void serializarUsuarios(ArrayList<Usuarios> misUsuarios, ServletContext context) {

        String ruta = "/data/usuarios.ser";

        String rutaa = context.getRealPath(ruta);

        System.out.println("El archivo serializado se encuentra en: " + rutaa);

        File archivo = new File(rutaa);

        try (ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream(archivo))) {

            escribiendoFichero.writeObject(misUsuarios);

            escribiendoFichero.close();
   
            System.out.println("Se escribio el archivo");

        } 
        catch (FileNotFoundException ex) {

            System.out.println("No se encontro el archivo");

        } catch (IOException ex) {

            System.out.println("Error al escribir el archivo");
        }
    }
   public static ArrayList<Usuarios> deserializarUsuarios(ServletContext context) {
    ArrayList<Usuarios> misUsuarios = new ArrayList<>();

    String ruta = "/data/usuarios.ser";
    String rutaa = context.getRealPath(ruta);
    File archivo = new File(rutaa);

    try (ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream(archivo))) {
        misUsuarios = (ArrayList<Usuarios>) leyendoFichero.readObject();
        System.out.println("Se leyó el archivo");
    } catch (FileNotFoundException ex) {
        System.out.println("No se encontró el archivo");
    } catch (IOException ex) {
        System.out.println("Error al leer el archivo");
    } catch (ClassNotFoundException ex) {
        System.out.println("Clase no encontrada al deserializar");
    }
    

    return misUsuarios;
}
    public static void serializarBiblioteca(Biblioteca biblioteca, ServletContext context) {
            String ruta = "/data/libros.ser";
            String rutaa = context.getRealPath(ruta);
            File archivo = new File(rutaa);
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
            salida.writeObject(biblioteca);
            System.out.println("Biblioteca serializada exitosamente en " + rutaa);
        } catch (IOException e) {
           System.out.println("NO SE SERIALIZO");
        }
    }

    public static Biblioteca deserializarBiblioteca(ServletContext context) {
        Biblioteca biblioteca = null;
        String ruta = "/data/libros.ser";
        String rutaa = context.getRealPath(ruta);
        File archivo = new File(rutaa);
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            biblioteca = (Biblioteca) entrada.readObject();
            System.out.println("Biblioteca deserializada exitosamente desde " + rutaa);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("NO SE DESERIALIZO");
        }
        return biblioteca;
    }

}
