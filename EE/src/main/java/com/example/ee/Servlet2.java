package com.example.ee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // в хроме в адресной строке - http://localhost:8082/EE_war/Servlet2?one=hello&two=world&abc=hokku
        // после знака вопроса параметры, разделены &
        String one = req.getParameter("one");
        // "one" - проверяет наличие в адресной строке, при соответствии выводит присвоенное значение
        System.out.println(one);
        String one2 = req.getParameter("two");
        System.out.println(one2);
        // вывод - hello world
        System.out.println();

        // вывод значений параметров
        // http://localhost:8082/EE_war/Servlet2?one=hello&two=world&one=hokku
        String[] ones = req.getParameterValues("one");
        for (String e:
             ones) {
            System.out.println(e);
        }
        System.out.println();

        // вывод имён параметров
        // http://localhost:8082/EE_war/Servlet2?one=hello&two=world&one=hokku&abc=jonh
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            System.out.println(parameterNames.nextElement());
        }
        System.out.println();

        // вывод имени и значения параметров
        // http://localhost:8082/EE_war/Servlet2?one=hello&two=hokku&abc=jonh
        Enumeration<String> parameterNames2 = req.getParameterNames();
        while (parameterNames2.hasMoreElements()) {
            String s = parameterNames2.nextElement();
            // что бы два раза не вызывать nextElement(), потому что ошибка
            System.out.println(s + " = " + req.getParameter(s));
        }
        System.out.println();

        // получение имени параметра и нескольких значений параметра
        // http://localhost:8082/EE_war/Servlet2?one=hello&two=hokku&abc=jonh&one=goky
        Map<String, String[]> parameterMap = req.getParameterMap();
        // в ключ передаётся имя параметра, в массив передаются значения параметров
        for (Map.Entry<String, String[]> r:
             parameterMap.entrySet()) {
            System.out.print(r.getKey() + " = ");
            for (String g:
                 r.getValue()) {
                    System.out.print(g + ", ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println(req.getRequestURI()); // /EE_war/Servlet2
        System.out.println(req.getRequestURL()); // http://localhost:8082/EE_war/Servlet2
        System.out.println(req.getServletPath()); // /Servlet2
        System.out.println(req.getRemoteHost()); // 0:0:0:0:0:0:0:1
        System.out.println(req.getRemoteUser()); // null
        System.out.println(req.getLocalPort()); // 8082
        System.out.println();
        System.out.println(req.getQueryString()); // one=hello&two=hokku&abc=jonh&one=goky (то что после ?)
        System.out.println();
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
}
