package it.uninsubria.emotionalsongs.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.uninsubria.emotionalsongs.utils.Utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Controller implements HttpHandler {


    public static Map<String, String> getPathParams(String path, String regexPath, String ...pathKeys) {
        Map<String, String> mappaPathParams = new HashMap<>();
        Pattern pattern = Pattern.compile(regexPath);
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()) {
            for (String key: pathKeys) {
                mappaPathParams.put(key, matcher.group(key));
            }
        }

        return mappaPathParams;
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

    public <T> T getRequestBody(HttpExchange exchange, Class<T> clazz) throws IOException {
        return Utils.convertInputStreamToObject(exchange.getRequestBody(), clazz);
    }

    /**
     * Risolve le variabili di percorso dal pattern e dal percorso specificati.
     * @param pattern il pattern contenente le variabili di percorso
     * @param path    il percorso da confrontare con il pattern
     * @return una mappa contenente le variabili di percorso risolte
     */
    public static Map<String, String> getPathVariables(String pattern, String path) {
        Map<String, String> variabiliPercorso = new HashMap<>();
        Pattern compiledPattern = compilePattern(pattern);
        Matcher matcher = compiledPattern.matcher(path);

        // Verifica se il percorso corrisponde al pattern
        if (matcher.matches()) {
            Pattern variablePattern = Pattern.compile(":([^/]+)");
            Matcher variableMatcher = variablePattern.matcher(pattern);

            // Trova i nomi delle variabili e i valori corrispondenti
            while (variableMatcher.find()) {
                String nomeVariabile = variableMatcher.group(1);
                String valoreVariabile = matcher.group(nomeVariabile);
                variabiliPercorso.put(nomeVariabile, valoreVariabile);
            }
        }

        return variabiliPercorso;
    }

    /**
     * Compila il pattern effettuando l'escape delle barre e convertendo i nomi delle variabili in gruppi di cattura denominati.
     *
     * @param pattern il pattern da compilare
     * @return il pattern compilato
     */
    private static Pattern compilePattern(String pattern) {
        // Esegue l'escape delle barre e converte i nomi delle variabili in gruppi di cattura denominati
        String regex = pattern
                .replaceAll("/", "\\\\/")
                .replaceAll(":([^/]+)", "(?<$1>[^/]+)");
        return Pattern.compile(regex);
    }



    /**
     * Verifica se un percorso corrisponde a un determinato pattern.
     * @param pattern il pattern da confrontare
     * @param path    il percorso da verificare
     * @return true se il percorso corrisponde al pattern, false altrimenti
     */
    public static boolean isPathMatching(String pattern, String path) {
        Pattern compiledPattern = compilePattern(pattern);
        Matcher matcher = compiledPattern.matcher(path);

        return matcher.matches();
    }



    public void sendResponse(HttpExchange exchange, Object responseObj, int statusCode) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, 0);

        OutputStream os = exchange.getResponseBody();
        os.write(Utils.convertObjectToBytes(responseObj));
        os.flush();
        os.close();
    }

}
