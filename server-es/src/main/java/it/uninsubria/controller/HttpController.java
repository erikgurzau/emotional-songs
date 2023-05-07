package it.uninsubria.controller;

import com.sun.net.httpserver.HttpHandler;
import it.uninsubria.config.RouteConfig;

public interface HttpController extends HttpHandler, RouteConfig {

}
