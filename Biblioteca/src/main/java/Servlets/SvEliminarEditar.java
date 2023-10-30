/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.umariana.biblioteca.Biblioteca;
import com.umariana.biblioteca.Libro;
import com.umariana.biblioteca.PersistenciaArchivo;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "SvEliminarEditar", urlPatterns = {"/SvEliminarEditar"})
public class SvEliminarEditar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String titulo=request.getParameter("titulo");
        Biblioteca libros = new Biblioteca();
        ServletContext context = getServletContext();
        libros = PersistenciaArchivo.deserializarBiblioteca(context);
        libros.eliminarLibro(titulo);
        PersistenciaArchivo.serializarBiblioteca(libros, context);
        response.sendRedirect("login.jsp?delete=si");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        
        String titulo=request.getParameter("titulo");
        String autor=request.getParameter("autor");
        int anio=Integer.parseInt(request.getParameter("anio"));
        String foto =request.getParameter("foto");
        String pres=request.getParameter("prestamo");
        boolean prestamo=false;
        if (pres!=null && pres.equals("true")){
            prestamo=true;
        } 
        Biblioteca libros = new Biblioteca();
        libros = PersistenciaArchivo.deserializarBiblioteca(context);
        libros.editar(titulo, autor, anio, foto, prestamo);
        PersistenciaArchivo.serializarBiblioteca(libros, context);
        response.sendRedirect("login.jsp?editado=a");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
