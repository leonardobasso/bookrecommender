package com.bookrecommender.jdbc;

import com.bookrecommender.model.Review;
import com.bookrecommender.utils.DbInfo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Tutte le operazioni di SQL/JDBC per le operazioni sulle recensioni
 *
 * @author Lorenzo Beretta
 * @see com.bookrecommender.model.Review
 * @see com.bookrecommender.controller.ReviewController
 */
public class ReviewSQL {
    /**
     * Esegue una query di inserimento di una nuova review
     *
     * @param libroId              id del libro consigliato
     * @param stileVoto            voto sullo stile del libro
     * @param userId               id dell'utente
     * @param contenutoVoto        voto sul contenuto del libro
     * @param gradevolezzaVoto     voto di quanto sia gradevole il libro
     * @param originalitaVoto      voto sull'originalità del libro
     * @param edizioneVoto         voto sull'edizione del libro
     * @param votoFinale           voto finale del libro
     * @param stileCommento        commento riguardante lo stile del libro
     * @param contenutoCommento    commmento riguardante il contenuto del libro
     * @param gradevolezzaCommento commmento riguardante la gradevolezza del libro
     * @param originalitaCommento  commmento riguardante l'originalità del libro
     * @param edizioneCommento     commmento riguardante l'edizione del libro
     * @author Lorenzo Beretta
     */
    public static void insertReview(String userId, int libroId, int stileVoto, int contenutoVoto,
                                    int gradevolezzaVoto, int originalitaVoto, int edizioneVoto,
                                    String stileCommento, String contenutoCommento, String gradevolezzaCommento,
                                    String originalitaCommento, String edizioneCommento, int votoFinale,
                                    String commentoFinale) {
        String sql = "INSERT INTO Recensione (UserId, LibroId, StileVoto, ContenutoVoto, GradevolezzaVoto, OriginalitaVoto, EdizioneVoto, VotoFinale, StileCommento, ContenutoCommento, GradevolezzaCommento, OriginalitaCommento, EdizioneCommento, CommentoFinale) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, userId);
            statement.setInt(2, libroId);
            statement.setInt(3, stileVoto);
            statement.setInt(4, contenutoVoto);
            statement.setInt(5, gradevolezzaVoto);
            statement.setInt(6, originalitaVoto);
            statement.setInt(7, edizioneVoto);
            statement.setInt(8, votoFinale);
            statement.setString(9, stileCommento);
            statement.setString(10, contenutoCommento);
            statement.setString(11, gradevolezzaCommento);
            statement.setString(12, originalitaCommento);
            statement.setString(13, edizioneCommento);
            statement.setString(14, commentoFinale);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new review was inserted successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error accessing the database at method insertReview().", e);
        }
    }

    /**
     * Esegue una query per riportare tutte le recensioni di un determinato libro
     *
     * @param libroId id del libro di cui si vogliono le recensioni
     * @return La lista delle review di un libro
     * @author Lorenzo Beretta
     */
    public static List<Review> getReviewByBook(int libroId) {
        List<Review> recensioni = new LinkedList<>();

        try (Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM Recensione WHERE LibroId = ?")) {

            statement.setInt(1, libroId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                recensioni.add(new Review(
                        rs.getString("UserId"),
                        rs.getInt("LibroId"),
                        rs.getInt("StileVoto"),
                        rs.getInt("ContenutoVoto"),
                        rs.getInt("GradevolezzaVoto"),
                        rs.getInt("OriginalitaVoto"),
                        rs.getInt("EdizioneVoto"),
                        rs.getString("StileCommento"),
                        rs.getString("ContenutoCommento"),
                        rs.getString("GradevolezzaCommento"),
                        rs.getString("OriginalitaCommento"),
                        rs.getString("EdizioneCommento"),
                        rs.getInt("VotoFinale"),
                        rs.getString("CommentoFinale")
                ));
            }
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
