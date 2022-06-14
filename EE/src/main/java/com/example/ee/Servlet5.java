package com.example.ee;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebServlet(name = "Servlet5", value = "/Servlet5") // прописан через web.xml
public class Servlet5 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Перенаправление, значения передаются
        // перенаправление на любую другую страницу
//        ServletContext servletContext = getServletContext();
//        String s = "/perenapr.jsp";
//        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(s);
//        requestDispatcher.forward(request, response);

        // http://localhost:8082/EE_war/Servlet5?one=boba в Servlet3 one передастся
        // задали значение в Servlet5 то оно используется в месте куда перенаправили
//        ServletContext servletContext = getServletContext();
//        String s = "/Servlet3";
//        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(s);
//        requestDispatcher.forward(request, response);

        // Переадресация, значения не передаются
//        // http://localhost:8082/EE_war/Servlet5?one=boba one не передастся
//        String s = request.getContextPath() + "/Servlet3"; // request.getContextPath() - путь к текущему приложению
//        // - http://localhost:8082/EE_war
//        response.sendRedirect(s);

//        // то же
//        response.sendRedirect(request.getContextPath() + "/Servlet3");

        // получим параметр из web.xml
        String mes = getServletContext().getInitParameter("message"); // параметр для всех сервлетов
        String mes2 = getServletConfig().getInitParameter("message2"); // параметр только для Servlet5
        response.getWriter().write("<h1>" + mes + mes2 + "</h1>");

        // в web.xml обозначена обработка арифметических ошибок с выводом error.jsp
//        int x = 0;
//        int y = 8/x;

        // Контекст запроса
        request.setAttribute("name", "Tom");

        // Контекст приложения
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("age", 34);

        // Контекст сессии
        HttpSession session = request.getSession();
        session.setAttribute("gender", "male");

        String[] users = new String[] {"Tom", "Bob", "Sam"};
        request.setAttribute("users", users);

        User tom = new User("Tom", 35);
        request.setAttribute("user", tom); // хоть данные и приватны, они всё равно отправятся

        getServletContext().getRequestDispatcher("/lesson.jsp").forward(request, response); // перенаправление
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
