package com.dmdev.http.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }


    public void run() {
        try (ServerSocket server = new ServerSocket(port)) {
            Socket socket = server.accept();
            processSocket(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
        try (
                socket;
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
        ) {
        //     step 1 handle request
            System.out.println("Request: " + new String(inputStream.readNBytes(400)));
        //     step 1 handle response
            byte[] body = Files.readAllBytes(Path.of("resourses", "example.html"));
            String headers = """
                    HTTP/1.1 200 OK
                    content-type: text/html
                    content-length: %d
                    """.formatted(body.length);
            outputStream.write(headers.getBytes());
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);
        } catch (IOException e) {
            // TODO: 19/04/2023 log error message
            throw new RuntimeException(e);
        }
    }
}
