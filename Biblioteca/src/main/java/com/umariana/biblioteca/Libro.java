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
public class Libro implements Serializable {

    /**
     * variables para guardar los atributos del libro
     */
    private String titulo;
    private String autor;
    private int anoPublicacion;
    private String fotoPortada;
    private boolean prestamo;

    /**
     * constructores de la clase libro
     *
     * @param titulo
     * @param autor
     * @param anoPublicacion
     * @param fotoPortada
     * @param prestamo
     */
    public Libro(String titulo, String autor, int anoPublicacion, String fotoPortada, boolean prestamo) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
        this.fotoPortada = fotoPortada;
        this.prestamo = prestamo;
    }

    public Libro() {
    }

    //METODOS GETTER Y SETTER
    /**
     *
     * @return titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     *
     * @return autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     *
     * @param autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     *
     * @return anoPublicacion
     */
    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    /**
     *
     * @param anoPublicacion
     */
    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    /**
     *
     * @return fotoPortada
     */
    public String getFotoPortada() {
        return fotoPortada;
    }

    /**
     *
     * @param fotoPortada
     */
    public void setFotoPortada(String fotoPortada) {
        this.fotoPortada = fotoPortada;
    }

    /**
     *
     * @return prestamo
     */
    public boolean isPrestamo() {
        return prestamo;
    }

    /**
     *
     * @param prestamo
     */
    public void setPrestamo(boolean prestamo) {
        this.prestamo = prestamo;
    }

}
