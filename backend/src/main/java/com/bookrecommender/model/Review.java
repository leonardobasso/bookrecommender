package com.bookrecommender.model;

/**
 * Model delle recensioni
 */
public class Review {
    private String userId;
    private String libroId;
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
    private float votoFinale;

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
    public Review(String userId, String libroId, int stileVoto, int contenutoVoto,
                  int gradevolezzaVoto, int originalitaVoto, int edizioneVoto, String stileCommento,
                  String contenutoCommento, String gradevolezzaCommento, String originalitaCommento,
                  String edizioneCommento, float votoFinale) {
        this.userId = userId;
        this.libroId = libroId;
        this.stileVoto = stileVoto;
        this.contenutoVoto = contenutoVoto;
        this.gradevolezzaVoto = gradevolezzaVoto;
        this.originalitaVoto = originalitaVoto;
        this.edizioneVoto = edizioneVoto;
        this.contenutoCommento = contenutoCommento;
        this.gradevolezzaCommento = gradevolezzaCommento;
        this.originalitaCommento = originalitaCommento;
        this.edizioneCommento = edizioneCommento;
        this.votoFinale = votoFinale;
    }
}
