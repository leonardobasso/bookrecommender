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
    public static void register(Context ctx) {
        try {
            User user = ctx.bodyAsClass(User.class);

            if (user.getUserID() == null || user.getNome() == null || user.getCognome() == null ||
                    user.getTaxcode() == null || user.getEmail() == null) {
                ctx.status(400).json(Map.of(
                        "status", "error",
                        "message", "Campi mancanti"
                ));
                return;
            }

            String userID = UserSQL.register(user);

            ctx.json(Map.of(
                    "status", "success",
                    "body", userID
            ));
        } catch (Exception e) {
            ctx.json(Map.of(
                    "status", "error",
                    "body", e.toString()
            ));
            e.printStackTrace();
        }
    }
}
