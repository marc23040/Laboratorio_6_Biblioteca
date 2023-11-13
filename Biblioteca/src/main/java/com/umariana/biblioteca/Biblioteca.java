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

     
   public String tabla(){
    Nodo actual = primero; // Comenzamos desde el primer nodo
        StringBuilder tablaHtml = new StringBuilder();
        String estado="Disponible";
        boolean encontrados=false;    
        while (actual != null) {
            Libro libro = actual.getLibro();
            if (libro.getPrestado()!=null){ estado="Prestado a "+libro.getPrestado().getCedula();}
            tablaHtml.append("<tr>");
            tablaHtml.append("<td>").append(libro.getTitulo()).append("</td>");
            tablaHtml.append("<td>").append(libro.getAutor()).append("</td>");
            tablaHtml.append("<td>").append(libro.getAnoPublicacion()).append("</td>");
            tablaHtml.append("<td>").append(libro.getGenero()).append("</td>");
            tablaHtml.append("<td>").append(estado).append("</td>");         
            tablaHtml.append("<td> <a href=\"#\" class=\"btn btn-outline-success\" data-bs-toggle=\"ver\" data-bs-target=\"#exampleModal\" data-nombre=\""+ libro.getId() + "\"><i class=\"fas fa-eye\"></i> </a>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-outline-warning\"  data-bs-toggle=\"editar\" data-bs-target=\"#editarModal\" data-nombre=\""+ libro.getId() + "\"><i class=\"fas fa-pencil-alt\"></i></a>");
            tablaHtml.append("<a href=\"#\" class=\"\"btn btn-outline-danger deleteButton\" id=\"deleteButton\" data-titulo=\""+ libro.getId() + "\"><i class=\"fas fa-trash-alt\"></i></a></td>");
            tablaHtml.append("</tr>");
            encontrados=true;
            actual = actual.getSiguiente(); // Avanzar al siguiente nodo
        }
        if (!encontrados){
            tablaHtml.append("<tr>");
            tablaHtml.append("<td>No hay libros</td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("<td></td>");         
            tablaHtml.append("<td> <a href=\"#\" class=\"btn btn-outline-success\"><i class=\"fas fa-eye\"></i> </a>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-outline-warning\"><i class=\"fas fa-pencil-alt\"></i></a>");
            tablaHtml.append("<a href=\"#\" class=\"\"btn btn-outline-danger\" ><i class=\"fas fa-trash-alt\"></i></a></td>");
            tablaHtml.append("</tr>");
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
        String estado="Disponible";
        boolean encontrados=false;    
        while (actual != null) {
            Libro libro = actual.getLibro();
            if (libro.getPrestado()!=null){ estado="Prestado a "+libro.getPrestado().getCedula();}
            if(libro.getTitulo().contains(terminoBusqueda) || libro.getAutor().contains(terminoBusqueda)){
            tablaHtml.append("<tr>");
            tablaHtml.append("<td>").append(libro.getTitulo()).append("</td>");
            tablaHtml.append("<td>").append(libro.getAutor()).append("</td>");
            tablaHtml.append("<td>").append(libro.getAnoPublicacion()).append("</td>");
            tablaHtml.append("<td>").append(libro.getGenero()).append("</td>");
            tablaHtml.append("<td>").append(estado).append("</td>");         
            tablaHtml.append("<td><a href=\\\"#\\\" class=\\\"btn btn-primary\\\" data-bs-toggle=\\\"ver\\\" data-bs-target=\\\"#exampleModal\\\" data-nombre=\\\"\"+ libro.getId() + \"\\\"><i class=\\\"fas fa-eye\\\"></i> </a>>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-warning\"  data-bs-toggle=\"editar\" data-bs-target=\"#editarModal\" data-nombre=\""+ libro.getId() + "\"><i class=\"fas fa-pencil-alt\"></i></a>");
            tablaHtml.append("<a href=\"#\" class=\"\"btn btn-danger deleteButton\" id=\"deleteButton\" data-titulo=\""+ libro.getId() + "\"><i class=\"fas fa-trash-alt\"></i></a></td>");
            tablaHtml.append("</tr>");
            encontrados=true;
            }
            actual = actual.getSiguiente(); // Avanzar al siguiente nodo
        }
        if (!encontrados){
            tablaHtml.append("<tr>");
            tablaHtml.append("<td>No encontrado</td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("<td></td>");
            tablaHtml.append("<td></td>");         
            tablaHtml.append("<td> <a href=\"#\" class=\"btn btn-outline-success\"><i class=\"fas fa-eye\"></i> </a>");
            tablaHtml.append("<a href=\"#\" class=\"btn btn-outline-warning\"><i class=\"fas fa-pencil-alt\"></i></a>");
            tablaHtml.append("<a href=\"#\" class=\"\"btn btn-outline-danger\" ><i class=\"fas fa-trash-alt\"></i></a></td>");
            tablaHtml.append("</tr>");
        }
          tablaHtml.append("</table>");
    return tablaHtml.toString();
   }

   
     public String librosLogin( ) {
        Nodo actual = primero; // Comenzamos desde el primer nodo
        String libros = "";
        boolean encontrado=false;
        libros="<div class=\"row row-cols-1 row-cols-md-4 g-4\">";  
            while (actual != null) {
                Libro libro = actual.getLibro();
                libros+=card(libro);
                actual=actual.getSiguiente();
                encontrado=true;
            }
            libros+="</div>";
            if (!encontrado){
                libros= noEncontrado();
            }
        return libros;
    }
     public String librosLoginBus(String buscar) {
        Nodo actual = primero; // Comenzamos desde el primer nodo
        
        String libros = "";
        boolean encontrado=false;
            while (actual != null) {
                Libro libro = actual.getLibro();
                if(libro.getTitulo().contains(buscar)|| libro.getAutor().contains(buscar)){
                libros+=card(libro);
                encontrado=true;
                }
                
                actual=actual.getSiguiente();
               
            }
            
            if (!encontrado){
                libros= noEncontrado();
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
        boolean encontrado=false;
        libros+=" <div class=\"row row-cols-1 row-cols-md-4 g-4\">";      
            while (actual != null) {              
                Libro libro = actual.getLibro();
                if(libro.getPrestado()==null){
                    libros+=card(libro);
                    encontrado=true;
                }
                actual=actual.getSiguiente();     
            }
            libros+="</div>";
            if (!encontrado){
                libros= noEncontrado();
                }

        return libros;
    }
        public String librosPrestados(int cedula) {
        Nodo actual = primero; // Comenzamos desde el primer nodo
        
        String libros = "";
        libros+=" <div class=\"row row-cols-1 row-cols-md-4 g-4\">";     
         boolean encontrado=false;
            while (actual != null) {
                Libro libro = actual.getLibro();
                Prestamo info=libro.getPrestado();
                if(libro.getPrestado()!=null && info.getCedula()==cedula){   
                     libros += "<div class=\"col\">";
                libros += "<div class=\"card\">";
               
                libros += "<div class=\"card-body\">";
                 libros += "<img src=\"imagenes/"+libro.getFotoPortada()+"\" class=\"card-img-top\" alt=\""+libro.getFotoPortada()+"\" width=\"200px\" height=\"300px\">";
                libros += "<center><h2 class=\"card-title\">"+libro.getTitulo()+"</h2></center>";
                libros += "<p class=\"card-text\"> <b> Autor: </b>"+libro.getAutor()+"</p>";
                libros += "<p class=\"card-text\"> <b>Año: </b>"+libro.getAnoPublicacion()+"</p>";
                libros += "<p class=\"card-text\"> <b>Genero: </b>"+libro.getGenero()+"</p>";
                 libros += "<p class=\"card-text\"> Regresalo en: "+libro.getPrestado().getTiempo()+"</p>";
                   
                libros += " <a href=\"#\" class=\"btn btn-primary\" data-bs-toggle=\"devolver\" data-bs-target=\"#devolverModal\" data-nombre=\"\"+libro.getId()+\"\" style=\" background-color: #5e3824;>Regresarlo</a>";
                libros += "</div>";
                libros += "</div>";
                libros += "</div>";
                encontrado=true;
                }
                actual=actual.getSiguiente();
            }
              libros+="</div>";
            if (!encontrado){
                libros= noEncontrado();
                }

        return libros;
    }
    public String librosGenero(String genero) {
        Nodo actual = primero; // Comenzamos desde el primer nodo      
        String libros = "";
         boolean encontrado=false;
            while (actual != null) {
                Libro libro = actual.getLibro();

                if(libro.getGenero().equals(genero)){

                libros+=card(libro);     
                encontrado=true;
                }
                actual=actual.getSiguiente();
            }
            if (!encontrado){
                libros= noEncontrado();
                }

        return libros;
    }
     public String librosGeneroBuscar(String genero, String termino) {
        Nodo actual = primero; // Comenzamos desde el primer nodo      
        String libros = "";
        boolean encontrado=false;
            while (actual != null) {
                Libro libro = actual.getLibro();

                if(libro.getGenero().equals(genero)){
                    if(libro.getTitulo().contains(termino) || libro.getAutor().contains(termino)){        
                        libros+=card(libro); 
                        encontrado=true;
                    }
                }
                actual=actual.getSiguiente();
            }
        if (!encontrado){
            libros= noEncontrado();
        }
        return libros;
    }
    public String librosOrdenadosPorAnoDescendente() {
    // Crear una copia de la lista doblemente enlazada para no modificar la original
    Nodo actual = primero; // Comenzamos desde el primer nodo
    List<Libro> librosList = new ArrayList<>();
    boolean encontrado=false;
     
    while (actual != null) {
        librosList.add(actual.getLibro());
        actual = actual.getSiguiente();
    }

    // Ordenar la lista de libros por año de publicación en orden descendente
    librosList.sort((libro1, libro2) -> libro2.getAnoPublicacion() - libro1.getAnoPublicacion());

    // Generar la tabla con la lista de libros ordenada
    String  libros="<div class=\"row row-cols-1 row-cols-md-4 g-4\">";  
    
    for (Libro libro : librosList) {
        libros+=card(libro);
        encontrado=true;
    }
    libros+="</div>";
    if (!encontrado){
        libros= noEncontrado();
    }
    return libros;
}
    
public String librosOrdenadosPorAnoAscendente() {
    // Crear una copia de la lista doblemente enlazada para no modificar la original
    Nodo actual = primero; // Comenzamos desde el primer nodo
    List<Libro> librosList = new ArrayList<>();
    boolean encontrado=false;
    
    while (actual != null) {
        librosList.add(actual.getLibro());
        actual = actual.getSiguiente();
    }

    // Ordenar la lista de libros por año de publicación en orden ascendente
    librosList.sort((libro1, libro2) -> libro1.getAnoPublicacion() - libro2.getAnoPublicacion());

    // Generar la tabla con la lista de libros ordenada
      String  libros="<div class=\"row row-cols-1 row-cols-md-4 g-4\">";  

    for (Libro libro : librosList) {
        libros+=card(libro);
        encontrado=true;
    }
    libros+="</div>";
    if (!encontrado){
        libros= noEncontrado();
    }

        return libros;
    }
    public String noEncontrado(){
         String libros= "<div class=\"box\" style=\" border-radius: 3.3rem;\">\n" +
                        "<center>\n" +
                        "<div class=\"card \" >\n" +
                        "<div class=\"card-body\">\n" +
                        "<h1 style=\"font-family: 'bold', sans-serif; font-size: 5rem;  letter-spacing: 7px;\"> Nada por aquí...</h1>\n" +
                        "\n" +
                        "<a href=\"gestionLibros.jsp\"style=\"font-family: 'Regular', sans-serif;font-size:3rem; margin-top: -50px; color:#a86b4c;\"> Contribuye!</a>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</center>\n" +
                        "</div>";
        return libros;
    }
    public String card(Libro libro){
        String libros = "<div class=\"col\">"
                +"<div class=\"card\">"  
                + "<div class=\"card-body\">"
                + "<img src=\"imagenes/"+libro.getFotoPortada()+"\" class=\"card-img-top\" alt=\""+libro.getFotoPortada()+"\" width=\"200px\" height=\"300px\">"
                + "<center><h2 class=\"card-title\">"+libro.getTitulo()+"</h2></center>"
                + "<p class=\"card-text\"> <b> Autor: </b>"+libro.getAutor()+"</p>"
                + "<p class=\"card-text\"> <b>Año: </b>"+libro.getAnoPublicacion()+"</p>"
                + "<p class=\"card-text\"> <b>Genero: </b>"+libro.getGenero()+"</p>";
                if(libro.getPrestado()==null){
                    libros+=" <center><a href=\"#\" class=\"btn btn-primary\" data-bs-toggle=\"alquilar\" data-bs-target=\"#alquilarModal\" data-nombre=\""+libro.getId()+"\" style=\" background-color: #5e3824;\">Alquilar</a></center>";
                }else if(libro.getPrestado()!=null){
                     libros+= "<p><b>Disponible en: </b>"+libro.getPrestado().getTiempo()+"</p>";
                } else{
                     libros+="<p><b>No disponible</b>";

                }
                libros+="</div>"
                + "</div>"
                + "</div>";
                return libros;
    }
}
       
   


