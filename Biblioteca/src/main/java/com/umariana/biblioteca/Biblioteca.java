/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
            tablaHtml.append("<td>").append(libro.getId()).append("</td>");
            tablaHtml.append("<td>").append(libro.getTitulo()).append("</td>");
            tablaHtml.append("<td>").append(libro.getAutor()).append("</td>");
            tablaHtml.append("<td>").append(libro.getAnoPublicacion()).append("</td>");
            tablaHtml.append("<td>").append(libro.getGenero()).append("</td>");
            tablaHtml.append("<td>").append(libro.getGenero()).append("</td>");
            tablaHtml.append("<td><center> <a href=\"#\" class=\"btn btn-primary\" data-bs-toggle=\"ver\" data-bs-target=\"#exampleModal\" data-nombre=\""+ libro.getId() + "\"><i class=\"fas fa-eye\"></i> </a>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-primary\"  data-bs-toggle=\"editar\" data-bs-target=\"#editarModal\" data-nombre=\""+ libro.getId() + "\"><i class=\"fas fa-pencil-alt\"></i></a>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-danger deleteButton\" id=\"deleteButton\" data-titulo=\""+ libro.getId() + "\"><i class=\"fas fa-trash-alt\"></i></a></center></td>");
            tablaHtml.append("</tr>");
            actual = actual.getSiguiente(); // Avanzar al siguiente nodo
        }
        tablaHtml.append("</table>");
    return tablaHtml.toString();
   }
   
   public Libro encontrarLibro(int id){
    Nodo actual = primero; // Comenzamos desde el primer nodo
    
    while (actual != null) { // Recorremos la lista mientras haya nodos
        Libro libro = actual.getLibro(); // Obtenemos el libro del nodo actual
        if(libro.getId()==id){
            return libro;
        }       
        actual = actual.getSiguiente(); // Avanzamos al siguiente nodo
    }
    return null;   
       
   }
     
   public void eliminarLibro(int id) {
    Nodo actual = primero;
    
    // Recorre la lista buscando el libro con el título dado
    while (actual != null) {
        if (actual.getLibro().getId()==id) {
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
    public void editar(int id,String titulo, String autor, int anio, String genero,String foto) {
        Nodo actual = primero;
        boolean encontrado = false;//Bandera
        while (actual != null && !encontrado) {
             Libro libro  = actual.getLibro();
            if (libro.getId()==id) {//Al encontrar la tarea
                libro.setTitulo(titulo);
                libro.setAutor(autor);
                libro.setAnoPublicacion(anio);
                libro.setGenero(genero);
                libro.setFotoPortada(foto);
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
                tablaHtml.append("<td>").append(libro.getId()).append("</td>");
                tablaHtml.append("<td>").append(libro.getTitulo()).append("</td>");
                tablaHtml.append("<td>").append(libro.getAutor()).append("</td>");
                tablaHtml.append("<td>").append(libro.getAnoPublicacion()).append("</td>");
                tablaHtml.append("<td>").append(libro.getGenero()).append("</td>");
                tablaHtml.append("<td>").append(libro.getGenero()).append("</td>");
                tablaHtml.append("<td><center> <a href=\"#\" class=\"btn btn-primary\" data-bs-toggle=\"ver\" data-bs-target=\"#exampleModal\" data-nombre=\""+ libro.getId() + "\"><i class=\"fas fa-eye\"></i> </a>");
                tablaHtml.append("<a href=\"#\" class=\"btn btn-primary\"  data-bs-toggle=\"editar\" data-bs-target=\"#editarModal\" data-nombre=\""+ libro.getId() + "\"><i class=\"fas fa-pencil-alt\"></i></a>");
                tablaHtml.append("<a href=\"#\" class=\"btn btn-danger deleteButton\" id=\"deleteButton\" data-titulo=\""+ libro.getId() + "\"><i class=\"fas fa-trash-alt\"></i></a></center></td>");
                tablaHtml.append("</tr>");
            encontrados=true;
        }
            actual = actual.getSiguiente(); // Avanzar al siguiente nodo
        }
        if(!encontrados){
            tablaHtml.append("<tr>");
            tablaHtml.append("<td>No hay coincidencias :(</td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("<td></td>");
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
   
     public String librosLogin() {
        Nodo actual = primero; // Comenzamos desde el primer nodo
        
        String libros = "";

            while (actual != null) {
                Libro libro = actual.getLibro();
                libros += "<div class=\"col\">";
                libros += "<div class=\"card\">";
                libros += "<div class=\"card-header\">";
                libros += "</div>";
                libros += "<img src=\"imagenes/"+libro.getFotoPortada()+"\" class=\"card-img-top\" alt=\""+libro.getFotoPortada()+"\" width=\"200px\" height=\"300px\">";
                libros += "<div class=\"card-body\">";
                libros += "<h5 class=\"card-title\">"+libro.getTitulo()+"</h5>";
                libros += "<p class=\"card-text\"> Autor: "+libro.getAutor()+"</p>";
                libros += "<p class=\"card-text\"> Año publicado: "+libro.getAnoPublicacion()+"</p>";
                if(libro.getPrestado()==null){
                    libros += " <a href=\"#\" class=\"btn btn-primary\" data-bs-toggle=\"alquilar\" data-bs-target=\"#alquilarModal\" data-nombre=\""+libro.getId()+"\">Alquilar</a>";
                }else if(libro.getPrestado()!=null){
                    libros += " <a href=\"#\" class=\"btn btn-primary\">no disponible</a>";
                }
                libros += "</div>";
                libros += "</div>";
                libros += "</div>";
                actual=actual.getSiguiente();
            }

        return libros;
    }
     public String librosLoginBus(String buscar) {
        Nodo actual = primero; // Comenzamos desde el primer nodo
        
        String libros = "";

            while (actual != null) {
                Libro libro = actual.getLibro();
                if(libro.getTitulo().contains(buscar)|| libro.getAutor().contains(buscar)){
                libros += "<div class=\"col\">";
                libros += "<div class=\"card\">";
                libros += "<div class=\"card-header\">";
                libros += "</div>";
                libros += "<img src=\"imagenes/"+libro.getFotoPortada()+"\" class=\"card-img-top\" alt=\""+libro.getFotoPortada()+"\" width=\"200px\" height=\"300px\">";
                libros += "<div class=\"card-body\">";
                libros += "<h5 class=\"card-title\">"+libro.getTitulo()+"</h5>";
                libros += "<p class=\"card-text\"> Autor: "+libro.getAutor()+"</p>";
                libros += "<p class=\"card-text\"> Año publicado: "+libro.getAnoPublicacion()+"</p>";
                if(libro.getPrestado()==null){
                    libros += " <a href=\"#\" class=\"btn btn-primary\" data-bs-toggle=\"alquilar\" data-bs-target=\"#alquilarModal\" data-nombre=\""+libro.getId()+"\">Alquilar</a>";
                }else if(libro.getPrestado()!=null){
                    libros += " <a href=\"#\" class=\"btn btn-primary\">no disponible</a>";
                }
                libros += "</div>";
                libros += "</div>";
                libros += "</div>";
                }
                actual=actual.getSiguiente();
            }

        return libros;
    }
     
    public void prestarLibro(int id, Prestamo prestado) {
        Nodo actual = primero;
        boolean encontrado = false;//Bandera
        while (actual != null && !encontrado) {
             Libro libro  = actual.getLibro();
            if (libro.getId()==id) {//Al encontrar la tarea
               libro.setPrestado(prestado);
                encontrado = true;//Finaliza ciclo
            }
            actual = actual.getSiguiente();//Continua recorriendo
        }
    } 
    public void devolverLibro(int id) {
        Nodo actual = primero;
        boolean encontrado = false;//Bandera
        while (actual != null && !encontrado) {
             Libro libro  = actual.getLibro();
            if (libro.getId()==id) {//Al encontrar la tarea
               libro.setPrestado(null);
                encontrado = true;//Finaliza ciclo
            }
            actual = actual.getSiguiente();//Continua recorriendo
        }
    } 
    
    public int id() {
        Nodo actual = primero;
        int id=0;
        while (actual != null) {
            id=actual.getLibro().getId();
            actual = actual.getSiguiente();//Continua recorriendo
        }
        return id;
    } 
    
    
    public String librosDisponibles() {
        Nodo actual = primero; // Comenzamos desde el primer nodo       
        String libros = "";
            while (actual != null) {
                Libro libro = actual.getLibro();
                if(libro.getPrestado()==null){
                libros += "<div class=\"col\">";
                libros += "<div class=\"card\">";
                libros += "<div class=\"card-header\">";
                libros += "</div>";
                libros += "<img src=\"imagenes/"+libro.getFotoPortada()+"\" class=\"card-img-top\" alt=\""+libro.getFotoPortada()+"\" width=\"200px\" height=\"300px\">";
                libros += "<div class=\"card-body\">";
                libros += "<h5 class=\"card-title\">"+libro.getTitulo()+"</h5>";
                libros += "<p class=\"card-text\"> Autor: "+libro.getAutor()+"</p>";
                libros += "<p class=\"card-text\"> Año publicado: "+libro.getAnoPublicacion()+"</p>";
                libros += " <a href=\"#\" class=\"btn btn-primary\" data-bs-toggle=\"alquilar\" data-bs-target=\"#alquilarModal\" data-nombre=\""+libro.getId()+"\">Alquilar</a>";               
                libros += "</div>";
                libros += "</div>";
                libros += "</div>";               
                }
                actual=actual.getSiguiente();
            }

        return libros;
    }
        public String librosPrestados(int cedula) {
        Nodo actual = primero; // Comenzamos desde el primer nodo
        
        String libros = "";
        boolean parametro=false;
            while (actual != null) {
                Libro libro = actual.getLibro();
                Prestamo info=libro.getPrestado();
                if(libro.getPrestado()!=null && info.getCedula()==cedula){
                    libros += "<div class=\"col\">";
                    libros += "<div class=\"card\">";
                    libros += "<div class=\"card-header\">";
                    libros += "</div>";
                    libros += "<img src=\"imagenes/"+libro.getFotoPortada()+"\" class=\"card-img-top\" alt=\""+libro.getFotoPortada()+"\" width=\"200px\" height=\"300px\">";
                    libros += "<div class=\"card-body\">";
                    libros += "<h5 class=\"card-title\">"+libro.getTitulo()+"</h5>";
                    libros += "<p class=\"card-text\"> Regresalo en: "+libro.getPrestado().getTiempo()+"</p>";
                   
                    libros += " <a href=\"#\" class=\"btn btn-primary devolverButton\" id=\"devolverButton\" data-titulo=\""+ libro.getId() + "\" >Regresarlo</a>";
                    
                    libros += "</div>";
                    libros += "</div>";
                    libros += "</div>";              
                }
                actual=actual.getSiguiente();
            }

        return libros;
    }
    public String librosGenero(String genero) {
        Nodo actual = primero; // Comenzamos desde el primer nodo      
        String libros = "";

            while (actual != null) {
                Libro libro = actual.getLibro();

                if(libro.getGenero().equals(genero)){
                    libros += "<div class=\"col\">";
                    libros += "<div class=\"card\">";
                    libros += "<div class=\"card-header\">";
                    libros += "</div>";
                    libros += "<img src=\"imagenes/"+libro.getFotoPortada()+"\" class=\"card-img-top\" alt=\""+libro.getFotoPortada()+"\" width=\"200px\" height=\"300px\">";
                    libros += "<div class=\"card-body\">";
                    libros += "<h5 class=\"card-title\">"+libro.getTitulo()+"</h5>";
                   
                    libros += " <a href=\"#\" class=\"btn btn-primary devolverButton\" id=\"devolverButton\" data-titulo=\""+ libro.getId() + "\" >Regresarlo</a>";
                    
                    libros += "</div>";
                    libros += "</div>";
                    libros += "</div>";              
                }
                actual=actual.getSiguiente();
            }

        return libros;
    }
     public String librosGeneroBuscar(String genero, String termino) {
        Nodo actual = primero; // Comenzamos desde el primer nodo      
        String libros = "";

            while (actual != null) {
                Libro libro = actual.getLibro();

                if(libro.getGenero().equals(genero)){
                    if(libro.getTitulo().contains(termino) || libro.getAutor().contains(termino)){
                        libros += "<div class=\"col\">";
                        libros += "<div class=\"card\">";
                        libros += "<div class=\"card-header\">";
                        libros += "</div>";
                        libros += "<img src=\"imagenes/"+libro.getFotoPortada()+"\" class=\"card-img-top\" alt=\""+libro.getFotoPortada()+"\" width=\"200px\" height=\"300px\">";
                        libros += "<div class=\"card-body\">";
                        libros += "<h5 class=\"card-title\">"+libro.getTitulo()+"</h5>";

                        libros += " <a href=\"#\" class=\"btn btn-primary devolverButton\" id=\"devolverButton\" data-titulo=\""+ libro.getId() + "\" >Regresarlo</a>";

                        libros += "</div>";
                        libros += "</div>";
                        libros += "</div>";              
                    }
                }
                actual=actual.getSiguiente();
            }

        return libros;
    }
    public String librosOrdenadosPorAnoDescendente() {
    // Crear una copia de la lista doblemente enlazada para no modificar la original
    Nodo actual = primero; // Comenzamos desde el primer nodo
    List<Libro> librosList = new ArrayList<>();

    while (actual != null) {
        librosList.add(actual.getLibro());
        actual = actual.getSiguiente();
    }

    // Ordenar la lista de libros por año de publicación en orden descendente
    librosList.sort((libro1, libro2) -> libro2.getAnoPublicacion() - libro1.getAnoPublicacion());

    // Generar la tabla con la lista de libros ordenada
    String libros = "";

    for (Libro libro : librosList) {
        libros += "<div class=\"col\">";
        libros += "<div class=\"card\">";
        libros += "<div class=\"card-header\">";
        libros += "</div>";
        libros += "<img src=\"imagenes/" + libro.getFotoPortada() + "\" class=\"card-img-top\" alt=\"" + libro.getFotoPortada() + "\" width=\"200px\" height=\"300px\">";
        libros += "<div class=\"card-body\">";
        libros += "<h5 class=\"card-title\">" + libro.getTitulo() + "</h5>";
        libros += "<p class=\"card-text\"> Autor: " + libro.getAutor() + "</p>";
        libros += "<p class=\"card-text\"> Año publicado: " + libro.getAnoPublicacion() + "</p>";
        if (libro.getPrestado() == null) {
            libros += " <a href=\"#\" class=\"btn btn-primary\" data-bs-toggle=\"alquilar\" data-bs-target=\"#alquilarModal\" data-nombre=\"" + libro.getId() + "\">Alquilar</a>";
        } else if (libro.getPrestado() != null) {
            libros += " <a href=\"#\" class=\"btn btn-primary\">no disponible</a>";
        }
        libros += "</div>";
        libros += "</div>";
        libros += "</div>";
    }

    return libros;
}
    
public String librosOrdenadosPorAnoAscendente() {
    // Crear una copia de la lista doblemente enlazada para no modificar la original
    Nodo actual = primero; // Comenzamos desde el primer nodo
    List<Libro> librosList = new ArrayList<>();

    while (actual != null) {
        librosList.add(actual.getLibro());
        actual = actual.getSiguiente();
    }

    // Ordenar la lista de libros por año de publicación en orden ascendente
    librosList.sort((libro1, libro2) -> libro1.getAnoPublicacion() - libro2.getAnoPublicacion());

    // Generar la tabla con la lista de libros ordenada
    String libros = "";

    for (Libro libro : librosList) {
        libros += "<div class=\"col\">";
        libros += "<div class=\"card\">";
        libros += "<div class=\"card-header\">";
        libros += "</div>";
        libros += "<img src=\"imagenes/" + libro.getFotoPortada() + "\" class=\"card-img-top\" alt=\"" + libro.getFotoPortada() + "\" width=\"200px\" height=\"300px\">";
        libros += "<div class \"card-body\">";
        libros += "<h5 class=\"card-title\">" + libro.getTitulo() + "</h5>";
        libros += "<p class=\"card-text\"> Autor: " + libro.getAutor() + "</p>";
        libros += "<p class=\"card-text\"> Año publicado: " + libro.getAnoPublicacion() + "</p>";
        if (libro.getPrestado() == null) {
            libros += " <a href=\"#\" class=\"btn btn-primary\" data-bs-toggle=\"alquilar\" data-bs-target=\"#alquilarModal\" data-nombre=\"" + libro.getId() + "\">Alquilar</a>";
        } else if (libro.getPrestado() != null) {
            libros += " <a href=\"#\" class=\"btn btn-primary\">no disponible</a>";
        }
        libros += "</div>";
        libros += "</div>";
        libros += "</div>";
    }

    return libros;
}

}
       
   


