package it.uninsubria.emotionalsongs.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
        if (inputStream.available() == 0)
            return null;

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(inputStream, clazz);
    }

    public static byte[] convertObjectToBytes(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(obj);
    }


    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isEmpty(Object obj) {
        return isNull(obj) || obj == "";
    }

    public static String formatLocalDateTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * Estrae i parametri dalla query string dell'URI e li restituisce come una mappa.
     * @param uri l'URI contenente la query string
     * @return una mappa contenente i parametri della query string
     */
    public static Map<String, String> getQueryParams(URI uri) {
        Map<String, String> queryParams = new HashMap<>();
        String query = uri.getQuery();
        if (query != null) {
            String[] params = query.split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    queryParams.put(key, value);
                }
            }
        }
        return queryParams;
    }



    /**
     * Risolve le variabili di percorso dal pattern e dal percorso specificati.
     *
     * @param pattern il pattern contenente le variabili di percorso
     * @param path    il percorso da confrontare con il pattern
     * @return una mappa contenente le variabili di percorso risolte
     */
    public static Map<String, String> getPathVariables(String pattern, String path) {
        Map<String, String> pathVariables = new HashMap<>();
        Pattern compiledPattern = compilePattern(pattern);
        Matcher matcher = compiledPattern.matcher(path);

        // Verifica se il percorso corrisponde al pattern
        if (matcher.matches()) {
            // Ottiene i nomi delle variabili di percorso dal pattern
            Pattern variablePattern = Pattern.compile(":([^/]+)");
            Matcher variableMatcher = variablePattern.matcher(pattern);

            // Ottiene i valori corrispondenti delle variabili di percorso dal percorso
            int groupCount = matcher.groupCount();
            while (variableMatcher.find()) {
                String variableName = variableMatcher.group(1);
                for (int i = 1; i <= groupCount; i++) {
                    String group = matcher.group(i);
                    pathVariables.put(variableName, group);
                    break;
                }
            }
        }

        return pathVariables;
    }



    /**
     * Compila il pattern effettuando l'escape delle barre e convertendo i nomi delle variabili in gruppi di cattura denominati.
     * @param pattern il pattern da compilare
     * @return il pattern compilato
     */
    private static Pattern compilePattern(String pattern) {
        String escapedPattern = pattern.replaceAll(":([^/]+)", "(?<$1>[^/]+)");
        return Pattern.compile(escapedPattern);
    }


    /**
     * Verifica se un percorso corrisponde a un determinato pattern.
     *
     * @param pattern il pattern da confrontare
     * @param path    il percorso da verificare
     * @return true se il percorso corrisponde al pattern, false altrimenti
     */
    public static boolean isPathMatching(String pattern, String path) {
        String regex = pattern.replaceAll(":([^/]+)", "(?<$1>[^/]+)");

        regex = "^" + regex + "$";

        try {
            Pattern compiledPattern = Pattern.compile(regex);
            Matcher matcher = compiledPattern.matcher(path);
            return matcher.matches();
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }





}
