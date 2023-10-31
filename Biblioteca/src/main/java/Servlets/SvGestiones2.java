/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.umariana.biblioteca.Biblioteca;
import com.umariana.biblioteca.Libro;
import com.umariana.biblioteca.Metodos;
import com.umariana.biblioteca.PersistenciaArchivo;
import com.umariana.biblioteca.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author maria
 */
@WebServlet(name = "SvGestiones2", urlPatterns = {"/SvGestiones2"})
public class SvGestiones2 extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          Biblioteca libros = new Biblioteca(); //creacion de un objeto tipo biblioteca
          ServletContext context = getServletContext(); //variable necesaria para obtener el contexto del servlet
        //se lee la informacion de los objetos ya guardados y se deserializan
        libros = PersistenciaArchivo.deserializarBiblioteca(context);//
        //pedimos el titulo del libro y lo guardamos en una variable
        int id =Integer.parseInt(request.getParameter("id"));
        

        //creamos un nuevo libro y llamamos al metodo encontrarLibro para adquirir sus atributos
        //enviamos como parametro el titulo para que el metodo lo filtre en el array 
        Libro li = libros.encontrarLibro(id);

        if (li != null) {
            String libroHtml ="<div class=\"input-group mb-3\">\n" +
"                        <span class=\"input-group-text\" id=\"basic-addon1\">Id</span>\n" +
"                        <input type=\"text\" name=\"id\" id=\"id\" class=\"form-control\" aria-describedby=\"basic-addon1\" value="+id+" readonly>\n" +
"                    </div>"+ "<div class=\"input-group mb-3\">\n" +
"                        <span class=\"input-group-text\" id=\"basic-addon1\">Titulo</span>\n" +
"                        <input type=\"text\" name=\"titulo\" id=\"titulo\" class=\"form-control\" aria-describedby=\"basic-addon1\"  value="+li.getTitulo()+" required>\n" +
"                    </div>"+ "<div class=\"input-group mb-3\">\n" +
"                        <span class=\"input-group-text\" id=\"basic-addon1\">Autor</span>\n" +
"                        <input type=\"text\" id=\"autor\" name=\"autor\" class=\"form-control\" aria-describedby=\"basic-addon1\" value="+li.getAutor()+" required>\n" +
"                    </div>"+"<div class=\"input-group mb-3\">\n" +
"                        <span class=\"input-group-text\" id=\"basic-addon1\">Año</span>\n" +
"                        <input type=\"text\" id=\"anio\" name=\"anio\" class=\"form-control\" aria-describedby=\"basic-addon1\" value="+li.getAnoPublicacion()+" required>\n" +
"                    </div>"+"<div class=\"input-group mb-3\">\n" +
"                        <span class=\"input-group-text\" id=\"basic-addon1\">Genero</span>\n" +
"                        <select class=\"form-select\" name=\"genero\" aria-label=\"Default select example\">\n" +
"                        <option selected value="+li.getGenero()+">"+li.getGenero()+"</option>\n" +
"                        <option value=\"Aventuras\">Aventuras</option>\n" +
"                        <option value=\"Ciencia Ficcion\">Ciencia Ficción</option>\n" +
"                        <option value=\"Romance\">Romance</option>\n" +
"                        <option value=\"Fantasia\">Fantasia</option>\n" +
"                        <option value=\"Humor\">Humor</option>\n" +
"                        <option value=\"Poesia\">Poesia</option>\n" +
"                        <option value=\"Mitologia\">Mitologia</option>\n" +
"                        <option value=\"Teatro\">Teatro</option>\n" +
"                        <option value=\"Infantil\">Infantil</option>\n" +
"                        <option value=\"Escolar\">Escolar</option>\n" +
"                      </select>\n" +
"                    </div>"+"<div class=\"input-group mb-3\">\n" +
"                        <span class=\"input-group-text\" id=\"basic-addon1\">Portada</span>\n" +
"                       <input type=\"file\" name=\"fotoPortada\" class=\"form-control\" id=\"fotoPortada\" accept=\"image/*\" required>\n" +
"                    </div>";
                    
                   
            
                  
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(libroHtml);
        } else {
            // Maneja el caso en el que no se encuentra el perro
            response.setContentType("text/plain");
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write("No disponible :(");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          //Obtener el contexto del servlet
        ServletContext context = getServletContext();
        ArrayList<Usuarios> users = PersistenciaArchivo.deserializarUsuarios(context);
        String cambio="";
        int cedula=Integer.parseInt(request.getParameter("cedula"));
        String contraVieja=request.getParameter("contraAnt");
        String contraNueva=request.getParameter("contrasenia");
        if(!Metodos.ingresoUsuarios(cedula, contraVieja, users).equals("no")){
            Usuarios user=Metodos.encontrarUsuario(cedula, users);
            user.setContrasena(contraNueva);
            PersistenciaArchivo.serializarUsuarios(users, context);
            cambio="si";
        }
        request.getRequestDispatcher("login.jsp?cambio=" + cambio).forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
