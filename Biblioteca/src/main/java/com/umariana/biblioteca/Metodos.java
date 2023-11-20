package com.umariana.biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 * Clase que contiene métodos relacionados con la gestión de usuarios y libros en un sistema de biblioteca.
 */
public class Metodos {

    /**
     * Verifica si ya existe un usuario con la cédula proporcionada en la lista de usuarios.
     * @param cedula Cédula del usuario a verificar.
     * @param misUsuarios Lista de usuarios.
     * @return Devuelve 'false' si el usuario ya existe, 'true' si no existe.
     * @throws IOException Excepción de E/S.
     */
    public static boolean usuarioIg(int cedula, ArrayList<Usuarios> misUsuarios) throws IOException {
        for (Usuarios u : misUsuarios) {
            if (u.getCedula() == cedula) {
                return false;
            }
        }
        return true;
    }

    /**
     * Encuentra y devuelve un objeto de tipo `Usuarios` con la cédula proporcionada en la lista de usuarios.
     * @param cedula Cédula del usuario a buscar.
     * @param misUsuarios Lista de usuarios.
     * @return Devuelve el objeto `Usuarios` si se encuentra, o 'null' si no se encuentra.
     * @throws IOException Excepción de E/S.
     */
    public static Usuarios encontrarUsuario(int cedula, ArrayList<Usuarios> misUsuarios) throws IOException {
        
        for (Usuarios u : misUsuarios) {
            if (u.getCedula() == cedula) {
                return u;
            }
        }
        return null;
    }

    /**
     * Verifica si existe un usuario con la cédula y contraseña proporcionadas en la lista de usuarios.
     * @param cedula Cédula del usuario.
     * @param contrasenia Contraseña del usuario.
     * @param misUsuarios Lista de usuarios.
     * @return Devuelve el nombre del usuario si la autenticación es exitosa, o "no" si no es exitosa.
     * @throws IOException Excepción de E/S.
     */
    public static String ingresoUsuarios(int cedula, String contrasenia, ArrayList<Usuarios> misUsuarios) throws IOException {
        for (Usuarios u : misUsuarios) {
            if (u.getCedula() == cedula && u.getContrasena().equals(contrasenia)) {
                return u.getNombre();
            }
        }
        return "no";
    }

    /**
     * Lista libros según diferentes criterios de búsqueda y devuelve el resultado como una cadena formateada.
     * @param context Contexto del servlet.
     * @param terminoBusqueda Término de búsqueda.
     * @param cedula Cédula del usuario.
     * @param buscar Término de búsqueda específico.
     * @return Devuelve una cadena formateada con el resultado de la búsqueda de libros.
     */
    public static String listar(ServletContext context, String terminoBusqueda, String cedula, String buscar) {
        Biblioteca libros = new Biblioteca();
        libros = PersistenciaArchivo.deserializarBiblioteca(context);
        if (libros == null) {
            libros = new Biblioteca();
        }
        String tabla = "";

        if (terminoBusqueda == null && buscar == null) {
            tabla = libros.librosLogin();
        } else if (terminoBusqueda != null) {
            if (terminoBusqueda.equals("disponibles")) {
                tabla = libros.librosDisponibles();
            } else if (terminoBusqueda.equals("alquilados")) {
                tabla = libros.librosPrestados(Integer.parseInt(cedula));
            } else if (terminoBusqueda.equals("antiguos")) {
                tabla = libros.librosOrdenadosPorAnoAscendente();
            } else if (terminoBusqueda.equals("recientes")) {
                tabla = libros.librosOrdenadosPorAnoDescendente();
            }

        } else if (buscar != null) {
            tabla = libros.librosLoginBus(buscar);
        }
        return tabla;
    }

    /**
     * Busca libros según un criterio específico y devuelve el resultado como una cadena formateada.
     * @param context Contexto del servlet.
     * @param terminoBusqueda Término de búsqueda.
     * @param cedula Cédula del usuario.
     * @return Devuelve una cadena formateada con el resultado de la búsqueda de libros.
     */
    public static String buscar(ServletContext context, String terminoBusqueda, String cedula) {
        Biblioteca libros = new Biblioteca();
        libros = PersistenciaArchivo.deserializarBiblioteca(context);
        if (libros == null) {
            libros = new Biblioteca();
        }
        String tabla = "";

        if (terminoBusqueda == null) {
            tabla = libros.librosLogin();
        } else if (terminoBusqueda != null) {
            if (terminoBusqueda.equals("disponibles")) {
                tabla = libros.librosDisponibles();
            } else if (terminoBusqueda.equals("alquilados")) {
                tabla = libros.librosPrestados(Integer.parseInt(cedula));
            } else if (terminoBusqueda.equals("antiguos")) {
                tabla = libros.librosOrdenadosPorAnoAscendente();
            } else if (terminoBusqueda.equals("recientes")) {
                tabla = libros.librosOrdenadosPorAnoDescendente();
            }

        }
        return tabla;
    }

    /**
     * Analiza la penalización para un libro según el número de días prestados y el tiempo transcurrido.
     * @param diasPrestados Número de días inicialmente prestados.
     * @param tiempo Tiempo transcurrido desde el préstamo.
     * @return Devuelve un código entero que representa el nivel de penalización.
     */
    public static int analizarPenalizacion(String diasPrestados, int tiempo) {
        int mensaje = 0;
        switch (diasPrestados) {
            case "7 dias":
                if (tiempo > 7 && tiempo < 16) {
                    mensaje = 1;
                } else if (tiempo > 16 && tiempo < 30) {
                    mensaje = 2;
                } else if (tiempo > 30) {
                    mensaje = 3;
                }
                break;
            case "15 dias":
                if (tiempo > 15 && tiempo < 30) {
                    mensaje = 1;
                } else if (tiempo > 30 && tiempo < 45) {
                    mensaje = 2;
                } else if (tiempo > 45) {
                    mensaje = 3;
                }
                break;
            case "1 mes":
                if (tiempo > 60 && tiempo < 75) {
                    mensaje = 1;
                } else if (tiempo > 75 && tiempo < 90) {
                    mensaje = 2;
                } else if (tiempo > 90) {
                    mensaje = 3;
                }
                break;
        }
        return mensaje;
    }
}
