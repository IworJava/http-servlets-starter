package com.dmdev.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-disposition", "attachment; filename=\"filename.txt\"");
        resp.setContentType("plain/text");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());



        try (
                OutputStream outputStream = resp.getOutputStream();
                InputStream stream = DownloadServlet.class.getClassLoader().getResourceAsStream("first.json")
        ) {
            outputStream.write(stream.readAllBytes());
        }
    }
}
