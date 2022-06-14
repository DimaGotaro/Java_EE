<%--
  Created by IntelliJ IDEA.
  User: dima
  Date: 14.06.2022
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
<h3>Name: <%= request.getParameter("username")%></h3>
<h3>Gender: <%= request.getParameter("gender")%></h3>
<h3>Country: <%= request.getParameter("country")%></h3>
<ul>Courses: <%
    String[] cor = request.getParameterValues("courses");
    if (cor != null) {
        for (String v:
             cor) {
            out.println("<li>" + v + "</li>");
        }
    }
%></ul>
</body>
</html>
