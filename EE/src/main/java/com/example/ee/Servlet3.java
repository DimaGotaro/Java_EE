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
        String one = req.getParameter("one");
        String two = req.getParameter("two");
        String three = req.getParameter("three");
        // заменим символы в поле ввода, для того что бы избежать взлома от уязвимости XSS, для того что бы нельзя было
        // выполнить код в котором есть <script>
        three = three == null ? "" : three.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        // если three = null то возвращаем пустое значение, в остадьных случаях заменяем символы в параметре(< на &lt;)
        // в HTML < это &lt;
        resp.getWriter().println(
            "<html>\n" +
            "<head>\n" +
            "<title>Servlet 3</title>\n" +
            "<link rel=\"stylesheet\" href=\"styles.css\">" +
            "</head>\n" +
            "<body>\n" +
                "<form action=\"Servlet3\" method='post'>\n" + // куда отправляем значения
                // если method='get', то данные будут видны в адресной строке в браузере, post - не будут видны
                // post не будет обработан doGet-ом, для этого определяем doPost и в нём вызываем doGet
                "<label for=\"name\">one:</label>\n" +
                "<br/>" +
                "<input type=\"text\" id=\"name\" name='one'>" +
                "<br/>" +
                "<label for=\"name2\">two:</label>\n" +
                "<br/>" +
                "<input type=\"text\" id=\"name2\" name='two'>" +
                "<br/>" +
                "<label for=\"name3\">three:</label>\n" +
                "<br/>" +
                "<textarea name=\"three\" cols=\"50\" rows=\"10\" id=name3></textarea>" +
                "<br/>" +
                "<input type=\"submit\" name='submit'>" +
                "</form>" +
                " one = " + one + // отображение полученных данных на странице сервлета
                "<br/>" +
                " two = " + two +
                "<br/>" +
                " three = " + three +
            "</body>\n" +
            "</html>");
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
        Enumeration<String> parameterNames2 = req.getParameterNames();
        while (parameterNames2.hasMoreElements()) {
            // что бы два раза не вызывать nextElement(), потому что ошибка
            String s = parameterNames2.nextElement();
            if (!Objects.equals(s, "submit")) { // если имя параметра не submit, то выводятся значения
                System.out.println(s + " = " + req.getParameter(s));
            }
        }
        Enumeration<String> headerNames = req.getHeaderNames(); // получаем все имена в хедере
        while (headerNames.hasMoreElements()) {
            String s = headerNames.nextElement(); // каждое имя хедера
            System.out.println(s + " = " + req.getHeader(s)); // имя = значение хедера с именем s
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
