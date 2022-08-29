package it.uninsubria.app.managers.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Classe che definisce la sicurezza delle password dell'applicazione
 * @author  Erik Gurzau
 * @author  Alessia Metaj
 * @author  Sara Biavaschi
 * @version 1.0.0
 */
public class SecurePassword {

    /**
     * Metodo per criptare la password attraverso l'utilizzo dell'algoritmo SHA1
     * @param password Stringa che contiene la password in chiaro da criptare
     * @return Stringa che contiene la password criptata secondo l'algoritmo di SHA1
     */
    public static String encrypt(String password){
        return sha1(password);
    }

    /**
     * Implementazione dell'algoritmo di SHA1
     * @param password Stringa che contiene la password in chiaro da criptare
     * @return Stringa che contiene la password criptata secondo l'algoritmo di SHA1
     */
    private static String sha1(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes(StandardCharsets.UTF_8));
            sha1 = SecurePassword.bytesToHex(crypt.digest());
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    /**
     * Converte dei byte in caratteri esadecimali
     * @param bytes Array di bytes
     * @return Stringa che contiene la conversione dei bytes in esadecimale
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


    /**
     * Genera una password lunga 16 caratteri in modo randomico
     * @return Stringa che contiene la password in chiaro generata in modo randomico
     */
    public static String genPsw() {
        String lettere = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$%&/?@[]^{}";
        int lengthPsw = 16;
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(lengthPsw);
        for(int i = 0; i < lengthPsw; i++) {
            sb.append(lettere.charAt(rnd.nextInt(lettere.length())));
        }
        return sb.toString();
    }

}
