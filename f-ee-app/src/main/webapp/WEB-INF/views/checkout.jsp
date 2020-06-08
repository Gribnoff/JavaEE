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
        <div class="d-flex justify-content-around">
            <c:url value="/shop/order" var="orderUrl">
                <c:param name="id" value="${requestScope.order.id}" />
            </c:url>
            <a class="btn btn-secondary" href="${orderUrl}">Просмотреть заказ</a>
            <a class="btn btn-secondary" href="<c:url value="/shop/home"/>">Вернуться в магазин</a>
        </div>
    </div>
</body>
</html>
