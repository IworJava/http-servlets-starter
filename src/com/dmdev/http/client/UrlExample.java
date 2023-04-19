package com.dmdev.http.client;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class UrlExample {
    public static void main(String[] args) throws IOException {
        URL url = new URL("file:/Users/iw/IdeaProjects/1Matveyenka/http-servlets-starter/src/com/dmdev/http/socket/DatagramRunner.java");
        URLConnection connection = url.openConnection();
        System.out.println(new String(connection.getInputStream().readAllBytes()));
    }

    private static void extracted() throws IOException {
        URL url = new URL("https://www.google.com");
        URLConnection connection = url.openConnection();
        connection.getContent();
    }
}
