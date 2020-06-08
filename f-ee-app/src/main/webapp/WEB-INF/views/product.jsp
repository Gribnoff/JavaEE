<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="common/head.jsp" />
</head>
<body>
<div class="container">
    <jsp:include page="common/navbar.jsp" />
    <div class="d-flex justify-content-center">
        <h1><%= request.getAttribute("title")%></h1>
    </div>
    <p>
        <c:out value="${requestScope.product.description}" />
    </p>
    <div class="d-flex justify-content-between">
        <c:url value="/shop/cart/add" var="addToCartUrl">
            <c:param name="id" value="${requestScope.product.id}" />
        </c:url>
        <a class="btn btn-secondary" href="${addToCartUrl}">В корзину</a>
        <h5>Цена: <c:out value="${requestScope.product.price}" /> руб</h5>
    </div>
</div>
</body>
</html>