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

        <table class="table table-hover">
            <thead class="thead-dark">
            <tr>
                <th>Номер заказа</th>
                <th>Сумма</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${requestScope.orders}">
                <tr>
                    <td>
                        <c:url var="orderUrl" value="/shop/order">
                            <c:param name="id" value="${order.id}" />
                        </c:url>
                        <a href="${orderUrl}">Заказ №<c:out value="${order.id}" /></a>
                    </td>
                    <td>
                        <c:out value="${order.price}" /> руб.
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="d-flex justify-content-end">
            <a class="btn btn-secondary" href="<c:url value="/shop/catalog"/>">В магазин</a>
        </div>
    </div>
</body>
</html>
