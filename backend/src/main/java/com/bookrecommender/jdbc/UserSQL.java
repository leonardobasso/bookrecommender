package com.bookrecommender.jdbc;

import com.bookrecommender.model.User;
import com.bookrecommender.utils.DbInfo;
import com.bookrecommender.utils.PassTools;

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
     * @param user l'utente (oggetto)
     * @return l'utente o un errore
     * @author Leonardo Basso
     */
    public static User register(User user) {
        try (
                Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
                PreparedStatement statement = conn.prepareStatement("INSERT INTO Utente (userid, nome, cognome, password, taxcode, email) VALUES (?, ?, ?, ?, ?, ?)");
        ) {
            String hashedPassword = PassTools.hash(user.getPassword());

            statement.setString(1, user.getUserID());
            statement.setString(2, user.getNome());
            statement.setString(3, user.getCognome());
            statement.setString(4, hashedPassword);
            statement.setString(5, user.getTaxcode());
            statement.setString(6, user.getEmail());

            statement.executeUpdate();

            return new User(
                    user.getUserID(),
                    user.getNome(),
                    user.getCognome(),
                    null,
                    user.getTaxcode(),
                    user.getEmail());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Esegue la query per il login di un utente
     *
     * @param userId l'id dell'utente
     * @param password la password in chiaro da comparare
     * @return l'utente o un errore
     * @author Leonardo Basso
     */
    public static User login(String userId, String password) {
        try (
                Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM Utente WHERE userid = ?")
        ) {
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String hashedPass = rs.getString("Password");
                if (PassTools.compare(password, hashedPass)) {
                    return new User(
                            rs.getString("userid"),
                            rs.getString("nome"),
                            rs.getString("cognome"),
                            null,
                            rs.getString("taxcode"),
                            rs.getString("email")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
