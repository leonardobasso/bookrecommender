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
    public static List<Book> getAllBooks() {
        List<Book> books = new LinkedList<>();

        try (Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM Libro");
             ResultSet rs = statement.executeQuery()) {

            System.out.println("Getting all the books");
            while (rs.next()) {
                books.add(new Book(
                        rs.getString("id"),
                        rs.getString("Nome"),
                        rs.getString("Autore"),
                        rs.getString("Descrizione"),
                        rs.getString("Categoria"),
                        rs.getString("Publisher"),
                        rs.getFloat("Prezzo"),
                        rs.getString("MesePub"),
                        rs.getInt("AnnoPub")
                ));
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException("Error accessing the database at method getAllBooks(). ", e);
        }
    }

    public static Book getSingleBook(int id) {
        try {
            Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Libro WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return new Book(
                    rs.getString("id"),
                    rs.getString("Nome"),
                    rs.getString("Autore"),
                    rs.getString("Descrizione"),
                    rs.getString("Categoria"),
                    rs.getString("Publisher"),
                    rs.getFloat("Prezzo"),
                    rs.getString("MesePub"),
                    rs.getInt("AnnoPub")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Esegue una query di ricerca dei libri dati nome, autore e anno
     *
     * @param name   Nome del libro
     * @param author Autore del libro
     * @param year   Anno di pubblicazione del libro
     * @return Una lista con i libri ottenuti dalla ricerca
     * @author Leonardo Basso
     */
    public static List<Book> searchBook(String name, String author, Integer year) {
        List<Book> books = new LinkedList<>();
        StringBuilder sb = new StringBuilder("SELECT * FROM Libro WHERE 1=1");

        List<Object> params = new LinkedList<>();

        if (name != null && !name.isEmpty()) {
            sb.append(" AND Nome LIKE ?");
            params.add("%" + name + "%");
        }
        if (author != null && !author.isEmpty()) {
            sb.append(" AND Autore LIKE ?");
            params.add("%" + author + "%");
        }
        if (year != null) {
            sb.append(" AND AnnoPub = ?");
            params.add(year);
        }

        try (Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
             PreparedStatement statement = conn.prepareStatement(sb.toString())) {

            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof String) {
                    statement.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    statement.setInt(i + 1, (Integer) param);
                }
            }

            System.out.println(statement.toString());

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getString("id"),
                            rs.getString("Nome"),
                            rs.getString("Autore"),
                            rs.getString("Descrizione"),
                            rs.getString("categoria"),
                            rs.getString("publisher"),
                            rs.getFloat("Prezzo"),
                            rs.getString("MesePub"),
                            rs.getInt("AnnoPub")
                    );
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL Error searching books", e);
        }

        return books;
    }

}
