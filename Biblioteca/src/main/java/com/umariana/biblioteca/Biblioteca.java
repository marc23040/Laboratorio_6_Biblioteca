/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.biblioteca;

import java.io.Serializable;

/**
 *
 * @author maria
 */
public class Biblioteca implements Serializable{
 
    private Nodo primero;
    private Nodo ultimo;

    public Biblioteca() {
        primero = null;
        ultimo = null;
    }

   public void insertar(Libro libro) {
    Nodo nuevo = new Nodo(libro, null, null);
    
    // Caso 1: La biblioteca está vacía
    if (primero == null) {
        primero = nuevo;
        ultimo = nuevo;
    } else {
        // Caso 2: La biblioteca no está vacía
        ultimo.setSiguiente(nuevo);
        nuevo.setAnterior(ultimo);
        ultimo = nuevo;
    }
       System.out.println("se inserto exitosamente");
}

      
   public void listarLibros() {
    Nodo actual = primero; // Comenzamos desde el primer nodo
    
    while (actual != null) { // Recorremos la lista mientras haya nodos
        Libro libro = actual.getLibro(); // Obtenemos el libro del nodo actual
        System.out.println(libro.getTitulo()); // Imprimimos el libro o puedes personalizar cómo deseas mostrar la información del libro
        
        actual = actual.getSiguiente(); // Avanzamos al siguiente nodo
    }
}   
   
   public String tabla(){
    Nodo actual = primero; // Comenzamos desde el primer nodo
        StringBuilder tablaHtml = new StringBuilder();
        while (actual != null) {
            Libro libro = actual.getLibro();
            tablaHtml.append("<tr>");
            tablaHtml.append("<td>").append(libro.getTitulo()).append("</td>");
            tablaHtml.append("<td>").append(libro.getAutor()).append("</td>");
            tablaHtml.append("<td>").append(libro.getAnoPublicacion()).append("</td>");
            if(libro.isPrestamo()){
            tablaHtml.append("<td>").append("prestado").append("</td>");    
            }else if(!libro.isPrestamo()){
             tablaHtml.append("<td>").append("NO prestado").append("</td>");    
            }           
            tablaHtml.append("<td> <a href=\"#\" class=\"btn btn-primary\" data-bs-toggle=\"ver\" data-bs-target=\"#exampleModal\" data-nombre=\""+ libro.getTitulo() + "\"><i class=\"fas fa-eye\"></i> </a>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-primary\" data-nombre=\""+ libro.getTitulo() + "\" data-autor=\""+ libro.getAutor() + "\" data-anio=\""+String.valueOf(libro.getAnoPublicacion())+ "\" data-portada=\""+ libro.getFotoPortada() + "\"id=\"abrirModalBtn\" onclick=\"abrirModal(this)\"><i class=\"fas fa-pencil-alt\"></i></a>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-danger\" id=\"deleteButton\" data-titulo=\""+ libro.getTitulo() + "\"><i class=\"fas fa-trash-alt\"></i></a></td>");
            tablaHtml.append("</tr>");
            actual = actual.getSiguiente(); // Avanzar al siguiente nodo
        }
          tablaHtml.append("</table>");
    return tablaHtml.toString();
   }
   
   public Libro encontrarLibro(String titulo){
    Nodo actual = primero; // Comenzamos desde el primer nodo
    
    while (actual != null) { // Recorremos la lista mientras haya nodos
        Libro libro = actual.getLibro(); // Obtenemos el libro del nodo actual
        if(libro.getTitulo().equals(titulo)){
            return libro;
        }
        
        actual = actual.getSiguiente(); // Avanzamos al siguiente nodo
    }
    return null;   
       
   }
     
   public void eliminarLibro(String titulo) {
    Nodo actual = primero;
    
    // Recorre la lista buscando el libro con el título dado
    while (actual != null) {
        if (actual.getLibro().getTitulo().equals(titulo)) {
            if (actual == primero) {
                primero = actual.getSiguiente();
                if (primero != null) {
                    primero.setAnterior(null);
                }
            } else if (actual == ultimo) {
                ultimo = actual.getAnterior();
                if (ultimo != null) {
                    ultimo.setSiguiente(null);
                }
            } else {
                actual.getAnterior().setSiguiente(actual.getSiguiente());
                actual.getSiguiente().setAnterior(actual.getAnterior());
            }
            return; // Libro encontrado y eliminado
        }
        actual = actual.getSiguiente();
    }
}
    public void editar(String titulo, String autor, int anio, String foto, boolean prestamo) {
        Nodo actual = primero;
        boolean encontrado = false;//Bandera
        while (actual != null && !encontrado) {
             Libro libro  = actual.getLibro();
            if (libro.getTitulo().equals(titulo)) {//Al encontrar la tarea
                libro.setAutor(autor);
                libro.setAnoPublicacion(anio);
                libro.setFotoPortada(foto);
                libro.setPrestamo(prestamo);
                encontrado = true;//Finaliza ciclo
            }
            actual = actual.getSiguiente();//Continua recorriendo
        }
    }

    public String tablaBusqueda(String terminoBusqueda){
    Nodo actual = primero; // Comenzamos desde el primer nodo
        StringBuilder tablaHtml = new StringBuilder();
        boolean encontrados=false;
        while (actual != null) {
            
            Libro libro = actual.getLibro();
            if(libro.getTitulo().contains(terminoBusqueda) || libro.getAutor().contains(terminoBusqueda)){
            tablaHtml.append("<tr>");
            tablaHtml.append("<td>").append(libro.getTitulo()).append("</td>");
            tablaHtml.append("<td>").append(libro.getAutor()).append("</td>");
            tablaHtml.append("<td>").append(libro.getAnoPublicacion()).append("</td>");
            if(libro.isPrestamo()){
            tablaHtml.append("<td>").append("prestado").append("</td>");    
            }else if(!libro.isPrestamo()){
             tablaHtml.append("<td>").append("NO prestado").append("</td>");    
            }           
            tablaHtml.append("<td> <a href=\"#\" class=\"btn btn-primary\" data-bs-toggle=\"ver\" data-bs-target=\"#exampleModal\" data-nombre=\""+ libro.getTitulo() + "\"><i class=\"fas fa-eye\"></i> </a>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-primary\" data-nombre=\""+ libro.getTitulo() + "\" data-autor=\""+ libro.getAutor() + "\" data-anio=\""+String.valueOf(libro.getAnoPublicacion())+ "\" data-portada=\""+ libro.getFotoPortada() + "\"id=\"abrirModalBtn\" onclick=\"abrirModal(this)\"><i class=\"fas fa-pencil-alt\"></i></a>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-danger\" id=\"deleteButton\" data-titulo=\""+ libro.getTitulo() + "\"><i class=\"fas fa-trash-alt\"></i></a></td>");
            tablaHtml.append("</tr>");
            encontrados=true;
        }
            actual = actual.getSiguiente(); // Avanzar al siguiente nodo
        }
        if(!encontrados){
            tablaHtml.append("<tr>");
            tablaHtml.append("<td>No hay coincidencias</td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("<td></td>");          
            tablaHtml.append("<td> <a href=\"#\" class=\"btn btn-primary\"><i class=\"fas fa-eye\"></i> </a>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-primary\" ><i class=\"fas fa-pencil-alt\"></i></a>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-danger\" ><i class=\"fas fa-trash-alt\"></i></a></td>");
            tablaHtml.append("</tr>");
        }
          tablaHtml.append("</table>");
    return tablaHtml.toString();
   }
   
   
       
   

}
