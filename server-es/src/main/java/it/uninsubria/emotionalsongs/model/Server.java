package it.uninsubria.emotionalsongs.model;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.ServerController;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;

import static it.uninsubria.emotionalsongs.utils.Costanti.PORTA_SERVER;

public class Server {

    private final HttpServer httpServer;
    private final ServerController serverController;
    private final Integer porta;
    private boolean running;


    public Server() {
        this.porta = PORTA_SERVER;
        serverController = new ServerController();
        httpServer = createHttpServer(porta, ApiConfig.PATH_SERVER_API, serverController);
    }
    public Server(Integer porta) {
        this.porta = porta;
        serverController = new ServerController();
        httpServer = createHttpServer(porta, ApiConfig.PATH_SERVER_API, serverController);

    }

    public void start() {
        Logger.info(this.getClass().getSimpleName() + ": running sulla porta " + porta);
        httpServer.start();
        running = true;
    }

    public void stop() {
        httpServer.stop(0);
        running = false;
    }


    /**
     * Crea e restituisce un'istanza di HttpServer configurata con la porta e l'URI specificati,
     * associata all'HttpHandler fornito.
     * @param porta la porta sulla quale aprire il server HTTP
     * @param pathURI l'URI associato all'HttpHandler
     * @param httpHandler l'HttpHandler associato all'URI
     * @return l'istanza di HttpServer configurata, o null se si è verificato un errore durante la creazione
     */
    public static HttpServer createHttpServer(Integer porta, String pathURI, HttpHandler httpHandler) {
        try {
            // Crea un'istanza di HttpServer sulla porta specificata
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(porta), 0);

            // Crea un contesto HTTP associato all'URI specificato e all'HttpHandler fornito
            httpServer.createContext(pathURI, httpHandler);

            // Imposta il thread pool dell'HttpServer a 4
            httpServer.setExecutor(Executors.newFixedThreadPool(4));

            // Restituisce l'istanza di HttpServer configurata
            return httpServer;
        } catch (IOException e) {
            // Se si verifica un'eccezione durante la creazione, stampa l'errore e restituisce null
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        new Server().start();
    }

}
