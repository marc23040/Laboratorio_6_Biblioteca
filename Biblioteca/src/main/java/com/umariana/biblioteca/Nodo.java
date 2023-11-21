/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// Declaración del paquete
package com.umariana.biblioteca;

// Importación de la clase Serializable desde java.io
import java.io.Serializable;

/**
 * Esta clase representa un nodo en una lista enlazada utilizada para un sistema de gestión de bibliotecas.
 * Cada nodo contiene información sobre un libro (Libro) y referencias al siguiente y al anterior nodo.
 * @author Maria- Juan- Alejandro- Juan 
 */
public class Nodo implements Serializable {

    // Variables miembro privadas
    private Libro libro;    // Representa el libro almacenado en el nodo
    private Nodo siguiente; // Referencia al siguiente nodo en la lista enlazada
    private Nodo anterior;  // Referencia al nodo anterior en la lista enlazada

    /**
     * Constructor para la clase Nodo.
     * @param libro     El libro asociado al nodo.
     * @param sig       Referencia al siguiente nodo.
     * @param ant       Referencia al nodo anterior.
     */
    public Nodo(Libro libro, Nodo sig, Nodo ant) {
        this.libro = libro;
        this.siguiente = sig;
        this.anterior = ant;
    }

    /**
     * Método getter para obtener el libro asociado al nodo.
     * @return El libro almacenado en el nodo.
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Método getter para obtener el siguiente nodo en la lista enlazada.
     * @return Referencia al siguiente nodo.
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     * Método setter para establecer el siguiente nodo en la lista enlazada.
     * @param siguiente Referencia al siguiente nodo.
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Método getter para obtener el nodo anterior en la lista enlazada.
     * @return Referencia al nodo anterior.
     */
    public Nodo getAnterior() {
        return anterior;
    }

    /**
     * Método setter para establecer el nodo anterior en la lista enlazada.
     * @param anterior Referencia al nodo anterior.
     */
    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
}

