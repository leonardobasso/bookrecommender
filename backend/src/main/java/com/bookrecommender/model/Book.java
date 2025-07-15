package com.bookrecommender.model;

/**
 * Model del libro
 * 
 * @see com.bookrecommender.jdbc.BookSQL
 * @see com.bookrecommender.controller.BookController
 */
public class Book {
    private String id;
    private String nome;
    private String autore;
    private String descrizione;
    private String categoria;
    private String publisher;
    private float prezzo;
    private String mesePub;
    private int annoPub;

    /**
     * Model del libro
     * @param id Id del libro
     * @param nome Nome del libro
     * @param autore Nome autore del libro
     * @param descrizione Descrizione del libro
     * @param categoria Categoria del libro
     * @param publisher Publisher del libro
     * @param prezzo Prezzo del libro
     * @param mesePub Mese in cui il libro è stato pubblicato
     * @param annoPub Anno in cui il libro è stato pubblicato
     *
     * @see com.bookrecommender.jdbc.BookSQL
     * @see com.bookrecommender.controller.BookController
     * @author Leonardo Basso
     */
    public Book(String id, String nome, String autore, String descrizione, String categoria, String publisher, float prezzo, String mesePub, int annoPub) {
        this.id = id;
        this.nome = nome;
        this.autore = autore;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.publisher = publisher;
        this.prezzo = prezzo;
        this.mesePub = mesePub;
        this.annoPub = annoPub;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAutore() {
        return autore;
    }
    public String getCategoria(){
        return categoria;
    }
    public String getPublisher() {
        return publisher;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public String getMesePub() {
        return mesePub;
    }

    public int getAnnoPub() {
        return annoPub;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
