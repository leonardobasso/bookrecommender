package com.bookrecommender.jdbc;

import com.bookrecommender.model.Book;
import com.bookrecommender.utils.DbInfo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Tutte le operazioni di SQL/JDBC per le operazioni sulle recensioni
 *
 * @author Lorenzo Beretta
 * @see com.bookrecommender.model.Book
 * @see com.bookrecommender.controller.BookController
 */
public class ReviewSQL
{
public static insertReview(int UserId, int LibroId, int StileVoto, int ContenutoVoto, int GradevolezzaVoto, int OriginalitaVoto, int EdizioneVoto, int VotoFinale, String StileCommento, String ContenutoCommento, String GradevolezzaCommento, String OriginalitaCommento, String EdizioneCommento)
{


    try (Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);

    { PreparedStatement statement = conn.prepareStatement("INSERT INTO `recensione` (`UserId`, `LibroId`, `StileVoto`, `ContenutoVoto`, `GradevolezzaVoto`, `OriginalitaVoto`, `EdizioneVoto`, `VotoFinale`, `StileCommento`, `ContenutoCommento`, `GradevolezzaCommento`, `OriginalitaCommento`, `EdizioneCommento`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        pstmt.setInt(1,userId );
        pstmt.setInt(2, LibroId);
        pstmt.setInt(3, StileVoto);
        pstmt.setInt(4, contenutoVoto);
        pstmt.setInt(5,  GradevolezzaVoto);
        pstmt.setInt(6,  OriginalitaVoto);
        pstmt.setInt(7,  EdizioneVoto);
        pstmt.setInt(8,  VotoFinale);
        pstmt.setInt(9,  StileCommento);
        pstmt.setInt(10,  ContenutoCommento);
        pstmt.setInt(11, GradevolezzaCommento);
        pstmt.setInt(12,  OriginalitaCommento);
        pstmt.setInt(13,  EdizioneCommento);

        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new row was inserted successfully!");
        }


    } catch (SQLException e) {
        throw new RuntimeException("Error accessing the database at method insertLibriConsigliati(). ", e);
    }


}

public static getReviewByBook(int LibroId)
{

    List<Recensione> Recensioni = new LinkedList<>();
    try  {
        Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM recensione where LibroId=?");
        statement.setInt(1, LibroId);
        ResultSet rs = statement.executeQuery();


        System.out.println("Getting all the Libri consigliati");
        while (rs.next()) {
            Recensioni.add(new recensione(

                    rs.getInt("LibroId"),
                    rs.getInt("StileVoto"),
                    rs.getInt("ContenutoVoto"),
                    rs.getInt("GradevolezzaVoto"),
                    rs.getInt("OriginalitaVoto"),
                    rs.getInt("EdizioneVoto"),
                    rs.getInt("VotoFinale"),
                    rs.getString("StileCommento"),
                    rs.getString("ContenutoCommento"),
                    rs.getString("GradevolezzaCommento"),
                    rs.getString("OriginalitaCommento"),
                    rs.getString("EdizioneCommento"),

            ));

        }
        return books;
    } catch (SQLException e) {
        throw new RuntimeException("Error accessing the database at method getLibriConsigliatiUtenteLibri(). ", e);
    }


}





}