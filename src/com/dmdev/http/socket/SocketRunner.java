package com.dmdev.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;

public class SocketRunner {
    public static final String HOST_NAME = "localhost";

    public static void main(String[] args) throws IOException {
        var inetAddress = Inet4Address.getByName(HOST_NAME);
        try (
                Socket socket = new Socket(inetAddress, 7777);
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream inputStream = new DataInputStream(socket.getInputStream())
        ) {
            outputStream.writeUTF("Hello world!");
            System.out.println(inputStream.readUTF());
        }
    }
}
