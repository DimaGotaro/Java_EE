package com.example.ee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/Servlet4")
public class Servlet4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // как сжать весь код HTML в gzip и послать его на сервер
//        if (req.getHeader("accept-encoding").contains("gzip")) { // сравниваем на равенство два значения
//            resp.setHeader("Content-Encoding", "gzip"); // задаём заголовок
//            PrintWriter printWriter = new PrintWriter(new GZIPOutputStream(resp.getOutputStream()));
//            printWriter.write("Hello World!");
//            printWriter.flush();
//            printWriter.close();
//        }
        // установка статуса
//        resp.setStatus(HttpServletResponse.SC_OK);

        // переход на страницу
//        resp.sendRedirect("/EE_war/Servlet3");

        // переход на страницу с ошибкой
//        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "notorius");

        // установка перезагрузки страницы с интервалом 2 сек
//        resp.setHeader("Refresh", "2");

        // переход на страницу через 4 секунды
//        resp.setHeader("Refresh", "4; URL=https://www.google.com");

        // Cookies - в хроме F12
        // получили куки
        Cookie[] cookies = req.getCookies();
        for (Cookie c:
             cookies) {
            System.out.println(c.getName());
            System.out.println(c.getValue());
            System.out.println(c.getMaxAge()); // -1 - по умолчанию, бесконечность(время до удаления)
        }
        // создали куки
        Cookie cookie = new Cookie("testc", "Jordano");

        // удалить куки через 5 сек
//        cookie2.setMaxAge(5);

        // доступ к куки только определённых страниц
        cookie.setPath("/EE_war/Servlet3");

        // какие домены(папки в проекте) могут видеть куки
//        cookie.setDomain("webapp.WEB-INF.localhost");

        // отправили куки, через 5 сек нечего отправлять
        resp.addCookie(cookie);

        // Сессии, блок инфы которыс связан с id
        HttpSession session = req.getSession();

        // получим и выведем атрибуты сессии, пока пусто
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String x = attributeNames.nextElement();
            System.out.println(x + " = " + session.getAttribute(x));
        }

        // добавить в сессию атрибуты
        session.setAttribute("one", "two"); // вывод в консоль - one = two

        // сессии не безграничны по времени, выведем период после которого сессия удалится
        System.out.println(session.getMaxInactiveInterval()); // 1800 мс = 30 мин - по умолчанию

        Person tom = (Person) session.getAttribute("person");
        if (tom == null) {
            tom = new Person("Tom", 23);
            session.setAttribute("person", tom);
        }
        else {
            System.out.println(tom.getName() + " " + tom.getAge());
            // удаление из сессии объекта с ключом person
            session.removeAttribute("person");
        }
    }
}
class Person {
    String name;
    int age;
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
