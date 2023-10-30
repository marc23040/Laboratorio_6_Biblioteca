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
public class Usuarios implements Serializable {

    /**
     * variables de datos del usuario
     */
    private int cedula;
    private String nombre;
    private String contrasena;

    /**
     * contstructores de la clase usuarios
     */
    public Usuarios() {
    }

    /**
     * 
     * @param cedula
     * @param nombre
     * @param contrasena 
     */
    public Usuarios(int cedula, String nombre, String contrasena) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    //METODOS GETTER Y SETTER
    /**
     *
     * @return cedula
     */
    public int getCedula() {
        return cedula;
    }

    /**
     *
     * @param cedula
     */
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    /**
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     *
     * @param contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}