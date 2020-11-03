<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.10.2020
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" session="true" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Cabinet</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">

<%--    <link rel="stylesheet" href="style.css">--%>

</head>
<body>

<div class="login-page">
<%--    <nav>--%>
<%--        <ul>--%>
<%--            <li><h3>Welcome user : ${userName} <b> (${userEmail}) </b></h3></li>--%>
<%--            <li><a href="createProduct.jsp">Create Product</a></li>--%>
<%--            <li><a href="updateProduct.jsp">Update Product</a></li>--%>
<%--            <li><a href="existsProductId.jsp">Exists Id Product</a></li>--%>

<%--        </ul>--%>
<%--    </nav>--%>

    <h2>Welcome user : ${userName} <b> (${userEmail}) </b></h2>

    <button type="button" id="logout">Logout</button>

    <a href="createProduct.jsp">Create Product</a>

<%--    <a href="updateProduct.jsp">Update Product</a>--%>

<%--    <a href="existsProductId.jsp">Exists Id Product</a>--%>

    <h3>All products</h3>
    <div id="all-products"></div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>

<script src="js/main.js"></script>
<script src="js/cabinet.js"></script>
</body>
</html>
