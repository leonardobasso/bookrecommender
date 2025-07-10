package com.bookrecommender.jdbc;

import com.bookrecommender.model.Book;
import com.bookrecommender.utils.DbInfo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Tutte le operazioni di SQL/JDBC per le operazioni sui libri
 *
 * @author Leonardo Basso
 * @see com.bookrecommender.model.Book
 * @see com.bookrecommender.controller.BookController
 */
public class BookSQL {
    /**
     * Esegue la query sql per ritornare una lista di tutti i libri
     *
     * @return Una lista con tutti i libri al suo interno
     * @author Leonardo Basso
     */
    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<>();

        try (Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM libro");
             ResultSet rs = statement.executeQuery()) {

            System.out.println("Getting all the books");
            while (rs.next()) {
                books.add(new Book(
                        rs.getString("id"),
                        rs.getString("Nome"),
                        rs.getString("Autore"),
                        rs.getString("Categoria"),
                        rs.getString("Publisher"),
                        rs.getFloat("Prezzo"),
                        rs.getString("MesePub"),
                        rs.getInt("AnnoPub")
                ));
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException("Error accessing the database.", e);
        }
    }
}
