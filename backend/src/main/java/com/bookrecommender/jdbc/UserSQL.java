package com.bookrecommender.jdbc;

import com.bookrecommender.model.User;
import com.bookrecommender.utils.DbInfo;

import java.sql.*;
/**
 * Tutte le operazioni di SQL/JDBC per le operazioni sugli utenti
 *
 * @author Leonardo Basso
 * @see com.bookrecommender.model.User
 * @see com.bookrecommender.controller.UserController
 */
public class UserSQL {
    /**
     * Esegue la query per la registrazione di un utente
     *
     * @param user l'utente
     * @return messaggio con userId o con l'errore ricevuto
     * @author Leonardo Basso
     */
    public static String register(User user) {
        try (
                Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
                PreparedStatement statement = conn.prepareStatement("INSERT INTO Utente (UserId, Nome, Cognome, Taxcode, Email) VALUES (?, ?, ?, ?, ?)");
        ) {
            statement.setString(1, user.getUserID());
            statement.setString(2, user.getNome());
            statement.setString(3, user.getCognome());
            statement.setString(4, user.getTaxcode());
            statement.setString(5, user.getEmail());

            statement.executeUpdate();

            return user.getUserID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
