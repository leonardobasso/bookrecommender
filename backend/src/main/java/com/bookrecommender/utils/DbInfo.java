package com.bookrecommender.utils;

/**
 * Contiene informazioni costanti riguardanti il Database
 *
 * @author Leonardo Basso
 * @see com.bookrecommender.jdbc.ReviewSQL
 * @see com.bookrecommender.jdbc.LibrarySQL
 * @see com.bookrecommender.jdbc.BookSQL
 * @see com.bookrecommender.jdbc.UserSQL
 */
public class DbInfo {
    public static final String url = "jdbc:postgresql://db:5432/libreria";
    public static final String user = "admin";
    public static final String pass = "admin";
}
