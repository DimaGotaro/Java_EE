<%--
  Created by IntelliJ IDEA.
  User: dima
  Date: 14.06.2022
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page import="com.example.ee.Calculator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String header = "Apache Tomcat";
    String[] mas = {"Tom", "Bob", "Markus"};
%>
<%!
    int square(int h) {
        return h * h;
    }
%>
<%
    pageContext.setAttribute("name2", "Tom");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>First JSP App</title>
</head>
<body>
<h2><%= header %></h2>
<p>Today <%= new java.util.Date() %></p>
<h3>2 + 2 = <%= 2+2 %></h3>
<h3>2 < 3 = <%= 2<3 %></h3>
<h3><%= new String("ronin").toUpperCase() %></h3>
<h3><%
    for (int i = 0; i < 2; i++) {
        out.println("Hello " + i);
    }
%></h3>
<p>Names: </p>
<%
    for (String v:
         mas) {
        out.println("<li>" + v + "</li>");
    }
%>
<p>
    <%= square(5) %>
</p>
<p>
    <%
        for (int i = 0; i < 3; i++) {
            out.println(square(i));
        }
    %>
</p>
<p><%= new Date()%></p>
<p>Квадрат числа 3 = <%= new Calculator().square2(3)%></p>
<jsp:include page="header.html" /> <%-- вложение из другой страницы--%>
<p>Main Content 1</p>
<p>Main Content 2</p>
<p>Main Content 3</p>
<jsp:include page="footer.jsp" />
<p>Name: ${name}</p>
<p>Age: ${age}</p>
<p>Gender: ${gender}</p>
<br>
<p>Users:</p>
<p>Name First: ${pageScope.name2}</p> <%--requestScope, sessionScope или applicationScope--%>
<p>Second: ${users[1]}</p>
<p>Third: ${users[2]}</p>
<br>
<p>Param user: ${user.name}</p>
<p>Param user: ${user.age}</p>
</body>
</html>
