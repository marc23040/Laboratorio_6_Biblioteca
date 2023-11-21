/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.biblioteca;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 * Clase que proporciona métodos para la persistencia de datos en archivos.
 * @author Maria- Juan- Alejandro- Juan 
 */
public class PersistenciaArchivo {

    /**
     * Serializa un ArrayList de Usuarios y lo guarda en un archivo.
     * 
     * @param misUsuarios ArrayList de Usuarios a serializar.
     * @param context     Contexto del servlet para obtener la ruta física.
     */
    public static void serializarUsuarios(ArrayList<Usuarios> misUsuarios, ServletContext context) {

        // Ruta relativa del archivo de usuarios serializado
        String ruta = "/data/usuarios.ser";

        // Obtener la ruta física en el servidor
        String rutaa = context.getRealPath(ruta);

        System.out.println("El archivo serializado se encuentra en: " + rutaa);

        // Crear un objeto File con la ruta
        File archivo = new File(rutaa);

        try (ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream(archivo))) {

            // Escribir el ArrayList de Usuarios en el archivo
            escribiendoFichero.writeObject(misUsuarios);

            // Cerrar el ObjectOutputStream
            escribiendoFichero.close();

            System.out.println("Se escribió el archivo");

        } catch (FileNotFoundException ex) {

            System.out.println("No se encontró el archivo");

        } catch (IOException ex) {

            System.out.println("Error al escribir el archivo");
        }
    }

    /**
     * Deserializa un archivo de usuarios y devuelve un ArrayList de Usuarios.
     * 
     * @param context Contexto del servlet para obtener la ruta física.
     * @return ArrayList de Usuarios deserializado.
     */
    public static ArrayList<Usuarios> deserializarUsuarios(ServletContext context) {
        ArrayList<Usuarios> misUsuarios = new ArrayList<>();

        // Ruta relativa del archivo de usuarios serializado
        String ruta = "/data/usuarios.ser";
        // Obtener la ruta física en el servidor
        String rutaa = context.getRealPath(ruta);
        // Crear un objeto File con la ruta
        File archivo = new File(rutaa);

        try (ObjectInputStream leyendoFichero = new ObjectInputStream(new FileInputStream(archivo))) {
            // Leer el objeto del archivo y castearlo a ArrayList de Usuarios
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

    /**
     * Serializa un objeto Biblioteca y lo guarda en un archivo.
     * 
     * @param biblioteca Objeto Biblioteca a serializar.
     * @param context    Contexto del servlet para obtener la ruta física.
     */
    public static void serializarBiblioteca(Biblioteca biblioteca, ServletContext context) {
        // Ruta relativa del archivo de biblioteca serializado
        String ruta = "/data/libros.ser";
        // Obtener la ruta física en el servidor
        String rutaa = context.getRealPath(ruta);
        // Crear un objeto File con la ruta
        File archivo = new File(rutaa);
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
            // Escribir el objeto Biblioteca en el archivo
            salida.writeObject(biblioteca);
            System.out.println("Biblioteca serializada exitosamente en " + rutaa);
        } catch (IOException e) {
            System.out.println("NO SE SERIALIZÓ");
        }
    }

    /**
     * Deserializa un archivo de biblioteca y devuelve un objeto Biblioteca.
     * 
     * @param context Contexto del servlet para obtener la ruta física.
     * @return Objeto Biblioteca deserializado.
     */
    public static Biblioteca deserializarBiblioteca(ServletContext context) {
        Biblioteca biblioteca = null;
        // Ruta relativa del archivo de biblioteca serializado
        String ruta = "/data/libros.ser";
        // Obtener la ruta física en el servidor
        String rutaa = context.getRealPath(ruta);
        // Crear un objeto File con la ruta
        File archivo = new File(rutaa);
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            // Leer el objeto del archivo y castearlo a Biblioteca
            biblioteca = (Biblioteca) entrada.readObject();
            System.out.println("Biblioteca deserializada exitosamente desde " + rutaa);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("NO SE DESERIALIZÓ");
        }
        return biblioteca;
    }
}
