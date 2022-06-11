package com.example.ee;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {

    int o = 0;

    // 1 (жизненный цикл)
    @Override
    public void init() throws ServletException { // инициализация сервлета, запускается только в первый раз
        System.out.println("Initialization");
    }

    // 3
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        synchronized (this) { // без синхронизирования полчим не корректный результат
            for (int i = 0; i < 100000; i++) {
                o++;
            }
            System.out.println(o);
        }
        System.out.println("doGet method");

        resp.setContentType("text/html");
        PrintWriter a = resp.getWriter();
        String s = "<h2>Interesting</h2>";
        String s1 = "<head>" +
                "<title>Servlet 1</title>" +
                "</head>";
        a.println("<html>");
        a.println(s1);
        a.println("<body><h1>Servlet 1</h1>");
        a.println(s);
        a.println("</body>");
        a.println("</html>");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread() { // создаём потоки которые обращаются к сервлету
                @Override
                public void run() {
                    try {
                        URLConnection f = new URL("http://localhost:8082/EE_war/Servlet1").openConnection();
                        f.getInputStream();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.start();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    // 2
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service method");
        super.service(req, res);
    }

    // последний
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
