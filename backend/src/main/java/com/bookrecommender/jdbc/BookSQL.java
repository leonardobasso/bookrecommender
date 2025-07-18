package com.bookrecommender.jdbc;

import com.bookrecommender.model.Book;
import com.bookrecommender.utils.DbInfo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Tutte le operazioni di SQL/JDBC per le operazioni sui libri
 *
 * @author Lorenzo Beretta
 * @see com.bookrecommender.model.Book
 * @see com.bookrecommender.controller.BookController
 */
public class BookSQL {
    /**
     * Esegue una query di inserimento di un libro nei libri consigliati dati libroId, libreriaId e
     *
     * @param suggestedId   id del libro
     * @param initialBookId id del libro che vede suggestedId come libro consigliato
     * @param userId        Id dell'utente
     * @return l'id del libro consigliato
     * @author Lorenzo Beretta, Leonardo Basso
     */
    public static String insertLibriConsigliati(int suggestedId, int initialBookId, String userId) {
        List<Book> existingSuggestions = BookSQL.getLibriConsigliatiUtenteLibri(userId, initialBookId);
        if (existingSuggestions.size() > 3) {
            return "Già inseriti 3 libri";
        }
        boolean check = false;
        List<Book> booksInLibrary = LibrarySQL.getBooksByUser(userId);
        for (Book book : booksInLibrary) {
            if (Integer.parseInt(book.getId()) == initialBookId) {
                check = true;
                break;
            }
        }
        if (!check) return "Libro non presente nella libreria";

        try (Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
             PreparedStatement statement = conn.prepareStatement(" INSERT INTO ConsigliLibri (userid, libroconsigliatoid, librodeiconsigliid) VALUES (?, ?, ?)");
        ) {
            statement.setString(1, userId);
            statement.setInt(2, suggestedId);
            statement.setInt(3, initialBookId);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new row was inserted successfully!");
            }
            return suggestedId + "";
        } catch (SQLException e) {
            throw new RuntimeException("Error accessing the database at method insertLibriConsigliati(). ", e);
        }
    }

    /**
     * Esegue la query sql per ritornare tutti i libri consigliati da un utente per un determinato libro
     *
     * @param UserId  id utente
     * @param LibroId id libro
     * @return Una lista con tutti i libri cercati al suo interno
     * @author Lorenzo Beretta
     */
    public static List<Book> getLibriConsigliatiUtenteLibri(String UserId, int LibroId) {
        List<Book> books = new LinkedList<>();
        try {
            Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Libro l INNER JOIN ConsigliLibri as lc ON l.id=lc.libroconsigliatoid where lc.userid=? and lc.librodeiconsigliid=?");
            statement.setString(1, UserId);
            statement.setInt(2, LibroId);
            ResultSet rs = statement.executeQuery();


            System.out.println("Getting all the Libri consigliati");
            while (rs.next()) {
                books.add(new Book(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getString("autore"),
                        rs.getString("descrizione"),
                        rs.getString("categoria"),
                        rs.getString("publisher"),
                        rs.getFloat("prezzo"),
                        rs.getString("mesepub"),
                        rs.getInt("annopub")
                ));

            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException("Error accessing the database at method getLibriConsigliatiUtenteLibri(). ", e);
        }

    }

    /**
     * Esegue la query sql per ritornare tutti i libri che sono stati selezionati come consigliati di un determinato libro
     *
     * @param LibroId id libro
     * @return Una lista con tutti i libri cercati al suo interno
     * @author Lorenzo Beretta
     */
    public static List<Book> getLibriConsigliatiLibri(int LibroId) {
        List<Book> books = new LinkedList<>();
        try {
            Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
            PreparedStatement statement = conn.prepareStatement("SELECT DISTINCT l.* FROM Libro as l INNER JOIN ConsigliLibri as lc ON l.id=lc.libroconsigliatoid where lc.librodeiconsigliid=?");
            statement.setInt(1, LibroId);
            ResultSet rs = statement.executeQuery();


            System.out.println("Getting all the Libri consigliati");
            while (rs.next()) {
                books.add(new Book(
                        rs.getString("id"),
                        rs.getString("nome"),
                        rs.getString("autore"),
                        rs.getString("descrizione"),
                        rs.getString("categoria"),
                        rs.getString("publisher"),
                        rs.getFloat("prezzo"),
                        rs.getString("mesepub"),
                        rs.getInt("annopub")
                ));

            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException("Error accessing the database at method getLibriConsigliatiLibri(). ", e);
        }


    }

    /**
     * Esegue la query sql per ritornare una lista di tutti i libri
     *
     * @return Una lista con tutti i libri al suo interno
     * @author Lorenzo Beretta
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
                        rs.getString("nome"),
                        rs.getString("autore"),
                        rs.getString("descrizione"),
                        rs.getString("categoria"),
                        rs.getString("publisher"),
                        rs.getFloat("prezzo"),
                        rs.getString("mesepub"),
                        rs.getInt("annopub")
                ));
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException("Error accessing the database at method getAllBooks(). ", e);
        }
    }

    /**
     * Esegue la query sql per ritornare un libro specifico
     *
     * @param id id del libro da cercare
     * @return il libro
     * @author Lorenzo Beretta
     */
    public static Book getSingleBook(int id) {
        try {
            Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Libro WHERE id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return new Book(
                    rs.getString("id"),
                    rs.getString("nome"),
                    rs.getString("autore"),
                    rs.getString("descrizione"),
                    rs.getString("categoria"),
                    rs.getString("publisher"),
                    rs.getFloat("prezzo"),
                    rs.getString("mesepub"),
                    rs.getInt("annopub")
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
     * @author Lorenzo Beretta
     */
    public static List<Book> searchBook(String name, String author, Integer year) {
        List<Book> books = new LinkedList<>();
        StringBuilder sb = new StringBuilder("SELECT * FROM Libro WHERE 1=1");

        List<Object> params = new LinkedList<>();

        if (name != null && !name.isEmpty()) {
            sb.append(" AND LOWER(nome) LIKE ?");
            params.add("%" + name.toLowerCase() + "%");
        }
        if (author != null && !author.isEmpty()) {
            sb.append(" AND LOWER(autore) LIKE ?");
            params.add("%" + author.toLowerCase() + "%");
        }
        if (year != null) {
            sb.append(" AND annopub = ?");
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
                            rs.getString("nome"),
                            rs.getString("autore"),
                            rs.getString("descrizione"),
                            rs.getString("categoria"),
                            rs.getString("publisher"),
                            rs.getFloat("prezzo"),
                            rs.getString("mesepub"),
                            rs.getInt("annopub")
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
