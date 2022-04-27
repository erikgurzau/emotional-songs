package it.uninsubria.app.managers.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SecurePassword {
    private static final String CARATTERI = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!#$%&/?@[]^{}";
    public static final int LENGTH_PSW = 16;

    public static String encrypt(String password){
        return sha1(password);
    }

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

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


    public static String genPsw() {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(LENGTH_PSW);
        for(int i = 0; i < LENGTH_PSW; i++) {
            sb.append(CARATTERI.charAt(rnd.nextInt(CARATTERI.length())));
        }
        return sb.toString();
    }

}
