package com.bookrecommender.model;

/**
 * Model per LibroInLibreria
 * usato per inserire i libri nelle librerie
 */
public class LibroInLibreria {
    private int idLibro;
    private int idLibreria;

    public LibroInLibreria(int idLibro, int idLibreria) {
        this.idLibro = idLibro;
        this.idLibreria = idLibreria;
    }
    public LibroInLibreria(){}

    public int getIdLibro() {
        return idLibro;
    }

    public int getIdLibreria() {
        return idLibreria;
    }
}
