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
public class Nodo implements Serializable{

    private Libro libro;
    private Nodo siguiente;
    private Nodo anterior;

    public Nodo(Libro libro, Nodo sig, Nodo ant) {
        this.libro = libro;
        this.siguiente = sig;
        this.anterior = ant;
    }

    public Libro getLibro() {
        return libro;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }


}
