package it.uninsubria.model;

import com.sun.net.httpserver.HttpHandler;
import it.uninsubria.config.ServerConfig;
import it.uninsubria.controller.ServerController;
import it.uninsubria.service.LoggerService;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;


public class Server implements ServerConfig {
    private HttpServer httpServer;

    private ServerController serverController;
    private int porta;
    private boolean running;


    public Server() {
        this.porta = porta;
        serverController = new ServerController();
        httpServer = Server.createHttpServer(PORTA, PATH_ROOT_API, serverController);
    }
    public Server(int porta) {
        this.porta = porta;
        serverController = new ServerController();
        httpServer = Server.createHttpServer(porta, PATH_ROOT_API, serverController);

    }

    public void start() {
        LoggerService.info("ServerES avviato sulla la porta " + porta);
        httpServer.start();
        running = true;
    }

    public void stop() {
        httpServer.stop(0);
        running = false;
    }


    static HttpServer createHttpServer(int porta, String pathURI, HttpHandler httpHandler) {
        HttpServer httpServer = null;
        try {
            httpServer = HttpServer.create(new InetSocketAddress(porta), 0);
            httpServer.createContext(pathURI, httpHandler);
            httpServer.setExecutor(null);
        } catch (IOException e) {
            LoggerService.errore(e.getMessage());
        }
        return httpServer;
    }

    public static void main(String[] args) {
        new Server().start();
    }

}
