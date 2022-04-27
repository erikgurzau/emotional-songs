package it.uninsubria.app.users.interfaces;

public interface LimitUserFields {
    /**
     * Lunghezza minima di caratteri per la password
     */
    byte MIN_LENGTH_PSW = 8;

    /**
     * Lunghezza massima di caratteri per la password
     */
   byte MAX_LENGTH_PSW = 16;


    /**
     * Lunghezza minima di caratteri per il nome
     */
    byte MIN_NAME_LENGTH = 3;

    /**
     * Lunghezza massima di caratteri per il nome
     */
    byte MAX_NAME_LENGTH = 50;


    /**
     * Lunghezza minima di caratteri per il cognome
     */
    byte MIN_SURNAME_LENGTH = 3;

    /**
     * Lunghezza massima di caratteri per il nome
     */
    byte MAX_SURNAME_LENGTH = 50;


    /**
     * Lunghezza minima e massima di caratteri per il codice fiscale
     */
    byte CF_LENGTH = 16;
}
