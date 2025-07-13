package com.bookrecommender.model;

/**
 * Model delle recensioni
 *
 * @see com.bookrecommender.jdbc.ReviewSQL
 * @see com.bookrecommender.controller.ReviewController
 */
public class Review {
    private String userId;
    private int libroId;
    private int stileVoto;
    private int contenutoVoto;
    private int gradevolezzaVoto;
    private int originalitaVoto;
    private int edizioneVoto;
    private String stileCommento;
    private String contenutoCommento;
    private String gradevolezzaCommento;
    private String originalitaCommento;
    private String edizioneCommento;
    private int votoFinale;
    private String commentoFinale;

    /**
     * Model della review
     * @param userId L'id dell'utente che ha scritto la recensione
     * @param libroId L'id del libro recensito
     * @param stileVoto Voto numerico (1..5) dello stile
     * @param contenutoVoto Voto numerico (1..5) del contenuto
     * @param gradevolezzaVoto Voto numerico (1..5) della gradevolezza
     * @param originalitaVoto Voto numerico (1..5) dell'originalita
     * @param edizioneVoto Voto numerico (1..5) dell'edizione
     * @param stileCommento Commento sullo stile
     * @param contenutoCommento Commento sul contenuto
     * @param gradevolezzaCommento Commento sulla gradevolezza
     * @param originalitaCommento Commento sull'originalita
     * @param edizioneCommento Commento sull'edizione
     * @param votoFinale Voto finale (1..5) derivato dalla media dei voti
     * @author Leonardo Basso
     */
    public Review(String userId, int libroId, int stileVoto, int contenutoVoto,
                  int gradevolezzaVoto, int originalitaVoto, int edizioneVoto, String stileCommento,
                  String contenutoCommento, String gradevolezzaCommento, String originalitaCommento,
                  String edizioneCommento, int votoFinale, String commentoFinale) {
        this.userId = userId;
        this.libroId = libroId;
        this.stileVoto = stileVoto;
        this.contenutoVoto = contenutoVoto;
        this.gradevolezzaVoto = gradevolezzaVoto;
        this.originalitaVoto = originalitaVoto;
        this.edizioneVoto = edizioneVoto;
        this.stileCommento = stileCommento;
        this.contenutoCommento = contenutoCommento;
        this.gradevolezzaCommento = gradevolezzaCommento;
        this.originalitaCommento = originalitaCommento;
        this.edizioneCommento = edizioneCommento;
        this.votoFinale = votoFinale;
        this.commentoFinale = commentoFinale;
    }

    public String getCommentoFinale() {
        return commentoFinale;
    }

    public int getVotoFinale() {
        return votoFinale;
    }

    public String getEdizioneCommento() {
        return edizioneCommento;
    }

    public String getOriginalitaCommento() {
        return originalitaCommento;
    }

    public String getGradevolezzaCommento() {
        return gradevolezzaCommento;
    }

    public String getContenutoCommento() {
        return contenutoCommento;
    }

    public int getEdizioneVoto() {
        return edizioneVoto;
    }

    public int getOriginalitaVoto() {
        return originalitaVoto;
    }

    public int getGradevolezzaVoto() {
        return gradevolezzaVoto;
    }

    public int getContenutoVoto() {
        return contenutoVoto;
    }

    public int getStileVoto() {
        return stileVoto;
    }

    public int getLibroId() {
        return libroId;
    }

    public String getUserId() {
        return userId;
    }

    public String getStileCommento() {
        return stileCommento;
    }
}
