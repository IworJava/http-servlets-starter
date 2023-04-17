package com.dmdev.http.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class DatagramRunner {
    public static final String HOST_NAME = "localhost";
    public static void main(String[] args) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(HOST_NAME);
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] buffer = "Hello from UDP client".getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, inetAddress, 7777);
            socket.send(packet);
        }
    }
}
