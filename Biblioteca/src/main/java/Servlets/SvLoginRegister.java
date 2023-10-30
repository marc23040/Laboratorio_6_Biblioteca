/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.umariana.biblioteca.Metodos;
import com.umariana.biblioteca.PersistenciaArchivo;
import com.umariana.biblioteca.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "SvLoginRegister", urlPatterns = {"/SvLoginRegister"})
public class SvLoginRegister extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    //REGISTER
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          //Obtener el contexto del servlet
        ServletContext context = getServletContext();
        
        int cedula=Integer.parseInt(request.getParameter("cedula"));
        String nombre=request.getParameter("nombre");
        String contrasenia=request.getParameter("contrasenia");
        
        ArrayList<Usuarios> users = PersistenciaArchivo.deserializarUsuarios(context);

         if(Metodos.usuarioIg(cedula, users)){
            Usuarios user = new Usuarios(cedula, nombre, contrasenia);
            
            users.add(user);

            PersistenciaArchivo.serializarUsuarios(users, context);

            response.sendRedirect("index.jsp?add=si");
         }else{
            response.sendRedirect("index.jsp?add=no");
         }          
      
    }

    //LOGIN
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          //Obtener el contexto del servlet
        ServletContext context = getServletContext();
        
        int cedula=Integer.parseInt(request.getParameter("cedula"));
        String contrasenia=request.getParameter("contrasenia");
        System.out.println(cedula+contrasenia);
        
        ArrayList<Usuarios> users = PersistenciaArchivo.deserializarUsuarios(context);
        String ingreso=Metodos.ingresoUsuarios(cedula, contrasenia, users);

         if(ingreso.equals("no")){
           
            request.getRequestDispatcher("index.jsp?ingreso=" + ingreso).forward(request, response);
         }else{
            request.getSession().setAttribute("usuario", ingreso);
            response.sendRedirect("login.jsp");
         }           
       
        

       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
