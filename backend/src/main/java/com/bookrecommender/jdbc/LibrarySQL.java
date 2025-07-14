package com.bookrecommender.jdbc;

import com.bookrecommender.model.Book;
import com.bookrecommender.model.Libreria;
import com.bookrecommender.utils.DbInfo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Tutte le operazioni di SQL/JDBC per le operazioni sulle librerie
 *
 * @author Lorenzo Beretta
 * @see com.bookrecommender.model.Libreria
 * @see com.bookrecommender.controller.LibraryController
 */
public class LibrarySQL {
    /**
     * Dato un utente, ritorna tutte le sue librerie
     *
     * @param id l'id dell'utente
     * @return la lista delle librerie
     * @author Lorenzo Beretta
     */
    public static List<Libreria> getLibrariesByUser(String id) {
        try (
                Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM Libreria WHERE UserId = ?");
        ) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            List<Libreria> librerias = new LinkedList<>();
            while (rs.next()) {
                librerias.add(new Libreria(
                        rs.getString("LibreriaId"),
                        rs.getString("NomeLibreria"),
                        rs.getString("UserId")
                ));
            }

            return librerias;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Esegue una query per riportare tutte le librerie di un determinato utente
     *
     * @param userId id dell'utente di cui si vogliono cercare le librerie
     * @return La lista dei libri messi in una libreria da un utente
     * @author Lorenzo Beretta
     */
    public static List<Book> getBooksByUser(String userId) {
        try (
                Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
                PreparedStatement statement = conn.prepareStatement("SELECT DISTINCT * FROM Libro l WHERE l.id IN (SELECT libroId FROM LibroInLibreria WHERE LibreriaId IN (SELECT LibreriaId FROM Libreria WHERE UserId = ?))")
        ) {
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            List<Book> books = new LinkedList<>();
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
            throw new RuntimeException(e);
        }
    }

    /**
     * esegue una query che crea una nuova libreria
     *
     * @param userId id dell'utente che crea la libreria
     * @param name   nome della libreria
     * @author Lorenzo Beretta
     */
    public static void createLibrary(String name, String userId) {
        try (
                Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
                PreparedStatement statement = conn.prepareStatement("INSERT INTO Libreria(NomeLibreria, UserId) VALUES (?, ?)");
        ) {
            statement.setString(1, name);
            statement.setString(2, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * esegue una query per aggiungere un libro alla libreria
     *
     * @param libreriaId id della libreria a cui si vuol aggiungere un libro
     * @param libroId    id del libro da inserire nella libreria
     * @author Lorenzo Beretta
     */
    public static void addBook(int libreriaId, int libroId) {
        try (
                Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
                PreparedStatement statement = conn.prepareStatement("INSERT INTO LibroInLibreria(LibroId, LibreriaId) VALUES (?, ?)");
        ) {
            statement.setInt(1, libroId);
            statement.setInt(2, libreriaId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * esegue una query per riportare tutti i libri di una determinata libreria
     *
     * @param idLibreria id della libreria di cui si vuol vedere i libri legati
     * @return  La lista dei libri dentro la libreria
     * @author Lorenzo Beretta
     */
    public static List<Book> details(int idLibreria) {
        try (
                Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM Libro l WHERE l.id IN (SELECT LibroId FROM LibroInLibreria WHERE LibreriaId = ?) ");
        ) {
            statement.setInt(1, idLibreria);
            ResultSet rs = statement.executeQuery();
            List<Book> books = new LinkedList<>();
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
            throw new RuntimeException(e);
        }
    }
}