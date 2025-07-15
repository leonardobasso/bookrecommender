package com.bookrecommender.model;

/**
 * Model dell'utente
 *
 * @author Leonardo Basso
 * @see com.bookrecommender.jdbc.UserSQL
 * @see com.bookrecommender.controller.UserController
 */
public class User {
    private String userID;
    private String nome;
    private String cognome;
    private String password;
    private String taxcode;
    private String email;

    /**
     * Mode dell'utente
     *
     * @param userID  Lo userID dell'utente
     * @param nome    Il nome dell'utente
     * @param cognome Il cognome dell'utente
     * @param taxcode Il codice fiscale dell'utente
     * @param email   L'email dell'utente
     * @author Leonardo Basso
     */
    public User(String userID, String nome, String cognome, String password, String taxcode, String email) {
        this.userID = userID;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.taxcode = taxcode;
        this.email = email;
    }

    /**
     * Serve a Jackson
     */
    public User() {
    }

    public String getUserID() {
        return userID;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getTaxcode() {
        return taxcode;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
