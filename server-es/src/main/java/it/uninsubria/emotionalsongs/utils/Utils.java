package it.uninsubria.emotionalsongs.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Questa classe contiene metodi di utilità generale.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 */
public class Utils {


    /**
     * Converte una stringa in formato snake_case in CamelCase.
     * Esempio: "test_string" diventa" diventa "TestString"
     * @param input la stringa in formato snake_case da convertire
     * @return la stringa convertita in formato camelCase
     */
    public static String convertToCamelCase(String input) {
        // Crea un oggetto StringBuilder per contenere la stringa convertita
        StringBuilder sb = new StringBuilder();

        // Divide la stringa di input in un array di parole separate dal carattere underscore "_"
        String[] words = input.split("_");

        // Cicla su tutte le parole e le converte in camelCase
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // Converte la prima lettera della parola corrente in maiuscolo e le altre lettere in minuscolo
            String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();

            sb.append(capitalizedWord);
        }

        // Restituisce la stringa convertita in formato camelCase
        return sb.toString();
    }

    /**
     * Calcola l'hash SHA-256 di una stringa di input.
     * @param input la stringa di input da hashare
     * @return l'hash calcolato come una stringa esadecimale
     * @throws NoSuchAlgorithmException se l'algoritmo di hash SHA-256 non è disponibile
     */
    public static String sha256(String input) throws NoSuchAlgorithmException {
        // Crea un oggetto MessageDigest utilizzando l'algoritmo di hash SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Calcola l'hash della stringa di input come array di byte
        byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

        // Converte l'array di byte in una stringa esadecimale
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        // Restituisce l'hash calcolato come una stringa esadecimale
        return hexString.toString();
    }

    public static <T> T convertInputStreamToObject(InputStream inputStream, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, clazz);
    }

    public static byte[] convertObjectToBytes(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(obj);
    }


}
