package lesson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet_L extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // req - данные которые передаются с сервера
        // resp - данные которые мы посылаем на сервер
        PrintWriter a1 = resp.getWriter(); // поток в который будем записывать информацию
        a1.write("<h2>Hello World!</h2>");
    }
}
