package com.bookrecommender.controller;

import com.bookrecommender.jdbc.UserSQL;
import com.bookrecommender.model.User;
import io.javalin.http.Context;

import java.util.Map;

/**
 * Controller per l'utente
 *
 * @author Leonardo Basso
 * @see com.bookrecommender.model.User
 * @see com.bookrecommender.jdbc.UserSQL
 */
public class UserController {
    /**
     * Controller per la registrazione
     *
     * @param ctx Context
     * @author Leonardo Basso
     */
    public static void register(Context ctx) {
        try {
            User user = ctx.bodyAsClass(User.class);

            if (user.getUserID() == null || user.getNome() == null || user.getCognome() == null ||
                    user.getTaxcode() == null || user.getEmail() == null || user.getPassword() == null) {
                ctx.status(400).json(Map.of(
                        "status", "error",
                        "message", "Campi mancanti"
                ));
                return;
            }

            User createUser = UserSQL.register(user);

            ctx.status(200).json(Map.of(
                    "status", "success",
                    "body", createUser
            ));
        } catch (Exception e) {
            ctx.status(500).json(Map.of(
                    "status", "error",
                    "body", e.getMessage()
            ));
            throw new RuntimeException(e);
        }
    }

    /**
     * Controller per il login
     *
     * @param ctx Context
     * @author Leonardo Basso
     */
    public static void login(Context ctx) {
        try {
            User userData = ctx.bodyAsClass(User.class);
            String userID = userData.getUserID();
            String password = userData.getPassword();

            if (userID == null || password == null) {
                ctx.status(400).json(Map.of(
                        "status", "error",
                        "body", "Parametri mancanti"
                ));
                return;
            }
            User user = UserSQL.login(userID, password);
            if (user == null) {
                ctx.status(400).json(Map.of(
                        "status", "error",
                        "body", "Id o Password sbagliati"
                ));
            } else {
                ctx.status(200).json(Map.of(
                        "status", "success",
                        "body", user
                ));
            }
        } catch (Exception e) {
            ctx.status(500).json(Map.of(
                    "status", "error",
                    "body", e.getMessage()
            ));
            throw new RuntimeException(e);
        }
    }
}
