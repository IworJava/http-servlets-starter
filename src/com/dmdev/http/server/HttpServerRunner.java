package com.dmdev.http.server;

public class HttpServerRunner {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(9001);
        httpServer.run();
    }
}
