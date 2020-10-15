<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@page import="org.logos.dao.User" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>

<html>
<head>
    <title>Login page</title>
</head>
<body>
<table>
    <tr>
        <th>Firstname</th>
        <th>Lastname</th>
        <th>Email</th>
    </tr>
    <%
        List<User> users = (ArrayList) request.getAttribute("users");
        for (User user : users) {
    %>
    <tr>
        <td><%=user.getFirstName()%></td>
        <td><%=user.getLastName()%></td>
        <td><%=user.getEmail()%></td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
