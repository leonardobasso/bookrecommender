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
 * @see com.bookrecommender.model.Book
 * @see com.bookrecommender.controller.BookController
 */
public class LibrarySQL {
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