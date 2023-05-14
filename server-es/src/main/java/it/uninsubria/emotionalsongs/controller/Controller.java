package it.uninsubria.emotionalsongs.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.uninsubria.emotionalsongs.config.RouteConfig;
import it.uninsubria.emotionalsongs.utils.Utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Controller implements HttpHandler, RouteConfig {


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

    public void sendResponse(HttpExchange exchange, Object responseObj, int statusCode) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, 0);

        OutputStream os = exchange.getResponseBody();
        os.write(Utils.convertObjectToBytes(responseObj));
        os.flush();
        os.close();
    }

}
