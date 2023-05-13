package it.uninsubria.utils;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import it.uninsubria.service.LoggerService;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Utils {

    public static HttpServer createHttpServer(Integer porta, String pathURI, HttpHandler httpHandler) {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(porta), 0);
            httpServer.createContext(pathURI, httpHandler);
            httpServer.setExecutor(null);
            return httpServer;
        } catch (IOException e) {
            LoggerService.errore(e.getMessage());
            return null;
        }
    }

    public static String convertToCamelCase(String input) {
        StringBuilder sb = new StringBuilder();
        String[] words = input.split("_");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0) {
                sb.append(word.substring(0, 1).toUpperCase());
                sb.append(word.substring(1).toLowerCase());
            } else {
                sb.append(word.substring(0, 1).toUpperCase());
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }






}
