/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.biblioteca;

import java.io.Serializable;

/**
 * Clase que representa un préstamo de la biblioteca.
 */
public class Prestamo implements Serializable {
    
    // Atributos de la clase
    int cedula;       // Identificación del usuario que realiza el préstamo
    String tiempo;    // Duración del préstamo

    // Constructores
    public Prestamo() {
    }

    public Prestamo(int cedula, String tiempo) {
        this.cedula = cedula;
        this.tiempo = tiempo;
    }

    // Métodos de acceso (getters y setters)
    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}

