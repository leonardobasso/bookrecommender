package com.bookrecommender.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * Crypt e decrypt usando Argon2
 * @see com.bookrecommender.jdbc.UserSQL
 */
public class PassTools {
    /**
     * Crypta le password usando Argon2
     *
     * @param clearPass La password non criptata
     * @return La password criptata
     * @author Leonardo Basso
     */
    public static String hash(String clearPass) {
        Argon2 argon2 = Argon2Factory.create();
        return argon2.hash(10, 65536, 1, clearPass);
    }

    /**
     * Confronta due password, una in chiaro e una criptata
     *
     * @param clearPass  La password non criptata
     * @param hashedPass La password criptata
     * @return <i>true</i> se sono uguale, <i>false</i> altrimenti
     * @author Leonardo Basso
     */
    public static boolean compare(String clearPass, String hashedPass) {
        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(hashedPass, clearPass);
    }
}
