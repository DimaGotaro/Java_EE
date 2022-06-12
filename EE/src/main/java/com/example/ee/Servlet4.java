package com.example.ee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

@WebServlet("/Servlet4")
public class Servlet4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // как сжать весь код HTML в gzip и послать его на сервер
        if (req.getHeader("accept-encoding").contains("gzip")) { // сравниваем на равенство два значения
            resp.setHeader("Content-Encoding", "gzip"); // задаём заголовок
            PrintWriter printWriter = new PrintWriter(new GZIPOutputStream(resp.getOutputStream()));
            printWriter.write("Hello World!");
            printWriter.flush();
            printWriter.close();
        }
    }
}
