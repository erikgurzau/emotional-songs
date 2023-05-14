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

public abstract class Controller implements HttpHandler, RouteConfig {

    /**
     * Metodo per ottenere i path params da una richiesta HttpExchange.
     * @param exchange l'oggetto HttpExchange che rappresenta la richiesta
     * @return una mappa che associa il nome del path param al suo valore
     */
    public static Map<String, String> getPathParams(HttpExchange exchange) {
        Map<String, String> pathParams = new HashMap<>();

        // ottiene l'URI della richiesta
        URI uri = exchange.getRequestURI();
        // ottiene il path dell'URI
        String path = uri.getPath();

        // analizza il path per trovare i path params
        String[] pathSegments = path.split("/");
        for (int i = 0; i < pathSegments.length; i++) {
            String segment = pathSegments[i];
            if (segment.startsWith("{") && segment.endsWith("}")) {
                // è un path param
                String paramName = segment.substring(1, segment.length() - 1);
                String paramValue = exchange.getAttribute(paramName).toString();
                pathParams.put(paramName, paramValue);
            }
        }

        return pathParams;
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
