package com.example.ee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;

@WebServlet("/Servlet3")
public class Servlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(
            "<html>\n" +
            "<head>\n" +
            "<title>Servlet 3</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "<form action=\"Servlet3\">\n" + // куда отправляем значения
            "<label for=\"name\">one:</label>\n" +
            "<br/>" +
            "<input type=\"text\" id=\"name\" name='one'" +
            "<br/>" +
            "<br/>" +
            "<label for=\"name2\">two:</label>\n" +
            "<br/>" +
            "<input type=\"text\" id=\"name2\" name='two'" +
            "<br/>" +
            "<input type=\"submit\" name='submit'>" +
            "</form>" +
            "</body>\n" +
            "</html>");
        System.out.println(req.getParameter("one"));
        System.out.println(req.getParameter("two"));
        Enumeration<String> parameterNames2 = req.getParameterNames();
        while (parameterNames2.hasMoreElements()) {
            // что бы два раза не вызывать nextElement(), потому что ошибка
            String s = parameterNames2.nextElement();
            if (!Objects.equals(s, "submit")) { // если имя параметра не submit, то выводятся значения
                System.out.println(s + " = " + req.getParameter(s));
            }
        }
        System.out.println();
    }
}
