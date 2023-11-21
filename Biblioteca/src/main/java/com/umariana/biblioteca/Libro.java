/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.biblioteca;

import java.io.Serializable;

/**
 *
 * @author Maria- Juan- Alejandro- Juan 
 */
public class Libro implements Serializable {

    /**
     * variables para guardar los atributos del libro
     */
    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacion;
    private String fotoPortada;
    private String genero;
    private Prestamo prestado;

    /**
     * constructores de la clase libro
     *
     * @param id
     * @param titulo
     * @param autor
     * @param anoPublicacion
     * @param fotoPortada
     * @param genero
     * @param prestado
     */
    
    

    public Libro(int id, String titulo, String autor, int anoPublicacion, String fotoPortada, String genero, Prestamo prestado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
        this.fotoPortada = fotoPortada;
        this.genero = genero;
        this.prestado = prestado;
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

    public Prestamo getPrestado() {
        return prestado;
    }

    public void setPrestado(Prestamo prestado) {
        this.prestado = prestado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
}
