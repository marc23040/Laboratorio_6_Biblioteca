/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.biblioteca;

import java.io.Serializable;

/**
 * Clase de usuarios
 * @author Maria- Juan- Alejandro- Juan 
 */
public class Usuarios implements Serializable {

    /**
     * variables de datos del usuario
     */
    private int cedula;
    private String nombre;
    private String contrasena;
    private int penalizacion;

    /**
     * contstructores de la clase usuarios
     */
    public Usuarios() {
    }

    /**
     * Constructor
     * @param cedula
     * @param nombre
     * @param contrasena 
     * @param penalizacion 
     */


    public Usuarios(int cedula, String nombre, String contrasena, int penalizacion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.penalizacion = penalizacion;
    }

    //METODOS GETTER Y SETTER

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getPenalizacion() {
        return penalizacion;
    }

    public void setPenalizacion(int penalizacion) {
        this.penalizacion = penalizacion;
    }
    
}
