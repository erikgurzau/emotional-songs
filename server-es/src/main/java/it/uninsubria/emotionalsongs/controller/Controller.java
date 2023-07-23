package it.uninsubria.emotionalsongs.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public abstract class Controller implements HttpHandler {

    public <T> T getRequestBody(HttpExchange exchange, Class<T> clazz) throws IOException {
        return Utils.convertInputStreamToObject(exchange.getRequestBody(), clazz);
    }

    public void sendResponse(HttpExchange exchange, Object responseObj, int statusCode) {
        try {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(statusCode, 0);

            OutputStream os = exchange.getResponseBody();
            os.write(Utils.convertObjectToBytes(responseObj));
            os.flush();
            os.close();
        } catch (IOException e) {
            sendResponse(exchange, Costanti.ErrorCode.INTERNAL_SERVER_ERROR, Costanti.ErrorCode.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }

    public String getSessionId(HttpExchange exchange) {
        List<String> params = exchange.getRequestHeaders().get(Costanti.KEY_SESSION_ID);
        if (!Utils.isNull(params) && params.size() > 0)
            return params.get(0).toString();
        return null;
    }

}
