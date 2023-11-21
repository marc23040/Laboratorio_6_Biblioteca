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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SvRegister
 * @author Maria- Juan- Alejandro- Juan 
 */
@WebServlet(name = "SvRegister", urlPatterns = {"/SvRegister"})
public class SvRegister extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Registrar
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          //Obtener el contexto del servlet
        ServletContext context = getServletContext();
        
        // Obtener la cédula, nombre y contraseña desde la solicitud
        int cedula=Integer.parseInt(request.getParameter("cedula"));
        String nombre=request.getParameter("nombre");
        String contrasenia=request.getParameter("contrasenia");
        
        // Deserializar la lista de usuarios desde un archivo utilizando la clase PersistenciaArchivo
        ArrayList<Usuarios> users = PersistenciaArchivo.deserializarUsuarios(context);

        // Verificar si ya existe un usuario con la misma cédula
         if(Metodos.usuarioIg(cedula, users)){
             // Crear un nuevo objeto Usuarios con la información proporcionada
            Usuarios user = new Usuarios(cedula, nombre, contrasenia,0);
            
            // Agregar el nuevo usuario a la lista de usuarios
            users.add(user);

            // Serializar la lista actualizada de usuarios y guardarla en el archivo
            PersistenciaArchivo.serializarUsuarios(users, context);
            // Redireccionar a la página index.jsp con un parámetro indicando que se añadió exitosamente
            response.sendRedirect("index.jsp?add=si");
         }else{
              // Redireccionar a la página index.jsp con un parámetro indicando que no se pudo añadir
            response.sendRedirect("index.jsp?add=no");
         }          
      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
