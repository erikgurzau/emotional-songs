package it.uninsubria.model;

import com.sun.net.httpserver.HttpHandler;
import it.uninsubria.config.ServerConfig;
import it.uninsubria.controller.ServerController;
import it.uninsubria.service.LoggerService;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

import static it.uninsubria.config.RouteConfig.PATH_ROOT_API;

public class Server implements ServerConfig {

    private HttpServer httpServer;
    private ServerController serverController;
    private Integer porta;
    private boolean running;


    public Server() {
        this.porta = PORTA;
        serverController = new ServerController();
        httpServer = Server.createHttpServer(porta, PATH_ROOT_API, serverController);
    }
    public Server(Integer porta) {
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

    static HttpServer createHttpServer(Integer porta, String pathURI, HttpHandler httpHandler) {
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

    public static void main(String[] args) {
        new Server().start();
    }

}
